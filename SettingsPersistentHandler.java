package de.unikl.seda.snake.settings;

import java.io.*;

public class SettingsPersistentHandler {
    private static String path = "settings.txt";
    private static File file = new File(path);

    static {
        try (FileInputStream f = new FileInputStream(file);
             ObjectInputStream oi = new ObjectInputStream(f)) {
            // Read objects
        } catch (IOException e) {
            writeSettings(null);
        }
    }

    public static SnakeGameSettingsAdjuster readSettings() {
        try (FileInputStream f = new FileInputStream(file);
             ObjectInputStream oi = new ObjectInputStream(f)) {
            // Read objects
            return  (SnakeGameSettingsAdjuster) oi.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException ignored) {
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean writeSettings(SnakeGameSettingsAdjuster adjuster) {
        try (FileOutputStream f = new FileOutputStream(file);
             ObjectOutputStream o = new ObjectOutputStream(f)) {
            // Write objects to file
            o.writeObject(adjuster);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            return false;
        } catch (IOException e) {
            System.out.println("Error initializing stream");
            return false;
        }
        return true;
    }
}
