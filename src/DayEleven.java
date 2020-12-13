import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DayEleven {

    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("src/inputs/day-eleven.txt");
        Scanner scan = new Scanner(f);
        List<List<Character>> data = new ArrayList<>();
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            List<Character> currentRow = new ArrayList<>();
            for (int i = 0; i < line.length(); i++) {
                currentRow.add(line.charAt(i));
            }
            data.add(currentRow);
        }
        int count = 0;
        List<List<Character>> previous = data;
        List<List<Character>> current = changeData(data);
        while (!current.equals(previous)) {
            previous = current;
            current = changeData(previous);
        }

        for (List<Character> list : current) {
            for (char c : list) if (c == '#') count++;
        }
        System.out.println(count);

    }

    public static List<List<Character>> changeData(List<List<Character>> data) {
        List<List<Character>> newData = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            List<Character> newList = new ArrayList<>();
            for (int j = 0; j < data.get(i).size(); j++) {
                if (data.get(i).get(j) == '.') newList.add('.');
                else if (data.get(i).get(j) == 'L' && emptyAdjacent(data, i, j)) newList.add('#');
                else if (data.get(i).get(j) == '#' && fourOccupied(data, i, j)) newList.add('L');
                else newList.add(data.get(i).get(j));
            }
            newData.add(newList);
        }
        return newData;
    }

    public static boolean emptyAdjacent(List<List<Character>> data, int x, int y) {
        boolean emptyAdjacent = true;
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j<= y + 1; j++) {
                if (i >= 0 && i < data.size()) {
                    if (j >= 0 && j < data.get(i).size()) {
                        if (x != i || y != j && i >= 0 && j >= 0 && i < data.size() && j < data.get(i).size()) {
                            if (data.get(i).get(j) == '#') emptyAdjacent = false;
                            else if (data.get(i).get(j) == '.') {
                                int pos = calcPos(x, y, i, j);
                                if (isAdjacentOccupied(data, x, y, pos)) emptyAdjacent = false;
                            }
                        }
                        //                        System.out.print(data.get(i).get(j));
                    }
                }
            }
//            System.out.println("");
        }
//        System.out.println("Empty adjacent: " + x + " " + y + " " +   emptyAdjacent);
        return emptyAdjacent;
    }

    public static int calcPos(int x, int y, int i, int j) {
        if (i < x) {
            if (j < y) return 1;
            if (j == y) return 2;
            if (j > y) return 3;
        } else if (i == x) {
            if (j < y) return 4;
            if (j > y) return 5;
        } else if (i > x) {
            if (j < y) return 6;
            if (j == y) return 7;
            if (j > y) return 8;
        }
        return -1;
    }

    /**
     * 123
     * 4x5
     * 678
     */
    public static boolean isAdjacentOccupied(List<List<Character>> data, int x, int y, int pos) {
        if (pos == 1) {
            int newX = x - 1;
            int newY = y - 1;
            while (newX >= 0 && newX < data.size() && newY >= 0 && newY < data.get(newX).size()) {
                if (data.get(newX).get(newY) == '#') return true;
                if (data.get(newX).get(newY) == 'L') return false;
                newX--;
                newY--;
            }
        } else if (pos == 2) {
            int newX = x - 1;
            while (newX >= 0 && newX < data.size()) {
                if (data.get(newX).get(y) == '#') return true;
                if (data.get(newX).get(y) == 'L') return false;
                newX--;
            }
        } else if (pos == 3) {
            int newX = x - 1;
            int newY = y + 1;
            while (newX >= 0 && newX < data.size() && newY >= 0 && newY < data.get(newX).size()) {
                if (data.get(newX).get(newY) == '#') return true;
                if (data.get(newX).get(newY) == 'L') return false;
                newX--;
                newY++;
            }
        } else if (pos == 4) {
            int newY = y - 1;
            while (newY >= 0 && newY < data.get(x).size()) {
                if (data.get(x).get(newY) == '#') return true;
                if (data.get(x).get(newY) == 'L') return false;
                newY--;
            }
        } else if (pos == 5) {
            int newY = y + 1;
            while (newY >= 0 && newY < data.get(x).size()) {
                if (data.get(x).get(newY) == '#') return true;
                if (data.get(x).get(newY) == 'L') return false;
                newY++;
            }
        } else if (pos == 6) {
            int newX = x + 1;
            int newY = y - 1;
            while (newX >= 0 && newX < data.size() && newY >= 0 && newY < data.get(newX).size()) {
                if (data.get(newX).get(newY) == '#') return true;
                if (data.get(newX).get(newY) == 'L') return false;
                newX++;
                newY--;
            }
        } else if (pos == 7) {
            int newX = x + 1;
            while (newX >= 0 && newX < data.size()) {
                if (data.get(newX).get(y) == '#') return true;
                if (data.get(newX).get(y) == 'L') return false;
                newX++;
            }

        } else if (pos == 8) {
            int newX = x + 1;
            int newY = y + 1;
            while (newX >= 0 && newX < data.size() && newY >= 0 && newY < data.get(newX).size()) {
                if (data.get(newX).get(newY) == '#') return true;
                if (data.get(newX).get(newY) == 'L') return false;
                newX++;
                newY++;
            }

        }
        return false;
    }

    public static boolean fourOccupied(List<List<Character>> data, int x, int y) {
        int occupied = 0;
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (i >= 0 && i < data.size()) {
                    if (j >= 0 && j < data.get(i).size()) {
                        if (x != i || y != j) {
                            if (data.get(i).get(j) == '#') occupied++;
                            else if (data.get(i).get(j) == '.') {
                                int pos = calcPos(x, y, i, j);
                                if (isAdjacentOccupied(data, x, y, pos)) occupied++;
                            }
                        }
                    }
                }
            }
        }
        return occupied >= 5;
    }

}
