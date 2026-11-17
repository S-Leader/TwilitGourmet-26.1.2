package com.gizmo.twilitgourmet.datagen;

import com.gizmo.twilitgourmet.TwilitGourmet;
import com.gizmo.twilitgourmet.datagen.assets.BlockStateGenerator;
import com.gizmo.twilitgourmet.datagen.assets.ItemModelGenerator;
import com.gizmo.twilitgourmet.datagen.assets.LangGenerator;
import com.gizmo.twilitgourmet.datagen.assets.SoundGenerator;
import com.gizmo.twilitgourmet.datagen.data.*;
import com.gizmo.twilitgourmet.datagen.data.tags.BlockTagGenerator;
import com.gizmo.twilitgourmet.datagen.data.tags.ItemTagGenerator;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@EventBusSubscriber(modid = TwilitGourmet.MODID)
public final class DataGenerators {
    @SubscribeEvent
    public static void gatherClient(GatherDataEvent.Client event) {
        gatherServerData(event);
        var output = event.getGenerator().getPackOutput();
        event.addProvider(new BlockStateGenerator(output));
        event.addProvider(new ItemModelGenerator(output));
        event.addProvider(new LangGenerator(output));
        event.addProvider(new SoundGenerator(output));
    }

    @SubscribeEvent
    public static void gatherServer(GatherDataEvent.Server event) {
        gatherServerData(event);
    }

    private static void gatherServerData(GatherDataEvent event) {
        var output = event.getGenerator().getPackOutput();
        var registries = new RegistryDataGenerator(output, event.getLookupProvider());
        var lookup = registries.getRegistryProvider();
        event.addProvider(registries);
        event.addProvider(new AdvancementGenerator(output, lookup));
        event.addProvider(new DataMapGenerator(output, lookup));
        event.addProvider(new LootGenerator(output, lookup));
        event.addProvider(new LootModifierGenerator(output, lookup));
        event.addProvider(new RecipeGenerator.Runner(output, lookup));
        event.addProvider(new BlockTagGenerator(output, lookup));
        event.addProvider(new ItemTagGenerator(output, lookup));
    }
}
