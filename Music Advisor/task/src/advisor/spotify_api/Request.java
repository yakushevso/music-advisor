package advisor.spotify_api;

import advisor.Config;

import java.net.URI;
import java.net.http.HttpRequest;

public class Request {
    public static HttpRequest get(String uri) {
        return HttpRequest.newBuilder()
                .header("Authorization", "Bearer " + Config.ACCESS_TOKEN)
                .uri(URI.create(uri))
                .GET()
                .build();
    }
}
