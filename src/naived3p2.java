import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalTime;
import java.util.Scanner;

public class naived3p2 {

    public static long v(String s, int i, int n) {
        return (long) ((s.charAt(i) - '0') * Math.pow(10, n - 1));
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("src/day3.txt"));
        long result = 0;
        System.out.println("Starting at " + LocalTime.now());
        while (in.hasNextLine()) {
            String s = in.nextLine();
            long max = 0;
            for (int i1 = 0; i1 < s.length() - 1; i1++)
                for (int i2 = i1 + 1; i2 < s.length() - 1; i2++)
                    for (int i3 = i2 + 1; i3 < s.length() - 1; i3++)
                        for (int i4 = i3 + 1; i4 < s.length() - 1; i4++)
                            for (int i5 = i4 + 1; i5 < s.length() - 1; i5++)
                                for (int i6 = i5 + 1; i6 < s.length() - 1; i6++)
                                    for (int i7 = i6 + 1; i7 < s.length() - 1; i7++)
                                        for (int i8 = i7 + 1; i8 < s.length() - 1; i8++)
                                            for (int i9 = i8 + 1; i9 < s.length() - 1; i9++)
                                                for (int i10 = i9 + 1; i10 < s.length() - 1; i10++)
                                                    for (int i11 = i10 + 1; i11 < s.length() - 1; i11++)
                                                        for (int i12 = i11 + 1; i12 < s.length() - 1; i12++)
                                                            max = Math.max(max, v(s, i1, 1) + v(s, i2, 2) + v(s, i3, 3) + v(s, i4, 4) + v(s, i5, 5) + v(s, i6, 6) + v(s, i7, 7) + v(s, i8, 8) + v(s, i9, 9) + v(s, i10, 10) + v(s, i11, 11) + v(s, i12, 12));
            result += max;
        }
        System.out.println("Ending at " + LocalTime.now());
        System.out.println(result);
    }
}
