package com.zxc74171.thaumicpotatoes.entities;

import javax.annotation.Nonnull;

import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderJagaimo extends RenderLiving<EntityJagaimo> {

    private ResourceLocation mobTexture = new ResourceLocation("thaumicpotatoes:textures/entity/potatouniversal.png");

    public static final Factory FACTORY = new Factory();

    public RenderJagaimo(RenderManager rendermanagerIn) {
        // We use the vanilla zombie model here and we simply
        // retexture it. Of course you can make your own model
        super(rendermanagerIn, new ModelZombie(), 0.5F);
    }

    @Override
    @Nonnull
    protected ResourceLocation getEntityTexture(@Nonnull EntityJagaimo entity) {
        return mobTexture;
    }

    public static class Factory implements IRenderFactory<EntityJagaimo> {

        @Override
        public Render<? super EntityJagaimo> createRenderFor(RenderManager manager) {
            return new RenderJagaimo(manager);
        }

    }

}