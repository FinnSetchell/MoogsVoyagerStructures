package com.finndog.mvs.world.structures;

import com.finndog.mvs.world.structures.configs.MVSGenericConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.CheckerboardColumnBiomeSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.structure.PostPlacementProcessor;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGenerator;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;
import net.minecraft.world.level.levelgen.structure.pools.JigsawPlacement;

import java.util.Optional;

import static com.finndog.mvs.world.structures.SkyStructures.CODEC;

public class GenericJigsawStructure extends StructureFeature<JigsawConfiguration> {

    public GenericJigsawStructure() {
        // Create the pieces layout of the structure and give it to the game
        super(CODEC, GenericJigsawStructure::createPiecesGenerator, PostPlacementProcessor.NONE);
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
     * This is where extra checks can be done to determine if the structure can spawn here.
     * This only needs to be overridden if you're adding additional spawn conditions.
     *
     * Fun fact, if you set your structure separation/spacing to be 0/1, you can use
     * isFeatureChunk to return true only if certain chunk coordinates are passed in
     * which allows you to spawn structures only at certain coordinates in the world.
     *
     * Basically, this method is used for determining if the land is at a suitable height,
     * if certain other structures are too close or not, or some other restrictive condition.
     *
     * For example, Pillager Outposts added a check to make sure it cannot spawn within 10 chunk of a Village.
     * (Bedrock Edition seems to not have the same check)
     *
     * If you are doing Nether structures, you'll probably want to spawn your structure on top of ledges.
     * Best way to do that is to use getBaseColumn to grab a column of blocks at the structure's x/z position.
     * Then loop through it and look for land with air above it and set blockpos's Y value to it.
     * Make sure to set the final boolean in JigsawPlacement.addPieces to false so
     * that the structure spawns at blockpos's y value instead of placing the structure on the Bedrock roof!
     *
     * Also, please for the love of god, do not do dimension checking here.
     * If you do and another mod's dimension is trying to spawn your structure,
     * the locate command will make minecraft hang forever and break the game.
     * Use the biome tags for where to spawn the structure and users can datapack
     * it to spawn in specific biomes that aren't in the dimension they don't like if they wish.
     */
    private static boolean isGenericFeatureChunk(PieceGeneratorSupplier.Context<JigsawConfiguration> context, MVSGenericConfig config) {
        ChunkPos chunkPos = context.chunkPos();

        if (!(context.biomeSource() instanceof CheckerboardColumnBiomeSource)) {
            for (int curChunkX = chunkPos.x - config.biomeRadius; curChunkX <= chunkPos.x + config.biomeRadius; curChunkX++) {
                for (int curChunkZ = chunkPos.z - config.biomeRadius; curChunkZ <= chunkPos.z + config.biomeRadius; curChunkZ++) {
                    int yValue = config.doNotUseHeightmap ? config.setFixedYSpawn : config.setFixedYSpawn + context.chunkGenerator().getFirstFreeHeight(curChunkX << 4, curChunkZ << 4, Heightmap.Types.WORLD_SURFACE_WG, context.heightAccessor());
                    Holder<Biome> biome = context.biomeSource().getNoiseBiome(curChunkX << 2, yValue >> 2, curChunkZ << 2, context.chunkGenerator().climateSampler());
                    if (!context.validBiome().test(biome)) {
                        return false;
                    }
                }
            }
        }

        if (config.cannotSpawnInLiquid) {
            BlockPos centerOfChunk = chunkPos.getMiddleBlockPosition(0);
            int landHeight = context.chunkGenerator().getFirstOccupiedHeight(centerOfChunk.getX(), centerOfChunk.getZ(), Heightmap.Types.WORLD_SURFACE_WG, context.heightAccessor());
            NoiseColumn columnOfBlocks = context.chunkGenerator().getBaseColumn(centerOfChunk.getX(), centerOfChunk.getZ(), context.heightAccessor());
            BlockState topBlock = columnOfBlocks.getBlock(centerOfChunk.getY() + landHeight);

            if(!topBlock.getFluidState().isEmpty()) {
                return false;
            }
        }

        //cannot be near other specified structure
        for (ResourceKey<StructureSet> structureSetToAvoid : config.structureSetToAvoid) {
            if (context.chunkGenerator().hasFeatureChunkInRange(structureSetToAvoid, context.seed(), chunkPos.x, chunkPos.z, config.structureAvoidRadius)) {
                return false;
            }
        }

        if (config.allowedTerrainHeightRange != -1) {
            int maxTerrainHeight = Integer.MIN_VALUE;
            int minTerrainHeight = Integer.MAX_VALUE;

            for (int curChunkX = chunkPos.x - config.terrainHeightCheckRadius; curChunkX <= chunkPos.x + config.terrainHeightCheckRadius; curChunkX++) {
                for (int curChunkZ = chunkPos.z - config.terrainHeightCheckRadius; curChunkZ <= chunkPos.z + config.terrainHeightCheckRadius; curChunkZ++) {
                    int height = context.chunkGenerator().getBaseHeight((curChunkX << 4) + 7, (curChunkZ << 4) + 7, Heightmap.Types.WORLD_SURFACE_WG, context.heightAccessor());
                    maxTerrainHeight = Math.max(maxTerrainHeight, height);
                    minTerrainHeight = Math.min(minTerrainHeight, height);

                    if (minTerrainHeight < config.minYAllowed) {
                        return false;
                    }
                }
            }

            if(maxTerrainHeight - minTerrainHeight > config.allowedTerrainHeightRange) {
                return false;
            }
        }

        return true;
        //return !context.chunkGenerator().hasFeatureChunkInRange(BuiltinStructureSets.OCEAN_MONUMENTS, context.seed(), chunkPos.x, chunkPos.z, 10);
    }

    public static Optional<PieceGenerator<JigsawConfiguration>> createPiecesGenerator(PieceGeneratorSupplier.Context<JigsawConfiguration> context) {

        // Check if the spot is valid for our structure. This is just as another method for cleanness.
        // Returning an empty optional tells the game to skip this spot as it will not generate the structure.
        if (!GenericJigsawStructure.isGenericFeatureChunk(context, null)) {
            return Optional.empty();
        }

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