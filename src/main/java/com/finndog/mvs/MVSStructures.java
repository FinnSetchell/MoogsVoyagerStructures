package com.finndog.mvs;

import com.finndog.mvs.mixin.StructureFeatureAccessor;
import com.finndog.mvs.structures.FloatingIslands;
import com.finndog.mvs.world.structures.*;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.StructureFeature;

public class MVSStructures {

    /**
     /**
     * Registers the structure itself and sets what its path is. In this case, the
     * structure will have the Identifier of structure_tutorial:sky_structures.
     *
     * It is always a good idea to register your Structures so that other mods and datapacks can
     * use them too directly from the registries. It great for mod/datapacks compatibility.
     */
    public static StructureFeature<?> FLOATING_ISLANDS = new FloatingIslands();
    public static StructureFeature<?> MVS_GENERIC_JIGSAW_STRUCTURE = new MVSGenericJigsawStructure();
    public static StructureFeature<?> MVS_GENERIC_NETHER_JIGSAW_STRUCTURE = new MVSGenericNetherJigsawStructure();
    public static StructureFeature<?> MVS_OCEAN_JIGSAW_STRUCTURE = new MVSOceanJigsawStructure();
    public static StructureFeature<?> MVS_SMALL_JIGSAW_STRUCTURE = new MVSSmallJigsawStructure();
    public static StructureFeature<?> MVS_SKY_STRUCTURE = new MVSSkyStructure();
    public static StructureFeature<?> MVS_INSET_STRUCTURE = new MVSInsetStructure();




    public static void registerStructureFeatures() {
        // The generation step for when to generate the structure. there are 10 stages you can pick from!
        // This surface structure stage places the structure before plants and ores are generated.
        StructureFeatureAccessor.callRegister(MVSMain.MODID + ":floatingislands", FLOATING_ISLANDS, GenerationStep.Feature.SURFACE_STRUCTURES);
        StructureFeatureAccessor.callRegister(MVSMain.MODID + ":mvs_generic_jigsaw_structure", MVS_GENERIC_JIGSAW_STRUCTURE, GenerationStep.Feature.SURFACE_STRUCTURES);
        StructureFeatureAccessor.callRegister(MVSMain.MODID + ":mvs_generic_nether_jigsaw_structure", MVS_GENERIC_NETHER_JIGSAW_STRUCTURE,  GenerationStep.Feature.SURFACE_STRUCTURES);
        StructureFeatureAccessor.callRegister(MVSMain.MODID + ":mvs_ocean_structure", MVS_OCEAN_JIGSAW_STRUCTURE, GenerationStep.Feature.SURFACE_STRUCTURES);
        StructureFeatureAccessor.callRegister(MVSMain.MODID + ":mvs_small_jigsaw_structure", MVS_SMALL_JIGSAW_STRUCTURE, GenerationStep.Feature.SURFACE_STRUCTURES);
        StructureFeatureAccessor.callRegister(MVSMain.MODID + ":mvs_sky_structure", MVS_SKY_STRUCTURE, GenerationStep.Feature.SURFACE_STRUCTURES);
        StructureFeatureAccessor.callRegister(MVSMain.MODID + ":mvs_inset_structure", MVS_INSET_STRUCTURE, GenerationStep.Feature.SURFACE_STRUCTURES);


    }
}
