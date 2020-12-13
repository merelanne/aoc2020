import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Template {
    
    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("src/inputs/day-ten.txt");
        Scanner scan = new Scanner(f);
        int answer = 0;
        List<String> data = new ArrayList<>();
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            data.add(line);
        }
        System.out.println(answer);
    }
}
