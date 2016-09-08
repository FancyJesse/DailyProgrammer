package challenge16.intermediate;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/*
 * Disclaimer: I never played Craps. Vaguely understand the rules. This
 * implementation of Craps may contain errors.
 * 
 */

public class Main {

	private static class Craps {
		private final List<Integer> winning = Arrays.asList(7, 11);
		private final List<Integer> losing = Arrays.asList(2, 3, 12);
		private Random rdm;
		private int gamesWon;
		private int gamesLost;
		private int totalRolls;
		private List<Rolls> rollList;

		public Craps() {
			this.rdm = new Random();
			this.gamesWon = 0;
			this.gamesLost = 0;
			this.totalRolls = 0;
			this.rollList = new ArrayList<>();
			for (int i = 2; i <= 12; i++) {
				this.rollList.add(new Rolls(i));
			}
		}

		public void play(int times) {

			for (int i = 1; i <= times; i++) {
				System.out.println("-- Games " + i + " --");
				int r = roll();
				if (winning.contains(r)) {
					System.out.println("Shooter Wins With " + r + ".");
					this.gamesWon++;
				} else if (losing.contains(r)) {
					System.out.println("Shooter Loses With " + r + ".");
					this.gamesLost++;
				} else {
					System.out.println("Rolled " + r + ". Continuing ...");
					int rr;
					while (true) {
						rr = roll();
						if (rr == 7) {
							System.out.println("Shooter Loses With 7 on Point Toss.");
							this.gamesLost++;
							break;
						} else if (rr == r) {
							System.out.println("Shooter Rolled Another " + r + " and Wins.");
							this.gamesWon++;
							break;
						} else {
							System.out.println("Shooter Rolled " + rr + ". Continuing ...");
						}

					}
				}
				System.out.println();
			}

			System.out.println("\t-- Stats --");
			stats();
			for (Rolls roll : this.rollList) {
				roll.stats();
			}
		}

		private int roll() {
			int roll = (rdm.nextInt(6) + 1) + (rdm.nextInt(6) + 1);
			this.rollList.get(roll - 2).rolled();
			this.totalRolls++;
			return roll;
		}

		private void stats() {
			System.out.println("Games Won: " + this.gamesWon);
			System.out.println("Games Lost: " + this.gamesLost);
		}

		private class Rolls {
			private int roll;
			private int timesRolled;

			public Rolls(int roll) {
				this.roll = roll;
				this.timesRolled = 0;
			}

			public void rolled() {
				this.timesRolled++;
			}

			private String getPercentage() {
				NumberFormat nf = new DecimalFormat("##.##");
				return nf.format(((double) this.timesRolled / totalRolls) * 100);
			}

			public void stats() {
				System.out.println(
						"Roll: " + this.roll + " \tTimes: " + this.timesRolled + "\tPercentage: " + getPercentage());
			}

		}

	}

	public static void main(String[] args) {
		Craps craps = new Craps();
		craps.play(5);
	}

}