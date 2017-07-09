package com.Pirhotau.ModTutorial.items;

import com.Pirhotau.ModTutorial.client.render.items.ItemRenderRegister;
import com.Pirhotau.ModTutorial.crafting.ModTutorialCreativeTab;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BasicItem extends Item {

	public BasicItem(String name) {
		super();
		setUnlocalizedName(name);
		setCreativeTab(ModTutorialCreativeTab.piCreativeTab);
		setMaxStackSize(64);

		this.setRegistryName(name);
		GameRegistry.register(this);
		ItemRenderRegister.addItemToRenderRegister(this);
	}
}
