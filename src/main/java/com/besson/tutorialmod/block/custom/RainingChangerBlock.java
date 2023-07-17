package com.besson.tutorialmod.block.custom;

import com.besson.tutorialmod.containers.RainingChangerContainer;
import com.besson.tutorialmod.tileentity.ModTileEntities;
import com.besson.tutorialmod.tileentity.RainingChangerTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

public class RainingChangerBlock extends Block {
    public RainingChangerBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return ModTileEntities.RAINING_CHANGER_TILE_ENTITY.get().create();
    }

    @Override
    public ActionResultType onBlockActivated(BlockState blockState, World world, BlockPos blockPos, PlayerEntity player, Hand hand, BlockRayTraceResult blockRayTraceResult) {
        if (!world.isRemote()){
            TileEntity tileEntity = world.getTileEntity(blockPos);
            if (!player.isCrouching()){
                if (tileEntity instanceof RainingChangerTileEntity){
                    INamedContainerProvider containerProvider = createContainerProvider(world,blockPos);
                    NetworkHooks.openGui(((ServerPlayerEntity) player), containerProvider, tileEntity.getPos());
                }else {
                    throw new IllegalStateException("our container provider is missing");
                }
            }else {
                if (tileEntity instanceof RainingChangerTileEntity){
                    if (world.isRaining()){
                        EntityType.LIGHTNING_BOLT.spawn((ServerWorld) world, null, player, blockPos,
                                SpawnReason.TRIGGERED, true, true);
                        ((RainingChangerTileEntity)tileEntity).RainingHasStruck();
                    }
                }
            }
        }
        return ActionResultType.SUCCESS;
    }

    private INamedContainerProvider createContainerProvider(World world, BlockPos blockPos) {
        return new INamedContainerProvider() {
            @Override
            public ITextComponent getDisplayName() {
                return new TranslationTextComponent("screen.tutorialmod.raining_changer");
            }

            @Nullable
            @Override
            public Container createMenu(int i, PlayerInventory playerInventory, PlayerEntity player) {
                return new RainingChangerContainer(i,world,blockPos,playerInventory,player);
            }
        };
    }

}
