package designer;

import java.io.ObjectInputFilter.Config;
import java.util.List;
import java.util.Map;

public class RewardCalculator {
    private final Config config;
    private final Map<String, List<String>> appliedWinningCombinations;

    public RewardCalculator(Config config, Map<String, List<String>> appliedWinningCombinations) {
        this.config = config;
        this.appliedWinningCombinations = appliedWinningCombinations;
    }

    public int calculateReward(int bettingAmount) {
        int totalReward = 0;

        for (Map.Entry<String, List<String>> entry : appliedWinningCombinations.entrySet()) {
            String symbol = entry.getKey();
            List<String> appliedCombinations = entry.getValue();

            for (String combination : appliedCombinations) {
                Config.WinCombination winCombination = config.getWinCombinations().get(combination);

                if (winCombination != null) {
                    double symbolMultiplier = config.getSymbols().get(symbol).getRewardMultiplier();
                    double combinationMultiplier = winCombination.getRewardMultiplier();
                    int count = winCombination.getCount();

                    totalReward += bettingAmount * symbolMultiplier * combinationMultiplier * count;
                }
            }
        }

        return totalReward;
    }

    public String getAppliedBonusSymbol() {
        for (Map.Entry<String, List<String>> entry : appliedWinningCombinations.entrySet()) {
            String symbol = entry.getKey();
            List<String> appliedCombinations = entry.getValue();

            for (String combination : appliedCombinations) {
                Config.WinCombination winCombination = config.getWinCombinations().get(combination);

                if (winCombination != null && winCombination.getRewardMultiplier() > 1) {
                    return symbol;
                }
            }
        }

        return null;
    }
}

