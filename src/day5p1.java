import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

public class day5p1 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("src/day5.txt"));
        String input = in.nextLine();
        ArrayList<long[]> ranges = new ArrayList<>();
        ArrayList<Long> search = new ArrayList<>();
        while (!input.isEmpty()) {
            String[] split = input.split("-");
            search.add(Long.parseLong(split[0]));
            ranges.add(new long[]{Long.parseLong(split[0]), Long.parseLong(split[1])});
            input = in.nextLine();
        }
        Collections.sort(ranges, (o1, o2) -> {
            long result = o1[0] - o2[0];
            if (result == 0) {
                return 0;
            } else if (result > 0) {
                return 1;
            }
            return -1;
        });
        Collections.sort(search);
        long result = 0;
        while (in.hasNextLine()) {
            long n = Long.parseLong(in.nextLine());
            boolean found = false;
            int i = 0;
            while (!found && i < ranges.size()) {
                if (between(ranges.get(i), n)) {
                    found = true;
                }
                i++;
            }
            if (found) {
                result++;
            }
        }
        System.out.println(result);
    }

    public static boolean between(long[] range, long n) {
        return n >= range[0] && n <= range[1];
    }
}
