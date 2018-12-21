import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

public class FrequencyTuner2 {
    public static void main(String[] args) {
        Map<Integer, Integer> countMap = new HashMap<>();
        int changingFreq = 0;
        countMap.put(changingFreq, 1);

        try (BufferedReader reader = Files.newBufferedReader(Paths.get("input.txt"));) {
            String input = "";
            reader.mark(5000);
            while (true) {
                input = reader.readLine();
                if (input == null) {
                    reader.reset();
                    continue;
                }

                changingFreq += Integer.valueOf(input);
                int timesSpotted = countMap.containsKey(changingFreq) ? (countMap.get(changingFreq) + 1) : 1;
                // We've found the repeating frequency
                if (timesSpotted == 2) break;

                countMap.put(changingFreq, timesSpotted);
            }
        } catch (IOException e) {
            e.printStackTrace();
            for (Throwable t : e.getSuppressed()) {
                t.printStackTrace();
            }
        }

        System.out.println(changingFreq);
    }
}