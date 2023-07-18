package com.besson.tutorialmod;

import com.besson.tutorialmod.block.ModBlocks;
import com.besson.tutorialmod.block.ModWoodTypes;
import com.besson.tutorialmod.containers.ModContainers;
import com.besson.tutorialmod.fluid.ModFluid;
import com.besson.tutorialmod.item.ModItems;
import com.besson.tutorialmod.screens.RainingChangerScreen;
import com.besson.tutorialmod.tileentity.ModTileEntities;
import com.besson.tutorialmod.util.ModSoundEvents;
import com.besson.tutorialmod.world.structure.ModStructures;
import com.google.common.collect.ImmutableMap;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.Atlases;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.tileentity.SignTileEntityRenderer;
import net.minecraft.item.AxeItem;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(TutorialMod.MOD_ID)
public class TutorialMod {
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "tutorialmod";
    public TutorialMod() {
        // Register the setup method for modloading
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(eventBus);
        ModBlocks.register(eventBus);
        ModTileEntities.register(eventBus);
        ModContainers.register(eventBus);
        ModStructures.register(eventBus);
        ModFluid.register(eventBus);
        ModSoundEvents.register(eventBus);

        eventBus.addListener(this::setup);
        // Register the enqueueIMC method for modloading
        eventBus.addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        eventBus.addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        eventBus.addListener(this::doClientStuff);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
        event.enqueueWork(()->{
            AxeItem.BLOCK_STRIPPING_MAP = new ImmutableMap.Builder<Block,Block>().putAll(AxeItem.BLOCK_STRIPPING_MAP)
                    .put(ModBlocks.ICE_LOG.get(),ModBlocks.STRIPPED_ICE_LOG.get())
                    .put(ModBlocks.ICE_WOOD.get(),ModBlocks.STRIPPED_ICE_WOOD.get()).build();
            ModStructures.setupStructures();
            Atlases.addWoodType(ModWoodTypes.ICE_WOOD);
        });
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        event.enqueueWork(()->{
            RenderTypeLookup.setRenderLayer(ModBlocks.ICE_ETHER_DOOR.get(),RenderType.getCutout());
            RenderTypeLookup.setRenderLayer(ModBlocks.ICE_ETHER_TRAPDOOR.get(),RenderType.getCutout());
            RenderTypeLookup.setRenderLayer(ModBlocks.CORNS.get(),RenderType.getCutout());
            RenderTypeLookup.setRenderLayer(ModBlocks.ICE_ETHER_LEAVES.get(),RenderType.getCutout());
            RenderTypeLookup.setRenderLayer(ModBlocks.ICE_ETHER_SAPLING.get(),RenderType.getCutout());
            RenderTypeLookup.setRenderLayer(ModBlocks.LEUCANTHEMUM_PALUDOSUM.get(),RenderType.getCutout());
            RenderTypeLookup.setRenderLayer(ModFluid.OIL.get(),RenderType.getTranslucent());
            RenderTypeLookup.setRenderLayer(ModFluid.OIL_FLOWING.get(),RenderType.getTranslucent());
            RenderTypeLookup.setRenderLayer(ModFluid.OIL_BLOCK.get(),RenderType.getTranslucent());

            ScreenManager.registerFactory(ModContainers.RAINING_CHANGER_CONTAINER.get(), RainingChangerScreen::new);
            ClientRegistry.bindTileEntityRenderer(ModTileEntities.SIGN_TILE_ENTITY.get(), SignTileEntityRenderer::new);
        });
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo("tutorialmod", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    private void processIMC(final InterModProcessEvent event)
    {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.getMessageSupplier().get()).
                collect(Collectors.toList()));
    }
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            // register a new block here
            LOGGER.info("HELLO from Register Block");
        }
    }
}
