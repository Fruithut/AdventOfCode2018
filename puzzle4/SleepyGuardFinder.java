import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.IntStream;

public class SleepyGuardFinder {
    private static List<String> parseTimeFile(String path, String file) throws IOException {
        List<String> inputList = Files.readAllLines(Paths.get(path, file));
        inputList.sort(String::compareTo);
        return inputList;
    }

    /**
     * Part 1 solution
     * @param inputList sorted list of dates and times.
     * @return a map where key is guard ID and value is frequency of sleeping-status per minute
     */
    private static Map<String, int[]> findSleepiestGuard(List<String> inputList) {
        Map<String, int[]> idMinuteMap = new HashMap<>();
        String currentId = "";
        int sleepStart = 0, sleepEnd;

        for (String note : inputList) {
            if (note.contains("Guard")) {
                currentId = note.split(" ")[3].substring(1);
                if (!idMinuteMap.containsKey(currentId)) {
                    idMinuteMap.put(currentId, new int[60]);
                }
            } else if (note.contains("falls")) {
                sleepStart = Integer.parseInt(note.substring(15, 17));
            } else {
                sleepEnd = Integer.parseInt(note.substring(15, 17));
                //Increment minute-indexes as a form of "hit-list"
                for (int i = sleepStart; i < sleepEnd; i++) {
                    idMinuteMap.get(currentId)[i] += 1;
                }
            }
        }

        Comparator<int[]> arrayComparator = (o1, o2) -> IntStream.of(o1).sum() > IntStream.of(o2).sum() ? 1 : -1;
        Map.Entry<String, int[]> sleepyGuard = idMinuteMap.entrySet()
                                                        .stream()
                                                        .max(Map.Entry.comparingByValue(arrayComparator)).get();
        int sleepiestMinute = 0, minuteFrequency = 0;
        for (int i = 0; i < sleepyGuard.getValue().length; i++) {
            if (sleepyGuard.getValue()[i] > minuteFrequency) {
                minuteFrequency = sleepyGuard.getValue()[i];
                sleepiestMinute = i;
            }
        }
        System.out.println("Sleepiest guard: " + sleepyGuard.getKey() + "\nSleeps the most on min: " + sleepiestMinute);

        return idMinuteMap;
    }

    /**
     * Part 2 solution
     * @param freqMap Map where key is guard ID and value is frequency of sleeping-status per minute
     */
    private static void findHighestFrequency(Map<String, int[]> freqMap) {
        String frequentSleeper = "";
        int sleepyMinute = 0, sleepyMinuteHits = 0;
        for (Map.Entry<String, int[]> entry : freqMap.entrySet()) {
            for (int i = 0; i < entry.getValue().length; i++) {
                if (entry.getValue()[i] > sleepyMinuteHits) {
                    sleepyMinute = i;
                    frequentSleeper = entry.getKey();
                    sleepyMinuteHits = entry.getValue()[i];
                }
            }
        }
        System.out.println("Frequent sleeper: " + frequentSleeper + "\nSleeps on min: " + sleepyMinute);
    }

    public static void main(String[] args) throws IOException {
        List<String> input = parseTimeFile("./puzzle4", "input.txt");
        Map<String, int[]> freqMap = findSleepiestGuard(input);
        findHighestFrequency(freqMap);
    }
}