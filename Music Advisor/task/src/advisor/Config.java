package advisor;

public class Config {
    public static final String CLIENT_ID = "5a0fc1b91aaf4a4999e9341931A2f9d811";
    public static final String CLIENT_SECRET = "ddb48d383fef4730a5fdd226d5c9649c2";
    public static final String REDIRECT_URI = "http://localhost:8080";
    public static String ACCESS_TOKEN;
    public static String AUTH_CODE;
    public static String SERVER_PATH = "https://accounts.spotify.com";
    public static String TOKEN_LINK;
    public static String URI;

    public static void setURI() {
        URI = Config.SERVER_PATH + "/authorize" +
                "?client_id=" + Config.CLIENT_ID +
                "&redirect_uri=" + Config.REDIRECT_URI +
                "&response_type=code";
    }

    public static void setTokenLink() {
        TOKEN_LINK = SERVER_PATH + "/api/token";
    }
}
