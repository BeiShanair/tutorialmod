package com.besson.tutorialmod.block;

import com.besson.tutorialmod.TutorialMod;
import com.besson.tutorialmod.block.custom.*;
import com.besson.tutorialmod.block.custom.trees.IceetherTree;
import com.besson.tutorialmod.item.ModItemGroup;
import com.besson.tutorialmod.item.ModItems;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.potion.Effects;
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
    public static final RegistryObject<Block> ICE_ETHER_DOOR = registerBlock("iceether_door",
            () -> new DoorBlock(AbstractBlock.Properties.create(Material.WOOD).harvestLevel(2).harvestTool(ToolType.AXE).setRequiresTool()
                    .hardnessAndResistance(5f).notSolid()));
    public static final RegistryObject<Block> ICE_ETHER_TRAPDOOR = registerBlock("iceether_trapdoor",
            () -> new TrapDoorBlock(AbstractBlock.Properties.create(Material.WOOD).harvestLevel(2).harvestTool(ToolType.AXE).setRequiresTool()
                    .hardnessAndResistance(5f).notSolid()));
    public static final RegistryObject<Block> CORNS = BLOCKS.register("corns",
            () -> new CornsBlock(AbstractBlock.Properties.from(Blocks.WHEAT)));
    public static final RegistryObject<Block> ICE_WOOD= registerBlock("ice_wood",
            () -> new RotatedPillarBlock(AbstractBlock.Properties.from(Blocks.OAK_WOOD).harvestLevel(2).harvestTool(ToolType.PICKAXE).setRequiresTool()
                    .hardnessAndResistance(5f)));
    public static final RegistryObject<Block> ICE_PLANKS = registerBlock("ice_planks",
            () -> new Block(AbstractBlock.Properties.from(Blocks.OAK_PLANKS).harvestLevel(2).harvestTool(ToolType.PICKAXE).setRequiresTool()
                    .hardnessAndResistance(5f)));
    public static final RegistryObject<Block> ICE_LOG = registerBlock("ice_log",
            () -> new RotatedPillarBlock(AbstractBlock.Properties.from(Blocks.OAK_LOG).harvestLevel(2).harvestTool(ToolType.PICKAXE).setRequiresTool()
                    .hardnessAndResistance(5f)));
    public static final RegistryObject<Block> STRIPPED_ICE_WOOD = registerBlock("stripped_ice_wood",
            () -> new RotatedPillarBlock(AbstractBlock.Properties.from(Blocks.STRIPPED_OAK_WOOD).harvestLevel(2).harvestTool(ToolType.PICKAXE).setRequiresTool()
                    .hardnessAndResistance(5f)));
    public static final RegistryObject<Block> STRIPPED_ICE_LOG = registerBlock("stripped_ice_log",
            () -> new RotatedPillarBlock(AbstractBlock.Properties.from(Blocks.STRIPPED_OAK_LOG).harvestLevel(2).harvestTool(ToolType.PICKAXE).setRequiresTool()
                    .hardnessAndResistance(5f)));
    public static final RegistryObject<Block> ICE_ETHER_LEAVES = registerBlock("iceether_leaves",
            () -> new LeavesBlock(AbstractBlock.Properties.create(Material.LEAVES).hardnessAndResistance(0.2f)
                    .tickRandomly().sound(SoundType.PLANT).notSolid()));
    public static final RegistryObject<Block> ICE_ETHER_SAPLING = registerBlock("iceether_sapling",
            () -> new SaplingBlock(new IceetherTree(),AbstractBlock.Properties.from(Blocks.OAK_SAPLING).notSolid()));
    public static final RegistryObject<Block> LEUCANTHEMUM_PALUDOSUM = registerBlock("leucanthemum_paludosum",
            () -> new FlowerBlock(Effects.HASTE,2, AbstractBlock.Properties.from(Blocks.DANDELION).notSolid()));
    public static final RegistryObject<Block> ICE_ETHER_ORE = registerBlock("iceether_ore",
            () -> new Block(AbstractBlock.Properties.create(Material.ROCK).harvestLevel(2).harvestTool(ToolType.PICKAXE).setRequiresTool()
                    .hardnessAndResistance(5f)));
    public static final RegistryObject<Block> BOX = registerBlock("box",
            () -> new BoxBlock(AbstractBlock.Properties.create(Material.ROCK).harvestLevel(2).harvestTool(ToolType.PICKAXE).setRequiresTool()
                    .hardnessAndResistance(5f)));
    public static final RegistryObject<Block> RAINING_CHANGER = registerBlock("raining_changer",
            () -> new RainingChangerBlock(AbstractBlock.Properties.create(Material.ROCK).harvestLevel(2).harvestTool(ToolType.PICKAXE).setRequiresTool()
                    .hardnessAndResistance(5f)));
    public static final RegistryObject<Block> ICE_SIGN = BLOCKS.register("ice_sign",
            () -> new ModStandingSignBlock(AbstractBlock.Properties.create(Material.WOOD),ModWoodTypes.ICE_WOOD));
    public static final RegistryObject<Block> ICE_WALL_SIGN = BLOCKS.register("ice_wall_sign",
            () -> new ModWallSignBlock(AbstractBlock.Properties.create(Material.WOOD),ModWoodTypes.ICE_WOOD));
    public static final RegistryObject<Block> DESK = registerBlock("desk",
            () -> new Desk(AbstractBlock.Properties.create(Material.IRON).notSolid()));

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
