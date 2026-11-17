package com.gizmo.twilitgourmet.init;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import vectorwing.farmersdelight.common.FoodValues;

public class GourmetFoods {

    public static final FoodProperties APPLE_SLICE = new FoodProperties.Builder().nutrition(2).saturationModifier(0.1F).build();
    public static final FoodProperties PANCAKE = new FoodProperties.Builder().nutrition(3).build();
    public static final FoodProperties FIDDLEHEAD = new FoodProperties.Builder().nutrition(1).build();
    public static final FoodProperties SAUTEED_FIDDLEHEAD = new FoodProperties.Builder().nutrition(4).saturationModifier(0.1F).build();
    public static final FoodProperties IRONWOOD_FIDDLEHEAD = new FoodProperties.Builder().nutrition(6).saturationModifier(1.2F).build();
    public static final FoodProperties SYRUP_BOTTLE = new FoodProperties.Builder().saturationModifier(0.3F).alwaysEdible().build();
    public static final FoodProperties RAW_BOARKCHOP = new FoodProperties.Builder().nutrition(4).saturationModifier(0.3F).build();
    public static final FoodProperties COOKED_BOARKCHOP = new FoodProperties.Builder().nutrition(9).saturationModifier(0.85F).build();
    public static final FoodProperties GROUND_VENISON = new FoodProperties.Builder().nutrition(2).saturationModifier(0.3F).build();
    public static final FoodProperties VENISON_PATTY = new FoodProperties.Builder().nutrition(4).saturationModifier(0.8F).build();
    public static final FoodProperties MINCED_MEEF = new FoodProperties.Builder().nutrition(2).saturationModifier(0.3F).build();
    public static final FoodProperties MEEF_PATTY = new FoodProperties.Builder().nutrition(4).saturationModifier(0.8F).build();
    public static final FoodProperties CRAB_MEAT = new FoodProperties.Builder().nutrition(2).saturationModifier(0.2F).build();
    public static final FoodProperties COOKED_CRAB_MEAT = new FoodProperties.Builder().nutrition(4).saturationModifier(0.5F).build();
    public static final FoodProperties CRAB_JERKY = new FoodProperties.Builder().nutrition(4).saturationModifier(1.075F).build();
    public static final FoodProperties VENISON_KEBAB = new FoodProperties.Builder().nutrition(8).saturationModifier(0.6F).build();
    public static final FoodProperties VENISON_SLIDER = new FoodProperties.Builder().nutrition(11).saturationModifier(0.6F).build();
    public static final FoodProperties CRAB_CAKE = new FoodProperties.Builder().nutrition(6).saturationModifier(0.6F).build();
    public static final FoodProperties VENISON_AND_VEGGIES = new FoodProperties.Builder().nutrition(12).saturationModifier(0.8F).build();
    public static final FoodProperties GOULASH = new FoodProperties.Builder().nutrition(14).saturationModifier(1.1F).build();
    public static final FoodProperties MUSHGLOOM_STEW = new FoodProperties.Builder().nutrition(8).saturationModifier(0.6F).build();
    public static final FoodProperties FIDDLEHEAD_RISOTTO = new FoodProperties.Builder().nutrition(8).saturationModifier(0.5F).build();
    public static final FoodProperties BERRY_SMOOTHIE = new FoodProperties.Builder().nutrition(6).saturationModifier(0.5F).build();

    public static Consumable consumableFor(FoodProperties food) {
        if (food == FoodValues.PIE_CRUST || food == FoodValues.PIE_SLICE) {
            return FoodValues.consumableFor(food);
        }
        Consumable.Builder builder = Consumable.builder();
        if (food == APPLE_SLICE || food == GROUND_VENISON || food == VENISON_PATTY
                || food == MINCED_MEEF || food == MEEF_PATTY || food == CRAB_MEAT || food == COOKED_CRAB_MEAT) {
            builder.consumeSeconds(0.8F);
        }
        if (food == FIDDLEHEAD) {
            builder.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.POISON, 100, 1)));
            builder.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.NAUSEA, 150)));
        } else if (food == IRONWOOD_FIDDLEHEAD) {
            builder.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.REGENERATION, 300, 1)));
            builder.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.HASTE, 1200, 1)));
            builder.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.SPEED, 1200)));
        } else if (food == CRAB_CAKE || food == VENISON_AND_VEGGIES || food == GOULASH || food == MUSHGLOOM_STEW) {
            builder.onConsume(new ApplyStatusEffectsConsumeEffect(FoodValues.nourishment(3600)));
        } else if (food == FIDDLEHEAD_RISOTTO) {
            builder.onConsume(new ApplyStatusEffectsConsumeEffect(FoodValues.nourishment(1200)));
        } else if (food == BERRY_SMOOTHIE) {
            builder.animation(ItemUseAnimation.DRINK).sound(SoundEvents.GENERIC_DRINK).hasConsumeParticles(false);
            builder.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.GLOWING, 1200)));
        } else if (food == SYRUP_BOTTLE) {
            builder.consumeSeconds(2.0F).animation(ItemUseAnimation.DRINK).sound(SoundEvents.HONEY_DRINK).hasConsumeParticles(false);
        }
        return builder.build();
    }
}
