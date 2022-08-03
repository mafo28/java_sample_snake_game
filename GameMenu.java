package de.unikl.seda.snake.gui.menu;

import de.unikl.seda.snake.gui.snake.SnakeGameEnvironment;
import de.unikl.seda.snake.gui.tools.SnakeGameDrawer;
import de.unikl.seda.snake.settings.SnakeGameSettingsAdjuster;
import de.unikl.seda.snake.gui.snake.interfaces.Drawable;
import de.unikl.seda.snake.gui.menu.interfaces.Adjustable;
import de.unikl.seda.snake.gui.menu.interfaces.MenuItem;
import de.unikl.seda.snake.gui.menu.interfaces.Selectable;

import java.util.Arrays;
import java.util.List;

public class GameMenu implements Drawable, Selectable {

    private String name;
    private GameMenu parentGameMenu;
    private List<MenuItem> childItem;
    private int position;

    public GameMenu(GameMenu parentGameMenu, List<MenuItem> childItem, String name) {
        this.parentGameMenu = parentGameMenu;
        this.childItem = childItem;
        this.name = name;
        this.position = 0;
    }

    @Override
    public void draw(SnakeGameDrawer snakeGameDrawer) {
        snakeGameDrawer.drawMenu(childItem, position);
    }

    public boolean selectItem(SnakeGameEnvironment snakeGameEnvironment) {
        if (childItem.get(position) instanceof Selectable) {
            ((Selectable) childItem.get(position)).selected(snakeGameEnvironment);
            return true;
        }
        return false;
    }

    @Override
    public void selected(SnakeGameEnvironment snakeGameEnvironment) {
        snakeGameEnvironment.goToMenu(this);
    }

    public boolean back(SnakeGameEnvironment snakeGameEnvironment) {
        if (parentGameMenu == null) {
            return false;
        }
        snakeGameEnvironment.goToMenu(parentGameMenu);
        return true;
    }

    public void up() {
        if (position == 0) {
            position = childItem.size() - 1;
        } else {
            position--;
        }
    }

    public void down() {
        if (position == childItem.size() - 1) {
            position = 0;
        } else {
            position++;
        }
    }

    public boolean left() {
        MenuItem item = this.childItem.get(position);
        if (item instanceof Adjustable) {
            ((Adjustable) item).decrease();
            return true;
        }
        return false;
    }

    public boolean right() {
        MenuItem item = this.childItem.get(position);
        if (item instanceof Adjustable) {
            ((Adjustable) item).increase();
            return true;
        }
        return false;
    }

    public String getName() {
        return name;
    }

    private void setParentGameMenu(GameMenu gameMenu) {
        this.parentGameMenu = gameMenu;
    }

    public static GameMenu createMainMenu(SnakeGameSettingsAdjuster snakeGameSettingsAdjuster) {
        GameMenu gameMenuSettings = new GameMenu(null,
                Arrays.asList(
                        new ResolutionMenuItem(snakeGameSettingsAdjuster),
                        new SpeedMenuItem(snakeGameSettingsAdjuster),
                        new LevelMenuItem(snakeGameSettingsAdjuster),
                        new PlayerNameMenuItem(),
                        new SoundMenuItem(snakeGameSettingsAdjuster),
                        new NumFoodMenuItem(snakeGameSettingsAdjuster),
                        new PoopModeMenuItem(snakeGameSettingsAdjuster)
                ),
                "Settings");
        GameMenu gameMenuRoot = new GameMenu(null,
                Arrays.asList(
                        new PlayMenuItem(),
                        gameMenuSettings,
                        new HighScoreMenuItem(),
                        new AboutMenuItem(),
                        new QuitMenuItem()),
                "root");
        gameMenuSettings.setParentGameMenu(gameMenuRoot);
        return gameMenuRoot;
    }
}
