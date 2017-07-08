package com.zxc74171.thaumicpotatoes.potatolauncher;

import com.zxc74171.thaumicpotatoes.init.ModItems;

import net.minecraft.item.Item;

public enum Ammos {
	
	NORMAL(2.0d, ModItems.POTATO_AMMO, "thaumicpotatoes:textures/entity/potatouniversal.png",3),
	POISONOUS(1.5d, ModItems.POISONOUS_AMMO, "thaumicpotatoes:textures/entity/potatouniversal.png",3),
	NULL(0.0d, null, "", 0);
	
	public double damage;
	public Item ammo;
	public String ammoTexture;
	public int tier;
	
	private Ammos(double damage, Item ammo, String boltTexture, int tier) {
		this.damage = damage;
		this.ammo = ammo;
		this.ammoTexture = boltTexture;
		this.tier = tier;
	}
}
