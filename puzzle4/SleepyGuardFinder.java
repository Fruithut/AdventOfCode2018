import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.List;
import java.util.ArrayList;

public class SleepyGuardFinder {
    public static void main(String[] args) {
        List<String> inputList = new ArrayList();
        try {
            inputList = Files.readAllLines(Paths.get("./puzzle4", "input.txt"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        inputList.sort((a,b) -> a.compareTo(b));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm", Locale.ENGLISH);
        LocalDateTime date = LocalDateTime.parse(inputList.get(0).substring(1, 17), formatter);
        LocalDateTime date2 = LocalDateTime.parse(inputList.get(2).substring(1, 17), formatter);
        System.out.println(Duration.between(date, date2).toMinutes());

        for (String x : inputList) {
            System.out.println(x);
        }
        
    }
}