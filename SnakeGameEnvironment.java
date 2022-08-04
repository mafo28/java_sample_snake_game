package de.unikl.seda.snake.gui.snake;

import de.unikl.seda.snake.gui.menu.BackToMainMenuItem;
import de.unikl.seda.snake.gui.menu.GameOverMenuItem;
import de.unikl.seda.snake.gui.menu.ResumeMenuItem;
import de.unikl.seda.snake.gui.snake.enums.MainState;
import de.unikl.seda.snake.gui.menu.GameMenu;
import de.unikl.seda.snake.gui.tools.*;
import de.unikl.seda.snake.highscore.HighScore;
import de.unikl.seda.snake.highscore.HighScoreHandler;
import de.unikl.seda.snake.settings.SettingsPersistentHandler;
import de.unikl.seda.snake.settings.SnakeGameSettings;
import de.unikl.seda.snake.settings.SnakeGameSettingsAdjuster;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import static de.unikl.seda.snake.gui.snake.enums.MainState.*;

public class SnakeGameEnvironment extends GameEnvironment {

    public static final int GAME_INFO_BANNER_HEIGHT = 25;
    public static final int INFO_HEIGHT = 18;

    private GameObjectManager gameObjectManager;
    private SnakeGameSettings snakeGameSettings;
    private GameMenu gameMenu;
    private MainState mainState;

    private SnakeGameDrawer snakeGameDrawer;
    private SnakeGameSettingsAdjuster snakeGameSettingsAdjuster;

    public SnakeGameEnvironment() {
        // sets the size of the snake environment
        super(0, 0, 0);
        this.snakeGameSettings = new SnakeGameSettings(this);
         snakeGameSettingsAdjuster = SettingsPersistentHandler.readSettings();
        if (snakeGameSettingsAdjuster == null) {
            snakeGameSettingsAdjuster = new SnakeGameSettingsAdjuster(this.snakeGameSettings);
        } else {
            snakeGameSettingsAdjuster.setupSnakeGameSettings(this.snakeGameSettings);
        }
        goToMainMenu();
    }

    @Override
    protected void handleKeypressUp() {
        if (mainState.handleKeypressUp(this)) {
            uiUpdateThread.interrupt();
        }
    }

    @Override
    protected void handleKeypressDown() {
        if (mainState.handleKeypressDown(this)) {
            uiUpdateThread.interrupt();
        }
    }

    @Override
    protected void handleKeypressLeft() {
        if (mainState.handleKeypressLeft(this)) {
            uiUpdateThread.interrupt();
        }
    }

    @Override
    protected void handleKeypressRight() {
        if (mainState.handleKeypressRight(this)) {
            uiUpdateThread.interrupt();
        }
    }

    @Override
    protected void handleReturnPress() {
        if (mainState.handleReturnPress(this)) {
            uiUpdateThread.interrupt();
        }
    }

    @Override
    protected void handleEscapePress() {
        if (mainState.handleEscapePress(this)) {
            uiUpdateThread.interrupt();
        }
    }

    @Override
    protected void drawSnakeEnvironment(Graphics2D graphics) {
        this.snakeGameDrawer = new SnakeGameDrawer(snakeGameSettings, graphics);
        this.snakeGameDrawer.drawInGameBanner(this);
        mainState.draw(this);
    }

    public void makeThreadSleep() throws InterruptedException {
        mainState.makeThreadSleep(this);
    }

    public void updateState() {
        mainState.update(this);
    }

    public SnakeGameSettings getSnakeGameSettings() {
        return snakeGameSettings;
    }

    public GameObjectManager getGameObjectManager() {
        return gameObjectManager;
    }

    public GameMenu getGameMenu() {
        return gameMenu;
    }

    public SnakeGameDrawer getSnakeGameDrawer() {
        return snakeGameDrawer;
    }

    public void startGame() {
        this.gameObjectManager = new GameObjectManager(this);
        this.mainState = IN_GAME;
    }

    public void goToMainMenu() {
        this.gameMenu = GameMenu.createMainMenu(snakeGameSettingsAdjuster);
        this.mainState = IN_MENU;
    }

    public void goToMenu(GameMenu gameMenu) {
        this.gameMenu = gameMenu;
    }

    public void goToPauseMenu() {
        this.gameMenu = new GameMenu(
                null,
                Arrays.asList(
                    new ResumeMenuItem(),
                    new BackToMainMenuItem()
                ),
                "pause"
        );

        this.mainState = IN_MENU;
    }

    public void resumeGame() {
        this.mainState = IN_GAME;
    }

    public void goToGameOverMenu(int score) {
        HighScore h = new HighScore(snakeGameSettings.getPlayerName(),  score, new Date()
                , snakeGameSettings.getGameSpeed(), snakeGameSettings.getGameLevel().toString());
        if (HighScoreHandler.isNewHighScore(h)) {
            JOptionPane.showMessageDialog(null, "You had reach the top 10");
            HighScoreHandler.updateHighScore(h);
        }
        this.gameMenu = new GameMenu(
                null,
                Collections.singletonList(new GameOverMenuItem()),
                "game over"
        );

        this.mainState = IN_MENU;

    }
}
