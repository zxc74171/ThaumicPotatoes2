package com.zxc74171.thaumicpotatoes.entities;


import com.zxc74171.thaumicpotatoes.potatolauncher.Ammos;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class EntityAmmoPoisonous extends EntityAmmo{

	public static Item renderSnowball;
	
	public EntityAmmoPoisonous(World worldIn) {
		super(worldIn);
		this.bolt = Ammos.POISONOUS;
	}
	
	public EntityAmmoPoisonous(World worldIn, double x, double y, double z)
	{
	    super(worldIn, x, y, z);
	    this.bolt = Ammos.POISONOUS;
	}
	
	public EntityAmmoPoisonous(World worldIn, EntityLivingBase shooter, Ammos bolt){
		super(worldIn, shooter, bolt);
		this.bolt = Ammos.POISONOUS;
	}
}
