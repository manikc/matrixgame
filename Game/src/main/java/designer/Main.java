package designer;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String configFilePath = args[0];
        int bettingAmount = Integer.parseInt(args[1]);

        try {
            ScratchGame scratchGame = new ScratchGame();
            String result = scratchGame.playGame(configFilePath, bettingAmount);
            System.out.println(result);
        } catch (IOException e) {
            System.err.println("Error reading config file: " + e.getMessage());
        }
    }
}