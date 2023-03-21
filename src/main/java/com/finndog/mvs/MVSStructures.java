package com.finndog.mvs;

import com.finndog.mvs.world.structures.MVSGenericJigsawStructure;
import com.finndog.mvs.world.structures.MVSGenericNetherJigsawStructure;
import com.finndog.mvs.world.structures.Jigsaw;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.structure.StructureType;

public class MVSStructures {

    public static StructureType<Jigsaw> JIGSAW;
    public static StructureType<MVSGenericJigsawStructure> MVS_GENERIC_JIGSAW_STRUCTURE;
    public static StructureType<MVSGenericNetherJigsawStructure> MVS_GENERIC_NETHER_JIGSAW_STRUCTURE;


    /**
     * Registers the structure itself and sets what its path is. In this case, the
     * structure will have the Identifier of structure_tutorial:sky_structures.
     *
     * It is always a good idea to register your Structures so that other mods and datapacks can
     * use them too directly from the registries. It's great for mod/datapacks compatibility.
     */
    public static void registerStructureFeatures() {
        MVS_GENERIC_JIGSAW_STRUCTURE = Registry.register(Registries.STRUCTURE_TYPE, new Identifier(MVSMain.MODID, "mvs_generic_jigsaw_structure"), () -> MVSGenericJigsawStructure.CODEC);
        MVS_GENERIC_NETHER_JIGSAW_STRUCTURE = Registry.register(Registries.STRUCTURE_TYPE, new Identifier(MVSMain.MODID, "mvs_generic_nether_jigsaw_structure"), () -> MVSGenericNetherJigsawStructure.CODEC);
        JIGSAW = Registry.register(Registries.STRUCTURE_TYPE, new Identifier(MVSMain.MODID, "jigsaw"), () -> Jigsaw.CODEC);

    }
}