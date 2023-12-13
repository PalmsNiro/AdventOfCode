import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class Day8 {

    static StringBuilder lrInstructions = new StringBuilder();
    Map<String, String> MovingOptions = new HashMap<>();

    public static void main(String[] args) {
        Day8 day8 = new Day8();
        day8.readLines();
        // day8.printMovingOptions();
        // System.out.println(lrInstructions.toString());
        // System.out.println(day8.getLeftWay("AAA") + day8.getRightWay("AAA"));
        System.out.println(day8.solve());
    }

    public int solve() {
        int turnsToGetZZZ = 0;
        int navigatingLR = 0;
        boolean zzzfound = false;
        String instructions = lrInstructions.toString();
        String nextMoveToGoFrom = getMove("AAA", instructions.charAt(navigatingLR));
        turnsToGetZZZ++;
        navigatingLR++;
        if (nextMoveToGoFrom.equals("ZZZ"))
            zzzfound = true;
        while (!zzzfound) {
            if(navigatingLR == lrInstructions.length())
                navigatingLR = 0;
            nextMoveToGoFrom = getMove(nextMoveToGoFrom, instructions.charAt(navigatingLR));
            turnsToGetZZZ++;
            navigatingLR ++;
            if (nextMoveToGoFrom.equals("ZZZ"))
                zzzfound = true;
        }
        return turnsToGetZZZ;
    }

    public String getMove(String key, char direction) {
        if (direction == 'L')
            return MovingOptions.get(key).substring(0, 3);
        else
            return MovingOptions.get(key).substring(4, 7);
    }

    public void printMovingOptions() {
        for (String name : MovingOptions.keySet()) {
            String key = name.toString();
            String value = MovingOptions.get(name).toString();
            System.out.println(key + " " + value);
        }
    }

    public void readLines() {
        try (Stream<String> gameStream = Files.lines(Paths.get("inputFile.txt"))) {
            gameStream.forEach(line -> {

                if (line.replaceAll("\\s+", "").matches("[RL]+"))
                    lrInstructions.append(line);
                else if (!line.isEmpty()) {
                    String outgoing = line.substring(0, 3);
                    String instructions = line.substring(line.indexOf('(') + 1, line.indexOf(')')).replaceAll("\\s+",
                            "");
                    MovingOptions.put(outgoing, instructions);
                }
            });
        } catch (IOException io) {
            System.err.println("Error reading file: " + io.getMessage());
        }
    }
}
