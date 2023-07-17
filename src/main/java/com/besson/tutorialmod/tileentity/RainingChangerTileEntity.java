package com.besson.tutorialmod.tileentity;

import com.besson.tutorialmod.item.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class RainingChangerTileEntity extends TileEntity {
    private final ItemStackHandler itemStackHandler = createHandler();
    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemStackHandler);
    public RainingChangerTileEntity(TileEntityType<?> tileEntityType) {
        super(tileEntityType);
    }
    public RainingChangerTileEntity(){
        this(ModTileEntities.RAINING_CHANGER_TILE_ENTITY.get());
    }
    public ItemStackHandler createHandler(){
        return new ItemStackHandler(2){
            @Override
            protected void onContentsChanged(int slot) {
                markDirty();
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                switch (slot){
                    case 0:
                        return stack.getItem() == Items.WATER_BUCKET;
                    case 1:
                        return stack.getItem() == ModItems.ICE_ETHER.get() || stack.getItem() == Items.ICE;
                    default:
                        return false;
                }
            }

            @Override
            protected int getStackLimit(int slot, @Nonnull ItemStack stack) {
                return 1;
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                if (!isItemValid(slot,stack)){
                    return stack;
                }
                return super.insertItem(slot, stack, simulate);
            }
        };
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY){
            return handler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public CompoundNBT write(CompoundNBT compoundNBT) {
        compoundNBT.put("inv",itemStackHandler.serializeNBT());
        return super.write(compoundNBT);
    }

    @Override
    public void read(BlockState blockState, CompoundNBT compoundNBT) {
        itemStackHandler.deserializeNBT(compoundNBT.getCompound("inv"));
        super.read(blockState, compoundNBT);
    }
    public void RainingHasStruck(){
        boolean hasFocusInFirstSlot = this.itemStackHandler.getStackInSlot(0).getCount() > 0 &&
                this.itemStackHandler.getStackInSlot(0).getItem() == Items.WATER_BUCKET;
        boolean hasFocusInSecondSlot = this.itemStackHandler.getStackInSlot(1).getCount() > 0 &&
                this.itemStackHandler.getStackInSlot(1).getItem() == ModItems.ICE_ETHER.get();
        if (hasFocusInFirstSlot && hasFocusInSecondSlot){
            this.itemStackHandler.getStackInSlot(0).shrink(1);
            this.itemStackHandler.getStackInSlot(1).shrink(1);
            this.itemStackHandler.insertItem(1,new ItemStack(Items.ICE), false);
        }
    }
}
