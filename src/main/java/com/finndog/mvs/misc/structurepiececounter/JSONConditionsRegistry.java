package com.finndog.mvs.misc.structurepiececounter;

import com.finndog.mvs.MVSMain;
import com.mojang.serialization.Lifecycle;
import net.minecraft.core.MappedRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.WritableRegistry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Supplier;

public final class JSONConditionsRegistry {
    private JSONConditionsRegistry() {}

    public static final ResourceKey<Registry<Supplier<Boolean>>> MVS_JSON_CONDITIONS_KEY = ResourceKey.createRegistryKey(new ResourceLocation(MVSMain.MODID, "json_conditions"));
    public static final Registry<Supplier<Boolean>> MVS_JSON_CONDITIONS_REGISTRY = createRegistry(MVS_JSON_CONDITIONS_KEY);

    public static void registerTestJSONCondition() {
        // Registers a condition for testing purposes.
        Registry.REGISTRY.getOptional(new ResourceLocation(MVSMain.MODID, "mvs_json_conditions"))
            .ifPresent(registry -> Registry.register(
                (Registry<Supplier<Boolean>>)registry,
                new ResourceLocation(MVSMain.MODID, "mvs_test"),
                () -> false));
    }

    private static <T, R extends Registry<T>> R createRegistry(ResourceKey<R> resourceKey) {
        return ((WritableRegistry<R>)Registry.REGISTRY).register(resourceKey, (R)new MappedRegistry<T>(resourceKey, Lifecycle.stable(), null), Lifecycle.stable()).value();
    }
}
