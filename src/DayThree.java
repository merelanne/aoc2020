import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DayThree {
    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("src/inputs/day-three.txt");
        Scanner scan = new Scanner(f);
        int onedown = 0;
        int three = 0;
        int five = 0;
        int seven = 0;
        int twoDown = 0;
        int yIndex = 0;
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            while (line.length() <= 7*yIndex) {
                line += line;
            }
            if (line.charAt(yIndex) == '#') {
                onedown++;
            }
            if (line.charAt(yIndex*3) == '#') {
                three++;
            }
            if (line.charAt(yIndex*5) == '#') {
                five++;
            }
            if (line.charAt(yIndex*7) == '#') {
                seven++;
            }
            if (yIndex%2 == 0 && line.charAt(yIndex/2) == '#') {
                twoDown++;
            }
            yIndex++;
        }
        System.out.println(onedown * twoDown * three * five * seven);
    }
}
