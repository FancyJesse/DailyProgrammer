package challenge13.hard;

import java.util.Random;

public class Main {

	private static String[] CHOICE = { "ROCK", "PAPER", "SCISSORS" };

	private static class Player {
		private String name;
		private int gamesWon;
		private int gamesLost;
		private int gamesTied;
		private Random rdm;

		public Player(String name) {
			this.name = name;
			this.gamesWon = 0;
			this.gamesLost = 0;
			this.gamesTied = 0;
			this.rdm = new Random();
		}

		@Override
		public String toString() {
			return this.name;
		}

		public String getName() {
			return this.name;
		}

		public int getGamesPlayed() {
			return this.gamesWon + this.gamesLost + this.gamesTied;
		}

		public int getGamesWon() {
			return this.gamesWon;
		}

		public int getGamesLost() {
			return this.gamesLost;
		}

		public int getGamesTied() {
			return this.gamesTied;
		}

		public int getMove() {
			return rdm.nextInt(3);
		}

		public void gameWon() {
			this.gamesWon++;
		}

		public void gameLost() {
			this.gamesLost++;
		}

		public void gameTied() {
			this.gamesTied++;
		}

		public void stats() {
			System.out.println(getName() + " stats:\n\tGames Played: " + getGamesPlayed() + "\n\tGames Won: "
					+ getGamesWon() + "\n\tGames Lost: " + getGamesLost() + "\n\tGames Tied: " + getGamesTied());
		}

	}

	private static void playRPS(Player player1, Player player2, int totalGames) {
		System.out.println("PLAYING ROCK PAPER SCISSORS");
		System.out.println(player1 + " vs " + player2);

		for (int i = 0; i < totalGames; i++) {
			System.out.println("\nGAME " + (i + 1));

			int result;
			int player1Choice = player1.getMove();
			int player2Choice = player2.getMove();

			System.out.println(player1 + " chooses " + CHOICE[player1Choice]);
			System.out.println(player2 + " chooses " + CHOICE[player2Choice]);

			if (player1Choice == player2Choice) {
				result = 0;
			} else {
				if ((player1Choice == 0 && player2Choice == 2) || (player1Choice == 1 && player2Choice == 0)
						|| (player1Choice == 2 && player2Choice == 1))
					result = 1;
				else
					result = -1;
			}

			switch (result) {
			case 0:
				System.out.println("TIE GAME");
				player1.gameTied();
				player2.gameTied();
				break;
			case 1:
				System.out.println(player1 + " WINS");
				player1.gameWon();
				player2.gameLost();
				break;
			case -1:
				System.out.println(player2 + " WINS");
				player1.gameLost();
				player2.gameWon();
			}
		}

		System.out.println("\nTotal Games Player: " + totalGames);
		player1.stats();
		player2.stats();

	}

	public static void main(String[] args) {
		Player cpu1 = new Player("CPU1");
		Player cpu2 = new Player("CPU2");
		playRPS(cpu1, cpu2, 10000);
	}

}