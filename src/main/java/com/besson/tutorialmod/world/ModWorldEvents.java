package com.besson.tutorialmod.world;

import com.besson.tutorialmod.TutorialMod;
import com.besson.tutorialmod.world.gen.ModFlowerGeneration;
import com.besson.tutorialmod.world.gen.ModOreGeneration;
import com.besson.tutorialmod.world.gen.ModTreeGeneration;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TutorialMod.MOD_ID)
public class ModWorldEvents {
    @SubscribeEvent
    public static void biomeLoadingEvent(final BiomeLoadingEvent event){
        ModTreeGeneration.generateTrees(event);
        ModFlowerGeneration.generateFlowers(event);
        ModOreGeneration.generateOre(event);
    }
}
