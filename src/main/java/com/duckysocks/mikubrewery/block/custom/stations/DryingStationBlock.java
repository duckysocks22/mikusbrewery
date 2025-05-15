package com.duckysocks.mikubrewery.block.custom.stations;

import com.duckysocks.mikubrewery.MikusBrewery;
import com.duckysocks.mikubrewery.block.entity.ModBlockEntities;
import com.duckysocks.mikubrewery.block.entity.custom.stations.DryingStationBlockEntity;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
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

public class DryingStationBlock extends BlockWithEntity implements BlockEntityProvider {
    public static final MapCodec<DryingStationBlock> CODEC = DryingStationBlock.createCodec(DryingStationBlock::new);

    public DryingStationBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        MikusBrewery.LOGGER.info("Creating Block Entity for Drying Station");
        return new DryingStationBlockEntity(pos, state);
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    protected void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof DryingStationBlockEntity) {
                ItemScatterer.spawn(world, pos, ((DryingStationBlockEntity) blockEntity));
                world.updateComparators(pos, this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos,
                                             PlayerEntity player, Hand hand, BlockHitResult hit) {

        if(world.getBlockEntity(pos) instanceof DryingStationBlockEntity dryingStationBlockEntity) {
            if(dryingStationBlockEntity.isEmpty() && !stack.isEmpty()) {
                dryingStationBlockEntity.setStack(0, stack.copyWithCount(1));
                world.playSound(player, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 1f, 2f);
                stack.decrement(1);

                dryingStationBlockEntity.markDirty();
                world.updateListeners(pos, state, state, 0);
            } else if(stack.isEmpty() && !player.isSneaking()) {
                ItemStack stackOnDrying = dryingStationBlockEntity.getStack(0);
                player.setStackInHand(Hand.MAIN_HAND, stackOnDrying);
                world.playSound(player, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 1f, 1f);
                dryingStationBlockEntity.clear();

                dryingStationBlockEntity.markDirty();
                world.updateListeners(pos, state, state, 0);
            }
        }

        return ItemActionResult.SUCCESS;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        if (world.isClient()) {
            return null;
        }

        return validateTicker(type, ModBlockEntities.DRYING_STATION_BE,
                (world1, pos, state1, blockEntity) -> blockEntity.tick(world1, pos, state1));
    }
}
