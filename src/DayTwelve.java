import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DayTwelve {
    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("src/inputs/day-twelve.txt");
        Scanner scan = new Scanner(f);
        int x = 0;
        int y = 0;
        int wpx = 10;
        int wpy = 1;
        Direction dir = Direction.EAST;
        // 1. east, 2. south, 3. west, 4. north
        List<String> data = new ArrayList<>();
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            data.add(line);
        }
        for (String str : data) {
            char instruction = str.charAt(0);
            int amount = Integer.parseInt(str.substring(1));
            switch (instruction){
                case 'E':
                    wpx += amount;
                    break;
                case 'W':
                    wpx -= amount;
                    break;
                case 'S':
                    wpy -= amount;
                    break;
                case 'N':
                    wpy += amount;
                    break;
                case 'F':
                    x = x + (amount * wpx);
                    y = y + (amount * wpy);
            }
            if ((instruction == 'R' && amount == 90) || (instruction == 'L' && amount == 270)) {
                int tempX = wpx;
                wpx = wpy;
                wpy = -tempX;
            } else if ((instruction == 'L' && amount == 90) || (instruction == 'R' && amount == 270)) {
                int tempX = wpx;
                wpx =-wpy;
                wpy = tempX;
            } else if (amount == 180) {
                wpx = -wpx;
                wpy = -wpy;
            }
            System.out.println(str + " - [" + x + "," + y + "] - wp: [" + wpx + "," + wpy + "]" );
        }
        int answer = Math.abs(x) + Math.abs(y);

        System.out.println(answer);
    }
}

enum Direction {
    EAST,
    NORTH,
    SOUTH,
    WEST;

    public Direction turnOpposite() {
        switch (this) {
            case EAST:
                return WEST;
            case NORTH:
                return SOUTH;
            case WEST:
                return EAST;
            case SOUTH:
                return NORTH;
        }
        return this;
    }

    public Direction turnLeft() {
        switch (this) {
            case EAST:
                return NORTH;
            case NORTH:
                return WEST;
            case WEST:
                return SOUTH;
            case SOUTH:
                return EAST;
        }
        return this;
    }

    public Direction turnRight() {
        switch (this) {
            case EAST:
                return SOUTH;
            case NORTH:
                return EAST;
            case WEST:
                return NORTH;
            case SOUTH:
                return WEST;
        }
        return this;
    }

}
