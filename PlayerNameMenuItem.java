package de.unikl.seda.snake.gui.menu;

import de.unikl.seda.snake.gui.snake.SnakeGameEnvironment;
import de.unikl.seda.snake.gui.menu.interfaces.Selectable;

import javax.swing.*;

public class PlayerNameMenuItem implements Selectable {
    @Override
    public void selected(SnakeGameEnvironment snakeGameEnvironment) {
        snakeGameEnvironment.getSnakeGameSettings().setPlayerName((String) JOptionPane.showInputDialog(
                null,
                "Write your name",
                "Change the name",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                "Red"
        ));
    }

    @Override
    public String getName() {
        return "name";
    }
}