import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class day3p2 {

    static long[][] dp;

    public static long solve(String input, int x, long n, int len) {
        if(x == input.length() || len == 12) {
            return n;
        }
        if (dp[len][x] <= n) {
            dp[len][x] = n;
            return Math.max(solve(input, x + 1, n, len), solve(input, x + 1, (n * 10) + (input.charAt(x) - '0'), len + 1));
        }
        return -1;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("src/day3.txt"));
        long result = 0;
        while(in.hasNextLine()) {
            String input = in.nextLine();
            dp = new long[12][input.length()];
            result += solve(input, 0, 0, 0);
        }
        System.out.println(result);
    }
}
