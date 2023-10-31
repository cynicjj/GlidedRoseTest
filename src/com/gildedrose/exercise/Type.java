package com.gildedrose.exercise;

public enum Type {
	AGED_BRIE("Aged Brie"),
	LEGENDARY("Sulfuras, Hand of Ragnaros"),
	BACKSTAGE_PASS("Backstage passes to a TAFKAL80ETC concert"),
	NORMAL("normal")
	;
	
	final String name;

	Type(String name) {
		this.name = name;
	}
}
