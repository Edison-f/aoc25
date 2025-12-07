import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class day6p2 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("src/day6.txt"));
        ArrayList<String> arr = new ArrayList<>();
        String input = in.nextLine();
        while (input.charAt(0) != '*') {
            arr.add(input);
            input = in.nextLine();
        }
        int max = 0;
        for (String s : arr) {
            max = Math.max(max, s.length());
        }
        for (int i = 0; i < arr.size(); i++) {
            String s = arr.get(i);
            StringBuilder sBuilder = new StringBuilder(s);
            while (sBuilder.length() < max) {
                sBuilder.append(" ");
            }
            arr.set(i, sBuilder.toString());
        }
        char[][] grid = arr.stream().map(String::toCharArray).toArray(char[][]::new);
        ArrayList<Character> ops;
        String[] split = input.strip().split("\\s+");
        ops = Arrays.stream(split).map(s -> s.charAt(0)).collect(Collectors.toCollection(ArrayList::new));
        long result = 0;
        int j = 0;
        for (int i = 0; i < ops.size(); i++) {
            ArrayList<Long> nums = new ArrayList<>();
            long n;
            while (j < grid[0].length && (n = read(grid, j++)) > 0) nums.add(n);
            result += ops.get(i) == '+' ? nums.stream().mapToLong(l -> l).sum() : nums.stream().mapToLong(l -> l).map(l -> l == 0 ? 1 : l).reduce(1, (a, b) -> a * b);
        }
        System.out.println(result);
    }

    public static long read(char[][] grid, int i) {
        long result = 0;
        int j = 0;
        while (j < grid.length && grid[j][i] == ' ') {
            j++;
        }
        while (j < grid.length) {
            if (grid[j][i] != ' ') {
                result *= 10;
                result += grid[j][i] - '0';
            }
            j++;
        }
        return result;
    }
}
