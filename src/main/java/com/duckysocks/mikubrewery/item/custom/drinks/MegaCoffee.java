package com.duckysocks.mikubrewery.item.custom.drinks;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.ExplosionBehavior;

public class MegaCoffee extends Item {
    public MegaCoffee(Settings settings) {
        super(settings);
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {

        return UseAction.DRINK;
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity player, int remainingUseTicks) {
        if (!world.isClient()) {
            world.createExplosion(null, player.getX(), player.getY(), player.getZ(), 100, World.ExplosionSourceType.MOB);
        }
        super.onStoppedUsing(stack, world, player, remainingUseTicks);
    }
}
