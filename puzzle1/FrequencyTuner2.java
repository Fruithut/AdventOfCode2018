import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class FrequencyTuner2 {

    public static int applyMathOperation(String freqChange, int baseFreq) {
        String operator = freqChange.substring(0, 1);
        int value = Integer.valueOf(freqChange.substring(1, freqChange.length()));
        if (operator.equals("+")) return baseFreq + value;
        return baseFreq - value;
    }

    public static void main(String[] args) {
        Map<Integer, Integer> countMap = new HashMap<>();
        int changingFreq = 0;
        countMap.put(changingFreq, 1);

        try (BufferedReader reader = new BufferedReader(new FileReader("./puzzle1/input.txt"));) {
            String input = "";
            reader.mark(4000);
            while (true) {
                input = reader.readLine();
                // reset after reaching end of file
                if (input == null) {
                    reader.reset();
                    continue;
                }

                changingFreq = applyMathOperation(input, changingFreq);

                int timesSpotted = countMap.containsKey(changingFreq) ? (countMap.get(changingFreq) + 1) : 1;
                countMap.put(changingFreq, timesSpotted);

                if (timesSpotted == 2) break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(changingFreq);
    }
}