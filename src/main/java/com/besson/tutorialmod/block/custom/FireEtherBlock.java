package com.besson.tutorialmod.block.custom;

import com.besson.tutorialmod.item.custom.FireEther;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
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
}
