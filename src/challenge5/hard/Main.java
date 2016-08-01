package challenge5.hard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

/*
 * Closely related to challenge2/intermediate
 * Thanks to http://www.insult-o-matic.com/insults/?mode=pirate 
 * 	for the pirate sayings
 * 
 */

public class Main {

	private static String getInput(String prompt) {
		while (true) {
			try {
				System.out.print(prompt + "\n> ");
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				return br.readLine().toUpperCase();
			} catch (IOException e) {
				System.out.println("Dead men tell no tales, ye sorry swine...");
			}
		}
	}

	private static class PirateGame {
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

			input = getInput("Ahoy freebooter, ye scalawag parrot...\nWhat be thy name? ");
			this.player = new Player(input);
			System.out.println(player + "? Enough with yer bilge, ye salty swabbie... Prepare for yer doom!");

			boolean newFighter = true;
			while (enemiesLeft > 0 && player.alive) {

				if (newFighter) {
					System.out.println("\n" + enemies[enemiesLeft - 1] + " Stands In Yer Path!");
					newFighter = false;
				}

				if (player.health < 25) {
					System.out.println("");
				}

				if (enemies[enemiesLeft - 1].health <= 20) {
					System.out.println(enemies[enemiesLeft - 1] + " Be Injured.");
				}

				System.out.println(" - F - ATTACK WITH FISTS");
				System.out.println(" - S - ATTACK WITH SWORD");
				System.out.println(" - P - ATTACK WITH PISTOL");
				System.out.println(" - T - THROW BIRD");
				System.out.println(" - B - BEG FOR LIFE");
				System.out.println(" - R - RUN");
				input = getInput("");
				System.out.println();
				switch (input) {
				case "F":
					enemies[enemiesLeft - 1].loseHealth(player.attack(0, 5, 0.1));
					break;
				case "S":
					enemies[enemiesLeft - 1].loseHealth(player.attack(10, 10, 0.3));
					break;
				case "P":
					enemies[enemiesLeft - 1].loseHealth(player.attack(25, -5, 1.0));
					break;
				case "T":
					enemies[enemiesLeft - 1].loseHealth(player.attack(5, -20, 5.0));
					break;
				case "B":
					System.out.println("'Ye'll not get our treasure, ye mutinous matey... Belay that talk!'");
					break;
				case "R":
					if (rdm.nextInt(100 + 1) < 20) {
						System.out.println("'Ye'll not get our treasure, ye mutinous matey... Belay that talk!'");
					} else {
						System.out.println(enemies[enemiesLeft - 1] + " Blocks As You Try To Run.");
					}
					break;
				default:
					System.out.println("Gangway Ye Grog-Snarfing Swab.\nINVALID INPUT");
					break;
				}

				if (enemies[enemiesLeft - 1].alive) {
					player.loseHealth(enemies[enemiesLeft - 1].attack(0, 0, 0));
				} else {
					enemiesLeft--;
					newFighter = true;
				}

			}

			if (!player.alive) {
				System.out.println("Dead men tell no tales, ye sorry swine...\nYOU ARE DEAD");
			}

			if (enemiesLeft == 0) {
				System.out.println("Land Ho!\nYOU MADE IT OUT THE ENEMY PIRATE SHIP ALIVE.");
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

			public int attack(int weaponBonus, int accuracyBonus, double criticalBonus) {
				int damage;
				boolean hit, crit;

				String[] pirateAttackSayings = { "'Aaaarrrrgggghhhh!'",
						"'I'll giv' ye a taste o' the cat, ye lice-infested pirate... Hoist the Jolly Roger!'",
						"'I'll hang ye by the gibbet, ye pox-faced swabbie... Land ho!'",
						"'I'll reduce yer ship to rubble, ye scurvy-infested parrot... Blimy!'",
						"'I'll rip and burn yer Jolly Roger, ye mutinous sea dog... To the poop deck!'",
						"'To 'ell with you, ye lily-livered swabbie... Yo ho ho an' a bottle o' rum!'",
						"'I'll hang ye by the gibbet, ye grog-snarfing pirate... Belay that talk!'",
						"'I'll hang ye by the gibbet, ye scrappy matey... Land ho!'",
						"'I'll giv' ye a taste o' the cat, ye pox-faced kraken... Belay that talk!'",
						"'I'll send ye to Davy Jones' locker, ye pox-faced sea bass... To the poop deck!'",
						"'Dance with Jack Ketch, ye sorry parrot... To the poop deck!'",
						"'I'll plunder yer coffer, ye scurvy-infested swabbie... Swab the deck!'",
						"'Enough with yer bilge, ye lily-livered kraken... Scupper that!'",
						"'Yarr! I be a pirate, ye pox-faced bilge-rat...'",
						"'I'll skewer yer gizzard, ye salty sea bass...'" };

				System.out.println(pirateAttackSayings[rdm.nextInt(pirateAttackSayings.length)]);
				System.out.println(this + " Attacks.");
				hit = rdm.nextInt(100 + 1) <= (this.accuracy + accuracyBonus);

				if (hit) {
					System.out.println("The Attack Lands!");
					damage = this.damage + weaponBonus;
					crit = rdm.nextInt(100 + 1) <= this.critical;
					if (crit) {
						System.out.println("CRITICAL HIT!!");
						damage *= (2 + criticalBonus);
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
			}
		}

		private class Enemy extends Base {
			private String[] type = { "BLACK BEARD", "CAPTAIN JACK SPARROW", "CAPTAIN HOOK", "BENITO DE SOTO" };

			public Enemy() {
				this.name = type[rdm.nextInt(type.length)];
				switch (name) {
				case "BLACK BEARD":
					this.health = 120;
					this.damage = 15;
					this.critical = 10;
					this.accuracy = 40;
					break;
				case "CAPTAIN JACK SPARROW":
					this.health = 150;
					this.damage = 23;
					this.critical = 2;
					this.accuracy = 45;
					break;
				case "CAPTAIN HOOK":
					this.health = 75;
					this.damage = 13;
					this.critical = 20;
					this.accuracy = 35;
					break;
				case "BENITO DE SOTO":
					this.health = 90;
					this.damage = 5;
					this.critical = 10;
					this.accuracy = 75;
					break;
				}

			}
		}

	}

	public static void main(String[] args) {
		PirateGame pirateeGame;

		pirateeGame = new PirateGame();
		pirateeGame.start();

	}

}
