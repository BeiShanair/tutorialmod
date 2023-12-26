package com.besson.tutorialmod.world.biomes;

import com.besson.tutorialmod.TutorialMod;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class ModConfiguredSurfaceBuilder {
    public static ConfiguredSurfaceBuilder<?> DIAMOND_SURFACE = register("diamond_surface",
            SurfaceBuilder.DEFAULT.func_242929_a(new SurfaceBuilderConfig(
                    Blocks.DIAMOND_BLOCK.getDefaultState(),
                    Blocks.IRON_BLOCK.getDefaultState(),
                    Blocks.GOLD_BLOCK.getDefaultState()
            )));
    private static <SC extends ISurfaceBuilderConfig>ConfiguredSurfaceBuilder<SC> register(String name,ConfiguredSurfaceBuilder<SC> csb){
        return WorldGenRegistries.register(WorldGenRegistries.CONFIGURED_SURFACE_BUILDER,new ResourceLocation(TutorialMod.MOD_ID,name),csb);
    }
}
