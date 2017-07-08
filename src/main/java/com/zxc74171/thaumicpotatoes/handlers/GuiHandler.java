package com.zxc74171.thaumicpotatoes.handlers;

import com.zxc74171.thaumicpotatoes.backpack.GuiBackpack;
import com.google.common.util.concurrent.Runnables;
import com.zxc74171.thaumicpotatoes.backpack.ContainerBackpack;
import com.zxc74171.thaumicpotatoes.backpack.InventoryBackpack;
import com.zxc74171.thaumicpotatoes.ending.GuiEnding;
import com.zxc74171.thaumicpotatoes.init.ModItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

    public static final int GUI_RUCKSACK = 0;

    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        if (id == GUI_RUCKSACK) {
            return new ContainerBackpack(player, new InventoryBackpack(player, getRucksack(player)));
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        if (id == GUI_RUCKSACK) {
            return new GuiBackpack(player, new InventoryBackpack(player, getRucksack(player)));
        }

        return null;
    }

    private ItemStack getRucksack(EntityPlayer player) {
        // try to test for mainhand first, if in offhand, it doesn't matter
        if (player.getHeldItemMainhand().getItem() == ModItems.BACKPACK) {
            return player.getHeldItemMainhand();
        }

        if (player.getHeldItemOffhand().getItem() == ModItems.BACKPACK) {
            return player.getHeldItemOffhand();
        }

        return ItemStack.EMPTY;
    }
}
