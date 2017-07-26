package com.zxc74171.thaumicpotatoes.items;



import static com.zxc74171.thaumicpotatoes.ThaumicPotatoes2.proxy;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.util.concurrent.Runnables;
import com.zxc74171.thaumicpotatoes.ending.GuiEnding;
import com.zxc74171.thaumicpotatoes.proxy.ClientProxy;
import com.zxc74171.thaumicpotatoes.sounds.ModSoundEvents;

import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemTruth extends Item {
	
	public static ClientProxy clientproxy;
	
	@SideOnly(Side.CLIENT)
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{	  
		  System.out.println("Thaumic Potatoes 2 Ending Start Playing");
	      Minecraft.getMinecraft().getSoundHandler().setSoundLevel(SoundCategory.MUSIC, 0);
	      Minecraft.getMinecraft().getSoundHandler().setSoundLevel(SoundCategory.AMBIENT, 0);
		  world.playSound(pos.getX(), pos.getY()+1, pos.getZ(), ModSoundEvents.FAREWELL, SoundCategory.MUSIC, 1, 1, true);
		  Minecraft.getMinecraft().displayGuiScreen(new GuiEnding(true, Runnables.doNothing()));
	    return EnumActionResult.PASS;
	}
	
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        tooltip.add(proxy.translateToLocal("tooltipTruth"));
    }

}