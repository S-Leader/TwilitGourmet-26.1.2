package com.gizmo.twilitgourmet.datagen.helpers;

import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import vectorwing.farmersdelight.common.crafting.CuttingBoardRecipe;
import vectorwing.farmersdelight.common.crafting.ingredient.ChanceResult;

import java.util.Optional;

public class CuttingBoardRecipeBuilder {
    private final Ingredient input;
    private final Ingredient tool;
    private final NonNullList<ChanceResult> results = NonNullList.create();
    private Optional<SoundEvent> sound = Optional.empty();
    private String directory = "cutting/";

    private CuttingBoardRecipeBuilder(Ingredient input, Ingredient tool, ItemLike result, int count, float chance) {
        this.input = input;
        this.tool = tool;
        addResultWithChance(result, chance, count);
    }

    public static CuttingBoardRecipeBuilder cuttingRecipe(Ingredient input, Ingredient tool, ItemLike result) {
        return cuttingRecipe(input, tool, result, 1);
    }

    public static CuttingBoardRecipeBuilder cuttingRecipe(Ingredient input, Ingredient tool, ItemLike result, int count) {
        return cuttingRecipe(input, tool, result, count, 1.0F);
    }

    public static CuttingBoardRecipeBuilder cuttingRecipe(Ingredient input, Ingredient tool, ItemLike result, int count, float chance) {
        return new CuttingBoardRecipeBuilder(input, tool, result, count, chance);
    }

    public CuttingBoardRecipeBuilder addResult(ItemLike result) {
        return addResultWithChance(result, 1.0F, 1);
    }

    public CuttingBoardRecipeBuilder addResultWithChance(ItemLike result, float chance, int count) {
        results.add(new ChanceResult(new ItemStackTemplate(result.asItem(), count), chance));
        return this;
    }

    public CuttingBoardRecipeBuilder addSound(SoundEvent sound) {
        this.sound = Optional.of(sound);
        return this;
    }

    public CuttingBoardRecipeBuilder salvaging() {
        directory = "salvaging/";
        return this;
    }

    public void save(RecipeOutput output) {
        Identifier source = input.items().findFirst().orElseThrow().unwrapKey().orElseThrow().identifier();
        save(output, source.withPrefix(directory));
    }

    public void saveToFD(RecipeOutput output) {
        String name = input.items().findFirst().orElseThrow().unwrapKey().orElseThrow().identifier().getPath();
        save(output, Identifier.fromNamespaceAndPath("farmersdelight", directory + name));
    }

    public void save(RecipeOutput output, String id) {
        save(output, Identifier.parse(id));
    }

    public void save(RecipeOutput output, Identifier id) {
        output.accept(ResourceKey.create(Registries.RECIPE, id), new CuttingBoardRecipe("", input, tool, results, sound), null);
    }
}
