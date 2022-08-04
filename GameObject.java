package de.unikl.seda.snake.gui.snake.gameobject.interfaces;

import de.unikl.seda.snake.gui.tools.SnakeGameDrawer;
import de.unikl.seda.snake.gui.snake.interfaces.Drawable;
import de.unikl.seda.snake.gui.snake.gameobject.Point;

import java.awt.*;

public abstract class GameObject implements Drawable {

    protected Point location;
    protected Color color;

    public GameObject(Point location, Color color) {
        this.location = location;
        this.color = color;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public abstract void draw(SnakeGameDrawer snakeGameDrawer);
}
