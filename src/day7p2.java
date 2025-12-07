import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class day7p2 {

    static long[][] dp;
    static char[][] grid;
    public static long solve(int x, int y) {
        if(x == grid.length) return 1;
        if(grid[x][y] == 'S') {
            return solve(x + 1, y);
        }
        if(dp[x][y] > 0) {
            return dp[x][y];
        }
        if(grid[x][y] == '^') {
            dp[x][y] = solve(x, y - 1) + solve(x, y + 1);
        } else {
            dp[x][y] = solve(x + 1, y);
        }
        return dp[x][y];
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("src/day7.txt"));
        ArrayList<String> strings = new ArrayList<>();
        while(in.hasNextLine()) {
            strings.add(in.nextLine());
        }
        grid = new char[strings.size()][strings.getFirst().length()];
        for(int i = 0; i < strings.size(); i++) {
            grid[i] = strings.get(i).toCharArray();
        }
        dp = new long[grid.length][grid[0].length];
        int i = 0;
        while (grid[0][i] != 'S') {
            i++;
        }
        System.out.println(solve(0, i));
    }
}
