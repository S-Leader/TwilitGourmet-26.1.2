package com.gizmo.twilitgourmet.datagen.helpers;

import com.google.gson.JsonObject;
import com.mojang.serialization.JsonOps;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.core.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.ItemLike;
import vectorwing.farmersdelight.client.recipebook.CookingPotRecipeBookTab;
import vectorwing.farmersdelight.common.crafting.CookingPotRecipe;

import java.util.Optional;

public class CookingPotRecipeBuilder extends CustomRecipeBuilder<CookingPotRecipeBuilder> {
    private final HolderGetter<Item> items;
    private final HolderLookup.Provider registries;
    private final NonNullList<Ingredient> ingredients = NonNullList.create();
    private final int time;
    private final float experience;
    private final Optional<ItemStackTemplate> container;
    private CookingPotRecipeBookTab tab = CookingPotRecipeBookTab.MISC;

    private CookingPotRecipeBuilder(HolderLookup.Provider registries, ItemLike result, int count, int time, float experience, Optional<ItemStackTemplate> container) {
        super(new ItemStackTemplate(result.asItem(), count), "cooking");
        this.items = registries.lookupOrThrow(Registries.ITEM);
        this.registries = registries;
        this.time = time;
        this.experience = experience;
        this.container = container;
    }

    public static CookingPotRecipeBuilder cookingPotRecipe(HolderLookup.Provider registries, ItemLike result, int count, int time, float experience) {
        return new CookingPotRecipeBuilder(registries, result, count, time, experience, Optional.empty());
    }

    public static CookingPotRecipeBuilder cookingPotRecipe(HolderLookup.Provider registries, ItemLike result, int count, int time, float experience, ItemLike container) {
        return new CookingPotRecipeBuilder(registries, result, count, time, experience, Optional.of(new ItemStackTemplate(container.asItem())));
    }

    public CookingPotRecipeBuilder addIngredient(ItemLike ingredient) {
        return addIngredient(Ingredient.of(ingredient));
    }

    public CookingPotRecipeBuilder addIngredient(TagKey<Item> ingredient) {
        return addIngredient(Ingredient.of(items.getOrThrow(ingredient)));
    }

    public CookingPotRecipeBuilder addIngredient(ItemLike ingredient, int count) {
        for (int i = 0; i < count; i++) addIngredient(ingredient);
        return this;
    }

    public CookingPotRecipeBuilder addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
        return this;
    }

    public CookingPotRecipeBuilder setRecipeBookTab(CookingPotRecipeBookTab tab) {
        this.tab = tab;
        return this;
    }

    public CookingPotRecipeBuilder unlockedByAnyIngredient(ItemLike... ingredients) {
        for (int i = 0; i < ingredients.length; i++) unlockedByItems("has_ingredient_" + i, ingredients[i]);
        return this;
    }

    public CookingPotRecipeBuilder unlockedByItems(String name, ItemLike... ingredients) {
        return unlockedBy(name, InventoryChangeTrigger.TriggerInstance.hasItems(ingredients));
    }

    @Override
    public ResourceKey<Recipe<?>> defaultId() {
        return ResourceKey.create(Registries.RECIPE, super.defaultId().identifier().withPrefix("cooking/"));
    }

    @Override
    public void save(RecipeOutput output, ResourceKey<Recipe<?>> id) {
        var ops = registries.createSerializationContext(JsonOps.INSTANCE);
        JsonObject json = new JsonObject();
        json.addProperty("group", group);
        json.add("ingredients", Ingredient.CODEC.listOf().encodeStart(ops, ingredients).getOrThrow());
        json.add("result", ItemStackTemplate.CODEC.encodeStart(ops, result).getOrThrow());
        json.add("recipe_book_tab", CookingPotRecipeBookTab.CODEC.encodeStart(ops, tab).getOrThrow());
        json.addProperty("experience", experience);
        json.addProperty("cookingtime", time);
        container.ifPresent(value -> json.add("container", ItemStackTemplate.CODEC.encodeStart(ops, value).getOrThrow()));
        var decodingOps = RegistryOps.create(JsonOps.INSTANCE, new RegistryOps.RegistryInfoLookup() {
            @Override
            public <T> Optional<RegistryOps.RegistryInfo<T>> lookup(ResourceKey<? extends Registry<? extends T>> key) {
                return registries.lookup(key).map(registry -> new RegistryOps.RegistryInfo<>(ops.owner(key).orElseThrow(), new HolderGetter<T>() {
                    @Override
                    public Optional<Holder.Reference<T>> get(ResourceKey<T> id) {
                        return ops.getter(key).orElseThrow().get(id);
                    }

                    @Override
                    public Optional<HolderSet.Named<T>> get(TagKey<T> tag) {
                        return Optional.of(HolderSet.emptyNamed(ops.owner(key).orElseThrow(), tag));
                    }
                }, registry.registryLifecycle()));
            }
        });
        save(output, id, CookingPotRecipe.Serializer.CODEC.codec().parse(decodingOps, json).getOrThrow());
    }
}
