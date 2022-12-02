import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Day1 {

    public static void main(String[] args) throws Exception {
        Path inputFilePath = Path.of("./src/inputs/day1.txt");
        List<List<Integer>> caloriesByElves = new ArrayList<>();
        List<Integer> currentElvesCalories = new ArrayList<>();

        for(String line: Files.readAllLines(inputFilePath)) {
            if(!line.isBlank()) {
                currentElvesCalories.add(Integer.valueOf(line));
            } else {
                caloriesByElves.add(currentElvesCalories);
                currentElvesCalories = new ArrayList<>();
            }
        }

        // top 3 elves cals
        Integer topElvesCalsSum = 0;
        for (int i = 0; i < 3; i++) {
            List<Integer> currentMax = caloriesByElves.stream().
                    max(Comparator.comparing(elfCalories -> elfCalories.stream()
                            .mapToInt(Integer::intValue).sum()))
                    .orElse(Collections.emptyList());

            topElvesCalsSum += currentMax.stream().mapToInt(Integer::intValue).sum();

            caloriesByElves.remove(currentMax);

            System.out.println("Top "+ (i+1) + ": " + currentMax.stream().mapToInt(Integer::intValue).sum());
        }

        System.out.println("\nSum of top 3: " + topElvesCalsSum);
    }
}
