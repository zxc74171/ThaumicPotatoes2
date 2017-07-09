package com.zxc74171.thaumicpotatoes.items;

import static com.zxc74171.thaumicpotatoes.ThaumicPotatoes2.proxy;

import java.util.List;

import javax.annotation.Nullable;

import com.zxc74171.thaumicpotatoes.entities.EntityJagaimo;
import com.zxc74171.thaumicpotatoes.entities.EntityZiemniak;
import com.zxc74171.thaumicpotatoes.sounds.ModSoundEvents;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemPotainto extends Item {
	
	public ItemPotainto() {
		setMaxDamage(1);
		setMaxStackSize(1);
		
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		if(!worldIn.isRemote) {
			 
				player.getHeldItem(hand).damageItem(2, player);
				EntityZiemniak boss = new EntityZiemniak(worldIn);
				boss.setLocationAndAngles(pos.getX(), pos.getY()+1,pos.getZ(), MathHelper.wrapDegrees(worldIn.rand.nextFloat() * 360.0F), 0.0F);
				worldIn.spawnEntity(boss);
				String message = String.format(proxy.translateToLocal("bosstext2"));
				player.sendMessage(new TextComponentString(message));
		}
	    return EnumActionResult.PASS;
	}
	
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        tooltip.add(proxy.translateToLocal("tooltipPotainto"));
    }
}