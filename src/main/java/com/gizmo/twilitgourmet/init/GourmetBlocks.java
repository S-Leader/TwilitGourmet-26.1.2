package com.gizmo.twilitgourmet.init;

import com.gizmo.twilitgourmet.TwilitGourmet;
import com.gizmo.twilitgourmet.block.*;
import com.gizmo.twilitgourmet.block.entity.PancakeStackBlockEntity;
import com.gizmo.twilitgourmet.block.entity.SyrupCauldronBlockEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import twilightforest.init.TFBlocks;
import vectorwing.farmersdelight.common.block.CabinetBlock;
import vectorwing.farmersdelight.common.block.FeastBlock;
import vectorwing.farmersdelight.common.block.PieBlock;

import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;

public class GourmetBlocks {

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(TwilitGourmet.MODID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, TwilitGourmet.MODID);

    //expected compat things
    public static final DeferredBlock<Block> TWILIGHT_OAK_CABINET = register("twilight_oak_cabinet", CabinetBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.BARREL));
    public static final DeferredBlock<Block> CANOPY_CABINET = register("canopy_cabinet", CabinetBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.BARREL).mapColor(MapColor.PODZOL));
    public static final DeferredBlock<Block> MANGROVE_CABINET = register("mangrove_cabinet", CabinetBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.BARREL).mapColor(MapColor.DIRT));
    public static final DeferredBlock<Block> DARK_CABINET = register("dark_cabinet", CabinetBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.BARREL).mapColor(MapColor.COLOR_BROWN));
    public static final DeferredBlock<Block> TIME_CABINET = register("time_cabinet", CabinetBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.BARREL).mapColor(MapColor.DIRT));
    public static final DeferredBlock<Block> TRANSFORMATION_CABINET = register("transformation_cabinet", CabinetBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.BARREL));
    public static final DeferredBlock<Block> MINING_CABINET = register("mining_cabinet", CabinetBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.BARREL).mapColor(MapColor.SAND));
    public static final DeferredBlock<Block> SORTING_CABINET = register("sorting_cabinet", CabinetBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.BARREL).mapColor(MapColor.PODZOL));
    public static final DeferredBlock<Block> MUSHGLOOM_COLONY = BLOCKS.registerBlock("mushgloom_colony", MushgloomColonyBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(TFBlocks.MUSHGLOOM.get()).randomTicks());

    //new stuff
    public static final DeferredBlock<Block> FIDDLEHEAD = BLOCKS.registerBlock("fiddlehead", FiddleheadCropBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT).mapColor(MapColor.PLANT));
    public static final DeferredBlock<Block> BREADCRUMBS = register("breadcrumbs", BreadcrumbBlock::new, () -> BlockBehaviour.Properties.of().replaceable().noCollision().noOcclusion().mapColor(MapColor.TERRACOTTA_BROWN).pushReaction(PushReaction.DESTROY).sound(SoundType.ROOTS));
    public static final DeferredBlock<Block> GIANT_APPLE = register("giant_apple", GiantAppleBlock::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).strength(2.0F, 10.0F).pushReaction(PushReaction.DESTROY).sound(SoundType.WOOL));
    public static final DeferredBlock<Block> TREE_TAPPER = register("tree_tapper", TreeTapperBlock::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.METAL).strength(2.0F, 10.0F).randomTicks().sound(SoundType.METAL));
    public static final DeferredBlock<Block> SYRUP_CAULDRON = BLOCKS.registerBlock("syrup_cauldron", SyrupCauldronBlock::new, () -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.CAULDRON));
    public static final DeferredBlock<Block> PANCAKE_STACK = BLOCKS.registerBlock("pancake_stack", PancakeStackBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
    public static final DeferredBlock<Block> COOKED_CRAB = BLOCKS.registerBlock("cooked_crab", CookedCrabBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE).sound(SoundType.ROOTS));
    public static final DeferredBlock<Block> MAZE_CHEESECAKE = BLOCKS.registerBlock("maze_cheesecake", properties -> new PieBlock(properties, GourmetItems.MAZE_CHEESECAKE_SLICE), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
    public static final DeferredBlock<Block> CREMESCHNITTE = BLOCKS.registerBlock("cremeschnitte", properties -> new PieBlock(properties, GourmetItems.CREMESCHNITTE_SLICE), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
    public static final DeferredBlock<Block> CRAB_QUICHE = BLOCKS.registerBlock("crab_quiche", properties -> new PieBlock(properties, GourmetItems.CRAB_QUICHE_SLICE), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
    public static final DeferredBlock<Block> CRAB_CAKES = BLOCKS.registerBlock("crab_cakes", properties -> new FeastBlock(properties, GourmetItems.CRAB_CAKE, true), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<SyrupCauldronBlockEntity>> SYRUP_CAULDRON_BE = BLOCK_ENTITIES.register("syrup_cauldron", () -> new BlockEntityType<>(SyrupCauldronBlockEntity::new, Set.of(SYRUP_CAULDRON.get())));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<PancakeStackBlockEntity>> PANCAKE_STACK_BE = BLOCK_ENTITIES.register("pancake_stack", () -> new BlockEntityType<>(PancakeStackBlockEntity::new, Set.of(PANCAKE_STACK.get())));

    public static <T extends Block> DeferredBlock<T> register(String name, Function<BlockBehaviour.Properties, T> factory, Supplier<BlockBehaviour.Properties> properties) {
        DeferredBlock<T> block = BLOCKS.registerBlock(name, factory, properties);
        GourmetItems.ITEMS.registerSimpleBlockItem(name, block);
        return block;
    }
}
