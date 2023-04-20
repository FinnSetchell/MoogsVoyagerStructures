package com.finndog.mvs.world.structures.placements;

import com.finndog.mvs.modinit.MVSStructurePlacementType;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.structure.StructureSet;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.math.random.CheckedRandom;
import net.minecraft.util.math.random.ChunkRandom;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryCodecs;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryEntryList;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.gen.chunk.placement.SpreadType;
import net.minecraft.world.gen.chunk.placement.StructurePlacementType;
import net.minecraft.world.gen.chunk.placement.StructurePlacement;
import net.minecraft.world.gen.noise.NoiseConfig;

import java.util.Optional;

public class AdvancedRandomSpread extends RandomSpreadStructurePlacement {
    // This is a codec for the AdvancedRandomSpread class, which defines a custom structure placement algorithm
    public static final Codec<AdvancedRandomSpread> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            // vector offset to add to the starting coordinates of each structure instance
            Vec3i.createOffsetCodec(16).optionalFieldOf("locate_offset", Vec3i.ZERO).forGetter(AdvancedRandomSpread::getLocateOffset),
            // method to reduce the frequency of structures based on biome or chunk properties
            StructurePlacement.FrequencyReductionMethod.CODEC.optionalFieldOf("frequency_reduction_method", StructurePlacement.FrequencyReductionMethod.DEFAULT).forGetter(AdvancedRandomSpread::getFrequencyReductionMethod),
            // probability of generating a structure in a given chunk
            Codec.floatRange(0.0F, 1.0F).optionalFieldOf("frequency", 1.0F).forGetter(AdvancedRandomSpread::getFrequency),
            // random number to add to the seed of the structure generator
            Codecs.NONNEGATIVE_INT.fieldOf("salt").forGetter(AdvancedRandomSpread::getSalt),
            // minimum distance between structures of the same type
            StructurePlacement.ExclusionZone.CODEC.optionalFieldOf("exclusion_zone").forGetter(AdvancedRandomSpread::getExclusionZone),
            // minimum distance between structures of different types
            SuperExclusionZone.CODEC.optionalFieldOf("super_exclusion_zone").forGetter(AdvancedRandomSpread::superExclusionZone),
            Codec.intRange(0, Integer.MAX_VALUE).fieldOf("spacing").forGetter(AdvancedRandomSpread::getSpacing),
            Codec.intRange(0, Integer.MAX_VALUE).fieldOf("separation").forGetter(AdvancedRandomSpread::getSeparation),
            SpreadType.CODEC.optionalFieldOf("spread_type", SpreadType.LINEAR).forGetter(AdvancedRandomSpread::getSpreadType),
            // minimum distance from the world origin where structures can spawn
            Codec.intRange(0, Integer.MAX_VALUE).optionalFieldOf("min_distance_from_world_origin").forGetter(AdvancedRandomSpread::minDistanceFromWorldOrigin)
    ).apply(instance, instance.stable(AdvancedRandomSpread::new)));

    private final StructurePlacement.FrequencyReductionMethod frequencyReductionMethod;
    private final float frequency;
    private final int salt;
    private final int spacing;
    private final int separation;
    private final SpreadType spreadType;
    private final Optional<Integer> minDistanceFromWorldOrigin;
    private final Optional<StructurePlacement.ExclusionZone> exclusionZone;
    private final Optional<SuperExclusionZone> superExclusionZone;

    public AdvancedRandomSpread(Vec3i locationOffset,
                                StructurePlacement.FrequencyReductionMethod frequencyReductionMethod,
                                float frequency,
                                int salt,
                                Optional<ExclusionZone> exclusionZone,
                                Optional<SuperExclusionZone> superExclusionZone,
                                int spacing,
                                int separation,
                                SpreadType spreadType,
                                Optional<Integer> minDistanceFromWorldOrigin
    ) {
        super(locationOffset, frequencyReductionMethod, frequency, salt, exclusionZone, spacing, separation, spreadType);
        this.frequencyReductionMethod = frequencyReductionMethod;
        this.frequency = frequency;
        this.salt = salt;
        this.spacing = spacing;
        this.separation = separation;
        this.spreadType = spreadType;
        this.minDistanceFromWorldOrigin = minDistanceFromWorldOrigin;
        this.exclusionZone = exclusionZone;
        this.superExclusionZone = superExclusionZone;

        if (spacing <= separation) {
            throw new RuntimeException("""
                MVS - Moog's Voyager Structures: Spacing cannot be less or equal to separation.
                Please correct this error as there's no way to spawn this structure properly
                    Spacing: %s
                    Separation: %s.
            """.formatted(spacing, separation));
        }
    }

    @Override
    public int getSpacing() {
        return this.spacing;
    }

    @Override
    public int getSeparation() {
        return this.separation;
    }

    @Override
    public SpreadType getSpreadType() {
        return this.spreadType;
    }

    public Optional<Integer> minDistanceFromWorldOrigin() {
        return this.minDistanceFromWorldOrigin;
    }

    public Optional<SuperExclusionZone> superExclusionZone() {
        return this.superExclusionZone;
    }

    @Override
    public boolean shouldGenerate(ChunkGenerator chunkGenerator, NoiseConfig noiseConfig, long seed, int x, int z) {
        if (!super.shouldGenerate(chunkGenerator, noiseConfig, seed, x, z)) {
            return false;
        }
        return this.superExclusionZone.isEmpty() || !this.superExclusionZone.get().isPlacementForbidden(chunkGenerator, noiseConfig, seed, x, z);
    }

    @Override
    public ChunkPos getStartChunk(long seed, int x, int z) {
        int regionX = Math.floorDiv(x, this.spacing);
        int regionZ = Math.floorDiv(z, this.spacing);
        ChunkRandom chunkRandom = new ChunkRandom(new CheckedRandom(0L));
        chunkRandom.setRegionSeed(seed, regionX, regionZ, this.getSalt());

        int diff = this.spacing - this.separation;
        int offsetX = this.spreadType.get(chunkRandom, diff);
        int offsetZ = this.spreadType.get(chunkRandom, diff);
        return new ChunkPos(regionX * this.spacing + offsetX, regionZ * this.spacing + offsetZ);
    }

    @Override
    protected boolean isStartChunk(ChunkGenerator chunkGenerator, NoiseConfig noiseConfig, long seed, int x, int z) {
        if (minDistanceFromWorldOrigin.isPresent()) {
            int xBlockPos = x * 16;
            int zBlockPos = z * 16;
            if((xBlockPos * xBlockPos) + (zBlockPos * zBlockPos) < (minDistanceFromWorldOrigin.get() * minDistanceFromWorldOrigin.get()))
            {
                return false;
            }
        }

        ChunkPos chunkpos = this.getStartChunk(seed, x, z);
        return chunkpos.x == x && chunkpos.z == z;
    }

    @Override
    public StructurePlacementType<?> getType() {
        return MVSStructurePlacementType.ADVANCED_RANDOM_SPREAD;
    }

    public record SuperExclusionZone(RegistryEntryList<StructureSet> otherSet, int chunkCount) {
        public static final Codec<SuperExclusionZone> CODEC = RecordCodecBuilder.create(builder -> builder.group(
                RegistryCodecs.entryList(Registry.STRUCTURE_SET_KEY, StructureSet.CODEC).fieldOf("other_set").forGetter(AdvancedRandomSpread.SuperExclusionZone::otherSet),
                Codec.intRange(1, 16).fieldOf("chunk_count").forGetter(SuperExclusionZone::chunkCount)
        ).apply(builder, SuperExclusionZone::new));

        boolean isPlacementForbidden(ChunkGenerator chunkGenerator, NoiseConfig noiseConfig, long seed, int x, int z) {
            for (RegistryEntry<StructureSet> registryEntry : this.otherSet) {
                if (chunkGenerator.shouldStructureGenerateInRange(registryEntry, noiseConfig, seed, x, z, this.chunkCount)) {
                    return true;
                }
            }

            return false;
        }
    }


}