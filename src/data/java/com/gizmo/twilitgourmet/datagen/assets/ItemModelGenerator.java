package com.gizmo.twilitgourmet.datagen.assets;

import com.gizmo.twilitgourmet.TwilitGourmet;
import com.gizmo.twilitgourmet.client.SyrupItemTintSource;
import com.gizmo.twilitgourmet.init.GourmetBlocks;
import com.gizmo.twilitgourmet.init.GourmetItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.*;
import net.minecraft.client.renderer.item.properties.select.DisplayContext;
import net.minecraft.client.resources.model.UnbakedModel;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.ExtraFaceData;
import net.neoforged.neoforge.client.model.generators.template.ExtendedModelTemplateBuilder;
import net.neoforged.neoforge.registries.DeferredItem;
import vectorwing.farmersdelight.common.block.CabinetBlock;

import java.util.stream.Stream;

import static com.gizmo.twilitgourmet.TwilitGourmet.prefix;

public class ItemModelGenerator extends ModelProvider {
    public ItemModelGenerator(PackOutput output) {
        super(output, TwilitGourmet.MODID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        this.registerModels(itemModels);
    }

    private void registerModels(ItemModelGenerators models) {
        this.tool(models, GourmetItems.ICE_KNIFE.get(), prefix("item/ice_knife_solid"), prefix("item/ice_knife_clear"));
        this.singleTex(models, GourmetItems.APPLE_SLICE);
        this.singleTexTool(models, GourmetItems.IRONWOOD_KNIFE);
        this.singleTexTool(models, GourmetItems.STEELEAF_KNIFE);
        this.singleTexTool(models, GourmetItems.KNIGHTMETAL_KNIFE);
        this.singleTexFullbrightTool(models, GourmetItems.FIERY_KNIFE);
        this.syrupBottle(models);

        this.singleTex(models, GourmetItems.RAW_BOARKCHOP);
        this.singleTex(models, GourmetItems.COOKED_BOARKCHOP);
        this.singleTex(models, GourmetItems.GROUND_VENISON);
        this.singleTex(models, GourmetItems.VENISON_PATTY);
        this.singleTex(models, GourmetItems.MINCED_MEEF);
        this.singleTex(models, GourmetItems.MEEF_PATTY);
        this.singleTex(models, GourmetItems.MAZE_WAFER_CRUST);
        //Kebab should "held in hand" lol -- keletu.
        this.singleTexTool(models, GourmetItems.VENISON_KEBAB);
        this.singleTex(models, GourmetItems.VENISON_SLIDER);
        this.singleTex(models, GourmetItems.VENISON_AND_VEGGIES);
        this.bowlFood(models, GourmetItems.GOULASH);
        this.bowlFood(models, GourmetItems.MUSHGLOOM_STEW);
        this.singleTex(models, GourmetItems.FIDDLEHEAD_RISOTTO);
        this.singleTex(models, GourmetItems.BERRY_SMOOTHIE);
        this.singleTex(models, GourmetItems.CRAB_SHELL_FRAGMENT);
        this.singleTex(models, GourmetItems.CRAB_CLAW);
        this.singleTex(models, GourmetItems.CRAB_LEG);
        this.singleTex(models, GourmetItems.SHELL_HELMET);
        this.singleTex(models, GourmetItems.CRAB_MEAT);
        this.singleTex(models, GourmetItems.COOKED_CRAB_MEAT);
        this.singleTex(models, GourmetItems.CRAB_JERKY);
        this.singleTex(models, GourmetItems.WHOLE_CRAB);
        this.singleTex(models, GourmetItems.COOKED_CRAB);
        this.singleTex(models, GourmetItems.CRAB_CAKE);
        this.singleTex(models, GourmetItems.CRAB_CAKES);
        this.singleTex(models, GourmetItems.CRAB_QUICHE);
        this.singleTex(models, GourmetItems.CRAB_QUICHE_SLICE);
        this.singleTex(models, GourmetItems.MAZE_CHEESECAKE);
        this.singleTex(models, GourmetItems.MAZE_CHEESECAKE_SLICE);
        this.singleTex(models, GourmetItems.CREMESCHNITTE);
        this.singleTex(models, GourmetItems.CREMESCHNITTE_SLICE);
        this.singleTex(models, GourmetItems.PANCAKE);
        this.singleTex(models, GourmetItems.PANCAKE_STACK);
        this.singleTex(models, GourmetItems.MUSHGLOOM_COLONY);

        this.singleTex(models, GourmetItems.FIDDLEHEAD);
        this.singleTex(models, GourmetItems.FIDDLEHEAD_SPORES);
        this.singleTex(models, GourmetItems.SAUTEED_FIDDLEHEAD);
        this.singleTex(models, GourmetItems.IRONWOOD_FIDDLEHEAD);

        this.singleTex(models, GourmetItems.PRESSED_CHEESE);
        this.singleTex(models, GourmetItems.ROTTEN_CHEESE);
        this.singleTex(models, GourmetItems.YOUNG_CHEESE);
        this.singleTex(models, GourmetItems.AGED_CHEESE);
        this.singleTex(models, GourmetItems.MATURE_CHEESE);
        this.singleTex(models, GourmetItems.YOUNG_SMOKED_CHEESE);
        this.singleTex(models, GourmetItems.AGED_SMOKED_CHEESE);
        this.singleTex(models, GourmetItems.MATURE_SMOKED_CHEESE);
        this.singleTex(models, GourmetItems.YOUNG_FUNKY_CHEESE);
        this.singleTex(models, GourmetItems.AGED_FUNKY_CHEESE);
        this.singleTex(models, GourmetItems.MATURE_FUNKY_CHEESE);

        this.basicItem(models, GourmetBlocks.BREADCRUMBS.asItem());
        this.basicItem(models, GourmetBlocks.TREE_TAPPER.asItem());

        this.giantApple(models, GourmetBlocks.GIANT_APPLE.asItem(), Identifier.withDefaultNamespace("item/apple"));
    }

    private void singleTexFullbrightTool(ItemModelGenerators models, DeferredItem<? extends Item> item) {
        this.buildItem(models, item.get(), "item/handheld", 15, item.getId().withPrefix("item/"));
    }

    private void singleTexTool(ItemModelGenerators models, DeferredItem<? extends Item> item) {
        this.tool(models, item.get(), item.getId().withPrefix("item/"));
    }

    private void singleTex(ItemModelGenerators models, DeferredItem<?> item) {
        this.generated(models, item.get(), item.getId().withPrefix("item/"));
    }

    private void basicItem(ItemModelGenerators models, Item item) {
        models.generateFlatItem(item, ModelTemplates.FLAT_ITEM);
    }

    private void generated(ItemModelGenerators models, Item item, Identifier... layers) {
        this.buildItem(models, item, "item/generated", 0, layers);
    }

    private void tool(ItemModelGenerators models, Item item, Identifier... layers) {
        this.buildItem(models, item, "item/handheld", 0, layers);
    }

    private void buildItem(ItemModelGenerators models, Item item, String parent, int emissivity, Identifier... layers) {
        ExtendedModelTemplateBuilder builder = ExtendedModelTemplateBuilder.builder()
                .parent(Identifier.withDefaultNamespace(parent));
        TextureMapping textures = new TextureMapping();
        for (int i = 0; i < layers.length; i++) {
            TextureSlot layer = TextureSlot.create("layer" + i);
            builder.requiredTextureSlot(layer);
            textures.put(layer, new Material(layers[i], emissivity > 0 && i == 0));
        }
        if (emissivity > 0) {
            builder.itemLayerFaceData("layer0", new ExtraFaceData(-1, emissivity, true));
        }
        Identifier model = builder.build().create(item, textures, models.modelOutput);
        models.itemModelOutput.accept(item, ItemModelUtils.plainModel(model));
    }

    private void bowlFood(ItemModelGenerators models, DeferredItem<?> item) {
        Material food = TextureMapping.getItemTexture(item.get());
        ModelTemplate template = ModelTemplates.TWO_LAYERED_ITEM.extend()
                .requiredTextureSlot(TextureSlot.PARTICLE)
                .build();
        TextureMapping textures = TextureMapping.layered(new Material(Identifier.withDefaultNamespace("item/bowl")), food)
                .put(TextureSlot.PARTICLE, food);
        Identifier model = template.create(item.get(), textures, models.modelOutput);
        models.itemModelOutput.accept(item.get(), ItemModelUtils.plainModel(model));
    }

    private void syrupBottle(ItemModelGenerators models) {
        Identifier model = ModelTemplates.TWO_LAYERED_ITEM.create(GourmetItems.SYRUP_BOTTLE.get(),
                TextureMapping.layered(new Material(Identifier.withDefaultNamespace("item/potion_overlay")),
                        new Material(Identifier.withDefaultNamespace("item/potion"))), models.modelOutput);
        models.itemModelOutput.accept(GourmetItems.SYRUP_BOTTLE.get(),
                ItemModelUtils.tintedModel(model, new SyrupItemTintSource(), ItemModelUtils.constantTint(0xFFFFFF)));
    }

    private void giantApple(ItemModelGenerators models, Item item, Identifier texture) {
        Identifier gui = ExtendedModelTemplateBuilder.builder()
                .requiredTextureSlot(TextureSlot.ALL)
                .guiLight(UnbakedModel.GuiLight.FRONT)
                .element(element -> element.from(0, 0, 0).to(16, 16, 0)
                        .face(Direction.SOUTH, face -> face.texture(TextureSlot.ALL).uvs(4, 4, 12, 12)))
                .build()
                .create(ModelLocationUtils.getModelLocation(item, "_gui"), TextureMapping.cube(new Material(texture)), models.modelOutput);

        Identifier base = ModelTemplates.FLAT_ITEM.extend()
                .transform(ItemDisplayContext.GROUND, transform -> transform.translation(0.0F, 2.0F, 0.0F).scale(2.5F))
                .transform(ItemDisplayContext.HEAD, transform -> transform.rotation(0.0F, 180.0F, 0.0F).translation(0.0F, 13.0F, 7.0F).scale(5.0F))
                .transform(ItemDisplayContext.FIXED, transform -> transform.rotation(0.0F, 180.0F, 0.0F).scale(5.0F))
                .transform(ItemDisplayContext.THIRD_PERSON_RIGHT_HAND, transform -> transform.translation(-0.1F, 28.0F, 1.5F).scale(4.25F))
                .transform(ItemDisplayContext.THIRD_PERSON_LEFT_HAND, transform -> transform.translation(-0.1F, 28.0F, 1.5F).scale(4.25F))
                .transform(ItemDisplayContext.FIRST_PERSON_RIGHT_HAND, transform -> transform.rotation(0.0F, -90.0F, 25.0F).translation(1.13F, 3.2F, 1.13F).scale(1.7F))
                .transform(ItemDisplayContext.FIRST_PERSON_LEFT_HAND, transform -> transform.rotation(0.0F, 90.0F, -25.0F).translation(1.13F, 3.2F, 1.13F).scale(1.7F))
                .build()
                .create(item, TextureMapping.layer0(new Material(texture)), models.modelOutput);

        models.itemModelOutput.accept(item, ItemModelUtils.select(new DisplayContext(), ItemModelUtils.plainModel(base),
                ItemModelUtils.when(ItemDisplayContext.GUI, ItemModelUtils.plainModel(gui))));
    }

    @Override
    protected Stream<? extends Holder<Block>> getKnownBlocks() {
        return Stream.empty();
    }

    @Override
    protected Stream<? extends Holder<Item>> getKnownItems() {
        return GourmetItems.ITEMS.getEntries().stream()
                .filter(item -> !(item.get() instanceof BlockItem blockItem && blockItem.getBlock() instanceof CabinetBlock));
    }

    @Override
    public String getName() {
        return "Item Models: " + TwilitGourmet.MODID;
    }
}
