import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DayTwo {

    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("src/inputs/day-two.txt");
        Scanner scan = new Scanner(f);
        int valid = 0;
        while (scan.hasNextLine()) {
//            2-8 w: wwwvwgwww
           String line = scan.nextLine();
           int min = Integer.parseInt(line.substring(0, line.indexOf('-')));
           int max = Integer.parseInt(line.substring(line.indexOf('-') + 1, line.indexOf(' ')));
            char letter = line.charAt(line.indexOf(':') - 1);
            String password = line.substring(line.lastIndexOf(' ') + 1);
            if (isValidSecond(min, max, letter, password)) valid++;
        }
        System.out.println(valid);
    }

    public static boolean isValidSecond(int one, int two, char letter, String password) {
        boolean oneIsTrue = password.charAt(one - 1) == letter && password.charAt(two - 1) != letter;
        boolean twoIsTrue = password.charAt(one - 1) != letter && password.charAt(two - 1) == letter;
        return oneIsTrue || twoIsTrue;
    }

    public static boolean isValid(int min, int max, char letter, String password) {
        return count(password, letter) >= min &&  count(password, letter) <= max;
    }

    public static int count(String password, char letter) {
        int count = 0;
        for (char c : password.toCharArray()) {
            if (c == letter) count++;
        }
        return count;
    }
}
