package de.unikl.seda.snake.gui.menu;

import de.unikl.seda.snake.settings.SnakeGameSettingsAdjuster;
import de.unikl.seda.snake.gui.snake.enums.GameLevel;
import de.unikl.seda.snake.gui.menu.interfaces.Adjustable;

import static de.unikl.seda.snake.settings.SnakeGameSettingsAdjuster.UNDER_BOUND;
import static de.unikl.seda.snake.settings.SnakeGameSettingsAdjuster.UPPER_BOUND;

public class LevelMenuItem extends Adjustable {
    private int level;
    public LevelMenuItem(SnakeGameSettingsAdjuster snakeGameSettingsAdjuster) {
        super(snakeGameSettingsAdjuster);
        this.level = snakeGameSettingsAdjuster.getGameLevel();
    }

    @Override
    public double getValue() {
        return level;
    }

    @Override
    public void increase() {
        if (this.level >= UPPER_BOUND) {
            return;
        }
        snakeGameSettingsAdjuster.setGameLevel(++this.level);
    }

    @Override
    public void decrease() {
        if (this.level <= UNDER_BOUND) {
            return;
        }
        snakeGameSettingsAdjuster.setGameLevel(--this.level);
    }

    @Override
    public String getName() {
        return "level";
    }
}