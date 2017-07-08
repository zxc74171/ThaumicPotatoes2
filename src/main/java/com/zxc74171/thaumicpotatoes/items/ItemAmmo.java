package com.zxc74171.thaumicpotatoes.items;



import com.zxc74171.thaumicpotatoes.potatolauncher.Ammos;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemAmmo extends Item {
	public Ammos ammo;
	
	public ItemAmmo(String name, CreativeTabs creativeTab, Ammos ammo){
		this.ammo = ammo;
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.setCreativeTab(creativeTab);
	}

    public boolean isInfinite(ItemStack stack, ItemStack crossbow, net.minecraft.entity.player.EntityPlayer player)
    {
        int enchant = net.minecraft.enchantment.EnchantmentHelper.getEnchantmentLevel(net.minecraft.init.Enchantments.INFINITY, crossbow);
        return enchant <= 0 ? false : this.getClass() == ItemAmmo.class;
    }

	public Ammos getAmmo() {
		return this.ammo;
	}

}
