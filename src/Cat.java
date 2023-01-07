import java.util.List;
import java.util.Random;

public class Cat {
    private static final Random RND = new Random();
    private String name;
    private int age;
    private int satietyLevel;
    private int spiritLevel;
    private int healthLevel;
    private static final List<String> names = List.of("LEOPOLD", "TOM", "LUCIFER", "NALA", "FELIX", "GARFIELD", "BIRBA", "HIPPO_CAT", "MATROSKIN", "SIMBA");
    private int average;


    public Cat(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Cat() {
        this.name = names.get(RND.nextInt(names.size()));
        this.age = RND.nextInt(1) + 19;
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

    public void countTheAverage() {
        average = (satietyLevel + spiritLevel + healthLevel) / 3;
    }

}
