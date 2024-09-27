package advisor.strategy;

import java.util.List;

public class AdvisorContext {
    private AdvisorStrategy strategy;

    public void setStrategy(AdvisorStrategy strategy) {
        this.strategy = strategy;
    }

    public List<String> execute() {
        return this.strategy.execute();
    }
}
