package advisor;

public class MusicAdvisor {
    public void run() {
        while (true) {
            switch (new UI().getUserInput()) {
                case "new" -> System.out.println("""
                        ---NEW RELEASES---
                        Mountains [Sia, Diplo, Labrinth]
                        Runaway [Lil Peep]
                        The Greatest Show [Panic! At The Disco]
                        All Out Life [Slipknot]""");
                case "featured" -> System.out.println("""
                        ---FEATURED---
                        Mellow Morning
                        Wake Up and Smell the Coffee
                        Monday Motivation
                        Songs to Sing in the Shower""");
                case "categories" -> System.out.println("""
                        ---CATEGORIES---
                        Top Lists
                        Pop
                        Mood
                        Latin""");
                case "playlists Mood" -> System.out.println("""
                        ---MOOD PLAYLISTS---
                        Walk Like A Badass \s
                        Rage Beats \s
                        Arab Mood Booster \s
                        Sunday Stroll""");
                case "exit" -> {
                    System.out.println("---GOODBYE!---");
                    return;
                }
            }
        }
    }
}
