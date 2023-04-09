/**
 * A huge thanks to TelepathicGrunt for much of the source code, partially from:
 * https://github.com/TelepathicGrunt/RepurposedStructures and
 * https://github.com/TelepathicGrunt/StructureTutorialMod*/
package com.finndog.mvs;

import com.finndog.mvs.world.structures.*;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MVSStructures {

    /**
     * We are using the Deferred Registry system to register our structure as this is the preferred way on Forge.
     * This will handle registering the base structure for us at the correct time so we don't have to handle it ourselves.
     *
     * HOWEVER, do note that Deferred Registries only work for anything that is a Forge Registry. This means that
     * configured structures and configured features need to be registered directly to BuiltinRegistries as there
     * is no Deferred Registry system for them.
     */
    public static final DeferredRegister<StructureFeature<?>> DEFERRED_REGISTRY_STRUCTURE = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, MVSMain.MODID);



    /**
     * Registers the base structure itself and sets what its path is. In this case,
     * this base structure will have the resourcelocation of structure_tutorial:sky_structures.
     */
    public static final RegistryObject<StructureFeature<?>> MVS_GENERIC_JIGSAW_STRUCTURE = DEFERRED_REGISTRY_STRUCTURE.register("mvs_generic_jigsaw_structure", Jigsaw::new);
    public static final RegistryObject<StructureFeature<?>> MVS_GENERIC_NETHER_JIGSAW_STRUCTURE = DEFERRED_REGISTRY_STRUCTURE.register("mvs_generic_nether_jigsaw_structure", MVSGenericNetherJigsawStructure::new);
    public static final RegistryObject<StructureFeature<?>> JIGSAW = DEFERRED_REGISTRY_STRUCTURE.register("jigsaw", Jigsaw::new);


}
