package com.zxc74171.thaumicpotatoes.items;

import com.zxc74171.thaumicpotatoes.Reference;
import com.zxc74171.thaumicpotatoes.init.ModCreativeTabs;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ItemThaumicPotato extends ItemFood {
    private PotionEffect[] effects;

    public ItemThaumicPotato(String unlocalizedName, int amount, boolean isWolfFood, PotionEffect...potionEffects) {
        super(amount,isWolfFood);
        init(unlocalizedName);
        this.effects = potionEffects;
    }

    public ItemThaumicPotato(String unlocalizedName, int amount, float saturation, boolean isWolfFood, PotionEffect...potionEffects) {
        super(amount,saturation,isWolfFood);
        init(unlocalizedName);
        this.effects = potionEffects;
    }

    @Override
    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
        // super.onFoodEaten(stack,worldIn,player);
        for(PotionEffect effect : effects) {
            player.addPotionEffect(effect);
        }
    }

    private void init(String unlocalizedName) {
        this.setUnlocalizedName(unlocalizedName);
        this.setRegistryName(new ResourceLocation(Reference.MODID, unlocalizedName));
        this.setCreativeTab(ModCreativeTabs.TABS_TP2);
    }
}
