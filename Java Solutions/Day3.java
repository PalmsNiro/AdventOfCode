import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day3 {
    List<List<Character>> lines = new ArrayList<>();

    public static void main(String[] args) {
        Day3 day3 = new Day3();
        day3.readLines();
        System.out.println("sum is: " + day3.sum());

    }

    public int sum() {
        int finalValue = 0;
        int currentLine = 0;
        StringBuilder currentNumber = new StringBuilder();
        System.out.println("Entered sum function");
        for (List<Character> charRow : lines) {
            // System.out.println("Entered foreach");
            System.out.println("bearbeite folgende line " + currentLine + " : " + charRow);
            // boolean currentNumberValid = false;
            List<Integer> indexesOfNumber = new ArrayList<>();
            // System.out.println(charRow.size());
            for (int i = 0; i < charRow.size(); i++) {
                // System.out.println(charRow.get(i));
                if (Character.isDigit(charRow.get(i))) {
                    // System.out.println("got a Digit!");
                    currentNumber.append(charRow.get(i));
                    indexesOfNumber.add(i);
                    if (isLastNumber(currentLine, i)) {
                        if (isValidNumber(indexesOfNumber, currentLine)) {
                            finalValue += Integer.parseInt(currentNumber.toString());
                            System.out.println("got final number: " + currentNumber.toString());
                        }
                        indexesOfNumber.clear();
                        currentNumber.setLength(0);
                    }
                }
            }
            currentLine++;
        }
        return finalValue;
    }

    public boolean isLastNumber(int row, int index) {
        if (index == lines.get(row).size() - 1) {
            return true;
        } else if (Character.isDigit(lines.get(row).get(index + 1))) {
            return false;
        }
        return true;
    }

    public boolean isValidNumber(List<Integer> indexesOfSingleDigits, int row) {
        for (Integer index : indexesOfSingleDigits) {
            System.out.println(index);
            // erste reihe
            if (row == 0) {
                // auf ersten und letzten index prüfen
                if (index == 0) {// char ganz links
                    if (matchingSymbol(lines.get(row + 1).get(index))
                            || matchingSymbol(lines.get(row + 1).get(index + 1))
                            || matchingSymbol(lines.get(row).get(index + 1))) {
                        return true;
                    }
                } else if (index == lines.get(row).size() - 1) {// char ganz rechts
                    if (matchingSymbol(lines.get(row + 1).get(index))
                            || matchingSymbol(lines.get(row + 1).get(index - 1))
                            || matchingSymbol(lines.get(row).get(index - 1))) {
                        return true;
                    }
                } else {// alles zwischen drin
                    if (matchingSymbol(lines.get(row + 1).get(index))
                            || matchingSymbol(lines.get(row + 1).get(index + 1))
                            || matchingSymbol(lines.get(row + 1).get(index - 1))
                            || matchingSymbol(lines.get(row).get(index + 1))
                            || matchingSymbol(lines.get(row).get(index - 1))) {
                        return true;
                    }
                }
            } else if (row == lines.size() - 1) {// letzte reihe
                if (index == 0) {// char ganz links
                    if (matchingSymbol(lines.get(row - 1).get(index))
                            || matchingSymbol(lines.get(row - 1).get(index + 1))
                            || matchingSymbol(lines.get(row).get(index + 1))) {
                        return true;
                    }
                } else if (index == lines.get(row).size() - 1) {// char ganz rechts
                    if (matchingSymbol(lines.get(row - 1).get(index))
                            || matchingSymbol(lines.get(row - 1).get(index - 1))
                            || matchingSymbol(lines.get(row).get(index - 1))) {
                        return true;
                    }
                } else {// alles zwischen drin
                    if (matchingSymbol(lines.get(row - 1).get(index))
                            || matchingSymbol(lines.get(row - 1).get(index + 1))
                            || matchingSymbol(lines.get(row - 1).get(index - 1))
                            || matchingSymbol(lines.get(row).get(index + 1))
                            || matchingSymbol(lines.get(row).get(index - 1))) {
                        return true;
                    }
                }
            } else {// normale reihen
                if (index == 0) {// char ganz links
                    if (matchingSymbol(lines.get(row - 1).get(index))
                            || matchingSymbol(lines.get(row - 1).get(index + 1))
                            || matchingSymbol(lines.get(row).get(index + 1))
                            || matchingSymbol(lines.get(row + 1).get(index))
                            || matchingSymbol(lines.get(row + 1).get(index + 1))) {
                        return true;
                    }
                } else if (index == lines.get(row).size() - 1) { // char ganz rechts
                    if (matchingSymbol(lines.get(row - 1).get(index))
                            || matchingSymbol(lines.get(row - 1).get(index - 1))
                            || matchingSymbol(lines.get(row).get(index - 1))
                            || matchingSymbol(lines.get(row + 1).get(index))
                            || matchingSymbol(lines.get(row + 1).get(index - 1))) {
                        return true;
                    }
                } else {// alles zwischen drin
                    if (matchingSymbol(lines.get(row - 1).get(index))
                            || matchingSymbol(lines.get(row - 1).get(index - 1))
                            || matchingSymbol(lines.get(row - 1).get(index + 1))
                            || matchingSymbol(lines.get(row).get(index + 1))
                            || matchingSymbol(lines.get(row).get(index - 1))
                            || matchingSymbol(lines.get(row + 1).get(index))
                            || matchingSymbol(lines.get(row + 1).get(index + 1))
                            || matchingSymbol(lines.get(row + 1).get(index - 1))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean matchingSymbol(char c) {
        if (!Character.isDigit(c) && c != '.') {
            return true;
        }
        return false;
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
