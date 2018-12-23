import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FrequencyTuner1 {
    public static void main(String[] args) {
        int changingFreq = 0;
        try (BufferedReader reader = Files.newBufferedReader(Paths.get("input.txt"));) {
            String input;
            while ((input = reader.readLine()) != null) {
                changingFreq += Integer.valueOf(input);
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