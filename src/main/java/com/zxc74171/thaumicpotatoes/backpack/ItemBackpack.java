package com.zxc74171.thaumicpotatoes.backpack;

import com.zxc74171.thaumicpotatoes.Reference;
import com.zxc74171.thaumicpotatoes.ThaumicPotatoes2;
import com.zxc74171.thaumicpotatoes.handlers.GuiHandler;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ItemBackpack extends Item {

	public ItemBackpack() {
        this.setMaxStackSize(1);
        this.setCreativeTab(CreativeTabs.TOOLS);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        ItemStack item = player.getHeldItem(hand);
        if (!world.isRemote && item.getItem() == this) {
            player.openGui(ThaumicPotatoes2.instance, GuiHandler.GUI_RUCKSACK, world, 0, 0, 0);
        }
        return ActionResult.newResult(EnumActionResult.SUCCESS, item);
    }

    

}