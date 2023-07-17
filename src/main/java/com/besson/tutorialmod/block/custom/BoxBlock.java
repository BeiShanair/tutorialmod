package com.besson.tutorialmod.block.custom;

import com.besson.tutorialmod.tileentity.BoxTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.Random;

public class BoxBlock extends Block {
    public static final BooleanProperty OPEN = BooleanProperty.create("open");
    public BoxBlock(Properties properties) {
        super(properties);
        this.setDefaultState(this.getStateContainer().getBaseState().with(OPEN, false));
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new BoxTileEntity();
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote){
            TileEntity tileEntity = worldIn.getTileEntity(pos);
            if (tileEntity instanceof BoxTileEntity){
                player.openContainer((INamedContainerProvider) tileEntity);
            }
        }
        return ActionResultType.SUCCESS;
    }

    @Override
    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
            TileEntity tileEntity = worldIn.getTileEntity(pos);
            if (tileEntity instanceof BoxTileEntity){
                ((BoxTileEntity) tileEntity).onScheduleTick();
            }
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
    {
        super.fillStateContainer(builder);
        builder.add(OPEN);
    }

    public ISidedInventory createInventory(BlockState state, IWorld world, BlockPos pos)
    {
        TileEntity tileEntity = world.getTileEntity(pos);
        if(tileEntity instanceof ISidedInventory)
        {
            return (ISidedInventory) tileEntity;
        }
        return null;
    }
}
