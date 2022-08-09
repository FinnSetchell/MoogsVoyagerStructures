package com.finndog.mvs;

import com.finndog.mvs.mixin.StructureFeatureAccessor;
import com.finndog.mvs.structures.Floatingislands;
import com.finndog.mvs.structures.Floatingislands;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
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
    public static StructureFeature<?> FLOATING_ISLANDS = new Floatingislands();

    public static void registerStructureFeatures() {
        // The generation step for when to generate the structure. there are 10 stages you can pick from!
        // This surface structure stage places the structure before plants and ores are generated.
        StructureFeatureAccessor.callRegister(MVSMain.MODID + ":floatingislands", FLOATING_ISLANDS, GenerationStep.Feature.SURFACE_STRUCTURES);
    }
}
