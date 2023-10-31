package com.gildedrose.exercise;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

class GildedRoseTest {

	protected void dayAfter(Item item) {
		Item[] items = new Item[] { item };
		GildedRose app = new GildedRose(items);
		app.update();
	}

	private ItemBuilder anItem() {
		return new ItemBuilder();
	}

	@Test
	void 하루가_지나면_sellIn_1감소() {
		int sellIn = 10;
		Item item = anItem().normal().sellIn(10).build();

		dayAfter(item);

		assertThat(item.sellIn, is(sellIn - 1));
	}

	@Test
	void 하루가_지나면_quality_1감소() {
		int quality = 20;
		Item item = anItem().normal().quality(quality).build();

		dayAfter(item);

		assertThat(item.quality, is(quality - 1));
	}

	@Test
	void 판매하는_나머지_일수가_없어지면_Quality_값은_2배로_떨어집니다() {
		int sellIn = 0;
		int quality = 20;
		Item item = anItem().normal().sellIn(sellIn).quality(quality).build();

		dayAfter(item);

		assertThat(item.quality, is(quality - 2));
	}

	@Test
	void Quality_값은_결코_음수가_되지는_않습니다() {
		int quality = 0;
		Item item = anItem().normal().quality(quality).build();

		dayAfter(item);

		assertThat(item.quality, greaterThanOrEqualTo(0));
	}

	@Test
	void AgedBrie_오래된_브리치즈는_시간이_지날수록_Quality_값이_올라갑니다() {
		int quality = 0;
		Item brie = anItem().agedBrie().quality(quality).build();

		dayAfter(brie);

		assertThat(brie.quality, is(quality + 1));
	}

	@Test
	void Quality_값은_50을_초과_할_수_없습니다() {
		int quality = 50;
		Item brie = anItem().agedBrie().quality(quality).build();

		dayAfter(brie);

		assertThat(brie.quality, is(quality));
	}

	@Test
	void Sulfuras는_전설의_아이템이므로_반드시_판매될_필요도_없고_Quality_값도_떨어지지_않습니다() {
		int quality = 100;
		Item legendary = anItem().legendary().quality(quality).build();

		dayAfter(legendary);

		assertThat(legendary.quality, is(quality));
	}

	@Test
	void 백스테이지_입장권은_남은_날이_10일_초과면_Quality_값이_1상승() {
		int sellIn = 12;
		int quality = 20;
		Item backstagePass = anItem().backstagePass().sellIn(sellIn).quality(quality).build();

		dayAfter(backstagePass);

		assertThat(backstagePass.quality, is(quality + 1));
	}
	
	@Test
	void 백스테이지_입장권은_남은날이_5일에서_10일_사이면__Quality_값이_2상승() {
		int sellIn = 7;
		int quality = 20;
		Item backstagePass = anItem().backstagePass().sellIn(sellIn).quality(quality).build();
		
		dayAfter(backstagePass);
		
		assertThat(backstagePass.quality, is(quality + 2));
	}

	@Test
	void 백스테이지_입장권은_남은날이_0일에서_5일_사이면__Quality_값이_3상승() {
		int sellIn = 1;
		int quality = 20;
		Item backstagePass = anItem().backstagePass().sellIn(sellIn).quality(quality).build();

		dayAfter(backstagePass);

		assertThat(backstagePass.quality, is(quality + 3));
	}
	
	@Test
	void 백스테이지_입장권은_콘서트_종료_후_Quality_값이_0() {
		int sellIn = 0;
		int quality = 20;
		Item backstagePass = anItem().backstagePass().sellIn(sellIn).quality(quality).build();
		
		dayAfter(backstagePass);
		
		assertThat(backstagePass.quality, is(0));
	}

	/*
	 **10일 부터는** 매일 *2* 씩 증가하다, **5일 부터는**이 되면 매일 *3* 씩 증가하지만, 콘서트 종료 후에는 *0*으로 떨어집니다.
	
	- "**Conjured**" 아이템은 일반 아이템의 2배의 속도로 품질(`Quality`)이 저하됩니다.
	다시 한 번 확인하자면, 아이템의 `Quality`는 50 이상으로 증가할 수는 없습니다. 하지만 `Sulfuras`는 전설의 아이템이기 때문에 `Quality` 값은 80이며, 값이 바뀌지 않습니다.
	
	`Item` 클래스와 `Items` 속성은 변경하지 마세요.
	*/
}
