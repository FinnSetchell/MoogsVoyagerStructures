package com.finndog.mvs.world.structures;

import com.finndog.mvs.MVSStructures;
import com.finndog.mvs.utils.StructureUtils;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolBasedGenerator;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.heightprovider.HeightProvider;
import net.minecraft.world.gen.structure.Structure;
import net.minecraft.world.gen.structure.StructureType;

import java.util.Optional;

public class MVSGenericNetherJigsawStructure extends Structure {

    public static final Codec<MVSGenericNetherJigsawStructure> CODEC = RecordCodecBuilder.<MVSGenericNetherJigsawStructure>mapCodec(instance ->
            instance.group(MVSGenericNetherJigsawStructure.configCodecBuilder(instance),
                    StructurePool.REGISTRY_CODEC.fieldOf("start_pool").forGetter(structure -> structure.startPool),
                    Identifier.CODEC.optionalFieldOf("start_jigsaw_name").forGetter(structure -> structure.startJigsawName),
                    Codec.intRange(0, 30).fieldOf("size").forGetter(structure -> structure.size),
                    HeightProvider.CODEC.fieldOf("start_height").forGetter(structure -> structure.startHeight),
                    Heightmap.Type.CODEC.optionalFieldOf("project_start_to_heightmap").forGetter(structure -> structure.projectStartToHeightmap),
                    Codec.intRange(1, 128).fieldOf("max_distance_from_center").orElse(10).forGetter(structure -> structure.maxDistanceFromCenter),
                    Codec.BOOL.fieldOf("spawn_in_liquid").orElse(false).forGetter(structure -> structure.spawnInLiquid)
            ).apply(instance, MVSGenericNetherJigsawStructure::new)).codec();

    private final RegistryEntry<StructurePool> startPool;
    private final Optional<Identifier> startJigsawName;
    private final int size;
    private final HeightProvider startHeight;
    private final Optional<Heightmap.Type> projectStartToHeightmap;
    private final int maxDistanceFromCenter;
    private final boolean spawnInLiquid;


    public MVSGenericNetherJigsawStructure(Structure.Config config,
                                     RegistryEntry<StructurePool> startPool,
                                     Optional<Identifier> startJigsawName,
                                     int size,
                                     HeightProvider startHeight,
                                     Optional<Heightmap.Type> projectStartToHeightmap,
                                     int maxDistanceFromCenter,
                                     boolean spawnInLiquid)
    {
        super(config);
        this.startPool = startPool;
        this.startJigsawName = startJigsawName;
        this.size = size;
        this.startHeight = startHeight;
        this.projectStartToHeightmap = projectStartToHeightmap;
        this.maxDistanceFromCenter = maxDistanceFromCenter;
        this.spawnInLiquid = spawnInLiquid;
    }


    @Override
    public Optional<Structure.StructurePosition> getStructurePosition(Structure.Context context) {
        // Turns the chunk coordinates into actual coordinates we can use. (Gets corner of that chunk)
        Optional<Integer> yLevel = StructureUtils.getSuitableNetherYLevel(context, context.chunkPos().getCenterAtY(0));
        if (yLevel.isEmpty()) {return Optional.empty();} //done
        BlockPos blockPos = context.chunkPos().getCenterAtY(yLevel.get());
        if (!StructureUtils.onLiquid(context, spawnInLiquid)) {return Optional.empty();} //done


        Optional<StructurePosition> structurePiecesGenerator =
                StructurePoolBasedGenerator.generate(
                        context, // Used for StructurePoolBasedGenerator to get all the proper behaviors done.
                        this.startPool, // The starting pool to use to create the structure layout from
                        this.startJigsawName, // Can be used to only spawn from one Jigsaw block. But we don't need to worry about this.
                        this.size, // How deep a branch of pieces can go away from center piece. (5 means branches cannot be longer than 5 pieces from center piece)
                        blockPos, // Where to spawn the structure.
                        false, // "useExpansionHack" This is for legacy villages to generate properly. You should keep this false always.
                        this.projectStartToHeightmap, // Adds the terrain height's y value to the passed in blockpos's y value. (This uses WORLD_SURFACE_WG heightmap which stops at top water too)
                        // Here, blockpos's y value is 60 which means the structure spawn 60 blocks above terrain height.
                        // Set this to false for structure to be place only at the passed in blockpos's Y value instead.
                        // Definitely keep this false when placing structures in the nether as otherwise, heightmap placing will put the structure on the Bedrock roof.
                        this.maxDistanceFromCenter); // Maximum limit for how far pieces can spawn from center. You cannot set this bigger than 128 or else pieces gets cutoff.


        // Return the pieces generator that is now set up so that the game runs it when it needs to create the layout of structure pieces.
        return structurePiecesGenerator;
    }

    @Override
    public StructureType<?> getType() {
        return MVSStructures.MVS_GENERIC_NETHER_JIGSAW_STRUCTURE; // Helps the game know how to turn this structure back to json to save to chunks
    }
}