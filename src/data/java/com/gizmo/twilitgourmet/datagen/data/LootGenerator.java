package com.gizmo.twilitgourmet.datagen.data;

import com.gizmo.twilitgourmet.datagen.data.loot.BlockLootTables;
import com.gizmo.twilitgourmet.datagen.data.loot.EntityLootTables;
import com.gizmo.twilitgourmet.datagen.data.loot.MiscLootTables;
import com.gizmo.twilitgourmet.init.GourmetLootTables;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class LootGenerator extends LootTableProvider {

    public LootGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, GourmetLootTables.allBuiltin(), List.of(
                new LootTableProvider.SubProviderEntry(BlockLootTables::new, LootContextParamSets.BLOCK),
                new LootTableProvider.SubProviderEntry(MiscLootTables::new, LootContextParamSets.CHEST),
                new LootTableProvider.SubProviderEntry(lookup -> tables -> tables.accept(GourmetLootTables.GIANT_APPLE, MiscLootTables.giantApple(lookup)), LootContextParamSets.BLOCK),
                new LootTableProvider.SubProviderEntry(EntityLootTables::new, LootContextParamSets.ENTITY)
        ), registries);
    }

}
