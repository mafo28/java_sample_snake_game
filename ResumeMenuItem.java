package de.unikl.seda.snake.gui.menu;

import de.unikl.seda.snake.gui.snake.SnakeGameEnvironment;
import de.unikl.seda.snake.gui.snake.enums.MainState;
import de.unikl.seda.snake.gui.menu.interfaces.Selectable;

public class ResumeMenuItem implements Selectable {
    @Override
    public void selected(SnakeGameEnvironment snakeGameEnvironment) {
        snakeGameEnvironment.resumeGame();
    }

    @Override
    public String getName() {
        return "resume";
    }
}
