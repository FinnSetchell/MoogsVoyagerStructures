package com.finndog.mvs.world.structures;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.structure.BuiltinStructureSets;
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.structure.PostPlacementProcessor;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGenerator;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;
import net.minecraft.world.level.levelgen.structure.pools.JigsawPlacement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

import java.util.Optional;

public class MVSGenericJigsawStructure extends StructureFeature<JigsawConfiguration> {
    // A custom codec that changes the size limit for our code_structure_sky_fan.json's config to not be capped at 7.
    // With this, we can have a structure with a size limit up to 30 if we want to have extremely long branches of pieces in the structure.
    public static final Codec<JigsawConfiguration> CODEC = RecordCodecBuilder.create((codec) -> {
        return codec.group(
                StructureTemplatePool.CODEC.fieldOf("start_pool").forGetter(JigsawConfiguration::startPool),
                Codec.intRange(0, 30).fieldOf("size").forGetter(JigsawConfiguration::maxDepth)
        ).apply(codec, JigsawConfiguration::new);
    });

    public MVSGenericJigsawStructure() {
        // Create the pieces layout of the structure and give it to the game
        super(CODEC, MVSGenericJigsawStructure::createPiecesGenerator, PostPlacementProcessor.NONE);
    }

    /**
     *        : WARNING!!! DO NOT FORGET THIS METHOD!!!! :
     * If you do not override step method, your structure WILL crash the biome as it is being parsed!
     *
     * Generation step for when to generate the structure. there are 10 stages you can pick from!
     * This surface structure stage places the structure before plants and ores are generated.
     */
    @Override
    public GenerationStep.Decoration step() {
        return GenerationStep.Decoration.SURFACE_STRUCTURES;
    }

    /*
    public static boolean isStructureInDistance(PieceGeneratorSupplier.Context<JigsawConfiguration> context, List<ResourceKey<StructureSet>> structures) {
        ChunkPos chunkpos = context.chunkPos();
        int structureAvoidRadius = 5;
        List<ResourceKey<StructureSet>> structureSetToAvoid = new ArrayList<>();


        if (structureAvoidRadius == 0) {
            return false;
        }

        for (ResourceKey<StructureSet> structureSetToAvoid : structureSetToAvoid) {
            if (context.chunkGenerator().hasFeatureChunkInRange(structureSetToAvoid, context.seed(), chunkpos.x, chunkpos.z, structureAvoidRadius)) {
                return false;
            }
        }

        return false;
    }*/

    public static boolean isOnWater(PieceGeneratorSupplier.Context<JigsawConfiguration> context) {
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

    public static boolean isAllowedTerrainHeightChange(PieceGeneratorSupplier.Context<JigsawConfiguration> context, Pair<Integer, Integer> size) {
        ChunkPos pos = context.chunkPos();
        int allowedTerrainHeightRange = 2;


        int[] cornerHeights = context.getCornerHeights(pos.getMiddleBlockX(), size.getFirst(), pos.getMiddleBlockZ(), size.getSecond());

        int minHeight = Math.min(Math.min(cornerHeights[0], cornerHeights[1]), Math.min(cornerHeights[2], cornerHeights[3]));
        int maxHeight = Math.max(Math.max(cornerHeights[0], cornerHeights[1]), Math.max(cornerHeights[2], cornerHeights[3]));

        return Math.abs(maxHeight - minHeight) <= allowedTerrainHeightRange;
    }

    private static boolean isFeatureChunk(PieceGeneratorSupplier.Context<JigsawConfiguration> context) {
        ChunkPos chunkpos = context.chunkPos();

        if (context.chunkGenerator().hasFeatureChunkInRange(BuiltinStructureSets.VILLAGES, context.seed(), chunkpos.x, chunkpos.z, 10)) {return true;}
        if (context.chunkGenerator().hasFeatureChunkInRange(BuiltinStructureSets.PILLAGER_OUTPOSTS, context.seed(), chunkpos.x, chunkpos.z, 10)) {return true;}
        if (context.chunkGenerator().hasFeatureChunkInRange(BuiltinStructureSets.OCEAN_MONUMENTS, context.seed(), chunkpos.x, chunkpos.z, 10)) {return true;}
        if (context.chunkGenerator().hasFeatureChunkInRange(BuiltinStructureSets.DESERT_PYRAMIDS, context.seed(), chunkpos.x, chunkpos.z, 10)) {return true;}
        if (context.chunkGenerator().hasFeatureChunkInRange(BuiltinStructureSets.END_CITIES, context.seed(), chunkpos.x, chunkpos.z, 5)) {return true;}
        if (context.chunkGenerator().hasFeatureChunkInRange(BuiltinStructureSets.IGLOOS, context.seed(), chunkpos.x, chunkpos.z, 10)) {return true;}
        if (context.chunkGenerator().hasFeatureChunkInRange(BuiltinStructureSets.JUNGLE_TEMPLES, context.seed(), chunkpos.x, chunkpos.z, 10)) {return true;}
        if (context.chunkGenerator().hasFeatureChunkInRange(BuiltinStructureSets.RUINED_PORTALS, context.seed(), chunkpos.x, chunkpos.z, 10)) {return true;}
        if (context.chunkGenerator().hasFeatureChunkInRange(BuiltinStructureSets.SHIPWRECKS, context.seed(), chunkpos.x, chunkpos.z, 10)) {return true;}
        if (context.chunkGenerator().hasFeatureChunkInRange(BuiltinStructureSets.SWAMP_HUTS, context.seed(), chunkpos.x, chunkpos.z, 10)) {return true;}
        if (context.chunkGenerator().hasFeatureChunkInRange(BuiltinStructureSets.WOODLAND_MANSIONS, context.seed(), chunkpos.x, chunkpos.z, 10)) {return true;}
        if (context.chunkGenerator().hasFeatureChunkInRange(BuiltinStructureSets.OCEAN_RUINS, context.seed(), chunkpos.x, chunkpos.z, 10)) {return true;}
        if (context.chunkGenerator().hasFeatureChunkInRange(BuiltinStructureSets.NETHER_FOSSILS, context.seed(), chunkpos.x, chunkpos.z, 2)) {return true;}
        return false;
    }


    public static Optional<PieceGenerator<JigsawConfiguration>> createPiecesGenerator(PieceGeneratorSupplier.Context<JigsawConfiguration> context) {

        if (!MVSGenericJigsawStructure.isFeatureChunk(context)) {return Optional.empty();}
        if (!isAllowedTerrainHeightChange(context, Pair.of(10, 10))) {return Optional.empty();}
        if (isOnWater(context)) {return Optional.empty();}

        // Turns the chunk coordinates into actual coordinates we can use. (Gets center of that chunk)
        BlockPos blockpos = context.chunkPos().getMiddleBlockPosition(0);


        Optional<PieceGenerator<JigsawConfiguration>> structurePiecesGenerator =
                JigsawPlacement.addPieces(
                        context, // Used for JigsawPlacement to get all the proper behaviors done.
                        PoolElementStructurePiece::new, // Needed in order to create a list of jigsaw pieces when making the structure's layout.
                        blockpos, // Position of the structure. Y value is ignored if last parameter is set to true.
                        false,  // Special boundary adjustments for villages. It's... hard to explain. Keep this false and make your pieces not be partially intersecting.
                        // Either not intersecting or fully contained will make children pieces spawn just fine. It's easier that way.
                        true // Adds the terrain height's y value to the passed in blockpos's y value. (This uses WORLD_SURFACE_WG heightmap which stops at top water too)
                        // Here, blockpos's y value is 60 which means the structure spawn 60 blocks above terrain height.
                        // Set this to false for structure to be place only at the passed in blockpos's Y value instead.
                        // Definitely keep this false when placing structures in the nether as otherwise, heightmap placing will put the structure on the Bedrock roof.
                );

        /*
         * Note, you are always free to make your own JigsawPlacement class and implementation of how the structure
         * should generate. It is tricky but extremely powerful if you are doing something that vanilla's jigsaw system cannot do.
         * Such as for example, forcing 3 pieces to always spawn every time, limiting how often a piece spawns, or remove the intersection limitation of pieces.
         *
         * An example of a custom JigsawPlacement.addPieces in action can be found here (warning, it is using Mojmap mappings):
         * https://github.com/TelepathicGrunt/RepurposedStructures/blob/1.18.2/src/main/java/com/telepathicgrunt/repurposedstructures/world/structures/pieces/PieceLimitedJigsawManager.java
         */

        // Return the pieces generator that is now set up so that the game runs it when it needs to create the layout of structure pieces.
        return structurePiecesGenerator;
    }
}