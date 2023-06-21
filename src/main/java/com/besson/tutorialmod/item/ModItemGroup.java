package com.besson.tutorialmod.item;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModItemGroup {
    public static final ItemGroup TUTORIAL_TAB = new ItemGroup("tutorialtab") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModItems.ICE_ETHER.get());
        }
    };
}
