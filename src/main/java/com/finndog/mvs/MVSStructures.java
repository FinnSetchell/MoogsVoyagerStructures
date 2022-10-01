/**
 * A huge thanks to TelepathicGrunt for much of the source code, partially from:
 * https://github.com/TelepathicGrunt/RepurposedStructures and
 * https://github.com/TelepathicGrunt/StructureTutorialMod*/
package com.finndog.mvs;

import com.finndog.mvs.world.structures.GenericJigsawStructure;
import com.finndog.mvs.world.structures.SkyStructures;
import com.finndog.mvs.world.structures.YLevelDownOne;
import com.finndog.mvs.world.structures.configs.MVSGenericConfig;
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
    public static final DeferredRegister<StructureFeature<?>> DEFERRED_REGISTRY_STRUCTURE = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, com.finndog.mvs.MVSMain.MODID);
    //public static final DeferredRegister<StructureFeature<?>> STRUCTURE_FEATURES = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, com.finndog.mvs.MVSMain.MODID);


    /**
     * Registers the base structure itself and sets what its path is. In this case,
     * this base structure will have the resourcelocation of structure_tutorial:sky_structures.
     */
    public static final RegistryObject<StructureFeature<?>> SKY_STRUCTURES = DEFERRED_REGISTRY_STRUCTURE.register("sky_structures", SkyStructures::new);
    public static final RegistryObject<StructureFeature<?>> YLEVEL_DOWN_ONE = DEFERRED_REGISTRY_STRUCTURE.register("ylevel_down_one", YLevelDownOne::new);
    //public static RegistryObject<StructureFeature<?>> GENERIC_JIGSAW_STRUCTURE = STRUCTURE_FEATURES.register("generic_jigsaw_structure", () -> new GenericJigsawStructure(MVSGenericConfig.CODEC));
    public static RegistryObject<StructureFeature<?>> GENERIC_JIGSAW_STRUCTURE = DEFERRED_REGISTRY_STRUCTURE.register("generic_jigsaw_structure", () -> new GenericJigsawStructure(MVSGenericConfig.CODEC));



}
