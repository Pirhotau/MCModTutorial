package com.Pirhotau.Utils.Enum;

import net.minecraft.util.IStringSerializable;

public enum EnumLaserOperation implements IStringSerializable{
	RAKE("rake", 0),
	FUSION("fusion", 1);

	private int index;
	private String name;

	private EnumLaserOperation(String name, int index) {
		this.index = index;
		this.name = name;
	}

	@Override
	public String getName() {
		return this.name;
	}

	public int getIndex() {
		return index;
	}

	@Override
	public String toString() {
		return getName();
	}
	
	public static EnumLaserOperation cycle(EnumLaserOperation value) {
		switch(value) {
			case RAKE: return EnumLaserOperation.FUSION;
			case FUSION: return EnumLaserOperation.RAKE;
			default: return null;
		}
	}
}
