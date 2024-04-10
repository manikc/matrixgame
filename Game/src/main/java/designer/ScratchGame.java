package designer;

import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ScratchGame {
	private final ObjectMapper objectMapper = new ObjectMapper();

    public String playGame(String configFilePath, int bettingAmount) throws IOException {
        Config config = loadConfig(configFilePath);
        MatrixGenerator matrixGenerator = new MatrixGenerator(config);
        String[][] matrix = matrixGenerator.generateMatrix();
        WinningCombinationsChecker winningCombinationsChecker = new WinningCombinationsChecker(config);
        Map<String, List<String>> appliedWinningCombinations = winningCombinationsChecker.checkWinningCombinations(matrix);
        RewardCalculator rewardCalculator = new RewardCalculator(config, appliedWinningCombinations);
        int reward = rewardCalculator.calculateReward(bettingAmount);
        String appliedBonusSymbol = rewardCalculator.getAppliedBonusSymbol();

        return generateOutput(matrix, reward, appliedWinningCombinations, appliedBonusSymbol);
    }

    private Config loadConfig(String configFilePath) throws IOException {
        return objectMapper.readValue(new File(configFilePath), Config.class);
    }

    private String generateOutput(String[][] matrix, int reward, Map<String, List<String>> appliedWinningCombinations, String appliedBonusSymbol) {
        // Generate JSON output
        return "{\"matrix\": " + objectMapper.valueToTree(matrix) +
                ", \"reward\": " + reward +
                ", \"applied_winning_combinations\": " + objectMapper.valueToTree(appliedWinningCombinations) +
                ", \"applied_bonus_symbol\": \"" + appliedBonusSymbol + "\"}";
    }
}
