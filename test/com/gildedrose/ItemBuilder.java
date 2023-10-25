package com.gildedrose;

class ItemBuilder {

	private String itemName = "";
	private int itemSellIn = 23;
	private int itemQuality = 23;
	
	public ItemBuilder sellIn(int sellIn) {
		itemSellIn = sellIn;
		return this;
	}

	public ItemBuilder quality(int quality) {
		itemQuality = quality;
		return this;
	}

	public Item build() {
		return new Item(itemName, itemSellIn, itemQuality);
	}

	public ItemBuilder brie() {
		itemName = "Aged Brie";
		return this;
	}

	public ItemBuilder sulfuras() {
		itemName = "Sulfuras, Hand of Ragnaros";
		return this;
	}

	public ItemBuilder backstage() {
		itemName = "Backstage passes to a TAFKAL80ETC concert";
		return this;
	}

	public ItemBuilder conjured() {
		itemName = "Conjured Mana Cake";
		return this;
	}

}
