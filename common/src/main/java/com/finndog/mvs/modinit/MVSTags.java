package com.finndog.mvs.modinit;

import com.finndog.mvs.MVSCommon;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.levelgen.structure.Structure;

public final class MVSTags {
    public static void initTags() {}

    public static TagKey<Structure> LARGER_LOCATE_SEARCH = TagKey.create(Registries.STRUCTURE,
            new ResourceLocation(MVSCommon.MODID, "larger_locate_search"));

}
