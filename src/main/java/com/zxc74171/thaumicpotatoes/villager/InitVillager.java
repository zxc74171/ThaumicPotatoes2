package com.zxc74171.thaumicpotatoes.villager;

import com.zxc74171.thaumicpotatoes.Reference;
import com.zxc74171.thaumicpotatoes.init.ModItems;
import com.zxc74171.thaumicpotatoes.util.Utils;

import net.minecraft.entity.passive.EntityVillager.PriceInfo;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerCareer;
import net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerProfession;

public final class InitVillager{

    public static VillagerProfession potatoProfession;

    public static void init(){
        Reference.LOGGER.info("Initializing Villager Stuffs...hope it'll work this time");
            initJamVillagePart();
    }

    private static void initJamVillagePart(){
        potatoProfession = new VillagerProfession(Reference.MODID+":jamGuy", "thaumicpotatoes:textures/entity/potatovillager.png", "thaumicpotatoes:textures/entity/potatovillagerzombie.png");

        VillagerCareer career = new VillagerCareer(potatoProfession, Reference.MODID+".potatovillager");
        career.addTrade(1,
                new BasicTradeList(new ItemStack(ModItems.THAUMIC_POTATO), new PriceInfo(3, 10), new PriceInfo(1, 2)),
                new BasicTradeList(new ItemStack(Items.POISONOUS_POTATO), new PriceInfo(4, 8), new PriceInfo(1, 2)));
        career.addTrade(2,
        		new BasicTradeList(new ItemStack(ModItems.POTATO_ALLOY), new PriceInfo(1, 3), new PriceInfo(3, 5)),
                new BasicTradeList(new ItemStack(ModItems.CARBONATOR), new PriceInfo(3, 6), new PriceInfo(5, 8)));
        career.addTrade(3,
        		new BasicTradeList(new ItemStack(ModItems.TRUTH), new PriceInfo(1, 1), new ItemStack(ModItems.RECORD_FAREWELL), new PriceInfo(1, 1)));
        		
        		
        		
        		
        VillagerRegistry.instance().registerVillageCreationHandler(new VillagePotatoHouseHandler());
        MapGenStructureIO.registerStructureComponent(VillageComponentPotatoHouse.class, Reference.MODID+":jamHouseStructure");
    }

}