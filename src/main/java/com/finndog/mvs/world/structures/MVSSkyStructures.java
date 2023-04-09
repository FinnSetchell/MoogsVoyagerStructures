package com.finndog.mvs.world.structures;

import com.finndog.mvs.utils.StructureUtils;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.structure.PostPlacementProcessor;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGenerator;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;
import net.minecraft.world.level.levelgen.structure.pools.JigsawPlacement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

import java.util.Optional;

public class MVSSkyStructures extends StructureFeature<JigsawConfiguration> {

    public static final Codec<JigsawConfiguration> CODEC = RecordCodecBuilder.create((codec) -> {
        return codec.group(
                StructureTemplatePool.CODEC.fieldOf("start_pool").forGetter(JigsawConfiguration::startPool),
                Codec.intRange(0, 30).fieldOf("size").forGetter(JigsawConfiguration::maxDepth)
        ).apply(codec, JigsawConfiguration::new);
    });

    public MVSSkyStructures() {
        super(CODEC, MVSSkyStructures::createPiecesGenerator, PostPlacementProcessor.NONE);
    }

    @Override
    public GenerationStep.Decoration step() {
        return GenerationStep.Decoration.SURFACE_STRUCTURES;
    }


    public static Optional<PieceGenerator<JigsawConfiguration>> createPiecesGenerator(PieceGeneratorSupplier.Context<JigsawConfiguration> context) {

        if (!StructureUtils.isFeatureChunk(context)) {return Optional.empty();}

        BlockPos blockpos = context.chunkPos().getMiddleBlockPosition(0);

        blockpos = blockpos.above(60);

        Optional<PieceGenerator<JigsawConfiguration>> structurePiecesGenerator =
                JigsawPlacement.addPieces(
                        context,
                        PoolElementStructurePiece::new,
                        blockpos,
                        false,
                        true
                );

        return structurePiecesGenerator;
    }
}