package advisor.spotify_api;

import advisor.Config;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NewReleases {
    public static List<String> getAll() {
        HttpResponse<String> response = Response.get(Config.getNewReleasesUrl());

        if (response == null) {
            return Collections.emptyList();
        }

        JsonArray jsonArray = Response.parseResponse(response)
                .get("albums")
                .getAsJsonObject()
                .get("items").getAsJsonArray();

        ArrayList<String> newReleasesList = new ArrayList<>();

        for (JsonElement jsonElement : jsonArray) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            String name = jsonObject.get("name").getAsString();
            String url = jsonObject.get("external_urls")
                    .getAsJsonObject()
                    .get("spotify")
                    .getAsString();

            ArrayList<String> artists = new ArrayList<>();

            for (JsonElement artist : jsonObject.get("artists").getAsJsonArray()) {
                artists.add(artist.getAsJsonObject()
                        .get("name")
                        .getAsString());
            }

            newReleasesList.add(name + "\n" + artists + "\n" + url);
        }

        return newReleasesList;
    }
}
