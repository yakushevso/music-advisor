package advisor;

public class Config {
    public static final String CLIENT_ID = "5a0fc1b91aaf4a4999e93419312f9d811";
    public static final String CLIENT_SECRET = "ddb48d383fef4730a5fdd226d5c9649c2";
    public static final String REDIRECT_URI = "http://localhost:8080";

    public static String SERVER_PATH = "https://accounts.spotify.com";
    public static String API_BASE_URL = "https://api.spotify.com";

    public static String ACCESS_TOKEN;
    public static String AUTH_CODE;

    public static String getAuthUri() {
        return SERVER_PATH + "/authorize" +
                "?client_id=" + CLIENT_ID +
                "&redirect_uri=" + REDIRECT_URI +
                "&response_type=code";
    }

    public static String getTokenLink() {
        return SERVER_PATH + "/api/token";
    }

    public static String getCategoriesUrl() {
        return API_BASE_URL + "/v1/browse/categories";
    }

    public static String getNewReleasesUrl() {
        return API_BASE_URL + "/v1/browse/new-releases";
    }

    public static String getFeaturedPlaylistsUrl() {
        return API_BASE_URL + "/v1/browse/featured-playlists";
    }

    public static String getCategoryPlaylistsUrl(String categoryId) {
        return String.format(API_BASE_URL + "/v1/browse/categories/%s/playlists", categoryId);
    }
}

