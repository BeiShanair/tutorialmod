package com.besson.tutorialmod.screens;

import com.besson.tutorialmod.TutorialMod;
import com.besson.tutorialmod.containers.RainingChangerContainer;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class RainingChangerScreen extends ContainerScreen<RainingChangerContainer> {
    private final ResourceLocation GUI = new ResourceLocation(TutorialMod.MOD_ID,"textures/gui/raining_changer_gui.png");
    public RainingChangerScreen(RainingChangerContainer screenContainer, PlayerInventory playerInventory, ITextComponent textComponent) {
        super(screenContainer,playerInventory,textComponent);
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float particleTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, particleTicks);
        this.renderHoveredTooltip(matrixStack,mouseX,mouseY);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int x, int y) {
        RenderSystem.color4f(1f,1f,1f,1f);
        this.minecraft.getTextureManager().bindTexture(GUI);
        int i = this.guiLeft;
        int j = this.guiTop;
        this.blit(matrixStack,i,j,0,0,this.xSize,this.ySize);

        if (container.isRaining()){
            this.blit(matrixStack,i+82,j+9,176,0,13,17);
        }
    }
}
