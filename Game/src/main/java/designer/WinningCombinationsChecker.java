package designer;

import java.util.*;
public class WinningCombinationsChecker {
    private final Config config;

    public WinningCombinationsChecker(Config config) {
        this.config = config;
    }

    public Map<String, List<String>> checkWinningCombinations(String[][] matrix) {
        Map<String, List<String>> appliedWinningCombinations = new HashMap<>();

        for (Map.Entry<String, Config.WinCombination> entry : config.getWinCombinations().entrySet()) {
            String combinationName = entry.getKey();
            Config.WinCombination winCombination = entry.getValue();

            List<String> appliedCombinations = new ArrayList<>();
            switch (winCombination.getWhen()) {
                case "same_symbols":
                    appliedCombinations.addAll(checkSameSymbolsCombination(matrix, winCombination));
                    break;
                case "linear_symbols":
                    appliedCombinations.addAll(checkLinearSymbolsCombination(matrix, winCombination));
                    break;
            }

            if (!appliedCombinations.isEmpty()) {
                appliedWinningCombinations.put(combinationName, appliedCombinations);
            }
        }

        return appliedWinningCombinations;
    }

    private List<String> checkSameSymbolsCombination(String[][] matrix, Config.WinCombination winCombination) {
        List<String> appliedCombinations = new ArrayList<>();

        for (Map.Entry<String, Config.StandardSymbolsProbability> entry : config.getProbabilities().getStandardSymbols().stream()
                .collect(HashMap::new, (m, c) -> m.put(c.getColumn() + ":" + c.getRow(), c), HashMap::putAll).entrySet()) {
            String coordinates = entry.getKey();
            Config.StandardSymbolsProbability probability = entry.getValue();

            Set<String> symbolsInCombination = new HashSet<>();
            for (String coveredArea : winCombination.getCoveredAreas().get(0)) {
                String[] parts = coveredArea.split(":");
                int row = Integer.parseInt(parts[0]);
                int column = Integer.parseInt(parts[1]);
                symbolsInCombination.add(matrix[row][column]);
            }

            if (symbolsInCombination.size() == 1) {
                appliedCombinations.add(coordinates);
            }
        }

        return appliedCombinations;
    }

    private List<String> checkLinearSymbolsCombination(String[][] matrix, Config.WinCombination winCombination) {
        List<String> appliedCombinations = new ArrayList<>();

        for (String coveredArea : winCombination.getCoveredAreas().get(0)) {
            String[] parts = coveredArea.split(":");
            int row = Integer.parseInt(parts[0]);
            int column = Integer.parseInt(parts[1]);

            Set<String> symbolsInCombination = new HashSet<>();
            for (String position : coveredArea.split(",")) {
                String[] coordinates = position.split(":");
                int r = Integer.parseInt(coordinates[0]);
                int c = Integer.parseInt(coordinates[1]);
                symbolsInCombination.add(matrix[r][c]);
            }

            if (symbolsInCombination.size() == 1) {
                appliedCombinations.add(row + ":" + column);
            }
        }

        return appliedCombinations;
    }
}
