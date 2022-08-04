package de.unikl.seda.snake.gui.snake.gameobject.enums;

import de.unikl.seda.snake.gui.snake.SnakeGameState;

import static de.unikl.seda.snake.gui.tools.RessourcesManager.*;

public enum Direction {
    LEFT {
        @Override
        public boolean goUp(SnakeGameState snakeGameState) {
            snakeGameState.getSnakeHead().setCurrentDirection(UP);
            return true;
        }

        @Override
        public boolean goDown(SnakeGameState snakeGameState) {
            snakeGameState.getSnakeHead().setCurrentDirection(DOWN);
            return true;
        }

        @Override
        public boolean goLeft(SnakeGameState snakeGameState) {
            return false;
        }

        @Override
        public boolean goRight(SnakeGameState snakeGameState) {
            return false;
        }

        @Override
        public int imageCode() {
            return SNAKE_HEAD_LEFT;
        }
    },

    RIGHT {
        @Override
        public boolean goUp(SnakeGameState snakeGameState) {
            snakeGameState.getSnakeHead().setCurrentDirection(UP);
            return true;
        }

        @Override
        public boolean goDown(SnakeGameState snakeGameState) {
            snakeGameState.getSnakeHead().setCurrentDirection(DOWN);
            return true;
        }

        @Override
        public boolean goLeft(SnakeGameState snakeGameState) {
            return false;
        }

        @Override
        public boolean goRight(SnakeGameState snakeGameState) {
            return false;
        }

        @Override
        public int imageCode() {
            return SNAKE_HEAD_RIGHT;
        }
    },

    UP {
        @Override
        public boolean goUp(SnakeGameState snakeGameState) {
            return false;
        }

        @Override
        public boolean goDown(SnakeGameState snakeGameState) {
            return false;
        }

        @Override
        public boolean goLeft(SnakeGameState snakeGameState) {
            snakeGameState.getSnakeHead().setCurrentDirection(LEFT);
            return true;
        }

        @Override
        public boolean goRight(SnakeGameState snakeGameState) {
            snakeGameState.getSnakeHead().setCurrentDirection(RIGHT);
            return true;
        }

        @Override
        public int imageCode() {
            return SNAKE_HEAD_UP;
        }
    },

    DOWN {
        @Override
        public boolean goUp(SnakeGameState snakeGameState) {
            return false;
        }

        @Override
        public boolean goDown(SnakeGameState snakeGameState) {
            return false;
        }

        @Override
        public boolean goLeft(SnakeGameState snakeGameState) {
            snakeGameState.getSnakeHead().setCurrentDirection(LEFT);
            return true;
        }

        @Override
        public boolean goRight(SnakeGameState snakeGameState) {
            snakeGameState.getSnakeHead().setCurrentDirection(RIGHT);
            return true;
        }

        @Override
        public int imageCode() {
            return SNAKE_HEAD_DOWN;
        }
    },

    IDLE {
        @Override
        public boolean goUp(SnakeGameState snakeGameState) {
            snakeGameState.getSnakeHead().setCurrentDirection(UP);
            return true;
        }

        @Override
        public boolean goDown(SnakeGameState snakeGameState) {
            snakeGameState.getSnakeHead().setCurrentDirection(DOWN);
            return true;
        }

        @Override
        // The snake facing right in the idle state
        public boolean goLeft(SnakeGameState snakeGameState) {
            return true;
        }

        @Override
        public boolean goRight(SnakeGameState snakeGameState) {
            snakeGameState.getSnakeHead().setCurrentDirection(RIGHT);
            return true;
        }

        @Override
        public int imageCode() {
            return SNAKE_HEAD_RIGHT;
        }
    };

    public abstract boolean goUp(SnakeGameState snakeGameState);
    public abstract boolean goDown(SnakeGameState snakeGameState);
    public abstract boolean goLeft(SnakeGameState snakeGameState);
    public abstract boolean goRight(SnakeGameState snakeGameState);

    public abstract int imageCode();
}
