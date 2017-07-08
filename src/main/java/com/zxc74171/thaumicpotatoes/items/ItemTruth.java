package com.zxc74171.thaumicpotatoes.items;

import static com.zxc74171.thaumicpotatoes.ThaumicPotatoes2.proxy;

import com.google.common.util.concurrent.Runnables;
import com.zxc74171.thaumicpotatoes.ThaumicPotatoes2;
import com.zxc74171.thaumicpotatoes.ending.GuiEnding;
import com.zxc74171.thaumicpotatoes.entities.EntityJagaimo;
import com.zxc74171.thaumicpotatoes.handlers.GuiHandler;
import com.zxc74171.thaumicpotatoes.proxy.CommonProxy;
import com.zxc74171.thaumicpotatoes.sounds.ModSoundEvents;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiWinGame;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemTruth extends Item {

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		if(world.isRemote) {
            Minecraft.getMinecraft().getSoundHandler().stopSounds();
            Minecraft.getMinecraft().getSoundHandler().update();
            Minecraft.getMinecraft().getSoundHandler().setSoundLevel(SoundCategory.MUSIC, 0);
            Minecraft.getMinecraft().getSoundHandler().setSoundLevel(SoundCategory.AMBIENT, 0);
		    world.playSound(pos.getX(), pos.getY()+1, pos.getZ(), ModSoundEvents.FAREWELL, SoundCategory.MUSIC, 1, 1, true);
			Minecraft.getMinecraft().displayGuiScreen(new GuiEnding(true, Runnables.doNothing()));
		}
	    return EnumActionResult.PASS;
	}


}