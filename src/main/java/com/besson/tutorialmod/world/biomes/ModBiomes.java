package com.besson.tutorialmod.world.biomes;

import com.besson.tutorialmod.TutorialMod;
import net.minecraft.client.audio.BackgroundMusicTracks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.structure.StructureFeatures;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ModBiomes {
    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, TutorialMod.MOD_ID);
    public static final RegistryObject<Biome> DIAMOND_BIOMES = BIOMES.register("diamond_biomes",
            () -> makeDiamondBiome(() -> ModConfiguredSurfaceBuilder.DIAMOND_SURFACE,0.01f,0.05f));
    public static Biome makeDiamondBiome(final Supplier<ConfiguredSurfaceBuilder<?>> surfaceBuilder,float depth,float scale){
        MobSpawnInfo.Builder mobspawninfobuilder = new MobSpawnInfo.Builder();
        DefaultBiomeFeatures.withPassiveMobs(mobspawninfobuilder);
        DefaultBiomeFeatures.withBatsAndHostiles(mobspawninfobuilder);
        mobspawninfobuilder.withSpawner(EntityClassification.MONSTER,
                new MobSpawnInfo.Spawners(EntityType.BLAZE, 100, 7, 10));
        mobspawninfobuilder.withSpawner(EntityClassification.MONSTER,
                new MobSpawnInfo.Spawners(EntityType.ZOMBIFIED_PIGLIN, 50, 4, 4));
        BiomeGenerationSettings.Builder biomegenerationsettingsbuilder =
                (new BiomeGenerationSettings.Builder()).withSurfaceBuilder(surfaceBuilder);

        biomegenerationsettingsbuilder.withStructure(StructureFeatures.MINESHAFT);
        biomegenerationsettingsbuilder.withStructure(StructureFeatures.RUINED_PORTAL_SWAMP);
        biomegenerationsettingsbuilder.withStructure(StructureFeatures.BURIED_TREASURE);

        DefaultBiomeFeatures.withCavesAndCanyons(biomegenerationsettingsbuilder);

        DefaultBiomeFeatures.withLavaAndWaterLakes(biomegenerationsettingsbuilder);
        DefaultBiomeFeatures.withMonsterRoom(biomegenerationsettingsbuilder);
        DefaultBiomeFeatures.withCommonOverworldBlocks(biomegenerationsettingsbuilder);
        DefaultBiomeFeatures.withOverworldOres(biomegenerationsettingsbuilder);
        DefaultBiomeFeatures.withClayDisks(biomegenerationsettingsbuilder);
        DefaultBiomeFeatures.withNormalMushroomGeneration(biomegenerationsettingsbuilder);
        DefaultBiomeFeatures.withDesertVegetation(biomegenerationsettingsbuilder);
        DefaultBiomeFeatures.withLavaAndWaterSprings(biomegenerationsettingsbuilder);

        biomegenerationsettingsbuilder.withFeature(GenerationStage.Decoration.LAKES, Features.LAKE_LAVA);
        DefaultBiomeFeatures.withFrozenTopLayer(biomegenerationsettingsbuilder);

        return (new Biome.Builder()).precipitation(Biome.RainType.NONE).category(Biome.Category.PLAINS).depth(depth).scale(scale)
                .temperature(1.5F).downfall(0.5F).setEffects((new BiomeAmbience.Builder()).setWaterColor(4159204).setWaterFogColor(329011)
                        .setFogColor(12638463).withSkyColor(getSkyColorWithTemperatureModifier(2F)).withFoliageColor(4159204).withGrassColor(4159204)
                        .setParticle(new ParticleEffectAmbience(ParticleTypes.WHITE_ASH, 0.3f)).withSkyColor(8103167)
                        .setAmbientSound(SoundEvents.AMBIENT_CRIMSON_FOREST_LOOP)
                        .setMoodSound(new MoodSoundAmbience(SoundEvents.AMBIENT_WARPED_FOREST_MOOD, 6000, 8, 2.0D))
                        .setAdditionsSound(new SoundAdditionsAmbience(SoundEvents.AMBIENT_NETHER_WASTES_ADDITIONS, 0.0111D))
                        .setMusic(BackgroundMusicTracks.getDefaultBackgroundMusicSelector(SoundEvents.MUSIC_NETHER_CRIMSON_FOREST))
                        .build())
                .withMobSpawnSettings(mobspawninfobuilder.build()).withGenerationSettings(biomegenerationsettingsbuilder.build()).build();
    }

    private static int getSkyColorWithTemperatureModifier(float temperature) {
        float lvt_1_1_ = temperature / 3.0F;
        lvt_1_1_ = MathHelper.clamp(lvt_1_1_, -1.0F, 1.0F);
        return MathHelper.hsvToRGB(0.2460909F - lvt_1_1_ * 0.05F, 0.5F + lvt_1_1_ * 0.1F, 1.0F);
    }
    public static void register(IEventBus eventBus){
        BIOMES.register(eventBus);
    }
}
