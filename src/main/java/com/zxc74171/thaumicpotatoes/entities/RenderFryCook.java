package com.zxc74171.thaumicpotatoes.entities;

import javax.annotation.Nonnull;

import com.zxc74171.thaumicpotatoes.entities.RenderJagaimo.Factory;

import net.minecraft.client.model.ModelIllager;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySpellcasterIllager;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderFryCook extends RenderLiving<EntityMob>
{
    private static final ResourceLocation FRY_COOK = new ResourceLocation("thaumicpotatoes:textures/entity/mcdonald.png");
    public static final Factory FACTORY = new Factory();
    
    public RenderFryCook(RenderManager p_i47207_1_)
    {
        super(p_i47207_1_, new ModelIllager(0.0F, 0.0F, 64, 64), 0.5F);
        this.addLayer(new LayerHeldItem(this)
        {
            public void doRenderLayer(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
            {
                if (((EntitySpellcasterIllager)entitylivingbaseIn).isSpellcasting())
                {
                    super.doRenderLayer(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scale);
                }
            }
            protected void translateToHand(EnumHandSide p_191361_1_)
            {
                ((ModelIllager)this.livingEntityRenderer.getMainModel()).getArm(p_191361_1_).postRender(0.0625F);
            }
        });
    }

    /**
     * Allows the render to do state modifications necessary before the model is rendered.
     */
    protected void preRenderCallback(EntityMob entitylivingbaseIn, float partialTickTime)
    {
        float f = 0.9375F;
        GlStateManager.scale(0.9375F, 0.9375F, 0.9375F);
    }

    public static class Factory implements IRenderFactory<EntityFryCook> {

        @Override
        public Render<? super EntityFryCook> createRenderFor(RenderManager manager) {
            return new RenderFryCook(manager);
        }

    }

	@Override
	protected ResourceLocation getEntityTexture(EntityMob entity) {
		return FRY_COOK;
	}
}