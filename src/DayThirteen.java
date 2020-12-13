import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class DayThirteen {
    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("src/inputs/day-thirteen.txt");
        Scanner scan = new Scanner(f);
        List<String> data = new ArrayList<>();
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            data.add(line);
        }
        List<Bus> busses = new ArrayList<>();
        List<Bus> unsatisfied = new ArrayList<>();
        String[] times = data.get(1).split(",");
        for (int s = 0; s < times.length; s++) {
            if (times[s].equals("x")) continue;
            else {
                busses.add(new Bus(Integer.parseInt(times[s]), s));
                unsatisfied.add(new Bus(Integer.parseInt(times[s]), s));
            }
        }
        long t = busses.get(0).id + busses.get(0).index;
        System.out.println("t start = " + t);

        long toIncrement = 1;
        while (!unsatisfied.isEmpty()) {
            Bus toRemove = null;
            for (Bus bus : unsatisfied) {
                if ((t+bus.index)%bus.id != 0) {
                    t += toIncrement;
                } else {
                    System.out.println(t + " " + toIncrement + " " + bus.id);
                    toRemove = bus;
                    toIncrement *= bus.id;
                }
                break;
            }
            if (toRemove != null) {
                unsatisfied.remove(toRemove);
            }
        }
        System.out.println("TIME = " + t);
    }

}

class Bus {
    int id;
    long biggestTime;
    int index;

    public Bus(int id, int index) {
        this.id = id;
        this.index = index;
        this.biggestTime = id;
    }

    public String toString() {
        return Integer.toString(this.id);

    }
}
