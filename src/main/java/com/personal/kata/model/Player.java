package com.personal.kata.model;

public class Player {
    private int playerScore;
    private final String playerName;

    public Player(String playerName) {
        this.playerName = playerName;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void scorePoint() {
        playerScore++;
    }
}
