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
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.HeightContext;
import net.minecraft.world.gen.heightprovider.HeightProvider;
import net.minecraft.world.gen.structure.Structure;
import net.minecraft.world.gen.structure.StructureType;

import java.util.Optional;

import static com.finndog.mvs.utils.StructureUtils.spawningChecks;

public class MVSGenericJigsawStructure extends Structure {

    public static final Codec<MVSGenericJigsawStructure> CODEC = RecordCodecBuilder.<MVSGenericJigsawStructure>mapCodec(instance ->
            instance.group(MVSGenericJigsawStructure.configCodecBuilder(instance),
                StructurePool.REGISTRY_CODEC.fieldOf("start_pool").forGetter(structure -> structure.startPool),
                Identifier.CODEC.optionalFieldOf("start_jigsaw_name").forGetter(structure -> structure.startJigsawName),
                Codec.intRange(0, 30).fieldOf("size").forGetter(structure -> structure.size),
                HeightProvider.CODEC.fieldOf("start_height").forGetter(structure -> structure.startHeight),
                Heightmap.Type.CODEC.optionalFieldOf("project_start_to_heightmap").forGetter(structure -> structure.projectStartToHeightmap),
                Codec.intRange(1, 128).fieldOf("max_distance_from_center").orElse(10).forGetter(structure -> structure.maxDistanceFromCenter),
                Codec.BOOL.fieldOf("spawn_in_liquid").orElse(false).forGetter(structure -> structure.spawnInLiquid),
                Codec.intRange(1, 32).fieldOf("radius").orElse(15).forGetter(structure -> structure.radius),
                Codec.intRange(1,16).fieldOf("allowed_terrain_height_range").orElse(2).forGetter(structure -> structure.allowedTerrainHeightRange)
                ).apply(instance, MVSGenericJigsawStructure::new)).codec();

    private final RegistryEntry<StructurePool> startPool;
    private final Optional<Identifier> startJigsawName;
    private final int size;
    private final HeightProvider startHeight;
    private final Optional<Heightmap.Type> projectStartToHeightmap;
    private final int maxDistanceFromCenter;
    private final boolean spawnInLiquid;
    private final int radius;
    private final int allowedTerrainHeightRange;
    public MVSGenericJigsawStructure(Structure.Config config,
                                     RegistryEntry<StructurePool> startPool,
                                     Optional<Identifier> startJigsawName,
                                     int size,
                                     HeightProvider startHeight,
                                     Optional<Heightmap.Type> projectStartToHeightmap,
                                     int maxDistanceFromCenter,
                                     boolean spawnInLiquid,
                                     int radius,
                                     int allowedTerrainHeightRange) {
        super(config);
        this.startPool = startPool;
        this.startJigsawName = startJigsawName;
        this.size = size;
        this.startHeight = startHeight;
        this.projectStartToHeightmap = projectStartToHeightmap;
        this.maxDistanceFromCenter = maxDistanceFromCenter;
        this.spawnInLiquid = spawnInLiquid;
        this.radius = radius;
        this.allowedTerrainHeightRange = allowedTerrainHeightRange;
    }


    @Override
    public Optional<Structure.StructurePosition> getStructurePosition(Structure.Context context) {

        if (spawningChecks(context, projectStartToHeightmap, radius, allowedTerrainHeightRange, spawnInLiquid)) {return Optional.empty();}

        int startY = this.startHeight.get(context.random(), new HeightContext(context.chunkGenerator(), context.world()));

        ChunkPos chunkPos = context.chunkPos();
        BlockPos blockPos = new BlockPos(chunkPos.getStartX(), startY, chunkPos.getStartZ());

        Optional<StructurePosition> structurePiecesGenerator =
                StructurePoolBasedGenerator.generate(
                        context,
                        this.startPool,
                        this.startJigsawName,
                        this.size,
                        blockPos,
                        false,
                        this.projectStartToHeightmap,
                        this.maxDistanceFromCenter);


        return structurePiecesGenerator;
    }

    @Override
    public StructureType<?> getType() {
        return MVSStructures.MVS_GENERIC_JIGSAW_STRUCTURE;
    }
}