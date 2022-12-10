import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day4 {
    public static void main(String[] args) throws Exception {
        List<List<String>> sectionList = new ArrayList<>();

        Files.readAllLines(Path.of("./src/inputs/day4.txt")).forEach(line -> {
            sectionList.add(List.of(line.split(",")[0],line.split(",")[1]));
        });


        Integer needReconsideration = 0;
        Integer overlapping = 0;

        for (List<String> sections : sectionList) {
            List<Integer> section1 = Arrays.stream(sections.get(0).split("-")).map(Integer::parseInt).toList();
            List<Integer> section2 = Arrays.stream(sections.get(1).split("-")).map(Integer::parseInt).toList();

            //part1
            if (validations(section1, section2, "contain") || validations(section2, section1, "contain")) {
                needReconsideration++;
            }

            //part2
            if (validations(section1, section2, "overlap") || validations(section2, section1, "overlap")) {
                overlapping++;
            }
        }

        System.out.println("Part1: " + needReconsideration + "\nPart2: " + overlapping);
    }

    public static Boolean validations(List<Integer> sectionA, List<Integer> sectionB, String option) {
        Boolean containOrOverlap = Boolean.FALSE;

        switch (option) {
            case "contain":
                if(sectionA.get(0) <= sectionB.get(0) && sectionA.get(1) >= sectionB.get(1)) {
                    containOrOverlap = Boolean.TRUE;
                }
                break;
            case "overlap":
                if((sectionA.get(0) >= sectionB.get(0) && sectionA.get(0) <= sectionB.get(1)) ||
                        (sectionB.get(1) >= sectionA.get(0) && sectionB.get(1) <= sectionA.get(1))) {
                    containOrOverlap = Boolean.TRUE;
                }
        }

        return containOrOverlap;
    }
}
