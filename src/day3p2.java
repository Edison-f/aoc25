import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class day3p2 {


    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("src/day3.txt"));
        long result = 0;
        while (in.hasNextLine()) {
            String input = in.nextLine();
            long[] dp = new long[12];
            long[] prev = new long[12];
            for (int i = 0; i < input.length(); i++) {
                dp[0] = Math.max(input.charAt(i) - '0', dp[0]);
                for (int j = 1; j < dp.length; j++) {
                    dp[j] = Math.max(prev[j], prev[j - 1] * 10 + input.charAt(i) - '0');
                }
                System.arraycopy(dp, 0, prev, 0, dp.length);
            }
            result += dp[11];
        }
        System.out.println(result);
    }
}
