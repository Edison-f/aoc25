import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class day9p1 {

    public static long area(long[] p1, long[] p2) {
        return (Math.abs(p1[0] - p2[0]) + 1) * (Math.abs(p1[1] - p2[1]) + 1);
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("src/day9.txt"));
        ArrayList<long[]> tiles = new ArrayList<>();
        while(in.hasNextLine()) {
            String[] split = in.nextLine().split(",");
            tiles.add(new long[] {Long.parseLong(split[0]), Long.parseLong(split[1])});
        }
        long result = 0;
        for (int i = 0; i < tiles.size() - 1; i++) {
            for (int j = i + 1; j < tiles.size(); j++) {
                result = Math.max(result, area(tiles.get(i), tiles.get(j)));
            }
        }
        System.out.println(result);
    }
}
