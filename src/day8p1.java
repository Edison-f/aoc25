import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class day8p1 {

    record Tuple(double dist, int p1, int p2) { }

    public static double dist(long[] p1, long[] p2) {
        return Math.sqrt(Math.pow(p1[0] - p2[0], 2) + Math.pow(p1[1] - p2[1], 2) + Math.pow(p1[2] - p2[2], 2));
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("src/day8.txt"));
        ArrayList<long[]> pts = new ArrayList<>();
        while(in.hasNextLine()) {
            String[] split = in.nextLine().split(",");
            pts.add(new long[] {Long.parseLong(split[0]), Long.parseLong(split[1]), Long.parseLong(split[2])});
        }
        int[] group = new int[pts.size()];
        for(int i = 0; i < group.length; i++) {
            group[i] = i;
        }
        PriorityQueue<Tuple> queue = new PriorityQueue<>((Comparator.comparingDouble(o -> o.dist)));
        for(int i = 0; i < pts.size() - 1; i++) {
            for(int j = i + 1; j < pts.size(); j++) {
                queue.add(new Tuple(dist(pts.get(i), pts.get(j)), i, j));
            }
        }
        int connections = 1000;
        while(connections > 0) {
            Tuple curr = queue.poll();
            int target= group[curr.p2];
            for(int i = 0; i < group.length; i++) {
                if(group[i] == target) {
                    group[i] = group[curr.p1];
                }
            }
            connections--;
        }
        int[] count = new int[group.length];
        for(int i : group) {
            count[i]++;
        }
        PriorityQueue<Integer> result = new PriorityQueue<>(((o1, o2) -> o2 - o1));
        for(int i : count) {
            result.add(i);
        }
        System.out.println(result.poll() * result.poll() * result.poll());
    }
}
