package com.zxc74171.thaumicpotatoes.init;


import com.zxc74171.thaumicpotatoes.backpack.ItemBackpack;
import com.zxc74171.thaumicpotatoes.items.ItemAmmo;
import com.zxc74171.thaumicpotatoes.items.ItemDrinkableFood;
import com.zxc74171.thaumicpotatoes.items.ItemLauncher;
import com.zxc74171.thaumicpotatoes.items.ItemMetaHoe;
import com.zxc74171.thaumicpotatoes.items.ItemMetaPickaxe;
import com.zxc74171.thaumicpotatoes.items.ItemMetaShovel;
import com.zxc74171.thaumicpotatoes.items.ItemMetaSword;
import com.zxc74171.thaumicpotatoes.items.ItemMusicDisc;
import com.zxc74171.thaumicpotatoes.items.ItemPotainto;
import com.zxc74171.thaumicpotatoes.items.ItemPotatoShears;
import com.zxc74171.thaumicpotatoes.items.ItemTaintedCoreOrdo;
import com.zxc74171.thaumicpotatoes.items.ItemTaintedCorePerditio;
import com.zxc74171.thaumicpotatoes.items.ItemThaumicPotato;
import com.zxc74171.thaumicpotatoes.items.ItemTruth;
import com.zxc74171.thaumicpotatoes.items.PincerBladesTest;
import com.zxc74171.thaumicpotatoes.potatolauncher.Ammos;
import com.zxc74171.thaumicpotatoes.potatolauncher.LauncherProperties;
import com.zxc74171.thaumicpotatoes.sounds.ModSoundEvents;

import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.common.util.EnumHelper;


public class ModItems {
	public static ToolMaterial POTATOALLOYMAT = EnumHelper.addToolMaterial("POTATOALLOYMAT", 2, 2048, 10.0F, 0.0F, 30);
    public static ToolMaterial POTATORODMAT = EnumHelper.addToolMaterial("POTATORODMAT", 1, 2048, 0.0F, 16.0F, 30);
    public static ToolMaterial FROZENRODMAT = EnumHelper.addToolMaterial("FROZENRODMAT", 1, 0, 0.0F, 36.0F, 30);
    public static ToolMaterial POTATOSHEARSMAT = EnumHelper.addToolMaterial("POTATOSHEARSMAT", 1, 0, 0.0F, 6.0F, 30);
    
		public static Item BACKPACK = new ItemBackpack().setCreativeTab(ModCreativeTabs.TABS_TP2).setRegistryName("backpack").setUnlocalizedName("backpack");
		public static Item TRUTH = new ItemTruth().setCreativeTab(ModCreativeTabs.TABS_TP2).setRegistryName("truth").setUnlocalizedName("truth");
		public static Item TAINTED_CORE_PERDITIO = new ItemTaintedCorePerditio().setCreativeTab(ModCreativeTabs.TABS_TP2).setRegistryName("taintedcore_perditio")
				.setUnlocalizedName("taintedcore_perditio");
		public static Item TAINTED_CORE_ORDO = new ItemTaintedCoreOrdo().setCreativeTab(ModCreativeTabs.TABS_TP2).setRegistryName("taintedcore_ordo")
				.setUnlocalizedName("taintedcore_ordo");
		public static  Item POTATOROD = new ItemMetaSword(POTATORODMAT).setCreativeTab(ModCreativeTabs.TABS_TP2).setRegistryName("potatorod").setUnlocalizedName("potatorod");
		public static  Item FROZENROD = new ItemMetaSword(FROZENRODMAT).setCreativeTab(ModCreativeTabs.TABS_TP2).setRegistryName("frozenrod").setUnlocalizedName("frozenrod");
		public static  Item POTATOPICKAXE = new ItemMetaPickaxe(POTATOALLOYMAT).setCreativeTab(ModCreativeTabs.TABS_TP2).setRegistryName("potatopickaxe").setUnlocalizedName("potatopickaxe");
		public static  Item POTATOSHOVEL = new ItemMetaShovel(POTATOALLOYMAT).setCreativeTab(ModCreativeTabs.TABS_TP2).setRegistryName("potatoshovel").setUnlocalizedName("potatoshovel");
		public static  Item POTATOHOE = new ItemMetaHoe(POTATOALLOYMAT).setCreativeTab(ModCreativeTabs.TABS_TP2).setRegistryName("potatohoe").setUnlocalizedName("potatohoe");
		public static  Item POTAINTO = new ItemPotainto().setCreativeTab(ModCreativeTabs.TABS_TP2).setRegistryName("potainto")
				.setUnlocalizedName("potainto");

		public static  Item AMMO_NORMAL = new ItemAmmo("ammo_normal", ModCreativeTabs.TABS_TP2, Ammos.NORMAL);
		public static  Item AMMO_POISONOUS = new ItemAmmo("ammo_poisonous", ModCreativeTabs.TABS_TP2, Ammos.POISONOUS);
		public static  Item AMMO_BAKED = new ItemAmmo("ammo_baked", ModCreativeTabs.TABS_TP2, Ammos.BAKED);
        public static  Item POTATO_LAUNCHER = new ItemLauncher("potatolauncher", ModCreativeTabs.TABS_TP2, LauncherProperties.STANDARD_LAUNCHER, 1);
		
		public static  Item POTATO_SHEARS = new PincerBladesTest(POTATOSHEARSMAT).setCreativeTab(ModCreativeTabs.TABS_TP2).setRegistryName("potatoshears").setUnlocalizedName("potatoshears");
		
		public static  Item THAUMIC_POTATO = new ItemThaumicPotato("thaumicpotato", 2, 1f, false,
                new PotionEffect(Potion.getPotionById(8), 300, 2),
                new PotionEffect(Potion.getPotionById(1), 600, 2));
		public static  Item PETRIFIED_POTATO= new ItemThaumicPotato("petrifiedpotato", 1, 1f, false,
				new PotionEffect(Potion.getPotionById(2), 300, 5));
	    public static  Item POTATO_SOUP= new ItemThaumicPotato("potatosoup", 6, 1f, false);
	    public static  Item RAVIOLI= new ItemThaumicPotato("ravioli", 4, 3f, false);
	    public static  Item FRENCHFRIES= new ItemThaumicPotato("frenchfries", 6, 0.5f, false);
	    public static  Item CARBONATOR= new ItemThaumicPotato("carbonator", 20, 3f, false,
	    		new PotionEffect(Potion.getPotionById(18), 2400, 5),
                new PotionEffect(Potion.getPotionById(4), 2400, 5),
	            new PotionEffect(Potion.getPotionById(2), 2400, 5));
	    public static  Item MASHEDPOTATOES= new ItemThaumicPotato("mashedpotatoes", 6, 3f, false);
	    
	    public static  Item VODKA= new ItemDrinkableFood("vodka", 3, 1f, false,
	    		new PotionEffect(Potion.getPotionById(9), 200, 5));
		
	    
	    
		public static  Item POTATO_ALLOY = new Item().setCreativeTab(ModCreativeTabs.TABS_TP2).setRegistryName("potatoalloy")
				.setUnlocalizedName("potatoalloy");
		public static  Item ELDRITCH_HEART = new Item().setCreativeTab(ModCreativeTabs.TABS_TP2).setRegistryName("eldritchheart")
				.setUnlocalizedName("eldritchheart");
		public static  Item POTADOUGH = new Item().setCreativeTab(ModCreativeTabs.TABS_TP2).setRegistryName("potadough")
				.setUnlocalizedName("potadough");
		public static  Item ELDRITCH_FLESH = new Item().setCreativeTab(ModCreativeTabs.TABS_TP2).setRegistryName("eldritchflesh")
				.setUnlocalizedName("eldritchflesh");
		public static  Item ELDRITCH_BLOOD = new Item().setCreativeTab(ModCreativeTabs.TABS_TP2).setRegistryName("eldritchblood")
				.setUnlocalizedName("eldritchblood");

		public static  Item RECORD_FAREWELL = new ItemMusicDisc("farewell", ModSoundEvents.FAREWELL).setCreativeTab(ModCreativeTabs.TABS_TP2).setUnlocalizedName("record_farewell").setRegistryName("record_farewell");
			
		//DON'T FORGET TO ADD YOUR NEW SHIT HERE DAMMIT
		public static  Item[] ITEMS = new Item[] {THAUMIC_POTATO, POTATO_ALLOY, BACKPACK, POTATOROD, POTATOPICKAXE, POTATOHOE, POTATOSHOVEL, POTATO_SHEARS, POTATO_LAUNCHER, AMMO_NORMAL, AMMO_POISONOUS, AMMO_BAKED, TAINTED_CORE_PERDITIO, TAINTED_CORE_ORDO, ELDRITCH_FLESH, ELDRITCH_BLOOD, PETRIFIED_POTATO, POTATO_SOUP, RAVIOLI, VODKA, FRENCHFRIES, CARBONATOR, MASHEDPOTATOES, POTAINTO, ELDRITCH_HEART, POTADOUGH, FROZENROD, RECORD_FAREWELL, TRUTH};
	

    
		
}
