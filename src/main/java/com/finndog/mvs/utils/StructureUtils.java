package com.finndog.mvs.utils;

import com.sun.jna.Structure;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.structure.BuiltinStructureSets;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;

import java.util.*;

public class StructureUtils {

    public static boolean isAllowedTerrainHeightChange(PieceGeneratorSupplier.Context<JigsawConfiguration> context, int size, int allowedTerrainHeightRange) {
        ChunkPos pos = context.chunkPos();
        int prevHeight = getHeight(context, pos.getMinBlockX(), pos.getMinBlockZ());
        int spacing = Math.max(2, size / 10);

        for (int x = 1; x < size; x+=spacing) {
            for (int z = 1; z < size; z+=spacing) {
                int height = getHeight(context, pos.getMinBlockX() + x, pos.getMinBlockZ() + z);
                if (Math.abs(height - prevHeight) > allowedTerrainHeightRange) {
                    return false;
                }
                prevHeight = height;
            }
        }
        return true;
    }

    public static int getHeight(PieceGeneratorSupplier.Context<JigsawConfiguration> context, int x, int z) {
        ChunkGenerator generator = context.chunkGenerator();
        LevelHeightAccessor heightAccessor = context.heightAccessor();

        return generator.getFirstOccupiedHeight(x, z, Heightmap.Types.WORLD_SURFACE_WG, heightAccessor);
    }

    // this should check every other top block to see if it contains fluid
    public static boolean onLiquid(PieceGeneratorSupplier.Context<JigsawConfiguration> context, int size) {
        int spacing = Math.max(2, size / 10);

        for (int x = 1; x < size; x+=spacing) {
            for (int z = 1; z < size; z+=spacing) {
                if (isTopBlockWater(context)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isTopBlockWater(PieceGeneratorSupplier.Context<JigsawConfiguration> context) {
        ChunkPos chunkPos = context.chunkPos();
        BlockPos centerOfChunk = chunkPos.getMiddleBlockPosition(0);
        int landHeight = context.chunkGenerator().getFirstOccupiedHeight(centerOfChunk.getX(), centerOfChunk.getZ(), Heightmap.Types.WORLD_SURFACE_WG, context.heightAccessor());
        NoiseColumn columnOfBlocks = context.chunkGenerator().getBaseColumn(centerOfChunk.getX(), centerOfChunk.getZ(), context.heightAccessor());
        BlockState topBlock = columnOfBlocks.getBlock(centerOfChunk.getY() + landHeight);
        if(!topBlock.getFluidState().isEmpty()) {
            return true;
        }
        return false;
    }

    public static Optional<Integer> getSuitableNetherYLevel(PieceGeneratorSupplier.Context<JigsawConfiguration> context, BlockPos pos) {
        NoiseColumn column = context.chunkGenerator().getBaseColumn(pos.getX(), pos.getZ(), context.heightAccessor());
        List<Integer> suitableYLevels = new ArrayList<>();

        for (int y = 127; y > context.chunkGenerator().getSeaLevel(); y--) {
            if (column.getBlock(y - 1).canOcclude() && column.getBlock(y).isAir() && column.getBlock(y + 4).isAir()) {
                suitableYLevels.add(y);
            }
        }

        if (suitableYLevels.isEmpty())
            return Optional.empty();

        return Optional.of(suitableYLevels.get(new Random(context.seed()).nextInt(suitableYLevels.size())));
    }

    public static boolean isFeatureChunk(PieceGeneratorSupplier.Context<JigsawConfiguration> context) {
        ChunkPos chunkpos = context.chunkPos();

        List<ResourceKey> structureSets = new ArrayList<>();

        structureSets.addAll(Arrays.asList(BuiltinStructureSets.VILLAGES, BuiltinStructureSets.PILLAGER_OUTPOSTS, BuiltinStructureSets.OCEAN_MONUMENTS, BuiltinStructureSets.DESERT_PYRAMIDS,
                BuiltinStructureSets.END_CITIES, BuiltinStructureSets.IGLOOS, BuiltinStructureSets.JUNGLE_TEMPLES, BuiltinStructureSets.RUINED_PORTALS, BuiltinStructureSets.SHIPWRECKS,
                BuiltinStructureSets.SWAMP_HUTS, BuiltinStructureSets.WOODLAND_MANSIONS, BuiltinStructureSets.OCEAN_RUINS));

        for (ResourceKey structureSet : structureSets) {
            if (context.chunkGenerator().hasFeatureChunkInRange(structureSet, context.seed(), chunkpos.x, chunkpos.z, 10)) {
                return true;
            }
        }
        return false;
    }
}
