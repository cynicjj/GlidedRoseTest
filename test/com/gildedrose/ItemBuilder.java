package com.gildedrose;

class ItemBuilder {

	private String name = "no name";
	private int sellIn = 73;
	private int quality = 73;

	public static ItemBuilder anItem() {
		return new ItemBuilder();
	}

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
		name = GildedRoseRefactored.NAME_AGED_BRIE;
		return this;
	}

	public ItemBuilder legendary() {
		name = GildedRoseRefactored.NAME_LEGENDARY;
		return this;
	}

	public ItemBuilder backstage() {
		name = GildedRoseRefactored.NAME_BACKSTAGE_PASS;
		return this;
	}

	public ItemBuilder conjured() {
		name = GildedRoseRefactored.NAME_CONJURED;
		return this;
	}

}
