package designer;
import java.util.List;
import java.util.Map;

import designer.Config.StandardSymbolsProbability;
public class Probabilities {
	private List<StandardSymbolsProbability> standardSymbols;
    private BonusSymbolsProbability bonusSymbols;

    // Constructor, getters, and setters
    public Probabilities() {}

    public List<StandardSymbolsProbability> getStandardSymbols() {
        return standardSymbols;
    }

    public void setStandardSymbols(List<StandardSymbolsProbability> standardSymbols) {
        this.standardSymbols = standardSymbols;
    }

    public BonusSymbolsProbability getBonusSymbols() {
        return bonusSymbols;
    }

    public void setBonusSymbols(BonusSymbolsProbability bonusSymbols) {
        this.bonusSymbols = bonusSymbols;
    }
}
