package de.unikl.seda.snake.highscore;
import java.util.List;
import java.io.File;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

public class JsonWriter {

    public static void write(List<HighScore> highscores) throws IOException {

        // create a object that allows writing to the file highscores.json
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File("c:\\highscores.json"), highscores );
    }

    public static List<HighScore> read() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue("C:\\highscores.json", new TypeReference<List<HighScore>>(){});
    }
}