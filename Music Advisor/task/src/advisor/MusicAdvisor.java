package advisor;

public class MusicAdvisor {
    private boolean isAuth = false;

    public void run() {
        UI ui = new UI();

        while (true) {
            String mode = ui.getUserInput();

            switch (mode) {
                case "auth" -> handleAuth();
                case "new" -> executeIfAuth(this::handleNewReleases);
                case "featured" -> executeIfAuth(this::handleFeatured);
                case "categories" -> executeIfAuth(this::handleCategories);
                case "playlists Mood" -> executeIfAuth(this::handleMoodPlaylists);
                case "exit" -> {
                    System.out.println(Messages.GOODBYE);
                    return;
                }
                default -> System.out.println(Messages.INVALID);
            }
        }
    }

    private void handleAuth() {
        Config.setURI();
        System.out.println(Messages.USE_LINK + "\n" + Config.URI);
        new Server().run();
        System.out.println(Messages.MAKINK);
        Config.setTokenLink();
        Config.ACCESS_TOKEN = new Client().getAccessToken();
        System.out.println(Messages.RESPONCE + "\n" + Config.ACCESS_TOKEN);
        System.out.println(Messages.SUCCESS);
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
        System.out.println("""
                ---NEW RELEASES---
                Mountains [Sia, Diplo, Labrinth]
                Runaway [Lil Peep]
                The Greatest Show [Panic! At The Disco]
                All Out Life [Slipknot]""");
    }

    private void handleFeatured() {
        System.out.println("""
                ---FEATURED---
                Mellow Morning
                Wake Up and Smell the Coffee
                Monday Motivation
                Songs to Sing in the Shower""");
    }

    private void handleCategories() {
        System.out.println("""
                ---CATEGORIES---
                Top Lists
                Pop
                Mood
                Latin""");
    }

    private void handleMoodPlaylists() {
        System.out.println("""
                ---MOOD PLAYLISTS---
                Walk Like A Badass
                Rage Beats
                Arab Mood Booster
                Sunday Stroll""");
    }
}
