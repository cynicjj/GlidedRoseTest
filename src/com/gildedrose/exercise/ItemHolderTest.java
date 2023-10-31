package com.gildedrose.exercise;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.jupiter.api.Test;

class ItemHolderTest {

	@Test
	void 생성_테스트() {
		ItemHolder item = new ItemHolder(new Item("name", 1, 1));
		assertThat(item, notNullValue());

		item = ItemHolder.fromItem(new Item("name", 1, 1));
		assertThat(item, notNullValue());
	}

	private ItemHolder createItem() {
		return ItemHolder.fromItem(new Item("name", 73, 73));
	}

	@Test
	void testGetSellIn() {
		ItemHolder item = createItem();
		assertThat(item.getSellIn(), is(73));
	}

	@Test
	void testSetSellIn() {
		ItemHolder item = createItem();

		int sellIn = 21;
		item.setSellIn(sellIn);
		assertThat(item.getSellIn(), is(sellIn));
	}

	@Test
	void testGetQuality() {
		ItemHolder item = createItem();
		assertThat(item.getQuality(), is(73));
	}
}
