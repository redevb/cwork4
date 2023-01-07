import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Simulation {
    public static FileService SERVICE = new FileService(Path.of("data/cats.json"));

    public static void displayInfoOfCats(List<Cat> cats) {
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

    public static List<Cat> sortByAverageLvl(List<Cat> cats) {
        return cats.stream()
                .sorted(Comparator.comparing(Cat::getAverage, Comparator.reverseOrder()))
                .collect(Collectors.toList());
    }

    public static List<Cat> sortByName(List<Cat> cats) {
        return cats.stream()
                .sorted(Comparator.comparing(Cat::getName, Comparator.reverseOrder()))
                .collect(Collectors.toList());
    }

    public static List<Cat> sortByAge(List<Cat> cats) {
        return cats.stream()
                .sorted(Comparator.comparing(Cat::getAge, Comparator.reverseOrder()))
                .collect(Collectors.toList());
    }

    public static List<Cat> sortByHealth(List<Cat> cats) {
        return cats.stream()
                .sorted(Comparator.comparing(Cat::getHealthLevel, Comparator.reverseOrder()))
                .collect(Collectors.toList());
    }

    public static List<Cat> sortBySpirit(List<Cat> cats) {
        return cats.stream()
                .sorted(Comparator.comparing(Cat::getSpiritLevel, Comparator.reverseOrder()))
                .collect(Collectors.toList());
    }

    public static List<Cat> sortBySatiety(List<Cat> cats) {
        return cats.stream()
                .sorted(Comparator.comparing(Cat::getSatietyLevel, Comparator.reverseOrder()))
                .collect(Collectors.toList());
    }

    public static void displayActions() {
        System.out.println("CHOOSE ACTION: " +
                "\n[ 1 ] - FEED THE CAT" +
                "\n[ 2 ] - PLAY WITH THE CAT" +
                "\n[ 3 ] - CURE THE CAT" +
                "\n[ 4 ] - GET A NEW CAT" +
                "\n[ 5 ] - THE NEXT DAY" +
                "\n[ 6 ] - SORTING" +
                "\n[ 7 ] - QUIT");
    }

    public static int chooseAction() {
        String s;
        displayActions();
        while (true) {
            System.out.println("ENTER THE NUMBER OF ACTION ABOVE: ");
            try {
                s = new Scanner(System.in).nextLine();
                return checkAction(s);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void getActions(List<Cat> cats) {
        while (true) {
            checkingIfIsCatDeadOrAlive(cats);
            SERVICE.writeFile(cats);
            switch (chooseAction()) {
                case 1:
                    chooseTheCat(cats).feeding();
                    displayInfoOfCats(sortByAverageLvl(cats));
                    break;
                case 2:
                    chooseTheCat(cats).playing();
                    displayInfoOfCats(sortByAverageLvl(cats));
                    break;
                case 3:
                    chooseTheCat(cats).curing();
                    displayInfoOfCats(sortByAverageLvl(cats));
                    break;
                case 4:
                    getANewCat(cats);
                    displayInfoOfCats(sortByAverageLvl(cats));
                    break;
                case 5:
                    getNextDay(cats);
                    displayInfoOfCats(sortByAverageLvl(cats));
                    break;
                case 6:
                    sortingByFeature(cats);
                case 7:
                    return;
            }
        }
    }

    public static void sortingByFeature(List<Cat> cats) {
        while (true) {
            switch (chooseSortingByWhichFeature()) {
                case 1: {
                    sortByName(cats);
                    displayInfoOfCats(sortByName(cats));
                }
                case 2: {
                    sortByAge(cats);
                    displayInfoOfCats(sortByAge(cats));
                }
                case 3: {
                    sortByHealth(cats);
                    displayInfoOfCats(sortByHealth(cats));
                }
                case 4: {
                    sortBySpirit(cats);
                    displayInfoOfCats(sortBySpirit(cats));
                }
                case 5: {
                    sortBySatiety(cats);
                    displayInfoOfCats(sortBySatiety(cats));
                }
                case 6: {
                    sortByAverageLvl(cats);
                    displayInfoOfCats(sortByAverageLvl(cats));
                }
            }
        }
    }

    public static int chooseSortingByWhichFeature() {
        String s;
        displaySortsFeature();
        while (true) {
            System.out.println("ENTER THE NUMBER OF SORTING: ");
            try {
                s = new Scanner(System.in).nextLine();
                return checkingTheSort(s);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void displaySortsFeature() {
        System.out.println("CHOOSE THE SORT FEATURE: " +
                "\n[ 1 ] - SORT BY NAME" +
                "\n[ 2 ] - SORT BY AGE" +
                "\n[ 3 ] - SORT BY HEALTH" +
                "\n[ 4 ] - SORT BY SPIRIT" +
                "\n[ 5 ] - SORT BY SATIETY" +
                "\n[ 6 ] - SORT BY AVERAGE");
    }

    public static int entryAge() {
        String s;
        while (true) {
            System.out.print("ENTER A NEW CAT'S AGE [1..18]: ");
            try {
                s = new Scanner(System.in).nextLine();
                checkingEntryAge(s);
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println(s);
        return Integer.parseInt(s);
    }

    public static String entryName() {
        String s;
        while (true) {
            System.out.print("ENTER A NEW CAT'S NAME: ");
            Scanner sc = new Scanner(System.in);
            try {
                s = checkingEntryName(sc, "[a-zA-Z]+");
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println(s);
        return s;
    }

    public static void checkingEntryAge(String s) throws Exception {
        int en = Integer.parseInt(s);
        if (en < 1 || en > 18) throw new Exception("AGE IS INCORRECT, TRY AGAIN");
    }

    public static String checkingEntryName(Scanner sc, String pt) throws Exception {
        if (!sc.hasNext(pt)) throw new Exception("NAME IS INCORRECT");
        else return sc.next();
    }

    public static int checkingTheSort(String s) throws Exception {
        int in = Integer.parseInt(s);
        if (in < 1 || in > 6)
            throw new Exception("INCORRECT, TRY AGAIN");
        return in;
    }

    public static void checkingIfIsCatDeadOrAlive(List<Cat> cats) {
        var isDead = cats.stream()
                .filter(e -> e.getHealthLevel() <= 0)
                .collect(Collectors.toList());
        if (isDead.size() != 0) {
            for (int i = 0; i < isDead.size(); i++) {
                cats.remove(isDead.get(i));
                System.out.printf("%n%s IS DEAD%n", isDead.get(i).getName());
            }
        }
    }

    public static void getANewCat(List<Cat> cats) {
        Cat cat = new Cat(entryName(), entryAge());
        cat.setSatietyLevel(new Random().nextInt(20) + 81);
        cat.setSpiritLevel(new Random().nextInt(20) + 81);
        cat.setHealthLevel(new Random().nextInt(20) + 81);
        cat.countTheAverage();
        cat.setPerformedAction(false);
        cats.add(cat);
    }

    public static void getNextDay(List<Cat> cats) {
        cats.forEach(Cat::nextDay);
        cats.forEach(Cat::resetNextDay);
    }

    public static int checkAction(String string) throws Exception {
        int s = Integer.parseInt(string);
        if (s < 1 || s > 7) {
            throw new Exception("DON'T HAVE SUCH ACTION, TRY AGAIN");
        }
        return s;
    }

    public static Cat chooseTheCat(List<Cat> cats) {
        String s;
        displayChoiceOfCat(cats);
        while (true) {
            System.out.printf("ENTER CAT NUMBER(1..%s): ", cats.size());
            try {
                s = new Scanner(System.in).nextLine();
                int ch = checkChoiceOfTheCat(s, cats);
                return cats.get(ch - 1);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static int checkChoiceOfTheCat(String s, List<Cat> cats) throws Exception {
        int in = Integer.parseInt(s);
        if (in < 1 || in > cats.size())
            throw new Exception("YOUR CHOICE IS INCORRECT");
        return in;
    }

    public static void displayChoiceOfCat(List<Cat> cats) {
        System.out.println("CHOOSE THE CAT: ");
        for (int i = 0; i < cats.size(); i++) {
            System.out.printf("%s - %s%n", i + 1, cats.get(i).getName());
        }
    }

}
