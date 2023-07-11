package com.besson.tutorialmod.world.gen;

import com.besson.tutorialmod.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraftforge.common.util.Lazy;

public enum OreType {
    ICE_ETHER(Lazy.of(ModBlocks.ICE_ETHER_ORE),8,25,50);
    private final Lazy<Block> block;
    private final int maxVeinSize;
    private final int minHeight;
    private final int maxHeight;

    OreType(Lazy<Block> block,int maxVeinSize,int minHeight,int maxHeight){
        this.block = block;
        this.maxVeinSize= maxVeinSize;
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
    }
    public Lazy<Block> getBlock() {
        return block;
    }

    public int getMaxVeinSize() {
        return maxVeinSize;
    }

    public int getMinHeight() {
        return minHeight;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public static OreType get(Block block) {
        for (OreType ore : values()) {
            if(block == ore.block) {
                return ore;
            }
        }
        return null;
    }
}
