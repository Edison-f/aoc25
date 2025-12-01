import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class day1 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("src/day1.txt"));
        String line;
        int pos = 50;
        int result = 0;
        boolean passed = false;
        while ((line = in.readLine()) != null) {
            int prev = pos;
            if(line.charAt(0) == 'L') {
                int change = Integer.parseInt(line.substring(1));
                while(change > 0) {
                    pos--;
                    if(pos < 0) {
                        pos = 100 + pos;
                    }
                    pos %= 100;
                    if (pos == 0) {
                        result++;
                    }
                    change--;
                }
            } else {
                int change = Integer.parseInt(line.substring(1));
                while (change > 0) {
                    pos++;
                    if (pos < 0) {
                        pos = 100 + pos;
                    }
                    pos %= 100;
                    if (pos == 0) {
                        result++;
                    }
                    change--;
                }
            }
        }
        System.out.println("result " + result);
    }
}
