package com.besson.tutorialmod.world.gen;

import com.besson.tutorialmod.world.biomes.ModBiomes;
import net.minecraft.util.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

public class ModBiomesGeneration {
    public static void generateBiomes(){
        addBiomes(ModBiomes.DIAMOND_BIOMES.get(),BiomeManager.BiomeType.WARM,20, BiomeDictionary.Type.HOT, BiomeDictionary.Type.DEAD, BiomeDictionary.Type.DRY);
    }
    private static void addBiomes(Biome biome, BiomeManager.BiomeType biomeType, int weight, BiomeDictionary.Type... types){
        RegistryKey<Biome> key =RegistryKey.getOrCreateKey(ForgeRegistries.Keys.BIOMES,
                Objects.requireNonNull(ForgeRegistries.BIOMES.getKey(biome)));
        BiomeDictionary.addTypes(key,types);
        BiomeManager.addBiome(biomeType,new BiomeManager.BiomeEntry(key,weight));
    }
}
