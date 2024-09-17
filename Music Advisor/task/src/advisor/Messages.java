package advisor;

public enum Messages {
    ACCESS("Please, provide access for application."),
    CODE_RECEIVED("code received"),
    FAILED_RESPONSE("Authorization code not found. Try again."),
    INVALID("Invalid command. Please try again."),
    MAKINK("Making http request for access_token..."),
    SUCCESS("Success!"),
    SUCCESSFUL_RESPONSE("Got the code. Return back to your program."),
    UNKNOWN_CATEGORY_NAME("Unknown category name."),
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
