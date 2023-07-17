package com.besson.tutorialmod.containers;

import com.besson.tutorialmod.TutorialMod;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModContainers {
    public static DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, TutorialMod.MOD_ID);
    public static final RegistryObject<ContainerType<RainingChangerContainer>> RAINING_CHANGER_CONTAINER = CONTAINERS.register("raining_changer_container",
            () -> IForgeContainerType.create((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                World world = inv.player.world;
                return new RainingChangerContainer(windowId,world,pos,inv,inv.player);
            }));
    public static void register(IEventBus eventBus){
        CONTAINERS.register(eventBus);
    }
}
