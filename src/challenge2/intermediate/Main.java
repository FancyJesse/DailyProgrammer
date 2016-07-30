package challenge2.intermediate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class Main {

	private static String getInput(String prompt) {
		while (true) {
			try {
				System.out.print(prompt + "\n> ");
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				return br.readLine().toUpperCase();
			} catch (IOException e) {
				System.out.println("I Must Have Dozed off. Can You Repeat That?");
			}
		}
	}

	private static class AdventureGame {
		private Player player;
		private Enemy[] enemies;
		private Random rdm;

		public void start() {
			String input;
			rdm = new Random();

			int enemiesLeft = rdm.nextInt(5) + 1;
			enemies = new Enemy[enemiesLeft];
			for (int i = 0; i < enemies.length; i++) {
				enemies[i] = new Enemy();
			}

			input = getInput(
					"You Awake In The Middle Of The Woods.\nYou Have No Recollection Of How You Got There.\nThe Only Thing You Remember Is Your Name: ");

			this.player = new Player(input);

			System.out.println(
					"\nYou Stand Quickly As You Have Gained Consciousness.\nYou Don't Seem Injured, But You Are Lost.");
			System.out.println("... " + player + " ... Your Adventure Beings ...");

			Boolean inCombat = false;
			while (enemiesLeft > 0 && player.alive) {

				if (player.health < 25) {
					System.out.println(
							"You are Feeling Very Weak.\n'I Don't Think I Can Make It ...' You Say To Yourself.");
				}

				if (inCombat) {
					if (enemies[enemiesLeft - 1].health <= 20) {
						System.out.println(enemies[enemiesLeft - 1] + " Apears To Be Injured.");
					}

					System.out.println(" - A - ATTACK");
					System.out.println(" - B - BEG FOR LIFE");
					System.out.println(" - R - RUN");
					input = getInput("");
					System.out.println();
					switch (input) {
					case "A":
						if (player.hasWeapon) {
							enemies[enemiesLeft - 1].loseHealth(player.attack());
						} else {
							System.out.println("You Attempt To Attack With Your Fists.\nBut Nothing Happens.");
						}
						break;
					case "B":
						System.out.println("There Is No Use Begging.");
						break;
					case "R":
						if (rdm.nextInt(100 + 1) < 20) {
							System.out.println("Your Cowardly Act Has Spared Your Life.");
							inCombat = false;
						} else {
							System.out.println(enemies[enemiesLeft - 1] + " Blocks You As You Try To Run.");
						}
						break;
					default:
						System.out.println("'I Need To Wake Up ...' You Think To Yourself.");
						break;
					}

					if (inCombat && enemies[enemiesLeft - 1].alive) {
						player.loseHealth(enemies[enemiesLeft - 1].attack());
					} else {
						System.out.println("\nThe Area Seems Clear ... For Now ...");
						enemiesLeft--;
						inCombat = false;
					}

				} else {

					System.out.println(" - L - LOOK AROUND");
					System.out.println(" - W - GO DEEPER INTO THE WOODS");
					System.out.println(" - S - GO TO SLEEP");
					input = getInput("");
					System.out.println();
					switch (input) {
					case "L":
						if (player.hasWeapon) {
							System.out.println(
									"There Is Nothing Interesting To See But Soft Patches Of Grass.\n'I'm Feeling Pretty Tired ...' You Think To Yourself.");
						} else {
							System.out.println(
									"You See A Crystal Sword On The Floor. You Pick It Up.\n'This Might Be Useful' You Say To Yourself.");
							player.hasWeapon = true;
						}
						break;
					case "W":
						System.out.println("You Walk Deeper Into The Woods.");
						if (rdm.nextBoolean()) {
							System.out.println("You Hear Something Quickly Approaching From The Distance ...\nA "
									+ enemies[enemiesLeft - 1] + " Now Stands In Your Path.");
							inCombat = true;
						}
						break;
					case "S":
						System.out.println(
								"You Lie Down In The Woods And Try To Sleep\n'This Might Just Be A Horrible Dream' You Say To Yourself.\nAs You Close Your Eyes, You Start To Drift Away Into A Dark Abyss.\nIt Might Have Been A Horrible Dream All Along.\nYou Just Need to Wake Up Now.");
						player.alive = false;
						break;
					default:
						System.out.println("'This Is Must Be A Horrible Dream ...' You Think To Yourself.");

						break;
					}

				}

			}

			if (enemiesLeft == 0) {
				System.out.println(
						"You Keep Venturing Through The Woods ... Eventually See Daylight And The Sounds Of Civilization.");
				System.out.println("\nCONGRATULATIONS, " + player + ". YOU CONQUERED THE WOODS!");
			}
			System.out.println("\nGAME OVER.");

		}

		private class Base {
			public String name;
			public boolean alive;
			public int health;
			public int damage;
			public int accuracy;
			public int critical;
			public boolean hasWeapon;

			public Base() {
				this.alive = true;
			}

			@Override
			public String toString() {
				return this.name;
			}

			public void loseHealth(int healthLost) {
				health -= healthLost;
				if (health <= 0) {
					System.out.println(this + " Has Been Killed.");
					alive = false;
				}
			}

			// public void gainHealth(int healthGain) {
			// health += healthGain;
			// }

			public int attack() {
				int damage;
				boolean hit, crit;

				System.out.println(this + " Attacks.");
				hit = rdm.nextInt(100 + 1) <= this.accuracy;

				if (hit) {
					System.out.println("The Attack Lands!");
					damage = this.damage;
					crit = rdm.nextInt(100 + 1) <= this.critical;
					if (crit) {
						System.out.println("CRITICAL HIT!!");
						damage *= 2;
					}
				} else {
					System.out.println(this + " Missed!");
					damage = 0;
				}

				return damage;
			}
		}

		private class Player extends Base {
			public Player(String name) {
				this.name = name;
				this.health = 100;
				this.damage = 20;
				this.accuracy = 90;
				this.critical = 15;
				this.hasWeapon = false;
			}
		}

		private class Enemy extends Base {
			private String[] type = { "OGRE", "DRAGON", "WILD DOG", "THIEF" };

			public Enemy() {
				this.name = type[rdm.nextInt(type.length)];
				this.hasWeapon = true;
				switch (name) {
				case "OGRE":
					this.health = 75;
					this.damage = 12;
					this.critical = 5;
					this.accuracy = 40;
					break;
				case "DRAGON":
					this.health = 150;
					this.damage = 23;
					this.critical = 8;
					this.accuracy = 20;
					break;
				case "WILD DOG":
					this.health = 25;
					this.damage = 4;
					this.critical = 7;
					this.accuracy = 75;
					break;
				case "THIEF":
					this.health = 100;
					this.damage = 10;
					this.critical = 20;
					this.accuracy = 65;
					break;
				}

			}
		}

	}

	public static void main(String[] args) {
		AdventureGame adventureGame;

		adventureGame = new AdventureGame();
		adventureGame.start();

	}

}
