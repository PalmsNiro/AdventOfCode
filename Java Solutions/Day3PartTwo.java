import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
public class Day3PartTwo {
    private List<List<Character>> lines = new ArrayList<>();

    public static void main(String[] args) {
        Day3PartTwo day3PartTwo = new Day3PartTwo();
        System.out.println(day3PartTwo.solve());
    }

    public long solve() {
        readLines();
        long sum = 0;
        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).size(); j++) {
                if (lines.get(i).get(j) == '*') {
                    
                    sum += calcGearRatio(i, j);
                }
            }
        }
        return sum;
    }

    private int calcGearRatio(int row, int col) {
        PointChecker checker = new PointChecker();
        StringBuilder numberNearGear = new StringBuilder();
        int numbersFoundCounter = 0;
        int product = 1;
        // rows
        int[] rowOffsets = {-1, -1, -1, 0, 1, 1, 1, 0};
        int[] colOffsets = {-1, 0, 1, 1, 1, 0, -1, -1};
        for (int i = 0; i < rowOffsets.length; i++) {
            int newRow = row + rowOffsets[i];
            int newCol = col + colOffsets[i];
                
                
                if (newRow >= 0 && newRow <= lines.size() - 1 && newCol >= 0 && newCol < lines.get(newRow).size() - 1) {
                    //System.out.println("first test passed");
                    if (Character.isDigit(lines.get(newRow).get(newCol)) && !checker.containsPoint(newCol, newRow)) {
                       // System.out.println("second test passed");
                        numberNearGear.append(lines.get(newRow).get(newCol));
                        checker.addPoint(newCol, newRow);
                        int left = 1;
                        // System.out.println("lines size(newRow: )"+lines.get(newRow).size());
                        while (newCol - left >= 0
                                && Character.isDigit(lines.get(newRow).get(newCol - left))
                                && !checker.containsPoint(newCol - left, newRow)) {
                                    // System.out.println("got something left");
                            numberNearGear.insert(0, lines.get(newRow).get(newCol - left));
                            checker.addPoint(newCol - left, newRow);
                            left++;
                        }
                        int right = 1;
                        while (newCol + right <= lines.get(newRow).size()-1
                                && Character.isDigit(lines.get(newRow).get(newCol + right))
                                && !checker.containsPoint(newCol + right, newRow)) {
                                    // System.out.println("got something right");
                            numberNearGear.append( lines.get(newRow).get(newCol + right));
                            checker.addPoint(newCol + right, newRow);
                            right++;
                        }
                        numbersFoundCounter++;
                        left = 1;
                        right = 1;
                        product *= Integer.parseInt(numberNearGear.toString());
                        // System.out.println("number that was found around gear: "+numberNearGear.toString());
                        numberNearGear.setLength(0);
                    } else if (!checker.containsPoint(newCol, newRow)) {
                        checker.addPoint(newCol, newRow);
                    }
                }
            
        }
        return numbersFoundCounter == 2 ? product : 0;
    }

    public void readLines() {
        try (Stream<String> gameStream = Files.lines(Paths.get("inputFile.txt"))) {
            gameStream.forEach(line -> {
                List<Character> list = line.chars().mapToObj((i) -> Character.valueOf((char) i))
                        .collect(Collectors.toList());
                lines.add(list);
            });
        } catch (IOException io) {
            System.err.println("Error reading file: " + io.getMessage());
        }
    }
}
