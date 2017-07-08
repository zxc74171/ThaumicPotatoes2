package com.zxc74171.thaumicpotatoes.items;

import static com.zxc74171.thaumicpotatoes.ThaumicPotatoes2.proxy;

import com.zxc74171.thaumicpotatoes.entities.EntityJagaimo;
import com.zxc74171.thaumicpotatoes.sounds.ModSoundEvents;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemTaintedCore extends Item {
	
	public ItemTaintedCore() {
		setMaxDamage(1);
		setMaxStackSize(1);
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		if(!worldIn.isRemote) {
			 
				player.getHeldItem(hand).damageItem(2, player);
				EntityJagaimo bosso = new EntityJagaimo(worldIn);
				bosso.setLocationAndAngles(pos.getX(), pos.getY()+1,pos.getZ(), MathHelper.wrapDegrees(worldIn.rand.nextFloat() * 360.0F), 0.0F);
				worldIn.spawnEntity(bosso);
				String message = String.format(proxy.translateToLocal("bosstext"));
				player.sendMessage(new TextComponentString(message));
		}
	    return EnumActionResult.PASS;
	}
}