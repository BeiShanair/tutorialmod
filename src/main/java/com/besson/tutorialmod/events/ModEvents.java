package com.besson.tutorialmod.events;

import com.besson.tutorialmod.TutorialMod;
import com.besson.tutorialmod.commands.ReturnPosCommand;
import com.besson.tutorialmod.commands.SetPosCommand;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;

@Mod.EventBusSubscriber(modid = TutorialMod.MOD_ID)
public class ModEvents {
    @SubscribeEvent
    public static void onCommandRegister(RegisterCommandsEvent event){
        new SetPosCommand(event.getDispatcher());
        new ReturnPosCommand(event.getDispatcher());
        ConfigCommand.register(event.getDispatcher());
    }

    @SubscribeEvent
    public static void onPlayerCloneEvent(PlayerEvent.Clone event){
        if (!event.getOriginal().getEntityWorld().isRemote()){
            event.getPlayer().getPersistentData().putIntArray(TutorialMod.MOD_ID+"pos",
                    event.getOriginal().getPersistentData().getIntArray(TutorialMod.MOD_ID+"pos"));
        }
    }
}
