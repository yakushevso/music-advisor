package advisor.view;

import java.util.List;

public class ConsoleView implements View{
    @Override
    public void showPage(List<String> list) {
        list.forEach(System.out::println);
    }

    @Override
    public void showMessage(Messages messages) {
        System.out.println(messages);
    }

    @Override
    public void showPageInfo(int currentPage, int totalPages) {
        System.out.printf(Messages.PAGE_OF + "%n", currentPage, totalPages);
    }
}
