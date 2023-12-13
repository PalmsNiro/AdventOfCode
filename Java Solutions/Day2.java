import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class Day2 {
    Map<Character, Integer> rgb = new HashMap<>(Map.of('r', 12, 'g', 13, 'b', 14));
    List<String> games = new ArrayList<>();
    List<Integer> solveableGames = new ArrayList<>();
    int currentGame = 0;

    public static void main(String[] args) {
        Day2 dayTwoObj = new Day2();
        dayTwoObj.readGamesFromFile();
        dayTwoObj.printAll(dayTwoObj.getGames());
        for (String string : dayTwoObj.getGames()) {
            dayTwoObj.nextGame();
            if (dayTwoObj.solveable(string)) {
                dayTwoObj.addToSolveableGames(dayTwoObj.getCurrentGame());
            }
        }
        dayTwoObj.printAll(dayTwoObj.getSolveableGames());
        System.out.print("Amount of solveable Games: ");
        dayTwoObj.printSolveableGamesSize();
        dayTwoObj.sumAndPrintGameIDS();
    }

    public void sumAndPrintGameIDS() {
        int sum = 0;
        for (int numb : solveableGames) {
            sum += numb;
        }
        System.out.println("sum of IDS: " + sum);
    }

    public void printSolveableGamesSize() {
        System.out.println(solveableGames.size());
    }

    public List<Integer> getSolveableGames() {
        return solveableGames;
    }

    public int getCurrentGame() {
        return currentGame;
    }

    public void addToSolveableGames(int game) {
        solveableGames.add(game);
    }

    public void nextGame() {
        currentGame++;
    }

    public void resetGames() {
        currentGame = 0;
    }

    public List<String> getGames() {
        return games;
    }

    public <T> void printAll(List<T> list) {
        for (T element : list) {
            System.out.println(element);
        }
    }

    public void readGamesFromFile() {
        try (Stream<String> gameStream = Files.lines(Paths.get("inputFile.txt"))) {
            gameStream.forEach(gameLine -> {
                String gameData = gameLine.substring(gameLine.indexOf(':') + 1).trim();
                games.add(gameData);
            });
        } catch (IOException io) {
            System.err.println(io.getMessage());
        }
    }

    public boolean solveable(String game) {
        String[] draws = game.split("; ");
        for (String draw : draws) {
            String[] cubes = draw.split(", ");
            for (String cube : cubes) {
                int spaceIndex = cube.indexOf(' ');
                int cubeNumber = Integer.parseInt(cube.substring(0, spaceIndex));
                char cubeColour = cube.charAt(spaceIndex + 1);

                if (cubeNumber > rgb.get(cubeColour)) {
                    return false;
                }
            }
        }
        return true;
    }

}
