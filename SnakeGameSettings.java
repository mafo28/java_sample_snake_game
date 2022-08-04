package de.unikl.seda.snake.gui.tools;

import de.unikl.seda.snake.gui.snake.SnakeGameEnvironment;
import de.unikl.seda.snake.gui.snake.enums.GameLevel;

import static de.unikl.seda.snake.gui.snake.enums.GameLevel.*;

public class SnakeGameSettings {

    private String playerName;
    private GameLevel gameLevel;
    private int gameSpeed;
    private boolean soundEnabled;

    private int squareSize;
    private int height;
    private int width;
    private int xBound;
    private int yBound;

    private SnakeGameEnvironment snakeGameEnvironment;

    public SnakeGameSettings(SnakeGameEnvironment snakeGameEnvironment) {
        this.snakeGameEnvironment = snakeGameEnvironment;
        playerName = "player";
        setGameLevel(NO_BORDER);
        gameSpeed = 200;
        squareSize = 50;
        height = 600;
        width = 800;
        soundEnabled = true;

        this.xBound = this.width / this.squareSize;
        this.yBound = this.height / this.squareSize;
    }


    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public GameLevel getGameLevel() {
        return gameLevel;
    }

    public void setGameLevel(GameLevel gameLevel) {
        this.gameLevel = gameLevel;
    }

    public int getGameSpeed() {
        return gameSpeed;
    }

    public void setGameSpeed(int gameSpeed) {
        this.gameSpeed = gameSpeed;
        snakeGameEnvironment.setGameSpeed(this.gameSpeed);
    }

    public int getSquareSize() {
        return squareSize;
    }

    public void setSquareSize(int squareSize) {
        this.squareSize = squareSize;
        this.width = this.squareSize *(this.width / this.squareSize);
        this.height = this.squareSize *(this.height / this.squareSize);
        this.xBound = this.width / this.squareSize;
        this.yBound = this.height / this.squareSize;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.yBound = height / this.squareSize;
        this.height = this.squareSize * this.yBound;
        snakeGameEnvironment.setHeight(this.height);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.xBound = width / this.squareSize;
        this.width = this.squareSize * this.xBound;
        snakeGameEnvironment.setWidth(this.width);
    }

    public int getxBound() {
        return xBound;
    }

    public int getyBound() {
        return yBound;
    }

    public SnakeGameEnvironment getSnakeGameEnvironment() {
        return snakeGameEnvironment;
    }

    public boolean isSoundEnabled() {
        return soundEnabled;
    }

    public void setSoundEnabled(boolean soundEnabled) {
        this.soundEnabled = soundEnabled;
    }

}
