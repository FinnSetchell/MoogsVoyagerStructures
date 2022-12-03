package com.finndog.mvs.utils;

import net.minecraft.block.BlockState;
import net.minecraft.structure.StructureGeneratorFactory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.HeightLimitView;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.VerticalBlockSample;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class StructureUtils {

    public static boolean isAllowedTerrainHeightChange(StructureGeneratorFactory.Context<StructurePoolFeatureConfig> context, int size, int allowedTerrainHeightRange) {
        ChunkPos pos = context.chunkPos();
        int[] cornerHeights = getCornerHeights(context, pos.getCenterX(), size, pos.getCenterZ(), size);

        int minHeight = Math.min(Math.min(cornerHeights[0], cornerHeights[1]), Math.min(cornerHeights[2], cornerHeights[3]));
        int maxHeight = Math.max(Math.max(cornerHeights[0], cornerHeights[1]), Math.max(cornerHeights[2], cornerHeights[3]));

        return Math.abs(maxHeight - minHeight) <= allowedTerrainHeightRange;
    }

    public static int[] getCornerHeights(StructureGeneratorFactory.Context<StructurePoolFeatureConfig> context, int middleBlockX, int xSize, int middleBlockZ, int zSize) {
        ChunkGenerator generator = context.chunkGenerator();
        HeightLimitView heightAccessor = context.world();

        return new int[]{
                generator.getHeightInGround(
                        middleBlockX,
                        middleBlockZ,
                        Heightmap.Type.WORLD_SURFACE_WG,
                        heightAccessor),
                generator.getHeightInGround(
                        middleBlockX,
                        middleBlockZ + zSize,
                        Heightmap.Type.WORLD_SURFACE_WG,
                        heightAccessor),
                generator.getHeightInGround(
                        middleBlockX + xSize,
                        middleBlockZ,
                        Heightmap.Type.WORLD_SURFACE_WG,
                        heightAccessor),
                generator.getHeightInGround(
                        middleBlockX + xSize,
                        middleBlockZ + zSize,
                        Heightmap.Type.WORLD_SURFACE_WG,
                        heightAccessor)};
    }

    public static boolean onLiquid(StructureGeneratorFactory.Context<StructurePoolFeatureConfig> context, boolean spawnInLiquid) {
        ChunkPos chunkPos = context.chunkPos();
        BlockPos centerOfChunk = chunkPos.getCenterAtY(0);
        int landHeight = context.chunkGenerator().getHeightInGround(centerOfChunk.getX(), centerOfChunk.getZ(), Heightmap.Type.WORLD_SURFACE_WG, context.world());

        VerticalBlockSample columnOfBlocks = context.chunkGenerator().getColumnSample(centerOfChunk.getX(), centerOfChunk.getZ(), context.world());
        BlockState topBlock = columnOfBlocks.getState(centerOfChunk.getY() + landHeight);

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
//
//    public static boolean isFeatureChunk(Structure.GenerationContext context, int distance) {
//        ChunkPos chunkpos = context.chunkPos();
//
//        if (context.chunkGenerator().hasStructureChunkInRange(StructureSets.VILLAGES, context.randomState(), context.seed(), chunkpos.x, chunkpos.z, distance)) {return true;}
//        if (context.chunkGenerator().hasStructureChunkInRange(StructureSets.PILLAGER_OUTPOSTS, context.randomState(), context.seed(), chunkpos.x, chunkpos.z, distance)) {return true;}
//        if (context.chunkGenerator().hasStructureChunkInRange(StructureSets.OCEAN_MONUMENTS, context.randomState(), context.seed(), chunkpos.x, chunkpos.z, distance)) {return true;}
//        if (context.chunkGenerator().hasStructureChunkInRange(StructureSets.DESERT_PYRAMIDS, context.randomState(), context.seed(), chunkpos.x, chunkpos.z, distance)) {return true;}
//        if (context.chunkGenerator().hasStructureChunkInRange(StructureSets.END_CITIES, context.randomState(), context.seed(), chunkpos.x, chunkpos.z, distance)) {return true;}
//        if (context.chunkGenerator().hasStructureChunkInRange(StructureSets.IGLOOS, context.randomState(), context.seed(), chunkpos.x, chunkpos.z, distance)) {return true;}
//        if (context.chunkGenerator().hasStructureChunkInRange(StructureSets.JUNGLE_TEMPLES, context.randomState(), context.seed(), chunkpos.x, chunkpos.z, distance)) {return true;}
//        if (context.chunkGenerator().hasStructureChunkInRange(StructureSets.RUINED_PORTALS, context.randomState(), context.seed(), chunkpos.x, chunkpos.z, distance)) {return true;}
//        if (context.chunkGenerator().hasStructureChunkInRange(StructureSets.SHIPWRECKS, context.randomState(), context.seed(), chunkpos.x, chunkpos.z, distance)) {return true;}
//        if (context.chunkGenerator().hasStructureChunkInRange(StructureSets.SWAMP_HUTS, context.randomState(), context.seed(), chunkpos.x, chunkpos.z, distance)) {return true;}
//        if (context.chunkGenerator().hasStructureChunkInRange(StructureSets.WOODLAND_MANSIONS, context.randomState(), context.seed(), chunkpos.x, chunkpos.z, distance)) {return true;}
//        if (context.chunkGenerator().hasStructureChunkInRange(StructureSets.OCEAN_RUINS, context.randomState(), context.seed(), chunkpos.x, chunkpos.z, distance)) {return true;}
//        if (context.chunkGenerator().hasStructureChunkInRange(StructureSets.NETHER_FOSSILS, context.randomState(), context.seed(), chunkpos.x, chunkpos.z, distance)) {return true;}
//        return false;
//    }

    public static Optional<Integer> getSuitableNetherYLevel(StructureGeneratorFactory.Context<StructurePoolFeatureConfig> context, BlockPos pos) {

        VerticalBlockSample column = context.chunkGenerator().getColumnSample(pos.getX(), pos.getZ(), context.world());
//        NoiseColumn column = context.chunkGenerator().getBaseColumn(pos.getX(), pos.getZ(), context.heightAccessor(), context.randomState());
        List<Integer> suitableYLevels = new ArrayList<>();

        for (int y = 127; y > context.chunkGenerator().getSeaLevel(); y--) {
            if ((!column.getState(y-1).isAir()) && column.getState(y).isAir() && column.getState(y + 4).isAir()) {
                suitableYLevels.add(y);
            }
        }

        if (suitableYLevels.isEmpty())
            return Optional.empty();

        return Optional.of(suitableYLevels.get(new Random(context.seed()).nextInt(suitableYLevels.size())));
    }

}