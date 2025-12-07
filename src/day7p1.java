import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class day7p1 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("src/day7.txt"));
        ArrayList<String> strings = new ArrayList<>();
        while(in.hasNextLine()) {
            strings.add(in.nextLine());
        }
        char[][] grid = new char[strings.size()][strings.getFirst().length()];
        for(int i = 0; i < strings.size(); i++) {
            grid[i] = strings.get(i).toCharArray();
        }
        long result = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if(grid[i][j] == 'S') {
                    grid[i + 1][j] = '|';
                } else if(grid[i][j] == '^' && grid[i - 1][j] == '|'){
                    if (grid[i][j - 1] != '|') {
                        grid[i][j - 1] = '|';
                    }
                    grid[i][j + 1] = '|';
                    result++;
                }
            }
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '|' && i < grid.length - 1 && grid[i + 1][j] == '.') {
                    grid[i + 1][j] = '|';
                }
            }
        }
        for(char[] row : grid) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println(result);
    }
}
