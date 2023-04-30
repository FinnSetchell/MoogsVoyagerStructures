package com.finndog.mvs;

import com.finndog.mvs.MVSMain;
import com.finndog.mvs.modinit.registry.RegistryEntry;
import com.finndog.mvs.modinit.registry.ResourcefulRegistries;
import com.finndog.mvs.modinit.registry.ResourcefulRegistry;
import com.finndog.mvs.world.structures.MVSGenericJigsawStructure;
import com.finndog.mvs.world.structures.MVSGenericNetherJigsawStructure;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.structure.StructureType;


public final class MVSStructures {
    public static final ResourcefulRegistry<StructureType<?>> STRUCTURE_TYPE = ResourcefulRegistries.create(BuiltInRegistries.STRUCTURE_TYPE, MVSMain.MODID);

    public static RegistryEntry<StructureType<MVSGenericJigsawStructure>> MVS_GENERIC_JIGSAW_STRUCTURE = STRUCTURE_TYPE.register("mvs_generic_jigsaw_structure", () -> () -> MVSGenericJigsawStructure.CODEC);
    public static RegistryEntry<StructureType<MVSGenericNetherJigsawStructure>> MVS_GENERIC_NETHER_JIGSAW_STRUCTURE = STRUCTURE_TYPE.register("mvs_generic_nether_jigsaw_structure", () -> () -> MVSGenericNetherJigsawStructure.CODEC);
}