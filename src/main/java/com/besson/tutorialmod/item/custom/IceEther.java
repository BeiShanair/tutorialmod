package com.besson.tutorialmod.item.custom;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;

public class IceEther extends Item {
    public IceEther(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResultType onItemUseFirst(ItemStack stack, ItemUseContext context) {
        World world = context.getWorld();
        if (!world.isRemote){
            PlayerEntity playerEntity = Objects.requireNonNull(context.getPlayer());
            changeBlock(context,playerEntity,context.getWorld());
            stack.damageItem(1,playerEntity,player -> player.sendBreakAnimation(context.getHand()));
        }
        return super.onItemUseFirst(stack, context);
    }

    private static void changeBlock(ItemUseContext context, PlayerEntity playerEntity, World world) {
        BlockPos blockPos = context.getPos();
        BlockState blockState1 = Blocks.GRASS_PATH.getDefaultState();
        BlockState blockState2 = world.getBlockState(blockPos);
        if (random.nextFloat() > 0.5f){
            madeEntitySlow(playerEntity);
        } else if (blockState2.getBlock() == Blocks.GRASS_BLOCK) {
            world.setBlockState(blockPos,blockState1);

        }
    }

    private static void madeEntitySlow(PlayerEntity playerEntity) {
        playerEntity.addPotionEffect(new EffectInstance(Effects.SLOWNESS,200));
    }

    @Override
    public void addInformation(ItemStack itemStack, @Nullable World world, List<ITextComponent> textComponents, ITooltipFlag tooltipFlag) {
        if (Screen.hasShiftDown()){
            textComponents.add(new TranslationTextComponent("tooltip.tutorialmod.iceether_shift"));
        }else {
            textComponents.add(new TranslationTextComponent("tooltip.tutorialmod.iceether"));
        }
        super.addInformation(itemStack, world, textComponents, tooltipFlag);
    }
}
