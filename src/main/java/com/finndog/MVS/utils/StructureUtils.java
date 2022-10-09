package com.finndog.mvs.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.StructureSets;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StructureUtils {

    public static boolean isAllowedTerrainHeightChange(Structure.GenerationContext context, int size, int allowedTerrainHeightRange) {
        ChunkPos pos = context.chunkPos();
        int[] cornerHeights = getCornerHeights(context, pos.getMinBlockX(), size, pos.getMinBlockZ(), size);

        int minHeight = Math.min(Math.min(cornerHeights[0], cornerHeights[1]), Math.min(cornerHeights[2], cornerHeights[3]));
        int maxHeight = Math.max(Math.max(cornerHeights[0], cornerHeights[1]), Math.max(cornerHeights[2], cornerHeights[3]));

        return Math.abs(maxHeight - minHeight) <= allowedTerrainHeightRange;
    }

    public static int[] getCornerHeights(Structure.GenerationContext context, int middleBlockX, int xSize, int middleBlockZ, int zSize) {
        ChunkGenerator generator = context.chunkGenerator();
        LevelHeightAccessor heightAccessor = context.heightAccessor();
        RandomState randomState = context.randomState();

        return new int[]{
                generator.getFirstOccupiedHeight(
                        middleBlockX,
                        middleBlockZ,
                        Heightmap.Types.WORLD_SURFACE_WG,
                        heightAccessor,
                        randomState),
                generator.getFirstOccupiedHeight(
                        middleBlockX,
                        middleBlockZ + zSize,
                        Heightmap.Types.WORLD_SURFACE_WG,
                        heightAccessor,
                        randomState),
                generator.getFirstOccupiedHeight(
                        middleBlockX + xSize,
                        middleBlockZ,
                        Heightmap.Types.WORLD_SURFACE_WG,
                        heightAccessor,
                        randomState),
                generator.getFirstOccupiedHeight(
                        middleBlockX + xSize,
                        middleBlockZ + zSize,
                        Heightmap.Types.WORLD_SURFACE_WG,
                        heightAccessor,
                        randomState)};
    }
    public static boolean onLiquid(Structure.GenerationContext context, boolean spawnInLiquid) {
        ChunkPos chunkPos = context.chunkPos();
        BlockPos centerOfChunk = chunkPos.getMiddleBlockPosition(0);
        int landHeight = context.chunkGenerator().getFirstOccupiedHeight(centerOfChunk.getX(), centerOfChunk.getZ(), Heightmap.Types.WORLD_SURFACE_WG, context.heightAccessor(), context.randomState());
        NoiseColumn columnOfBlocks = context.chunkGenerator().getBaseColumn(centerOfChunk.getX(), centerOfChunk.getZ(), context.heightAccessor(), context.randomState());
        BlockState topBlock = columnOfBlocks.getBlock(centerOfChunk.getY() + landHeight);

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
    public static boolean isStructureInDistance(Structure.GenerationContext context, List<Holder<StructureSet>> structures, int minStructureDistance) {
        ChunkGenerator generator = context.chunkGenerator();
        RandomState randomState = context.randomState();
        long seed = context.seed();
        ChunkPos chunkPos = context.chunkPos();

        if (minStructureDistance == 0) {
            return false;
        }

        for (Holder<StructureSet> structure : structures) {
            if (generator.hasStructureChunkInRange(structure, randomState, seed, chunkPos.x, chunkPos.z, minStructureDistance)) {
                return true;
            }
        }

        return false;
    }

    public static boolean isFeatureChunk(Structure.GenerationContext context, int distance) {
        ChunkPos chunkpos = context.chunkPos();

        if (context.chunkGenerator().hasStructureChunkInRange(StructureSets.VILLAGES, context.randomState(), context.seed(), chunkpos.x, chunkpos.z, distance)) {return true;}
        if (context.chunkGenerator().hasStructureChunkInRange(StructureSets.PILLAGER_OUTPOSTS, context.randomState(), context.seed(), chunkpos.x, chunkpos.z, distance)) {return true;}
        if (context.chunkGenerator().hasStructureChunkInRange(StructureSets.OCEAN_MONUMENTS, context.randomState(), context.seed(), chunkpos.x, chunkpos.z, distance)) {return true;}
        if (context.chunkGenerator().hasStructureChunkInRange(StructureSets.DESERT_PYRAMIDS, context.randomState(), context.seed(), chunkpos.x, chunkpos.z, distance)) {return true;}
        if (context.chunkGenerator().hasStructureChunkInRange(StructureSets.END_CITIES, context.randomState(), context.seed(), chunkpos.x, chunkpos.z, distance)) {return true;}
        if (context.chunkGenerator().hasStructureChunkInRange(StructureSets.IGLOOS, context.randomState(), context.seed(), chunkpos.x, chunkpos.z, distance)) {return true;}
        if (context.chunkGenerator().hasStructureChunkInRange(StructureSets.JUNGLE_TEMPLES, context.randomState(), context.seed(), chunkpos.x, chunkpos.z, distance)) {return true;}
        if (context.chunkGenerator().hasStructureChunkInRange(StructureSets.RUINED_PORTALS, context.randomState(), context.seed(), chunkpos.x, chunkpos.z, distance)) {return true;}
        if (context.chunkGenerator().hasStructureChunkInRange(StructureSets.SHIPWRECKS, context.randomState(), context.seed(), chunkpos.x, chunkpos.z, distance)) {return true;}
        if (context.chunkGenerator().hasStructureChunkInRange(StructureSets.SWAMP_HUTS, context.randomState(), context.seed(), chunkpos.x, chunkpos.z, distance)) {return true;}
        if (context.chunkGenerator().hasStructureChunkInRange(StructureSets.WOODLAND_MANSIONS, context.randomState(), context.seed(), chunkpos.x, chunkpos.z, distance)) {return true;}
        if (context.chunkGenerator().hasStructureChunkInRange(StructureSets.OCEAN_RUINS, context.randomState(), context.seed(), chunkpos.x, chunkpos.z, distance)) {return true;}
        if (context.chunkGenerator().hasStructureChunkInRange(StructureSets.NETHER_FOSSILS, context.randomState(), context.seed(), chunkpos.x, chunkpos.z, distance)) {return true;}
        return false;
    }

    public static int getSuitableNetherYLevel(Structure.GenerationContext context) {
        ChunkPos chunkPos = context.chunkPos();
        NoiseColumn column = context.chunkGenerator().getBaseColumn(chunkPos.x, chunkPos.z, context.heightAccessor(), context.randomState());
        List<Integer> suitableYLevels = new ArrayList<>();

        for (int y = 127; y >= 32; y--) {
            if (column.getBlock(y - 1).canOcclude() && column.getBlock(y).isAir() && column.getBlock(y + 4).isAir() && column.getBlock(y + 8).isAir()) {
                suitableYLevels.add(y);
            }
        }

        if (suitableYLevels.isEmpty()) {return 0;}

        return suitableYLevels.get(new Random().nextInt(suitableYLevels.size()));
    }

}
