package com.finndog.mvs.world.structures;

import com.finndog.mvs.MVSStructures;
import com.finndog.mvs.utils.StructureUtils;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.WorldGenerationContext;
import net.minecraft.world.level.levelgen.heightproviders.HeightProvider;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pools.JigsawPlacement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

import java.util.Optional;

import static com.finndog.mvs.utils.StructureUtils.spawningChecks;

public class MVSGenericJigsawStructure extends Structure {

    public static final Codec<MVSGenericJigsawStructure> CODEC = RecordCodecBuilder.<MVSGenericJigsawStructure>mapCodec(instance ->
        instance.group(MVSGenericJigsawStructure.settingsCodec(instance),
                StructureTemplatePool.CODEC.fieldOf("start_pool").forGetter(structure -> structure.startPool),
                ResourceLocation.CODEC.optionalFieldOf("start_jigsaw_name").forGetter(structure -> structure.startJigsawName),
                Codec.intRange(0, 30).fieldOf("size").forGetter(structure -> structure.size),
                HeightProvider.CODEC.fieldOf("start_height").forGetter(structure -> structure.startHeight),
                Heightmap.Types.CODEC.optionalFieldOf("project_start_to_heightmap").forGetter(structure -> structure.projectStartToHeightmap),
                Codec.intRange(1, 128).fieldOf("max_distance_from_center").orElse(10).forGetter(structure -> structure.maxDistanceFromCenter),
                Codec.BOOL.fieldOf("spawn_in_liquid").orElse(false).forGetter(structure -> structure.spawnInLiquid),
                Codec.intRange(1, 32).fieldOf("radius").orElse(15).forGetter(structure -> structure.radius),
                Codec.intRange(1,16).fieldOf("allowed_terrain_height_range").orElse(2).forGetter(structure -> structure.allowedTerrainHeightRange)
                ).apply(instance, MVSGenericJigsawStructure::new)).codec();

    private final Holder<StructureTemplatePool> startPool;
    private final Optional<ResourceLocation> startJigsawName;
    private final int size;
    private final HeightProvider startHeight;
    private final Optional<Heightmap.Types> projectStartToHeightmap;
    private final int maxDistanceFromCenter;
    private final boolean spawnInLiquid;
    private final int radius;
    private final int allowedTerrainHeightRange;

    public MVSGenericJigsawStructure(Structure.StructureSettings config,
                                     Holder<StructureTemplatePool> startPool,
                                     Optional<ResourceLocation> startJigsawName,
                                     int size,
                                     HeightProvider startHeight,
                                     Optional<Heightmap.Types> projectStartToHeightmap,
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
    public Optional<Structure.GenerationStub> findGenerationPoint(Structure.GenerationContext context) {

        if (spawningChecks(context, projectStartToHeightmap, radius, allowedTerrainHeightRange, spawnInLiquid)) {return Optional.empty();}

        int startY = this.startHeight.sample(context.random(), new WorldGenerationContext(context.chunkGenerator(), context.heightAccessor()));

        ChunkPos chunkPos = context.chunkPos();
        BlockPos blockPos = new BlockPos(chunkPos.getMinBlockX(), startY, chunkPos.getMinBlockZ());

        Optional<Structure.GenerationStub> structurePiecesGenerator =
                JigsawPlacement.addPieces(
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
    public StructureType<?> type() {
        return MVSStructures.MVS_GENERIC_JIGSAW_STRUCTURE.get(); // Helps the game know how to turn this structure back to json to save to chunks
    }
}