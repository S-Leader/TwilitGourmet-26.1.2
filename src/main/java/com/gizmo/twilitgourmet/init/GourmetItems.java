package com.gizmo.twilitgourmet.init;

import com.gizmo.twilitgourmet.TwilitGourmet;
import com.gizmo.twilitgourmet.item.CrabPartItem;
import com.gizmo.twilitgourmet.item.FieryKnifeItem;
import com.gizmo.twilitgourmet.item.KnightmetalKnifeItem;
import com.gizmo.twilitgourmet.item.SyrupBottleItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import twilightforest.init.TFToolMaterials;
import vectorwing.farmersdelight.common.FoodValues;
import vectorwing.farmersdelight.common.item.*;
import vectorwing.farmersdelight.common.tag.ModTags;

public class GourmetItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(TwilitGourmet.MODID);

    public static final DeferredItem<Item> GROUND_VENISON = ITEMS.registerItem("ground_venison", properties -> new Item(properties.food(GourmetFoods.GROUND_VENISON, GourmetFoods.consumableFor(GourmetFoods.GROUND_VENISON))));
    public static final DeferredItem<Item> VENISON_PATTY = ITEMS.registerItem("venison_patty", properties -> new Item(properties.food(GourmetFoods.VENISON_PATTY, GourmetFoods.consumableFor(GourmetFoods.VENISON_PATTY))));
    public static final DeferredItem<Item> MINCED_MEEF = ITEMS.registerItem("minced_meef", properties -> new Item(properties.food(GourmetFoods.MINCED_MEEF, GourmetFoods.consumableFor(GourmetFoods.MINCED_MEEF))));
    public static final DeferredItem<Item> MEEF_PATTY = ITEMS.registerItem("meef_patty", properties -> new Item(properties.food(GourmetFoods.MEEF_PATTY, GourmetFoods.consumableFor(GourmetFoods.MEEF_PATTY))));
    public static final DeferredItem<KnifeItem> IRONWOOD_KNIFE = ITEMS.registerItem("ironwood_knife", properties -> new KnifeItem(TFToolMaterials.IRONWOOD, knifeProperties(properties, TFToolMaterials.IRONWOOD)));
    public static final DeferredItem<KnifeItem> STEELEAF_KNIFE = ITEMS.registerItem("steeleaf_knife", properties -> new KnifeItem(TFToolMaterials.STEELEAF, knifeProperties(properties, TFToolMaterials.STEELEAF)));
    public static final DeferredItem<KnifeItem> KNIGHTMETAL_KNIFE = ITEMS.registerItem("knightmetal_knife", properties -> new KnightmetalKnifeItem(TFToolMaterials.KNIGHTMETAL, knifeProperties(properties, TFToolMaterials.KNIGHTMETAL)));
    public static final DeferredItem<KnifeItem> FIERY_KNIFE = ITEMS.registerItem("fiery_knife", properties -> new FieryKnifeItem(TFToolMaterials.FIERY, knifeProperties(properties, TFToolMaterials.FIERY)));
    public static final DeferredItem<KnifeItem> ICE_KNIFE = ITEMS.registerItem("ice_knife", properties -> new KnifeItem(TFToolMaterials.ICE, knifeProperties(properties, TFToolMaterials.ICE)));
    public static final DeferredItem<Item> MUSHGLOOM_COLONY = ITEMS.registerItem("mushgloom_colony", properties -> new MushroomColonyItem(GourmetBlocks.MUSHGLOOM_COLONY.get(), properties.useBlockDescriptionPrefix()));

    public static final DeferredItem<Item> APPLE_SLICE = ITEMS.registerItem("apple_slice", properties -> new Item(properties.food(GourmetFoods.APPLE_SLICE, GourmetFoods.consumableFor(GourmetFoods.APPLE_SLICE))));
    public static final DeferredItem<Item> PANCAKE = ITEMS.registerItem("pancake", properties -> new Item(properties.food(GourmetFoods.PANCAKE, GourmetFoods.consumableFor(GourmetFoods.PANCAKE))));
    public static final DeferredItem<Item> PANCAKE_STACK = ITEMS.registerItem("pancake_stack", properties -> new PlaceableItem(GourmetBlocks.PANCAKE_STACK.get(), properties.useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> SYRUP_BOTTLE = ITEMS.registerItem("syrup_bottle", properties -> new SyrupBottleItem(properties.food(GourmetFoods.SYRUP_BOTTLE, GourmetFoods.consumableFor(GourmetFoods.SYRUP_BOTTLE)).stacksTo(16).craftRemainder(Items.GLASS_BOTTLE).usingConvertsTo(Items.GLASS_BOTTLE)));
    public static final DeferredItem<Item> RAW_BOARKCHOP = ITEMS.registerItem("boarkchop", properties -> new Item(properties.food(GourmetFoods.RAW_BOARKCHOP, GourmetFoods.consumableFor(GourmetFoods.RAW_BOARKCHOP))));
    public static final DeferredItem<Item> COOKED_BOARKCHOP = ITEMS.registerItem("cooked_boarkchop", properties -> new Item(properties.food(GourmetFoods.COOKED_BOARKCHOP, GourmetFoods.consumableFor(GourmetFoods.COOKED_BOARKCHOP))));
    public static final DeferredItem<Item> VENISON_KEBAB = ITEMS.registerItem("venison_kebab", properties -> new ConsumableItem(properties.food(GourmetFoods.VENISON_KEBAB, GourmetFoods.consumableFor(GourmetFoods.VENISON_KEBAB)).craftRemainder(Items.STICK).usingConvertsTo(Items.STICK)));
    public static final DeferredItem<Item> VENISON_SLIDER = ITEMS.registerItem("venison_slider", properties -> new ConsumableItem(properties.food(GourmetFoods.VENISON_SLIDER, GourmetFoods.consumableFor(GourmetFoods.VENISON_SLIDER))));
    public static final DeferredItem<Item> VENISON_AND_VEGGIES = ITEMS.registerItem("venison_and_root_veggies", properties -> new ConsumableItem(properties.food(GourmetFoods.VENISON_AND_VEGGIES, GourmetFoods.consumableFor(GourmetFoods.VENISON_AND_VEGGIES)).stacksTo(16).craftRemainder(Items.BOWL).usingConvertsTo(Items.BOWL)));
    public static final DeferredItem<Item> GOULASH = ITEMS.registerItem("goulash", properties -> new ConsumableItem(properties.food(GourmetFoods.GOULASH, GourmetFoods.consumableFor(GourmetFoods.GOULASH)).stacksTo(16).craftRemainder(Items.BOWL).usingConvertsTo(Items.BOWL)));
    public static final DeferredItem<Item> MUSHGLOOM_STEW = ITEMS.registerItem("mushgloom_stew", properties -> new ConsumableItem(properties.food(GourmetFoods.MUSHGLOOM_STEW, GourmetFoods.consumableFor(GourmetFoods.MUSHGLOOM_STEW)).stacksTo(16).craftRemainder(Items.BOWL).usingConvertsTo(Items.BOWL)));
    public static final DeferredItem<Item> FIDDLEHEAD_RISOTTO = ITEMS.registerItem("fiddlehead_risotto", properties -> new ConsumableItem(properties.food(GourmetFoods.FIDDLEHEAD_RISOTTO, GourmetFoods.consumableFor(GourmetFoods.FIDDLEHEAD_RISOTTO)).stacksTo(16).craftRemainder(Items.BOWL).usingConvertsTo(Items.BOWL)));
    public static final DeferredItem<Item> BERRY_SMOOTHIE = ITEMS.registerItem("berry_smoothie", properties -> new DrinkableItem(properties.food(GourmetFoods.BERRY_SMOOTHIE, GourmetFoods.consumableFor(GourmetFoods.BERRY_SMOOTHIE)).stacksTo(16).craftRemainder(Items.GLASS_BOTTLE).usingConvertsTo(Items.GLASS_BOTTLE), false, false));
    public static final DeferredItem<Item> MAZE_WAFER_CRUST = ITEMS.registerItem("maze_wafer_crust", properties -> new Item(properties.food(FoodValues.PIE_CRUST, GourmetFoods.consumableFor(FoodValues.PIE_CRUST))));
    public static final DeferredItem<Item> MAZE_CHEESECAKE = ITEMS.registerItem("maze_cheesecake", properties -> new PlaceableItem(GourmetBlocks.MAZE_CHEESECAKE.get(), properties.useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> MAZE_CHEESECAKE_SLICE = ITEMS.registerItem("maze_cheesecake_slice", properties -> new ConsumableItem(properties.food(FoodValues.PIE_SLICE, GourmetFoods.consumableFor(FoodValues.PIE_SLICE))));
    public static final DeferredItem<Item> CREMESCHNITTE = ITEMS.registerItem("cremeschnitte", properties -> new PlaceableItem(GourmetBlocks.CREMESCHNITTE.get(), properties.useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> CREMESCHNITTE_SLICE = ITEMS.registerItem("cremeschnitte_slice", properties -> new ConsumableItem(properties.food(FoodValues.PIE_SLICE, GourmetFoods.consumableFor(FoodValues.PIE_SLICE))));

    public static final DeferredItem<Item> FIDDLEHEAD_SPORES = ITEMS.registerItem("fiddlehead_spores", properties -> new BlockItem(GourmetBlocks.FIDDLEHEAD.get(), properties));
    public static final DeferredItem<Item> FIDDLEHEAD = ITEMS.registerItem("fiddlehead", properties -> new Item(properties.food(GourmetFoods.FIDDLEHEAD, GourmetFoods.consumableFor(GourmetFoods.FIDDLEHEAD))));
    public static final DeferredItem<Item> SAUTEED_FIDDLEHEAD = ITEMS.registerItem("sauteed_fiddlehead", properties -> new Item(properties.food(GourmetFoods.SAUTEED_FIDDLEHEAD, GourmetFoods.consumableFor(GourmetFoods.SAUTEED_FIDDLEHEAD))));
    public static final DeferredItem<Item> IRONWOOD_FIDDLEHEAD = ITEMS.registerItem("ironwood_fiddlehead", properties -> new Item(properties.food(GourmetFoods.IRONWOOD_FIDDLEHEAD, GourmetFoods.consumableFor(GourmetFoods.IRONWOOD_FIDDLEHEAD))));

    //crab things
    public static final DeferredItem<Item> CRAB_SHELL_FRAGMENT = ITEMS.registerItem("crab_shell_fragment", properties -> new Item(properties));
    public static final DeferredItem<Item> CRAB_LEG = ITEMS.registerItem("crab_leg", properties -> new CrabPartItem(properties.food(GourmetFoods.CRAB_MEAT, GourmetFoods.consumableFor(GourmetFoods.CRAB_MEAT))));
    public static final DeferredItem<Item> CRAB_CLAW = ITEMS.registerItem("crab_claw", properties -> new CrabPartItem(properties.food(GourmetFoods.CRAB_MEAT, GourmetFoods.consumableFor(GourmetFoods.CRAB_MEAT))));
    public static final DeferredItem<Item> SHELL_HELMET = ITEMS.registerItem("shell_helmet", properties -> new Item(properties.humanoidArmor(GourmetArmorMaterials.SHELL, ArmorType.HELMET)));
    public static final DeferredItem<Item> CRAB_MEAT = ITEMS.registerItem("crab_meat", properties -> new Item(properties.food(GourmetFoods.CRAB_MEAT, GourmetFoods.consumableFor(GourmetFoods.CRAB_MEAT))));
    public static final DeferredItem<Item> COOKED_CRAB_MEAT = ITEMS.registerItem("cooked_crab_meat", properties -> new Item(properties.food(GourmetFoods.COOKED_CRAB_MEAT, GourmetFoods.consumableFor(GourmetFoods.COOKED_CRAB_MEAT))));
    public static final DeferredItem<Item> CRAB_JERKY = ITEMS.registerItem("crab_jerky", properties -> new Item(properties.food(GourmetFoods.CRAB_JERKY, GourmetFoods.consumableFor(GourmetFoods.CRAB_JERKY))));
    public static final DeferredItem<Item> WHOLE_CRAB = ITEMS.registerItem("whole_crab", properties -> new Item(properties));
    public static final DeferredItem<Item> COOKED_CRAB = ITEMS.registerItem("cooked_crab", properties -> new PlaceableItem(GourmetBlocks.COOKED_CRAB.get(), properties.useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> CRAB_CAKES = ITEMS.registerItem("crab_cakes", properties -> new PlaceableItem(GourmetBlocks.CRAB_CAKES.get(), properties.useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> CRAB_CAKE = ITEMS.registerItem("crab_cake", properties -> new ConsumableItem(properties.food(GourmetFoods.CRAB_CAKE, GourmetFoods.consumableFor(GourmetFoods.CRAB_CAKE)).stacksTo(16).craftRemainder(Items.BOWL).usingConvertsTo(Items.BOWL)));
    public static final DeferredItem<Item> CRAB_QUICHE = ITEMS.registerItem("crab_quiche", properties -> new PlaceableItem(GourmetBlocks.CRAB_QUICHE.get(), properties.useBlockDescriptionPrefix()));
    public static final DeferredItem<Item> CRAB_QUICHE_SLICE = ITEMS.registerItem("crab_quiche_slice", properties -> new ConsumableItem(properties.food(FoodValues.PIE_SLICE, GourmetFoods.consumableFor(FoodValues.PIE_SLICE))));

    //cheesy
    public static final DeferredItem<Item> PRESSED_CHEESE = ITEMS.registerItem("pressed_cheese", properties -> new Item(properties));
    public static final DeferredItem<Item> ROTTEN_CHEESE = ITEMS.registerItem("rotten_cheese", properties -> new Item(properties));
    public static final DeferredItem<Item> YOUNG_CHEESE = ITEMS.registerItem("young_cheese", properties -> new Item(properties));
    public static final DeferredItem<Item> AGED_CHEESE = ITEMS.registerItem("aged_cheese", properties -> new Item(properties));
    public static final DeferredItem<Item> MATURE_CHEESE = ITEMS.registerItem("mature_cheese", properties -> new Item(properties));
    public static final DeferredItem<Item> YOUNG_SMOKED_CHEESE = ITEMS.registerItem("young_smoked_cheese", properties -> new Item(properties));
    public static final DeferredItem<Item> AGED_SMOKED_CHEESE = ITEMS.registerItem("aged_smoked_cheese", properties -> new Item(properties));
    public static final DeferredItem<Item> MATURE_SMOKED_CHEESE = ITEMS.registerItem("mature_smoked_cheese", properties -> new Item(properties));
    public static final DeferredItem<Item> YOUNG_FUNKY_CHEESE = ITEMS.registerItem("young_funky_cheese", properties -> new Item(properties));
    public static final DeferredItem<Item> AGED_FUNKY_CHEESE = ITEMS.registerItem("aged_funky_cheese", properties -> new Item(properties));
    public static final DeferredItem<Item> MATURE_FUNKY_CHEESE = ITEMS.registerItem("mature_funky_cheese", properties -> new Item(properties));

    private static Item.Properties knifeProperties(Item.Properties properties, ToolMaterial material) {
        return material.applyToolProperties(properties, ModTags.Blocks.MINEABLE_WITH_KNIFE, 0.5F, -2.0F, 0.0F);
    }
}
