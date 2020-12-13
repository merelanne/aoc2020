import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class DayThirteen {
    public static void main(String[] args) throws FileNotFoundException {
        // Reading data
        File f = new File("src/inputs/day-thirteen.txt");
        Scanner scan = new Scanner(f);
        String line = "";
        while (scan.hasNextLine()) line = scan.nextLine();
        List<Bus> busses = new ArrayList<>();
        String[] times = line.split(",");
        for (int s = 0; s < times.length; s++) {
            if (times[s].equals("x")) continue;
            else busses.add(new Bus(Integer.parseInt(times[s]), s));
        }

        // Initializing t
        long t = busses.get(0).id + busses.get(0).index;
        long toIncrement = 1;

        // Finding right t
        while (!busses.isEmpty()) {
            Bus toRemove = null;
            for (Bus bus : busses) {
                if ((t+bus.index)%bus.id != 0) t += toIncrement;
                else {
                    toRemove = bus;
                    toIncrement *= bus.id;
                }
                break;
            }
            if (toRemove != null) busses.remove(toRemove);
        }

        // Printing the answer
        System.out.println("TIME = " + t);
    }
}

class Bus {
    int id;
    int index;

    public Bus(int id, int index) {
        this.id = id;
        this.index = index;
    }

    public String toString() {
        return Integer.toString(this.id);
    }
}
