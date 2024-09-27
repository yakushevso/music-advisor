package advisor.spotify_api;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Response {
    public static HttpResponse<String> get(String uri) {
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = Request.get(uri);

        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            return null;
        }
        return response;
    }

    public static JsonObject parseResponse(HttpResponse<String> response) {
        return JsonParser.parseString(response.body()).getAsJsonObject();
    }
}
