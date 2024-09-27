package advisor.spotify_api;

import advisor.Config;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Playlists {
    public static List<String> getFeatured() {
        return getPlaylists(Config.getFeaturedPlaylistsUrl());
    }

    public static List<String> getCategories(String name) {
        return getPlaylists(Config.getCategoryPlaylistsUrl(name));
    }

    private static List<String> getPlaylists(String uri) {
        HttpResponse<String> response = Response.get(uri);

        if (response == null) {
            return Collections.emptyList();
        }

        JsonObject jsonObject = Response.parseResponse(response);

        if (jsonObject.has("error")) {
            System.out.println(jsonObject.getAsJsonObject("error")
                    .get("message")
                    .getAsString());
            return Collections.emptyList();
        }

        JsonArray jsonArray = jsonObject
                .get("playlists")
                .getAsJsonObject()
                .get("items").getAsJsonArray();

        ArrayList<String> playlistsList = new ArrayList<>();

        for (JsonElement playlist : jsonArray) {
            String name = playlist.getAsJsonObject().
                    get("name")
                    .getAsString();

            String url = playlist.getAsJsonObject()
                    .get("external_urls")
                    .getAsJsonObject()
                    .get("spotify")
                    .getAsString();

            playlistsList.add(name + "\n" + url);
        }

        return playlistsList;
    }
}
