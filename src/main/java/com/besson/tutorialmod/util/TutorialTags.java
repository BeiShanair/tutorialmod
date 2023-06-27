package com.besson.tutorialmod.util;

import com.besson.tutorialmod.TutorialMod;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;

public class TutorialTags {
    public static class Blocks{

        public static final Tags.IOptionalNamedTag<Block> RIGHT_BLOCK =createTag("rightblock");
        private static Tags.IOptionalNamedTag<Block> createTag(String name){
            return BlockTags.createOptional(new ResourceLocation(TutorialMod.MOD_ID,name));
        }
        private static Tags.IOptionalNamedTag<Block> createForgeTag(String name){
            return BlockTags.createOptional(new ResourceLocation("forge",name));
        }
    }

    public static class Items{
        public static final Tags.IOptionalNamedTag<Item> ICE_ETHER =createForgeTag("gems/iceether");
        private static Tags.IOptionalNamedTag<Item> createTag(String name){
            return ItemTags.createOptional(new ResourceLocation(TutorialMod.MOD_ID,name));
        }
        private static Tags.IOptionalNamedTag<Item> createForgeTag(String name){
            return ItemTags.createOptional(new ResourceLocation("forge",name));
        }
    }
}
