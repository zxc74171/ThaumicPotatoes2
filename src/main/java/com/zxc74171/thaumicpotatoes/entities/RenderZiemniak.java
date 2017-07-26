package com.zxc74171.thaumicpotatoes.entities;

import javax.annotation.Nonnull;

import net.minecraft.client.model.ModelSquid;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderZiemniak extends RenderLiving<EntityZiemniak> {

    private ResourceLocation mobTexture = new ResourceLocation("thaumicpotatoes:textures/entity/potatouniversal.png");

    public static final Factory FACTORY = new Factory();

    public RenderZiemniak(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelSquid(), 1F);
    }

    @Override
    @Nonnull
    protected ResourceLocation getEntityTexture(@Nonnull EntityZiemniak entity) {
        return mobTexture;
    }

    public static class Factory implements IRenderFactory<EntityZiemniak> {

        @Override
        public Render<? super EntityZiemniak> createRenderFor(RenderManager manager) {
            return new RenderZiemniak(manager);
        }

    }

}