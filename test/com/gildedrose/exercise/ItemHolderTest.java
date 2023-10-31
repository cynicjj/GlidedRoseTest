package com.gildedrose.exercise;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

class ItemHolderTest {

	@Test
	void 생성_테스트() {
		new ItemHolder(new Item("name", 1, 1));
		ItemHolder.fromItem(new Item("name", 1, 1));
	}

	@Test
	void setSellInTest() {
		int newSellIn = 9;
		ItemHolder item = createItem();

		item.setSellIn(newSellIn);

		assertThat(item.getSellIn(), is(newSellIn));
	}
	
	@Test
	void setQualityTest() {
		int newQuality = 26;
		ItemHolder item = createItem();
		
		item.setQuality(newQuality);
		
		assertThat(item.getQuality(), is(newQuality));
	}

	private ItemHolder createItem() {
		return ItemHolder.fromItem(new Item("name", 1, 1));
	}

}
