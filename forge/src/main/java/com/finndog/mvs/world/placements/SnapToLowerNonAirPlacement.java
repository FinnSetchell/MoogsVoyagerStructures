package com.finndog.mvs.world.placements;

import com.finndog.mvs.modinit.MVSPlacements;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.placement.PlacementContext;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;

import java.util.stream.Stream;

public class SnapToLowerNonAirPlacement extends PlacementModifier {
	private static final SnapToLowerNonAirPlacement INSTANCE = new SnapToLowerNonAirPlacement();
	public static final Codec<SnapToLowerNonAirPlacement> CODEC = Codec.unit(() -> INSTANCE);

	public static SnapToLowerNonAirPlacement snapToLowerNonAir() {
		return INSTANCE;
	}

	@Override
	public final Stream<BlockPos> getPositions(PlacementContext placementContext, RandomSource random, BlockPos blockPos) {
		BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos().set(blockPos);
		while(placementContext.getBlockState(mutable).isAir() && mutable.getY() > placementContext.getMinGenY()) {
			mutable.move(Direction.DOWN);
		}
		return Stream.of(mutable.immutable());
	}

	@Override
	public PlacementModifierType<?> type() {
		return MVSPlacements.SNAP_TO_LOWER_NON_AIR_PLACEMENT.get();
	}
}