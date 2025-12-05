import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class day5p2 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("src/day5.txt"));
        String input = in.nextLine();
        ArrayList<long[]> ranges = new ArrayList<>();
        while (!input.isEmpty()) {
            String[] split = input.split("-");
            ranges.add(new long[]{Long.parseLong(split[0]), Long.parseLong(split[1])});
            input = in.nextLine();
        }
        while (in.hasNextLine()) in.nextLine();
        long result = 0;
        long max = 0;
        ranges.sort((o1, o2) -> o1[0] == o2[0] ? Long.compare(o1[1], o2[1]) : Long.compare(o1[0], o2[0]));
        for (long[] range : ranges) {
            range[1]++;
            long add = Math.max(0, range[1] - Math.max(range[0], max));
            result += add;
            max = Math.max(max, range[1]);
        }
        System.out.println(result);
    }

    public static boolean between(long[] range, long n) {
        return n >= range[0] && n <= range[1];
    }
}
