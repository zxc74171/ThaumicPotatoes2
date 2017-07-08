package com.zxc74171.thaumicpotatoes.init;


import com.zxc74171.thaumicpotatoes.Reference;
import com.zxc74171.thaumicpotatoes.ThaumicPotatoes2;
import com.zxc74171.thaumicpotatoes.entities.EntityAmmoPoisonous;
import com.zxc74171.thaumicpotatoes.entities.EntityAmmoNormal;
import com.zxc74171.thaumicpotatoes.entities.EntityJagaimo;
import com.zxc74171.thaumicpotatoes.entities.EntityPotatoMobProjectile;
import com.zxc74171.thaumicpotatoes.entities.EntityZiemniak;
import com.zxc74171.thaumicpotatoes.entities.RenderEntityPotatoMobProjectile;
import com.zxc74171.thaumicpotatoes.entities.RenderJagaimo;
import com.zxc74171.thaumicpotatoes.entities.RenderAmmoNormal;
import com.zxc74171.thaumicpotatoes.entities.RenderAmmoPoisonous;
import com.zxc74171.thaumicpotatoes.entities.RenderZiemniak;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModEntities {

    public static void init() {
        // Every entity in our mod has an ID (local to this mod)
        int id = 420;
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MODID, "jagaimo"), EntityJagaimo.class, "Jagaimo", id++, ThaumicPotatoes2.instance, 64, 3, true, 0x996600, 0x00ff00);
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MODID, "ziemniak"), EntityZiemniak.class, "Ziemniak", id++, ThaumicPotatoes2.instance, 64, 3, true, 0x996600, 0x00ff00);
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MODID, "potatoprojectile"), EntityPotatoMobProjectile.class, "PotatoProjectile", id++, ThaumicPotatoes2.instance, 64, 3, true);
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MODID, "ironboltentity"), EntityAmmoNormal.class, "IronBoltEntity", id++, ThaumicPotatoes2.instance, 64, 3, true);
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MODID, "freezeboltentity"), EntityAmmoPoisonous.class, "FreezeBoltEntity", id++, ThaumicPotatoes2.instance, 64, 3, true);


        // We want our mob to spawn in Plains and ice plains biomes. If you don't add this then it will not spawn automatically
        // but you can of course still make it spawn manually
        EntityRegistry.addSpawn(EntityJagaimo.class, 100, 3, 5, EnumCreatureType.MONSTER);
        EntityRegistry.addSpawn(EntityZiemniak.class, 100, 3, 5, EnumCreatureType.MONSTER);
        
        // This is the loot table for our mob
    }

    @SideOnly(Side.CLIENT)
    public static void initModels() {
    	
    	net.minecraft.client.renderer.entity.RenderManager manager = Minecraft.getMinecraft().getRenderManager();
        RenderingRegistry.registerEntityRenderingHandler(EntityJagaimo.class, RenderJagaimo.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityZiemniak.class, RenderZiemniak.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityPotatoMobProjectile.class, RenderEntityPotatoMobProjectile.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityAmmoNormal.class, RenderAmmoNormal.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityAmmoPoisonous.class, RenderAmmoPoisonous.FACTORY);
    }

}