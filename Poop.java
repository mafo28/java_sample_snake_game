package de.unikl.seda.snake.gui.snake.gameobject;

import de.unikl.seda.snake.gui.tools.ResourceManager;
import de.unikl.seda.snake.gui.tools.SnakeGameDrawer;
import de.unikl.seda.snake.gui.tools.GameObjectManager;
import de.unikl.seda.snake.gui.snake.gameobject.interfaces.GameObject;
import de.unikl.seda.snake.gui.snake.gameobject.interfaces.Hittable;

import java.awt.*;

public class Poop extends GameObject implements Hittable {
    public Poop(Point location) {
        super(location, Color.ORANGE.darker());
    }

    @Override
    public void whenHitting(GameObjectManager gameObjectManager) {
        gameObjectManager.decreaseLife();
        if (gameObjectManager.getLife() == 0) {
            gameObjectManager.dead();
        }
        gameObjectManager.removeObject(this);
    }

    @Override
    public void draw(SnakeGameDrawer snakeGameDrawer) {
        snakeGameDrawer.drawImage(ResourceManager.getImage(ResourceManager.POOP), this.location);
    }
}
