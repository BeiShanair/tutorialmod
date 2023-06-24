package com.besson.tutorialmod.block.custom;

import com.besson.tutorialmod.item.custom.FireEther;
import jdk.nashorn.internal.ir.Statement;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class FireEtherBlock extends Block {

    public FireEtherBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void onEntityWalk(World world, BlockPos pos, Entity entity) {
        FireEther.lightEntityOnFire(entity,10);
        super.onEntityWalk(world,pos,entity);
    }
}
