package com.zxc74171.thaumicpotatoes.backpack;

import com.zxc74171.thaumicpotatoes.backpack.ItemBackpack;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

// Slot that doesn't allow rucksacks to be place inside
public class BackpackSlot extends Slot {
    public BackpackSlot(IInventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    @Override
    public boolean isItemValid(ItemStack itemStack) {
        return acceptsStack(itemStack);
    }

    public boolean acceptsStack(ItemStack itemStack) {
        return !(itemStack.getItem() instanceof ItemBackpack);
    }
}
