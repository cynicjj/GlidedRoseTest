package com.gildedrose;

import static com.gildedrose.ItemBuilder.anItem;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class GildedRoseTest {

	@Test
	void 하루_지나면_sellIn_감소() {
		int sellIn = 1;
		Item normal = anItem().sellIn(sellIn).build();
		dayAfter(normal);
		assertEquals(sellIn - 1,normal.sellIn);
	}

	private void dayAfter(Item concernedItem) {
		GildedRose app = new GildedRose(new Item[] { concernedItem });
		app.updateQuality();
	}

	@Test
	void 하루_지나면_Quality_감소() {
		int quality = 1;
		Item normal = anItem().quality(quality).build();
		dayAfter(normal);
		assertEquals(quality - 1, normal.quality);
	}

	@Test
	void Quality는_음수가_되지_않는다() {
		int zero = 0;
		Item normal = anItem().quality(zero).build();
		dayAfter(normal);
		assertEquals(zero, normal.quality);
	}

	@Test
	void SellIn이_남아있지_않으면_Quality는_2배로_떨어진다() {
		int quality = 2;
		Item normal = anItem().sellIn(0).quality(quality).build();
		dayAfter(normal);
		assertEquals(quality - 2, normal.quality);
	}

	@Test
	void 브리치즈는_다음날_Quality가_올라간다() {
		int quality = 1;
		Item agedBrie = anItem().brie().quality(quality).build();
		dayAfter(agedBrie);
		assertEquals(quality + 1, agedBrie.quality);
	}

	@Test
	void Sulfuras의_Quality값은_80으로_고정() {
		int quality80 = 80;
		Item legendary = anItem().legendary().quality(quality80).build();
		dayAfter(legendary);
		assertEquals(quality80, legendary.quality);
	}

	@Test
	void 백스테이지_입장권은_다음날_Quality가_올라간다() {
		int quality = 49;
		Item backstagePass = anItem().backstage().quality(quality).build();
		dayAfter(backstagePass);
		assertEquals(quality + 1, backstagePass.quality);
	}

	@Test
	void 백스테이지_입장권은_sellIn_10부터_다음날_Quality가_2씩_올라간다() {
		int quality = 40;
		Item backstagePass = anItem().backstage().sellIn(10).quality(quality).build();
		dayAfter(backstagePass);
		assertEquals(quality + 2, backstagePass.quality);
	}

	@Test
	void 백스테이지_입장권은_sellIn_5부터_다음날_Quality가_3씩_올라간다() {
		int quality = 40;
		Item backstagePass = anItem().backstage().sellIn(5).quality(quality).build();
		dayAfter(backstagePass);
		assertEquals(quality + 3, backstagePass.quality);
	}

	@Test
	void Quality_최대값은_50() {
		int quality = 49;
		Item backstagePass = anItem().backstage().sellIn(5).quality(quality).build();
		dayAfter(backstagePass);
		assertEquals(50, backstagePass.quality);
	}

	@Test
	void 백스테이지_입장권은_콘서트_종료_후_Quality가_0이_된다() {
		Item backstagePass = anItem().backstage().sellIn(0).build();
		dayAfter(backstagePass);
		assertEquals(0, backstagePass.quality);
	}

//	@Test
	void Conjured_아이템은_일반_아이템의_2배의_속도로_Quality가_떨어진다() {
		int quality = 2;
		Item conjured = anItem().conjured().quality(quality).build();
		dayAfter(conjured);
		assertEquals(quality - 2, conjured.quality);
	}

//	@Test
	void Conjured_아이템은_일반_아이템의_2배의_속도로_Quality가_떨어진다_만료() {
		int quality = 4;
		Item conjured = anItem().conjured().sellIn(0).quality(quality).build();
		dayAfter(conjured);
		assertEquals(quality - 4, conjured.quality);
	}
}