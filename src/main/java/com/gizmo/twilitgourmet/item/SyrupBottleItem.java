package com.gizmo.twilitgourmet.item;

import com.gizmo.twilitgourmet.Syrup;
import com.gizmo.twilitgourmet.TwilitGourmet;
import com.gizmo.twilitgourmet.init.GourmetDataComponents;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;

import java.util.function.Consumer;

public class SyrupBottleItem extends Item {
    public SyrupBottleItem(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        ResourceKey<Syrup> syrup = stack.get(GourmetDataComponents.SYRUP);
        if (!level.isClientSide() && syrup != null) {
            level.registryAccess().lookupOrThrow(TwilitGourmet.SYRUP_KEY).get(syrup).ifPresent(holder -> holder.value().effect().ifPresent(instance -> entity.addEffect(new MobEffectInstance(instance))));
        }
        return super.finishUsingItem(stack, level, entity);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display, Consumer<Component> tooltip, TooltipFlag flag) {
        ResourceKey<Syrup> syrup = stack.get(GourmetDataComponents.SYRUP);
        if (syrup != null) {
            Identifier key = syrup.identifier();
            tooltip.accept(Component.translatable("item.twilitgourmet.syrup_bottle.type", Component.translatable("syrup." + key.getNamespace() + "." + key.getPath())).withStyle(ChatFormatting.GRAY));
        }
    }
}
