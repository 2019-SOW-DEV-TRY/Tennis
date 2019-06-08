package com.personal.kata;

import com.personal.kata.model.Player;

import java.util.Optional;
import java.util.stream.Stream;

public class TennisGame {

    private static final String HYPHEN = "-";
    private static final String SAME_GAME_SCORE = "All";
    private static final String BLANK = "";
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

    private String getScoreCall(int score) {
        Optional<Score> scoreCallOfScore = Stream.of(Score.values()).filter(scoreValue -> scoreValue.score == score).findFirst();
        return scoreCallOfScore.isPresent() ? scoreCallOfScore.get().scoreCall : BLANK;
    }

    private String calculateGameScore() {
        if (isPlayerScoresEqual()) {
            gameScore = getScoreCall(player1.getPlayerScore()) + HYPHEN + SAME_GAME_SCORE;
        } else {
            gameScore = getScoreCall(player1.getPlayerScore()) + HYPHEN + getScoreCall(player2.getPlayerScore());
        }
        return gameScore;
    }

    private boolean isPlayerScoresEqual() {
        return player1.getPlayerScore() == player2.getPlayerScore();
    }
}
