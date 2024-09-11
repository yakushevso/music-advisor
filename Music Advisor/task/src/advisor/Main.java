package advisor;

public class Main {
    public static void main(String[] args) {
        if (args.length > 1 && args[0].equals("-access")) {
            Config.SERVER_PATH = args[1];
        }

        new MusicAdvisor().run();
    }
}
