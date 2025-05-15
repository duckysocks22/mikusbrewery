package com.duckysocks.mikubrewery.block.entity.custom.stations;

import com.duckysocks.mikubrewery.MikusBrewery;
import com.duckysocks.mikubrewery.block.custom.stations.DryingStationBlock;
import com.duckysocks.mikubrewery.block.entity.ModBlockEntities;
import com.duckysocks.mikubrewery.block.entity.custom.ImplementedInventory;
import com.duckysocks.mikubrewery.recipe.ModRecipes;
import com.duckysocks.mikubrewery.recipe.stations.drying_station.DryingStationRecipe;
import com.duckysocks.mikubrewery.recipe.stations.drying_station.DryingStationRecipeInput;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class DryingStationBlockEntity extends BlockEntity implements ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(1, ItemStack.EMPTY);

    private int progress = 0;
    private int maxProgress = 20;

    private float rotation = 0;
    private ItemStack currentItem;

    private String DEBUG;

    public DryingStationBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.DRYING_STATION_BE, pos, state);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    public float getRenderingRotation() {
        rotation = 0f;
        return rotation;
    }

    public ItemStack getRenderingStack() {
        currentItem =  getStack(0);
        return currentItem;
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        if(hasRecipe()) {
            MikusBrewery.LOGGER.info("Drying Has Recipe");
            increaseCraftingProgress();
            markDirty(world, pos, state);

            if(hasCraftingFinished()) {
                craftItem();
                resetProgress();
                world.updateListeners(pos, state, state, DryingStationBlock.NOTIFY_ALL);
            }
        } else {
            resetProgress();
        }
    }

    private boolean hasRecipe() {
        Optional<RecipeEntry<DryingStationRecipe>> recipe = getCurrentRecipe();
        if(recipe.isEmpty()) {
            return false;
        }

        ItemStack output = recipe.get().value().output();
        return true;
    }

    private Optional<RecipeEntry<DryingStationRecipe>> getCurrentRecipe() {
        return this.getWorld().getRecipeManager()
                .getFirstMatch(ModRecipes.DRYING_STATION_TYPE, new DryingStationRecipeInput(getStack(0)), this.getWorld());
    }

    private void increaseCraftingProgress() {
        this.progress++;
    }

    private boolean hasCraftingFinished() {
        return this.progress >= this.maxProgress;
    }

    private void craftItem() {
        Optional<RecipeEntry<DryingStationRecipe>> recipe = getCurrentRecipe();

        ItemStack output = recipe.get().value().output();
        this.setStack(0, new ItemStack(output.getItem()));
        MikusBrewery.LOGGER.info("CRAFTED ITEM");
    }

    private void resetProgress() {
        this.progress = 0;
        this.maxProgress = 20;
    }


    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        Inventories.writeNbt(nbt, inventory, registryLookup);
        nbt.putInt("drying_station.progress", progress);
        nbt.putInt("drying_station.max_progress", maxProgress);
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        Inventories.readNbt(nbt, inventory, registryLookup);
        progress = nbt.getInt("drying_station.progress");
        maxProgress = nbt.getInt("drying_station.max_progress");
        super.readNbt(nbt, registryLookup);
    }


    @Override
    public @Nullable Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registryLookup) {
        return createNbt(registryLookup);
    }
}
