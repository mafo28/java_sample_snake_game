package de.unikl.seda.snake.highscore;

import java.io.IOException;
import java.util.List;

public class HighScoreHandler {

    public List<HighScore> readHighScores() throws IOException {
        return JsonWriter.read();
    }

    public void WriteHighScore(List<HighScore> highScores) throws IOException {
        JsonWriter.write(highScores);
    }

    public boolean isNewHighScore(HighScore highScore) throws IOException {
        List<HighScore> highScores = readHighScores();
        for(HighScore tmp: highScores){
            if (tmp.getAchievedPoints() >= highScore.getAchievedPoints())
                return false;
        }
        return true;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class HighScoreHandler {
    private static String path = "highscore.txt";
    private static File file = new File(path);

    static {
        try (FileInputStream f = new FileInputStream(file);
             ObjectInputStream oi = new ObjectInputStream(f)) {
            // Read objects
        } catch (IOException e) {
            List<HighScore> res = new ArrayList<>();
            res.add(new HighScore("default",0,null,0,"default"));
            writeHighScore(res);
        }
    }

    public static List<HighScore> readHighScores() {
        List<HighScore> res = new ArrayList<>();
        try (FileInputStream f = new FileInputStream(file);
             ObjectInputStream oi = new ObjectInputStream(f)) {
            // Read objects
            HighScore temp = (HighScore) oi.readObject();
            while (temp !=null) {
                res.add(temp);
                temp = (HighScore) oi.readObject();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException ignored) {
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return res;
    }

    private static void writeHighScore(List<HighScore> highScores) {
        try (FileOutputStream f = new FileOutputStream(file);
             ObjectOutputStream o = new ObjectOutputStream(f)) {
            // Write objects to file
            for (HighScore highScore : highScores) {
                o.writeObject(highScore);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        }
    }

    public static boolean isNewHighScore(HighScore highScore) {
        return readHighScores().stream()
                .map(HighScore::getAchievedPoints)
                .anyMatch(
                    highScore1 -> highScore.getAchievedPoints() > highScore1
                );
    }

    public static void updateHighScore(HighScore highScore) {
        List<HighScore> highScoresList = HighScoreHandler.readHighScores();
        highScoresList.add(highScore);
        if (highScoresList.size() > 10) {
            highScoresList.sort(Comparator.comparing(HighScore::getAchievedPoints));
            highScoresList.remove(0);
        }
        HighScoreHandler.writeHighScore(highScoresList);
>>>>>>> origin
    }

}
