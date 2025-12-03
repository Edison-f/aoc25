import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class day3p1 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("src/day3.txt"));
        long result = 0;
        while(in.hasNextLine()) {
            String input = in.nextLine();
            long max = 0;
            for (int i = 0; i < input.length() - 1; i++) {
                for (int j = i + 1; j < input.length(); j++) {
                    max = Math.max((input.charAt(i) - '0') * 10 + input.charAt(j) - '0', max);
                }
            }
            result += max;
        }
        System.out.println(result);
    }
}
