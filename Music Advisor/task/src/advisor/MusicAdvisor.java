package advisor;

public class MusicAdvisor {
    private boolean isAuth;
    private final UI ui;
    private final Server server;
    private final Client client;

    public MusicAdvisor() {
        isAuth = false;
        ui = new UI();
        server = new Server();
        client = new Client();
    }

    public void run() {
        while (true) {
            String mode = ui.getUserInput();

            switch (mode) {
                case "auth" -> handleAuth();
                case "new" -> executeIfAuth(this::handleNewReleases);
                case "featured" -> executeIfAuth(this::handleFeatured);
                case "categories" -> executeIfAuth(this::handleCategories);
                case "playlists" -> executeIfAuth(this::handlePlaylists);
                case "exit" -> {
                    return;
                }
                default -> System.out.println(Messages.INVALID);
            }
        }
    }

    private void handleAuth() {
        server.getAuthCode();
        client.getAccessToken();
        isAuth = true;
    }

    private void executeIfAuth(Runnable action) {
        if (isAuth) {
            action.run();
        } else {
            System.out.println(Messages.ACCESS);
        }
    }

    private void handleNewReleases() {
        System.out.print(client.getNewReleases());
    }

    private void handleFeatured() {
        System.out.print(client.getFeaturedPlaylists());
    }

    private void handleCategories() {
        System.out.print(client.getAllCategories());
    }

    private void handlePlaylists() {
        System.out.print(client.getCategoriesPlaylists(ui.getUserInputLine()));
    }
}
