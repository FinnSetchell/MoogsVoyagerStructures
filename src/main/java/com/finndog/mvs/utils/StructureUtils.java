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

    public static boolean isAllowedTerrainHeightChange(Structure.Context context, int size, int allowedTerrainHeightRange) {
        ChunkPos pos = context.chunkPos();
        int[] cornerHeights = getCornerHeights(context, pos.getCenterX(), size, pos.getCenterZ(), size);

        int minHeight = Math.min(Math.min(cornerHeights[0], cornerHeights[1]), Math.min(cornerHeights[2], cornerHeights[3]));
        int maxHeight = Math.max(Math.max(cornerHeights[0], cornerHeights[1]), Math.max(cornerHeights[2], cornerHeights[3]));

        return Math.abs(maxHeight - minHeight) <= allowedTerrainHeightRange;
    }

    public static int[] getCornerHeights(Structure.Context context, int middleBlockX, int xSize, int middleBlockZ, int zSize) {
        ChunkGenerator generator = context.chunkGenerator();
        HeightLimitView heightAccessor = context.world();
        NoiseConfig randomState = context.noiseConfig();

        return new int[]{
                generator.getHeightInGround(
                        middleBlockX,
                        middleBlockZ,
                        Heightmap.Type.WORLD_SURFACE_WG,
                        heightAccessor,
                        randomState),
                generator.getHeightInGround(
                        middleBlockX,
                        middleBlockZ + zSize,
                        Heightmap.Type.WORLD_SURFACE_WG,
                        heightAccessor,
                        randomState),
                generator.getHeightInGround(
                        middleBlockX + xSize,
                        middleBlockZ,
                        Heightmap.Type.WORLD_SURFACE_WG,
                        heightAccessor,
                        randomState),
                generator.getHeightInGround(
                        middleBlockX + xSize,
                        middleBlockZ + zSize,
                        Heightmap.Type.WORLD_SURFACE_WG,
                        heightAccessor,
                        randomState)};
    }
    public static boolean onLiquid(Structure.Context context, boolean spawnInLiquid) {
        ChunkPos chunkPos = context.chunkPos();
        BlockPos centerOfChunk = chunkPos.getCenterAtY(0);
        int landHeight = context.chunkGenerator().getHeightInGround(centerOfChunk.getX(), centerOfChunk.getZ(), Heightmap.Type.WORLD_SURFACE_WG, context.world(), context.noiseConfig());


        VerticalBlockSample column = context.chunkGenerator().getColumnSample(chunkPos.x, chunkPos.z, context.world(), context.noiseConfig());
        BlockState topBlock = column.getState(centerOfChunk.getY() + landHeight);

        if (spawnInLiquid) {
            if(!topBlock.getFluidState().isEmpty()) {
                return true;
            } else {
                return false;
            }
        } //else
        if(topBlock.getFluidState().isEmpty()) {
            return true;
        }
        return false;
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

}
