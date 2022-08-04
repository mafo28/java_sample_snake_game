package de.unikl.seda.snake.gui.snake.gameobject.interfaces;

import de.unikl.seda.snake.gui.tools.GameObjectManager;

public interface Hittable {
    void whenHitting(GameObjectManager gameObjectManager);
}
