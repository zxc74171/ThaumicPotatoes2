package com.zxc74171.thaumicpotatoes.proxy;

import javax.annotation.Nonnull;

import com.zxc74171.thaumicpotatoes.handlers.GuiHandler;
import com.zxc74171.thaumicpotatoes.init.ModEntities;
import com.zxc74171.thaumicpotatoes.items.ItemTruth;
import com.zxc74171.thaumicpotatoes.worldgen.TP2WorldGen;
import com.zxc74171.thaumicpotatoes.ThaumicPotatoes2;
import com.zxc74171.thaumicpotatoes.handlers.FuelHandler;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@SuppressWarnings("deprecation")
public class CommonProxy{

	public String translateToLocal(@Nonnull String key, Object... args) {
		
		return I18n.translateToLocal(key);
	}
	
	
    public void preInit(FMLPreInitializationEvent e) {
    	ModEntities.init();
    }
    
    public void registerItemModel(Item item, int meta, String id) {

	}

	public void registerItemModel(Block block, int meta, String id) {
		registerItemModel(Item.getItemFromBlock(block), meta, id);
	}


}



