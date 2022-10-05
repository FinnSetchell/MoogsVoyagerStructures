package com.finndog.mvs.utils;

import net.minecraft.data.worldgen.StructureSets;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.Structure;

public class StructureUtils {

//    public static boolean isOnWater(Structure.GenerationContext context) {
//        ChunkPos chunkpos = context.chunkPos();
//
//        BlockPos centerOfChunk = chunkpos.getMiddleBlockPosition(0);
//        int landHeight = context.chunkGenerator().getFirstOccupiedHeight(centerOfChunk.getX(), centerOfChunk.getZ(), Heightmap.Types.WORLD_SURFACE_WG, context.heightAccessor());
//        NoiseColumn columnOfBlocks = context.chunkGenerator().getBaseColumn(centerOfChunk.getX(), centerOfChunk.getZ(), context.heightAccessor());
//        BlockState topBlock = columnOfBlocks.getBlock(centerOfChunk.getY() + landHeight);
//
//        if(!topBlock.getFluidState().isEmpty()) {
//            return true;
//        }
//        return false;
//    }
//
//    public static boolean isAllowedTerrainHeightChange(Structure.GenerationContext context, Pair<Integer, Integer> size) {
//        ChunkPos pos = context.chunkPos();
//        int allowedTerrainHeightRange = 2;
//
//
//        int[] cornerHeights = context.getCornerHeights(pos.getMiddleBlockX(), size.getFirst(), pos.getMiddleBlockZ(), size.getSecond());
//
//        int minHeight = Math.min(Math.min(cornerHeights[0], cornerHeights[1]), Math.min(cornerHeights[2], cornerHeights[3]));
//        int maxHeight = Math.max(Math.max(cornerHeights[0], cornerHeights[1]), Math.max(cornerHeights[2], cornerHeights[3]));
//
//        return Math.abs(maxHeight - minHeight) <= allowedTerrainHeightRange;
//    }

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

//    public static int getSuitableNetherYLevel(Structure.GenerationContext context, BlockPos pos) {
//        NoiseColumn column = context.chunkGenerator().getBaseColumn(pos.getX(), pos.getZ(), context.heightAccessor());
//        List<Integer> suitableYLevels = new ArrayList<>();
//
//        for (int y = 127; y >= 32; y--) {
//            if (column.getBlock(y - 1).canOcclude() && column.getBlock(y).isAir() && column.getBlock(y + 4).isAir() && column.getBlock(y + 8).isAir()) {
//                suitableYLevels.add(y);
//            }
//        }
//
//        if (suitableYLevels.isEmpty()) {return 0;}
//
//        int yLevel = suitableYLevels.get(new Random(context.seed()).nextInt(suitableYLevels.size()));
//        return yLevel;
//    }

    public static boolean extraSpawningChecks(Structure.GenerationContext context) {
        // Grabs the chunk position we are at
        ChunkPos chunkpos = context.chunkPos();

        // Checks to make sure our structure does not spawn above land that's higher than y = 150
        // to demonstrate how this method is good for checking extra conditions for spawning
        return context.chunkGenerator().getFirstOccupiedHeight(
                chunkpos.getMinBlockX(),
                chunkpos.getMinBlockZ(),
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                context.heightAccessor(),
                context.randomState()) < 150;
    }
}
