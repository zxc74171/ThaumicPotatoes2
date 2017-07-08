package com.zxc74171.thaumicpotatoes.handlers;

import com.zxc74171.thaumicpotatoes.ThaumicPotatoes2;
import com.zxc74171.thaumicpotatoes.init.ModBlocks;
import com.zxc74171.thaumicpotatoes.init.ModItems;
import com.zxc74171.thaumicpotatoes.util.Utils;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class SmeltingHandler {
	
	public static void registerSmeltingRecipes() {
        GameRegistry.addSmelting(ModItems.PETRIFIED_POTATO, new ItemStack((ModItems.THAUMIC_POTATO)), 1F);
        Utils.getLogger().info("Registering Furnace Recipes.");

    }
}
