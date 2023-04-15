package com.finndog.mvs.modinit;

import com.finndog.mvs.MVSMain;
import com.finndog.mvs.world.structures.placements.AdvancedRandomSpread;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.chunk.placement.StructurePlacementType;

public final class MVSStructurePlacementType {
    private MVSStructurePlacementType() {}

    public static final StructurePlacementType<AdvancedRandomSpread> ADVANCED_RANDOM_SPREAD = () -> AdvancedRandomSpread.CODEC;

    public static void registerStructurePlacementTypes() {
        Registry.register(Registries.STRUCTURE_PLACEMENT, new Identifier(MVSMain.MODID, "advanced_random_spread"), ADVANCED_RANDOM_SPREAD);
    }
}

