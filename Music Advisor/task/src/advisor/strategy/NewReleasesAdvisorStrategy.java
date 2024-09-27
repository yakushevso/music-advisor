package advisor.strategy;

import advisor.spotify_api.NewReleases;

import java.util.Collections;
import java.util.List;

public class NewReleasesAdvisorStrategy implements AdvisorStrategy {

    @Override
    public List<String> execute() {
        try {
            return NewReleases.getAll();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }
}
