import Mob.*;
import Items.*;
import java.util.Scanner;

/*
 * DISCLAIMER:
 * I'm writing this while still sane, the whole Main program is a mess.
 * I maybe not even readable without the notes that I've put here.
 * Or the opposite, the notes makes it more messy.
 *
 * The program is a looping turn-based combat with random monsters.
 * The list of monsters is in the enemyTrigger() method.
 * For now, the balancing still a bit lack because you cant really survive
 * without any good equipment early game. So focus on flee, if fail, heal.
 *
 * Q: AI Generated?
 * A: NO! This program as no AI / code generation influence. If any, there's
 *    only few lines that's a result of AI suggestion / forum code for optimization.
 *
 * Q: Are the items/enemies a reference to something?
 * A: Yes! Those references are: Overlord, Jujutsu Kaisen, Terraria, and Skyrim.
 *
 * That's it, enjoy the RNG grind!
 * ~ Erik / Quackeyikz
*/

public class Main {
	// Untuk pewarnaan di terminal :P
	public static final String RESET = "\u001B[0m";
        public static final String BLACK = "\u001B[30m";
        public static final String RED = "\u001B[31m";
        public static final String GREEN = "\u001B[32m";
        public static final String YELLOW = "\u001B[33m";
        public static final String BLUE = "\u001B[34m";

	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		char replay = 'n';
		boolean battleResult = true;

		// Start!
		welcomeBanner();

		// Character customization
		Player player = characterCustomization();

		// Intro
		intro(player);

		do
		{
			battleResult = battle(player, enemyTrigger());
			
			if(battleResult){
				System.out.print("Still want to continue exploring? (Y/n) ");
				replay = sc.next().charAt(0);
			} else {
				// 'You died' route
				outro(player, -1);
				System.exit(0);
			}
		} 
		while(replay == 'y' || replay == 'Y');

		// Alive route
		outro(player, 1);
	}

	public static void welcomeBanner(){
		String welcome = "WELCOME";
		String toGame = "TO THE GAME!\n";
		String turnBase = "This game is a simple turn-based fight";
		String between = "between your own character and an enemy!";
		String enemy = "The enemy will come as you still alive before";
		String survive = "the game ends, try to survive as long as you can!";

		String kalimat[] = new String[]{welcome, toGame, turnBase, between, enemy, survive};
		int maksHuruf = 55;

		System.out.println("=+-----------------------------------------------------+=");

		for(int i = 0; i < 6; i++){
			// Padding untuk satu sisi, digunakan 2x
			int padding = (maksHuruf - kalimat[i].length()) / 2; // Diinspirasi oleh GeeksForGeeks ~ Erik
			System.out.printf("%" + padding + "s%s%" + padding + "s\n", "", kalimat[i], "");
		}

		System.out.println("=+-----------------------------------------------------+=");

		System.out.println("\nDon't talk about balancing.");
		System.out.println("~ Made with insanity, Erik\n");
	}

	public static Player characterCustomization(){
		Scanner sc = new Scanner(System.in);
		Armor starterArmor = null;
		Weapon starterWeapon = null;
		
		System.out.println("+- CHARACTER CUSTOMIZATION -+");

		System.out.print("Enter your Name: ");
		String name = sc.nextLine();

		System.out.print("\nWould you like a " + GREEN + "Starter ARMOR?" + RESET + " (y/n) ");
		char armorConfirm = sc.nextLine().charAt(0); // Buat reading char

		if(armorConfirm == 'y' || armorConfirm == 'Y'){
			starterArmor = new Armor("Wooden Armor", "Starter Armor", "Common", 10);
			System.out.println("Successfully made a " + GREEN + starterArmor.getName() + RESET + "!");
		} 

		System.out.print("Would you like a " + GREEN + "Starter WEAPON?" + RESET + " (y/n) ");
		char weaponConfirm = sc.next().charAt(0);

		if(weaponConfirm == 'y' || weaponConfirm == 'Y'){
			starterWeapon = new Weapon("Wooden Shortsword", "Starter Weapon", "Common", 5);
			System.out.println("Successfully made a " + GREEN + starterWeapon.getName() + RESET + "!");
		}
		
		// Buat & Print data player
		Player newPlayer = new Player(name, starterArmor, starterWeapon);
		newPlayer.info();

		System.out.println(RED + "\nReady to Play?" + RESET);
		System.out.println("Press Enter to Continue...");
		sc.nextLine();
		sc.nextLine();

		return newPlayer;
	}

	public static void intro(Player player){
		System.out.println("A grand dungeon, known for it's massive area and difficulty, \nswarmed by monsters and rare loots, is now right in front of you.");
		System.out.println("You heard of the rumors, immediately going with the hope to get some good drops.");
		System.out.println("You, " + YELLOW + player.getName() + RESET + ", the famous adventurer, rushes in.");

		System.out.println("\nBut only after a few steps in, your challenge appears...");

		delayOutput(5000);
	}

	// Pls dont talk about balancing.
	public static Enemy enemyTrigger(){
		double enemyChance = Math.random();

		if(enemyChance <= 0.001)
			return new Enemy("Sukuna", 900, 100, 50);
		else if(enemyChance <= 0.1)
			return new Enemy("Rainbow Dragon", 0, 50, 10);
		else if(enemyChance <= 0.3)
			return new Enemy("Dragonewt", -20, 10, 15);
		else if(enemyChance <= 0.6)
			return new Enemy("High Orc", -40, 5, 10);
		else
			return new Enemy("Slime", -90, 2, 0);
	}

	// Pilihan:  Attack, Heal, Flee.
	public static boolean battle(Player player, Enemy enemy){
		Scanner sc = new Scanner(System.in);

		System.out.println("A " + RED + enemy.getName() + RESET + " is blocking your way.\n");

		// Selama health tidak habis, battle berlanjut
		BattleTurns:
		while(!(player.getHealth() <= 0)){
			delayOutput(1000);
			
			System.out.printf(YELLOW + "%15s" + RESET + "%15s" + RED + "%20s" + RESET + "\n", player.getName(), "vs.", enemy.getName());
			System.out.printf("%3s HP %3s DEF %3s ATK %10s %3s HP %3s DEF %3s ATK\n\n", player.getHealth(), player.getDefense(), player.getAttack(), "", enemy.getHealth(), enemy.getDefense(), enemy.getAttack());

			System.out.println("+- Your Turn! ---------------------------------+");
			System.out.println("| (1) Attack   (2) Heal   (3) Flee   (4) Stats |");
			System.out.println("+- Choose one option: -------------------------+");

			int action = sc.nextInt();
			System.out.println("");

			switch(action){
				case 1:
					// DO NOT TALK ABOUT BALANCING (dapat dari random forum)
					double damageDealtRaw = player.getAttack() * (100.0 / (100.0 + enemy.getDefense()));
					long damageDealt = Math.round(damageDealtRaw);
					enemy.damaged((int) damageDealt);

					System.out.println("You attacked the " + enemy.getName());

					double voiceChance = Math.random();

					if(voiceChance <= 0.3){
						player.chat("SHAW!");
						enemy.chat("URGH!..");
					} else if(voiceChance <= 0.6){
						player.chat("Hya!-");
						enemy.chat("*grunts*");
					} else {
						player.chat("RRAH!");
						enemy.chat("AARGHH!...");
					}

					System.out.println("\nYou dealt " + RED + damageDealt + RESET + " damage.\n");

					if(enemy.getHealth() <= 0){
						System.out.println("You" + GREEN + " defeated" + RESET + " the " + enemy.getName() + ".");
						break BattleTurns;
					}
					break;

				case 2:
					player.chat("I cast: " + GREEN + "HEAL!" + RESET);
					player.heal();

					double easterEggChat = Math.random();
					if(easterEggChat <= 0.2)
						enemy.chat("Hey, I can't do that... that's so unfair!");

					System.out.println("You healed " + GREEN + "20 health." + RESET);
					break;

				case 3:
					double fleeChance = Math.random();

					if(enemy.getHealth() < (enemy.getBaseHealth() * 0.5)){
						System.out.println("You fled from the battle...");
						return true;
					} else {
						// 40% failchance jika health musuh lebih dari 50% base health
						if(fleeChance < 0.4){
							System.out.println("You " + RED + "failed" + RESET + " to run away ...");
						} else {
							System.out.println("You somehow fled from enemy's grasp...");
							return true;
						}
					}
					break;

				case 4:
					player.info();
					continue;

				default:
					System.out.println("\nYou shouted: ");
					player.chat("SHAWWW!");
					System.out.println(RED + "That did nothing." + RESET);
			}

			System.out.println("");

			delayOutput(1000);

			// Enemy's Turn!
			System.out.println(enemy.getName() + "'s turn!");
			if(enemy.getHealth() <= 10){
				double enemyFleeChance = Math.random();
				if(enemyFleeChance < 0.3){
					System.out.println("Because of your terrifying AURA, the " + GREEN + "enemy fled..." + RESET);
					return true;
				}
				else {
					System.out.println("The " + enemy.getName() + " stands still, terrified...\n");
				}
			} else {
				double damageTakenRaw = enemy.getAttack() * (100.0 / (100.0 + player.getDefense()));
				long damageTaken = Math.round(damageTakenRaw);
				player.damaged((int) damageTaken);

				System.out.println(RED + enemy.getName() + RESET + " decided to attack!");
				enemy.chat("RYAH!");
				player.chat("Oof!..");

				System.out.println("\nYou took " + RED + damageTaken + RESET + " damage.\n");
			}
		}

		// Lose
		if(player.getHealth() <= 0)
			return false;
		// Win
		else if (enemy.getHealth() <= 0){
			double dropChance = Math.random();
			char equip = 'y';
			
			if(dropChance <= 0.45)
			{
				Armor armorDrop = enemy.dropLootArmor();
				System.out.println("\nThe " + RED + enemy.getName() + RESET + " dropped an armor: " + GREEN + armorDrop.getName() + " (+" + armorDrop.getModifier() + ")" + RESET);
				System.out.print("\nWould you like to wear it? (y/n) ");

				equip = sc.next().charAt(0);
				
				if(equip == 'y' || equip == 'Y'){
					player.setArmor(armorDrop);
					System.out.println("You wore the armor.");
				} else {
					System.out.println("You left the armor.");
				}
			}
			else if(dropChance <= 0.95)
			{
				Weapon weaponDrop = enemy.dropLootWeapon();
				System.out.println("\nThe " + RED + enemy.getName() + RESET + " dropped an weapon: " + GREEN + weaponDrop.getName() + " (+" + weaponDrop.getModifier() + ")" + RESET);
				System.out.print("\nWould you like to equip it? (y/n) ");

				equip = sc.next().charAt(0);
				
				if(equip == 'y' || equip == 'Y'){
					player.setWeapon(weaponDrop);
					System.out.println("You equipped the weapon.");
				} else {
					System.out.println("You left the weapon.");
				}
			}
			else {
				System.out.println("The " + enemy.getName() + " didn't drop anything.");
			}

			return true;
		}
		else
			// If for some reason you win, idk ~ Erik
			return true;
	}

	public static void outro(Player player, int condition){
		if(condition > 0){
			System.out.println("\nAfter some gruesome battle with the monsters in the dungeon, \nYou decided to call it a day and bring all the loots you've gained");
			System.out.println("You didn't managed to clear the dungeon. But in the end of the day, your life is the most valuable treasure.\n\nThis is your final stats:");

			player.info();

			System.out.println(GREEN + "\nCongratulation! You've made it out alive!" + RESET + "\nTHE END");
		} else {
			System.out.println(RED + "YOU DIED\nThe End." + RESET);
		}
	}

	public static void delayOutput(int time){
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
