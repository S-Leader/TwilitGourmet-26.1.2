package com.gizmo.twilitgourmet.datagen.helpers;

import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.Criterion;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeUnlockAdvancementBuilder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Recipe;
import org.jspecify.annotations.Nullable;

public abstract class CustomRecipeBuilder<T extends CustomRecipeBuilder<T>> implements RecipeBuilder {
    protected final ItemStackTemplate result;
    private final RecipeUnlockAdvancementBuilder advancement = new RecipeUnlockAdvancementBuilder();
    private final String advancementDirectory;
    protected String group = "";

    protected CustomRecipeBuilder(ItemStackTemplate result, String advancementDirectory) {
        this.result = result;
        this.advancementDirectory = advancementDirectory;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T unlockedBy(String name, Criterion<?> criterion) {
        advancement.unlockedBy(name, criterion);
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T group(@Nullable String group) {
        this.group = group == null ? "" : group;
        return (T) this;
    }

    @Override
    public ResourceKey<Recipe<?>> defaultId() {
        return RecipeBuilder.getDefaultRecipeId(result);
    }

    protected void save(RecipeOutput output, ResourceKey<Recipe<?>> id, Recipe<?> recipe) {
        var holder = advancement.build(output, id, RecipeCategory.FOOD);
        String name = id.identifier().getPath();
        if (name.startsWith(advancementDirectory + "/")) name = name.substring(advancementDirectory.length() + 1);
        output.accept(id, recipe, new AdvancementHolder(id.identifier().withPath("recipes/" + advancementDirectory + "/" + name), holder.value()));
    }
}
