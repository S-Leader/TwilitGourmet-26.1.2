package com.gizmo.twilitgourmet.datagen.helpers;

import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.ItemLike;
import twilightforest.item.recipe.DryingRecipe;

public class DryingRecipeBuilder extends CustomRecipeBuilder<DryingRecipeBuilder> {
    private final Ingredient input;

    private DryingRecipeBuilder(ItemLike input, ItemLike result) {
        super(new ItemStackTemplate(result.asItem()), "drying");
        this.input = Ingredient.of(input);
    }

    public static DryingRecipeBuilder drying(ItemLike input, ItemLike result) {
        return new DryingRecipeBuilder(input, result);
    }

    @Override
    public void save(RecipeOutput output, ResourceKey<Recipe<?>> id) {
        save(output, id, new DryingRecipe(new Recipe.CommonInfo(true), input, result, 6000));
    }
}
