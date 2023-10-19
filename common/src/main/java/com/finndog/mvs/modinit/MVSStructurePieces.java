package com.finndog.mvs.modinit;

import com.finndog.mvs.MVSCommon;
import com.finndog.mvs.modinit.registry.RegistryEntry;
import com.finndog.mvs.modinit.registry.ResourcefulRegistries;
import com.finndog.mvs.modinit.registry.ResourcefulRegistry;
import com.finndog.mvs.world.structures.pieces.LegacyOceanBottomSinglePoolElement;
import com.finndog.mvs.world.structures.pieces.MirroringSingleJigsawPiece;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElementType;


public final class MVSStructurePieces {
    public static final ResourcefulRegistry<StructurePoolElementType<?>> STRUCTURE_POOL_ELEMENT = ResourcefulRegistries.create(Registry.STRUCTURE_POOL_ELEMENT, MVSCommon.MODID);
    public static final ResourcefulRegistry<StructurePieceType> STRUCTURE_PIECE = ResourcefulRegistries.create(Registry.STRUCTURE_PIECE, MVSCommon.MODID);

    public static final RegistryEntry<StructurePoolElementType<MirroringSingleJigsawPiece>> MIRROR_SINGLE = STRUCTURE_POOL_ELEMENT.register("mirroring_single_pool_element", () -> () -> MirroringSingleJigsawPiece.CODEC);
    public static final RegistryEntry<StructurePoolElementType<LegacyOceanBottomSinglePoolElement>> LEGACY_OCEAN_BOTTOM = STRUCTURE_POOL_ELEMENT.register("legacy_ocean_bottom_single_pool_element", () -> () -> LegacyOceanBottomSinglePoolElement.CODEC);
}
