package com.Pirhotau.ModTutorial.blocks.laserprinter;

import net.minecraft.util.IStringSerializable;

public enum EnumLaserPrinter implements IStringSerializable {
	BOTTOM("bottom", 0),
	TOP("top", 1);

	private int index;
	private String name;

	private EnumLaserPrinter(String name, int index) {
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