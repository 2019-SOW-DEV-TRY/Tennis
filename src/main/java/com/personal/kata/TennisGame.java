package com.personal.kata;

import com.personal.kata.model.Player;

public class TennisGame {

    private Player player1;
    private Player player2;
    private String gameScore;

    public TennisGame() {
        player1 = new Player("Player 1");
        player2 = new Player("Player 2");
        gameScore = "Love-All";
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public String getGameScore() {
        return gameScore;
    }
}
