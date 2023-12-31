package com.besson.tutorialmod.item.custom;

import com.besson.tutorialmod.util.TutorialTags;
import net.minecraft.block.AbstractFireBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Objects;

public class FireEther extends Item {

    public FireEther(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResultType onItemUseFirst(ItemStack stack, ItemUseContext context) {
        World world = context.getWorld();
        if (!world.isRemote){
            PlayerEntity playerEntity = Objects.requireNonNull(context.getPlayer());
            BlockState blockState = world.getBlockState(context.getPos());
            lightFire(blockState,context,playerEntity);
            stack.damageItem(1,playerEntity,player -> player.sendBreakAnimation(context.getHand()));
        }
        return super.onItemUseFirst(stack, context);
    }

    private static void lightFire(BlockState blockState, ItemUseContext context, PlayerEntity playerEntity) {
        boolean playerEntityNotOnFire = !playerEntity.isBurning();
        if (random.nextFloat() > 0.5f){
            lightEntityOnFire(playerEntity,10);
        } else if (playerEntityNotOnFire && blockIsRight(blockState)) {
            gainFRAndDB(playerEntity,context.getWorld(),context.getPos());
        }else {
            lightGround(context);
        }
    }

    private static void lightGround(ItemUseContext context) {
        PlayerEntity playerentity = context.getPlayer();
        World world = context.getWorld();
        BlockPos blockpos = context.getPos();

        BlockPos blockpos1 = blockpos.offset(context.getFace());
        if (AbstractFireBlock.canLightBlock(world, blockpos1, context.getPlacementHorizontalFacing())) {
            world.playSound(playerentity, blockpos1, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, random.nextFloat() * 0.4F + 0.8F);

            BlockState blockstate1 = AbstractFireBlock.getFireForPlacement(world, blockpos1);
            world.setBlockState(blockpos1, blockstate1, 11);

        }
    }

    private static void gainFRAndDB(PlayerEntity playerEntity, World world, BlockPos pos) {
        playerEntity.addPotionEffect(new EffectInstance(Effects.FIRE_RESISTANCE,200));
        world.destroyBlock(pos,false);
    }

    private static boolean blockIsRight(BlockState blockState) {
        return blockState.isIn(TutorialTags.Blocks.RIGHT_BLOCK);
    }

    public static void lightEntityOnFire(Entity entity, int second) {
        entity.setFire(second);
    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return true;
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {
        ItemStack container = itemStack.copy();
        if (container.attemptDamageItem(1,random,null)){
            return ItemStack.EMPTY;
        }else {
            return container;
        }
    }
}
