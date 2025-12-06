import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class day6p2 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("src/day6.txt"));
        ArrayList<String> arr = new ArrayList<>();
        String input = in.nextLine();
        while(input.charAt(0) != '*') {
            arr.add(input);
            input= in.nextLine();
        }
        int max = 0;
        for(String s : arr) {
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
        char[][] grid = new char[arr.size()][arr.getFirst().length()];
        for(int i = 0; i < arr.size(); i++) {
            grid[i] = arr.get(i).toCharArray();
        }
        ArrayList<Character> ops = new ArrayList<>();
        String[] split = input.strip().split("\\s+");
        for (String s : split) {
            ops.add(s.charAt(0));
        }
        long result = 0;
        int j = 0;
        for(int i = 0; i < ops.size(); i++) {
            ArrayList<Long> nums = new ArrayList<>();
            while(j < grid[0].length && read(grid, j) > 0) {
                nums.add(read(grid, j));
                j++;
            }
            j++;
            if(ops.get(i) == '+') {
                long add = 0;
                for (long l : nums) {
                    add += l;
                }
                result += add;
                System.out.println(add);
            } else {
                long mult = 1;
                for (long l : nums) {
                    mult *= l == 0 ? 1 : l;
                }
                System.out.println(mult);
                result += mult;
            }
        }
        System.out.println(result);
    }

    public static long read(char[][] grid, int i) {
        long result = 0;
        int j = 0;
        while(j < grid.length && grid[j][i] == ' ') {
            j++;
        }
        while(j < grid.length) {
            if(grid[j][i] != ' ') {
                result *= 10;
                result += grid[j][i] - '0';
            }
            j++;
        }
        return result;
    }
}
