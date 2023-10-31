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

	public void setSellIn(int newSellIn) {
		item.sellIn = newSellIn;
	}

	public void setQuality(int newQuality) {
		item.quality = newQuality;
	}

	public Object getQuality() {
		return item.quality;
	}

	public Item getItem() {
		return item;
	}

}
