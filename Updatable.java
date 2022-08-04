package de.unikl.seda.snake.gui.snake.gameobject.interfaces;

import de.unikl.seda.snake.gui.tools.GameObjectManager;
import de.unikl.seda.snake.gui.snake.gameobject.Point;

import java.awt.*;

public abstract class Updatable extends GameObject implements Comparable {

    private static final int BASE = 0;
    protected static int FOOD = BASE + 1;
    protected static int SNAKE_HEAD = FOOD + 1;
    private static int SNAKE_BODY = SNAKE_HEAD + 1;

    protected static int getFoodProperty() {
        SNAKE_HEAD++;
        SNAKE_BODY++;
        return FOOD++;
    }

    protected static int getSnakeBodyProperty() {
        return SNAKE_BODY++;
    }

    protected static int getSnakeTailProperty() {
        return SNAKE_BODY++;
    }

    private int priority;

    public Updatable(Point location, Color color, int priority) {
        super(location, color);
        this.priority = priority;
        System.out.println(color.toString() + " " + priority);
    }

    public abstract void update(GameObjectManager gameObjectManager);

    protected int getPriority() {
        return this.priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public int compareTo(Object o) {
        return  ((Updatable) o).getPriority() - this.priority;
    }
}
