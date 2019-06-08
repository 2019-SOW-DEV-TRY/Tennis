###TENNIS

About this Kata

This Kata is about implementing a simple tennis game. The game is between two players similar to a Singles Tennis match. In this solution, a single set is considered as one game.

The scoring system is rather simple:

1. Each player can have either of these points in one game 0 15 30 40

2. If you have 40 and you win the ball you win the game, however there are special rules.

3. If both have 40 the players are deuce. a. If the game is in deuce, the winner of a ball will have advantage and game ball. b. If the player with advantage wins the ball he wins the game c. If the player without advantage wins they are back at deuce.

Reference: http://codingdojo.org/kata/Tennis/

###Prerequisite for this application

JDK 1.8\
Maven 3.x

#### Set up Application
1. Clone the repository https://github.com/
2. Add as maven project in your IDE
3. Select project sdk as JDK1.8
4. Add as maven project

#### Run Test cases
**Run from command prompt or Terminal**
1. Clone the project
2. Open the command prompt / terminal from project directory
3. Run `mvn clean test`

**Run from IDE**
1. Clone from Git repository and add as maven project
2. Run as Maven test

#### Code coverage and Mutation coverage Report
Application uses pitest dependency to check code coverage and mutation testing coverage. follow instruction below in order to generate report
1. Run `mvn pitest:mutationCoverage`
2. Go to target -> pit-reports -> directory in the name of YYYYMMddHHmm -> index.html
3. Code coverage and mutation coverage report will be displayed as summary and package wise report

### Run Application
From console, 
1. Move to the directory where you have cloned the project
2. Run `mvn clean install` to build the application
3. Launch the application using `mvn exec:java`
4. Follow the onscreen instructions and proceed with the game until a player is a winner
5. You can exit anytime by using 'C' or 'c' when prompted or use Ctrl+C
