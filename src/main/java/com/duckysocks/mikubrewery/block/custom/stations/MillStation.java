package com.duckysocks.mikubrewery.block.custom.stations;

import com.duckysocks.mikubrewery.block.entity.custom.stations.MillStationEntity;
import com.duckysocks.mikubrewery.util.ModTags;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class MillStation extends BlockWithEntity implements BlockEntityProvider {

    public static final MapCodec<MillStation> CODEC = MillStation.createCodec(MillStation::new);

    public MillStation(Settings settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new MillStationEntity(pos, state);
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    protected void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof MillStationEntity) {
                ItemScatterer.spawn(world, pos, ((MillStationEntity) blockEntity));
                world.updateComparators(pos, this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos,
                                             PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.getBlockEntity(pos) instanceof MillStationEntity millStationEntity) {
            if (millStationEntity.isEmpty() && !stack.isEmpty()) {
                if (stack.isIn(ModTags.Items.MILLABLE_ITEMS)) {
                    millStationEntity.setStack(0, stack.copyWithCount(1));
                    world.playSound(player, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 1f, 2f);
                    stack.decrement(1);

                    millStationEntity.markDirty();
                    world.updateListeners(pos, state, state, 0);
                }
            } else if(stack.isEmpty() && !player.isSneaking()) {
                ItemStack stackInMill = millStationEntity.getStack(0);
                player.setStackInHand(Hand.MAIN_HAND, stackInMill);
                world.playSound(player, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 1f, 1f);
                millStationEntity.clear();

                millStationEntity.markDirty();
                world.updateListeners(pos, state, state, 0);
            } else if(player.isSneaking() && !world.isClient()) {
                player.openHandledScreen(millStationEntity);
            }
        }

        return ItemActionResult.SUCCESS;

    }
}