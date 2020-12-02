import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DayOne {

    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("src/inputs/day-one.txt");
        Scanner scan = new Scanner(f);
        List<Integer> numbers = new ArrayList<>();
        while (scan.hasNextInt()) {
            numbers.add(scan.nextInt());
        }
        for (int i : numbers) {
            int second = 2020 - i;
            for (int j : numbers) {
                if (i != j && numbers.contains(second - j)) {
                    System.out.println((second - j) * j * i);
                    return;
                }
            }
        }
    }
}