package com.besson.tutorialmod.item;

import com.besson.tutorialmod.TutorialMod;
import com.besson.tutorialmod.block.ModBlocks;
import com.besson.tutorialmod.fluid.ModFluid;
import com.besson.tutorialmod.item.custom.FireEther;
import com.besson.tutorialmod.item.custom.IceEther;
import net.minecraft.inventory.EquipmentSlotType;
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
    public static final RegistryObject<Item> ICE_ETHER_HELMET = ITEMS.register("iceether_helmet",
            () -> new ArmorItem(ModArmorMaterial.ICEETHER, EquipmentSlotType.HEAD,new Item.Properties().group(ModItemGroup.TUTORIAL_TAB)));
    public static final RegistryObject<Item> ICE_ETHER_CHESTPLATE = ITEMS.register("iceether_chestplate",
            () -> new ArmorItem(ModArmorMaterial.ICEETHER, EquipmentSlotType.CHEST,new Item.Properties().group(ModItemGroup.TUTORIAL_TAB)));
    public static final RegistryObject<Item> ICE_ETHER_LEGGINGS = ITEMS.register("iceether_leggings",
            () -> new ArmorItem(ModArmorMaterial.ICEETHER, EquipmentSlotType.LEGS,new Item.Properties().group(ModItemGroup.TUTORIAL_TAB)));
    public static final RegistryObject<Item> ICE_ETHER_BOOTS = ITEMS.register("iceether_boots",
            () -> new ArmorItem(ModArmorMaterial.ICEETHER, EquipmentSlotType.FEET,new Item.Properties().group(ModItemGroup.TUTORIAL_TAB)));
    public static final RegistryObject<Item> ICE_ETHER_HORSE_ARMOR = ITEMS.register("iceether_horse_armor",
            () -> new HorseArmorItem(9,"iceether",new Item.Properties().group(ModItemGroup.TUTORIAL_TAB)));
    public static final RegistryObject<Item> CORNS = ITEMS.register("corns",
            () -> new BlockItem(ModBlocks.CORNS.get(),new Item.Properties().food(new Food.Builder().hunger(3)
                    .saturation(0.1f).fastToEat().build()).group(ModItemGroup.TUTORIAL_TAB)));
    public static final RegistryObject<Item> ICE_SIGN = ITEMS.register("ice_sign",
            () -> new SignItem(new Item.Properties().maxStackSize(16).group(ModItemGroup.TUTORIAL_TAB),
                    ModBlocks.ICE_SIGN.get(),ModBlocks.ICE_WALL_SIGN.get()));
    public static final RegistryObject<Item> OIL_BUCKET = ITEMS.register("oil_bucket",
            () -> new BucketItem(() -> ModFluid.OIL.get(),new Item.Properties().maxStackSize(1).group(ModItemGroup.TUTORIAL_TAB)));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
