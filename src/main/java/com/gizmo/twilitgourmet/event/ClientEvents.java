package com.gizmo.twilitgourmet.event;

import com.gizmo.twilitgourmet.TwilitGourmet;
import com.gizmo.twilitgourmet.client.ShellHelmetExtension;
import com.gizmo.twilitgourmet.client.ShellHelmetModel;
import com.gizmo.twilitgourmet.client.SyrupBlockTintSource;
import com.gizmo.twilitgourmet.client.SyrupItemTintSource;
import com.gizmo.twilitgourmet.init.GourmetBlocks;
import com.gizmo.twilitgourmet.init.GourmetItems;
import net.minecraft.client.color.block.BlockTintSources;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;

import java.util.List;

public class ClientEvents {
    public static void register(IEventBus bus) {
        bus.addListener(ClientEvents::registerExtensions);
        bus.addListener(ClientEvents::registerLayers);
        bus.addListener(ClientEvents::registerBlockColors);
        bus.addListener(ClientEvents::registerItemColors);
    }

    private static void registerExtensions(RegisterClientExtensionsEvent event) {
        event.registerItem(new ShellHelmetExtension(), GourmetItems.SHELL_HELMET.get());
    }

    private static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ShellHelmetModel.SHELL_HELMET_INNER, () -> ShellHelmetModel.addPieces(new CubeDeformation(0.5F)));
        event.registerLayerDefinition(ShellHelmetModel.SHELL_HELMET_OUTER, () -> ShellHelmetModel.addPieces(new CubeDeformation(1.0F)));
    }

    private static void registerBlockColors(RegisterColorHandlersEvent.BlockTintSources event) {
        event.register(List.of(new SyrupBlockTintSource(false)), GourmetBlocks.SYRUP_CAULDRON.get(), GourmetBlocks.PANCAKE_STACK.get());
        event.register(List.of(BlockTintSources.constant(-1), new SyrupBlockTintSource(true)), GourmetBlocks.TREE_TAPPER.get());
    }

    private static void registerItemColors(RegisterColorHandlersEvent.ItemTintSources event) {
        event.register(TwilitGourmet.prefix("syrup"), SyrupItemTintSource.MAP_CODEC);
    }
}
