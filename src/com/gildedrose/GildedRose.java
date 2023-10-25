package com.gildedrose;

class GildedRose {
	Item[] items;

	public GildedRose(Item[] items) {
		this.items = items;
	}

	public void update() {
		for (int i = 0; i < items.length; i++) {
			updateItem(items[i]);
		}
	}

	// 전설, 백스테이지, 브리, 그 외 노멀
	private void updateItem(Item item) {
		updateSellIn(item);
		updateQuality(item);
	}

	private void updateQuality(Item item) {
		final boolean isAgedBrie = item.name.equals("Aged Brie");
		final boolean isBackstagePass = item.name.equals("Backstage passes to a TAFKAL80ETC concert");
		final boolean isLegendary = item.name.equals("Sulfuras, Hand of Ragnaros");
		final boolean isConjured = item.name.equals("Conjured Mana Cake");

		if (isLegendary) { // 전설 아이템은 퀄리티 80 고정
			updateLegendary(item);
		} else if (isAgedBrie) { // 브리 치즈
			updateAgedBrie(item);
		} else if (isBackstagePass) { // 백 스테이지 패스
			updateBackstagePass(item);
		} else if (isConjured) { // 마법 아이템
			updateConjured(item);
		} else { // 그 외 노멀 케이스
			updateNormal(item);
		}
	}

	private void updateConjured(Item item) {
		final boolean isExpired = item.sellIn < 0;
		if (isExpired) { // 만료일 지나면
			decreaseQuality(item, 4);
		} else {
			decreaseQuality(item, 2);
		}
	}

	private void updateNormal(Item item) {
		final boolean isExpired = item.sellIn < 0;
		if (isExpired) { // 만료일 지나면
			decreaseQuality(item, 2);
		} else {
			decreaseQuality(item, 1);
		}
	}

	private void decreaseQuality(Item item, int value) {
		// quality는 음수가 아니다
		item.quality = Math.max(item.quality - value, 0);
	}

	private void updateBackstagePass(Item item) {
		final boolean isEnded = item.sellIn < 0; // 공연 끝
		final boolean is0to5 = 0 <= item.sellIn && item.sellIn <= 5; // 공연 당일~5일 전
		final boolean is6to10 = 6 <= item.sellIn && item.sellIn <= 10; // 공연 6~10일 전

		if (isEnded) {
			item.quality = 0;
		} else if (is0to5) {
			increaseQuality(item, 3);
		} else if (is6to10) {
			increaseQuality(item, 2);
		} else {
			increaseQuality(item, 1);
		}
	}

	private void updateAgedBrie(Item item) {
		increaseQuality(item, 1);
	}

	private void updateLegendary(Item item) {
		item.quality = 80;
	}

	private void increaseQuality(Item item, int value) {
		// 퀄리티 최대값 50, 전설 제외
		item.quality = Math.min(item.quality + value, 50);
	}

	private void updateSellIn(Item item) {
		item.sellIn--;
	}
}