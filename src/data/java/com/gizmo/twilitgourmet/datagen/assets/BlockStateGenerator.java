package com.gizmo.twilitgourmet.datagen.assets;

import com.gizmo.twilitgourmet.TwilitGourmet;
import com.gizmo.twilitgourmet.block.*;
import com.gizmo.twilitgourmet.init.GourmetBlocks;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.blockstates.MultiPartGenerator;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.*;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.core.Holder;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.neoforged.neoforge.registries.DeferredBlock;
import twilightforest.TwilightForestMod;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.block.CabinetBlock;
import vectorwing.farmersdelight.common.block.FeastBlock;
import vectorwing.farmersdelight.common.block.MushroomColonyBlock;
import vectorwing.farmersdelight.common.block.PieBlock;

import java.util.Optional;
import java.util.stream.Stream;

import static com.gizmo.twilitgourmet.TwilitGourmet.prefix;
import static net.minecraft.client.data.models.BlockModelGenerators.*;

public class BlockStateGenerator extends ModelProvider {
    private static final TextureSlot PANCAKE = TextureSlot.create("pancake");
    private static final TextureSlot CROSS2 = TextureSlot.create("cross2");
    private static final TextureSlot INNER = TextureSlot.create("inner");

    public BlockStateGenerator(PackOutput output) {
        super(output, TwilitGourmet.MODID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        this.registerStatesAndModels(blockModels);
    }

    private void registerStatesAndModels(BlockModelGenerators models) {
        this.cabinetBlock(models, GourmetBlocks.TWILIGHT_OAK_CABINET);
        this.cabinetBlock(models, GourmetBlocks.CANOPY_CABINET);
        this.cabinetBlock(models, GourmetBlocks.MANGROVE_CABINET);
        this.cabinetBlock(models, GourmetBlocks.DARK_CABINET);
        this.cabinetBlock(models, GourmetBlocks.TIME_CABINET);
        this.cabinetBlock(models, GourmetBlocks.TRANSFORMATION_CABINET);
        this.cabinetBlock(models, GourmetBlocks.MINING_CABINET);
        this.cabinetBlock(models, GourmetBlocks.SORTING_CABINET);

        this.pieBlock(models, GourmetBlocks.MAZE_CHEESECAKE, prefix("block/maze_crust_side"), prefix("block/maze_crust_bottom"));
        this.pieBlock(models, GourmetBlocks.CRAB_QUICHE, Identifier.fromNamespaceAndPath(FarmersDelight.MODID, "block/pie_side"), Identifier.fromNamespaceAndPath(FarmersDelight.MODID, "block/pie_bottom"));
        this.pieBlock(models, GourmetBlocks.CREMESCHNITTE, prefix("block/cremeschnitte_side"), prefix("block/maze_crust_bottom"));

        models.blockStateOutput.accept(MultiVariantGenerator.dispatch(GourmetBlocks.GIANT_APPLE.get())
                .with(PropertyDispatch.initial(GiantAppleBlock.SLICES).generate(slices -> {
                    String suffix = slices == 0 ? "_core" : slices == 8 ? "" : "_" + slices + "_slice";
                    return plainVariant(prefix("block/giant_apple" + suffix));
                }))
                .with(ROTATION_HORIZONTAL_FACING));

        models.blockStateOutput.accept(MultiVariantGenerator.dispatch(GourmetBlocks.TREE_TAPPER.get())
                .with(PropertyDispatch.initial(TreeTapperBlock.POURING)
                        .generate(pouring -> plainVariant(prefix("block/tree_tapper" + (pouring ? "_pouring" : "")))))
                .with(ROTATION_HORIZONTAL_FACING));

        models.blockStateOutput.accept(MultiVariantGenerator.dispatch(GourmetBlocks.SYRUP_CAULDRON.get())
                .with(PropertyDispatch.initial(LayeredCauldronBlock.LEVEL).generate(level -> {
                    ModelTemplate template = new ModelTemplate(Optional.of(Identifier.withDefaultNamespace(
                            "block/template_cauldron_" + (level == 3 ? "full" : "level" + level))), Optional.empty(), TextureSlot.CONTENT);
                    Identifier model = template.createWithSuffix(GourmetBlocks.SYRUP_CAULDRON.get(), "_level_" + level,
                            new TextureMapping().put(TextureSlot.CONTENT, new Material(Identifier.withDefaultNamespace("block/water_still"))), models.modelOutput);
                    return plainVariant(model);
                })));

        models.blockStateOutput.accept(MultiVariantGenerator.dispatch(GourmetBlocks.PANCAKE_STACK.get())
                .with(PropertyDispatch.initial(PancakeStackBlock.PANCAKES, PancakeStackBlock.SYRUP).generate((pancakes, syrup) -> {
                    String name = "pancake_stack_" + pancakes;
                    String suffix = syrup ? "_syrup" : "";
                    ModelTemplate template = new ModelTemplate(Optional.of(prefix("block/template_" + name)), Optional.empty(), PANCAKE);
                    Identifier model = template.create(prefix("block/" + name + suffix),
                            new TextureMapping().put(PANCAKE, new Material(prefix("block/pancake" + suffix))), models.modelOutput);
                    return plainVariant(model);
                })));

        models.blockStateOutput.accept(MultiVariantGenerator.dispatch(GourmetBlocks.CRAB_CAKES.get())
                .with(PropertyDispatch.initial(FeastBlock.SERVINGS).generate(servings -> plainVariant(prefix("block/crab_cakes_" + servings))))
                .with(ROTATION_HORIZONTAL_FACING));
        models.blockStateOutput.accept(MultiVariantGenerator.dispatch(GourmetBlocks.COOKED_CRAB.get())
                .with(PropertyDispatch.initial(CookedCrabBlock.SERVINGS).generate(servings -> plainVariant(prefix("block/cooked_crab_" + servings))))
                .with(ROTATION_HORIZONTAL_FACING));

        models.blockStateOutput.accept(MultiPartGenerator.multiPart(GourmetBlocks.BREADCRUMBS.get())
                .with(plainVariant(prefix("block/breadcrumbs")))
                .with(condition().term(BreadcrumbBlock.NORTH, true), plainVariant(prefix("block/breadcrumbs_extension")))
                .with(condition().term(BreadcrumbBlock.SOUTH, true), plainVariant(prefix("block/breadcrumbs_extension")).with(Y_ROT_180))
                .with(condition().term(BreadcrumbBlock.EAST, true), plainVariant(prefix("block/breadcrumbs_extension")).with(Y_ROT_90))
                .with(condition().term(BreadcrumbBlock.WEST, true), plainVariant(prefix("block/breadcrumbs_extension")).with(Y_ROT_270)));

        models.blockStateOutput.accept(MultiVariantGenerator.dispatch(GourmetBlocks.MUSHGLOOM_COLONY.get())
                .with(PropertyDispatch.initial(MushroomColonyBlock.COLONY_AGE).generate(age -> {
                    String name = "mushgloom_colony_stage" + age;
                    ModelTemplate template = new ModelTemplate(Optional.of(TwilightForestMod.prefix("block/mushgloom")), Optional.empty(), TextureSlot.CROSS, CROSS2);
                    Identifier model = template.create(prefix("block/" + name), new TextureMapping()
                            .put(TextureSlot.CROSS, new Material(prefix("block/" + name)))
                            .put(CROSS2, new Material(prefix("block/" + name + "_head"))), models.modelOutput);
                    return plainVariant(model);
                })));
    }

    private void cabinetBlock(BlockModelGenerators models, DeferredBlock<?> block) {
        TextureMapping textures = TextureMapping.orientableCubeOnlyTop(block.get());
        Identifier closed = ModelTemplates.CUBE_ORIENTABLE.create(block.get(), textures, models.modelOutput);
        Identifier open = ModelTemplates.CUBE_ORIENTABLE.createWithSuffix(block.get(), "_open",
                textures.copyAndUpdate(TextureSlot.FRONT, TextureMapping.getBlockTexture(block.get(), "_front_open")), models.modelOutput);
        models.blockStateOutput.accept(MultiVariantGenerator.dispatch(block.get())
                .with(PropertyDispatch.initial(CabinetBlock.OPEN).select(false, plainVariant(closed)).select(true, plainVariant(open)))
                .with(ROTATION_HORIZONTAL_FACING));

        Identifier item = new ModelTemplate(Optional.of(closed), Optional.empty())
                .create(block.asItem(), new TextureMapping(), models.modelOutput);
        models.itemModelOutput.accept(block.asItem(), ItemModelUtils.plainModel(item));
    }

    private void pieBlock(BlockModelGenerators models, DeferredBlock<Block> block, Identifier side, Identifier bottom) {
        models.blockStateOutput.accept(MultiVariantGenerator.dispatch(block.get())
                .with(PropertyDispatch.initial(PieBlock.BITES)
                        .generate(bites -> plainVariant(this.pieModel(models, block, bites, side, bottom))))
                .with(ROTATION_HORIZONTAL_FACING));
    }

    private Identifier pieModel(BlockModelGenerators models, DeferredBlock<Block> block, int bites, Identifier side, Identifier bottom) {
        String suffix = bites == 0 ? "" : "_slice" + bites;
        ModelTemplate template = new ModelTemplate(Optional.of(Identifier.fromNamespaceAndPath(FarmersDelight.MODID, "block/template_pie" + suffix)),
                Optional.empty(), TextureSlot.BOTTOM, TextureSlot.SIDE, TextureSlot.TOP);
        TextureMapping textures = new TextureMapping()
                .put(TextureSlot.BOTTOM, new Material(bottom))
                .put(TextureSlot.SIDE, new Material(side))
                .put(TextureSlot.TOP, TextureMapping.getBlockTexture(block.get(), "_top"));
        if (bites > 0) {
            template = template.extend().requiredTextureSlot(INNER).build();
            textures.put(INNER, TextureMapping.getBlockTexture(block.get(), "_inner"));
        }
        return template.createWithSuffix(block.get(), suffix, textures, models.modelOutput);
    }

    @Override
    protected Stream<? extends Holder<Block>> getKnownBlocks() {
        return GourmetBlocks.BLOCKS.getEntries().stream().filter(block -> block != GourmetBlocks.FIDDLEHEAD);
    }

    @Override
    protected Stream<? extends Holder<Item>> getKnownItems() {
        return this.getKnownBlocks().filter(block -> block.value() instanceof CabinetBlock)
                .map(block -> block.value().asItem().builtInRegistryHolder());
    }

    @Override
    public String getName() {
        return "Block States: " + TwilitGourmet.MODID;
    }
}
