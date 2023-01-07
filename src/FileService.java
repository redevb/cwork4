import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileService {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private final Path path;

    public FileService(Path path) {
        this.path = path;
    }

    public List<Cat> getCats() {
        String json;
        Type type = new TypeToken<List<Cat>>() {
        }.getType();
        try {
            json = Files.readString(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return GSON.fromJson(json, type);
    }

    public void writeFile(List<Cat> cats) {
        String json = GSON.toJson(cats);

        byte[] arr = json.getBytes();
        try {
            Files.write(path, arr);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
