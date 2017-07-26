package com.zxc74171.thaumicpotatoes.entities;


import com.zxc74171.thaumicpotatoes.potatolauncher.Ammos;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class EntityAmmoBaked extends EntityAmmo{

	public static Item renderSnowball;
	
	public EntityAmmoBaked(World worldIn) {
		super(worldIn);
		this.bolt = Ammos.BAKED;
	}
	
	public EntityAmmoBaked(World worldIn, double x, double y, double z)
	{
	    super(worldIn, x, y, z);
	    this.bolt = Ammos.BAKED;
	}
	
	public EntityAmmoBaked(World worldIn, EntityLivingBase shooter, Ammos bolt){
		super(worldIn, shooter, bolt);
		this.bolt = Ammos.BAKED;
	}
}
