package com.finndog.mvs.utils;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.HeightLimitView;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.VerticalBlockSample;
import net.minecraft.world.gen.noise.NoiseConfig;
import net.minecraft.world.gen.structure.Structure;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class StructureUtils {

    public static boolean spawningChecks(Structure.Context context, Optional<Heightmap.Type> projectStartToHeightmap, int radius, int allowedTerrainHeightRange, boolean spawnInLiquid) {
        ChunkPos chunkPos = context.chunkPos();

        // ON LAND OR FLUID CHECK
        BlockPos centerOfChunk = chunkPos.getCenterAtY(0);
        int landHeight = context.chunkGenerator().getHeightInGround(centerOfChunk.getX(), centerOfChunk.getZ(), Heightmap.Type.WORLD_SURFACE_WG, context.world(), context.noiseConfig());
        VerticalBlockSample columnOfBlocks = context.chunkGenerator().getColumnSample(centerOfChunk.getX(), centerOfChunk.getZ(), context.world(), context.noiseConfig());
        BlockState topBlock = columnOfBlocks.getState(centerOfChunk.getY() + landHeight);


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
                int height = context.chunkGenerator().getHeightInGround((curChunkX << 4) + 7, (curChunkZ << 4) + 7, projectStartToHeightmap.orElse(Heightmap.Type.WORLD_SURFACE_WG), context.world(), context.noiseConfig());
                maxTerrainHeight = Math.max(maxTerrainHeight, height);
                minTerrainHeight = Math.min(minTerrainHeight, height);
            }
        }

        if(maxTerrainHeight - minTerrainHeight > allowedTerrainHeightRange) {
            return false;
        }
        return true;
    }



    public static boolean isAllowedTerrainHeightChange(Structure.Context context, int size, int allowedTerrainHeightRange) {
        ChunkPos pos = context.chunkPos();
        int prevHeight = getHeight(context, pos.getCenterX(), pos.getCenterZ());
        int spacing = Math.max(2, size / 10);

        for (int x = 1; x < size; x+=spacing) {
            for (int z = 1; z < size; z+=spacing) {
                int height = getHeight(context, pos.getCenterX() + x, pos.getCenterZ() + z);
                if (Math.abs(height - prevHeight) > allowedTerrainHeightRange) {
                    return false;
                }
                prevHeight = height;
            }
        }
        return true;
    }
    public static int getHeight(Structure.Context context, int x, int z) {
        ChunkGenerator generator = context.chunkGenerator();
        HeightLimitView heightAccessor = context.world();
        NoiseConfig randomState = context.noiseConfig();

        return generator.getHeightInGround(x, z, Heightmap.Type.WORLD_SURFACE_WG, heightAccessor, randomState);
    }
    // this should check every other top block to see if it contains fluid
    public static boolean onLiquid(Structure.Context context, int size) {
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

    public static boolean isTopBlockWater(Structure.Context context) {
        ChunkPos chunkPos = context.chunkPos();
        BlockPos centerOfChunk = chunkPos.getCenterAtY(0);
        int landHeight = context.chunkGenerator().getHeightInGround(centerOfChunk.getX(), centerOfChunk.getZ(), Heightmap.Type.WORLD_SURFACE_WG, context.world(), context.noiseConfig());
        VerticalBlockSample columnOfBlocks = context.chunkGenerator().getColumnSample(chunkPos.x, chunkPos.z, context.world(), context.noiseConfig());
        BlockState topBlock = columnOfBlocks.getState(centerOfChunk.getY() + landHeight);
        if(!topBlock.getFluidState().isEmpty()) {
            return true;
        }
        return false;
    }

    public static Optional<Integer> getSuitableNetherYLevel(Structure.Context context, BlockPos pos) {

        VerticalBlockSample column = context.chunkGenerator().getColumnSample(pos.getX(), pos.getZ(), context.world(), context.noiseConfig());
//        NoiseColumn column = context.chunkGenerator().getBaseColumn(pos.getX(), pos.getZ(), context.heightAccessor(), context.randomState());
        List<Integer> suitableYLevels = new ArrayList<>();

        for (int y = 127; y > context.chunkGenerator().getSeaLevel(); y--) {
            if ((!column.getState(y-1).isAir()) && column.getState(y).isAir() && column.getState(y + 4).isAir()) {
                suitableYLevels.add(y);
            }
        }

//        for (int y = 127; y > context.chunkGenerator().getSeaLevel(); y--) {
//            if (column.getBlock(y - 1).canOcclude() && column.getBlock(y).isAir() && column.getBlock(y + 4).isAir()) {
//                suitableYLevels.add(y);
//            }
//        }

        if (suitableYLevels.isEmpty())
            return Optional.empty();

        return Optional.of(suitableYLevels.get(new Random(context.seed()).nextInt(suitableYLevels.size())));
    }

    //    public static boolean isStructureInDistance(Structure.Context context, List<Holder<StructureSet>> structures, int minStructureDistance) {
//        ChunkGenerator generator = context.chunkGenerator();
//        RandomState randomState = context.randomState();
//        long seed = context.seed();
//        ChunkPos chunkPos = context.chunkPos();
//
//        if (minStructureDistance == 0) {
//            return false;
//        }
//
//        for (Holder<StructureSet> structure : structures) {
//            if (generator.hasStructureChunkInRange(structure, randomState, seed, chunkPos.x, chunkPos.z, minStructureDistance)) {
//                return true;
//            }
//        }
//
//        return false;
//    }
}
