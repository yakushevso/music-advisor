package advisor;

public enum Messages {
    ACCESS("Please, provide access for application."),
    CODE_RECEIVED("code received"),
    FAILED_RESPONSE("Authorization code not found. Try again."),
    SUCCESSFUL_RESPONSE("Got the code. Return back to your program."),
    GOODBYE("---GOODBYE!---"),
    INVALID("Invalid command. Please try again."),
    MAKINK("making http request for access_token..."),
    RESPONCE("response:"),
    SUCCESS("---SUCCESS---"),
    USE_LINK("use this link to request the access code:"),
    WAITING("waiting for code...");

    private final String message;

    Messages(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
