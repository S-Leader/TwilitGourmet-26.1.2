package com.gizmo.twilitgourmet.init;

import com.gizmo.twilitgourmet.TwilitGourmet;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAssets;

import java.util.Map;

public final class GourmetArmorMaterials {
    public static final ArmorMaterial SHELL = new ArmorMaterial(20, Map.of(ArmorType.BOOTS, 3, ArmorType.LEGGINGS, 7, ArmorType.CHESTPLATE, 9, ArmorType.HELMET, 4, ArmorType.BODY, 15), 15, SoundEvents.ARMOR_EQUIP_TURTLE, 2.0F, 0.05F, ItemTags.create(TwilitGourmet.prefix("repairs_shell_armor")), ResourceKey.create(EquipmentAssets.ROOT_ID, TwilitGourmet.prefix("shell")));
}
