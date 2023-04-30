package com.finndog.mvs.modinit;

import com.finndog.mvs.MVSMain;
import com.finndog.mvs.world.structures.placements.AdvancedRandomSpread;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacementType;


public final class MVSStructurePlacementType {
    private MVSStructurePlacementType() {}

    public static final StructurePlacementType<AdvancedRandomSpread> ADVANCED_RANDOM_SPREAD = () -> AdvancedRandomSpread.CODEC;

    public static void registerStructurePlacementTypes() {
        Registry.register(Registry.STRUCTURE_PLACEMENT_TYPE, new ResourceLocation(MVSMain.MODID, "advanced_random_spread"), ADVANCED_RANDOM_SPREAD);
    }
}
