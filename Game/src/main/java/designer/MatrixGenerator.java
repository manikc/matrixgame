package designer;


import java.util.*;
import java.util.Random;

public class MatrixGenerator {
    private final Config config;
    private final Random random;

    public MatrixGenerator(Config config) {
        this.config = config;
        this.random = new Random();
    }

    public String[][] generateMatrix() {
        String[][] matrix = new String[config.getRows()][config.getColumns()];

        // Generate matrix with symbols
        for (int i = 0; i < config.getRows(); i++) {
            for (int j = 0; j < config.getColumns(); j++) {
                if (random.nextDouble() < getStandardSymbolsProbability(j, i)) {
                    matrix[i][j] = getRandomStandardSymbol();
                } else if (random.nextDouble() < getBonusSymbolsProbability()) {
                    matrix[i][j] = getRandomBonusSymbol();
                } else {
                    // No symbol generated
                    matrix[i][j] = "-";
                }
            }
        }

        return matrix;
    }

    private double getStandardSymbolsProbability(int column, int row) {
        for (Config.StandardSymbolsProbability prob : config.getProbabilities().getStandardSymbols()) {
            if (prob.getColumn() == column && prob.getRow() == row) {
                double totalProbability = prob.getSymbols().values().stream().mapToInt(Integer::intValue).sum();
                String[] symbols = prob.getSymbols().keySet().toArray(new String[0]);
                for (int i = 0; i < symbols.length; i++) {
                    symbols[i] = symbols[i].toUpperCase();
                }
                double randomProbability = random.nextDouble() * totalProbability;
                for (int i = 0; i < symbols.length; i++) {
                    randomProbability -= prob.getSymbols().get(symbols[i]);
                    if (randomProbability <= 0) {
                        return 1.0 / symbols.length;
                    }
                }
            }
        }
        return 0.0;
    }

    private double getBonusSymbolsProbability() {
        double totalProbability = config.getProbabilities().getBonusSymbols().getSymbols().values().stream().mapToInt(Integer::intValue).sum();
        double randomProbability = random.nextDouble() * totalProbability;
        String[] symbols = config.getProbabilities().getBonusSymbols().getSymbols().keySet().toArray(new String[0]);
        for (int i = 0; i < symbols.length; i++) {
            symbols[i] = symbols[i].toUpperCase();
        }
        for (int i = 0; i < symbols.length; i++) {
            randomProbability -= config.getProbabilities().getBonusSymbols().getSymbols().get(symbols[i]);
            if (randomProbability <= 0) {
                return 1.0 / symbols.length;
            }
        }
        return 0.0;
    }

    private String getRandomStandardSymbol() {
        String[] symbols = config.getSymbols().keySet().toArray(new String[0]);
        for (int i = 0; i < symbols.length; i++) {
            symbols[i] = symbols[i].toUpperCase();
        }
        return symbols[random.nextInt(symbols.length)];
    }

    private String getRandomBonusSymbol() {
        String[] symbols = config.getProbabilities().getBonusSymbols().getSymbols().keySet().toArray(new String[0]);
        for (int i = 0; i < symbols.length; i++) {
            symbols[i] = symbols[i].toUpperCase();
        }
        return symbols[random.nextInt(symbols.length)];
    }
}

