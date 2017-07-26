package com.zxc74171.thaumicpotatoes.items;

import java.util.Set;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;


public class ItemMetaShovel extends ItemSpade {
	public ItemMetaShovel(Item.ToolMaterial material) {
		super(material);
	}
	
	@Override
	public Set<String> getToolClasses(ItemStack stack) {
	    return ImmutableSet.of("axe");
	}
	
	@SuppressWarnings({ "rawtypes", "unused" })
	private static Set effectiveAgainst = Sets.newHashSet(new Block[] {
		    Blocks.PLANKS, Blocks.BOOKSHELF, Blocks.LOG, Blocks.LOG2, 
		    Blocks.CHEST, Blocks.PUMPKIN, Blocks.LIT_PUMPKIN});
}
