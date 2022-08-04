package de.unikl.seda.snake.gui.tools;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import java.awt.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ResourceManager {

    public static final String sound_prefix = "/de/unikl/seda/snake/gui/ressources/sounds/";
    public static final String image_prefix = "/de/unikl/seda/snake/gui/ressources/images/";

    // sound indices
    public static final int FOOD_EATEN = 0;
    public static final int GAME_OVER = 1;
    public static final int BACKGROUND = 2;

    // sound paths
    public static final String FOOD_EATEN_ITEM = sound_prefix + "snakehit.wav";
    public static final String GAME_OVER_ITEM = sound_prefix + "mixkitGameOver.wav";
    public static final String BACKGROUND_ITEM = sound_prefix + "KevinMacLeodMerryGoHQ.wav";

    // image indices
    public static final int SNAKE_HEAD_DOWN = 0;
    public static final int SNAKE_HEAD_RIGHT = 1;
    public static final int SNAKE_HEAD_UP = 2;
    public static final int SNAKE_HEAD_LEFT = 3;
    public static final int FOOD = 4;
    public static final int SNAKE_BODY_DOWNRIGHT = 1 + FOOD;
    public static final int SNAKE_BODY_UPRIGHT   = 2 + FOOD;
    public static final int SNAKE_BODY_STRAIGHT_HOZ = 3 + FOOD;
    public static final int SNAKE_BODY_UPLEFT    = 4 + FOOD;
    public static final int SNAKE_BODY_STRAIGHT_VER = 5 + FOOD;
    public static final int SNAKE_BODY_DOWNLEFT  = 6 + FOOD;
    public static final int SNAKE_TAIL_UP = 1 + SNAKE_BODY_DOWNLEFT;
    public static final int SNAKE_TAIL_LEFT = 2 + SNAKE_BODY_DOWNLEFT;
    public static final int SNAKE_TAIL_RIGHT = 3 + SNAKE_BODY_DOWNLEFT;
    public static final int SNAKE_TAIL_DOWN = 4 + SNAKE_BODY_DOWNLEFT;
    public static final int POOP    = 1 + SNAKE_TAIL_DOWN;

    // image paths
    public static final String SNAKE_HEAD_DOWN_ITEM = image_prefix + "snakeHeadD.png";
    public static final String SNAKE_HEAD_RIGHT_ITEM = image_prefix + "snakeHeadR.png";
    public static final String SNAKE_HEAD_UP_ITEM = image_prefix + "snakeHeadU.png";
    public static final String SNAKE_HEAD_LEFT_ITEM = image_prefix + "snakeHeadL.png";
    public static final String FOOD_ITEM = image_prefix + "food.png";
    public static final String SNAKE_BODY_ITEM1 = image_prefix + "snakebody1.png";
    public static final String SNAKE_BODY_ITEM2 = image_prefix + "snakebody2.png";
    public static final String SNAKE_BODY_ITEM3 = image_prefix + "snakebody3.png";
    public static final String SNAKE_BODY_ITEM4 = image_prefix + "snakebody4.png";
    public static final String SNAKE_BODY_ITEM5 = image_prefix + "snakebody5.png";
    public static final String SNAKE_BODY_ITEM6 = image_prefix + "snakebody6.png";

    public static final String SNAKE_TAIL_ITEM1 = image_prefix + "snaketail1.png";
    public static final String SNAKE_TAIL_ITEM2 = image_prefix + "snaketail2.png";
    public static final String SNAKE_TAIL_ITEM3 = image_prefix + "snaketail3.png";
    public static final String SNAKE_TAIL_ITEM4 = image_prefix + "snaketail4.png";
    public static final String POOP_ITEM = image_prefix + "poop.png";


    private static Map<Integer, AudioInputStream> soundMap;
    private static Map<Integer, Image> imageMap;
    private static Clip backgroundSound;

    private static boolean isSoundEnable;

    static {
        soundMap =  new HashMap<>();
        imageMap =  new HashMap<>();

        try {
            //import sounds
            soundMap.put(FOOD_EATEN, createReusableAudioInputStream(FOOD_EATEN_ITEM));
            soundMap.put(GAME_OVER, createReusableAudioInputStream(GAME_OVER_ITEM));
            soundMap.put(BACKGROUND, createReusableAudioInputStream(BACKGROUND_ITEM));

            //import images
            imageMap.put(SNAKE_HEAD_DOWN, ImageIO.read(ResourceManager.class.getResourceAsStream(SNAKE_HEAD_DOWN_ITEM)));
            imageMap.put(SNAKE_HEAD_RIGHT, ImageIO.read(ResourceManager.class.getResourceAsStream(SNAKE_HEAD_RIGHT_ITEM)));
            imageMap.put(SNAKE_HEAD_UP, ImageIO.read(ResourceManager.class.getResourceAsStream(SNAKE_HEAD_UP_ITEM)));
            imageMap.put(SNAKE_HEAD_LEFT, ImageIO.read(ResourceManager.class.getResourceAsStream(SNAKE_HEAD_LEFT_ITEM)));
            imageMap.put(FOOD, ImageIO.read(ResourceManager.class.getResourceAsStream(FOOD_ITEM)));
            imageMap.put(SNAKE_BODY_DOWNRIGHT, ImageIO.read(ResourceManager.class.getResourceAsStream(SNAKE_BODY_ITEM1)));
            imageMap.put(SNAKE_BODY_UPRIGHT, ImageIO.read(ResourceManager.class.getResourceAsStream(SNAKE_BODY_ITEM2)));
            imageMap.put(SNAKE_BODY_STRAIGHT_HOZ, ImageIO.read(ResourceManager.class.getResourceAsStream(SNAKE_BODY_ITEM3)));
            imageMap.put(SNAKE_BODY_UPLEFT, ImageIO.read(ResourceManager.class.getResourceAsStream(SNAKE_BODY_ITEM4)));
            imageMap.put(SNAKE_BODY_STRAIGHT_VER, ImageIO.read(ResourceManager.class.getResourceAsStream(SNAKE_BODY_ITEM5)));
            imageMap.put(SNAKE_BODY_DOWNLEFT, ImageIO.read(ResourceManager.class.getResourceAsStream(SNAKE_BODY_ITEM6)));
            imageMap.put(SNAKE_TAIL_UP, ImageIO.read(ResourceManager.class.getResourceAsStream(SNAKE_TAIL_ITEM1)));
            imageMap.put(SNAKE_TAIL_LEFT, ImageIO.read(ResourceManager.class.getResourceAsStream(SNAKE_TAIL_ITEM2)));
            imageMap.put(SNAKE_TAIL_RIGHT, ImageIO.read(ResourceManager.class.getResourceAsStream(SNAKE_TAIL_ITEM3)));
            imageMap.put(SNAKE_TAIL_DOWN, ImageIO.read(ResourceManager.class.getResourceAsStream(SNAKE_TAIL_ITEM4)));
            imageMap.put(POOP, ImageIO.read(ResourceManager.class.getResourceAsStream(POOP_ITEM)));
        } catch (UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
    }

    private static AudioInputStream createReusableAudioInputStream(String path)
            throws IOException, UnsupportedAudioFileException
    {
        try (InputStream inputStream = new BufferedInputStream(ResourceManager.class.getResourceAsStream(path));
             AudioInputStream ais = AudioSystem.getAudioInputStream(inputStream)) {
            byte[] buffer = new byte[1024 * 32];
            int read = 0;
            ByteArrayOutputStream baos = new ByteArrayOutputStream(buffer.length);
            while ((read = ais.read(buffer, 0, buffer.length)) != -1) {
                baos.write(buffer, 0, read);
            }
            return new AudioInputStream(
                            new ByteArrayInputStream(baos.toByteArray()),
                            ais.getFormat(),
                            AudioSystem.NOT_SPECIFIED);
        }
    }

    public static void playSound(int sound) {
        if (!isSoundEnable) {
            return;
        }
        try {
            AudioInputStream stream = soundMap.get(sound);
            stream.reset();
            Clip clip = AudioSystem.getClip();
            clip.open(stream);
            clip.start();
        } catch (LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void playBackgroundSound() {
        if (!isSoundEnable) {
            return;
        }
        try {
            AudioInputStream stream = ResourceManager.soundMap.get(ResourceManager.BACKGROUND);
            stream.reset();
            backgroundSound = AudioSystem.getClip();
            backgroundSound.open(stream);
            backgroundSound.loop(Clip.LOOP_CONTINUOUSLY);
            backgroundSound.start();
        } catch (LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void stopBackgroundSound() {
        if (!isSoundEnable) {
            return;
        }
        backgroundSound.stop();
    }

    public static Image getImage(int image) {
        return imageMap.get(image);
    }

    public static void setSoundEnable(boolean isSoundEnable) {
        ResourceManager.isSoundEnable = isSoundEnable;
    }
}
