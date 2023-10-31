package com.gildedrose.exercise;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import static com.gildedrose.exercise.GildedRose.*;

class GildedRoseTest {

	private void dayAfter(ItemHolder item) {
		Item[] items = new Item[] { item.getItem() };
		new GildedRose(items).updateQuality();
	}

	@Test
	void 하루가_지나면_SellIn_1감소() {
		int sellIn = 10;
		ItemHolder item = anItem().sellIn(sellIn).build();

		dayAfter(item);

		assertThat(item.getSellIn(), is(sellIn - 1));
	}

	private ItemBuilder anItem() {
		return new ItemBuilder();
	}

	@Test
	void 하루가_지나면_Quality_1감소() {
		int quality = 20;
		ItemHolder item = anItem().quality(quality).build();

		dayAfter(item);

		assertThat(item.getQuality(), is(quality - 1));
	}

	@Test
	void 판매하는_나머지_일수가_없어지면_Quality_값은_2배로_떨어집니다() {
		int zero = 0;
		int quality = 20;
		ItemHolder item = anItem().sellIn(zero).quality(quality).build();

		dayAfter(item);

		assertThat(item.getQuality(), is(quality - 2));
	}

	@Test
	void Quality_값은_결코_음수가_되지는_않습니다() {
		int zero = 0;
		ItemHolder item = anItem().quality(zero).build();

		dayAfter(item);

		assertThat(item.getQuality(), is(zero));
	}

	@Test
	void Aged_Brie_오래된_브리치즈는_시간이_지날수록_Quality_값이_올라갑니다() {
		int quality = 3;
		ItemHolder brie = anItem().name(AGED_BRIE).quality(quality).build();

		dayAfter(brie);

		assertThat(brie.getQuality(), is(quality + 1));
	}

	@Test
	void Quality_값은_50를_초과_할_수_없습니다() {
		int qualityLimit = 50;
		ItemHolder item = anItem().name(AGED_BRIE).quality(qualityLimit).build();

		dayAfter(item);

		assertThat(item.getQuality(), is(qualityLimit));
	}

	@Test
	void 전설_아이템_Quality는_80_고정() {
		int quality = 80;
		ItemHolder item = anItem().name(LEGENDARY).quality(quality).build();

		dayAfter(item);

		assertThat(item.getQuality(), is(quality));
	}

	@Test
	void backstagePass는_남은_일수가_11이상일_때_quality_1증가() {
		int sellIn = 15;
		int quality = 23;
		ItemHolder item = anItem().name(BACKSTAGE_PASS).sellIn(sellIn).quality(quality).build();

		dayAfter(item);

		assertThat(item.getQuality(), is(quality + 1));
	}

	@Test
	void backstagePass는_남은_일수가_5일에서_10일_사이일_때_quality_2증가() {
		int sellIn = 6;
		int quality = 23;
		ItemHolder item = anItem().name(BACKSTAGE_PASS).sellIn(sellIn).quality(quality).build();

		dayAfter(item);

		assertThat(item.getQuality(), is(quality + 2));
	}

	@Test
	void backstagePass는_남은_일수가_4일부터_quality_3증가() {
		int sellIn = 5;
		int quality = 23;
		ItemHolder item = anItem().name(BACKSTAGE_PASS).sellIn(sellIn).quality(quality).build();

		dayAfter(item);

		assertThat(item.getQuality(), is(quality + 3));
	}

	@Test
	void 콘서트_종료_후_quality_0() {
		int sellIn = 0;
		ItemHolder item = anItem().name(BACKSTAGE_PASS).sellIn(sellIn).build();

		dayAfter(item);

		assertThat(item.getQuality(), is(0));
	}

	@Ignore
	void Conjured_마법에_걸린_아이템은_일반_아이템의_2배의_속도로_품질Quality이_저하됩니다() {
		int quality = 22;
		ItemHolder item = anItem().name(CONJURED).quality(quality).build();

		dayAfter(item);

		assertThat(item.getQuality(), is(quality - 2));
	}
}
