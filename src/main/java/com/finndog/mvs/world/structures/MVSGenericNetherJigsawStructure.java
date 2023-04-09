package com.finndog.mvs.world.structures;

import com.finndog.mvs.utils.StructureUtils;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.structure.PostPlacementProcessor;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGenerator;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;
import net.minecraft.world.level.levelgen.structure.pools.JigsawPlacement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class MVSGenericNetherJigsawStructure extends StructureFeature<JigsawConfiguration> {
    public static final Codec<JigsawConfiguration> CODEC = RecordCodecBuilder.create((codec) -> {
        return codec.group(
                StructureTemplatePool.CODEC.fieldOf("start_pool").forGetter(JigsawConfiguration::startPool),
                Codec.intRange(0, 30).fieldOf("size").forGetter(JigsawConfiguration::maxDepth)
        ).apply(codec, JigsawConfiguration::new);
    });

    public MVSGenericNetherJigsawStructure() {
        super(CODEC, MVSGenericNetherJigsawStructure::createPiecesGenerator, PostPlacementProcessor.NONE);
    }

    @Override
    public GenerationStep.Decoration step() {
        return GenerationStep.Decoration.SURFACE_STRUCTURES;
    }


    public static Optional<PieceGenerator<JigsawConfiguration>> createPiecesGenerator(PieceGeneratorSupplier.Context<JigsawConfiguration> context) {
        boolean spawnInLiquid = false;
        int radius = 6;

        if (!StructureUtils.isFeatureChunk(context)) {return Optional.empty();}
        Optional<Integer> yLevel = StructureUtils.getSuitableNetherYLevel(context, context.chunkPos().getMiddleBlockPosition(0));
        if (yLevel.isEmpty()) {return Optional.empty();}
        BlockPos blockPos = context.chunkPos().getMiddleBlockPosition(yLevel.get());
        if (!spawnInLiquid) {
            if (StructureUtils.onLiquid(context, radius*2)) {
                return Optional.empty();
            }
        }


        Optional<PieceGenerator<JigsawConfiguration>> structurePiecesGenerator =
                JigsawPlacement.addPieces(
                        context,
                        PoolElementStructurePiece::new,
                        blockPos,
                        false,
                        false
                );

        return structurePiecesGenerator;
    }
}