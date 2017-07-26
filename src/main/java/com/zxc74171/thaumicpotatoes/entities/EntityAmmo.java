package com.zxc74171.thaumicpotatoes.entities;

import com.zxc74171.thaumicpotatoes.init.ModItems;
import com.zxc74171.thaumicpotatoes.potatolauncher.Ammos;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

public class EntityAmmo extends EntityArrow implements IEntityAdditionalSpawnData{
	public Ammos bolt;
	
	public EntityAmmo(World worldIn) {
		super(worldIn);
	}
	
	public EntityAmmo(World worldIn, double x, double y, double z)
	{
	    this(worldIn);
	    this.setPosition(x, y, z);
	}
	
	public EntityAmmo(World worldIn, EntityLivingBase shooter, Ammos bolt)
	{
	    this(worldIn, shooter.posX, shooter.posY + (double)shooter.getEyeHeight() - 0.10000000149011612D, shooter.posZ);
	    this.shootingEntity = shooter;
	    if (shooter instanceof EntityPlayer)
        {
            this.pickupStatus = EntityArrow.PickupStatus.ALLOWED;
        }
	    
	    this.bolt = bolt;
	    
	    this.setDamage(bolt.damage);
	}
	
	
	public Ammos getBolt() {
		return this.bolt;
	}
	
	@Override
	public boolean getIsCritical()
	{
	    return false;
	}
	
	@Override
	protected ItemStack getArrowStack() {
		switch (this.bolt) {
		case NORMAL:
			return new ItemStack(ModItems.AMMO_NORMAL);
		case POISONOUS:
			return new ItemStack(ModItems.AMMO_POISONOUS);
		case BAKED:
			return new ItemStack(ModItems.AMMO_BAKED);
		default:
			return new ItemStack(ModItems.AMMO_NORMAL);
		}
	}
	
	@Override
	protected void arrowHit(EntityLivingBase living)
	{
		if (this.bolt == Ammos.POISONOUS){
	        PotionEffect potioneffect = new PotionEffect(MobEffects.POISON, 100, 4);
	        living.addPotionEffect(potioneffect);
		}
		
		if (this.bolt == Ammos.BAKED){
	        living.setFire(5);;
		}
	}

    public void onCollideWithPlayer(EntityPlayer entityIn)
    {
        if (!this.world.isRemote && this.inGround && this.arrowShake <= 0)
        {
            boolean flag = this.pickupStatus == EntityArrow.PickupStatus.ALLOWED || this.pickupStatus == EntityArrow.PickupStatus.CREATIVE_ONLY && entityIn.capabilities.isCreativeMode;
            if (this.pickupStatus == EntityArrow.PickupStatus.ALLOWED && !entityIn.inventory.addItemStackToInventory(this.getArrowStack()))
            {
                flag = false;
            }
            if (flag)
            {
                entityIn.onItemPickup(this, 1);
                this.setDead();
            }
        }
    }

	@Override
	public void writeSpawnData(ByteBuf buffer) {
		try {
			buffer.writeInt(this.shootingEntity.getEntityId());
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	@Override
	public void readSpawnData(ByteBuf additionalData) {
		Entity shooter = world.getEntityByID(additionalData.readInt());
		if (shooter instanceof EntityLivingBase) {
		shootingEntity = (EntityLivingBase) shooter;
		}
	}
}