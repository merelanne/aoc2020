import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class DayFive {
    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("src/inputs/day-five.txt");
        Scanner scan = new Scanner(f);
        List<Integer> ids = new ArrayList<>();
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            int row = getRow(line.substring(0, 7));
            int column = getColumn(line.substring(7));
            ids.add(row*8 + column);
        }
        Collections.sort(ids);
        int ret = 0;
        for (int i = 0; i < ids.size() - 1; i++) {
            System.out.println(ids.get(i));
            if (ids.get(i) + 1 != ids.get(i+1)) {
                ret = ids.get(i) + 1;
            }
        }
        System.out.println(ret);
    }

    public static int getRow(String line) {
        int low = 0;
        int high = 127;
        int mid = (low+high)/2;
        for (char c : line.toCharArray()) {
            if (c == 'F') high = mid;
            if (c == 'B') low = mid + 1;
            mid = (low + high)/2;
//            System.out.println(c + ":" + low + " - " + high);
        }
        assert (high == low);
//        System.out.println(line + " " + high);
        return high;
    }

    public static int getColumn(String line) {
        int low = 0;
        int high = 7;
        int mid = (low+high)/2;
        for (char c : line.toCharArray()) {
            if (c == 'L') high = mid;
            if (c == 'R') low = mid + 1;
            mid = (low + high)/2;
//            System.out.println(c + ":" + low + " - " + high);
        }
        assert (high == low);
//        System.out.println(line + " " + (high));
        return high;
    }
}
