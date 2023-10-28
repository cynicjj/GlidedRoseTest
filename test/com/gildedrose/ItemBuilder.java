package com.gildedrose;

class ItemBuilder {

	private String name = "no name";
	private int sellIn = 73;
	private int quality = 73;

	public ItemBuilder sellIn(int sellIn) {
		this.sellIn = sellIn;
		return this;
	}

	public ItemBuilder quality(int quality) {
		this.quality = quality;
		return this;
	}

	public Item build() {
		return new Item(name, sellIn, quality);
	}

	public ItemBuilder brie() {
		name = "Aged Brie";
		return this;
	}

	public ItemBuilder legendary() {
		name = "Sulfuras, Hand of Ragnaros";
		return this;
	}

	public ItemBuilder backstage() {
		name = "Backstage passes to a TAFKAL80ETC concert";
		return this;
	}

	public ItemBuilder conjured() {
		name = "Conjured Mana Cake";
		return this;
	}

}
