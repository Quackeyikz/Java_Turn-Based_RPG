package Mob;

class Entity {
	private String name;
	private int baseHealth;
	private int baseDefense;
	private int baseAttack;

	public Entity() {
		this("");
	}

	public Entity(String name){
		this.name = name;
		this.baseHealth = 100;
		this.baseDefense = 10;
		this.baseAttack = 30;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return this.name;
	}

	public int getBaseHealth(){
		return this.baseHealth;
	}

	// From BaseArmor -> BaseDefense
	public int getBaseDefense(){
		return this.baseDefense;
	}

	public int getBaseAttack(){
		return this.baseAttack;
	}

	// Untuk dialog
	public void chat(String msg){
		System.out.println("> " + this.name + ": \"" + msg + "\"");
	}
}
