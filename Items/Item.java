package Items;

class Item {
	private String name;
	private String rarity;

	public Item(String name){
		// Opportunity for item identify system, but i aint doin allat.
		this(name, "Unknown");
	}

	public Item(String name, String rarity){
		this.name = name;
		this.rarity = rarity;
	}

	public String getName(){
		return this.name;
	}

	public String getRarity(){
		return this.rarity;
	}
}
