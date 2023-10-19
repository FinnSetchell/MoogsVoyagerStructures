package com.finndog.mvs.misc.pooladditions;

import com.finndog.mvs.MVSCommon;
import com.finndog.mvs.events.lifecycle.ServerGoingToStartEvent;
import com.finndog.mvs.mixins.structures.ListPoolElementAccessor;
import com.finndog.mvs.mixins.structures.SinglePoolElementAccessor;
import com.finndog.mvs.mixins.structures.StructurePoolAccessor;
import com.finndog.mvs.mixins.structures.StructureTemplateManagerAccessor;
import com.finndog.mvs.utils.GeneralUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.level.levelgen.structure.pools.ListPoolElement;
import net.minecraft.world.level.levelgen.structure.pools.SinglePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public final class PoolAdditionMerger {
    private PoolAdditionMerger() {}

    // Needed for detecting the correct files, ignoring file extension, and what JSON parser to use for parsing the files
    private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().setLenient().disableHtmlEscaping().create();
    private static final String DATA_TYPE = "pool_additions";
    private static final int FILE_SUFFIX_LENGTH = ".json".length();

    /**
     * Call this at mod init so we can subscribe our pool merging to run at server startup as that's when the dynamic registry exists.
     */
    public static void mergeAdditionPools(final ServerGoingToStartEvent event) {
        ResourceManager resourceManager = ((StructureTemplateManagerAccessor) event.getServer().getStructureManager()).mvs_getResourceManager();
        Map<ResourceLocation, List<JsonElement>> poolAdditionJSON = GeneralUtils.getAllDatapacksJSONElement(resourceManager, GSON, DATA_TYPE, FILE_SUFFIX_LENGTH);
        parsePoolsAndBeginMerger(poolAdditionJSON, event.getServer().registryAccess(),  event.getServer().getStructureManager());
    }

    /**
     * Using the given dynamic registry, will now parse the JSON objects of pools and resolve their processors with the dynamic registry.
     * Afterwards, it will merge the parsed pool into the targeted pool found in the dynamic registry.
     */
    private static void parsePoolsAndBeginMerger(Map<ResourceLocation, List<JsonElement>> poolAdditionJSON, RegistryAccess dynamicRegistryManager, StructureTemplateManager StructureTemplateManager) {
        Registry<StructureTemplatePool> poolRegistry = dynamicRegistryManager.registryOrThrow(Registry.TEMPLATE_POOL_REGISTRY);
        RegistryOps<JsonElement> customRegistryOps = RegistryOps.create(JsonOps.INSTANCE, dynamicRegistryManager);

        // Will iterate over all of our found pool additions and make sure the target pool exists before we parse our JSON objects
        for (Map.Entry<ResourceLocation, List<JsonElement>> entry : poolAdditionJSON.entrySet()) {
            if (poolRegistry.get(entry.getKey()) == null) continue;

            // Parse the given pool addition JSON objects and add their pool to the dynamic registry pool
            for (JsonElement jsonElement : entry.getValue()) {
                try {
                    AdditionalStructureTemplatePool.DIRECT_CODEC.parse(customRegistryOps, jsonElement)
                            .resultOrPartial(mvssageString -> logBadData(entry.getKey(), mvssageString))
                            .ifPresent(validPool -> mergeIntoExistingPool(validPool, poolRegistry.get(entry.getKey()), StructureTemplateManager));
                }
                catch (Exception e) {
                    MVSCommon.LOGGER.error("""

                            Moog's End Structures: Pool Addition json failed to be parsed.
                            This is usually due to using a mod compat datapack without the other mod being on.
                            Entry failed to be resolved: %s
                            Registry being used: %s
                            Error mvssage is: %s""".formatted(entry.getKey(), poolRegistry, e.getMessage()).indent(1));
                }
            }
        }
    }

    /**
     * Merges the incoming pool with the given target pool in an additive manner that does not affect any other pools and can be stacked safely.
     */
    private static void mergeIntoExistingPool(AdditionalStructureTemplatePool feedingPool, StructureTemplatePool gluttonyPool, StructureTemplateManager structureTemplateManager) {
        // Make new copies of lists as the originals are immutable lists and we want to make sure our changes only stays with this pool element
        ObjectArrayList<StructurePoolElement> elements = new ObjectArrayList<>(((StructurePoolAccessor) gluttonyPool).mvs_getTemplates());
        List<Pair<StructurePoolElement, Integer>> elementCounts = new ArrayList<>(((StructurePoolAccessor) gluttonyPool).mvs_getRawTemplates());

        elements.addAll(((StructurePoolAccessor) feedingPool).mvs_getTemplates());
        elementCounts.addAll(((StructurePoolAccessor) feedingPool).mvs_getRawTemplates());

        // Helps people know if they typoed their merger pool's nbt file paths
        for(StructurePoolElement element : elements) {
            if(element instanceof SinglePoolElement singlePoolElement) {
                Optional<ResourceLocation> pieceRL = ((SinglePoolElementAccessor)singlePoolElement).mvs_getTemplate().left();
                if(pieceRL.isEmpty()) continue;
                checkIfPieceExists(feedingPool, structureTemplateManager, pieceRL.get());
            }
            else if(element instanceof ListPoolElement listPoolElement) {
                for(StructurePoolElement listElement : ((ListPoolElementAccessor)listPoolElement).mvs_getElements()) {
                    if(listElement instanceof SinglePoolElement singlePoolElement) {
                        Optional<ResourceLocation> pieceRL = ((SinglePoolElementAccessor) singlePoolElement).mvs_getTemplate().left();
                        if (pieceRL.isEmpty()) continue;
                        checkIfPieceExists(feedingPool, structureTemplateManager, pieceRL.get());
                    }
                }
            }
        }


        ((StructurePoolAccessor) gluttonyPool).mvs_setTemplates(elements);
        ((StructurePoolAccessor) gluttonyPool).mvs_setRawTemplates(elementCounts);
    }

    private static void checkIfPieceExists(AdditionalStructureTemplatePool feedingPool, StructureTemplateManager structureTemplateManager, ResourceLocation pieceRL) {
        ResourceLocation resourcelocation = new ResourceLocation(pieceRL.getNamespace(), "structures/" + pieceRL.getPath() + ".nbt");
        try {
            InputStream inputstream = ((StructureTemplateManagerAccessor) structureTemplateManager).mvs_getResourceManager().open(resourcelocation);
            if (inputstream.available() == 0 || inputstream.read(new byte[1]) == -1) {
                MVSCommon.LOGGER.error("(Moog's End Structures POOL MERGER) Found an entry in {} that points to the non-existent nbt file called {}", feedingPool.getName(), pieceRL);
            }
            inputstream.close();
        }
        catch (Throwable filenotfoundexception) {
            MVSCommon.LOGGER.error("(Moog's End Structures POOL MERGER) Found an entry in {} that points to the non-existent nbt file called {}", feedingPool.getName(), pieceRL);
        }
    }

    /**
     * Log out the pool that failed to be parsed and what the error is.
     */
    private static void logBadData(ResourceLocation poolPath, String mvssageString) {
        MVSCommon.LOGGER.error("(Moog's End Structures POOL MERGER) Failed to parse {} additions file. Error is: {}", poolPath, mvssageString);
    }
    private static class AdditionalStructureTemplatePool extends StructureTemplatePool {
        private static final Codec<ExpandedPoolEntry> EXPANDED_POOL_ENTRY_CODEC = RecordCodecBuilder.create(instance -> instance.group(
                StructurePoolElement.CODEC.fieldOf("element").forGetter(ExpandedPoolEntry::poolElement),
                Codec.intRange(1, 5000).fieldOf("weight").forGetter(ExpandedPoolEntry::weight),
                ResourceLocation.CODEC.optionalFieldOf("condition").forGetter(ExpandedPoolEntry::condition)
        ).apply(instance, ExpandedPoolEntry::new));

        public static final Codec<AdditionalStructureTemplatePool> DIRECT_CODEC = RecordCodecBuilder.create(instance -> instance.group(
                ResourceLocation.CODEC.fieldOf("name").forGetter(structureTemplatePool -> structureTemplatePool.name),
                ResourceLocation.CODEC.fieldOf("fallback").forGetter(StructureTemplatePool::getFallback),
                EXPANDED_POOL_ENTRY_CODEC.listOf().fieldOf("elements").forGetter(structureTemplatePool -> structureTemplatePool.rawTemplatesWithConditions)
        ).apply(instance, AdditionalStructureTemplatePool::new));

        protected final List<ExpandedPoolEntry> rawTemplatesWithConditions;
        protected final ResourceLocation name;

        public AdditionalStructureTemplatePool(ResourceLocation name, ResourceLocation fallback, List<ExpandedPoolEntry> rawTemplatesWithConditions) {
            super(name, fallback, rawTemplatesWithConditions.stream().map(triple -> Pair.of(triple.poolElement(), triple.weight())).collect(Collectors.toList()));
            this.rawTemplatesWithConditions = rawTemplatesWithConditions;
            this.name = name;
        }
        public record ExpandedPoolEntry(StructurePoolElement poolElement, Integer weight, Optional<ResourceLocation> condition) {}
    }
}