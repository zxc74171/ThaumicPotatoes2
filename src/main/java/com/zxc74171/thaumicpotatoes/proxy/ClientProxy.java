package com.zxc74171.thaumicpotatoes.proxy;

import com.zxc74171.thaumicpotatoes.Reference;
import com.zxc74171.thaumicpotatoes.init.ModBlocks;
import com.zxc74171.thaumicpotatoes.init.ModEntities;
import com.zxc74171.thaumicpotatoes.init.ModItems;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;


public class ClientProxy extends CommonProxy {

	@Override
	public void registerItemModel(Item item, int meta, String id) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(Reference.MODID + ":" + id, "inventory"));
	}
	public void preInit(FMLPreInitializationEvent e) {
	
	}
}
