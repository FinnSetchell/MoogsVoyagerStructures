package com.finndog.repurposedstructures.modinit.neoforge;

import com.mojang.serialization.MapCodec;
import com.finndog.repurposedstructures.RepurposedStructures;
import com.finndog.repurposedstructures.world.neoforge.biomemodifiers.AdditionsModifier;
import com.finndog.repurposedstructures.world.neoforge.biomemodifiers.AdditionsTemperatureModifier;
import com.finndog.repurposedstructures.world.neoforge.biomemodifiers.RemovalsModifier;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public final class MVSBiomeModifiers {
	public static final DeferredRegister<MapCodec<? extends BiomeModifier>> BIOME_MODIFIER_SERIALIZERS = DeferredRegister.create(NeoForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, RepurposedStructures.MODID);

	public static final DeferredHolder<MapCodec<? extends BiomeModifier>, MapCodec<AdditionsModifier>> ADDITIONS_MODIFIER = BIOME_MODIFIER_SERIALIZERS.register("additions_modifier", () -> AdditionsModifier.CODEC);
	public static final DeferredHolder<MapCodec<? extends BiomeModifier>, MapCodec<AdditionsTemperatureModifier>> ADDITIONS_TEMPERATURE_MODIFIER = BIOME_MODIFIER_SERIALIZERS.register("additions_temperature_modifier", () -> AdditionsTemperatureModifier.CODEC);
	public static final DeferredHolder<MapCodec<? extends BiomeModifier>, MapCodec<RemovalsModifier>> REMOVALS_MODIFIER = BIOME_MODIFIER_SERIALIZERS.register("removals_modifier", () -> RemovalsModifier.CODEC);
}
