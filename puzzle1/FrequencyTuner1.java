import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class FrequencyTuner1 {
    
    public static void main(String[] args) {
        int changingFreq = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader("./puzzle1/input.txt"));) {
            String input = reader.readLine();
            while (input != null) {
                changingFreq += Integer.valueOf(input);
                input = reader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(changingFreq);
    }
}