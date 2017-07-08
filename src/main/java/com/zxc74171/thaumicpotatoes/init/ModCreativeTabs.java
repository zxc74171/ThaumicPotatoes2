package com.zxc74171.thaumicpotatoes.init;

import java.awt.List;

import com.zxc74171.thaumicpotatoes.Reference;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ModCreativeTabs extends CreativeTabs{
	
	public static ModCreativeTabs TABS_TP2 = new ModCreativeTabs();
	List list;
	
	public ModCreativeTabs() {
		super(Reference.MODID);
		
	}

	@Override
	public ItemStack getTabIconItem() {
		// TODO Auto-generated method stub
		return new ItemStack(ModItems.POTAINTO);
	}
}
