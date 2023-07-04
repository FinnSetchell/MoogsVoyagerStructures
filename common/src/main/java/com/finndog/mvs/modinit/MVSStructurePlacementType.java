package com.finndog.mvs.modinit;

import com.finndog.mvs.MVSCommon;
import com.finndog.mvs.modinit.registry.RegistryEntry;
import com.finndog.mvs.modinit.registry.ResourcefulRegistries;
import com.finndog.mvs.modinit.registry.ResourcefulRegistry;
import com.finndog.mvs.world.structures.placements.AdvancedRandomSpread;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacementType;


public final class MVSStructurePlacementType {
    public static final ResourcefulRegistry<StructurePlacementType<?>> STRUCTURE_PLACEMENT_TYPE = ResourcefulRegistries.create(BuiltInRegistries.STRUCTURE_PLACEMENT, MVSCommon.MODID);

    public static final RegistryEntry<StructurePlacementType<AdvancedRandomSpread>> ADVANCED_RANDOM_SPREAD = STRUCTURE_PLACEMENT_TYPE.register("advanced_random_spread", () -> () -> AdvancedRandomSpread.CODEC);
}
