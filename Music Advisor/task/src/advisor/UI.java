package advisor;

import java.util.Scanner;

public class UI {
    private final Scanner sc = new Scanner(System.in);

    public String getUserInput() {
        return sc.nextLine();
    }
}
