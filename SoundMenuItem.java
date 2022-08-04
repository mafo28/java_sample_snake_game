package de.unikl.seda.snake.gui.menu;

import de.unikl.seda.snake.gui.menu.interfaces.Adjustable;
import de.unikl.seda.snake.settings.SnakeGameSettingsAdjuster;

public class SoundMenuItem extends Adjustable {

    private boolean enable;

    public SoundMenuItem(SnakeGameSettingsAdjuster snakeGameSettingsAdjuster) {
        super(snakeGameSettingsAdjuster);
        this.enable = snakeGameSettingsAdjuster.isSoundEnabled();
    }

    @Override
    public double getValue() {
        if (enable) return 1; return 0;
    }

    @Override
    public void increase() {
        if (enable) {
            enable = false;
            snakeGameSettingsAdjuster.setSoundEnabled(false);
        } else {
            enable = true;
            snakeGameSettingsAdjuster.setSoundEnabled(true);
        }
    }

    @Override
    public void decrease() {
        if (enable) {
            enable = false;
            snakeGameSettingsAdjuster.setSoundEnabled(false);
        } else {
            enable = true;
            snakeGameSettingsAdjuster.setSoundEnabled(true);
        }
    }

    @Override
    public String getName() {
        return "Sound";
    }
}
