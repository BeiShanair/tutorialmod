package com.besson.tutorialmod.block.custom;

import com.besson.tutorialmod.item.custom.FireEther;
import com.besson.tutorialmod.util.ModSoundEvents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

import java.util.Random;

public class FireEtherBlock extends Block {

    public FireEtherBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void onEntityWalk(World world, BlockPos pos, Entity entity) {
        FireEther.lightEntityOnFire(entity,10);
        super.onEntityWalk(world,pos,entity);
    }

    @Override
    public void animateTick(BlockState blockState, World world, BlockPos blockPos, Random random) {
        float chance = 0.25f;
        if (chance < random.nextFloat()){
            world.addParticle(ParticleTypes.FLAME,blockPos.getX()+random.nextDouble(),blockPos.getY()+0.5d,
                    blockPos.getZ()+random.nextDouble(),0d,0.1d, 0d);
            world.addParticle(new BlockParticleData(ParticleTypes.BLOCK,blockState),blockPos.getX(),blockPos.getY(),
                    blockPos.getZ(),0d,0.1d, 0d);
        }
        super.animateTick(blockState, world, blockPos, random);
    }

    @Override
    public ActionResultType onBlockActivated(BlockState blockState, World world, BlockPos blockPos, PlayerEntity player,
                                             Hand hand, BlockRayTraceResult blockRayTraceResult) {
        if (!world.isRemote()){
            if (hand == Hand.MAIN_HAND){
                world.playSound(null,blockPos, ModSoundEvents.SMALL_EXPLOSION.get(), SoundCategory.BLOCKS, 1,1);
            }
        }
        return super.onBlockActivated(blockState, world, blockPos, player, hand, blockRayTraceResult);
    }
}
