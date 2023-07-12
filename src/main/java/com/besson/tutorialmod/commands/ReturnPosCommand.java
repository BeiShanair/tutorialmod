package com.besson.tutorialmod.commands;

import com.besson.tutorialmod.TutorialMod;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;

public class ReturnPosCommand {
    public ReturnPosCommand(CommandDispatcher<CommandSource> dispatcher){
        // /return pos
        dispatcher.register(Commands.literal("return").then(Commands.literal("pos").executes((command) ->{
            return returnPos(command.getSource());
        })));
    }
    private int returnPos(CommandSource source)throws CommandSyntaxException{
        ServerPlayerEntity player = source.asPlayer();
        boolean hasPos =player.getPersistentData().getIntArray(TutorialMod.MOD_ID+"pos").length != 0;
        if (hasPos){
            int[] playerPos = player.getPersistentData().getIntArray(TutorialMod.MOD_ID+"pos");
            player.setPositionAndUpdate(playerPos[0],playerPos[1],playerPos[2]);
            return 1;
        }else {
            source.sendFeedback(new StringTextComponent("No Pos has been saved"),true);
            return -1;
        }
    }
}
