package com.personal.kata;

import com.personal.kata.model.Player;

public class TennisGame {

    public static final String HYPHEN = "-";
    public static final String SAME_GAME_SCORE = "All";
    public static final String LOVE = "Love";
    public static final String FIFTEEN = "Fifteen";
    private Player player1;
    private Player player2;
    private String gameScore;

    public TennisGame() {
        player1 = new Player("Player 1");
        player2 = new Player("Player 2");
        gameScore = LOVE + HYPHEN + SAME_GAME_SCORE;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public String getGameScore() {
        return calculateGameScore();
    }

    private String calculateGameScore() {
        String player1GameScore = LOVE;
        String player2GameScore = LOVE;
        if (isPlayerScoresEqual() && player1.getPlayerScore() == 0) {
            gameScore = LOVE + HYPHEN + SAME_GAME_SCORE;
        } else if (isPlayerScoresEqual() && player1.getPlayerScore() == 1) {
            gameScore = FIFTEEN + HYPHEN + SAME_GAME_SCORE;
        } else if (player1.getPlayerScore() == 1) {
            player1GameScore = FIFTEEN;
            gameScore = player1GameScore + HYPHEN + player2GameScore;
        } else if (player2.getPlayerScore() == 1) {
            player2GameScore = FIFTEEN;
            gameScore = player1GameScore + HYPHEN + player2GameScore;
        }
        return gameScore;
    }

    private boolean isPlayerScoresEqual() {
        return player1.getPlayerScore() == player2.getPlayerScore();
    }
}
