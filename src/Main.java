import java.nio.file.Path;

public class Main {
    static FileService SERVICE = new FileService(Path.of("data/cats.json"));
    public static void main(String[] args) {

        System.out.println("Wish Success");
        var cats = SERVICE.getCats();
        Simulation simulation = new Simulation();
        simulation.displayInfoOfCats(simulation.sortByAverageLvl(cats));
        Simulation.getActions(cats);
    }
}