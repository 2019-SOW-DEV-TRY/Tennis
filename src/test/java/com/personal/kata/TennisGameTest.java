package com.personal.kata;

import com.personal.kata.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TennisGameTest {

    private static final String ALL = "-All";
    private TennisGame tennisGame;

    @BeforeEach
    public void newGameSetup() {
        tennisGame = new TennisGame();
    }

    @Test
    @DisplayName("Given a tennis game When tennis game starts Then there should be two players with scores at zero each")
    public void test_NewGameState_ShouldHaveTwoPlayerScores_ScoresAtZero() {

        assertPointsScoredByPlayers(0, 0);
    }

    @Test
    @DisplayName("Given a tennis game When tennis game starts Then there should be two players named Player 1 and Player 2")
    public void test_NewGameState_ShouldHaveTwoPlayerNames_Player1_Player2() {

        assertEquals("Player 1", tennisGame.getPlayer1().getPlayerName());
        assertEquals("Player 2", tennisGame.getPlayer2().getPlayerName());
    }

    @Test
    @DisplayName("Given a tennis game started When Player1 scores a point Then the score of Player1 should increase by one point")
    public void test_GameInProgress_Player1Scores_ShouldIncreaseScoreOfPlayer1() {

        scoreWinsByPlayer(tennisGame.getPlayer1(), 1);

        assertPointsScoredByPlayers(1, 0);
    }

    @Test
    @DisplayName("Given a tennis game started When Player1 scores two points Then the score of Player1 should increase by two points")
    public void test_GameInProgress_Player1ScoresTwoPoints_ShouldIncreaseScoreOfPlayer1ByTwoPoints() {

        scoreWinsByPlayer(tennisGame.getPlayer1(), 2);

        assertPointsScoredByPlayers(2, 0);
    }

    @ParameterizedTest
    @CsvSource({"0,Love", "1,Fifteen", "2,Thirty"})
    @DisplayName("Given a tennis game started When Player 1 and Player 2 scores same points Then the game score is followed by -All")
    public void test_GameInProgress_Player1AndPlayer2_ScoreSame_ShouldHaveGameScoreAll(int wins, String scoreCall) {
        scoreWinsByPlayer(tennisGame.getPlayer1(), wins);
        scoreWinsByPlayer(tennisGame.getPlayer2(), wins);

        assertEquals(scoreCall + ALL, tennisGame.getGameScore());
    }

    @ParameterizedTest
    @CsvSource({"1,0,Fifteen-Love", "2,0,Thirty-Love", "0,1,Love-Fifteen", "0,2,Love-Thirty", "1,2,Fifteen-Thirty", "2,1,Thirty-Fifteen"})
    @DisplayName("Given a tennis game started When Player 1 and Player 2 score different points Then the game score contains the score of Player 1 followed by score of Player 2")
    public void test_GameInProgress_Player1AndPlayer2_ScoreSame_ShouldHaveGameScoreAll(int player1Score, int player2Score, String scoreCall) {
        scoreWinsByPlayer(tennisGame.getPlayer1(), player1Score);
        scoreWinsByPlayer(tennisGame.getPlayer2(), player2Score);

        assertEquals(scoreCall, tennisGame.getGameScore());
    }

    private void scoreWinsByPlayer(Player player, int totalWins) {
        for (int ball = 1; ball <= totalWins; ball++) {
            player.scorePoint();
        }
    }

    private void assertPointsScoredByPlayers(int player1Score, int player2Score) {
        assertEquals(player1Score, tennisGame.getPlayer1().getPlayerScore());
        assertEquals(player2Score, tennisGame.getPlayer2().getPlayerScore());
    }

}
