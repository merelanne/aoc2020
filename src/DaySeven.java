import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class DaySeven {

    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("src/inputs/day-seven.txt");
        Scanner scan = new Scanner(f);
        Map<String, Map<String, Integer>> bags = new HashMap<>();
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            String[] words = line.split(" ");
            Map<String, Integer> contents = new HashMap<>();
            String color = words[0] + " " + words[1];
            assert(words[2].equals("bags"));
            assert(words[3].equals("contain"));
            if (words[4].equals("no")) {
                bags.put(color, contents);
                continue;
            }
            for (int i = 4; i < words.length; i+=4) {
                int amount = Integer.parseInt(words[i]);
                String contains = words[i + 1] + " " + words[i + 2];
                contents.put(contains, amount);
            }
            bags.put(color, contents);
        }
        int required = findRequired(bags, "shiny gold");
        System.out.println(required);
    }

    public static int findRequired(Map<String, Map<String, Integer>> bags, String current) {
        Map<String, Integer> contents = bags.get(current);
        if (contents.isEmpty()) return 0;
        int count = 0;
        for (String s : contents.keySet()) count += contents.get(s) + contents.get(s)*findRequired(bags, s);
        return count;
    }

    public static boolean canHoldGold(Map<String, List<String>> bags, String color) {
        List<String> canContain = bags.get(color);
        if (canContain.contains("shiny gold")) return true;
        if (canContain.isEmpty()) return false;
        else for (String s : canContain) if (canHoldGold(bags, s)) return true;
        return false;
    }
}

