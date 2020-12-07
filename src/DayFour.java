import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DayFour {
    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("src/inputs/day-four.txt");
        Scanner scan = new Scanner(f);
        int valid = 0;
        String passport = "";
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            if (line.isEmpty()) {
                if (isValid(passport)) {
                    valid++;
                }
                passport = "";
            } else {
                passport += " " + line;
            }
        }
        if (isValid(passport)) {
            valid++;
        }
        System.out.println(valid);
    }

    public static boolean isValid(String passport) {
        String[] arguments = passport.split(" ");
        if (!passport.contains("byr") || !passport.contains("iyr") || !passport.contains("eyr") ||
                !passport.contains("hgt") || !passport.contains("hcl") ||
                !passport.contains("ecl") || !passport.contains("pid")) return false;

        for (String s : arguments) {
            if (s.contains("byr") && !checkYear(1920, 2002, s)) return false;
            if (s.contains("iyr") && !checkYear(2010, 2020, s)) return false;
            if (s.contains("eyr") && !checkYear(2020, 2030, s)) return false;
            if (s.contains("hgt:") && !checkHeight(s)) return false;
            if (s.contains("hcl:") && !checkHcl(s)) return false;
            if (s.contains("ecl:") && !checkEcl(s)) return false;
            if (s.contains("pid:") && !checkPid(s)) return false;
        }
        return true;
    }

    public static boolean checkYear(int min, int max, String year) {
        int numbers = Integer.parseInt(year.substring(4));
//        System.out.println(numbers + " min: " + min + " max: " + max);
        return numbers >= min && numbers <= max;
    }

    public static boolean checkHeight(String passport) {
        if (!passport.endsWith("cm") && !passport.endsWith("in")) return false;
        int numbers = Integer.parseInt(passport.substring(4, passport.length() - 2));
//        System.out.println(numbers + " " + passport.substring(passport.length() - 2));
        if (passport.endsWith("in")) return numbers <= 76 && numbers >= 59;
        else return numbers <= 193 && numbers >= 150;
    }

    public static boolean checkHcl(String s) {
        return s.matches("hcl:#(\\d|[a-f]){6}");
    }

    public static boolean checkEcl(String s) {
        return s.equals("ecl:amb") ||
                s.equals("ecl:blu") ||
                s.equals("ecl:brn") ||
                s.equals("ecl:gry") ||
                s.equals("ecl:grn") ||
                s.equals("ecl:hzl") ||
                s.equals("ecl:oth");
    }

    public static boolean checkPid(String s) {
//        System.out.println(s + " " + s.matches("pid:\\d{9}"));
        return s.matches("pid:\\d{9}");
    }
}