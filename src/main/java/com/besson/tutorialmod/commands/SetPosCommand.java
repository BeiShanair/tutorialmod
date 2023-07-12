package com.besson.tutorialmod.commands;

import com.besson.tutorialmod.TutorialMod;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;

public class SetPosCommand {
    public SetPosCommand(CommandDispatcher<CommandSource> dispatcher){
        // /pos set
        dispatcher.register(Commands.literal("pos").then(Commands.literal("set").executes((command) ->{
            return setPos(command.getSource());
        })));
    }
    private int setPos(CommandSource source)throws CommandSyntaxException{
        ServerPlayerEntity player = source.asPlayer();
        BlockPos blockPos = player.getPosition();
        String pos = "("+blockPos.getX() +","+blockPos.getY()+","+blockPos.getZ()+")";
        source.sendFeedback(new StringTextComponent("Set Pos at:"+pos),true);
        player.getPersistentData().putIntArray(TutorialMod.MOD_ID + "pos",new int[]{blockPos.getX(), blockPos.getY(), blockPos.getZ()});
        return 1;
    }
}
