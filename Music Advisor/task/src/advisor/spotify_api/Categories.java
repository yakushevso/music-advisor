package advisor.spotify_api;

import advisor.Config;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.net.http.HttpResponse;
import java.util.*;

public class Categories {
    private static final Map<String, String> categories = new HashMap<>();

    public static Map<String, String> getCategories() {
        if (categories.isEmpty()) {
            getAll();
        }

        return categories;
    }

    public static List<String> getAll() {
        HttpResponse<String> response = Response.get(Config.getCategoriesUrl());

        if (response == null) {
            return Collections.emptyList();
        }

        JsonArray jsonArray = Response.parseResponse(response)
                .get("categories")
                .getAsJsonObject()
                .get("items").getAsJsonArray();

        ArrayList<String> listCategories = new ArrayList<>();

        for (JsonElement category : jsonArray) {
            String name = category.getAsJsonObject()
                    .get("name")
                    .getAsString();
            String id = category.getAsJsonObject()
                    .get("id")
                    .getAsString();

            listCategories.add(name);
            categories.put(name, id);
        }

        return listCategories;
    }
}
