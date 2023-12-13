import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day2PartTwo {
    List<String> games = new ArrayList<>();

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

    /**
     * returns of the set of cubes
     * max of each cubes to make the game possible could be for example:
     * 2 red, 4 green, 6 blue
     * Output of this Method should be 2x4x6 = 48
     */
    public static int getPowerOfMaxCubes(String game) {
        String[] draws = game.split("; ");
        Map<Character, Integer> rgbCubesMaxValues = new HashMap<>(Map.of('r', 0, 'g', 0, 'b', 0));
        for (String draw : draws) {
            String[] cubes = draw.split(", ");
            for (String cube : cubes) {
                int spaceIndex = cube.indexOf(' ');
                int cubeNumber = Integer.parseInt(cube.substring(0, spaceIndex));
                char cubeColour = cube.charAt(spaceIndex + 1);
                if (cubeNumber > rgbCubesMaxValues.get(cubeColour)) {
                    rgbCubesMaxValues.replace(cubeColour, cubeNumber);
                }
            }
        }
        return rgbCubesMaxValues.get('r')*rgbCubesMaxValues.get('g')*rgbCubesMaxValues.get('b');
    }

    public List<String> getGames() {
        return games;
    }

    public static void main(String[] args) {
        // read out file into games array
        Day2PartTwo day2PartTwo = new Day2PartTwo();
        day2PartTwo.readGamesFromFile();

        int finalvalue = 0;
        for (String string : day2PartTwo.getGames()) {
            finalvalue += getPowerOfMaxCubes(string);
        }
        System.out.println(finalvalue);
    }
}
