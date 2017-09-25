package com.addstones.sg.katas.tennis;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import java.util.stream.IntStream;

import org.junit.Before;
import org.junit.Test;

import com.addstones.sg.katas.tennis.Game;
import com.addstones.sg.katas.tennis.Player;

public class GameTest {

	Player playerNumberOne;
	Player playerNumberTwo;
	Game game;

	@Before
	public void beforeGameTest() {
		playerNumberOne = new Player("Victor");
		playerNumberTwo = new Player("Ahmed");
		game = new Game(playerNumberOne, playerNumberTwo);
	}

	@Test
	public void setScoreAndPointScoreShouldZeroInTheBeginningForAllPlayers() {

		assertThat(playerNumberOne, hasProperty("pointScore", equalTo(0)));
		assertThat(playerNumberOne, hasProperty("setScore", equalTo(0)));

		assertThat(playerNumberTwo, hasProperty("pointScore", equalTo(0)));
		assertThat(playerNumberTwo, hasProperty("setScore", equalTo(0)));
	}

	@Test
	public void pointScoreShouldBeFifteenIfPlayerWinTheFirstBall() {
		playerNumberTwo.winBall();
		assertThat(playerNumberTwo, hasProperty("pointScore", equalTo(15)));
	}

	@Test
	public void pointsScoreShouldBeThirtyIfPlayerWinTheSecondBall() {
		playerNumberTwo.winBall();
		playerNumberTwo.winBall();
		assertThat(playerNumberTwo, hasProperty("pointScore", equalTo(30)));
	}

	@Test
	public void scoreShouldBeFortyIfPlayerWinTheThirdBall() {
		playerNumberTwo.winBall();
		playerNumberTwo.winBall();
		playerNumberTwo.winBall();
		assertThat(playerNumberTwo, hasProperty("pointScore", equalTo(40)));
	}

	@Test
	public void deuceShouldBeActivatedIfTheTwoPlayersReachTheScoreForty() {
		IntStream.rangeClosed(1, 3).forEach((Integer) -> {
			playerNumberOne.winBall();
		});
		IntStream.rangeClosed(1, 3).forEach((Integer) -> {
			playerNumberTwo.winBall();
		});
		game.isDeuceActivated();
		assertThat(game, hasProperty("deuce", is(true)));
	}

	@Test
	public void advantageShouldBeDescriptionWhenLeastThreeBallHaveBeenScoredByEachSideAndPlayerHasOneBallMoreThanHisOpponent() {
		IntStream.rangeClosed(1, 3).forEach((Integer) -> {
			playerNumberOne.winBall();
		});
		IntStream.rangeClosed(1, 4).forEach((Integer) -> {
			playerNumberTwo.winBall();
		});
		game.isAdvancedGameManger(playerNumberTwo, playerNumberOne);
		assertThat(playerNumberOne, hasProperty("advanced", is(false)));
		assertThat(playerNumberTwo, hasProperty("advanced", is(true)));
	}

	@Test
	public void setScoreShouldBeOneWhenAtLeastThreeBallHaveBeenScoredByEachSideAndPlayerHasTowBallMoreThanHisOpponent() {
		IntStream.rangeClosed(1, 3).forEach((Integer) -> {
			playerNumberOne.winBall();
		});
		IntStream.rangeClosed(1, 4).forEach((Integer) -> {
			playerNumberTwo.winBall();
		});
		game.isAdvancedGameManger(playerNumberTwo,playerNumberOne);
		playerNumberTwo.winBall();
		assertThat(playerNumberTwo, hasProperty("setScore", equalTo(1)));
		assertThat(playerNumberOne, hasProperty("setScore", equalTo(0)));
	}

	@Test
	public void setScoreShouldBeOneIfPlayerWinTheFirstSet() {
		playerNumberTwo.winSet();
		assertThat(playerNumberTwo, hasProperty("setScore", equalTo(1)));
	}

	@Test
	public void tieBreakShouldBeActivatedIfTheTwoPlayersReachTheScoreSix() {
		IntStream.rangeClosed(1, 6).forEach((Integer) -> {
			playerNumberOne.winSet();
		});
		IntStream.rangeClosed(1, 6).forEach((Integer) -> {
			playerNumberTwo.winSet();
		});
		game.isTieBreakActivated();
		assertThat(game, hasProperty("tieBreak", is(true)));
	}

	@Test
	public void equabilityShouldBeDescriptionWhenAtLeastSixSetHaveBeenScoredByEachPlayerAndTheScoresAreEqual() {
		IntStream.rangeClosed(1, 6).forEach((Integer) -> {
			playerNumberOne.winSet();
		});
		IntStream.rangeClosed(1, 6).forEach((Integer) -> {
			playerNumberTwo.winSet();
		});
		game.isTieBreakActivated();
		assertThat(game.manageGameSetScore(), is("Equability"));
		playerNumberOne.winSet();
		assertThat(game.manageGameSetScore(), is(not("Equability")));
		assertThat(game.manageGameSetScore(), is("Victor on advanced"));
		playerNumberTwo.winSet();
		assertThat(game.manageGameSetScore(), is("Equability"));
	}

	@Test
	public void gameShouldBeWonByTheFirstPlayerwhoHaveWonAtLeastSixSetsInTotalAndWithAtLeastTwoSetsMoreThanTheOpponent() {
		IntStream.rangeClosed(1, 6).forEach((Integer) -> {
			playerNumberOne.winSet();
		});
		IntStream.rangeClosed(1, 5).forEach((Integer) -> {
			playerNumberTwo.winSet();
		});
		assertThat(game.manageGameSetScore(), is(not("Victor won")));
		assertThat(game.manageGameSetScore(), is(not("Ahmed won")));
		playerNumberOne.winSet();
		assertThat(game.manageGameSetScore(), is("Victor won"));
	}

}
