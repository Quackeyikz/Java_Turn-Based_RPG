package Mob;
import Items.*;

public class Player extends Entity {
	private int health;
	private int defense;
	private int attack;
	private Armor armor;
	private Weapon weapon;

	public Player(String name){
		this(name, null, null);
	}

	public Player(Armor armor){
		this("", armor, null);
	}

	public Player(Weapon weapon){
		this("", null, weapon);
	}

	// Note: Player lebih lemah dari mob, jadi tanpa weapon tidak ada tambahan attack
	public Player(String name, Armor armor, Weapon weapon){
		super.setName(name);
		this.armor = armor;
		this.weapon = weapon;

		this.health = super.getBaseHealth() + 20;
		this.defense = armor != null ? super.getBaseDefense() + armor.getModifier() : super.getBaseDefense(); 
		this.attack = weapon != null ? super.getBaseAttack() + weapon.getModifier() : super.getBaseAttack(); 
	}

	public void setArmor(Armor armor){
		this.defense -= this.armor.getModifier();

		this.armor = armor;
		this.defense += armor.getModifier();
	}

	public void setWeapon(Weapon weapon){
		this.attack -= this.weapon.getModifier();

		this.weapon = weapon;
		this.attack += weapon.getModifier();
	}

	public Armor getArmor(){
		return this.armor;
	}

	public Weapon getWeapon(){
		return this.weapon;
	}

	public int getAttack(){
		return this.attack;
	}

	public int getDefense(){
		return this.defense;
	}

	public int getHealth(){
		return this.health;
	}

	public void damaged(int damage){
		this.health -= damage;
	}

	public void heal(){
		// This is crucial for your fleeing strategy xD ~Erik
		this.health += 30;
	}

	public void info(){
		System.out.println("\n=---+ Character Status +---=");
		System.out.println("- Name\t\t: " + super.getName());
		System.out.println("- Health\t: " + this.health);
		System.out.println("- Attack\t: " + this.attack);
		System.out.println("- Defense\t: " + this.defense);
		System.out.print("~ Armor\t\t: "); 
		System.out.print(this.armor != null ? this.armor.getName() + " (+" + this.armor.getModifier() + " defense)\n" : "(Not wearing any armor)\n");
		System.out.print("~ Weapon\t: ");
		System.out.print(this.weapon != null ? this.weapon.getName() + " (+" + this.weapon.getModifier() + " attack)\n" : "(Not equipping any weapon)\n\n");
	}
}
