package com.personal.kata;

import com.personal.kata.model.Player;

import java.io.PrintStream;
import java.util.Scanner;
import java.util.stream.Stream;

public class TennisGame {

    private static final String HYPHEN = "-";
    private static final String SAME_GAME_SCORE = "All";
    private static final String BLANK = "";
    private static final String DEUCE_GAME_SCORE = "Deuce";
    private static final int MINIMUM_POINT_DIFFERENCE = 1;
    private static final String WELCOME_MESSAGE = "Welcome! Lets Play Tennis";
    private static final String PROMPT_FOR_PLAYER1_NAME = "Please enter Player One name: ";
    private static final String PROMPT_FOR_PLAYER2_NAME = "Please enter Player Two name: ";
    private static final String GAME_STARTS_NOW_MESSAGE = "Game Starts Now!!";
    private static final String PLAYER_1_INDICATOR = "1";
    private static final String PLAYER_2_INDICATOR = "2";
    private static final String GAME_CANCEL_INDICATOR = "C";
    private static final String PLAYING_INSTRUCTIONS_PART1 = "Please enter who won this Ball, Press [" + PLAYER_1_INDICATOR + "]: ";
    private static final String PLAYING_INSTRUCTIONS_PART2 = " / [" + PLAYER_2_INDICATOR + "]: ";
    private static final String PLAYING_INSTRUCTIONS_PART3 = " Or Press [" + GAME_CANCEL_INDICATOR + "] to stop playing";
    private static final String INVALID_INPUT_MESSAGE = "Please enter a valid Input !!";
    private static final String GAME_OVER_MESSAGE = "Game Over !!";
    private static final String OR_REGEX = "|";

    private Player player1;
    private Player player2;
    private String gameScore;

    public TennisGame() {
        player1 = new Player("Player 1");
        player2 = new Player("Player 2");
        gameScore = Score.LOVE.scoreCall + HYPHEN + SAME_GAME_SCORE;
    }

    private TennisGame(String player1Name, String player2Name) {
        player1 = new Player(player1Name);
        player2 = new Player(player2Name);
        gameScore = Score.LOVE.scoreCall + HYPHEN + SAME_GAME_SCORE;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public String getGameScore() {
        calculateGameScore();
        return gameScore;
    }

    public static TennisGame launchTennisGame(PrintStream out) {
        out.println(WELCOME_MESSAGE);
        out.println(PROMPT_FOR_PLAYER1_NAME);
        Scanner inputFromConsole = new Scanner(System.in);
        String player1Name = inputFromConsole.nextLine();
        out.println(PROMPT_FOR_PLAYER2_NAME);
        String player2Name = inputFromConsole.nextLine();
        out.println(GAME_STARTS_NOW_MESSAGE);
        TennisGame tennisAppGame = new TennisGame(player1Name, player2Name);

        do {
            out.println(PLAYING_INSTRUCTIONS_PART1 + player1Name + PLAYING_INSTRUCTIONS_PART2 + player2Name + PLAYING_INSTRUCTIONS_PART3);
            String input = inputFromConsole.nextLine();
            if (input.matches("^(([" + PLAYER_1_INDICATOR + OR_REGEX + PLAYER_2_INDICATOR + "])" + OR_REGEX + "([" + GAME_CANCEL_INDICATOR.toLowerCase() + OR_REGEX + GAME_CANCEL_INDICATOR.toUpperCase() + "]))$")) {
                if (input.equals(PLAYER_1_INDICATOR)) {
                    tennisAppGame.getPlayer1().scorePoint();
                } else if (input.equals(PLAYER_2_INDICATOR)) {
                    tennisAppGame.getPlayer2().scorePoint();
                } else {
                    break;
                }
                out.println(tennisAppGame.getGameScore());
            } else {
                out.println(INVALID_INPUT_MESSAGE);
            }
        } while (!tennisAppGame.getGameScore().contains("Wins"));
        out.println(GAME_OVER_MESSAGE);
        return tennisAppGame;
    }

    private String getScoreCall(int score) {
        return Stream.of(Score.values()).filter(scoreValue -> scoreValue.scoreVal == score).findFirst().map(score1 -> score1.scoreCall).orElse(BLANK);
    }

    private boolean isDeuce() {
        return isPlayerScoresEqual() && player1.getPlayerScore() >= Score.FORTY.scoreVal;
    }

    private void calculateGameScore() {
        if (isPlayerScoresEqual()) {
            if (isDeuce()) {
                gameScore = DEUCE_GAME_SCORE;
            } else {
                gameScore = getScoreCall(player1.getPlayerScore()) + HYPHEN + SAME_GAME_SCORE;
            }
        } else if (isAnyPlayerCanWin()) {
            if (isAdvantage()) {
                gameScore = "Advantage " + getTopPlayer().getPlayerName();
            } else {
                gameScore = getTopPlayer().getPlayerName() + " Wins";
            }

        } else {
            gameScore = getScoreCall(player1.getPlayerScore()) + HYPHEN + getScoreCall(player2.getPlayerScore());
        }
    }

    private boolean isPlayerScoresEqual() {
        return player1.getPlayerScore() == player2.getPlayerScore();
    }

    private boolean isAnyPlayerCanWin() {
        return player1.getPlayerScore() > Score.FORTY.scoreVal || player2.getPlayerScore() > Score.FORTY.scoreVal;
    }

    private Player getTopPlayer() {
        return player1.getPlayerScore() > player2.getPlayerScore() ? player1 : player2;
    }

    private boolean isAdvantage() {
        return isAnyPlayerCanWin() && Math.abs(player1.getPlayerScore() - player2.getPlayerScore()) == MINIMUM_POINT_DIFFERENCE;
    }


    private enum Score {
        LOVE(0, "Love"),
        FIFTEEN(1, "Fifteen"),
        THIRTY(2, "Thirty"),
        FORTY(3, "Forty");

        private final int scoreVal;
        private final String scoreCall;

        Score(int scoreVal, String scoreCall) {
            this.scoreVal = scoreVal;
            this.scoreCall = scoreCall;
        }
    }
}
