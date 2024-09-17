package advisor;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Client {
    private static HttpClient client;
    private static Map<String, String> categories;

    public Client() {
        client = HttpClient.newBuilder().build();
        categories = new HashMap<>();
    }

    public void getAccessToken() {
        System.out.println(Messages.MAKINK);

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
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(Messages.SUCCESS);

        Config.ACCESS_TOKEN = JsonParser.parseString(response.body())
                .getAsJsonObject()
                .get("access_token")
                .getAsString();
    }

    private static HttpResponse<String> getStringHttpResponse(String url) {
        HttpRequest request = HttpRequest.newBuilder()
                .header("Authorization", "Bearer " + Config.ACCESS_TOKEN)
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return response;
    }

    public String getNewReleases() {
        HttpResponse<String> response = getStringHttpResponse(Config.getNewReleasesUrl());

        JsonArray jsonArray = JsonParser.parseString(response.body())
                .getAsJsonObject()
                .get("albums")
                .getAsJsonObject()
                .get("items").getAsJsonArray();

        StringBuilder listNewReleases = new StringBuilder();

        for (JsonElement jsonElement : jsonArray) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            String name = jsonObject.get("name").getAsString();
            String url = jsonObject.get("external_urls").getAsJsonObject().get("spotify").getAsString();

            ArrayList<String> artists = new ArrayList<>();

            for (JsonElement artist : jsonObject.get("artists").getAsJsonArray()) {
                artists.add(artist.getAsJsonObject().get("name").getAsString());
            }

            listNewReleases.append(name).append("\n")
                    .append(artists).append("\n")
                    .append(url).append("\n\n");
        }

        return listNewReleases.toString();
    }

    public String getFeaturedPlaylists() {
        HttpResponse<String> response = getStringHttpResponse(Config.getFeaturedPlaylistsUrl());

        JsonArray jsonArray = JsonParser.parseString(response.body())
                .getAsJsonObject()
                .get("playlists")
                .getAsJsonObject()
                .get("items")
                .getAsJsonArray();

        StringBuilder playlistsList = new StringBuilder();

        for (JsonElement playlist : jsonArray) {
            playlistsList.append(playlist.getAsJsonObject()
                            .get("name")
                            .getAsString())
                    .append("\n")
                    .append(playlist.getAsJsonObject()
                            .get("external_urls")
                            .getAsJsonObject()
                            .get("spotify")
                            .getAsString())
                    .append("\n\n");
        }

        return playlistsList.toString();
    }

    public String getAllCategories() {
        HttpResponse<String> response = getStringHttpResponse(Config.getCategoriesUrl());

        JsonArray jsonArray = JsonParser.parseString(response.body())
                .getAsJsonObject()
                .get("categories")
                .getAsJsonObject()
                .get("items").getAsJsonArray();

        StringBuilder listCategories = new StringBuilder();

        for (JsonElement category : jsonArray) {
            String name = category.getAsJsonObject().get("name").getAsString();
            String id = category.getAsJsonObject().get("id").getAsString();

            listCategories.append(name).append("\n");
            categories.put(name, id);
        }

        return listCategories.toString();
    }

    public String getCategoriesPlaylists(String name) {
        if (categories.isEmpty()) {
            getAllCategories();
        }

        if (!categories.containsKey(name)) {
            return Messages.UNKNOWN_CATEGORY_NAME.toString();
        }

        HttpResponse<String> response = getStringHttpResponse(Config.getCategoryPlaylistsUrl(categories.get(name)));

        JsonObject jsonObject = JsonParser.parseString(response.body()).getAsJsonObject();

        if (jsonObject.has("error")) {
            return jsonObject.getAsJsonObject("error").get("message").getAsString();
        }

        JsonArray jsonArray = JsonParser.parseString(response.body())
                .getAsJsonObject()
                .get("playlists")
                .getAsJsonObject()
                .get("items").getAsJsonArray();

        StringBuilder playlistsList = new StringBuilder();

        for (JsonElement playlist : jsonArray) {
            playlistsList.append(playlist.getAsJsonObject().
                            get("name")
                            .getAsString())
                    .append("\n")
                    .append(playlist.getAsJsonObject()
                            .get("external_urls")
                            .getAsJsonObject()
                            .get("spotify")
                            .getAsString())
                    .append("\n\n");
        }

        return playlistsList.toString();
    }
}
