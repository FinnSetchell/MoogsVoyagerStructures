package com.finndog.mvs.utils;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.structure.BuiltinStructureSets;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StructureUtils {

    public static boolean isOnWater(Structure.GenerationContext context) {
        ChunkPos chunkpos = context.chunkPos();

        BlockPos centerOfChunk = chunkpos.getMiddleBlockPosition(0);
        int landHeight = context.chunkGenerator().getFirstOccupiedHeight(centerOfChunk.getX(), centerOfChunk.getZ(), Heightmap.Types.WORLD_SURFACE_WG, context.heightAccessor());
        NoiseColumn columnOfBlocks = context.chunkGenerator().getBaseColumn(centerOfChunk.getX(), centerOfChunk.getZ(), context.heightAccessor());
        BlockState topBlock = columnOfBlocks.getBlock(centerOfChunk.getY() + landHeight);

        if(!topBlock.getFluidState().isEmpty()) {
            return true;
        }
        return false;
    }

    public static boolean isAllowedTerrainHeightChange(Structure.GenerationContext context, Pair<Integer, Integer> size) {
        ChunkPos pos = context.chunkPos();
        int allowedTerrainHeightRange = 2;


        int[] cornerHeights = context.getCornerHeights(pos.getMiddleBlockX(), size.getFirst(), pos.getMiddleBlockZ(), size.getSecond());

        int minHeight = Math.min(Math.min(cornerHeights[0], cornerHeights[1]), Math.min(cornerHeights[2], cornerHeights[3]));
        int maxHeight = Math.max(Math.max(cornerHeights[0], cornerHeights[1]), Math.max(cornerHeights[2], cornerHeights[3]));

        return Math.abs(maxHeight - minHeight) <= allowedTerrainHeightRange;
    }

    public static boolean isFeatureChunk(Structure.GenerationContext context) {
        ChunkPos chunkpos = context.chunkPos();

        if (context.chunkGenerator().hasStructureChunkInRange(BuiltinStructureSets.VILLAGES, context.randomState(), context.seed(), chunkpos.x, chunkpos.z, 10)) {return true;}




        if (context.chunkGenerator().hasStructureChunkInRange(BuiltinStructureSets.PILLAGER_OUTPOSTS, , context.seed(), chunkpos.x, chunkpos.z, 10)) {return true;}
        if (context.chunkGenerator().hasStructureChunkInRange(BuiltinStructureSets.OCEAN_MONUMENTS, , context.seed(), chunkpos.x, chunkpos.z, 10)) {return true;}
        if (context.chunkGenerator().hasStructureChunkInRange(BuiltinStructureSets.DESERT_PYRAMIDS, , context.seed(), chunkpos.x, chunkpos.z, 10)) {return true;}
        if (context.chunkGenerator().hasStructureChunkInRange(BuiltinStructureSets.END_CITIES, , context.seed(), chunkpos.x, chunkpos.z, 5)) {return true;}
        if (context.chunkGenerator().hasStructureChunkInRange(BuiltinStructureSets.IGLOOS, , context.seed(), chunkpos.x, chunkpos.z, 10)) {return true;}
        if (context.chunkGenerator().hasStructureChunkInRange(BuiltinStructureSets.JUNGLE_TEMPLES, , context.seed(), chunkpos.x, chunkpos.z, 10)) {return true;}
        if (context.chunkGenerator().hasStructureChunkInRange(BuiltinStructureSets.RUINED_PORTALS, , context.seed(), chunkpos.x, chunkpos.z, 10)) {return true;}
        if (context.chunkGenerator().hasStructureChunkInRange(BuiltinStructureSets.SHIPWRECKS, , context.seed(), chunkpos.x, chunkpos.z, 10)) {return true;}
        if (context.chunkGenerator().hasStructureChunkInRange(BuiltinStructureSets.SWAMP_HUTS, , context.seed(), chunkpos.x, chunkpos.z, 10)) {return true;}
        if (context.chunkGenerator().hasStructureChunkInRange(BuiltinStructureSets.WOODLAND_MANSIONS, , context.seed(), chunkpos.x, chunkpos.z, 10)) {return true;}
        if (context.chunkGenerator().hasStructureChunkInRange(BuiltinStructureSets.OCEAN_RUINS, , context.seed(), chunkpos.x, chunkpos.z, 10)) {return true;}
        if (context.chunkGenerator().hasStructureChunkInRange(BuiltinStructureSets.NETHER_FOSSILS, , context.seed(), chunkpos.x, chunkpos.z, 2)) {return true;}
        return false;
    }

    public static int getSuitableNetherYLevel(Structure.GenerationContext context, BlockPos pos) {
        NoiseColumn column = context.chunkGenerator().getBaseColumn(pos.getX(), pos.getZ(), context.heightAccessor());
        List<Integer> suitableYLevels = new ArrayList<>();

        for (int y = 127; y >= 32; y--) {
            if (column.getBlock(y - 1).canOcclude() && column.getBlock(y).isAir() && column.getBlock(y + 4).isAir() && column.getBlock(y + 8).isAir()) {
                suitableYLevels.add(y);
            }
        }

        if (suitableYLevels.isEmpty()) {return 0;}

        int yLevel = suitableYLevels.get(new Random(context.seed()).nextInt(suitableYLevels.size()));
        return yLevel;
    }

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
