/*
 * See note on Weapon.java, pls
*/
package Items;

public class Armor extends Item {
	private int defense;
	private String type;

	public Armor(String name){
		this(name, "Unknown", "Unknown", 5);
	}

	public Armor(String name, int modifier){
		this(name, "Unknown", "Unknown", modifier);
	}

	public Armor(String name, String type, String rarity, int modifier){
		super(name, rarity);
		this.defense = modifier;
		this.type = type;
	}

	public int getModifier(){
		return this.defense;
	}

	public void setModifier(int modifier){
		this.defense = modifier;
	}
}
