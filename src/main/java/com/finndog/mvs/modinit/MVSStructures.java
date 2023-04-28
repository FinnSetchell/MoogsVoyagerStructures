package com.finndog.mvs.modinit;

import com.finndog.mvs.MVSMain;
import com.finndog.mvs.world.structures.GenericJigsawStructure;
import com.finndog.mvs.world.structures.GenericNetherJigsawStructure;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;


public final class MVSStructures {
    public static final DeferredRegister<StructureType<?>> STRUCTURE_TYPE = DeferredRegister.create(Registry.STRUCTURE_TYPE_REGISTRY, MVSMain.MODID);

    public static RegistryObject<StructureType<GenericJigsawStructure>> GENERIC_JIGSAW_STRUCTURE = STRUCTURE_TYPE.register("mvs_generic_jigsaw_structure", () -> () -> GenericJigsawStructure.CODEC);
    public static RegistryObject<StructureType<GenericNetherJigsawStructure>> GENERIC_NETHER_JIGSAW_STRUCTURE = STRUCTURE_TYPE.register("mvs_nether_jigsaw_structure", () -> () -> GenericNetherJigsawStructure.CODEC);
}
