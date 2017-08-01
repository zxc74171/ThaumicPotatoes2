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
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.ShapelessOreRecipe;


@amerifrance.guideapi.api.GuideBook
public class GuideBookVol1 implements IGuideBook {

    public static Book guideBookv1;
    
    

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
        entries.put(new ResourceLocation(Reference.MODID, "1.1"), new EntryItemStack(pages, proxy.translateToLocal("entry11"), new ItemStack(ModItems.TRUTH)));
        
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

        pages = Lists.newArrayList();
        pages.add(new PageText(proxy.translateToLocal("guide.1.6.1")));
        pages.add(new PageImage(new ResourceLocation(Reference.MODID, "textures/guide/villager.png")));
        pages.add(new PageText(proxy.translateToLocal("guide.1.6.2")));
        
        entries.put(new ResourceLocation(Reference.MODID, "1.6"), new EntryItemStack(pages, proxy.translateToLocal("entry16"), new ItemStack(Items.EMERALD)));

        
        
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
             
        categories.add(new CategoryResourceLocation(entries, proxy.translateToLocal("cat2"), new ResourceLocation("thaumicpotatoes:textures/guide/icon_cat2.png")));
        
        // Setup the book's base information
        guideBookv1 = new Book();
        guideBookv1.setTitle(proxy.translateToLocal("bookname1"));
        guideBookv1.setDisplayName(proxy.translateToLocal("bookname1"));
        guideBookv1.setAuthor("zxc74171");
        guideBookv1.setColor(Color.GREEN);
        guideBookv1.setCreativeTab(ModCreativeTabs.TABS_TP2);
        guideBookv1.setCategoryList(categories);
        guideBookv1.setRegistryName(new ResourceLocation("thaumicpotatoes", "guideBookv1"));
        guideBookv1.setWelcomeMessage(proxy.translateToLocal("booktitle1"));
        return guideBookv1;
    }
    

    @SideOnly(Side.CLIENT)
    @Override
    public void handleModel(ItemStack bookStack) {
        // Use the default GuideAPI model 
        GuideAPI.setModel(guideBookv1);
    }

    @Override
    public void handlePost(ItemStack bookStack) {
        // Register a recipe so player's can obtain the book
    	ForgeRegistries.RECIPES.register(new ShapelessOreRecipe(null, bookStack, Items.BOOK, Items.POTATO).setRegistryName(guideBookv1.getRegistryName()));
    }
}