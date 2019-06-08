package com.personal.kata;

public class TennisGame {

    private int player1Score;
    private int player2Score;
    private String player1Name;
    private String player2Name;

    public TennisGame() {
        this.player1Name = "Player 1";
        this.player2Name = "Player 2";
    }

    public int getPlayer1Score() {
        return player1Score;
    }

    public int getPlayer2Score() {
        return player2Score;
    }

    public String getPlayer1Name() {
        return player1Name;
    }

    public String getPlayer2Name() {
        return player2Name;
    }
}
