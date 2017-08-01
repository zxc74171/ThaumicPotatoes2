package com.zxc74171.thaumicpotatoes.entities;

import com.zxc74171.thaumicpotatoes.entities.RenderJagaimo.Factory;

import net.minecraft.client.model.ModelEvokerFangs;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.projectile.EntityEvokerFangs;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderPotatoFangs extends Render<EntityPotatoFangs>
{
    private static final ResourceLocation EVOKER_ILLAGER_FANGS = new ResourceLocation("thaumicpotatoes:textures/entity/potatouniversal.png");
    private final ModelEvokerFangs model = new ModelEvokerFangs();
    public static final Factory FACTORY = new Factory();
    
    public RenderPotatoFangs(RenderManager p_i47208_1_)
    {
        super(p_i47208_1_);
    }

    /**
     * Renders the desired {@code T} type Entity.
     */
    public void doRender(EntityPotatoFangs entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        float f = entity.getAnimationProgress(partialTicks);

        if (f != 0.0F)
        {
            float f1 = 2.0F;

            if (f > 0.9F)
            {
                f1 = (float)((double)f1 * ((1.0D - (double)f) / 0.10000000149011612D));
            }

            GlStateManager.pushMatrix();
            GlStateManager.disableCull();
            GlStateManager.enableAlpha();
            this.bindEntityTexture(entity);
            GlStateManager.translate((float)x, (float)y, (float)z);
            GlStateManager.rotate(90.0F - entity.rotationYaw, 0.0F, 1.0F, 0.0F);
            GlStateManager.scale(-f1, -f1, f1);
            float f2 = 0.03125F;
            GlStateManager.translate(0.0F, -0.626F, 0.0F);
            this.model.render(entity, f, 0.0F, 0.0F, entity.rotationYaw, entity.rotationPitch, 0.03125F);
            GlStateManager.popMatrix();
            GlStateManager.enableCull();
            super.doRender(entity, x, y, z, entityYaw, partialTicks);
        }
    }

    
    public static class Factory implements IRenderFactory<EntityPotatoFangs> {

        @Override
        public Render<? super EntityPotatoFangs> createRenderFor(RenderManager manager) {
            return new RenderPotatoFangs(manager);
        }

    }
    
	@Override
	protected ResourceLocation getEntityTexture(EntityPotatoFangs entity) {
		return EVOKER_ILLAGER_FANGS;
	}
}