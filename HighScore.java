package de.unikl.seda.snake.highscore;

import java.io.Serializable;
import java.util.Date;

public class HighScore implements Serializable {
    private String playerName;
    private int achievedPoints;
    private Date date;
    private int speed;
    private String level;

    public HighScore(String playerName, int achievedPoints, Date date, int speed, String level) {
        this.playerName = playerName;
        this.achievedPoints = achievedPoints;
        this.date = date;
        this.speed = speed;
        this.level = level;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getAchievedPoints() {
        return achievedPoints;
    }

    public void setAchievedPoints(int achievedPoints) {
        this.achievedPoints = achievedPoints;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Player Name: " + playerName +
                "\nPoints: " + achievedPoints +
                "\nDate: " + date +
                "\nSpeed: " + speed +
                "\nLevel: " + level + "\n";
    }
}
