import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class DayNine {

    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("src/inputs/day-nine.txt");
        Scanner scan = new Scanner(f);
        ArrayList<Long> numbers = new ArrayList<>();
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            numbers.add(Long.parseLong(line));
        }
        for (int i = 25; i < numbers.size(); i++) {
            List<Long> toSearch = numbers.subList(i - 25, i);
            long toFind = numbers.get(i);
            boolean found = false;
            for (long j : toSearch) {
                if (toFind - j != j && toSearch.contains(toFind - j)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("Part one: " + toFind);
                System.out.println("Part two: " + findSet(toFind, numbers));
                return;
            }
        }
    }

    public static long findSet(long toFind, ArrayList<Long> numbers) {
        for (int i = 0; i < numbers.size(); i++) {
            int currentIndex = i;
            long sum = 0;
            sum = numbers.get(i);
            while (sum < toFind) {
                currentIndex++;
                sum += numbers.get(currentIndex);
            }
            if (sum == toFind) {
                List<Long> subset = numbers.subList(i, currentIndex + 1);
                Collections.sort(subset);
                return Collections.min(subset) + Collections.max(subset);
            }
        }
        return -1;
    }

}
