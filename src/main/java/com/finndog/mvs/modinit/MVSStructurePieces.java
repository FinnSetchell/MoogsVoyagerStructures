package com.finndog.mvs.modinit;

import com.finndog.mvs.MVSMain;
import com.finndog.mvs.world.structures.pieces.LegacyOceanBottomSinglePoolElement;
import com.finndog.mvs.world.structures.pieces.MirroringSingleJigsawPiece;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElementType;


public final class MVSStructurePieces {
    private MVSStructurePieces() {}

    public static StructurePoolElementType<MirroringSingleJigsawPiece> MIRROR_SINGLE;
    public static StructurePoolElementType<LegacyOceanBottomSinglePoolElement> LEGACY_OCEAN_BOTTOM;

    public static void registerStructurePieces() {
        MIRROR_SINGLE = Registry.register(Registry.STRUCTURE_POOL_ELEMENT, new ResourceLocation(MVSMain.MODID, "mirroring_single_pool_element"), () -> MirroringSingleJigsawPiece.CODEC);
        LEGACY_OCEAN_BOTTOM = Registry.register(Registry.STRUCTURE_POOL_ELEMENT, new ResourceLocation(MVSMain.MODID, "legacy_ocean_bottom_single_pool_element"), () -> LegacyOceanBottomSinglePoolElement.CODEC);
    }
}

