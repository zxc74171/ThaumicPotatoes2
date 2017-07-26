package com.zxc74171.thaumicpotatoes.items;

import javax.annotation.Nullable;

import com.zxc74171.thaumicpotatoes.entities.EntityAmmo;
import com.zxc74171.thaumicpotatoes.entities.EntityAmmoBaked;
import com.zxc74171.thaumicpotatoes.entities.EntityAmmoNormal;
import com.zxc74171.thaumicpotatoes.entities.EntityAmmoPoisonous;
import com.zxc74171.thaumicpotatoes.potatolauncher.Ammos;
import com.zxc74171.thaumicpotatoes.potatolauncher.LauncherProperties;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemLauncher extends Item
{
	public float strength;
	final LauncherProperties material;
	float multiplier;
	Ammos ammo;
	boolean fromStorage;
	int slot;

	
    public ItemLauncher(String name, CreativeTabs creativeTab, final LauncherProperties material, final float multiplier)
    {
    	this.material = material;
    	this.multiplier = multiplier;
    	this.ammo = material.ammo;
        this.maxStackSize = 1;
        this.strength = material.strength * multiplier;
        this.setUnlocalizedName(name);
        this.setRegistryName(name);
        this.setMaxDamage((int) (material.durability * multiplier));
        this.setCreativeTab(creativeTab);
        this.fromStorage = false;
        this.slot = 0;
        this.addPropertyOverride(new ResourceLocation("pull"), new IItemPropertyGetter()
        {
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
            {
                return entityIn == null ? 0.0F : (entityIn.getActiveItemStack().getItem() instanceof ItemLauncher) ? (float)(stack.getMaxItemUseDuration() - entityIn.getItemInUseCount()) / (material.drawbackSpeed * multiplier): 0.0F;
            }
        });
        this.addPropertyOverride(new ResourceLocation("pulling"), new IItemPropertyGetter()
        {
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
            {
                return entityIn != null && entityIn.isHandActive() && entityIn.getActiveItemStack() == stack ? 1.0F : 0.0F;
            }
        });
        
    }
    


    private ItemStack findAmmo(EntityPlayer player)
    {
        if (this.isAmmo(player.getHeldItem(EnumHand.OFF_HAND)))
        {
            return player.getHeldItem(EnumHand.OFF_HAND);
        }
        else if (this.isAmmo(player.getHeldItem(EnumHand.MAIN_HAND)))
        {
            return player.getHeldItem(EnumHand.MAIN_HAND);
        }
        else
        {
            for (int i = 0; i < player.inventory.getSizeInventory(); ++i)
            {
                ItemStack itemstack = player.inventory.getStackInSlot(i);
                
                fromStorage = false;
                if (this.isAmmo(itemstack))
                {
                    return itemstack;
                }
            }

            return ItemStack.EMPTY;
        }
    }

    protected boolean isAmmo(ItemStack stack)
    {
    	if (stack.getItem() instanceof ItemAmmo){
    		this.ammo = ((ItemAmmo)stack.getItem()).getAmmo();
    		
    		return this.ammo.tier <= this.material.tier;
    	}
    	return false;
    }

    /**
     * Called when the player stops using an Item (stops holding the right mouse button).
     */
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft)
    {
        if (entityLiving instanceof EntityPlayer)
        {
            EntityPlayer entityplayer = (EntityPlayer)entityLiving;
            boolean flag = entityplayer.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) > 0;
            ItemStack itemstack = this.findAmmo(entityplayer);
            if (this == this) return;
            int i = this.getMaxItemUseDuration(stack) - timeLeft;
            i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(stack, worldIn, entityplayer, i, !itemstack.isEmpty() || flag);
            if (i < 0) return;

            if (!itemstack.isEmpty() || flag)
            {
            	if (itemstack == ItemStack.EMPTY)
                {
            		itemstack = new ItemStack(material.ammo.ammo);
                }
            }
            fireAmmo(stack, worldIn, entityplayer, i);
        }
    }

    /**
     * Gets the velocity of the arrow entity from the bow's charge
     */
    public float getAmmoVelocity(int charge)
    {
    	float f = (float) (charge / (material.drawbackSpeed * multiplier));
    	
        if (f > 1.0F)
        {
            f = 1.0F;
        }

        return f;
    }

    /**
     * How long it takes to use or consume an item
     */
    public int getMaxItemUseDuration(ItemStack stack)
    {
        return 0;
    }

    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    public EnumAction getItemUseAction(ItemStack stack)
    {
        return EnumAction.NONE;
    }
    
    public EntityAmmo selectAmmo(World worldIn, EntityPlayer entityplayer ){
    	switch (this.ammo) {
		case NORMAL:
			return new EntityAmmoNormal(worldIn, entityplayer, this.ammo);
		case POISONOUS:
			return new EntityAmmoPoisonous(worldIn, entityplayer, this.ammo);
		case BAKED:
			return new EntityAmmoBaked(worldIn, entityplayer, this.ammo);
		default:
			return new EntityAmmoNormal(worldIn, entityplayer, this.ammo);
    	}
    }
    
    public void fireAmmo(ItemStack stack, World worldIn, EntityPlayer entityplayer, int i){
    	ItemStack itemstack = this.findAmmo(entityplayer);
    	if (fromStorage){
        	((ItemLauncher)stack.getItem()).writeToNBT(stack.getTagCompound(), this.slot);
        }
    	float f = getAmmoVelocity(i);

        if ((double)f >= 0.1D)
        {
            boolean flag1 = entityplayer.capabilities.isCreativeMode || (itemstack.getItem() instanceof ItemAmmo && ((ItemAmmo) itemstack.getItem()).isInfinite(itemstack, stack, entityplayer));
	    	if (!worldIn.isRemote)
	        {
	        	EntityAmmo entityAmmo;
	        	entityAmmo = selectAmmo(worldIn, entityplayer);
	           

	            if (f == 1.0F)
	            {
	            	entityAmmo.setIsCritical(true);
	            }
	
	            int j = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);
	
	            if (j > 0)
	            {
	            	entityAmmo.setDamage(entityAmmo.getDamage() + (double)j * 0.5D + 0.5D);
	            }
	
	            int k = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, stack);
	
	            if (k > 0)
	            {
	            	entityAmmo.setKnockbackStrength(k);
	            }
	
	            if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, stack) > 0)
	            {
	            	entityAmmo.setFire(100);
	            }
	            
	            
	            
	
	            if (flag1 || entityplayer.capabilities.isCreativeMode)
	            {
	            	entityAmmo.pickupStatus = EntityAmmo.PickupStatus.CREATIVE_ONLY;
	            }
	            
	            if (this == this){
	            	
	            	entityAmmo.setAim(entityplayer, entityplayer.rotationPitch, entityplayer.rotationYaw, 0.0F, f * this.material.strength, 1.0F);
	            	worldIn.spawnEntity(entityAmmo);
	            }
	            
	        }
	
	        worldIn.playSound((EntityPlayer)null, entityplayer.posX, entityplayer.posY, entityplayer.posZ, SoundEvents.ENTITY_BLAZE_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
	
	        if (!flag1 && !entityplayer.capabilities.isCreativeMode)
	        {
	        	itemstack.shrink(1);
	
	            if (itemstack.isEmpty())
	            {
	                entityplayer.inventory.deleteStack(itemstack);
	            }
	        }
	
	        entityplayer.addStat(StatList.getObjectUseStats(this));
        }
    }
    
    @Override
    /**
     * Called when the equipped item is right clicked.
     */
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        ItemStack crossbow = playerIn.getHeldItem(handIn);
        if(crossbow.getItem() instanceof ItemLauncher){
        	int invSize = 0;
			switch (this.material) {
 			default:
				break;
			}
        	for (int j = 0; j < invSize; ++j){
				ItemStack temp = ((ItemLauncher)crossbow.getItem()).readFromNBT(crossbow.getTagCompound(), j);
				
			}
        }
        
        boolean flag = !this.findAmmo(playerIn).isEmpty();

        ActionResult<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onArrowNock(crossbow, worldIn, playerIn, handIn, flag);

        if (!playerIn.capabilities.isCreativeMode && !flag)
        {
            return flag ? new ActionResult<ItemStack>(EnumActionResult.PASS, crossbow) : new ActionResult<ItemStack>(EnumActionResult.FAIL, crossbow);
        }
        else
        {
            playerIn.setActiveHand(handIn);
            {
				fireAmmo(crossbow ,worldIn, playerIn, 72000);
			}
			
            return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, crossbow);
        }
        
    }

    /**
     * Return the enchantability factor of the item, most of the time is based on material.
     */
    public int getItemEnchantability()
    {
        return 1;
    }
    
    public ItemStack readFromNBT(NBTTagCompound compound, int index)
	{
		if (compound == null){
			return ItemStack.EMPTY;
		}
		NBTTagList items = compound.getTagList("InventoryCrossbow", Constants.NBT.TAG_COMPOUND);
		NBTTagCompound item = (NBTTagCompound) items.getCompoundTagAt(index);
		return new ItemStack(item);
	}
	
	public void writeToNBT(NBTTagCompound tagcompound, int index)
	{
		NBTTagList items = tagcompound.getTagList("InventoryCrossbow", Constants.NBT.TAG_COMPOUND);
		NBTTagCompound item = (NBTTagCompound) items.getCompoundTagAt(index);
		int currentCount = item.getInteger("Count");
		if (currentCount == 1){
			items.removeTag(index);
		} else {
			item.setInteger("Count", currentCount-1);
		}
	}
}