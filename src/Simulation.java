import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Simulation {
    public void displayInfoOfCats(List<Cat> cats) {
        System.out.println("*----*-----------*---------*----------*------------*---------*---------------*");
        System.out.println("| #  | NAME      | AGE     | HEALTH   | MOOD       | SATIETY | AVERAGE LEVEL |");
        System.out.println("*----*-----------*---------*----------*------------*---------*---------------*");
        for (int i = 0; i < cats.size(); i++) {
            System.out.printf("| %-2s | %-9s |    %-4s |    %-5s |     %-6s |    %-4s |       %-7s |%n", i + 1, cats.get(i).getName(),
                    cats.get(i).getAge(), cats.get(i).getHealthLevel(), cats.get(i).getSpiritLevel(),
                    cats.get(i).getSatietyLevel(), cats.get(i).getAverage());
        }
        System.out.println("*----*-----------*---------*----------*------------*---------*---------------*\n");
    }

    public List<Cat> sortByAverageLvl(List<Cat> cats) {
        return cats.stream()
                .sorted(Comparator.comparing(Cat::getAverage, Comparator.reverseOrder()))
                .collect(Collectors.toList());
    }
}
