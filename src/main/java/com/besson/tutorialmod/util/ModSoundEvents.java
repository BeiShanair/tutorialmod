package com.besson.tutorialmod.util;

import com.besson.tutorialmod.TutorialMod;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModSoundEvents {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, TutorialMod.MOD_ID);
    public static final RegistryObject<SoundEvent> SMALL_EXPLOSION = registerSoundEvent("small_explosion");
    public static final RegistryObject<SoundEvent> TEST = registerSoundEvent("test");
    public static RegistryObject<SoundEvent> registerSoundEvent(String name){
        return SOUNDS.register(name,() -> new SoundEvent(new ResourceLocation(TutorialMod.MOD_ID,name)));
    }
    public static void register(IEventBus eventBus){
        SOUNDS.register(eventBus);
    }
}
