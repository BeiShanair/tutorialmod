package com.besson.tutorialmod.item;

import com.besson.tutorialmod.TutorialMod;
import com.besson.tutorialmod.item.custom.FireEther;
import com.besson.tutorialmod.item.custom.IceEther;
import net.minecraft.item.*;
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
    public static final RegistryObject<Item> FIRE_ETHER_AXE = ITEMS.register("fireether_axe",
            () -> new AxeItem(ModItemTier.FIRE_ETHER,5f,-3f,new Item.Properties().group(ModItemGroup.TUTORIAL_TAB)));
    public static final RegistryObject<Item> FIRE_ETHER_SWORD = ITEMS.register("fireether_sword",
            () -> new SwordItem(ModItemTier.FIRE_ETHER,3,-2.4f,new Item.Properties().group(ModItemGroup.TUTORIAL_TAB)));
    public static final RegistryObject<Item> FIRE_ETHER_PICKAXE = ITEMS.register("fireether_pickaxe",
            () -> new PickaxeItem(ModItemTier.FIRE_ETHER,1,-2.8f,new Item.Properties().group(ModItemGroup.TUTORIAL_TAB)));
    public static final RegistryObject<Item> FIRE_ETHER_HOE = ITEMS.register("fireether_hoe",
            () -> new HoeItem(ModItemTier.FIRE_ETHER,-2,0f,new Item.Properties().group(ModItemGroup.TUTORIAL_TAB)));
    public static final RegistryObject<Item> FIRE_ETHER_SHOVEL = ITEMS.register("fireether_shovel",
            () -> new ShovelItem(ModItemTier.FIRE_ETHER,1.5f,-3f,new Item.Properties().group(ModItemGroup.TUTORIAL_TAB)));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
