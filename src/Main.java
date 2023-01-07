import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Wish Success");
        Simulation simulation = new Simulation();
        simulation.displayInfoOfCats(simulation.sortByAverageLvl(List.of()));
    }
}