/**
 * A huge thanks to TelepathicGrunt for much of the source code, partially from:
 * https://github.com/TelepathicGrunt/RepurposedStructures and
 * https://github.com/TelepathicGrunt/StructureTutorialMod*/
package com.finndog.mvs;

import com.finndog.mvs.world.structures.GenericJigsawStructure;
import com.finndog.mvs.world.structures.GenericNetherJigsawStructure;
import com.mojang.serialization.Codec;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class MVSStructures {
    /**
     * We are using the Deferred Registry system to register our structure as this is the preferred way on Forge.
     * This will handle registering the base structure for us at the correct time so we don't have to handle it ourselves.
     */
    public static final DeferredRegister<StructureType<?>> STRUCTURE_TYPE = DeferredRegister.create(Registries.STRUCTURE_TYPE, MVSMain.MODID);

    public static final RegistryObject<StructureType<GenericJigsawStructure>> MVS_GENERIC_JIGSAW_STRUCTURE = STRUCTURE_TYPE.register("mvs_generic_jigsaw_structure", () -> explicitStructureTypeTyping(GenericJigsawStructure.CODEC));
    public static final RegistryObject<StructureType<GenericNetherJigsawStructure>> MVS_GENERIC_NETHER_JIGSAW_STRUCTURE = STRUCTURE_TYPE.register("mvs_generic_nether_jigsaw_structure", () -> explicitStructureTypeTyping(GenericNetherJigsawStructure.CODEC));
//    public static final RegistryObject<StructureType<Jigsaw>> JIGSAW = STRUCTURE_TYPE.register("jigsaw", () -> explicitStructureTypeTyping(Jigsaw.CODEC));

    /**
     * Originally, I had a double lambda ()->()-> for the RegistryObject line above, but it turns out that
     * some IDEs cannot resolve the typing correctly. This method explicitly states what the return type
     * is so that the IDE can put it into the DeferredRegistry properly.
     */
    private static <T extends Structure> StructureType<T> explicitStructureTypeTyping(Codec<T> structureCodec) {
        return () -> structureCodec;
    }
}
