package com.zxc74171.thaumicpotatoes.init;


import com.zxc74171.thaumicpotatoes.Reference;
import com.zxc74171.thaumicpotatoes.ThaumicPotatoes2;
import com.zxc74171.thaumicpotatoes.entities.EntityAmmoBaked;
import com.zxc74171.thaumicpotatoes.entities.EntityAmmoNormal;
import com.zxc74171.thaumicpotatoes.entities.EntityAmmoPoisonous;
import com.zxc74171.thaumicpotatoes.entities.EntityFrenchFries;
import com.zxc74171.thaumicpotatoes.entities.EntityFryCook;
import com.zxc74171.thaumicpotatoes.entities.EntityJagaimo;
import com.zxc74171.thaumicpotatoes.entities.EntityPotatoFangs;
import com.zxc74171.thaumicpotatoes.entities.EntityPotatoMobProjectile;
import com.zxc74171.thaumicpotatoes.entities.EntityZiemniak;
import com.zxc74171.thaumicpotatoes.entities.RenderAmmoBaked;
import com.zxc74171.thaumicpotatoes.entities.RenderAmmoNormal;
import com.zxc74171.thaumicpotatoes.entities.RenderAmmoPoisonous;
import com.zxc74171.thaumicpotatoes.entities.RenderEntityPotatoMobProjectile;
import com.zxc74171.thaumicpotatoes.entities.RenderFrenchFries;
import com.zxc74171.thaumicpotatoes.entities.RenderFryCook;
import com.zxc74171.thaumicpotatoes.entities.RenderJagaimo;
import com.zxc74171.thaumicpotatoes.entities.RenderPotatoFangs;
import com.zxc74171.thaumicpotatoes.entities.RenderZiemniak;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModEntities {

    public static void init() {
        // Mobs
        int id = 420;
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MODID, "jagaimo"), EntityJagaimo.class, "Jagaimo", id++, ThaumicPotatoes2.instance, 64, 3, true, 0xffcc00, 0xff0000);
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MODID, "ziemniak"), EntityZiemniak.class, "Ziemniak", id++, ThaumicPotatoes2.instance, 64, 3, true, 0xffcc00, 0x0000ff);
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MODID, "frycook"), EntityFryCook.class, "FryCook", id++, ThaumicPotatoes2.instance, 64, 3, true, 0xffcc00, 0xffcc00);
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MODID, "frenchfries"), EntityFrenchFries.class, "FrenchFries", id++, ThaumicPotatoes2.instance, 64, 3, true, 0xffcc00, 0xffcc00);
        
        // Projectiles
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MODID, "potatoprojectile"), EntityPotatoMobProjectile.class, "PotatoProjectile", id++, ThaumicPotatoes2.instance, 64, 3, true);
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MODID, "ammo_normal"), EntityAmmoNormal.class, "AmmoNormalEntity", id++, ThaumicPotatoes2.instance, 64, 3, true);
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MODID, "ammo_poisonous"), EntityAmmoPoisonous.class, "AmmoPoisonousEntity", id++, ThaumicPotatoes2.instance, 64, 3, true);
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MODID, "ammo_baked"), EntityAmmoBaked.class, "AmmoBakedEntity", id++, ThaumicPotatoes2.instance, 64, 3, true);
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MODID, "potatofangs"), EntityPotatoFangs.class, "PotatoFangsEntity", id++, ThaumicPotatoes2.instance, 64, 3, true);
        

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
        RenderingRegistry.registerEntityRenderingHandler(EntityFryCook.class, RenderFryCook.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityFrenchFries.class, RenderFrenchFries.FACTORY);
        
        RenderingRegistry.registerEntityRenderingHandler(EntityPotatoMobProjectile.class, RenderEntityPotatoMobProjectile.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityAmmoNormal.class, RenderAmmoNormal.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityAmmoPoisonous.class, RenderAmmoPoisonous.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityAmmoBaked.class, RenderAmmoBaked.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityPotatoFangs.class, RenderPotatoFangs.FACTORY);
    }

}