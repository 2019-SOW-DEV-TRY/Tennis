package com.personal.kata;

import com.personal.kata.model.Player;

import java.io.PrintStream;
import java.util.Optional;
import java.util.stream.Stream;

public class TennisGame {

    private static final String HYPHEN = "-";
    private static final String SAME_GAME_SCORE = "All";
    private static final String BLANK = "";
    private static final String DEUCE_GAME_SCORE = "Deuce";
    private static final int MINIMUM_POINT_DIFFERENCE = 1;
    private static final String WELCOME_MESSAGE = "Welcome! Lets Play Tennis";
    private static final String PROMPT_FOR_PLAYER1_NAME = "Please enter Player One name: ";
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

    public void launchTennisGame(PrintStream out) {
        out.println(WELCOME_MESSAGE);
        out.println(PROMPT_FOR_PLAYER1_NAME);
    }

    private enum Score {
        LOVE(0, "Love"),
        FIFTEEN(1, "Fifteen"),
        THIRTY(2, "Thirty"),
        FORTY(3, "Forty");

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
        if (isDeuce()) {
            gameScore = DEUCE_GAME_SCORE;
        } else if (isPlayerScoresEqual()) {
            gameScore = getScoreCall(player1.getPlayerScore()) + HYPHEN + SAME_GAME_SCORE;
        } else if (isAdvantage()) {
            gameScore = "Advantage " + getTopPlayer().getPlayerName();
        } else if (isWin()) {
            gameScore = getTopPlayer().getPlayerName() + " Wins";
        } else {
            gameScore = getScoreCall(player1.getPlayerScore()) + HYPHEN + getScoreCall(player2.getPlayerScore());
        }
        return gameScore;
    }

    private boolean isPlayerScoresEqual() {
        return player1.getPlayerScore() == player2.getPlayerScore();
    }

    private boolean isDeuce() {
        return isPlayerScoresEqual() && player1.getPlayerScore() >= Score.FORTY.score;
    }

    private Player getTopPlayer() {
        return player1.getPlayerScore() > player2.getPlayerScore() ? player1 : player2;
    }

    private boolean isAdvantage() {
        return isAnyPlayerCanWin() && Math.abs(player1.getPlayerScore() - player2.getPlayerScore()) == MINIMUM_POINT_DIFFERENCE;
    }

    private boolean isWin() {
        return isAnyPlayerCanWin() && Math.abs(player1.getPlayerScore() - player2.getPlayerScore()) > MINIMUM_POINT_DIFFERENCE;
    }

    private boolean isAnyPlayerCanWin() {
        return player1.getPlayerScore() > Score.FORTY.score || player2.getPlayerScore() > Score.FORTY.score;
    }
}
