package com.zxc74171.thaumicpotatoes.items;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketEntityVelocity;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.UUID;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.zxc74171.thaumicpotatoes.init.ModItems;
import com.zxc74171.thaumicpotatoes.sounds.ModSoundEvents;
import com.zxc74171.thaumicpotatoes.util.EntityUtil;
import com.zxc74171.thaumicpotatoes.util.PotatoDamageSource;

public class PincerBladesTest extends ItemPotatoShears{
	
  
  public PincerBladesTest(ToolMaterial material) {
		super(material);
		this.addPropertyOverride(new ResourceLocation("pull"), new IItemPropertyGetter()
        {

			@SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
            {
                if (entityIn == null)
                {
                    return 0.0F;
                    
                }
                else
                {
                    return entityIn.getActiveItemStack().getItem() != ModItems.POTATO_SHEARS ? 0.0F : (float)(stack.getMaxItemUseDuration() - entityIn.getItemInUseCount()) / 20.0F;
                    
                }
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

  public ResourceLocation getModelLocation() {
		return getRegistryName();
	}
  
	  
  
  
  
private ItemStack getHeadDrop(EntityLivingBase entity) {

    if(entity instanceof EntitySkeleton) {
      return new ItemStack(Items.SKULL, 1, 0);
    }

    else if(entity instanceof EntityWitherSkeleton) {
      return new ItemStack(Items.SKULL, 1, 1);
    }

    else if(entity instanceof EntityZombie) {
      return new ItemStack(Items.SKULL, 1, 2);
    }

    else if(entity instanceof EntityCreeper) {
      return new ItemStack(Items.SKULL, 1, 4);
    }

    else if(entity instanceof EntityPlayer) {
      ItemStack head = new ItemStack(Items.SKULL, 1, 3);
      NBTTagCompound nametag = new NBTTagCompound();
      nametag.setString("SkullOwner", entity.getDisplayName().getFormattedText());
      head.setTagCompound(nametag);
      return head;
    }

    return new ItemStack(Items.POTATO, 0, 0);
  }


  public void onPlayerStoppedUsing(ItemStack stack, World world, EntityLivingBase player, int timeLeft) {
    if(world.isRemote) {
    }

    float progress = Math.min(1f, (getMaxItemUseDuration(stack) - timeLeft) / 30f);
    float strength = .1f + 2.5f * progress * progress;
    float range = 5f;


    Vec3d eye = new Vec3d(player.posX, player.posY + player.getEyeHeight(), player.posZ); // Entity.getPositionEyes
    Vec3d look = player.getLook(1.0f);
    RayTraceResult mop = EntityUtil.raytraceEntity(player, eye, look, range, true);


    if(mop == null) {
      return;
    }


    if(mop.typeOfHit == RayTraceResult.Type.ENTITY) {
      EntityLivingBase entity = (EntityLivingBase) mop.entityHit;
      double x = look.x * strength;
      double y = look.y / 3f * strength + 0.1f + 0.4f * progress;
      double z = look.z * strength;



      boolean chopHead = progress >= 0.6f;
      if(chopHead) {
        entity.attackEntityFrom(PotatoDamageSource.POTATO, 200.0f);
        entity.addVelocity(x, y, z);
        ItemStack head = getHeadDrop(entity);
        entity.entityDropItem(head, 0);
      }
      
      else{
      }
      
      
      
      world.playSound(null, player.getPosition(), SoundEvents.ENTITY_SHEEP_SHEAR, SoundCategory.PLAYERS, 3f, 1f);
      if(entity instanceof EntityPlayerMP) {
      }
    }
  }

	    
	    
  @Override
  public ItemStack onItemUseFinish(@Nonnull ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
    return stack;
  }


  /**
   * How long it takes to use or consume an item
   */
  @Override
  public int getMaxItemUseDuration(ItemStack stack) {
    return 10 * 20;
  }

  /**
   * returns the action that specifies what animation to play when the items is being used
   */
  @Nonnull
  @Override
  public EnumAction getItemUseAction(ItemStack stack) {
    return EnumAction.NONE;
  }

  @Nonnull
  @Override
  public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand) {
    ItemStack itemStackIn = playerIn.getHeldItem(hand);
    playerIn.setActiveHand(hand);
    return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
  }

  
    }
  
