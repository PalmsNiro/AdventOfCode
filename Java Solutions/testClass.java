import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class testClass {
    public static void main(String[] args) {
        List<List<Character>> testArrList = new ArrayList<>();
        List<Character> test1 = new ArrayList<>(Arrays.asList('a', 'b', 'c'));
        List<Character> test2 = new ArrayList<>(Arrays.asList('d', 'e', 'f'));
        List<Character> test3 = new ArrayList<>(Arrays.asList('g', 'h', 'i'));
        testArrList.addAll(Arrays.asList(test1, test2, test3));

        int row;
        int col;
        row = 1;
        col = 1;
        for (int i = -1; i < 2; i++) {
            int newRow = row + i;
            // col
            for (int j = -1; j < 2; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                int newCol = col + i;
                System.out.println(testArrList.get(newRow).get(newCol));
            }
        }
    }
}
