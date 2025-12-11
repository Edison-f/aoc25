import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class day10p2 {

    static long[] dp;

    public static int[] press(int[] state, ArrayList<Integer> button) {
        int[] result = Arrays.copyOf(state, state.length);
        for (int i : button) {
           result[i]++;
        }
        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("src/day10.txt"));
        int maxlen = 0;
        long result = 0;
        while (in.hasNextLine()) {
            String input = in.nextLine();
            String inputButtons = input.split("] ")[1].split(" \\{")[0];
            String inputPresses = input.split("\\{")[1];
            inputPresses = inputPresses.substring(0, inputPresses.length() - 1);
            String[] split = inputPresses.split(",");
            int[] targets = new int[split.length];
            for(int i = 0; i < targets.length; i++) {
                targets[i] = Integer.parseInt(split[i]);
            }
            ArrayList<ArrayList<Integer>> buttons = new ArrayList<>();
            split = inputButtons.split(" ");
            for (String s : split) {
                ArrayList<Integer> temp = new ArrayList<>();
                for (String num : s.substring(1, s.length() - 1).split(",")) {
                    temp.add(Integer.valueOf(num));
                }
                buttons.add(temp);
            }

            HashSet<Integer> set = new HashSet<>();
            Queue<int[]> queue = new LinkedList<>();
            queue.add(new int[targets.length]);
            int presses = 0;
            boolean found = false;
            while(!queue.isEmpty() && !found) {
                presses++;
                int size = queue.size();
                System.out.println(size);
                for(int i = 0; i < size; i++) {
                    int[] curr = queue.poll();
                    if(Arrays.equals(curr, targets)) {
                        found = true;
                        break;
                    }
                    boolean invalid = false;
                    for(int j = 0; j < curr.length; j++) {
                        if(curr[j] > targets[j]) {
                            invalid = true;
                            break;
                        }
                    }
                    if(invalid) continue;
                    if(set.add(Arrays.hashCode(curr))) {
                        for(ArrayList<Integer> button : buttons) {
                            queue.add(press(curr, button));
                        }
                    }
                }
            }
            System.out.println(presses);
            result += presses - 1;
        }
        System.out.println(result);
    }
}
