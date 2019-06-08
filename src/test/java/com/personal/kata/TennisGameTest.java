package com.personal.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TennisGameTest {

    private TennisGame tennisGame;

    @BeforeEach
    public void newGameSetup() {
        tennisGame = new TennisGame();
    }

    @Test
    @DisplayName("Given a tennis game When tennis game starts Then there should be two players with scores at zero each")
    public void test_NewGameState_ShouldHaveTwoPlayerScores_ScoresAtZero() {
        assertEquals(0, tennisGame.getPlayer1().getPlayerScore());
        assertEquals(0, tennisGame.getPlayer2().getPlayerScore());
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

        tennisGame.getPlayer1().scorePoint();

        assertEquals(1, tennisGame.getPlayer1().getPlayerScore());
        assertEquals(0, tennisGame.getPlayer2().getPlayerScore());
    }

}
