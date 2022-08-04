package de.unikl.seda.snake.gui.tools;

import de.unikl.seda.snake.gui.snake.SnakeGameEnvironment;

/**
 * The {@link UiUpdateThread update thread} will continuously cause the {@link GameEnvironment} to be redrawn.
 */
public class UiUpdateThread extends Thread {
    private final GameEnvironment drawingEnv;

    public UiUpdateThread(GameEnvironment drawingEnvironment) {
        drawingEnv = drawingEnvironment;

        // Terminate thread once the application's main thread has terminated. Otherwise the java application would keep running after the dialog/window has been closed.
        setDaemon(true);
    }

    @Override
    public void run() {
        SnakeGameEnvironment snakeGameEnvironment = (SnakeGameEnvironment) drawingEnv;
        while(drawingEnv.isVisible()) {
            snakeGameEnvironment.updateState();
            drawingEnv.repaint();

            try {
                snakeGameEnvironment.makeThreadSleep();
            } catch (InterruptedException ignored) {}
        }
    }
}
