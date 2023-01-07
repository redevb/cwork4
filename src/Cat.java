import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Cat implements Feedable, Playable, Curable {
    private static final Random RND = new Random();
    private String name;
    private int age;
    private int satietyLevel;
    private int spiritLevel;
    private int healthLevel;
    private static final List<String> names = List.of("LEOPOLD", "TOM", "LUCIFER", "NALA", "FELIX", "GARFIELD", "BIRBA", "HIPPO_CAT", "MATROSKIN", "SIMBA");
    private int average;
    private boolean isPerformedAction;


    public Cat(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Cat() {
        this.name = names.get(RND.nextInt(names.size()));
        this.age = RND.nextInt(18) + 1;
        this.satietyLevel = RND.nextInt(20) + 81;
        this.spiritLevel = RND.nextInt(20) + 81;
        this.healthLevel = RND.nextInt(20) + 81;
    }

    public int getAverage() {
        return average;
    }

    public void setAverage(int average) {
        this.average = average;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSatietyLevel() {
        return satietyLevel;
    }

    public void setSatietyLevel(int satietyLevel) {
        if (getSatietyLevel() <= 0) {
            this.satietyLevel = 0;
        } else {
            this.satietyLevel = Math.min(satietyLevel, 100);
        }
    }

    public int getSpiritLevel() {
        return spiritLevel;
    }

    public void setSpiritLevel(int spiritLevel) {
        if (getSpiritLevel() <= 0) {
            this.spiritLevel = 0;
        } else {
            this.spiritLevel = Math.min(spiritLevel, 100);
        }
    }

    public int getHealthLevel() {
        return healthLevel;
    }

    public void setHealthLevel(int healthLevel) {
        if (getHealthLevel() <= 0) {
            this.healthLevel = 0;
        } else {
            this.healthLevel = Math.min(healthLevel, 100);
        }
    }

    public static List<Cat> createCatsCats(int a) {
        return Stream.generate(Cat::new)
                .distinct()
                .limit(a)
                .collect(Collectors.toList());
    }

    public void countTheAverage() {
        average = (satietyLevel + spiritLevel + healthLevel) / 3;
    }

    public boolean isPerformedAction() {
        return isPerformedAction;
    }

    public void setPerformedAction(boolean performedAction) {
        isPerformedAction = performedAction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cat cat = (Cat) o;
        return Objects.equals(name, cat.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public void nextDay() {
        satietyLevel -= RND.nextInt(6) + 1;
        spiritLevel += RND.nextInt(4) + (-3);
        healthLevel += RND.nextInt(4) + (-3);
        countTheAverage();
    }

    public void resetNextDay() {
        isPerformedAction = false;
        name = name.replace(" *", "");
    }

    @Override
    public void curing() {
        if (!isPerformedAction()) {
            System.out.printf("%nYOU CURED THE CAT %s, %s YEARS OLD%n%n", getName(), getAge());
            if (getAge() < 6) {
                healthLevel += 7;
                spiritLevel -= 3;
                satietyLevel -= 3;
            } else if (getAge() >= 6 && getAge() <= 10) {
                healthLevel += 5;
                spiritLevel -= 5;
                satietyLevel -= 5;
            } else if (getAge() > 10) {
                healthLevel += 4;
                spiritLevel -= 6;
                satietyLevel -= 6;
            }
            setPerformedAction(true);
            countTheAverage();
            name = name + " *";
        } else System.out.println("CAN'T PERFORM AN ACTION WITH THIS CAT TODAY\n");
    }

    @Override
    public void feeding() {
        if (!isPerformedAction()) {
            int venom = RND.nextInt(4) + 1;
            if (venom != 1) {
                System.out.printf("%nYOU FED THE CAT %s, %s YEARS OLD%n%n", getName(), getAge());
                if (getAge() < 6) {
                    satietyLevel += 7;
                    spiritLevel += 7;
                } else if (getAge() >= 6 && getAge() <= 10) {
                    satietyLevel += 5;
                    spiritLevel += 5;
                } else if (getAge() > 10) {
                    satietyLevel += 4;
                    spiritLevel += 4;
                }
            } else {
                System.out.printf("%nYOU POISONED THE CAT %s. MOOD AND HEALTH LEVELS DECREASED BY 10%n%n", getName());
                spiritLevel -= 10;
                healthLevel -= 10;
            }
            setPerformedAction(true);
            countTheAverage();
            name = name + " *";
        } else System.out.println("CAN'T PERFORM AN ACTION WITH THIS CAT TODAY\n");
    }

    @Override
    public void playing() {
        if (!isPerformedAction()) {
            int venom = RND.nextInt(5) + 1;
            if (venom != 1) {
                System.out.printf("%nYOU PLAYED THE CAT %s, %s YEARS OLD%n%n", getName(), getAge());
                if (getAge() < 6) {
                    spiritLevel += 7;
                    healthLevel += 7;
                    satietyLevel -= 3;
                } else if (getAge() >= 6 && getAge() <= 10) {
                    spiritLevel += 5;
                    healthLevel += 5;
                    satietyLevel -= 5;
                } else if (getAge() > 10) {
                    spiritLevel += 5;
                    healthLevel += 4;
                    satietyLevel -= 6;
                }
            } else {
                System.out.printf("%n%s WAS INJURED. MOOD AND HEALTH LEVELS DECREASED BY 10%n%n", getName());
                spiritLevel -= 10;
                healthLevel -= 10;
            }
            setPerformedAction(true);
            countTheAverage();
            name = name + " *";
        } else System.out.println("CAN'T PERFORM AN ACTION WITH THIS CAT TODAY\n");
    }
}
