package advisor.view;

public enum Messages {
    ACCESS("Please, provide access for application."),
    CODE_RECEIVED("code received"),
    FAILED_RESPONSE("Authorization code not found. Try again."),
    INVALID("Invalid command. Please try again."),
    MAKING("Making http request for access_token..."),
    SUCCESS("Success!"),
    SUCCESSFUL_RESPONSE("Got the code. Return back to your program."),
    UNKNOWN_CATEGORY_NAME("Unknown category name."),
    USE_LINK("use this link to request the access code:"),
    WAITING("waiting for code..."),
    PAGE_OF("---PAGE %d OF %d---"),
    NO_MORE_PAGES("No more pages.");

    private final String message;

    Messages(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
