import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class day9p2 {

    public static long area(long[] p1, long[] p2) {
        return (Math.abs(p1[0] - p2[0]) + 1) * (Math.abs(p1[1] - p2[1]) + 1);
    }

    public static int[] state(long[] p1, long[] p2, long[] next) { // too lazy to convert to a ternary
        int horizontal = 0;
        int vertical = 0;
        long x1 = p1[0];
        long y1 = p1[1];
        long x2 = p2[0];
        long y2 = p2[1];
        long x3 = next[0];
        long y3 = next[1];
        if (x3 >= Math.max(x1, x2)) {
            horizontal = 1;
        } else if (x3 <= Math.min(x1, x2)) {
            horizontal = -1;
        }
        if (y3 >= Math.max(y1, y2)) {
            vertical = 1;
        } else if (y3 <= Math.min(y1, y2)) {
            vertical = -1;
        }
        return new int[]{horizontal, vertical};
    }

    public static int hash(int[] state) {
        int result = 0;
        if (state[0] > 0) {
            result += 1;
        } else if (state[0] < 0) {
            result += 2;
        }
        if (state[1] > 0) {
            result += 10;
        } else if (state[1] < 0) {
            result += 20;
        }
        return result;
    }

    // false is good true is bad
    public static boolean within(int p1, int p2, ArrayList<long[]> tiles) {
        if (tiles.get(p1)[0] == tiles.get(p2)[0] || tiles.get(p1)[1] == tiles.get(p2)[1]) {
            return false;
        }
        HashSet<Integer> set = new HashSet<>();
        int[] prev = state(tiles.get(p1), tiles.get(p2), tiles.get(Math.min(p1, p2)));
        for (int i = Math.min(p1, p2) + 1; i < Math.max(p1, p2); i++) {
            int[] state = state(tiles.get(p1), tiles.get(p2), tiles.get(i));
            set.add(hash(state));
            if (badState(prev, state) || state[0] == 0 && state[1] == 0) {
                return true;
            }
            prev = state;
        }
        prev = state(tiles.get(p1), tiles.get(p2), tiles.get(Math.max(p1, p2)));
        for (int i = (Math.max(p1, p2) + 1) % tiles.size(); i != Math.min(p1, p2); i = (i + 1) % tiles.size()) {
            int[] state = state(tiles.get(p1), tiles.get(p2), tiles.get(i));
            set.add(hash(state));
            if (badState(prev, state) || state[0] == 0 && state[1] == 0) {
                return true;
            }
            prev = state;
        }
        int[] state = state(tiles.get(p1), tiles.get(p2), tiles.get(p1));
        set.add(hash(state));
        state = state(tiles.get(p1), tiles.get(p2), tiles.get(p2));
        set.add(hash(state));
        return !(set.contains(11) && set.contains(22) && set.contains(12) && set.contains(21));
    }

    private static boolean badState(int[] prev, int[] state) {
        return prev != null && ((Math.abs(state[0] - prev[0]) == 2 && state[1] == 0) || (Math.abs(state[1] - prev[1]) == 2 && state[0] == 0));
    }


    public static void visualize(ArrayList<long[]> tiles) {
        int n = 0;
        int m = 0;
        for (long[] l : tiles) {
            n = (int) Math.max(l[1], n);
            m = (int) Math.max(l[0], m);
        }
        char[][] grid = new char[n + 1][m + 1];
        for (int i = 0; i < tiles.size(); i++) {
            long[] l = tiles.get(i);
            grid[(int) l[1]][(int) l[0]] = (char) (i + '0');
        }
        for (char[] arr : grid) {
            for (int i = 0; i < arr.length; i++) {
                arr[i] = arr[i] > 0 ? arr[i] : '.';
            }
        }
        for (char[] arr : grid) {
            System.out.println(Arrays.toString(arr));
        }
    }

    // idea: same, but follow along the tiles to see if dim would invalidate it
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("src/day9.txt"));
        ArrayList<long[]> tiles = new ArrayList<>();
        while (in.hasNextLine()) {
            String[] split = in.nextLine().split(",");
            tiles.add(new long[]{Long.parseLong(split[0]), Long.parseLong(split[1])});
        }
        if (tiles.size() < 10) {
            visualize(tiles);
        }
        long result = 0;
        for (int i = 0; i < tiles.size() - 1; i++) {
            for (int j = i + 1; j < tiles.size(); j++) {
                long area = area(tiles.get(i), tiles.get(j));
                boolean good = !within(i, j, tiles);
                if (area > result && good) {
                    result = area;
                }
            }
        }
        System.out.println(result);
    }
}
