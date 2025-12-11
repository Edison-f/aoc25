import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class day10p1 {

    static long[] dp;

    public static int press(int state, ArrayList<Integer> button, int len) {
        for (int i : button) {
            state ^= (1 << (len - i - 1));
        }
        return state;
    }

    public static long solve(ArrayList<ArrayList<Integer>> buttons, int target, int state, int presses, int len) {
        if (target == state) {
            return presses;
        }
//        System.out.println(Integer.toBinaryString(state) + " " + presses);
        long result = Long.MAX_VALUE;
        for (ArrayList<Integer> button : buttons) {
            int next = press(state, button, len);
            if (dp[next] == 0 || presses < dp[next]) {
                dp[next] = presses + 1;
                result = Math.min(result, solve(buttons, target, next, presses + 1, len));
            }
        }
        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("src/day10.txt"));
        long result = 0;
        while (in.hasNextLine()) {
            String input = in.nextLine();
            String inputState = input.split("]")[0].substring(1);
            String inputButtons = input.split("] ")[1].split(" \\{")[0];
            int target = 0;
            for (int i = 0; i < inputState.length(); i++) {
                target <<= 1;
                if (inputState.charAt(i) == '#') {
                    target |= 1;
                }
            }
            ArrayList<ArrayList<Integer>> buttons = new ArrayList<>();
            String[] split = inputButtons.split(" ");
            for (String s : split) {
                ArrayList<Integer> temp = new ArrayList<>();
                for (String num : s.substring(1, s.length() - 1).split(",")) {
                    temp.add(Integer.valueOf(num));
                }
                buttons.add(temp);
            }
            dp = new long[1 << inputState.length()];
            Queue<int[]> queue = new LinkedList<>();
            queue.add(new int[]{0, 0}); // state, presses
            long presses = Long.MAX_VALUE;
            while (!queue.isEmpty()) {
                int[] curr = queue.poll();
                if (curr[0] == target) {
                    presses = Math.min(curr[1], presses);
                } else {
                    for (ArrayList<Integer> button : buttons) {
                        int next = press(curr[0], button, inputState.length());
                        if (dp[next] == 0 || curr[1] < dp[next]) {
                            dp[next] = curr[1] + 1;
                            queue.add(new int[] {next, curr[1] + 1});
                        }
                    }
                }
            }
//            System.out.println("target = " + Integer.toBinaryString(target));
            result += presses;
        }
        System.out.println(result);
    }
}
