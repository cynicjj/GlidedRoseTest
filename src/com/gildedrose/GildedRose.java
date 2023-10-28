package com.gildedrose;

class GildedRose {
	private static final int QUALITY_MAX = 50;
	private static final int QUALITY_LEGENDARY = 80;

	private Item[] items;

	public GildedRose(Item[] items) {
		this.items = items;
	}

	public void update() {
		for (int i = 0; i < items.length; i++)
			updateItem(items[i]);
	}

	private void updateItem(Item item) {
		updateSellIn(item);
		updateQuality(item);
	}

	private void updateSellIn(Item item) {
		item.sellIn--;
	}

	private void updateQuality(Item item) {
		final boolean isAgedBrie = item.name.equals("Aged Brie");
		final boolean isBackstagePass = item.name.equals("Backstage passes to a TAFKAL80ETC concert");
		final boolean isLegendary = item.name.equals("Sulfuras, Hand of Ragnaros");
		final boolean isConjured = item.name.equals("Conjured Mana Cake");

		if (isLegendary)
			updateLegendary(item);
		else if (isAgedBrie)
			updateAgedBrie(item);
		else if (isBackstagePass)
			updateBackstagePass(item);
		else if (isConjured)
			updateConjured(item);
		else
			updateNormal(item);

	}

	private void updateConjured(Item item) {
		final boolean isExpired = item.sellIn < 0;
		int decrease = isExpired ? 4 : 2;
		decreaseQuality(item, decrease);
	}

	private void updateNormal(Item item) {
		final boolean isExpired = item.sellIn < 0;
		int decrease = isExpired ? 2 : 1;
		decreaseQuality(item, decrease);
	}

	private void decreaseQuality(Item item, int value) {
		// quality 는 음수가 아니다
		item.quality = Math.max(item.quality - value, 0);
	}

	private void updateBackstagePass(Item item) {
		final boolean isEnded = item.sellIn < 0; // 공연 끝
		final boolean is0to5 = 0 <= item.sellIn && item.sellIn <= 5; // 공연 당일~5일 전
		final boolean is6to10 = 6 <= item.sellIn && item.sellIn <= 10; // 공연 6~10일 전

		if (isEnded)
			item.quality = 0;
		else if (is0to5)
			increaseQuality(item, 3);
		else if (is6to10)
			increaseQuality(item, 2);
		else
			increaseQuality(item, 1);

	}

	private void updateAgedBrie(Item item) {
		increaseQuality(item, 1);
	}

	private void updateLegendary(Item item) {
		item.quality = QUALITY_LEGENDARY;
	}

	private void increaseQuality(Item item, int value) {
		item.quality = Math.min(item.quality + value, QUALITY_MAX);
	}
}