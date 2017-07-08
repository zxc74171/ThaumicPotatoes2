package com.zxc74171.thaumicpotatoes.backpack;

import com.zxc74171.thaumicpotatoes.handlers.GuiHandler;
import com.zxc74171.thaumicpotatoes.Reference;
import com.zxc74171.thaumicpotatoes.ThaumicPotatoes2;

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

    @Override
    public String getItemStackDisplayName(ItemStack itemStack) {
        if (itemStack.hasTagCompound() && itemStack.getTagCompound().hasKey("display", 10)) {
            NBTTagCompound display = itemStack.getTagCompound().getCompoundTag("display");
            if (display.hasKey("Name")) {
                return display.getString("Name");
            }
        }

        return super.getItemStackDisplayName(itemStack);
    }
    
    public boolean hasColor(ItemStack itemStack) {
        return (itemStack.hasTagCompound() && (itemStack.getTagCompound().hasKey("display", 10) && itemStack.getTagCompound().getCompoundTag("display").hasKey("color", 3)));
    }

    public int getColor(ItemStack itemStack) {
        NBTTagCompound nbt = itemStack.getTagCompound();
        if (nbt != null) {
            NBTTagCompound nbtDisplay = nbt.getCompoundTag("display");
            if (nbtDisplay.hasKey("color", 3)) {
                return nbtDisplay.getInteger("color");
            }
        }

        return 10511680;
    }
    
    private void init(String unlocalizedName) {
        this.setUnlocalizedName("backpack");
        this.setRegistryName(new ResourceLocation(Reference.MODID, unlocalizedName));
        this.setCreativeTab(CreativeTabs.FOOD);
    }
}
