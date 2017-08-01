package com.zxc74171.thaumicpotatoes.entities;

import javax.annotation.Nonnull;

import com.zxc74171.thaumicpotatoes.entities.RenderFryCook.Factory;

import net.minecraft.client.model.ModelBlaze;
import net.minecraft.client.model.ModelVex;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderBlaze;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityVex;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderFrenchFries extends RenderLiving<EntityMob>
{
    private static final ResourceLocation VEX_TEXTURE = new ResourceLocation("thaumicpotatoes:textures/entity/potatouniversal.png");
    private static final ResourceLocation VEX_CHARGING_TEXTURE = new ResourceLocation("thaumicpotatoes:textures/entity/potatouniversal.png");
    private int modelVersion;
    public static final Factory FACTORY = new Factory();
    
    public RenderFrenchFries(RenderManager rendermanagerIn)
    {
        super(rendermanagerIn, new ModelBlaze(), 0.5F);
    }

    /**
     * Renders the desired {@code T} type Entity.
     */
    public void doRender(EntityBlaze entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        int i = ((ModelVex)this.mainModel).getModelVersion();

        if (i != this.modelVersion)
        {
            this.mainModel = new ModelVex();
            this.modelVersion = i;
        }

        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    /**
     * Allows the render to do state modifications necessary before the model is rendered.
     */
    protected void preRenderCallback(EntityFrenchFries entitylivingbaseIn, float partialTickTime)
    {
        GlStateManager.scale(0.4F, 0.4F, 0.4F);
    }
    
    protected ResourceLocation getEntityTexture(EntityMob entity) {
		return ((EntityFrenchFries) entity).isCharging() ? VEX_CHARGING_TEXTURE : VEX_TEXTURE;
	}
    
    public static class Factory implements IRenderFactory<EntityFrenchFries> {

        @Override
        public Render<? super EntityFrenchFries> createRenderFor(RenderManager manager) {
            return new RenderFrenchFries(manager);
        }

    }
	
}