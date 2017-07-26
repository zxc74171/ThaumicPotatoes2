package com.zxc74171.thaumicpotatoes.handlers;

import com.zxc74171.thaumicpotatoes.init.ModItems;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.IFuelHandler;

public class FuelHandler implements IFuelHandler {
    @Override
    public int getBurnTime(ItemStack fuel) {

        if(fuel.getItem() == ModItems.THAUMIC_POTATO)
            return 100;
        return 0;
    }
}
