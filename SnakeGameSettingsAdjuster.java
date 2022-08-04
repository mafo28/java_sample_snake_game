package de.unikl.seda.snake.gui.tools;

import de.unikl.seda.snake.gui.snake.enums.GameLevel;

import java.util.HashMap;
import java.util.Map;

public class SnakeGameSettingsAdjuster {

    public final static int SLOW = 1;
    public final static int MEDIUM = 2;
    public final static int FAST = 3;

    public final static int SMALL = 1;
    public final static int REGULAR = 2;
    public final static int BIG = 3;

    private final static int speedBias = 100;

    private final static int speedNorm = 25;
    private final static int heightNorm = 9 * 30;
    private final static int widthNorm = 16 * 30;
    private final static int pixelNorm = 20;
    private final static Map<Integer, GameLevel> gameLevelMap;

    static {
        gameLevelMap = new HashMap<>();
        for (GameLevel gameLevel : GameLevel.values()) {
            gameLevelMap.put(gameLevel.getConst(), gameLevel);
        }
    }

    private SnakeGameSettings snakeGameSettings;

    private int gameLevel;
    private int speedLevel;
    private int screenSize;

    private boolean soundEnabled;

    public SnakeGameSettingsAdjuster(SnakeGameSettings snakeGameSettings) {
        this.snakeGameSettings = snakeGameSettings;
        setGameLevel(snakeGameSettings.getGameLevel().getConst());
        setSpeedLevel(1);
        setScreenSize(1);
        setSoundEnabled(true);
    }

    public int getGameLevel() {
        return gameLevel;
    }

    public void setGameLevel(int gameLevel) {
        this.gameLevel = gameLevel;
        snakeGameSettings.setGameLevel(gameLevelMap.get(this.gameLevel));
    }

    public int getSpeedLevel() {
        return speedLevel;
    }

    public void setSpeedLevel(int speedLevel) {
        this.speedLevel = speedLevel;
        snakeGameSettings.setGameSpeed(speedBias - speedLevel * speedNorm);
    }

    public int getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(int screenSize) {
        this.screenSize = screenSize;
        snakeGameSettings.setSquareSize(screenSize * pixelNorm);
        snakeGameSettings.setWidth(screenSize * widthNorm);
        snakeGameSettings.setHeight(screenSize * heightNorm);
    }

    public boolean isSoundEnabled() {
        return soundEnabled;
    }

    public void setSoundEnabled(boolean soundEnabled) {
        this.soundEnabled = soundEnabled;
        snakeGameSettings.setSoundEnabled(soundEnabled);
    }
}
