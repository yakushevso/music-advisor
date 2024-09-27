package advisor.strategy;

import advisor.spotify_api.Playlists;

import java.util.Collections;
import java.util.List;

public class FeaturedPlaylistsAdvisorStrategy implements AdvisorStrategy {
    @Override
    public List<String> execute() {
        try {
            return Playlists.getFeatured();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }
}
