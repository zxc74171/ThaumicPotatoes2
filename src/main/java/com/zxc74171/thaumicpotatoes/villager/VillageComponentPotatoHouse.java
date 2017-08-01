package com.zxc74171.thaumicpotatoes.villager;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerProfession;

import java.util.List;
import java.util.Random;

import com.zxc74171.thaumicpotatoes.ThaumicPotatoes2;

public class VillageComponentPotatoHouse extends StructureVillagePieces.House1{

    private static final int X_SIZE = 5;
    private static final int Y_SIZE = 12;
    private static final int Z_SIZE = 9;

    private int averageGroundLevel = -1;

    public VillageComponentPotatoHouse(){

    }

    public VillageComponentPotatoHouse(StructureBoundingBox boundingBox, EnumFacing par5){
        this.setCoordBaseMode(par5);
        this.boundingBox = boundingBox;
    }

    public static VillageComponentPotatoHouse buildComponent(List<StructureComponent> pieces, int p1, int p2, int p3, EnumFacing p4){
        StructureBoundingBox boundingBox = StructureBoundingBox.getComponentToAddBoundingBox(p1, p2, p3, 0, 0, 0, X_SIZE, Y_SIZE, Z_SIZE, p4);
        return canVillageGoDeeper(boundingBox) && StructureComponent.findIntersecting(pieces, boundingBox) == null ? new VillageComponentPotatoHouse(boundingBox, p4) : null;
    }

   
    @Override
    public boolean addComponentParts(World world, Random rand, StructureBoundingBox sbb){
        if(this.averageGroundLevel < 0){
            this.averageGroundLevel = this.getAverageGroundLevel(world, sbb);
            if(this.averageGroundLevel < 0){
                return true;
            }
            this.boundingBox.offset(0, this.averageGroundLevel-this.boundingBox.maxY+Y_SIZE-1, 0);
        }

        this.fillWithBlocks(world, sbb, 0, 0, 0, X_SIZE-1, Y_SIZE-1, Z_SIZE-1, Blocks.AIR);
        this.spawnActualHouse(world, rand, sbb);

        for(int i = 0; i < X_SIZE; i++){
            for(int j = 0; j < Z_SIZE; j++){
                this.clearCurrentPositionBlocksUpwards(world, i, Y_SIZE, j, sbb);
                this.replaceAirAndLiquidDownwards(world, Blocks.COBBLESTONE.getDefaultState(), i, -1, j, sbb);
            }
        }

        this.spawnVillagers(world, sbb, 3, 1, 3, 1);

        return true;
    }

    public void fillWithBlocks(World world, StructureBoundingBox sbb, int minX, int minY, int minZ, int maxX, int maxY, int maxZ, Block block){
        this.fillWithBlocks(world, sbb, minX, minY, minZ, maxX, maxY, maxZ, block.getDefaultState(), block.getDefaultState(), false);
    }

    @SuppressWarnings("deprecation")
	public void spawnActualHouse(World world, Random rand, StructureBoundingBox sbb){
        //Base
        IBlockState iblockstate = ThaumicPotatoes2.blocks.POTATOBRICK.getDefaultState();
        this.fillWithBlocks(world, sbb, 1, 1, 1, 3, 3, 7, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
        this.fillWithBlocks(world, sbb, 1, 5, 1, 3, 9, 3, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
        this.fillWithBlocks(world, sbb, 1, 0, 0, 3, 0, 8, iblockstate, iblockstate, false);
        this.fillWithBlocks(world, sbb, 1, 1, 0, 3, 10, 0, iblockstate, iblockstate, false);
        this.fillWithBlocks(world, sbb, 0, 1, 1, 0, 10, 3, iblockstate, iblockstate, false);
        this.fillWithBlocks(world, sbb, 4, 1, 1, 4, 10, 3, iblockstate, iblockstate, false);
        this.fillWithBlocks(world, sbb, 0, 0, 4, 0, 4, 7, iblockstate, iblockstate, false);
        this.fillWithBlocks(world, sbb, 4, 0, 4, 4, 4, 7, iblockstate, iblockstate, false);
        this.fillWithBlocks(world, sbb, 1, 1, 8, 3, 4, 8, iblockstate, iblockstate, false);
        this.fillWithBlocks(world, sbb, 1, 5, 4, 3, 10, 4, iblockstate, iblockstate, false);
        this.fillWithBlocks(world, sbb, 1, 5, 5, 3, 5, 7, iblockstate, iblockstate, false);
        this.fillWithBlocks(world, sbb, 0, 9, 0, 4, 9, 4, iblockstate, iblockstate, false);
        this.fillWithBlocks(world, sbb, 0, 4, 0, 4, 4, 4, iblockstate, iblockstate, false);
        this.setBlockState(world, iblockstate, 0, 11, 2, sbb);
        this.setBlockState(world, iblockstate, 4, 11, 2, sbb);
        this.setBlockState(world, iblockstate, 2, 11, 0, sbb);
        this.setBlockState(world, iblockstate, 2, 11, 4, sbb);
        this.setBlockState(world, iblockstate, 1, 1, 6, sbb);
        this.setBlockState(world, iblockstate, 1, 1, 7, sbb);
        this.setBlockState(world, iblockstate, 2, 1, 7, sbb);
        this.setBlockState(world, iblockstate, 3, 1, 6, sbb);
        this.setBlockState(world, iblockstate, 3, 1, 7, sbb);
        this.setBlockState(world, iblockstate, 1, 1, 5, sbb);
        this.setBlockState(world, iblockstate, 2, 1, 6, sbb);
        this.setBlockState(world, iblockstate, 3, 1, 5, sbb);
        this.setBlockState(world, iblockstate, 1, 2, 7, sbb);
        this.setBlockState(world, iblockstate, 3, 2, 7, sbb);
        this.setBlockState(world, Blocks.GLASS_PANE.getDefaultState(), 0, 2, 2, sbb);
        this.setBlockState(world, Blocks.GLASS_PANE.getDefaultState(), 0, 3, 2, sbb);
        this.setBlockState(world, Blocks.GLASS_PANE.getDefaultState(), 4, 2, 2, sbb);
        this.setBlockState(world, Blocks.GLASS_PANE.getDefaultState(), 4, 3, 2, sbb);
        this.setBlockState(world, Blocks.GLASS_PANE.getDefaultState(), 0, 6, 2, sbb);
        this.setBlockState(world, Blocks.GLASS_PANE.getDefaultState(), 0, 7, 2, sbb);
        this.setBlockState(world, Blocks.GLASS_PANE.getDefaultState(), 4, 6, 2, sbb);
        this.setBlockState(world, Blocks.GLASS_PANE.getDefaultState(), 4, 7, 2, sbb);
        this.setBlockState(world, Blocks.GLASS_PANE.getDefaultState(), 2, 6, 0, sbb);
        this.setBlockState(world, Blocks.GLASS_PANE.getDefaultState(), 2, 7, 0, sbb);
        this.setBlockState(world, Blocks.GLASS_PANE.getDefaultState(), 2, 6, 4, sbb);
        this.setBlockState(world, Blocks.GLASS_PANE.getDefaultState(), 2, 7, 4, sbb);
        this.setBlockState(world, Blocks.GLASS_PANE.getDefaultState(), 0, 3, 6, sbb);
        this.setBlockState(world, Blocks.GLASS_PANE.getDefaultState(), 4, 3, 6, sbb);
        this.setBlockState(world, Blocks.GLASS_PANE.getDefaultState(), 2, 3, 8, sbb);
        this.placeTorch(world, EnumFacing.SOUTH, 2, 4, 7, sbb);
        this.placeTorch(world, EnumFacing.EAST, 1, 4, 6, sbb);
        this.placeTorch(world, EnumFacing.WEST, 3, 4, 6, sbb);
        this.placeTorch(world, EnumFacing.NORTH, 2, 4, 5, sbb);
        IBlockState iblockstate4 = Blocks.LADDER.getDefaultState().withProperty(BlockLadder.FACING, EnumFacing.WEST);
        for (int i = 1; i <= 9; ++i)
        {
            this.setBlockState(world, iblockstate4, 3, i, 3, sbb);
        }

        this.setBlockState(world, Blocks.AIR.getDefaultState(), 2, 1, 0, sbb);
        this.setBlockState(world, Blocks.AIR.getDefaultState(), 2, 2, 0, sbb);
        this.createVillageDoor(world, sbb, rand, 2, 1, 0, EnumFacing.NORTH);

        if (this.getBlockStateFromPos(world, 2, 0, -1, sbb).getMaterial() == Material.AIR && this.getBlockStateFromPos(world, 2, -1, -1, sbb).getMaterial() != Material.AIR)
        {
            this.setBlockState(world, iblockstate, 2, 0, -1, sbb);

            if (this.getBlockStateFromPos(world, 2, -1, -1, sbb).getBlock() == Blocks.GRASS_PATH)
            {
                this.setBlockState(world, Blocks.GRASS.getDefaultState(), 2, -1, -1, sbb);
            }
        }

        for (int k = 0; k < 9; ++k)
        {
            for (int j = 0; j < 5; ++j)
            {
                this.clearCurrentPositionBlocksUpwards(world, j, 12, k, sbb);
                this.replaceAirAndLiquidDownwards(world, iblockstate, j, -1, k, sbb);
            }
        }
    }

    @Override
    protected VillagerProfession chooseForgeProfession(int count, VillagerProfession prof){
        return InitVillager.potatoProfession;
    }
}