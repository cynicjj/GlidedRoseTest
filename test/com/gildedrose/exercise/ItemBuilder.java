package com.gildedrose.exercise;

public class ItemBuilder {

	private String name = "no name";
	private int sellIn = 73;
	private int quality = 73;

	public ItemBuilder name(String name) {
		this.name = name;
		return this;
	}

	public ItemBuilder sellIn(int sellIn) {
		this.sellIn = sellIn;
		return this;
	}

	public ItemBuilder quality(int quality) {
		this.quality = quality;
		return this;
	}

	public ItemHolder build() {
		return ItemHolder.fromItem(new Item(name, sellIn, quality));
	}

}
