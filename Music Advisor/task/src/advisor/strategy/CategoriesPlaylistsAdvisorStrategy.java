package advisor.strategy;

import advisor.view.Messages;
import advisor.spotify_api.Categories;
import advisor.spotify_api.Playlists;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class CategoriesPlaylistsAdvisorStrategy implements AdvisorStrategy {
    private final String name;

    public CategoriesPlaylistsAdvisorStrategy(String name) {
        this.name = name;
    }

    @Override
    public List<String> execute() {
        try {
            Map<String, String> categories = Categories.getCategories();

            if (!categories.containsKey(name)) {
                System.out.println(Messages.UNKNOWN_CATEGORY_NAME);
                return Collections.emptyList();
            }

            return Playlists.getCategories(categories.get(name));
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }
}
