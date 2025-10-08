package Mob;
import Items.*;

public class Enemy extends Entity {
	private int health;
	private int defense;
	private int attack;

	public Enemy(String name){
		this(name, 0, 0, 0);
	}

	public Enemy(String name, int health){
		this(name, health, 0, 0);
	}

	public Enemy(String name, int health, int defense){
		this(name, health, defense, 0);
	}

	// UML ada salah penamaan ( ToT)
	public Enemy(String name, int health, int defense, int attack){
		super.setName(name);
		this.health = super.getBaseHealth() + health;
		this.defense = super.getBaseDefense() + defense;
		this.attack = super.getBaseAttack() + attack;
	}

	public int getHealth(){
		return this.health;
	}

	public int getDefense(){
		return this.defense;
	}

	public int getAttack(){
		return this.attack;
	}

	// To make it more fun, i added random generator
	public Weapon dropLootWeapon(){
		double rarityChance = Math.random();
		double weaponChance = Math.random();

		// Rare rarity
		if(rarityChance <= 0.2)
		{
			if(weaponChance <= 0.001)
				return new Weapon("World Savior", "Mace", "Rare", 999);
			else if(weaponChance < 0.5)
				return new Weapon("A Thousand of Blazing Suns", "Greatsword", "Rare", 40);
			else
				return new Weapon("Mace of Molag Bal", "Mace", "Rare", 35);
		}
		// Uncommon
		else if(rarityChance <= 0.5)
		{
			if(weaponChance <= 0.5)
				return new Weapon("Playful Cloud", "Staff", "Uncommon", 20);
			else
				return new Weapon("Blasting Staff", "Staff", "Uncommon", 25);
		}
		// Common
		else
		{
			if(weaponChance <= 0.3)
				return new Weapon("Wooden Shortsword", "Sword", "Common", 10);
			else if(weaponChance <= 0.6)
				return new Weapon("Toy Knife", "Knife", "Common", 5);
			else
				return new Weapon("Nagakiba", "Katana", "Common", 15);
		}
	}

	public Armor dropLootArmor(){
		double rarityChance = Math.random();
		double armorChance = Math.random();

		// Rare
		if(rarityChance <= 0.2)
		{
			if(armorChance <= 0.3)
				return new Armor("Complience With Law", "Full-Body Armor", "Rare", 40);
			else if(armorChance <= 0.6)
				return new Armor("Platinum Dragon Lord", "Full-Body Armor", "Rare", 35);
			else
				return new Armor("Auric Tesla", "Plate Armor", "Rare", 30);
		}
		else if(rarityChance <= 0.5)
		{
			if(armorChance <= 0.5)
				return new Armor("Ebony Plate", "Plate Armor", "Uncommon", 25);
			else
				return new Armor("Ancient Shrouded Armor", "Light Armor", "Uncommon", 20);
		}
		else
		{
			if(armorChance <= 0.5)
				return new Armor("Ragged Tunic", "Cloth", "Common", 5);
			else
				return new Armor("Iron Plate", "Plate Armor", "Common", 15);
		}
	}

	public void damaged(int damage){
		this.health -= damage;
	}

	public void info(){
		System.out.println("\n[!]--= Enemy Stats =--[!]");
		System.out.println("- Name\t: " + super.getName());
		System.out.println("- Health\t: " + this.health);
		System.out.println("- Defense\t: " + this.defense);
		System.out.println("- Attack\t: " + this.attack + "\n");
	}
}
