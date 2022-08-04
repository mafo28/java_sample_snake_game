package de.unikl.seda.snake.gui.snake.gameobject;

import de.unikl.seda.snake.gui.snake.enums.Direction;
import de.unikl.seda.snake.gui.snake.gameobject.interfaces.Updatable;
import de.unikl.seda.snake.gui.tools.ResourceManager;
import de.unikl.seda.snake.gui.tools.SnakeGameDrawer;
import de.unikl.seda.snake.gui.tools.GameObjectManager;
import de.unikl.seda.snake.gui.snake.gameobject.interfaces.GameObject;
import de.unikl.seda.snake.gui.snake.gameobject.interfaces.Hittable;

import java.awt.*;

import static de.unikl.seda.snake.gui.snake.enums.Direction.IDLE;

public class SnakeBody extends SnakeHead implements Hittable {

    protected SnakeHead successor;
    private Direction nextDirection;

    public SnakeBody(Point location, SnakeHead successor) {
        super(location);
        setPriority(getSnakeBodyProperty());
        this.successor = successor;
        setCurrentDirection(successor.getCurrentDirection());
        this.nextDirection = successor.getCurrentDirection();
    }

    @Override
    public void whenHitting(GameObjectManager gameObjectManager) {
        gameObjectManager.dead();
    }

    @Override
    public void update(GameObjectManager gameObjectManager) {
        if (gameObjectManager.getSnakeHead().getCurrentDirection() == IDLE) {
            return;
        }
        if (successor instanceof SnakeBody) {
            setCurrentDirection(successor.getCurrentDirection());
        } else {
            setCurrentDirection(nextDirection);
        }
        Point currentLocation = getLocation();
        currentLocation.setX(successor.getLocation().getX());
        currentLocation.setY(successor.getLocation().getY());

        nextDirection = successor.getCurrentDirection();
    }

    @Override
    public void draw(SnakeGameDrawer snakeGameDrawer) {
        if (successor instanceof SnakeBody) {
            snakeGameDrawer.drawImage(ResourceManager.getImage(getCurrentDirection().bodyImageCode(successor.getCurrentDirection())), this.location);

        } else {
            snakeGameDrawer.drawImage(ResourceManager.getImage(getCurrentDirection().bodyImageCode(nextDirection)), this.location);
        }
    }
}
