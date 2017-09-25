package com.addstones.sg.katas.tennis;

public class Game {

	private Player player1;
	private Player player2;
	private boolean deuce;
	private boolean tieBreak;
	private boolean theEnd;

	public Game(Player player1, Player player2) {
		this.player1 = player1;
		this.player2 = player2;
	}

	public Player getPlayer1() {
		return player1;
	}

	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}

	public boolean isDeuce() {
		return deuce;
	}

	public void setDeuce(boolean deuce) {
		this.deuce = deuce;
	}

	public boolean isTieBreak() {
		return tieBreak;
	}

	public void setTieBreak(boolean tieBreak) {
		this.tieBreak = tieBreak;
	}

	public boolean isTheEnd() {
		return theEnd;
	}

	public void setTheEnd(boolean theEnd) {
		this.theEnd = theEnd;
	}
	
	

	public String manageGameSetScore() {
		if (this.player1.getSetScore() >= 5 && this.player2.getSetScore() >= 5) {
			if (Math.abs(this.player2.getSetScore()
					- this.player1.getSetScore()) >= 2) {
				return getLeadPlayer().getName() + " won";
			} else if (this.player1.getSetScore() == this.player2.getSetScore()) {
				return "Equability";
			} else {
				return getLeadPlayer().getName() + " on advanced";
			}
		} else {
			if (this.player1.getSetScore() == 6
					|| this.player2.getSetScore() == 6) {
				return getLeadPlayer().getName() + " won";
			} else {
				return this.player1.getSetScore() + ", "
						+ this.player2.getSetScore();
			}
		}
	}

	public void managePlayerAdvanced() {
		if (this.player1.getPointScore() == 40
				|| this.player2.getPointScore() == 40) {
			if (Math.abs(this.player2.getPointScore()
					- this.player1.getPointScore()) >= 10) {
				getPointScoreLeadPlayer().setAdvanced(true);
			}
		}

	}

	public void manageEndOfTheGame() {
		if (this.player1.getSetScore() >= 5 && this.player2.getSetScore() >= 5) {
			if (Math.abs(this.player2.getSetScore()
					- this.player1.getSetScore()) >= 2) {
				this.setTheEnd(true);
			}
		} else {
			if ((this.player1.getSetScore() == 6 || this.player2.getSetScore() == 6)
					&& Math.abs(this.player2.getSetScore()
							- this.player1.getSetScore()) >= 2) {
				this.setTheEnd(true);
			}
		}

	}

	public Boolean isDeuceActivated() {

		if (this.player2.getPointScore() >= 40
				&& this.player1.getPointScore() >= 40) {
			this.setDeuce(true);
			return true;
		} else {
			return false;
		}
	}

	public void isTieBreakActivated() {

		if (this.player2.getSetScore() >= 6 && this.player1.getSetScore() >= 6) {
			this.setTieBreak(true);

		}
	}

	public void isAdvancedGameManger(Player winner, Player looser) {
		if (this.deuce) {
			if (looser.isAdvanced()) {
				looser.setAdvanced(false);
			} else {
				winner.setAdvanced(true);
			}
		}
	}

	public Player getLeadPlayer() {
		return (player1.getSetScore() > player2.getSetScore()) ? player1
				: player2;
	}

	public Player getPointScoreLeadPlayer() {
		return (player1.getPointScore() > player2.getPointScore()) ? player1
				: player2;
	}


	public void afficherScore() {
		System.out.println(this.player1.getName() + " [ " + "Set :"
				+ this.player1.getSetScore() + " Points :"
				+ this.player1.getPointScore()+" ] ");
		System.out.println(this.player2.getName() + " [ " + "Set :"
				+ this.player2.getSetScore() + " Points :"
				+ this.player2.getPointScore()+" ] ");
		System.out
				.println("**************************************************************");
	}

	public void checkToReset() {

		if (this.player1.isRest() || this.player2.isRest()) {

			this.player1.setPointScore(0);
			this.player1.setRest(false);
			this.player1.setAdvanced(false);

			this.player2.setPointScore(0);
			this.player2.setRest(false);
			this.player2.setAdvanced(false);

		}
	}

}
