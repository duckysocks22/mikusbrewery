package com.duckysocks.mikubrewery.item;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class ModFoodComponents {

    public static final FoodComponent WINE = new FoodComponent.Builder()
            .statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 200), 0.30f)
            .statusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 300), 0.90f)
            .statusEffect(new StatusEffectInstance(StatusEffects.POISON, 500), 0.05f)
            .alwaysEdible()
            .build();

    public static final FoodComponent COFFEE = new FoodComponent.Builder()
            .statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 3000), 1.0f)
            .statusEffect(new StatusEffectInstance(StatusEffects.HASTE, 3000), 1.0f)
            .statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 200), 0.5f)
            .alwaysEdible()
            .build();

    public static final FoodComponent TEA = new FoodComponent.Builder()
            .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 600), 1.0f)
            .statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 1200), 1.0f)
            .alwaysEdible()
            .build();
}
