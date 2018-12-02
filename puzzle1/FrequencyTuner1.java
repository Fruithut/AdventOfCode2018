import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class FrequencyTuner1 {

    public static int applyMathOperation(String freqChange, int baseFreq) {
        String operator = freqChange.substring(0, 1);
        int value = Integer.valueOf(freqChange.substring(1, freqChange.length()));
        if (operator.equals("+")) return baseFreq + value;
        return baseFreq - value;
    }
    public static void main(String[] args) {
        int changingFreq = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader("./puzzle1/input.txt"));) {
            String input = reader.readLine();
            while (input != null) {
                changingFreq = applyMathOperation(input, changingFreq);
                input = reader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(changingFreq);
    }
}