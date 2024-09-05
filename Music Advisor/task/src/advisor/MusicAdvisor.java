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
                    System.out.println("---GOODBYE!---");
                    return;
                }
                default -> System.out.println("Invalid command. Please try again.");
            }
        }
    }

    private void handleAuth() {
        System.out.println("""
                https://accounts.spotify.com/authorize?client_id=5a0fc1b91aaf4a4999e93419312f9d811&redirect_uri=http://localhost:8080&response_type=code
                ---SUCCESS---""");
        isAuth = true;
    }

    private void executeIfAuth(Runnable action) {
        if (isAuth) {
            action.run();
        } else {
            requestAuth();
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

    private void requestAuth() {
        System.out.println("Please, provide access for application.");
    }
}
