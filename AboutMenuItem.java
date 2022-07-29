package de.unikl.seda.snake.gui.menu;

import de.unikl.seda.snake.gui.snake.SnakeGameEnvironment;
import de.unikl.seda.snake.gui.menu.interfaces.Selectable;

import javax.swing.*;

public class AboutMenuItem implements Selectable {
    @Override
    public void selected(SnakeGameEnvironment snakeGameEnvironment) {
        JOptionPane.showMessageDialog(null, "Title: Snake\n " +
                "Developers: Minh Duc Duong, Duy Nguyen Dinh, Merveille Kana Tsopze Mafo\n" +
                "Contact information: duong@rhrk.uni-kl.de\n" +
                "Version date: 31.12.2020\n" +
                "Game version: 1.5");

    }

    @Override
    public String getName() {
        return "About";
    }
}
