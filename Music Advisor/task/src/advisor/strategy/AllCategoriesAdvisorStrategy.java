package advisor.strategy;

import advisor.spotify_api.Categories;

import java.util.Collections;
import java.util.List;

public class AllCategoriesAdvisorStrategy implements AdvisorStrategy {
    @Override
    public List<String> execute() {
        try {
            return Categories.getAll();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }
}
