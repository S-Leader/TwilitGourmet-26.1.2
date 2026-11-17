package com.gizmo.twilitgourmet.mixin;

import com.gizmo.twilitgourmet.TwilitGourmet;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import vectorwing.farmersdelight.common.block.entity.CuttingBoardBlockEntity;

@Mixin(CuttingBoardBlockEntity.class)
public abstract class CuttingBoardBlockEntityMixin extends BlockEntity {
    public CuttingBoardBlockEntityMixin(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @WrapOperation(method = "lambda$processStoredItemUsingTool$0", remap = false,
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;copy()Lnet/minecraft/world/item/ItemStack;"))
    private ItemStack smeltCuttingBoardItemsWithFieryTools(ItemStack result, Operation<ItemStack> original,
                                                           @Local(argsOnly = true) ItemStack toolStack) {
        if (this.level instanceof ServerLevel level && toolStack.is(TwilitGourmet.COOKS_CUTTING_BOARD_ITEMS)) {
            SingleRecipeInput input = new SingleRecipeInput(result);
            ItemStack cooked = level.recipeAccess().getRecipeFor(RecipeType.SMELTING, input, level)
                    .map(holder -> holder.value().assemble(input))
                    .filter(stack -> !stack.isEmpty())
                    .map(stack -> stack.copyWithCount(stack.getCount() * result.getCount()))
                    .orElse(result);
            return original.call(cooked);
        }
        return original.call(result);
    }
}
