package advisor;

public class Main {
    public static void main(String[] args) {
        if (args.length > 1 && args[0].equals("-access")) {
            Config.SERVER_PATH = args[1];
        }
        if (args.length > 3 && args[2].equals("-resource")) {
            Config.API_BASE_URL = args[3];
        }
        if (args.length > 5 && args[4].equals("-page")) {
            Config.SIZE_PAGE = Integer.parseInt(args[5]);
        }

        new MusicAdvisor().start();
    }
}
