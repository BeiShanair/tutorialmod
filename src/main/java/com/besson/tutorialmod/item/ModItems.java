package com.besson.tutorialmod.item;

import com.besson.tutorialmod.TutorialMod;
import com.besson.tutorialmod.item.custom.FireEther;
import com.besson.tutorialmod.item.custom.IceEther;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TutorialMod.MOD_ID);

    public static final RegistryObject<Item> ICE_ETHER = ITEMS.register("iceether",
            () -> new IceEther(new Item.Properties().group(ModItemGroup.TUTORIAL_TAB).maxDamage(8)));
    public static final RegistryObject<Item> FIRE_ETHER = ITEMS.register("fireether",
            () -> new FireEther(new Item.Properties().group(ModItemGroup.TUTORIAL_TAB).maxDamage(8)));
    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
