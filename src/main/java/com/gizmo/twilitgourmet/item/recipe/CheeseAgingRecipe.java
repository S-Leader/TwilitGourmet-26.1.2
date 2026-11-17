package com.gizmo.twilitgourmet.item.recipe;

import com.gizmo.twilitgourmet.init.GourmetRecipeSerializers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryCodecs;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SingleItemRecipe;
import net.minecraft.world.level.block.Block;
import twilightforest.item.recipe.DryingRecipe;

import java.util.Optional;

public class CheeseAgingRecipe extends DryingRecipe {
    public static final MapCodec<CheeseAgingRecipe> MAP_CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(CommonInfo.MAP_CODEC.forGetter(recipe -> recipe.commonInfo), Ingredient.CODEC.fieldOf("input").forGetter(DryingRecipe::getInput), ItemStackTemplate.CODEC.fieldOf("result").forGetter(DryingRecipe::getResult), Codec.INT.fieldOf("aging_time").forGetter(DryingRecipe::getDryingTime), RotInfo.CODEC.optionalFieldOf("rot_handler").forGetter(CheeseAgingRecipe::getRotInfo)).apply(instance, CheeseAgingRecipe::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, CheeseAgingRecipe> STREAM_CODEC = StreamCodec.composite(CommonInfo.STREAM_CODEC, recipe -> recipe.commonInfo, Ingredient.CONTENTS_STREAM_CODEC, DryingRecipe::getInput, ItemStackTemplate.STREAM_CODEC, DryingRecipe::getResult, ByteBufCodecs.INT, DryingRecipe::getDryingTime, ByteBufCodecs.optional(RotInfo.STREAM_CODEC), CheeseAgingRecipe::getRotInfo, CheeseAgingRecipe::new);

    public static final RecipeSerializer<CheeseAgingRecipe> SERIALIZER = new RecipeSerializer<>(MAP_CODEC, STREAM_CODEC);

    private final Optional<RotInfo> rotInfo;

    public CheeseAgingRecipe(CommonInfo commonInfo, Ingredient ingredient, ItemStackTemplate result, int dryingTime, Optional<RotInfo> rotInfo) {
        super(commonInfo, ingredient, result, dryingTime);
        this.rotInfo = rotInfo;
    }

    @Override
    public RecipeSerializer<? extends SingleItemRecipe> getSerializer() {
        return GourmetRecipeSerializers.CHEESE_AGING_SERIALIZER.get();
    }

    public Optional<RotInfo> getRotInfo() {
        return this.rotInfo;
    }

    public record RotInfo(ItemStackTemplate rotsInto, int checkRadius, boolean checkSkylight,
                          HolderSet<Block> rotCausers) {
        public static final Codec<RotInfo> CODEC = RecordCodecBuilder.create(instance -> instance.group(ItemStackTemplate.CODEC.fieldOf("rots_into").forGetter(RotInfo::rotsInto), Codec.INT.fieldOf("check_radius").forGetter(RotInfo::checkRadius), Codec.BOOL.fieldOf("check_skylight").forGetter(RotInfo::checkSkylight), RegistryCodecs.homogeneousList(Registries.BLOCK).fieldOf("rot_causers").forGetter(RotInfo::rotCausers)).apply(instance, RotInfo::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, RotInfo> STREAM_CODEC = StreamCodec.composite(ItemStackTemplate.STREAM_CODEC, RotInfo::rotsInto, ByteBufCodecs.INT, RotInfo::checkRadius, ByteBufCodecs.BOOL, RotInfo::checkSkylight, ByteBufCodecs.holderSet(Registries.BLOCK), RotInfo::rotCausers, RotInfo::new);
    }
}
