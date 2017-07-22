package com.Pirhotau.Utils.Enum;

public enum EnumLRSide {
	LEFT("left", 0),
	RIGHT("right", 1);
	
	private String name;
	private int index;
	
	private EnumLRSide(String name, int index) {
		this.name = name;
		this.index = index;
	}
	
	public static EnumLRSide cycle(EnumLRSide value) {
		switch(value) {
			case LEFT: return EnumLRSide.RIGHT;
			case RIGHT: return EnumLRSide.LEFT;
			default: return null;
		}
	}
	
	private int getIndex() {
		return index;
	}
	
	private String getName() {
		return name;
	}
	
	public String toString() {
		return this.getName();
	}
}
