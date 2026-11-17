package com.gizmo.twilitgourmet.datagen.helpers;

import net.minecraft.core.HolderGetter;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;
import net.neoforged.neoforge.common.crafting.CompoundIngredient;
import vectorwing.farmersdelight.common.crafting.ingredient.ItemAbilityIngredient;
import vectorwing.farmersdelight.common.item.KnifeItem;
import vectorwing.farmersdelight.common.tag.CommonTags;

public final class CuttingRecipes {
    public final Ingredient KNIVES;
    public final Ingredient AXES;
    public final Ingredient AXES_STRIP;
    public final Ingredient HOES;

    public CuttingRecipes(HolderGetter<Item> items) {
        KNIVES = CompoundIngredient.of(new ItemAbilityIngredient(KnifeItem.KNIFE_DIG).toVanilla(), Ingredient.of(items.getOrThrow(CommonTags.Items.TOOLS_KNIFE)));
        AXES = CompoundIngredient.of(new ItemAbilityIngredient(ItemAbility.get("axe_dig")).toVanilla(), Ingredient.of(items.getOrThrow(ItemTags.AXES)));
        AXES_STRIP = CompoundIngredient.of(new ItemAbilityIngredient(ItemAbilities.AXE_STRIP).toVanilla(), Ingredient.of(items.getOrThrow(ItemTags.AXES)));
        HOES = CompoundIngredient.of(new ItemAbilityIngredient(ItemAbility.get("hoe_dig")).toVanilla(), Ingredient.of(items.getOrThrow(ItemTags.HOES)));
    }
}
