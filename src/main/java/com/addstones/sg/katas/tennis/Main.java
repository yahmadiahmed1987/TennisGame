package com.addstones.sg.katas.tennis;

public class Main implements Runnable {

	public Main() {
		Thread game = new Thread(this);
		game.start();

	}

	public static void main(String[] args) {

		new Main();

	}

	@Override
	public void run() {

		Player player1 = new Player("Ahmed");
		Player player2 = new Player("Octavian");
		Game game = new Game(player1, player2);

		while (true) {

			double ball = Math.random() * 2;

			beforeBallWinCheck(game);

			if (ball > 1) {
				game.isAdvancedGameManger(game.getPlayer1(), game.getPlayer2());
				game.getPlayer1().winBall();

			} else {
				game.isAdvancedGameManger(game.getPlayer2(), game.getPlayer1());
				game.getPlayer2().winBall();
			}
			afterBallWinCheck(game);

			try {

				Thread.sleep(1000);
			} catch (InterruptedException exception) {

				exception.printStackTrace();
			}

			if (game.isTheEnd()) {
				System.exit(0);
			}
		}
	}

	public void beforeBallWinCheck(Game game) {
		game.isDeuceActivated();
		game.isTieBreakActivated();
		game.managePlayerAdvanced();
	}

	public void afterBallWinCheck(Game game) {
		game.afficherScore();
		System.out.println(game.manageGameSetScore());
		game.checkToReset();
		game.manageEndOfTheGame();
	}

}
