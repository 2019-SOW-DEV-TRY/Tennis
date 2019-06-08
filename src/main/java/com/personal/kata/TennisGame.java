package com.personal.kata;

import com.personal.kata.model.Player;

public class TennisGame {

    public static final String HYPHEN = "-";
    public static final String SAME_GAME_SCORE = "All";
    private Player player1;
    private Player player2;
    private String gameScore;

    public TennisGame() {
        player1 = new Player("Player 1");
        player2 = new Player("Player 2");
        gameScore = Score.LOVE.scoreCall + HYPHEN + SAME_GAME_SCORE;
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

    private enum Score {
        LOVE(0, "Love"),
        FIFTEEN(1, "Fifteen"),
        THIRTY(2, "Thirty");

        private final int score;
        private final String scoreCall;

        Score(int score, String scoreCall) {
            this.score = score;
            this.scoreCall = scoreCall;
        }
    }

    private String calculateGameScore() {
        String player1GameScore = Score.LOVE.scoreCall;
        String player2GameScore = Score.LOVE.scoreCall;
        if (isPlayerScoresEqual() && player1.getPlayerScore() == 0) {
            gameScore = Score.LOVE.scoreCall + HYPHEN + SAME_GAME_SCORE;
        } else if (isPlayerScoresEqual() && player1.getPlayerScore() == 1) {
            gameScore = Score.FIFTEEN.scoreCall + HYPHEN + SAME_GAME_SCORE;
        } else if (player1.getPlayerScore() == 1) {
            player1GameScore = Score.FIFTEEN.scoreCall;
            gameScore = player1GameScore + HYPHEN + player2GameScore;
        } else if (player2.getPlayerScore() == 1) {
            player2GameScore = Score.FIFTEEN.scoreCall;
            gameScore = player1GameScore + HYPHEN + player2GameScore;
        } else if (player1.getPlayerScore() == 2) {
            player1GameScore = Score.THIRTY.scoreCall;
            gameScore = player1GameScore + HYPHEN + player2GameScore;
        } else if (player2.getPlayerScore() == 2) {
            player2GameScore = Score.THIRTY.scoreCall;
            gameScore = player1GameScore + HYPHEN + player2GameScore;
        }
        return gameScore;
    }

    private boolean isPlayerScoresEqual() {
        return player1.getPlayerScore() == player2.getPlayerScore();
    }
}
