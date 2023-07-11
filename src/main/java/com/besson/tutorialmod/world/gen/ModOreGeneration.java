package com.besson.tutorialmod.world.gen;

import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.ConfiguredPlacement;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class ModOreGeneration {
    public static void generateOre(final BiomeLoadingEvent event){
        for (OreType ore : OreType.values()){
            OreFeatureConfig oreFeatureConfig = new OreFeatureConfig(
                    OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD,
                    ore.getBlock().get().getDefaultState(), ore.getMaxVeinSize());
            ConfiguredPlacement<TopSolidRangeConfig> configConfiguredPlacement = Placement.RANGE.configure(
                    new TopSolidRangeConfig(ore.getMinHeight(),ore.getMinHeight(), ore.getMaxHeight()));
            ConfiguredFeature<?,?> oreFeature = registerOreFeature(ore,oreFeatureConfig, configConfiguredPlacement);

            event.getGeneration().withFeature(GenerationStage.Decoration.UNDERGROUND_ORES,oreFeature);
        }
    }
    private static ConfiguredFeature<?,?> registerOreFeature(OreType oreType,OreFeatureConfig oreFeatureConfig,ConfiguredPlacement configuredPlacement){
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE,oreType.getBlock().get().getRegistryName(),
                Feature.ORE.withConfiguration(oreFeatureConfig).withPlacement(configuredPlacement).square().count(oreType.getMaxVeinSize()));
    }
}
