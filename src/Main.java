import java.nio.file.Path;

public class Main {
    static FileService service = new FileService(Path.of("data/cats.json"));
    public static void main(String[] args) {

        System.out.println("Wish Success");
        var cats = service.getCats();
        Simulation simulation = new Simulation();
        simulation.displayInfoOfCats(simulation.sortByAverageLvl(cats));
    }
}