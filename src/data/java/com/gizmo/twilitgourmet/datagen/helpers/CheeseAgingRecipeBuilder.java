package com.gizmo.twilitgourmet.datagen.helpers;

import com.gizmo.twilitgourmet.item.recipe.CheeseAgingRecipe;
import net.minecraft.core.HolderSet;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

import java.util.Optional;

public class CheeseAgingRecipeBuilder extends CustomRecipeBuilder<CheeseAgingRecipeBuilder> {
    private final Ingredient input;
    private final int time;
    private Optional<CheeseAgingRecipe.RotInfo> rot = Optional.empty();

    private CheeseAgingRecipeBuilder(ItemLike input, ItemLike result, float minutes) {
        super(new ItemStackTemplate(result.asItem()), "aging");
        this.input = Ingredient.of(input);
        time = (int) (minutes * 1200);
    }

    public static CheeseAgingRecipeBuilder aging(ItemLike input, ItemLike result, float minutes) {
        return new CheeseAgingRecipeBuilder(input, result, minutes);
    }

    public CheeseAgingRecipeBuilder withRottingCriteria(ItemLike result, int radius, boolean skylight, HolderSet<Block> causes) {
        rot = Optional.of(new CheeseAgingRecipe.RotInfo(new ItemStackTemplate(result.asItem()), radius, skylight, causes));
        return this;
    }

    @Override
    public void save(RecipeOutput output, ResourceKey<Recipe<?>> id) {
        save(output, id, new CheeseAgingRecipe(new Recipe.CommonInfo(true), input, result, time, rot));
    }
}
