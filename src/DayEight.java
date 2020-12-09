import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class DayEight {

    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("src/inputs/day-eight.txt");
        Scanner scan = new Scanner(f);
        ArrayList<String> commands = new ArrayList<>();
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            commands.add(line);
        }
        for (int i = 0; i < commands.size(); i ++) {
            System.out.println(commands.get(i));
            if (commands.get(i).contains("jmp")) {
                commands.set(i, "nop " + commands.get(i).split(" ")[1] );
            }
            else if (commands.get(i).contains("nop")) {
                commands.set(i, "jmp " + commands.get(i).split(" ")[1] );
            }
            int acc = executeCommand(0, 0, commands, new ArrayList<>(), commands.size());
            if (acc != -1) {
                return;
            } else {
                if (commands.get(i).contains("jmp")) {
                    commands.set(i, "nop " + commands.get(i).split(" ")[1] );
                }
                else if (commands.get(i).contains("nop")) {
                    commands.set(i, "jmp " + commands.get(i).split(" ")[1] );
                }
            }

        }
        assert(!commands.isEmpty());
    }

    public static int executeCommand(int acc, int index, List<String> commands, List<Integer> visited, int toReach) {
        if (index == toReach) return acc;
        String current = commands.get(index);
        if (index > toReach || visited.contains(index)) return -1;
        visited.add(index);
        int amount = Integer.parseInt(current.substring(current.indexOf(' ') + 1));
        if (current.contains("acc") || current.contains("nop")) {
            index++;
        }
        if (current.contains("acc")) {
            acc += amount;
        }
        if (current.contains("jmp")) {
            index += amount;
        }
        return executeCommand(acc, index, commands, visited, toReach);
    }

}

