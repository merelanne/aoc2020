import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class DayTen {
    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("src/inputs/day-ten.txt");
        Scanner scan = new Scanner(f);
        List<Integer> data = new ArrayList<>();
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            data.add(Integer.parseInt(line));
        }
        Collections.sort(data);
        data.add(0, 0);
        data.add(Collections.max(data) + 3);
        System.out.println(data.toString());
//        Tree tree = new Tree(0);

        System.out.println(countPossibilities(data));
    }

    public static long countPossibilities(List<Integer> data) {
        Map<Integer, Long> poss = new HashMap<>();
        for (int i : data) poss.put(i, 0l);
        poss.put(data.get(0), 1l);
        for (int i = 0; i < data.size() - 1; i++) {
            for (int j = i + 1; j < data.size(); j++) {
                int diff = (data.get(j) - data.get(i));
                if (diff > 3) break;
                if (diff <= 3 && diff >= 1) {
                    poss.put(data.get(j), poss.get(data.get(j)) + poss.get(data.get(i)));
                }
            }
        }
        System.out.println(poss.get(data.get(data.size() - 1)));
        return poss.get(data.get(data.size() - 1));
    }

}


