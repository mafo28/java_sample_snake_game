package de.unikl.seda.snake;

import de.unikl.seda.snake.gui.snake.SnakeGameEnvironment;
import de.unikl.seda.snake.gui.tools.GuiContainer;

import java.io.File;
import java.util.Scanner;

public class Main {
    static Scanner s = new Scanner(System.in);
    public static void main(String[] args) {
        SnakeGameEnvironment game = new SnakeGameEnvironment();
        // Start game session
        GuiContainer.show("Snake", game);
    }
}

