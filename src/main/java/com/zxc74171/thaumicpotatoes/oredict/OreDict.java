package com.zxc74171.thaumicpotatoes.oredict;

import com.zxc74171.thaumicpotatoes.ThaumicPotatoes2;
import com.zxc74171.thaumicpotatoes.init.ModItems;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;

public class OreDict {
	

	public static void init() {
		addOreDictEntries();
	}
	
	
	private static void addOreDictEntries() {
		OreDictionary.registerOre("ingotPotato", new ItemStack(ModItems.POTATO_ALLOY));
		OreDictionary.registerOre("record", new ItemStack(ModItems.RECORD_FAREWELL));
		OreDictionary.registerOre("orePotato", new ItemStack(ThaumicPotatoes2.blocks.POTATOORE));
		
		//That's wicked
		OreDictionary.registerOre("dontLewdTheDragonLoli", new ItemStack(Items.COOKED_BEEF));
		OreDictionary.registerOre("dontLewdTheDragonLoli", new ItemStack(Items.COOKED_CHICKEN));
		OreDictionary.registerOre("dontLewdTheDragonLoli", new ItemStack(Items.COOKED_FISH));
		OreDictionary.registerOre("dontLewdTheDragonLoli", new ItemStack(Items.COOKED_MUTTON));
		OreDictionary.registerOre("dontLewdTheDragonLoli", new ItemStack(Items.COOKED_PORKCHOP));
		OreDictionary.registerOre("dontLewdTheDragonLoli", new ItemStack(Items.COOKED_RABBIT));
}
}
