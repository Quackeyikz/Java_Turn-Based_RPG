/*
 * Pada UML Diagram, beberapa method dan atribut tidak benar-benar berguna/sulit diakses.
 * Maka dari itu, terdapat perubahan pada Weapon dan Armor class.
 * Sehingga atribut 'type' dan 'rarity' dapat digunakan dengan maksimal.
 * ~ Erik
*/
package Items;

public class Weapon extends Item {
	private int attack;
	private String type;

	public Weapon(String name){
		// 5 sebagai modifier terkecil (idk about game balancing)
		this(name, "Unknown", "Unknown", 5);
	}

	public Weapon(String name, int modifier){
		this(name, "Unknown", "Unknown", modifier);
	}

	// Terdapat perubahan untuk memaksimalkan penggunaan atribut
	public Weapon(String name, String type, String rarity, int modifier){
		super(name, rarity);
		this.attack = modifier;
		this.type = type;
	}

	public int getModifier(){
		return this.attack;
	}

	public void setModifier(int modifier){
		this.attack = modifier;
	}
}
