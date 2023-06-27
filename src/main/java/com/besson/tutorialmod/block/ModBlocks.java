package com.besson.tutorialmod.block;

import com.besson.tutorialmod.TutorialMod;
import com.besson.tutorialmod.block.custom.FireEtherBlock;
import com.besson.tutorialmod.item.ModItemGroup;
import com.besson.tutorialmod.item.ModItems;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =DeferredRegister.create(ForgeRegistries.BLOCKS, TutorialMod.MOD_ID);

    public static final RegistryObject<Block> ICE_ETHER_BLOCK = registerBlock("iceetherblock",
            () -> new Block(AbstractBlock.Properties.create(Material.ROCK).harvestLevel(2).harvestTool(ToolType.PICKAXE).setRequiresTool()
                    .hardnessAndResistance(5f)));
    public static final RegistryObject<Block> FIRE_ETHER_BLOCK = registerBlock("fireetherblock",
            () -> new FireEtherBlock(AbstractBlock.Properties.create(Material.ROCK).harvestLevel(2).harvestTool(ToolType.PICKAXE).setRequiresTool()
                    .hardnessAndResistance(5f)));

    public static final RegistryObject<Block> ICE_ETHER_STAIRS = registerBlock("iceether_stairs",
            () -> new StairsBlock(() -> ICE_ETHER_BLOCK.get().getDefaultState(),AbstractBlock.Properties.create(Material.ROCK)
                    .harvestLevel(2).harvestTool(ToolType.PICKAXE).setRequiresTool().hardnessAndResistance(5f)));

    public static final RegistryObject<Block> ICE_ETHER_FENCE = registerBlock("iceether_fence",
            () -> new FenceBlock(AbstractBlock.Properties.create(Material.ROCK).harvestLevel(2).harvestTool(ToolType.PICKAXE).setRequiresTool()
                    .hardnessAndResistance(5f)));
    public static final RegistryObject<Block> ICE_ETHER_FENCE_GATE = registerBlock("iceether_fence_gate",
            () -> new FenceGateBlock(AbstractBlock.Properties.create(Material.ROCK).harvestLevel(2).harvestTool(ToolType.PICKAXE).setRequiresTool()
                    .hardnessAndResistance(5f)));

    public static final RegistryObject<Block> ICE_ETHER_SLAB = registerBlock("iceether_slab",
            () -> new SlabBlock(AbstractBlock.Properties.create(Material.ROCK).harvestLevel(2).harvestTool(ToolType.PICKAXE).setRequiresTool()
                    .hardnessAndResistance(5f)));
    public static final RegistryObject<Block> ICE_ETHER_BUTTON = registerBlock("iceether_button",
            () -> new StoneButtonBlock(AbstractBlock.Properties.create(Material.ROCK).harvestLevel(2).harvestTool(ToolType.PICKAXE).setRequiresTool()
                    .hardnessAndResistance(5f).doesNotBlockMovement()));
    public static final RegistryObject<Block> ICE_ETHER_PRESSURE_PLATE = registerBlock("iceether_pressure_plate",
            () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,AbstractBlock.Properties.create(Material.ROCK).harvestLevel(2).harvestTool(ToolType.PICKAXE).setRequiresTool()
                    .hardnessAndResistance(5f)));

    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block){
        RegistryObject<T> tRegistryObject = BLOCKS.register(name,block);
        registerBlockItem(name,tRegistryObject);
        return tRegistryObject;
    }
    private static <T extends Block> void registerBlockItem(String name,Supplier<T> block){
        ModItems.ITEMS.register(name,() -> new BlockItem(block.get(),new Item.Properties().group(ModItemGroup.TUTORIAL_TAB)));
    }

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
