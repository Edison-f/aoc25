import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

public class day2p1 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("src/day2.txt"));
        long result = 0;
        String[] input = in.nextLine().split(",");
        HashSet<Long> set = new HashSet<>();
        long l = 1;
        long n = 0;
        try {
            while (l < 100000) {
//            n = (long) (l + l * Math.pow(10, Math.log(l) / Math.log(10) + 1));
                n = Long.parseLong(l + Long.toString(l));
                set.add(n);
                l++;
            }
        } catch (Exception ignored) {}
        for(String range : input) {
            String[] split = range.split("-");
            long end = Long.parseLong(split[1]);
            for (long i = Long.parseLong(split[0]); i <= end; i++) {
                if (set.contains(i)) {
                    result += i;
                }
            }
        }
        System.out.println(result);
    }
}
