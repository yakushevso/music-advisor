package advisor;

import java.util.Scanner;

public class UI {
    private final Scanner sc = new Scanner(System.in);

    public String getUserInput() {
        return sc.next();
    }

    public String getUserInputLine() {
        String input = sc.nextLine();

        return !input.isEmpty() ? input.substring(1) : "";
    }
}
