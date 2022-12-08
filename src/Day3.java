import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day3 {
    public static void main(String[] args) throws IOException {
        List<String> rucksacks = new ArrayList<>();
        Files.readAllLines(Path.of("./src/inputs/day3.txt")).forEach(line -> {
            rucksacks.add(line);
        });

        part1(rucksacks);
        part2(rucksacks);
    }

    public static void part1(List<String> rucksacks) {
        Integer prioritiesSum = 0;

        for(String rucksack: rucksacks) {
            String compartment1 = rucksack.substring(0, rucksack.length() / 2);
            String compartment2 = rucksack.substring(rucksack.length() / 2, rucksack.length());

            char commonType = validateItemType(List.of(compartment1, compartment2));

            prioritiesSum += checkTypePriority(commonType);
        }

        System.out.println(prioritiesSum);
    }

    public static void part2(List<String> rucksacks) {
        Integer prioritiesSum = 0;
        Integer groupSize = 0;
        List<String> rucksackGroup = new ArrayList<>();
        char commonType;

        for(String rucksack: rucksacks) {

            rucksackGroup.add(rucksack);
            groupSize++;

            if (groupSize == 3) {
                commonType = validateItemType(rucksackGroup);
                prioritiesSum += checkTypePriority(commonType);

                rucksackGroup = new ArrayList<>();
                groupSize = 0;
            }
        }

        System.out.println(prioritiesSum);
    }

    public static char validateItemType(List<String> rucksackGroup) {
        Integer commonTypeCounter = 1;
        Integer groupSize = rucksackGroup.size();
        char commonType = 'a';
        char actualType;

        for (int i = 0; i <= rucksackGroup.get(0).length(); i++) {
            actualType = rucksackGroup.get(0).charAt(i);

            for(int j = 1; j < groupSize; j++) {
                if (rucksackGroup.get(j).contains(String.valueOf(actualType))) {
                    commonTypeCounter++;
                }
            }

            if (commonTypeCounter == groupSize) {
                commonType = actualType;
                break;
            } else {
                commonTypeCounter = 1;
            }
        }

        return commonType;
    }

    public static Integer checkTypePriority(char type) {
        Integer priority = Integer.valueOf(type) - 38;;

        if(Integer.valueOf(type) >= 97) {
            priority = Integer.valueOf(type) - 96;
        }

        return priority;
    }
}
