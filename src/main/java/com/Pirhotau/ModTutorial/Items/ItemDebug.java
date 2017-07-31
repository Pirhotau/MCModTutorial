package com.Pirhotau.ModTutorial.Items;

import com.Pirhotau.ModTutorial.Crafting.ModTutorialCreativeTab;
import com.Pirhotau.ModTutorial.client.render.items.ItemRenderRegister;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public final class ItemDebug extends Item {
	
	public static final String name = "debug";
	
	public ItemDebug() {
		super();
		setUnlocalizedName(name);
		setCreativeTab(ModTutorialCreativeTab.piCreativeTab);
		setMaxStackSize(1);
		
		this.setRegistryName(name);
		GameRegistry.register(this);
		ItemRenderRegister.addItemToRenderRegister(this);
	}
	
	@Override
	public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, EntityPlayer player) {
		return true;
	}
	
	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if(!worldIn.isRemote) {
			String msg = new String();
			
			msg += worldIn.getBlockState(pos).getBlock().getLocalizedName();
			playerIn.addChatComponentMessage(new TextComponentString(msg));
		}
		return super.onItemUse(stack, playerIn, worldIn, pos, hand, facing, hitX, hitY, hitZ);
	}
	
}
