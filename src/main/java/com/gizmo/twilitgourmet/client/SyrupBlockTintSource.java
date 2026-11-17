package com.gizmo.twilitgourmet.client;

import com.gizmo.twilitgourmet.Syrup;
import com.gizmo.twilitgourmet.TwilitGourmet;
import com.gizmo.twilitgourmet.block.TreeTapperBlock;
import com.gizmo.twilitgourmet.block.entity.SyrupHolder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockTintSource;
import net.minecraft.client.renderer.block.BlockAndTintGetter;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;

import java.util.Set;

public record SyrupBlockTintSource(boolean tapper) implements BlockTintSource {
    @Override
    public int color(BlockState state) {
        return -1;
    }

    @Override
    public int colorInWorld(BlockState state, BlockAndTintGetter level, BlockPos pos) {
        var clientLevel = Minecraft.getInstance().level;
        if (clientLevel == null) return -1;
        if (this.tapper) {
            var key = TreeTapperBlock.getSyrup(level, pos, state.getValue(TreeTapperBlock.FACING));
            if (key != null) {
                return clientLevel.registryAccess().lookupOrThrow(TwilitGourmet.SYRUP_KEY).get(key)
                        .map(holder -> holder.value().color()).orElse(-1);
            }
        } else if (level.getBlockEntity(pos) instanceof SyrupHolder holder) {
            Syrup syrup = holder.getSyrup(clientLevel.registryAccess());
            if (syrup != null) return syrup.color();
        }
        return -1;
    }

    @Override
    public Set<Property<?>> relevantProperties() {
        return this.tapper ? Set.of(TreeTapperBlock.FACING) : Set.of();
    }
}
