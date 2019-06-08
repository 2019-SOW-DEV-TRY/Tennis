package com.personal.kata;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TennisGameTest {

    @Test
    @DisplayName("Given a tennis game When tennis game starts Then there should be two players with scores at zero each")
    public void test_NewGameState_ShouldHaveTwoPlayerScores_ScoresAtZero() {

        TennisGame tennisGame = new TennisGame();

        assertEquals(0, tennisGame.getPlayer1().getPlayerScore());
        assertEquals(0, tennisGame.getPlayer2().getPlayerScore());
    }

    @Test
    @DisplayName("Given a tennis game When tennis game starts Then there should be two players named Player 1 and Player 2")
    public void test_NewGameState_ShouldHaveTwoPlayerNames_Player1_Player2() {

        TennisGame tennisGame = new TennisGame();

        assertEquals("Player 1", tennisGame.getPlayer1().getPlayerName());
        assertEquals("Player 2", tennisGame.getPlayer2().getPlayerName());
    }

}
