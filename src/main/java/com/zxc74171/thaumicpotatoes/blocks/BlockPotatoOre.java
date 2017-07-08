package com.zxc74171.thaumicpotatoes.blocks;

import java.util.Random;

import javax.annotation.Nullable;

import com.zxc74171.thaumicpotatoes.ThaumicPotatoes2;
import com.zxc74171.thaumicpotatoes.init.ModCreativeTabs;
import com.zxc74171.thaumicpotatoes.init.ModItems;
import com.zxc74171.thaumicpotatoes.proxy.ClientProxy;
import com.zxc74171.thaumicpotatoes.proxy.CommonProxy;
import com.zxc74171.thaumicpotatoes.util.ItemModelProvider;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class BlockPotatoOre extends Block implements ItemModelProvider {


	public BlockPotatoOre() {
		super(Material.ROCK);
		setRegistryName("POTATOORE");
		setUnlocalizedName(getRegistryName().toString());
		setHardness(2f);
		setCreativeTab(ModCreativeTabs.TABS_TP2);
		setSoundType(SoundType.STONE);
	}

	@Override
    @Nullable
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return ModItems.PETRIFIED_POTATO;
    }

	public Item createItemBlock() {
		return new ItemBlock(this).setRegistryName(getRegistryName());
	}

	@Override
	public void initItemModel() {
		ThaumicPotatoes2.proxy.registerItemModel(this, 0, getRegistryName().getResourcePath());
	}

}