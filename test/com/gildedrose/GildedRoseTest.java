package com.gildedrose;

import static com.gildedrose.ItemBuilder.anItem;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class GildedRoseTest {

	@Test
	void 하루_지나면_sellIn_감소() {
		int initialSellIn = 1;
		Item normalItem = anItem().sellIn(initialSellIn).build();
		dayAfter(normalItem);
		assertEquals(normalItem.sellIn, initialSellIn - 1);
	}

	

	private void dayAfter(Item concernedItem) {
		GildedRose app = new GildedRose(new Item[] { concernedItem });
		app.update();
	}

	@Test
	void 하루_지나면_Quality_감소() {
		int initialQuality = 1;
		Item normalItem = anItem().quality(initialQuality).build();
		dayAfter(normalItem);
		assertEquals(normalItem.quality, initialQuality - 1);
	}

	@Test
	void Quality는_음수가_되지_않는다() {
		int zeroQuality = 0;
		Item normalItem = anItem().quality(zeroQuality).build();
		dayAfter(normalItem);
		assertEquals(normalItem.quality, zeroQuality);
	}

	@Test
	void SellIn이_남아있지_않으면_Quality는_2배로_떨어진다() {
		int initialQuality = 2;
		Item normalItem = anItem().sellIn(0).quality(initialQuality).build();
		dayAfter(normalItem);
		assertEquals(normalItem.quality, initialQuality - 2);
	}

	@Test
	void 브리치즈는_다음날_Quality가_올라간다() {
		int initialQuality = 1;
		Item agedBrie = anItem().brie().quality(initialQuality).build();
		dayAfter(agedBrie);
		assertEquals(agedBrie.quality, initialQuality + 1);
	}

	@Test
	void Sulfuras의_Quality값은_80으로_고정() {
		int quality80 = 80;
		Item sulfuras = anItem().legendary().quality(quality80).build();
		dayAfter(sulfuras);
		assertEquals(sulfuras.quality, quality80);
	}

	@Test
	void 백스테이지_입장권은_다음날_Quality가_올라간다() {
		int initialQuality = 49;
		Item backstagePass = anItem().backstage().quality(initialQuality).build();
		dayAfter(backstagePass);
		assertEquals(backstagePass.quality, initialQuality + 1);
	}

	@Test
	void 백스테이지_입장권은_sellIn_10부터_다음날_Quality가_2씩_올라간다() {
		int initialQuality = 40;
		Item backstagePass = anItem().backstage().sellIn(10).quality(initialQuality).build();
		dayAfter(backstagePass);
		assertEquals(backstagePass.quality, initialQuality + 2);
	}

	@Test
	void 백스테이지_입장권은_sellIn_5부터_다음날_Quality가_3씩_올라간다() {
		int initialQuality = 40;
		Item backstagePass = anItem().backstage().sellIn(5).quality(initialQuality).build();
		dayAfter(backstagePass);
		assertEquals(backstagePass.quality, initialQuality + 3);
	}

	@Test
	void Quality_최대값은_50() {
		int initialQuality = 49;
		Item backstagePass = anItem().backstage().sellIn(5).quality(initialQuality).build();
		dayAfter(backstagePass);
		assertEquals(50, backstagePass.quality);
	}
	@Test
	void 백스테이지_입장권은_콘서트_종료_후_Quality가_0이_된다() {
		Item backstagePass = anItem().backstage().sellIn(0).build();
		dayAfter(backstagePass);
		assertEquals(backstagePass.quality, 0);
	}

	@Test
	void Conjured_아이템은_일반_아이템의_2배의_속도로_Quality가_떨어진다() {
		int initialQuality = 2;
		Item conjured = anItem().conjured().quality(initialQuality).build();
		dayAfter(conjured);
		assertEquals(conjured.quality, initialQuality - 2);
	}

	@Test
	void Conjured_아이템은_일반_아이템의_2배의_속도로_Quality가_떨어진다_만료() {
		int initialQuality = 4;
		Item conjured = anItem().conjured().sellIn(0).quality(initialQuality).build();
		dayAfter(conjured);
		assertEquals(conjured.quality, initialQuality - 4);
	}
}