import java.awt.image.AreaAveragingScaleFilter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class DaySix {

    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("src/inputs/day-six.txt");
        Scanner scan = new Scanner(f);
        int yes = 0;
        List<String> group = new ArrayList<>();
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            if (line.isEmpty()) {
                yes += countGroup(group);
                group = new ArrayList<>();
                System.out.println(yes);
            }
            else group.add(line);
        }
        yes += countGroup(group);
        System.out.println(yes);
    }

    public static int countUnique(String word) {
        List<Character> visited = new ArrayList<>();
        for (char c : word.toCharArray()) {
            if (visited.contains(c)) continue;
            else visited.add(c);
        }
        return visited.size();
    }

    public static int countGroup(List<String> list) {
        if (list.size() == 1) return list.get(0).length();
        ArrayList<Character> answers = new ArrayList<>();
        ArrayList<Character> answerCopy = new ArrayList<>();
        for (char c : list.get(0).toCharArray()) {
            if (!answers.contains(c)) {
                answers.add(c);
                answerCopy.add(c);
            }
        }
        System.out.println(answers.toString());
        for (int i = 1; i < list.size(); i++) {
            for (char c : answerCopy) {
                if (list.get(i).indexOf(c) != -1) continue;
                else if (answers.contains(c)) {
                    answers.remove(answers.indexOf(c));
                    System.out.println(answers.toString());
                }
            }
        }
        return answers.size();
    }
}
