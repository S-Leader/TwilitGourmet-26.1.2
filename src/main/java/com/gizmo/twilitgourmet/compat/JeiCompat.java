package com.gizmo.twilitgourmet.compat;

import com.gizmo.twilitgourmet.TwilitGourmet;
import com.gizmo.twilitgourmet.init.GourmetItems;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.recipe.IRecipeManager;
import mezz.jei.api.recipe.types.IRecipeHolderType;
import mezz.jei.api.runtime.IJeiRuntime;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import twilightforest.compat.jei.categories.JEIUncraftingCategory;

import java.util.List;

@JeiPlugin
public class JeiCompat implements IModPlugin {
    @Override
    public Identifier getPluginUid() {
        return TwilitGourmet.prefix("jei_plugin");
    }

    @Override
    public void onRuntimeAvailable(IJeiRuntime runtime) {
        hideHelmetRecipe(runtime.getRecipeManager(), RecipeTypes.CRAFTING);
        hideHelmetRecipe(runtime.getRecipeManager(), JEIUncraftingCategory.UNCRAFTING);
        runtime.getIngredientManager().removeIngredientsAtRuntime(VanillaTypes.ITEM_STACK,
                List.of(GourmetItems.SHELL_HELMET.toStack()));
    }

    private static void hideHelmetRecipe(IRecipeManager manager, IRecipeHolderType<CraftingRecipe> type) {
        List<RecipeHolder<CraftingRecipe>> recipes = manager.createRecipeLookup(type).includeHidden().get()
                .filter(holder -> holder.id().identifier().equals(TwilitGourmet.prefix("shell_helmet"))).toList();
        manager.hideRecipes(type, recipes);
    }
}
