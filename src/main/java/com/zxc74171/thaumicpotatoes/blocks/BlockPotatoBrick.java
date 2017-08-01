package com.zxc74171.thaumicpotatoes.blocks;

import java.util.Random;

import javax.annotation.Nullable;

import com.zxc74171.thaumicpotatoes.ThaumicPotatoes2;
import com.zxc74171.thaumicpotatoes.init.ModCreativeTabs;
import com.zxc74171.thaumicpotatoes.init.ModItems;
import com.zxc74171.thaumicpotatoes.util.ItemModelProvider;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class BlockPotatoBrick extends Block implements ItemModelProvider {


	public BlockPotatoBrick() {
		super(Material.ROCK);
		setRegistryName("POTATOBRICK");
		setUnlocalizedName(getRegistryName().toString());
		setHardness(1.5f);
		setResistance(2000.0f);
		setCreativeTab(ModCreativeTabs.TABS_TP2);
		setSoundType(SoundType.STONE);
	}

	public Item createItemBlock() {
		return new ItemBlock(this).setRegistryName(getRegistryName());
	}

	@Override
	public void initItemModel() {
		ThaumicPotatoes2.proxy.registerItemModel(this, 0, getRegistryName().getResourcePath());
	}

}