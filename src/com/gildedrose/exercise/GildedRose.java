package com.gildedrose.exercise;

class GildedRose {

	static final String AGED_BRIE = "Aged Brie";
	static final String BACKSTAGE_PASS = "Backstage passes to a TAFKAL80ETC concert";
	static final String LEGENDARY = "Sulfuras, Hand of Ragnaros";
	static final String CONJURED = "Conjured Mana Cake";

	static final int QUALITY_MAX = 50;

	Item[] items;

	public GildedRose(Item[] items) {
		this.items = items;
	}

	public void updateQuality() {
		for (Item item : items) {
//			updateSellIn(item);
			updateQuality(item);
		}
	}

	private void updateSellIn(Item item) {
		// TODO Auto-generated method stub

	}

	private void updateQuality(Item item) {
		if (isLegendary(item)) {
			setSellIn(item, getSellIn(item) - 1);
			return;
		}

		if (not(isAgedBrie(item)) && not(isBackstagePass(item))) {
			if (getQuality(item) > 0) {
				setQuality(item, getQuality(item) - 1);
			}
		} else {
			if (getQuality(item) < QUALITY_MAX) {
				setQuality(item, getQuality(item) + 1);

				if (isBackstagePass(item)) {
					if (getSellIn(item) < 11) {
						if (getQuality(item) < QUALITY_MAX) {
							setQuality(item, getQuality(item) + 1);
						}
					}

					if (getSellIn(item) < 6) {
						if (getQuality(item) < QUALITY_MAX) {
							setQuality(item, getQuality(item) + 1);
						}
					}
				}
			}
		}

		setSellIn(item, getSellIn(item) - 1);

		if (getSellIn(item) < 0) {
			if (not(isAgedBrie(item))) {
				if (not(isBackstagePass(item))) {
					if (getQuality(item) > 0) {
							setQuality(item, getQuality(item) - 1);
					}
				} else {
					setQuality(item, 0);
				}
			} else {
				if (getQuality(item) < QUALITY_MAX) {
					setQuality(item, getQuality(item) + 1);
				}
			}
		}
	}

	private boolean isBackstagePass(Item item) {
		return item.name.equals(BACKSTAGE_PASS);
	}

	private boolean isAgedBrie(Item item) {
		return item.name.equals(AGED_BRIE);
	}

	private boolean isLegendary(Item item) {
		return item.name.equals(LEGENDARY);
	}

	private void setSellIn(Item item, int newSellIn) {
		item.sellIn = newSellIn;
	}

	private int getSellIn(Item item) {
		return item.sellIn;
	}

	private void setQuality(Item item, int newQuality) {
		item.quality = newQuality;
	}

	private int getQuality(Item item) {
		return item.quality;
	}

	private boolean not(boolean bool) {
		return !bool;
	}
}