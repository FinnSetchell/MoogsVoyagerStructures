package com.finndog.mvs.misc.structurepiececounter;

import com.finndog.mvs.MVSMain;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.NewRegistryEvent;
import net.minecraftforge.registries.RegistryBuilder;

import java.util.function.Supplier;

public final class JSONConditionsRegistry {
    private JSONConditionsRegistry() {}

    public static final ResourceKey<Registry<Supplier<Boolean>>> MVS_JSON_CONDITIONS_KEY = ResourceKey.createRegistryKey(new ResourceLocation(MVSMain.MODID, "json_conditions"));
    public static Supplier<IForgeRegistry<Supplier<Boolean>>> MVS_JSON_CONDITIONS_REGISTRY;

    public static void createNewRegistry(NewRegistryEvent event) {
        MVS_JSON_CONDITIONS_REGISTRY = event.create(new RegistryBuilder<Supplier<Boolean>>()
                .setName(MVS_JSON_CONDITIONS_KEY.location())
                .setIDRange(1, Integer.MAX_VALUE - 1)
                .disableSaving());
    }

    public static void registerTestJSONCondition() {
        // Registers a condition for testing purposes.
        MVS_JSON_CONDITIONS_REGISTRY.get().register(
                new ResourceLocation(MVSMain.MODID, "test"),
                () -> false);
    }
}