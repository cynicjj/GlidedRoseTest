package com.gildedrose.exercise;

public class ItemHolder {

	private Item item;

	public ItemHolder(Item item) {
		this.item = item;
	}

	public static ItemHolder fromItem(Item item) {
		return new ItemHolder(item);
	}

	public int getSellIn() {
		return item.sellIn;
	}

	public void setSellIn(int sellIn) {
		item.sellIn = sellIn;
	}

	public int getQuality() {
		return item.quality;
	}

}
