package com.zxc74171.thaumicpotatoes.handlers;

import com.zxc74171.thaumicpotatoes.init.ModBlocks;
import com.zxc74171.thaumicpotatoes.init.ModItems;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class RegistryHandler {



	@SubscribeEvent
	public void registerItems(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(ModItems.ITEMS);
		for (int i = 0; i < ModItems.ITEMS.length; i++) {
			Item item = ModItems.ITEMS[i];
			ModelLoader.setCustomModelResourceLocation(item, 0,
					new ModelResourceLocation(item.getRegistryName(), "inventory"));
		}
	}

	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent event) {
		
		

		for (int i = 0; i < ModItems.ITEMS.length; i++) {
			Item item = ModItems.ITEMS[i];
			ModelLoader.setCustomModelResourceLocation(item, 0,
					new ModelResourceLocation(item.getRegistryName(), "inventory"));
		}
	}

}