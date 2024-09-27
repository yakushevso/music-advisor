package advisor;

import advisor.auth.Client;
import advisor.auth.Server;
import advisor.strategy.*;
import advisor.view.ConsoleView;
import advisor.view.Messages;
import advisor.view.View;

import java.util.List;
import java.util.Scanner;

public class MusicAdvisor {
    private final Scanner sc = new Scanner(System.in);
    private final AdvisorContext context = new AdvisorContext();
    private final View view = new ConsoleView();
    private Paginator paginator;

    public void start() {
        while (true) {
            String input = getInput();

            if (input.equals("auth")) {
                Server.getAuthCode();
                Client.getAccessToken();
                continue;
            }

            if (Config.ACCESS_TOKEN == null) {
                view.showMessage(Messages.ACCESS);
                continue;
            }

            switch (input) {
                case "new" -> executeStrategy(new NewReleasesAdvisorStrategy());
                case "featured" -> executeStrategy(new FeaturedPlaylistsAdvisorStrategy());
                case "categories" -> executeStrategy(new AllCategoriesAdvisorStrategy());
                case "playlists" -> executeStrategy(new CategoriesPlaylistsAdvisorStrategy(getInputLine()));
                case "next" -> navigateNext();
                case "prev" -> navigatePrev();
                case "exit" -> {
                    return;
                }
                default -> view.showMessage(Messages.INVALID);
            }
        }
    }

    private void executeStrategy(AdvisorStrategy strategy) {
        context.setStrategy(strategy);
        List<String> list = context.execute();

        if (list.isEmpty()) {
            view.showMessage(Messages.NO_MORE_PAGES);
            paginator = null;
            return;
        }

        paginator = new Paginator(list, Config.SIZE_PAGE);
        displayCurrentPage();
    }

    private void navigateNext() {
        if (paginator == null) {
            view.showMessage(Messages.NO_MORE_PAGES);
            return;
        }

        if (paginator.hasNext()) {
            paginator.nextPage();
            displayCurrentPage();
        } else {
            view.showMessage(Messages.NO_MORE_PAGES);
        }
    }

    private void navigatePrev() {
        if (paginator == null) {
            view.showMessage(Messages.NO_MORE_PAGES);
            return;
        }

        if (paginator.hasPrev()) {
            paginator.prevPage();
            displayCurrentPage();
        } else {
            view.showMessage(Messages.NO_MORE_PAGES);
        }
    }

    private void displayCurrentPage() {
        List<String> pageItems = paginator.getCurrentPage();
        if (pageItems.isEmpty()) {
            view.showMessage(Messages.NO_MORE_PAGES);
            paginator = null;
            return;
        }
        view.showPage(pageItems);
        view.showPageInfo(paginator.getCurrentPageNumber(), paginator.getTotalPages());
    }

    private String getInput() {
        return sc.next();
    }

    private String getInputLine() {
        String input = sc.nextLine().trim();
        return input.isEmpty() ? "" : input;
    }
}
