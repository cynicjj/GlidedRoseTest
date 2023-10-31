package com.gildedrose.exercise;

import static com.gildedrose.exercise.Type.*;

class GildedRose {

	Item[] items;

	public GildedRose(Item[] items) {
		this.items = items;
	}

	public void update() {
		for (Item item : items) {
			updateSellIn(item);
			updateQuality(item);
		}
	}

	private void updateSellIn(Item item) {
		setSellIn(item, getSellIn(item) - 1);
	}

	private void setSellIn(Item item, int newSellIn) {
		item.sellIn = newSellIn;
	}

	protected void updateQuality(Item item) {
		if (isLegendary(item))
			return;
		else if (isAgedBrie(item)) {
			increaseQuality(item);
		} else if (isBackstagePass(item)) {
			updateBackStagePass(item);
		} else { // normal
			decreaseQuality(item);
			if (getSellIn(item) < 0)
				decreaseQuality(item); // 만기 지나면 한번 더 낮춘다
		}
	}

	protected void updateBackStagePass(Item item) {
		increaseQuality(item);

		if (getSellIn(item) < 11) {
			increaseQuality(item);
		}

		if (getSellIn(item) < 6) {
			increaseQuality(item);
		}

		if (getSellIn(item) < 0) {
			setQuality(item, 0);
		}
	}

	protected void decreaseQuality(Item item) {
		if (getQuality(item) > 0) {
			setQuality(item, getQuality(item) - 1);
		}
	}

	protected void increaseQuality(Item item) {
		setQuality(item, Math.min(50, getQuality(item) + 1));
	}

	private void setQuality(Item item, int newQuality) {
		item.quality = newQuality;
	}

	private int getQuality(Item item) {
		return item.quality;
	}

	private int getSellIn(Item item) {
		return item.sellIn;
	}

	protected boolean isBackstagePass(Item item) {
		return item.name.equals(BACKSTAGE_PASS.name);
	}

	protected boolean isAgedBrie(Item item) {
		return item.name.equals(AGED_BRIE.name);
	}

	private boolean isLegendary(Item item) {
		return item.name.equals(LEGENDARY.name);
	}
}