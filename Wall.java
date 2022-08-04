package de.unikl.seda.snake.gui.snake.gameobject;

import de.unikl.seda.snake.gui.tools.SnakeGameDrawer;
import de.unikl.seda.snake.gui.tools.GameObjectManager;
import de.unikl.seda.snake.gui.snake.gameobject.interfaces.GameObject;
import de.unikl.seda.snake.gui.snake.gameobject.interfaces.Hittable;

import java.awt.*;

public class Wall extends GameObject implements Hittable {
    public Wall(Point location) {
        super(location, Color.BLACK);
    }

    @Override
    public void whenHitting(GameObjectManager gameObjectManager) {
        gameObjectManager.dead();
    }

    @Override
    public void draw(SnakeGameDrawer snakeGameDrawer) {
        snakeGameDrawer.drawRect(this.location, this.color);
    }
}
