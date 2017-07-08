package com.zxc74171.thaumicpotatoes.guidebooks;

import amerifrance.guideapi.api.GuideAPI;
import amerifrance.guideapi.api.IGuideBook;
import amerifrance.guideapi.api.IPage;
import amerifrance.guideapi.api.impl.Book;
import amerifrance.guideapi.api.impl.abstraction.CategoryAbstract;
import amerifrance.guideapi.api.impl.abstraction.EntryAbstract;
import amerifrance.guideapi.category.CategoryItemStack;
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
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import javax.annotation.Nonnull;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zxc74171.thaumicpotatoes.Reference;
import com.zxc74171.thaumicpotatoes.init.ModCreativeTabs;
import com.zxc74171.thaumicpotatoes.init.ModItems;

import java.awt.*;
import java.util.*;
import java.util.List;
import static com.zxc74171.thaumicpotatoes.ThaumicPotatoes2.proxy;


@amerifrance.guideapi.api.GuideBook
public class GuideBookVol2 implements IGuideBook {

    public static Book guideBookv2;
    
    

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
        categories.add(new CategoryItemStack(entries, proxy.translateToLocal("cat1"), new ItemStack(ModItems.ICON_CAT1)));
        
        

 entries = Maps.newLinkedHashMap();
 pages = Lists.newArrayList();
        pages.add(new PageText(proxy.translateToLocal("guide.2.1.1")));
        pages.add(PageIRecipe.newShaped(new ItemStack(Items.ACACIA_BOAT), "X X", "XXX", 'X', "plankWood"));
        pages.add(PageIRecipe.newShaped(new ItemStack(ModItems.POTATO_SOUP), "AB ", "C  ", "   ", 'A', Items.BAKED_POTATO, 'B', Items.MILK_BUCKET, 'C', Items.WHEAT_SEEDS));
        entries.put(new ResourceLocation(Reference.MODID, "2.1"), new EntryItemStack(pages, proxy.translateToLocal("entry21"), new ItemStack(ModItems.POTATO_SOUP)));
        
        pages = Lists.newArrayList();
        pages.add(new PageText(proxy.translateToLocal("guide.2.2.1")));
        pages.add(PageIRecipe.newShaped(new ItemStack(ModItems.POTADOUGH), "KSK", "SKS", "KSK", 'K', Items.BAKED_POTATO, 'S', Items.WHEAT));
        pages.add(PageIRecipe.newShaped(new ItemStack(ModItems.RAVIOLI), "A  ", "B  ", "   ", 'A', ModItems.POTADOUGH, 'B', Items.COOKED_BEEF));
        pages.add(new PageText(proxy.translateToLocal("guide.2.2.2")));
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
             
        categories.add(new CategoryItemStack(entries, proxy.translateToLocal("cat2"), new ItemStack(ModItems.ICON_CAT2)));

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
 
        
        categories.add(new CategoryItemStack(entries, proxy.translateToLocal("cat3"), new ItemStack(ModItems.ICON_CAT3)));

 entries = Maps.newLinkedHashMap();
 pages = Lists.newArrayList();
        pages.add(new PageText(proxy.translateToLocal("guide.4.1.1")));
        pages.add(PageIRecipe.newShaped(new ItemStack(ModItems.POTATOROD), "AWA","AWA","KSK",'A', ModItems.THAUMIC_POTATO, 'S', Items.IRON_SWORD, 'W', ModItems.POTATO_ALLOY, 'K', Items.DIAMOND_SWORD));
        pages.add(new PageText(proxy.translateToLocal("guide.4.1.2")));

        entries.put(new ResourceLocation(Reference.MODID, "4.1"), new EntryItemStack(pages, proxy.translateToLocal("entry41"), new ItemStack(ModItems.POTATOROD)));
             
        categories.add(new CategoryItemStack(entries, proxy.translateToLocal("cat4"), new ItemStack(ModItems.ICON_CAT4)));

 entries = Maps.newLinkedHashMap();
 pages = Lists.newArrayList();
        pages.add(new PageText(proxy.translateToLocal("guide.5.1.1")));
        pages.add(new PageText(proxy.translateToLocal("guide.5.1.2")));
        pages.add(new PageText(proxy.translateToLocal("guide.5.1.3")));
        pages.add(new PageImage(new ResourceLocation(Reference.MODID, "textures/guide/vol3.png")));
        pages.add(new PageImage(new ResourceLocation(Reference.MODID, "textures/guide/patata.png")));
        entries.put(new ResourceLocation(Reference.MODID, "5.1"), new EntryItemStack(pages, proxy.translateToLocal("entry51"), new ItemStack(Items.BAKED_POTATO)));
        
        pages = Lists.newArrayList();
        pages.add(new PageText(proxy.translateToLocal("guide.5.2.1")));
        entries.put(new ResourceLocation(Reference.MODID, "5.2"), new EntryItemStack(pages, proxy.translateToLocal("entry52"), new ItemStack(Items.ENDER_EYE)));

        categories.add(new CategoryItemStack(entries, proxy.translateToLocal("cat5"), new ItemStack(ModItems.ICON_CAT5)));


        
        // Setup the book's base information
        guideBookv2 = new Book();
        guideBookv2.setTitle(proxy.translateToLocal("bookname2"));
        guideBookv2.setDisplayName(proxy.translateToLocal("bookname2"));
        guideBookv2.setAuthor("zxc74171");
        guideBookv2.setColor(Color.BLUE);
        guideBookv2.setCreativeTab(ModCreativeTabs.TABS_TP2);
        guideBookv2.setCategoryList(categories);
        guideBookv2.setRegistryName(new ResourceLocation("thaumicpotatoes", "guideBookv2"));
        guideBookv2.setWelcomeMessage(proxy.translateToLocal("booktitle2"));
        return guideBookv2;
    }
    

    @SideOnly(Side.CLIENT)
    @Override
    public void handleModel(ItemStack bookStack) {
        // Use the default GuideAPI model 
        GuideAPI.setModel(guideBookv2);
    }

    @Override
    public void handlePost(ItemStack bookStack) {
        // Register a recipe so player's can obtain the book
    	ForgeRegistries.RECIPES.register(new ShapelessOreRecipe(null, bookStack, Items.BOOK, ModItems.THAUMIC_POTATO, ModItems.RAVIOLI).setRegistryName(guideBookv2.getRegistryName()));
    }
}