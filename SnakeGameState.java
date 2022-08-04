package de.unikl.seda.snake.gui.snake;

import de.unikl.seda.snake.gui.snake.enums.MainState;
import de.unikl.seda.snake.gui.snake.enums.State;
import de.unikl.seda.snake.gui.menu.GameMenu;
import de.unikl.seda.snake.gui.menu.GameOverMenuItem;
import de.unikl.seda.snake.gui.snake.gameobject.*;
import de.unikl.seda.snake.gui.snake.gameobject.interfaces.GameObject;
import de.unikl.seda.snake.gui.snake.gameobject.interfaces.Hittable;
import de.unikl.seda.snake.gui.snake.gameobject.interfaces.Updatable;
import de.unikl.seda.snake.gui.tools.RessourcesManager;
import de.unikl.seda.snake.gui.tools.SnakeGameSettings;

import javax.sound.sampled.Clip;
import java.util.*;

import static de.unikl.seda.snake.gui.snake.enums.State.ALIVE;
import static de.unikl.seda.snake.gui.snake.enums.State.DEAD;
import static de.unikl.seda.snake.gui.snake.gameobject.enums.Direction.IDLE;

public class SnakeGameState {
    // Game State
    private State state;
    private SnakeGameSettings gameSettings;
    private int score;

    // In-game Objects
    private Set<GameObject> objectSet;
    private Set<Updatable> updatableSet;
    private Map<Point, Hittable> hittableMap;
    private Queue<Runnable> updateQueue;
    private boolean updating = false;
    // Snake
    private SnakeHead snakeHead;
    private ArrayList<SnakeBody> snakeBody;

    private Clip activeClip;

    public SnakeGameState(SnakeGameSettings gameSettings) {
        RessourcesManager.playSound(RessourcesManager.BACKGROUND);
        this.state = ALIVE;
        this.gameSettings = gameSettings;

        System.out.println("Setting up the game");
        this.score = 0;
        this.objectSet = new HashSet<>();
        this.updatableSet = new TreeSet<>();
        this.hittableMap = new HashMap<>();
        this.updateQueue = new LinkedList<>();
        buildWall();
        createSnake();
        addObject(new Food(generateRandomPoint()));
    }

    private void buildWall() {
        gameSettings.getGameLevel()
                .buildWall(gameSettings.getxBound(), gameSettings.getyBound())
                .forEach(this::addObject);
    }

    private void createSnake() {
        this.snakeHead = new SnakeHead(new Point(2,1));
        SnakeBody firstSnakeBody = new SnakeBody(new Point(1,1));
        addObject(snakeHead);
        snakeBody = new ArrayList<>();
        this.snakeBody.add(firstSnakeBody);
        addObject(firstSnakeBody);
    }

    public SnakeGameSettings getGameSettings() {
        return gameSettings;
    }

    public void setState(State state) {
        this.state = state;
        if (state == DEAD) {
            if (gameSettings.isSoundEnabled()) {
                RessourcesManager.playSound(RessourcesManager.GAME_OVER);
            }
            activeClip.stop();
            //TODO uberprufe ob es eine neue HighScore gibt, falls ja teile User mit und neue HIghScore speichern
            //TODO Nguyen
            this.gameSettings.getSnakeGameEnvironment().setGameMenu(new GameMenu(null, Arrays.asList(new GameOverMenuItem()),"game over"));
            this.gameSettings.getSnakeGameEnvironment().setMainState(MainState.IN_MENU);
        }
    }

    public SnakeHead getSnakeHead() {
        return snakeHead;
    }

    public int getScore() {
        return score;
    }

    public void increaseScore() {
        this.score += 1;
    }

    public Set<GameObject> getObjectSet() {
        return new HashSet<>(this.objectSet);
    }

    public void update() {
        // game over
        if (this.state == DEAD) {
            return;
        }
        updating = true;
        this.updatableSet.forEach(updatable -> updatable.update(this));
        while (!updateQueue.isEmpty()) updateQueue.poll().run();
        Hittable hittable = hittableMap.get(snakeHead.getLocation());
        if (!(hittable instanceof Food) && snakeHead.getCurrentDirection() != IDLE) {
            removeObject(snakeBody.remove(0));
        }
        if (hittable instanceof Food) {
            if (gameSettings.isSoundEnabled()) {
                RessourcesManager.playSound(RessourcesManager.FOOD_EATEN);
            }
        }
        if (hittable != null) { hittable.whenHitting(this); }
        while (!updateQueue.isEmpty()) updateQueue.poll().run();
        updating = false;
    }

    public Point generateRandomPoint() {
        // generate a random Point
        Random rand = new Random();

        boolean overlap = false;
        int x;
        int y;

        Point tempPoint;
        do {
            //Generate a x-coordinate between [0, getWidth()], the value must divisible by pixel
            x = rand.nextInt(gameSettings.getxBound());
            //Generate a y-coordinate between [GAME_INFO_BANNER_HEIGHT, getHeight() - pixel]
            y = rand.nextInt(gameSettings.getyBound());
            tempPoint = new Point(x, y);
            //Make sure the generated coordinate not overlap with the snake or wall if so spawn the food and reset the counter
            for (GameObject g : objectSet) {
                if (g.getLocation().equals(tempPoint)) {
                    overlap = true;
                    break;
                }
            }
        } while (overlap);
        // Update the sets
        System.out.println("Generate point at " + tempPoint.getX() + " " + tempPoint.getY());
        return tempPoint;

    }

    public void addObject(GameObject object) {
        if (updating) {
            this.updateQueue.add(()-> {
                this.objectSet.add(object);
                if (object instanceof Updatable) {
                    //System.out.println("Added Updatable");
                    updatableSet.add((Updatable)object);
                }
                if (object instanceof Hittable) {
                    //System.out.println("Added Hittable");
                    hittableMap.put(object.getLocation(), (Hittable)object);
                }
            });
        } else {
            this.objectSet.add(object);
            if (object instanceof Updatable) {

                updatableSet.add((Updatable)object);
            }
            if (object instanceof Hittable) {
                hittableMap.put(object.getLocation(), (Hittable)object);
            }
        }
    }

    public void removeObject(GameObject object) {
        if (updating) {
            this.updateQueue.add(()-> {
                this.objectSet.remove(object);
                if (object instanceof Updatable) {
                    updatableSet.remove(object);
                }
                if (object instanceof Hittable) {
                    hittableMap.remove(object.getLocation());
                }
            });
        } else {
            this.objectSet.remove(object);
            if (object instanceof Updatable) {
                updatableSet.remove(object);
            }
            if (object instanceof Hittable) {
                hittableMap.remove(object.getLocation());
            }
        }
    }

    public ArrayList<SnakeBody> getSnakeBody() {
        return snakeBody;
    }

    public Clip getActiveClip() {
        return activeClip;
    }
}
