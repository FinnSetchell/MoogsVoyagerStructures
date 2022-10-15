package com.finndog.mvs.utils;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class StructureUtils {

    public static boolean isAllowedTerrainHeightChange(ChunkGenerator chunkGenerator, int size, int allowedTerrainHeightRange, BlockPos pos) {
        int[] cornerHeights = getCornerHeights(chunkGenerator, pos.getX(), size, pos.getZ(), size);

        int minHeight = Math.min(Math.min(cornerHeights[0], cornerHeights[1]), Math.min(cornerHeights[2], cornerHeights[3]));
        int maxHeight = Math.max(Math.max(cornerHeights[0], cornerHeights[1]), Math.max(cornerHeights[2], cornerHeights[3]));

        return Math.abs(maxHeight - minHeight) <= allowedTerrainHeightRange;
    }

    public static int[] getCornerHeights(ChunkGenerator chunkGenerator, int middleBlockX, int xSize, int middleBlockZ, int zSize) {

        return new int[]{
                chunkGenerator.getFirstOccupiedHeight(
                        middleBlockX,
                        middleBlockZ,
                        Heightmap.Type.WORLD_SURFACE_WG),
                chunkGenerator.getFirstOccupiedHeight(
                        middleBlockX,
                        middleBlockZ + zSize,
                        Heightmap.Type.WORLD_SURFACE_WG),
                chunkGenerator.getFirstOccupiedHeight(
                        middleBlockX + xSize,
                        middleBlockZ,
                        Heightmap.Type.WORLD_SURFACE_WG),
                chunkGenerator.getFirstOccupiedHeight(
                        middleBlockX + xSize,
                        middleBlockZ + zSize,
                        Heightmap.Type.WORLD_SURFACE_WG)};
    }
    public static boolean onLiquid(BlockState topBlock, IBlockReader columnOfBlocks) {
        return topBlock.getFluidState().isEmpty();
    }

//    public static boolean isStructureInDistance(Structure.GenerationContext context, List<Holder<StructureSet>> structures, int minStructureDistance) {
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

    public static Optional<Integer> getSuitableNetherYLevel(ChunkGenerator chunkGenerator, BlockPos pos, IBlockReader column, long seed) {
        List<Integer> suitableYLevels = new ArrayList<>();

        for (int y = 127; y > chunkGenerator.getSeaLevel(); y--) {
            BlockPos block = new BlockPos(pos.getX(), y-1, pos.getZ());
            BlockPos air = new BlockPos(pos.getX(), y, pos.getZ());
            BlockPos topAir = new BlockPos(pos.getX(), y+4, pos.getZ());

            if (column.getBlockState(block).canOcclude() && column.getBlockState(air).isAir() && column.getBlockState(topAir).isAir()) {
                suitableYLevels.add(y);
            }
        }

        if (suitableYLevels.isEmpty())
            return Optional.empty();

        return Optional.of(suitableYLevels.get(new Random(seed).nextInt(suitableYLevels.size())));
    }
}
