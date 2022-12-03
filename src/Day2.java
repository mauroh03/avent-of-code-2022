import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Day2 {

    public static void main(String[] args) throws IOException {
        List<String> strategy = new ArrayList<>();

        Files.readAllLines(Path.of("./src/inputs/day2.txt")).forEach(line -> {
            strategy.add(line);
        });

        HashMap<String, Integer> pointTable = new HashMap<>();

        /*
         * Winners
         * A Y 2 + 6
         * B Z 3 + 6
         * C X 1 + 6
         */
        pointTable.put("A Y", (2 + 6));
        pointTable.put("B Z", (3 + 6));
        pointTable.put("C X", (1 + 6));
        /* Losers
         * A Z 3 + 0
         * B X 1 + 0
         * C Y 2 + 0
         */
        pointTable.put("A Z", (3 + 0));
        pointTable.put("B X", (1 + 0));
        pointTable.put("C Y", (2 + 0));
        /* Draw
         * A X 1 + 3
         * B Y 2 + 3
         * C Z 3 + 3
         * */
        pointTable.put("A X", (1 + 3));
        pointTable.put("B Y", (2 + 3));
        pointTable.put("C Z", (3 + 3));

        part1(strategy, pointTable);
    }

    public static void part1(List<String> strategy, HashMap<String, Integer> pointTable) {
        Integer score = 0;

        for(String play: strategy) {
            score += pointTable.get(play);
        }

        System.out.println("Part1 final score: " + score);
    }
}
