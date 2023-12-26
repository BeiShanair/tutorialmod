package com.besson.tutorialmod.entity.custom;

import com.besson.tutorialmod.entity.ModEntityTypes;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.UUID;

public class TigerEntity extends WolfEntity {
    public TigerEntity(EntityType<? extends WolfEntity> type, World worldIn) {
        super(type, worldIn);
    }
    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MobEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 200.0D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.33D)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 15.0D);
    }

    @Override
    public ActionResultType getEntityInteractionResult(PlayerEntity playerIn, Hand hand) {
        ItemStack itemstack = playerIn.getHeldItem(hand);
        Item item = itemstack.getItem();
        if (this.world.isRemote) {
            boolean flag = this.isOwner(playerIn) || this.isTamed() || item == Items.COD && !this.isTamed() && !this.isAngry();
            return flag ? ActionResultType.CONSUME : ActionResultType.PASS;
        } else {
            if (this.isTamed()) {
                if (this.isBreedingItem(itemstack) && this.getHealth() < this.getMaxHealth()) {
                    if (!playerIn.abilities.isCreativeMode) {
                        itemstack.shrink(1);
                    }

                    this.heal((float)item.getFood().getHealing());
                    return ActionResultType.SUCCESS;
                }

                if (!(item instanceof DyeItem)) {
                    ActionResultType actionresulttype = super.getEntityInteractionResult(playerIn, hand);
                    if ((!actionresulttype.isSuccessOrConsume() || this.isChild()) && this.isOwner(playerIn)) {
                        this.setSitting(!this.isQueuedToSit());
                        this.isJumping = false;
                        this.navigator.clearPath();
                        this.setAttackTarget((LivingEntity)null);
                        return ActionResultType.SUCCESS;
                    }

                    return actionresulttype;
                }

                DyeColor dyecolor = ((DyeItem)item).getDyeColor();
                if (dyecolor != this.getCollarColor()) {
                    this.setCollarColor(dyecolor);
                    if (!playerIn.abilities.isCreativeMode) {
                        itemstack.shrink(1);
                    }

                    return ActionResultType.SUCCESS;
                }
            } else if (item == Items.COD && !this.isAngry()) {
                if (!playerIn.abilities.isCreativeMode) {
                    itemstack.shrink(1);
                }

                if (this.rand.nextInt(3) == 0 && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, playerIn)) {
                    this.setTamedBy(playerIn);
                    this.navigator.clearPath();
                    this.setAttackTarget((LivingEntity)null);
                    this.setSitting(true);
                    this.world.setEntityState(this, (byte)7);
                } else {
                    this.world.setEntityState(this, (byte)6);
                }

                return ActionResultType.SUCCESS;
            }

            return super.getEntityInteractionResult(playerIn, hand);
        }
    }
    public TigerEntity createChild(ServerWorld world, AgeableEntity mate) {
        TigerEntity tigerEntity = ModEntityTypes.TIGER.get().create(world);
        UUID uuid = this.getOwnerId();
        if (uuid != null) {
            tigerEntity.setOwnerId(uuid);
            tigerEntity.setTamed(true);
        }

        return tigerEntity;
    }
}
