import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class day6p1 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("src/day6.txt"));
        ArrayList<ArrayList<Long>> nums = new ArrayList<>();
        String input = in.nextLine();
        while(input.charAt(0) != '*') {
            String[] split = input.strip().split("\\s+");
            for (int i = 0; i < split.length; i++) {
                if(nums.size() <= i) nums.add(new ArrayList<>());
                nums.get(i).add(Long.parseLong(split[i]));
            }
            input= in.nextLine();
        }
        ArrayList<Character> ops = new ArrayList<>();
        String[] split = input.strip().split("\\s+");
        for (String s : split) {
            ops.add(s.charAt(0));
        }
        long result = 0;
        for(int i = 0; i < nums.size(); i++) {
            if(ops.get(i) == '+') {
                for (long l : nums.get(i)) {
                    result += l;
                }
            } else {
                long mult = 1;
                for (long l : nums.get(i)) {
                    mult *= l;
                }
                result += mult;
            }
        }
        System.out.println(result);
    }
}
