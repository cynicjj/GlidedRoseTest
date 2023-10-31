package com.gildedrose;

class GildedRoseRefactored {
	private static final int QUALITY_MAX = 50;
	private static final int QUALITY_LEGENDARY = 80;

	public static final String NAME_AGED_BRIE = "Aged Brie";
	public static final String NAME_BACKSTAGE_PASS = "Backstage passes to a TAFKAL80ETC concert";
	public static final String NAME_LEGENDARY = "Sulfuras, Hand of Ragnaros";
	public static final String NAME_CONJURED = "Conjured Mana Cake";

	private Item[] items;

	public GildedRoseRefactored(Item[] items) {
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
		final boolean isAgedBrie = item.name.equals(NAME_AGED_BRIE);
		final boolean isBackstagePass = item.name.equals(NAME_BACKSTAGE_PASS);
		final boolean isLegendary = item.name.equals(NAME_LEGENDARY);
		final boolean isConjured = item.name.equals(NAME_CONJURED);

		if (isLegendary)
			updateQualityLegendary(item);
		else if (isAgedBrie)
			updateQualityAgedBrie(item);
		else if (isBackstagePass)
			updateQualityBackstagePass(item);
		else if (isConjured)
			updateQualityConjured(item);
		else
			updateQualityNormal(item);

	}

	//"Conjured" 아이템은 일반 아이템의 2배의 속도로 품질(Quality)이 저하됩니다
	private void updateQualityConjured(Item item) {
		updateItemByNormalStandard(item);
		updateItemByNormalStandard(item);
	}

	private void updateQualityNormal(Item item) {
		updateItemByNormalStandard(item);
	}

	private void updateItemByNormalStandard(Item item) {
		final boolean isExpired = item.sellIn < 0;
		int decrease = isExpired ? 2 : 1;
		decreaseQuality(item, decrease);
	}

	private void decreaseQuality(Item item, int value) {
		// quality 는 음수가 아니다
		item.quality = Math.max(item.quality - value, 0);
	}

	private void updateQualityBackstagePass(Item item) {
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

	private void updateQualityAgedBrie(Item item) {
		increaseQuality(item, 1);
	}

	private void updateQualityLegendary(Item item) {
		item.quality = QUALITY_LEGENDARY;
	}

	private void increaseQuality(Item item, int value) {
		item.quality = Math.min(item.quality + value, QUALITY_MAX);
	}
}