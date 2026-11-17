package com.gizmo.twilitgourmet.block.entity;

import com.gizmo.twilitgourmet.Syrup;
import com.gizmo.twilitgourmet.TwilitGourmet;
import com.gizmo.twilitgourmet.init.GourmetBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jetbrains.annotations.Nullable;

public class SyrupCauldronBlockEntity extends BlockEntity implements SyrupHolder {
    @Nullable
    private ResourceKey<Syrup> syrup;

    public SyrupCauldronBlockEntity(BlockPos pos, BlockState state) {
        super(GourmetBlocks.SYRUP_CAULDRON_BE.get(), pos, state);
    }

    @Override
    @Nullable
    public ResourceKey<Syrup> getSyrupKey() {
        return this.syrup;
    }

    @Override
    public void setSyrup(@Nullable ResourceKey<Syrup> syrup) {
        this.syrup = syrup;
        this.setChanged();
        if (this.level != null && !this.level.isClientSide()) {
            this.level.sendBlockUpdated(this.worldPosition, this.getBlockState(), this.getBlockState(), Block.UPDATE_CLIENTS);
        }
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        output.storeNullable("syrup", ResourceKey.codec(TwilitGourmet.SYRUP_KEY), this.syrup);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        this.syrup = input.read("syrup", ResourceKey.codec(TwilitGourmet.SYRUP_KEY)).orElse(null);
        if (this.level != null && this.level.isClientSide()) {
            this.level.sendBlockUpdated(this.worldPosition, this.getBlockState(), this.getBlockState(), Block.UPDATE_CLIENTS);
        }
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return this.saveCustomOnly(registries);
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }
}
