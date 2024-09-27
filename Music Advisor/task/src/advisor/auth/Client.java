package advisor.auth;

import advisor.Config;
import advisor.view.Messages;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Client {
    public static void getAccessToken() {
        System.out.println(Messages.MAKING);

        HttpRequest request = HttpRequest.newBuilder()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .uri(URI.create(Config.getTokenLink()))
                .POST(HttpRequest.BodyPublishers.ofString(
                        "grant_type=authorization_code" +
                                "&code=" + Config.AUTH_CODE +
                                "&client_id=" + Config.CLIENT_ID +
                                "&client_secret=" + Config.CLIENT_SECRET +
                                "&redirect_uri=" + Config.REDIRECT_URI))
                .build();

        HttpResponse<String> response;
        try {
            response = HttpClient.newBuilder()
                    .build()
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(Messages.SUCCESS);

        Config.ACCESS_TOKEN = JsonParser.parseString(response.body())
                .getAsJsonObject()
                .get("access_token")
                .getAsString();
    }
}
