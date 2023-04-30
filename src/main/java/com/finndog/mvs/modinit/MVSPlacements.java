package com.finndog.mvs.modinit;

import com.finndog.mvs.MVSMain;
import com.finndog.mvs.world.placements.MinusEightPlacement;
import com.finndog.mvs.world.placements.SnapToLowerNonAirPlacement;
import com.finndog.mvs.world.placements.UnlimitedCountPlacement;
import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;

public final class MVSPlacements {
	private MVSPlacements() {}

	public static PlacementModifierType<?> MINUS_EIGHT_PLACEMENT;
	public static PlacementModifierType<?> UNLIMITED_COUNT;
	public static PlacementModifierType<?> SNAP_TO_LOWER_NON_AIR_PLACEMENT;

	public static void registerPlacements() {
		MINUS_EIGHT_PLACEMENT = register(new ResourceLocation(MVSMain.MODID, "minus_eight_placement"), MinusEightPlacement.CODEC);
		UNLIMITED_COUNT = register(new ResourceLocation(MVSMain.MODID, "unlimited_count"), UnlimitedCountPlacement.CODEC);
		SNAP_TO_LOWER_NON_AIR_PLACEMENT = register(new ResourceLocation(MVSMain.MODID, "snap_to_lower_non_air_placement"), SnapToLowerNonAirPlacement.CODEC);
	}

	private static <P extends PlacementModifier> PlacementModifierType<P> register(ResourceLocation resourceLocation, Codec<P> codec) {
		return Registry.register(Registry.PLACEMENT_MODIFIERS, resourceLocation, () -> codec);
	}
}
