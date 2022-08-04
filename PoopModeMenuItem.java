package de.unikl.seda.snake.gui.menu;

import de.unikl.seda.snake.gui.menu.interfaces.Adjustable;
import de.unikl.seda.snake.settings.SnakeGameSettingsAdjuster;

public class PoopModeMenuItem extends Adjustable {
    private boolean enable;
    public PoopModeMenuItem(SnakeGameSettingsAdjuster snakeGameSettingsAdjuster) {
        super(snakeGameSettingsAdjuster);
        this.enable = snakeGameSettingsAdjuster.getPoopMode();
    }

    @Override
    public double getValue() {
        if (enable) return 1; return 0;
    }

    @Override
    public void increase() {
        if (enable) {
            enable = false;
            snakeGameSettingsAdjuster.setPoopMode(false);
        } else {
            enable = true;
            snakeGameSettingsAdjuster.setPoopMode(true);
        }
    }

    @Override
    public void decrease() {
        if (enable) {
            enable = false;
            snakeGameSettingsAdjuster.setPoopMode(false);
        } else {
            enable = true;
            snakeGameSettingsAdjuster.setPoopMode(true);
        }
    }

    @Override
    public String getName() {
        return "Poop Mode";
    }
}
