package de.unikl.seda.snake.gui.menu;

import de.unikl.seda.snake.gui.menu.interfaces.Adjustable;
import de.unikl.seda.snake.settings.SnakeGameSettingsAdjuster;

import static de.unikl.seda.snake.settings.SnakeGameSettingsAdjuster.UNDER_BOUND;
import static de.unikl.seda.snake.settings.SnakeGameSettingsAdjuster.UPPER_BOUND;

public class NumFoodMenuItem extends Adjustable {
    private int num;

    public NumFoodMenuItem(SnakeGameSettingsAdjuster snakeGameSettingsAdjuster) {
        super(snakeGameSettingsAdjuster);
        this.num = snakeGameSettingsAdjuster.getNumOfFoods();
    }

    @Override
    public double getValue() {
        return num;
    }

    @Override
    public void increase() {
        if (this.num >= UPPER_BOUND) {
            return;
        }
        snakeGameSettingsAdjuster.setNumOfFoods(++this.num);
    }

    @Override
    public void decrease() {
        if (this.num <= UNDER_BOUND) {
            return;
        }
        snakeGameSettingsAdjuster.setNumOfFoods(--this.num);
    }

    @Override
    public String getName() {
        return "Number of Foods";
    }
}