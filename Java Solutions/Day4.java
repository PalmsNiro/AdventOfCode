import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day4 {

    List<List<Integer>> leftNumbers = new ArrayList<>();
    List<List<Integer>> rightNumbers = new ArrayList<>();

    public static void main(String[] args) {
        Day4 day4 = new Day4();
        day4.readLines();
        day4.printBothLists();
        System.out.println(day4.solve());
    }

    private long solve() {
        long sum = 0;
        int cardValue = 0;
        for (int i = 0; i < leftNumbers.size(); i++) {
            for (int j = 0; j < rightNumbers.get(i).size(); j++) {
                if (leftNumbers.get(i).contains(rightNumbers.get(i).get(j))) {
                    if (cardValue == 0) {
                        cardValue++;
                    } else {
                        cardValue = cardValue << 1;
                    }
                }
            }
            System.out.println(cardValue);
            sum += cardValue;
            cardValue = 0;
        }
        return sum;
    }

    public void printBothLists() {
        for (List<Integer> list : leftNumbers) {
            System.out.println(list);
        }
        System.out.println("right");
        for (List<Integer> list : rightNumbers) {
            System.out.println(list);
        }
    }

    public void readLines() {
        try (Stream<String> lineStream = Files
                .lines(Paths.get("D:\\Code\\AdventOfCode\\Java Solutions\\inputFile.txt"))) {
            lineStream.forEach(line -> {
                // Entfernen des "Card X:" Teils und Teilen der Zeile am "|"
                String[] parts = line.split(":")[1].trim().split("\\|");
                List<Integer> leftSide = new ArrayList<>();
                List<Integer> rightSide = new ArrayList<>();
                // Parsen und Hinzufügen der Zahlen von der linken Seite
                for (String num : parts[0].trim().split("\\s+")) {
                    leftSide.add(Integer.parseInt(num));
                }
                // Parsen und Hinzufügen der Zahlen von der rechten Seite
                for (String num : parts[1].trim().split("\\s+")) {
                    rightSide.add(Integer.parseInt(num));
                }
                leftNumbers.add(leftSide);
                rightNumbers.add(rightSide);
            });
        } catch (IOException io) {
            System.err.println("Error reading file: " + io.getMessage());
        }
    }
}