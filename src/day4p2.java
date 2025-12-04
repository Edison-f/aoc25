import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class day4p2 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("src/day4.txt"));
        ArrayList<String> arr = new ArrayList<>();
        while(in.hasNextLine()) {
            arr.add(in.nextLine());
        }
        char[][] grid = new char[arr.size()][arr.get(0).length()];
        for (int i = 0; i < arr.size(); i++) {
            for (int j = 0; j < arr.get(i).length(); j++) {
                grid[i][j] = arr.get(i).charAt(j);
            }
        }
        long result = 0;
        boolean good = false;
        while(!good) {
            good = true;
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    if (grid[i][j] != '@') continue;
                    int count = 0;
                    for (int k = -1; k <= 1; k++) {
                        for (int l = -1; l <= 1; l++) {
                            if ((k == 0 && l == 0) || i + k < 0 || i + k == grid.length || j + l < 0 || j + l == grid[0].length)
                                continue;
                            if (grid[i + k][j + l] == '@') count++;
                        }
                    }
                    if (count < 4) {
                        result++;
                        grid[i][j] = 'x';
                        good = false;
                    }
                }
            }
        }
        System.out.println(result);
    }
}
