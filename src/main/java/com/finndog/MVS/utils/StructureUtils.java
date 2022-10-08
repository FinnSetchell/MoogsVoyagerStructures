package com.finndog.mvs.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.data.worldgen.StructureSets;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.Structure;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StructureUtils {

    public static boolean isAllowedTerrainHeightChange(Structure.GenerationContext context, int radius, int allowedTerrainHeightRange) {
        ChunkPos chunkPos = context.chunkPos();
        BlockPos centerOfChunk = chunkPos.getMiddleBlockPosition(0);
        int[] outerHeights = new int[4];

        outerHeights[0] = centerOfChunk.getX() + radius;
        outerHeights[1] = centerOfChunk.getX() - radius;
        outerHeights[2] = centerOfChunk.getZ() + radius;
        outerHeights[3] = centerOfChunk.getZ() - radius;

        int minHeight = Math.min(Math.min(outerHeights[0], outerHeights[1]), Math.min(outerHeights[2], outerHeights[3]));
        int maxHeight = Math.max(Math.max(outerHeights[0], outerHeights[1]), Math.max(outerHeights[2], outerHeights[3]));

        if (Math.abs(maxHeight - minHeight) <= allowedTerrainHeightRange) {
            return true;
        }
        return false;
    }
    public static boolean onLiquid(Structure.GenerationContext context, boolean spawnInLiquid) {
        ChunkPos chunkPos = context.chunkPos();
        BlockPos centerOfChunk = chunkPos.getMiddleBlockPosition(0);
        int landHeight = context.chunkGenerator().getFirstOccupiedHeight(centerOfChunk.getX(), centerOfChunk.getZ(), Heightmap.Types.WORLD_SURFACE_WG, context.heightAccessor(), context.randomState());
        NoiseColumn columnOfBlocks = context.chunkGenerator().getBaseColumn(centerOfChunk.getX(), centerOfChunk.getZ(), context.heightAccessor(), context.randomState());
        BlockState topBlock = columnOfBlocks.getBlock(centerOfChunk.getY() + landHeight);

        if (spawnInLiquid) { //if we want it to spawn in liquid
            if(!topBlock.getFluidState().isEmpty()) { //if top block liquid
                return true; //spawn
            } else { //if top block not liquid
                return false; //dont spawn
            }
        } //else
        if(topBlock.getFluidState().isEmpty()) {  //if not liquid
            return true; //spawn
        }
        return false; //otherwise dont spawn
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
