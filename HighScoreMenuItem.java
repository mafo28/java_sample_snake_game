package de.unikl.seda.snake.gui.menu;

import de.unikl.seda.snake.gui.menu.interfaces.Selectable;
import de.unikl.seda.snake.gui.snake.SnakeGameEnvironment;
import de.unikl.seda.snake.highscore.HighScore;
import de.unikl.seda.snake.highscore.HighScoreHandler;

import javax.swing.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HighScoreMenuItem implements Selectable {
    @Override
    public String getName() {
        return "High Score";
    }

    @Override
    public void selected(SnakeGameEnvironment snakeGameEnvironment) {
        List<HighScore> highScores = HighScoreHandler.readHighScores();
        highScores.sort(Comparator.comparing(HighScore::getAchievedPoints));
        Collections.reverse(highScores);
        StringBuilder sb = new StringBuilder();
        int counter = 1;
        for (HighScore h : highScores) {
            sb.append(h.toString());
            sb.append("\n");
            if (counter <= 10) {
                counter++;
            } else {
                break;
            }
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

}