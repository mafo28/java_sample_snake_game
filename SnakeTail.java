package de.unikl.seda.snake.gui.snake.gameobject;

import de.unikl.seda.snake.gui.snake.enums.Direction;
import de.unikl.seda.snake.gui.tools.GameObjectManager;
import de.unikl.seda.snake.gui.tools.ResourceManager;
import de.unikl.seda.snake.gui.tools.SnakeGameDrawer;

import static de.unikl.seda.snake.gui.snake.enums.Direction.IDLE;

public class SnakeTail extends SnakeBody {

    Point extensibleLocation;
    Direction extensibleDirection;

    public SnakeTail(Point location, SnakeHead successor) {
        super(location, successor);
        extensibleDirection = null;
        extensibleLocation = null;
        setPriority(getSnakeTailProperty());
    }

    @Override
    public void update(GameObjectManager gameObjectManager) {
        if (gameObjectManager.getSnakeHead().getCurrentDirection() == IDLE) {
            return;
        }
        extensibleLocation = new Point(getLocation());
        extensibleDirection = getCurrentDirection();
        super.update(gameObjectManager);
        if (gameObjectManager.isPoop()) {
            gameObjectManager.addObject(new Poop(extensibleLocation));
            gameObjectManager.setPoop(!gameObjectManager.isPoop());
        }
    }

    @Override
    public void draw(SnakeGameDrawer snakeGameDrawer) {
        snakeGameDrawer.drawImage(ResourceManager.getImage(successor.getCurrentDirection().tailImageCode()), this.location);

    }

    void extendBody(GameObjectManager gameObjectManager) {
        Point oldLocation = this.location;
        Direction oldDir = getCurrentDirection();
        this.location = this.extensibleLocation;
        this.setCurrentDirection(extensibleDirection);
        extensibleDirection = null;
        extensibleLocation = null;
        SnakeBody newBody = new SnakeBody(oldLocation, this.successor);
        newBody.setCurrentDirection(oldDir);
        newBody.setPriority(this.getPriority());
        setPriority(getSnakeTailProperty());
        this.successor = newBody;
        gameObjectManager.addObject(newBody);
    }
}
