package com.besson.tutorialmod.fluid;

import com.besson.tutorialmod.TutorialMod;
import com.besson.tutorialmod.block.ModBlocks;
import com.besson.tutorialmod.item.ModItems;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModFluid {
    public static final ResourceLocation WATER_STILL = new ResourceLocation("block/water_still");
    public static final ResourceLocation WATER_FLOWING = new ResourceLocation("block/water_flow");
    public static final ResourceLocation WATER_OVERLAY = new ResourceLocation("block/water_overlay");
    public static final DeferredRegister<Fluid> FLUID = DeferredRegister.create(ForgeRegistries.FLUIDS, TutorialMod.MOD_ID);
    public static final RegistryObject<FlowingFluid> OIL = FLUID.register("oil",() -> new ForgeFlowingFluid.Source(ModFluid.OIL_PROPERTY));
    public static final RegistryObject<FlowingFluid> OIL_FLOWING = FLUID.register("oil_flowing",() -> new ForgeFlowingFluid.Flowing(ModFluid.OIL_PROPERTY));

    public static final ForgeFlowingFluid.Properties OIL_PROPERTY = new ForgeFlowingFluid.Properties(() -> OIL.get(),() -> OIL_FLOWING.get(),
            FluidAttributes.builder(WATER_STILL,WATER_FLOWING).density(5).luminosity(2).viscosity(5).sound(SoundEvents.ITEM_HONEY_BOTTLE_DRINK).overlay(WATER_OVERLAY)
                    .color(0xbf5d5d5d)).slopeFindDistance(2).levelDecreasePerBlock(2).block(() -> ModFluid.OIL_BLOCK.get()).bucket(() -> ModItems.OIL_BUCKET.get());

    public static final RegistryObject<FlowingFluidBlock> OIL_BLOCK = ModBlocks.BLOCKS.register("oil",
            () -> new FlowingFluidBlock(() -> ModFluid.OIL.get(), AbstractBlock.Properties.create(Material.WATER)
                    .doesNotBlockMovement().hardnessAndResistance(100f).noDrops()));
    public static void register(IEventBus eventBus){
        FLUID.register(eventBus);
    }
}
