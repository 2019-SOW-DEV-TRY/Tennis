package com.personal.kata;

import com.personal.kata.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TennisGameTest {

    private static final String ALL = "-All";
    private static final String DEUCE_GAME_SCORE = "Deuce";
    private TennisGame tennisGame;
    ByteArrayOutputStream outputStream;
    PrintStream printStream;

    @BeforeEach
    public void newGameSetup() {
        tennisGame = new TennisGame();
        outputStream = new ByteArrayOutputStream();
        printStream = new PrintStream(outputStream);
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
    @CsvSource({"1,0,Fifteen-Love", "2,0,Thirty-Love", "0,1,Love-Fifteen", "0,2,Love-Thirty", "1,2,Fifteen-Thirty", "2,1,Thirty-Fifteen", "3,0,Forty-Love", "3,1,Forty-Fifteen", "3,2,Forty-Thirty", "1,3,Fifteen-Forty", "2,3,Thirty-Forty"})
    @DisplayName("Given a tennis game started When Player 1 and Player 2 score different points Then the game score contains the score of Player 1 followed by score of Player 2")
    public void test_GameInProgress_Player1AndPlayer2_ScoreSame_ShouldHaveGameScoreAll(int player1Score, int player2Score, String scoreCall) {
        scoreWinsByPlayer(tennisGame.getPlayer1(), player1Score);
        scoreWinsByPlayer(tennisGame.getPlayer2(), player2Score);

        assertEquals(scoreCall, tennisGame.getGameScore());
    }

    @Test
    @DisplayName("Given a tennis game started When Player 1 and Player 2 score 3 points each Then the game score is Deuce")
    public void test_GameInProgress_Player1AndPlayer2_ScoreThreePointsEach_ShouldHaveGameScoreDeuce() {

        scoreWinsByPlayer(tennisGame.getPlayer1(), 3);
        scoreWinsByPlayer(tennisGame.getPlayer2(), 3);

        assertEquals(DEUCE_GAME_SCORE, tennisGame.getGameScore());
    }

    @Test
    @DisplayName("Given a tennis game started When Player 1 and Player 2 score atleast 3 points and each score same points Then the game score is Deuce")
    public void test_GameInProgress_Player1AndPlayer2_ScoreAtLeast3Points_AndScoreSamePoints_ShouldHaveGameScoreDeuce() {
        scoreWinsByPlayer(tennisGame.getPlayer1(), 5);
        scoreWinsByPlayer(tennisGame.getPlayer2(), 5);

        assertEquals(DEUCE_GAME_SCORE, tennisGame.getGameScore());
    }

    @ParameterizedTest
    @CsvSource({"6,5,Player 1", "5,6,Player 2"})
    @DisplayName("Given a tennis game started When Player 1 and Player 2 score atleast 3 points and any Player is ahead by 1 point Then the game score is Advantage Player")
    public void test_GameInProgress_Player1AndPlayer2_ScoreAtLeast3Points_AndPlayerAheadBy1point_ShouldHaveGameScoreAdvantagePlayer(int player1Score, int player2Score, String playerName) {

        scoreWinsByPlayer(tennisGame.getPlayer1(), player1Score);
        scoreWinsByPlayer(tennisGame.getPlayer2(), player2Score);

        assertEquals("Advantage " + playerName, tennisGame.getGameScore());
    }

    @ParameterizedTest
    @CsvSource({"4,2,Player 1", "3,5,Player 2", "4,6,Player 2", "6,4,Player 1", "0,4,Player 2", "1,4,Player 2", "4,1,Player 1", "12,10,Player 1"})
    @DisplayName("Given a tennis game started When Any Player has scored atleast 4 points and is ahead by two points Then the game score is Player Wins")
    public void test_GameInProgress_Player1ScoresAtleast4Points_AndPlayer1AheadByTwoPoints_ShouldHaveGameScorePlayer1Wins(int player1Score, int player2Score, String playerName) {

        scoreWinsByPlayer(tennisGame.getPlayer1(), player1Score);
        scoreWinsByPlayer(tennisGame.getPlayer2(), player2Score);

        assertEquals(playerName + " Wins", tennisGame.getGameScore());
    }

    @Test
    @DisplayName("Given tennis application is available When the tennis application is launched Then a welcome message is displayed")
    public void test_TennisApplicationIsLaunched_ShouldDisplayWelcomeMessage() {
        inputLinesToConsole();

        tennisGame.launchTennisGame(printStream);

        assertConsoleLines("Welcome! Lets Play Tennis", 0);
    }

    @Test
    @DisplayName("Given tennis application is available When the tennis application is launched Then after the welcome message it prompts to enter first player name ")
    public void test_TennisApplicationIsLaunched_AfterWelcomeMessage_ShouldPromptForFirstPlayerName() {
        inputLinesToConsole();

        tennisGame.launchTennisGame(printStream);

        assertConsoleLines("Please enter Player One name: ", 1);
    }

    @Test
    @DisplayName("Given tennis application is launched When the prompt to enter Player one name is displayed and entered Then the entered player name is set as Player 1 name")
    public void test_TennisApplicationLaunched_AfterPlayer1NamePrompt_ShouldAssignEntryToFirstPlayerName() {
        inputLinesToConsole();

        tennisGame = tennisGame.launchTennisGame(printStream);

        assertEquals("Rob", tennisGame.getPlayer1().getPlayerName());
    }

    @Test
    @DisplayName("Given tennis application is launched When the player 1 name is entered Then it prompts to enter second player name ")
    public void test_TennisApplicationIsLaunched_AfterPlayer1NameEntered_ShouldPromptForSecondPlayerName() {
        inputLinesToConsole();

        tennisGame.launchTennisGame(printStream);

        assertConsoleLines("Please enter Player Two name: ", 2);
    }

    @Test
    @DisplayName("Given tennis application is launched When the prompt to enter Player two name is displayed and entered Then the entered player name is set as Player 2 name")
    public void test_TennisApplicationLaunched_AfterPlayer2NamePrompt_ShouldAssignEntryToSecondPlayerName() {
        inputLinesToConsole();

        tennisGame = tennisGame.launchTennisGame(printStream);

        assertEquals("Bob", tennisGame.getPlayer2().getPlayerName());
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

    private void assertConsoleLines(String content, int lineNumber) {
        String console = new String(outputStream.toByteArray());
        String[] consoleLines = console.split(System.getProperty("line.separator"));
        assertEquals(content, consoleLines[lineNumber]);
    }

    private void inputLinesToConsole() {
        String newLine = System.getProperty("line.separator");
        String consoleInput = "Rob" + newLine + "Bob";
        System.setIn(new ByteArrayInputStream(consoleInput.getBytes()));
    }

}
