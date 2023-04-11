package com.finndog.mvs.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.structure.BuiltinStructureSets;
import net.minecraft.world.level.levelgen.structure.Structure;

import java.util.*;

public class StructureUtils {

    public static boolean spawningChecks(Structure.GenerationContext context, Optional<Heightmap.Types> projectStartToHeightmap, int radius, int allowedTerrainHeightRange, boolean spawnInLiquid) {
        ChunkPos chunkPos = context.chunkPos();


        // ON LAND OR FLUID CHECK
        BlockPos centerOfChunk = chunkPos.getMiddleBlockPosition(0);
        int landHeight = context.chunkGenerator().getFirstOccupiedHeight(centerOfChunk.getX(), centerOfChunk.getZ(), Heightmap.Types.WORLD_SURFACE_WG, context.heightAccessor(), context.randomState());
        NoiseColumn columnOfBlocks = context.chunkGenerator().getBaseColumn(centerOfChunk.getX(), centerOfChunk.getZ(), context.heightAccessor(), context.randomState());
        BlockState topBlock = columnOfBlocks.getBlock(centerOfChunk.getY() + landHeight);

        if (spawnInLiquid) {
            if (topBlock.getFluidState().isEmpty()) {
                return false;
            }
        } else {
            if (!topBlock.getFluidState().isEmpty()) {
                return false;
            }
        }


        // TERRAIN HEIGHT CHECK
        int maxTerrainHeight = Integer.MIN_VALUE;
        int minTerrainHeight = Integer.MAX_VALUE;
        int terrainCheckRange = radius*2;

        for (int curChunkX = chunkPos.x - terrainCheckRange; curChunkX <= chunkPos.x + terrainCheckRange; curChunkX++) {
            for (int curChunkZ = chunkPos.z - terrainCheckRange; curChunkZ <= chunkPos.z + terrainCheckRange; curChunkZ++) {
                int height = context.chunkGenerator().getBaseHeight((curChunkX << 4) + 7, (curChunkZ << 4) + 7, projectStartToHeightmap.orElse(Heightmap.Types.WORLD_SURFACE_WG), context.heightAccessor(), context.randomState());
                maxTerrainHeight = Math.max(maxTerrainHeight, height);
                minTerrainHeight = Math.min(minTerrainHeight, height);

            }
        }

        if(maxTerrainHeight - minTerrainHeight > allowedTerrainHeightRange) {
            return false;
        }

        return true;
    }

    // replaced with above code
    public static boolean isAllowedTerrainHeightChange(Structure.GenerationContext context, int size, int allowedTerrainHeightRange) {
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
    public static int getHeight(Structure.GenerationContext context, int x, int z) {
        ChunkGenerator generator = context.chunkGenerator();
        LevelHeightAccessor heightAccessor = context.heightAccessor();
        RandomState randomState = context.randomState();

        return generator.getFirstOccupiedHeight(x, z, Heightmap.Types.WORLD_SURFACE_WG, heightAccessor, randomState);
    }
    // this should check every other top block to see if it contains fluid
    public static boolean onLiquid(Structure.GenerationContext context, int size) {
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
    public static boolean isTopBlockWater(Structure.GenerationContext context) {
        ChunkPos chunkPos = context.chunkPos();
        BlockPos centerOfChunk = chunkPos.getMiddleBlockPosition(0);
        int landHeight = context.chunkGenerator().getFirstOccupiedHeight(centerOfChunk.getX(), centerOfChunk.getZ(), Heightmap.Types.WORLD_SURFACE_WG, context.heightAccessor(), context.randomState());
        NoiseColumn columnOfBlocks = context.chunkGenerator().getBaseColumn(centerOfChunk.getX(), centerOfChunk.getZ(), context.heightAccessor(), context.randomState());
        BlockState topBlock = columnOfBlocks.getBlock(centerOfChunk.getY() + landHeight);
        if(!topBlock.getFluidState().isEmpty()) {
            return true;
        }
        return false;
    }


    public static Optional<Integer> getSuitableNetherYLevel(Structure.GenerationContext context, BlockPos pos) {
        NoiseColumn column = context.chunkGenerator().getBaseColumn(pos.getX(), pos.getZ(), context.heightAccessor(), context.randomState());
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
}
