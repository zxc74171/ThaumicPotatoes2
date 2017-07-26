package com.zxc74171.thaumicpotatoes.guidebooks;

import static com.zxc74171.thaumicpotatoes.ThaumicPotatoes2.proxy;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zxc74171.thaumicpotatoes.Reference;
import com.zxc74171.thaumicpotatoes.init.ModCreativeTabs;
import com.zxc74171.thaumicpotatoes.init.ModItems;

import amerifrance.guideapi.api.GuideAPI;
import amerifrance.guideapi.api.IGuideBook;
import amerifrance.guideapi.api.IPage;
import amerifrance.guideapi.api.impl.Book;
import amerifrance.guideapi.api.impl.abstraction.CategoryAbstract;
import amerifrance.guideapi.api.impl.abstraction.EntryAbstract;
import amerifrance.guideapi.category.CategoryResourceLocation;
import amerifrance.guideapi.entry.EntryItemStack;
import amerifrance.guideapi.page.PageFurnaceRecipe;
import amerifrance.guideapi.page.PageIRecipe;
import amerifrance.guideapi.page.PageImage;
import amerifrance.guideapi.page.PageText;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.ShapelessOreRecipe;


@amerifrance.guideapi.api.GuideBook
public class GuideBookVol3 implements IGuideBook {

    public static Book guideBookv3;
    
    

    @Nonnull
    @Override
    public Book buildBook() {
        // Create the map of entries. A LinkedHashMap is used to retain the order of entries.
    	
 Map<ResourceLocation, EntryAbstract> entries = new LinkedHashMap<ResourceLocation, EntryAbstract>();

        // Creation of our first entry.
        List<IPage> pages = new ArrayList<IPage>();
        pages.add(new PageText(proxy.translateToLocal("guide.1.1.1")));
        pages.add(new PageText(proxy.translateToLocal("guide.1.1.2")));
        pages.add(new PageText(proxy.translateToLocal("guide.1.1.3")));
        pages.add(new PageText(proxy.translateToLocal("guide.1.1.4")));
        entries.put(new ResourceLocation(Reference.MODID, "1.1"), new EntryItemStack(pages, proxy.translateToLocal("entry11"), new ItemStack(Items.FLINT_AND_STEEL)));
        
        pages = Lists.newArrayList();
        pages.add(new PageText(proxy.translateToLocal("guide.1.2.1")));
        pages.add(new PageText(proxy.translateToLocal("guide.1.2.2")));
        pages.add(new PageText(proxy.translateToLocal("guide.1.2.3")));
        pages.add(new PageText(proxy.translateToLocal("guide.1.2.4")));
        entries.put(new ResourceLocation(Reference.MODID, "1.2"), new EntryItemStack(pages, proxy.translateToLocal("entry12"), new ItemStack(Items.POTATO)));
        
        pages = Lists.newArrayList();
        pages.add(new PageText(proxy.translateToLocal("guide.1.3.1")));
        pages.add(new PageText(proxy.translateToLocal("guide.1.3.2")));
        pages.add(new PageText(proxy.translateToLocal("guide.1.3.3")));
        pages.add(new PageText(proxy.translateToLocal("guide.1.3.4")));
        entries.put(new ResourceLocation(Reference.MODID, "1.3"), new EntryItemStack(pages, proxy.translateToLocal("entry13"), new ItemStack(Items.WRITABLE_BOOK)));

        pages = Lists.newArrayList();
        pages.add(new PageText(proxy.translateToLocal("guide.1.4.1")));
        pages.add(new PageText(proxy.translateToLocal("guide.1.4.2")));
        pages.add(new PageImage(new ResourceLocation(Reference.MODID, "textures/guide/ore.png")));
        pages.add(new PageText(proxy.translateToLocal("guide.1.4.3")));
        entries.put(new ResourceLocation(Reference.MODID, "1.4"), new EntryItemStack(pages, proxy.translateToLocal("entry14"), new ItemStack(ModItems.PETRIFIED_POTATO)));

        pages = Lists.newArrayList();
        pages.add(new PageText(proxy.translateToLocal("guide.1.5.1")));
        pages.add(new PageFurnaceRecipe(ModItems.PETRIFIED_POTATO));
        pages.add(new PageText(proxy.translateToLocal("guide.1.5.3")));
        pages.add(new PageText(proxy.translateToLocal("guide.1.5.4")));
        pages.add(new PageImage(new ResourceLocation(Reference.MODID, "textures/guide/vol2recipe.png")));
        
        entries.put(new ResourceLocation(Reference.MODID, "1.5"), new EntryItemStack(pages, proxy.translateToLocal("entry15"), new ItemStack(ModItems.THAUMIC_POTATO)));

        
        
        
        List<CategoryAbstract> categories = new ArrayList<CategoryAbstract>();
        categories.add(new CategoryResourceLocation(entries, proxy.translateToLocal("cat1"), new ResourceLocation("thaumicpotatoes:textures/guide/icon_cat1.png")));
        
        

 entries = Maps.newLinkedHashMap();
 pages = Lists.newArrayList();
        pages.add(new PageText(proxy.translateToLocal("guide.2.1.1")));
        pages.add(PageIRecipe.newShaped(new ItemStack(ModItems.POTATO_SOUP), "AB ", "C  ", "   ", 'A', Items.BAKED_POTATO, 'B', Items.MILK_BUCKET, 'C', Items.WHEAT_SEEDS));
        entries.put(new ResourceLocation(Reference.MODID, "2.1"), new EntryItemStack(pages, proxy.translateToLocal("entry21"), new ItemStack(ModItems.POTATO_SOUP)));
        
        pages = Lists.newArrayList();
        pages.add(new PageText(proxy.translateToLocal("guide.2.2.1")));
        pages.add(PageIRecipe.newShaped(new ItemStack(ModItems.POTADOUGH, 2), "KSK", "SKS", "KSK", 'K', Items.BAKED_POTATO, 'S', Items.WHEAT));
        pages.add(PageIRecipe.newShaped(new ItemStack(ModItems.RAVIOLI), "AB ", "   ", "   ", 'A', ModItems.POTADOUGH, 'B', "dontLewdTheDragonLoli"));
        entries.put(new ResourceLocation(Reference.MODID, "2.2"), new EntryItemStack(pages, proxy.translateToLocal("entry22"), new ItemStack(ModItems.RAVIOLI)));

        pages = Lists.newArrayList();
        pages.add(new PageText(proxy.translateToLocal("guide.2.3.1")));
        pages.add(PageIRecipe.newShaped(new ItemStack(ModItems.VODKA), "AB ", "C  ", "   ", 'A', Items.POTATO, 'B', Items.SUGAR, 'C', Blocks.GLASS_PANE));
        pages.add(new PageText(proxy.translateToLocal("guide.2.3.2")));

        entries.put(new ResourceLocation(Reference.MODID, "2.3"), new EntryItemStack(pages, proxy.translateToLocal("entry23"), new ItemStack(ModItems.VODKA)));

        pages = Lists.newArrayList();
        pages.add(new PageText(proxy.translateToLocal("guide.2.4.1")));
        pages.add(PageIRecipe.newShaped(new ItemStack(ModItems.FRENCHFRIES), "AB ", "C  ", "   ", 'A', Items.BAKED_POTATO, 'B', Items.WHEAT_SEEDS, 'C', Items.PAPER));
        pages.add(new PageText(proxy.translateToLocal("guide.2.4.2")));

        entries.put(new ResourceLocation(Reference.MODID, "2.4"), new EntryItemStack(pages, proxy.translateToLocal("entry24"), new ItemStack(ModItems.FRENCHFRIES)));

        pages = Lists.newArrayList();
        pages.add(new PageText(proxy.translateToLocal("guide.2.5.1")));
        pages.add(PageIRecipe.newShaped(new ItemStack(ModItems.CARBONATOR), "RBR","SKS","RBR",'B', Items.BAKED_POTATO, 'K', ModItems.POTADOUGH, 'S', Items.BREAD, 'R', ModItems.RAVIOLI));
        pages.add(new PageText(proxy.translateToLocal("guide.2.5.2")));
        pages.add(new PageText(proxy.translateToLocal("guide.2.5.3")));

       
        entries.put(new ResourceLocation(Reference.MODID, "2.5"), new EntryItemStack(pages, proxy.translateToLocal("entry25"), new ItemStack(ModItems.CARBONATOR)));
        
        categories.add(new CategoryResourceLocation(entries, proxy.translateToLocal("cat2"), new ResourceLocation("thaumicpotatoes:textures/guide/icon_cat2.png")));

 entries = Maps.newLinkedHashMap();
 pages = Lists.newArrayList();
        pages.add(new PageText(proxy.translateToLocal("guide.3.1.1")));
        pages.add(PageIRecipe.newShaped(new ItemStack(ModItems.POTATO_ALLOY), "AB ", "CD ", "   ", 'A', ModItems.THAUMIC_POTATO, 'B', ModItems.POTADOUGH, 'C', Items.IRON_INGOT, 'D', Items.GOLD_INGOT));
        pages.add(new PageText(proxy.translateToLocal("guide.3.1.2")));
        entries.put(new ResourceLocation(Reference.MODID, "3.1"), new EntryItemStack(pages, proxy.translateToLocal("entry31"), new ItemStack(ModItems.POTATO_ALLOY)));
        
        pages = Lists.newArrayList();
        pages.add(new PageText(proxy.translateToLocal("guide.3.2.1")));
        pages.add(PageIRecipe.newShaped(new ItemStack(ModItems.POTATOPICKAXE), "AAA", " B ", " B ", 'A', ModItems.POTATO_ALLOY, 'B', Items.STICK));
        pages.add(PageIRecipe.newShaped(new ItemStack(ModItems.POTATOSHOVEL), " A ", " B ", " B ", 'A', ModItems.POTATO_ALLOY, 'B', Items.STICK));
        pages.add(PageIRecipe.newShaped(new ItemStack(ModItems.POTATOHOE), "AA ", " B ", " B ", 'A', ModItems.POTATO_ALLOY, 'B', Items.STICK));
        pages.add(new PageText(proxy.translateToLocal("guide.3.2.2")));
        pages.add(new PageText(proxy.translateToLocal("guide.3.2.3")));
        entries.put(new ResourceLocation(Reference.MODID, "3.2"), new EntryItemStack(pages, proxy.translateToLocal("entry32"), new ItemStack(ModItems.POTATOPICKAXE)));
 
        pages = Lists.newArrayList();
        pages.add(new PageText(proxy.translateToLocal("guide.3.4.1")));
        pages.add(PageIRecipe.newShaped(new ItemStack(Items.POISONOUS_POTATO), "A  ", "B  ", "   ", 'A', Items.POTATO, 'B', Items.ROTTEN_FLESH));
        pages.add(PageIRecipe.newShaped(new ItemStack(Items.POISONOUS_POTATO, 8), "PPP","PIP","PPP",'P', Items.POTATO, 'I', Items.SPIDER_EYE));
        pages.add(new PageText(proxy.translateToLocal("guide.3.4.2")));
        entries.put(new ResourceLocation(Reference.MODID, "3.4"), new EntryItemStack(pages, proxy.translateToLocal("entry34"), new ItemStack(Items.POISONOUS_POTATO)));
 
        pages = Lists.newArrayList();
        pages.add(new PageText(proxy.translateToLocal("guide.3.3.1")));
        pages.add(new PageText(proxy.translateToLocal("guide.3.3.2")));
        pages.add(PageIRecipe.newShaped(new ItemStack(ModItems.BACKPACK), "AWA","WPW","WPW",'A', ModItems.POTATO_ALLOY, 'W', Blocks.WOOL, 'P', ModItems.THAUMIC_POTATO));
        pages.add(new PageText(proxy.translateToLocal("guide.3.3.3")));
        entries.put(new ResourceLocation(Reference.MODID, "3.3"), new EntryItemStack(pages, proxy.translateToLocal("entry33"), new ItemStack(ModItems.BACKPACK)));
 
        categories.add(new CategoryResourceLocation(entries, proxy.translateToLocal("cat3"), new ResourceLocation("thaumicpotatoes:textures/guide/icon_cat3.png")));

 entries = Maps.newLinkedHashMap();
 pages = Lists.newArrayList();
        pages.add(new PageText(proxy.translateToLocal("guide.4.1.1")));
        pages.add(PageIRecipe.newShaped(new ItemStack(ModItems.POTATOROD), "AWA","AWA","KSK",'A', ModItems.THAUMIC_POTATO, 'S', Items.IRON_SWORD, 'W', ModItems.POTATO_ALLOY, 'K', Items.DIAMOND));
        pages.add(new PageText(proxy.translateToLocal("guide.4.1.2")));

        entries.put(new ResourceLocation(Reference.MODID, "4.1"), new EntryItemStack(pages, proxy.translateToLocal("entry41"), new ItemStack(ModItems.POTATOROD)));
        
        pages = Lists.newArrayList();
        pages.add(new PageText(proxy.translateToLocal("guide.4.2.1")));
        pages.add(PageIRecipe.newShaped(new ItemStack(ModItems.FROZENROD), "ISI","ASA","KSK",'I', Blocks.PACKED_ICE, 'A', ModItems.CARBONATOR, 'S', ModItems.POTATOROD, 'K', Items.DIAMOND_SWORD));
        pages.add(new PageText(proxy.translateToLocal("guide.4.2.2")));

        entries.put(new ResourceLocation(Reference.MODID, "4.2"), new EntryItemStack(pages, proxy.translateToLocal("entry42"), new ItemStack(ModItems.FROZENROD)));
       
        pages = Lists.newArrayList();
        pages.add(new PageText(proxy.translateToLocal("guide.4.3.1")));
        pages.add(new PageText(proxy.translateToLocal("guide.4.3.2")));
        pages.add(PageIRecipe.newShaped(new ItemStack(ModItems.POTATO_LAUNCHER), "CCP","RSC","SRC",'S', Items.STICK, 'C', ModItems.CARBONATOR, 'R', ModItems.POTATOROD, 'P', ModItems.THAUMIC_POTATO));
        pages.add(PageIRecipe.newShaped(new ItemStack(ModItems.AMMO_NORMAL, 8), "PPP","PIP","PPP",'P', Items.POTATO, 'I', Items.IRON_INGOT));
        pages.add(PageIRecipe.newShaped(new ItemStack(ModItems.AMMO_POISONOUS, 8), "PPP","PIP","PPP",'P', Items.POISONOUS_POTATO, 'I', Items.IRON_INGOT));
        pages.add(PageIRecipe.newShaped(new ItemStack(ModItems.AMMO_BAKED, 8), "PPP","PIP","PPP",'P', Items.BAKED_POTATO, 'I', Items.IRON_INGOT));
        pages.add(new PageText(proxy.translateToLocal("guide.4.3.3")));

        entries.put(new ResourceLocation(Reference.MODID, "4.3"), new EntryItemStack(pages, proxy.translateToLocal("entry43"), new ItemStack(ModItems.POTATO_LAUNCHER)));
  
        pages = Lists.newArrayList();
        pages.add(new PageText(proxy.translateToLocal("guide.4.4.1")));
        pages.add(PageIRecipe.newShaped(new ItemStack(ModItems.POTATO_SHEARS), "CRT","CPR","SCC",'S', ModItems.POTATO_LAUNCHER,'T', ModItems.RECORD_FAREWELL, 'C', ModItems.POTATO_ALLOY, 'R', ModItems.POTATOROD, 'P', ModItems.FROZENROD));
        pages.add(new PageText(proxy.translateToLocal("guide.4.4.2")));
        pages.add(new PageText(proxy.translateToLocal("guide.4.4.3")));
        pages.add(new PageText(proxy.translateToLocal("guide.4.4.4")));
        pages.add(new PageText(proxy.translateToLocal("guide.4.4.5")));

        entries.put(new ResourceLocation(Reference.MODID, "4.4"), new EntryItemStack(pages, proxy.translateToLocal("entry44"), new ItemStack(ModItems.POTATO_SHEARS)));
 
        categories.add(new CategoryResourceLocation(entries, proxy.translateToLocal("cat4"), new ResourceLocation("thaumicpotatoes:textures/guide/icon_cat4.png")));

        entries = Maps.newLinkedHashMap();
        
        pages = Lists.newArrayList();
        pages.add(new PageText(proxy.translateToLocal("guide.5.3.1")));
        pages.add(new PageText(proxy.translateToLocal("guide.5.3.2")));
        pages.add(new PageText(proxy.translateToLocal("guide.5.3.3")));
        pages.add(new PageText(proxy.translateToLocal("guide.5.3.4")));
        entries.put(new ResourceLocation(Reference.MODID, "5.3"), new EntryItemStack(pages, proxy.translateToLocal("entry53"), new ItemStack(Items.WRITTEN_BOOK)));

        pages = Lists.newArrayList();
        pages.add(new PageText(proxy.translateToLocal("guide.5.4.1")));
        pages.add(PageIRecipe.newShaped(new ItemStack(ModItems.TAINTED_CORE), "GEG","SKS","GSG",'S', ModItems.CARBONATOR, 'K', ModItems.THAUMIC_POTATO, 'G', Items.POISONOUS_POTATO, 'E', Items.ENDER_EYE));
        pages.add(new PageImage(new ResourceLocation(Reference.MODID, "textures/guide/text.png")));

        entries.put(new ResourceLocation(Reference.MODID, "5.4"), new EntryItemStack(pages, proxy.translateToLocal("entry54"), new ItemStack(ModItems.TAINTED_CORE)));

 
        pages = Lists.newArrayList();
 
        pages.add(new PageText(proxy.translateToLocal("guide.5.1.1")));
        pages.add(new PageText(proxy.translateToLocal("guide.5.1.2")));
        pages.add(new PageText(proxy.translateToLocal("guide.5.1.3")));
        pages.add(new PageImage(new ResourceLocation(Reference.MODID, "textures/guide/vol3.png")));
        pages.add(new PageImage(new ResourceLocation(Reference.MODID, "textures/guide/patata.png")));
        entries.put(new ResourceLocation(Reference.MODID, "5.1"), new EntryItemStack(pages, proxy.translateToLocal("entry51"), new ItemStack(ModItems.ELDRITCH_HEART)));
        
        categories.add(new CategoryResourceLocation(entries, proxy.translateToLocal("cat5"), new ResourceLocation("thaumicpotatoes:textures/guide/icon_cat5.png")));

 entries = Maps.newLinkedHashMap();
 pages = Lists.newArrayList();
        pages.add(new PageText(proxy.translateToLocal("guide.6.1.2")));
        pages.add(new PageText(proxy.translateToLocal("guide.6.1.3")));
        pages.add(new PageImage(new ResourceLocation(Reference.MODID, "textures/guide/tainto.png")));
 
        entries.put(new ResourceLocation(Reference.MODID, "6.1"), new EntryItemStack(pages, proxy.translateToLocal("entry61"), new ItemStack(ModItems.POTAINTO)));
        
        
        pages = Lists.newArrayList();
        pages.add(new PageText(proxy.translateToLocal("guide.6.2.1")));

        entries.put(new ResourceLocation(Reference.MODID, "6.2"), new EntryItemStack(pages, proxy.translateToLocal("entry62"), new ItemStack(ModItems.TRUTH)));

        categories.add(new CategoryResourceLocation(entries, proxy.translateToLocal("cat6"), new ResourceLocation("thaumicpotatoes:textures/guide/icon_cat6.png")));

        
        // Setup the book's base information
        guideBookv3 = new Book();
        guideBookv3.setTitle(proxy.translateToLocal("bookname3"));
        guideBookv3.setDisplayName(proxy.translateToLocal("bookname3"));
        guideBookv3.setAuthor("zxc74171");
        guideBookv3.setColor(Color.RED);
        guideBookv3.setCreativeTab(ModCreativeTabs.TABS_TP2);
        guideBookv3.setCategoryList(categories);
        guideBookv3.setRegistryName(new ResourceLocation("thaumicpotatoes", "guideBookv3"));
        guideBookv3.setWelcomeMessage(proxy.translateToLocal("booktitle3"));
        return guideBookv3;
    }
    

    @SideOnly(Side.CLIENT)
    @Override
    public void handleModel(ItemStack bookStack) {
        // Use the default GuideAPI model 
        GuideAPI.setModel(guideBookv3);
    }

    @Override
    public void handlePost(ItemStack bookStack) {
        // Register a recipe so player's can obtain the book
    	ForgeRegistries.RECIPES.register(new ShapelessOreRecipe(null, bookStack, Items.BOOK, ModItems.BACKPACK, ModItems.ELDRITCH_HEART, Items.BLAZE_ROD).setRegistryName(guideBookv3.getRegistryName()));
    }
}