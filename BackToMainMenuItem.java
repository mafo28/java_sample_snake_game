package de.unikl.seda.snake.gui.menu;

import de.unikl.seda.snake.gui.snake.SnakeGameEnvironment;
import de.unikl.seda.snake.gui.menu.interfaces.Selectable;
import de.unikl.seda.snake.gui.tools.ResourceManager;

public class BackToMainMenuItem implements Selectable {
    @Override
    public void selected(SnakeGameEnvironment snakeGameEnvironment) {
        ResourceManager.stopBackgroundSound();
        snakeGameEnvironment.goToMainMenu();
    }

    @Override
    public String getName() {
        return "back to main menu";
    }
}
