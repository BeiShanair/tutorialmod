package com.besson.tutorialmod.entity.render;

import com.besson.tutorialmod.TutorialMod;
import com.besson.tutorialmod.entity.custom.TigerEntity;
import com.besson.tutorialmod.entity.model.TigerModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class TigerRenderer extends MobRenderer<TigerEntity, TigerModel<TigerEntity>> {
    public TigerRenderer(EntityRendererManager rendererManager) {
        super(rendererManager,new TigerModel<>(),0.7F);
    }

    protected static final ResourceLocation TEXTURE =
            new ResourceLocation(TutorialMod.MOD_ID,"textures/entity/tiger.png");

    @Override
    public ResourceLocation getEntityTexture(TigerEntity entity) {
        return TEXTURE;
    }
}
