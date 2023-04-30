package com.finndog.mvs;

import com.finndog.mvs.modinit.MVSStructurePieces;
import com.finndog.mvs.world.structures.MVSGenericJigsawStructure;
import com.finndog.mvs.world.structures.MVSMVSGenericNetherJigsawStructure;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.StructureType;

public class MVSStructures {
    private MVSStructures() {}

    public static StructureType<MVSGenericJigsawStructure> GENERIC_JIGSAW_STRUCTURE = () -> MVSGenericJigsawStructure.CODEC;
    public static StructureType<MVSMVSGenericNetherJigsawStructure> GENERIC_NETHER_JIGSAW_STRUCTURE = () -> MVSMVSGenericNetherJigsawStructure.CODEC;


    public static void registerStructures() {
        Registry.register(Registry.STRUCTURE_TYPES, new ResourceLocation(MVSMain.MODID, "mvs_generic_jigsaw_structure"), GENERIC_JIGSAW_STRUCTURE);
        Registry.register(Registry.STRUCTURE_TYPES, new ResourceLocation(MVSMain.MODID, "mvs_generic_nether_jigsaw_structure"), GENERIC_NETHER_JIGSAW_STRUCTURE);

        //registers the structure pieces.
        MVSStructurePieces.registerStructurePieces();
    }
}