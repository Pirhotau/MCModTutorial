package com.Pirhotau.Utils.Enum;

import net.minecraft.util.IStringSerializable;

public enum EnumHalf implements IStringSerializable {
	BOTTOM("bottom", 0),
	TOP("top", 1);

	private int index;
	private String name;

	private EnumHalf(String name, int index) {
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
}