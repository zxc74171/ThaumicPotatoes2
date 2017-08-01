package com.zxc74171.thaumicpotatoes;

import com.zxc74171.thaumicpotatoes.handlers.FuelHandler;
import com.zxc74171.thaumicpotatoes.handlers.GuiHandler;
import com.zxc74171.thaumicpotatoes.handlers.VillagerRegistryHandler;
import com.zxc74171.thaumicpotatoes.handlers.SmeltingHandler;
import com.zxc74171.thaumicpotatoes.init.ModBlocks;
import com.zxc74171.thaumicpotatoes.init.ModItems;
import com.zxc74171.thaumicpotatoes.oredict.OreDict;
import com.zxc74171.thaumicpotatoes.proxy.CommonProxy;
import com.zxc74171.thaumicpotatoes.util.Utils;
import com.zxc74171.thaumicpotatoes.villager.InitVillager;
import com.zxc74171.thaumicpotatoes.worldgen.TP2WorldGen;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerProfession;

@Mod(dependencies="required-before:guideapi", modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION)
public class ThaumicPotatoes2
{

    @Mod.Instance(Reference.MODID)
   
    public static ThaumicPotatoes2 instance;
    
    // content
    public static ModBlocks blocks = new ModBlocks();

    
    
    @SidedProxy(serverSide = Reference.SERVER_PROXY_CLASS, clientSide = Reference.CLIENT_PROXY_CLASS)
    public static CommonProxy proxy;

    @EventHandler
	public static void preInit(FMLPreInitializationEvent event) {
		new ModItems();
		SmeltingHandler.registerSmeltingRecipes();
		MinecraftForge.EVENT_BUS.register(new EventBus());
		proxy.preInit(event);
		 Utils.getLogger().info("TP2 PreInitialize");
	}
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        GameRegistry.registerFuelHandler(new FuelHandler());
        GameRegistry.registerWorldGenerator(new TP2WorldGen(), 0);
        NetworkRegistry.INSTANCE.registerGuiHandler(ThaumicPotatoes2.instance, new GuiHandler());
        OreDict.init();
        Utils.getLogger().info("TP2 Initialize");
        

    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        Utils.getLogger().info("TP2 PostInitialize");

    }
    
    @Mod.EventBusSubscriber
	public static class EventBus {

    	
		@SubscribeEvent
		public static void registerBlocks(RegistryEvent.Register<Block> event) {
			event.getRegistry().registerAll(
					blocks.POTATOORE,
					blocks.POTATOBRICK
			);
			}

		@SubscribeEvent
		public static void registerItems(RegistryEvent.Register<Item> event) {
			event.getRegistry().registerAll(
					blocks.POTATOORE.createItemBlock(),
					blocks.POTATOBRICK.createItemBlock()
			);
			event.getRegistry().registerAll(ModItems.ITEMS);
		}

		@SubscribeEvent
		public static void registerModels(ModelRegistryEvent event) {
			blocks.POTATOORE.initItemModel();
			blocks.POTATOBRICK.initItemModel();
			
			for (int i = 0; i < ModItems.ITEMS.length; i++) {
				Item item = ModItems.ITEMS[i];
				ModelLoader.setCustomModelResourceLocation(item, 0,
						new ModelResourceLocation(item.getRegistryName(), "inventory"));
			}
		}
		@SubscribeEvent
	    public void onVillagerRegistry(Register<VillagerProfession> event){
	        InitVillager.init();
	        event.getRegistry().register(InitVillager.potatoProfession);
	    }
		

		

	}


}
