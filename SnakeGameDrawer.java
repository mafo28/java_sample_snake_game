package de.unikl.seda.snake.gui.tools;

import de.unikl.seda.snake.gui.menu.interfaces.Adjustable;
import de.unikl.seda.snake.gui.menu.interfaces.MenuItem;
import de.unikl.seda.snake.gui.snake.SnakeGameEnvironment;
import de.unikl.seda.snake.settings.SnakeGameSettings;
import de.unikl.seda.snake.gui.snake.gameobject.Point;

import java.awt.*;
import java.util.List;

import static de.unikl.seda.snake.gui.snake.SnakeGameEnvironment.GAME_INFO_BANNER_HEIGHT;
import static de.unikl.seda.snake.gui.snake.SnakeGameEnvironment.INFO_HEIGHT;

public class SnakeGameDrawer {
    private final static int FONT_SIZE = 20;

    private final SnakeGameSettings snakeGameSettings;
    private final Graphics2D graphics;

    public SnakeGameDrawer(SnakeGameSettings snakeGameSettings, Graphics2D graphics) {
        this.snakeGameSettings = snakeGameSettings;
        this.graphics = graphics;
    }

    public void drawInGameBanner(SnakeGameEnvironment snakeGameEnvironment) {
        // draw Info Banner
        graphics.setColor(new Color(125, 167, 116));
        graphics.fillRect(0,0,snakeGameSettings.getWidth(), GAME_INFO_BANNER_HEIGHT);
        graphics.setColor(Color.BLACK);
        graphics.drawString(snakeGameSettings.getPlayerName(), 10, INFO_HEIGHT);
        if (snakeGameEnvironment.getGameObjectManager() != null) {
            graphics.drawString(
                    "Life: "+ snakeGameEnvironment.getGameObjectManager().getLife() +
                    " | Score: " + snakeGameEnvironment.getGameObjectManager().getScore(),
                    snakeGameSettings.getWidth() - 160, INFO_HEIGHT);
        }
    }

    public void drawGrid() {
        int y = GAME_INFO_BANNER_HEIGHT;
        while (y <= snakeGameSettings.getHeight()) {
            int x = 0;
            while (x <= snakeGameSettings.getWidth()) {
                graphics.drawRect(x, y, snakeGameSettings.getSquareSize(), snakeGameSettings.getSquareSize());
                x += snakeGameSettings.getSquareSize();
            }
            y += snakeGameSettings.getSquareSize();
        }
    }

    public void drawRect(Point location, Color color) {
        graphics.setColor(color);
        graphics.fillRect(location.getX() * snakeGameSettings.getSquareSize(),
                location.getY() * snakeGameSettings.getSquareSize() + GAME_INFO_BANNER_HEIGHT,
                snakeGameSettings.getSquareSize(),
                snakeGameSettings.getSquareSize());
    }

    public void drawImage(Image image, Point location) {
        graphics.drawImage(image,
                location.getX() * snakeGameSettings.getSquareSize(),
                location.getY() * snakeGameSettings.getSquareSize() + GAME_INFO_BANNER_HEIGHT,
                snakeGameSettings.getSquareSize(),
                snakeGameSettings.getSquareSize(),
                null
        );
    }

    public void drawMenu(List<MenuItem> childItem, int position) {
        final int cellHeight = FONT_SIZE + 10;
        FontMetrics fontMetrics = graphics.getFontMetrics();

        //highlight current position
        graphics.setColor(Color.lightGray);
        graphics.fillRect(0, GAME_INFO_BANNER_HEIGHT + cellHeight*position, snakeGameSettings.getWidth(), cellHeight);

        //draw cell
        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font(Font.SANS_SERIF, Font.BOLD, FONT_SIZE));
        int yText = GAME_INFO_BANNER_HEIGHT - 6;
        for (MenuItem item : childItem) {
            int xText = 10;
            yText += cellHeight;
            graphics.drawString(item.getName(), xText, yText);
            if (item instanceof Adjustable) {
                String text = "- " + ((Adjustable)item).getValue() + " +";
                xText = snakeGameSettings.getWidth() - fontMetrics.stringWidth(text) * 2;
                graphics.drawString(text, xText, yText);
            }
        }
    }
}
