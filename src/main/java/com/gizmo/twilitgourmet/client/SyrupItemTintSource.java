package com.gizmo.twilitgourmet.client;

import com.gizmo.twilitgourmet.TwilitGourmet;
import com.gizmo.twilitgourmet.init.GourmetDataComponents;
import com.mojang.serialization.MapCodec;
import net.minecraft.client.color.item.ItemTintSource;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public record SyrupItemTintSource() implements ItemTintSource {
    public static final MapCodec<SyrupItemTintSource> MAP_CODEC = MapCodec.unit(SyrupItemTintSource::new);

    @Override
    public int calculate(ItemStack stack, @Nullable ClientLevel level, @Nullable LivingEntity owner) {
        var key = stack.get(GourmetDataComponents.SYRUP);
        if (level != null && key != null) {
            return level.registryAccess().lookupOrThrow(TwilitGourmet.SYRUP_KEY).get(key).map(holder -> holder.value().color()).orElse(-1);
        }
        return -1;
    }

    @Override
    public MapCodec<SyrupItemTintSource> type() {
        return MAP_CODEC;
    }
}
