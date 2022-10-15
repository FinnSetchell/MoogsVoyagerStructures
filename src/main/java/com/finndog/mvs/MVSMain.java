package com.finndog.mvs;

import com.mojang.serialization.Codec;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.FlatChunkGenerator;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Mod(MVSMain.MODID)
public class MVSMain {

    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "mvs";

    private static boolean isLoaded(String modid){ return ModList.get().isLoaded(modid); }
    private static boolean isBiome(final BiomeLoadingEvent event, String key){ return event.getName().toString().equals(key); }
    private static boolean isBiome(final BiomeLoadingEvent event, ResourceLocation location){ return event.getName().equals(location); }

    public MVSMain() {
        // For registration and init stuff.
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        MVSStructures.DEFERRED_REGISTRY_STRUCTURE.register(modEventBus);
        modEventBus.addListener(this::setup);

        // For events that happen after initialization. This is probably going to be use a lot.
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;
        forgeBus.addListener(EventPriority.NORMAL, this::addDimensionalSpacing);

        // The comments for BiomeLoadingEvent and StructureSpawnListGatherEvent says to do HIGH for additions.
        forgeBus.addListener(EventPriority.HIGH, this::biomeModification);
    }

    /**
     * Here, setupStructures will be ran after registration of all structures are finished.
     * This is important to be done here so that the Deferred Registry has already ran and
     * registered/created our structure for us.
     *
     * Once after that structure instance is made, we then can now do the rest of the setup
     * that requires a structure instance such as setting the structure spacing, creating the
     * configured structure instance, and more.
     */
    public void setup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(() -> {
            MVSStructures.setupStructures();
            MVSConfiguredStructures.registerConfiguredStructures();
        });
    }


    /**
     * This is the event you will use to add anything to any biome.
     * This includes spawns, changing the biome's looks, messing with its surfacebuilders,
     * adding carvers, spawning new features... etc
     *
     * Here, we will use this to add our structure to all biomes.
     */


    @SubscribeEvent(priority = EventPriority.HIGH)
    public void biomeModification(final BiomeLoadingEvent event) {

        if (event.getCategory() != Biome.Category.NETHER && event.getCategory() != Biome.Category.THEEND) {
            event.getGeneration().getStructures().add(() -> MVSConfiguredStructures.CONFIGURED_COMMONOVERWORLDSTRUCTURES);

            if (event.getCategory() == Biome.Category.SAVANNA) {
            }
            if (event.getCategory() == Biome.Category.BEACH) {
            }
            if (isBiome(event, Biomes.BIRCH_FOREST.location())) {
            }
            if (isBiome(event, Biomes.DARK_FOREST.location())) {
            }
            if (event.getCategory() == Biome.Category.DESERT) {
            }
            if (isBiome(event, Biomes.FLOWER_FOREST.location())) {
            }
            if (event.getCategory() == Biome.Category.JUNGLE) {
            }
            if (isBiome(event, Biomes.MUSHROOM_FIELDS.location())) {
            }
            if (event.getCategory() == Biome.Category.FOREST) {
            }
            if (event.getCategory() == Biome.Category.OCEAN) {
            }
            if (event.getCategory() == Biome.Category.FOREST || event.getCategory() == Biome.Category.PLAINS) {
            }
            if (event.getCategory() == Biome.Category.TAIGA) {
            }
            if (isBiome(event, Biomes.FOREST.location())) {
            }
            if (isBiome(event, Biomes.SWAMP.location())) {
            }
        }
        if (event.getCategory() == Biome.Category.NETHER) {
            if (isBiome(event, Biomes.BASALT_DELTAS.location())) {
            }
            if (isBiome(event, Biomes.NETHER_WASTES.location())) {
            }
            if (isBiome(event, Biomes.CRIMSON_FOREST.location())) {
            }
        }
        if (event.getCategory() == Biome.Category.THEEND) {
        }
//        if (isLoaded("biomesoplenty")) {
//           if (isBiome(event, "biomesoplenty:crystalline_chasm")
//                   || isBiome(event, "biomesoplenty:undergrowth")
//                   || isBiome(event, "biomesoplenty:visceral_heap")) {
//
//           }
//           if (isBiome(event, "biomesoplenty:withered_abyss")) {
//
//           }
//       }
//
//       if (isLoaded("byg")) {
//           if (isBiome(event, "byg:waiting_garth")) {
//
//           }
//           else if (isBiome(event, "byg:crimson_gardens")) {
//
//           }
//           else if (isBiome(event, "byg:glowstone_gardens")) {
//
//           }
//       }


    }



    /**
     * Will go into the world's chunkgenerator and manually add our structure spacing.
     * If the spacing is not added, the structure doesn't spawn.
     *
     * Use this for dimension blacklists for your structure.
     * (Don't forget to attempt to remove your structure too from the map if you are blacklisting that dimension!)
     * (It might have your structure in it already.)
     *
     * Basically use this to make absolutely sure the chunkgenerator can or cannot spawn your structure.
     */
    private static Method GETCODEC_METHOD;
    public void addDimensionalSpacing(final WorldEvent.Load event) {
        if(event.getWorld() instanceof ServerWorld){
            ServerWorld serverWorld = (ServerWorld)event.getWorld();

            /*
             * Skip Terraforged's chunk generator as they are a special case of a mod locking down their chunkgenerator.
             * They will handle your structure spacing for your if you add to WorldGenRegistries.NOISE_GENERATOR_SETTINGS in your structure's registration.
             * This here is done with reflection as this tutorial is not about setting up and using Mixins.
             * If you are using mixins, you can call the codec method with an invoker mixin instead of using reflection.
             */
            try {
                if(GETCODEC_METHOD == null) GETCODEC_METHOD = ObfuscationReflectionHelper.findMethod(ChunkGenerator.class, "func_230347_a_");
                ResourceLocation cgRL = Registry.CHUNK_GENERATOR.getKey((Codec<? extends ChunkGenerator>) GETCODEC_METHOD.invoke(serverWorld.getChunkSource().generator));
                if(cgRL != null && cgRL.getNamespace().equals("terraforged")) return;
            }
            catch(Exception e){
                MVSMain.LOGGER.error("Was unable to check if " + serverWorld.dimension().location() + " is using Terraforged's ChunkGenerator.");
            }

            /*
             * Prevent spawning our structure in Vanilla's superflat world as
             * people seem to want their superflat worlds free of modded structures.
             * Also that vanilla superflat is really tricky and buggy to work with in my experience.
             */
            if(serverWorld.getChunkSource().getGenerator() instanceof FlatChunkGenerator &&
                serverWorld.dimension().equals(World.OVERWORLD)){
                return;
            }

            /*
             * putIfAbsent so people can override the spacing with dimension datapacks themselves if they wish to customize spacing more precisely per dimension.
             * Requires AccessTransformer  (see resources/META-INF/accesstransformer.cfg)
             *
             * NOTE: if you add per-dimension spacing configs, you can't use putIfAbsent as WorldGenRegistries.NOISE_GENERATOR_SETTINGS in FMLCommonSetupEvent
             * already added your default structure spacing to some dimensions. You would need to override the spacing with .put(...)
             * And if you want to do dimension blacklisting, you need to remove the spacing entry entirely from the map below to prevent generation safely.
             */
            Map<Structure<?>, StructureSeparationSettings> tempMap = new HashMap<>(serverWorld.getChunkSource().generator.getSettings().structureConfig());

            tempMap.putIfAbsent(MVSStructures.COMMONOVERWORLDSTRUCTURES.get(), DimensionStructuresSettings.DEFAULTS.get(MVSStructures.COMMONOVERWORLDSTRUCTURES.get()));

            serverWorld.getChunkSource().generator.getSettings().structureConfig = tempMap;
        }
   }
}
