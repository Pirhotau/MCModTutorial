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
	
	private void toggle() {
		switch(this) {
			case LEFT: {
				this.setValue(RIGHT);
				break;
			}
			case RIGHT: {
				this.setValue(LEFT);
				break;
			}
		}
	}
	
	private void setValue(EnumLRSide side) {
		this.name = side.getName();
		this.index = side.getIndex();
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
