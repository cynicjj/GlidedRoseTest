package com.gildedrose.exercise;

import static com.gildedrose.exercise.Type.*;

public class ItemBuilder {

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

	public ItemBuilder agedBrie() {
		name = AGED_BRIE.name;
		return this;
	}

	public ItemBuilder legendary() {
		name = LEGENDARY.name;
		return this;
	}

	public ItemBuilder normal() {
		name = NORMAL.name;
		return this;
	}

	public ItemBuilder backstagePass() {
		name = BACKSTAGE_PASS.name;
		return this;
	}

}
