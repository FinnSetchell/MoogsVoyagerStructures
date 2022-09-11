package com.finndog.mvs;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.BuiltinStructureSets;
import net.minecraft.world.level.levelgen.structure.StructureSet;

public class StructureSetToAvoid implements BuiltinStructureSets {

    public interface BuiltinStructureSets {
        ResourceKey<StructureSet> MVSSTRUCTURES = register("mvsstructures");
    }

    private static ResourceKey<StructureSet> register(String p_209839_) {
        return ResourceKey.create(Registry.STRUCTURE_SET_REGISTRY, new ResourceLocation(p_209839_));
    }
}
