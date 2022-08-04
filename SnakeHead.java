package de.unikl.seda.snake.gui.snake.gameobject;

import de.unikl.seda.snake.gui.tools.ResourceManager;
import de.unikl.seda.snake.gui.tools.SnakeGameDrawer;
import de.unikl.seda.snake.gui.tools.GameObjectManager;
import de.unikl.seda.snake.gui.snake.enums.Direction;
import de.unikl.seda.snake.gui.snake.gameobject.interfaces.Updatable;

import java.awt.*;

import static de.unikl.seda.snake.gui.snake.enums.Direction.*;

public class SnakeHead extends Updatable {

    // The snake is not moving, facing right
    private Direction currentDirection = IDLE;

    public SnakeHead(Point location) {
        super(location, Color.RED, SNAKE_HEAD);
    }

    @Override
    public void update(GameObjectManager gameObjectManager) {
        currentDirection.update(gameObjectManager);
    }

    @Override
    public void draw(SnakeGameDrawer snakeGameDrawer) {
        snakeGameDrawer.drawImage(ResourceManager.getImage(this.currentDirection.headImageCode()), this.location);
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentDirection(Direction currentDirection) {
        this.currentDirection = currentDirection;
    }
}
