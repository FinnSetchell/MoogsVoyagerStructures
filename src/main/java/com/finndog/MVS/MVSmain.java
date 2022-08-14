package com.finndog.mvs;

import com.mojang.serialization.Codec;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
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
    public void biomeModification(final BiomeLoadingEvent event) {
        /*
         * Add our structure to all biomes including other modded biomes.
         * You can skip or add only to certain biomes based on stuff like biome category,
         * temperature, scale, precipitation, mod id, etc. All kinds of options!
         *
         * You can even use the BiomeDictionary as well! To use BiomeDictionary, do
         * RegistryKey.getOrCreateKey(Registry.BIOME_KEY, event.getName()) to get the biome's
         * registrykey. Then that can be fed into the dictionary to get the biome's types.
         */
        event.getGeneration().getStructures().add(() -> MVSConfiguredStructures.CONFIGURED_RUN_DOWN_HOUSE);

        event.getGeneration().getStructures().add(() -> MVSConfiguredStructures.CONFIGURED_ABANDONEDLIBRARY);
        event.getGeneration().getStructures().add(() -> MVSConfiguredStructures.CONFIGURED_ACACIALOGPILE);
        event.getGeneration().getStructures().add(() -> MVSConfiguredStructures.CONFIGURED_AZELEAHOUSE);
        event.getGeneration().getStructures().add(() -> MVSConfiguredStructures.CONFIGURED_BARN);
        event.getGeneration().getStructures().add(() -> MVSConfiguredStructures.CONFIGURED_BASALTSTATUE);
        event.getGeneration().getStructures().add(() -> MVSConfiguredStructures.CONFIGURED_BEACHBAR);
        event.getGeneration().getStructures().add(() -> MVSConfiguredStructures.CONFIGURED_BIGOAKTREE);
        event.getGeneration().getStructures().add(() -> MVSConfiguredStructures.CONFIGURED_BIRCHLOGPILE);
        event.getGeneration().getStructures().add(() -> MVSConfiguredStructures.CONFIGURED_BIRCHTREE1);
        event.getGeneration().getStructures().add(() -> MVSConfiguredStructures.CONFIGURED_BOULDER);
        event.getGeneration().getStructures().add(() -> MVSConfiguredStructures.CONFIGURED_CALCITEHOUSE);
        event.getGeneration().getStructures().add(() -> MVSConfiguredStructures.CONFIGURED_CAMPSITE);
        event.getGeneration().getStructures().add(() -> MVSConfiguredStructures.CONFIGURED_CART);
        event.getGeneration().getStructures().add(() -> MVSConfiguredStructures.CONFIGURED_CASTLERUINS);
        event.getGeneration().getStructures().add(() -> MVSConfiguredStructures.CONFIGURED_CRYSTAL);
        event.getGeneration().getStructures().add(() -> MVSConfiguredStructures.CONFIGURED_DARKOAKLOGPILE);
        event.getGeneration().getStructures().add(() -> MVSConfiguredStructures.CONFIGURED_DEEPSLATEHOUSE);
        event.getGeneration().getStructures().add(() -> MVSConfiguredStructures.CONFIGURED_DESERTPUMP);
        event.getGeneration().getStructures().add(() -> MVSConfiguredStructures.CONFIGURED_DUCK);
        event.getGeneration().getStructures().add(() -> MVSConfiguredStructures.CONFIGURED_FLOWERHOLE);
        event.getGeneration().getStructures().add(() -> MVSConfiguredStructures.CONFIGURED_FOXHUT);
        event.getGeneration().getStructures().add(() -> MVSConfiguredStructures.CONFIGURED_HORSEPEN);
        event.getGeneration().getStructures().add(() -> MVSConfiguredStructures.CONFIGURED_HOUSE);
        event.getGeneration().getStructures().add(() -> MVSConfiguredStructures.CONFIGURED_ISLAND);
        event.getGeneration().getStructures().add(() -> MVSConfiguredStructures.CONFIGURED_JUNGLELOGPILE);
        event.getGeneration().getStructures().add(() -> MVSConfiguredStructures.CONFIGURED_JUNGLETOWER);
        event.getGeneration().getStructures().add(() -> MVSConfiguredStructures.CONFIGURED_LAMPCHEST);
        event.getGeneration().getStructures().add(() -> MVSConfiguredStructures.CONFIGURED_LECTURNGARDEN);
        event.getGeneration().getStructures().add(() -> MVSConfiguredStructures.CONFIGURED_LOGRUIN);
        event.getGeneration().getStructures().add(() -> MVSConfiguredStructures.CONFIGURED_MUSHROOMPOND);
        event.getGeneration().getStructures().add(() -> MVSConfiguredStructures.CONFIGURED_MUSHROOMSTATUE);
        event.getGeneration().getStructures().add(() -> MVSConfiguredStructures.CONFIGURED_NETHERDEVIL);
        event.getGeneration().getStructures().add(() -> MVSConfiguredStructures.CONFIGURED_OAKLOGPILE);
        event.getGeneration().getStructures().add(() -> MVSConfiguredStructures.CONFIGURED_POND);
        event.getGeneration().getStructures().add(() -> MVSConfiguredStructures.CONFIGURED_RAILWAY);
        event.getGeneration().getStructures().add(() -> MVSConfiguredStructures.CONFIGURED_RAREWELL);
        event.getGeneration().getStructures().add(() -> MVSConfiguredStructures.CONFIGURED_RUINEDBEACON);
        event.getGeneration().getStructures().add(() -> MVSConfiguredStructures.CONFIGURED_SHED);
        event.getGeneration().getStructures().add(() -> MVSConfiguredStructures.CONFIGURED_SMALLCOPPERWELL);
        event.getGeneration().getStructures().add(() -> MVSConfiguredStructures.CONFIGURED_SMALLRUIN);
        event.getGeneration().getStructures().add(() -> MVSConfiguredStructures.CONFIGURED_SPRUCELOGPILE);
        event.getGeneration().getStructures().add(() -> MVSConfiguredStructures.CONFIGURED_STONEFOUNTAIN);
        event.getGeneration().getStructures().add(() -> MVSConfiguredStructures.CONFIGURED_STONEPILLARS);
        event.getGeneration().getStructures().add(() -> MVSConfiguredStructures.CONFIGURED_SUNZIGATE);
        event.getGeneration().getStructures().add(() -> MVSConfiguredStructures.CONFIGURED_TALLHOUSE);
        event.getGeneration().getStructures().add(() -> MVSConfiguredStructures.CONFIGURED_TREEMONUMENT);
        event.getGeneration().getStructures().add(() -> MVSConfiguredStructures.CONFIGURED_VILLAGERSTATUE);
        event.getGeneration().getStructures().add(() -> MVSConfiguredStructures.CONFIGURED_WARPEDHOUSE);
        event.getGeneration().getStructures().add(() -> MVSConfiguredStructures.CONFIGURED_WELL);
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
            tempMap.putIfAbsent(MVSStructures.RUN_DOWN_HOUSE.get(), DimensionStructuresSettings.DEFAULTS.get(MVSStructures.RUN_DOWN_HOUSE.get()));

            tempMap.putIfAbsent(MVSStructures.ABANDONEDLIBRARY.get(), DimensionStructuresSettings.DEFAULTS.get(MVSStructures.ABANDONEDLIBRARY.get()));
            tempMap.putIfAbsent(MVSStructures.ACACIALOGPILE.get(), DimensionStructuresSettings.DEFAULTS.get(MVSStructures.ACACIALOGPILE.get()));
            tempMap.putIfAbsent(MVSStructures.AZELEAHOUSE.get(), DimensionStructuresSettings.DEFAULTS.get(MVSStructures.AZELEAHOUSE.get()));
            tempMap.putIfAbsent(MVSStructures.BARN.get(), DimensionStructuresSettings.DEFAULTS.get(MVSStructures.BARN.get()));
            tempMap.putIfAbsent(MVSStructures.BASALTSTATUE.get(), DimensionStructuresSettings.DEFAULTS.get(MVSStructures.BASALTSTATUE.get()));
            tempMap.putIfAbsent(MVSStructures.BEACHBAR.get(), DimensionStructuresSettings.DEFAULTS.get(MVSStructures.BEACHBAR.get()));
            tempMap.putIfAbsent(MVSStructures.BIGOAKTREE.get(), DimensionStructuresSettings.DEFAULTS.get(MVSStructures.BIGOAKTREE.get()));
            tempMap.putIfAbsent(MVSStructures.BIRCHLOGPILE.get(), DimensionStructuresSettings.DEFAULTS.get(MVSStructures.BIRCHLOGPILE.get()));
            tempMap.putIfAbsent(MVSStructures.BIRCHTREE1.get(), DimensionStructuresSettings.DEFAULTS.get(MVSStructures.BIRCHTREE1.get()));
            tempMap.putIfAbsent(MVSStructures.BOULDER.get(), DimensionStructuresSettings.DEFAULTS.get(MVSStructures.BOULDER.get()));
            tempMap.putIfAbsent(MVSStructures.CALCITEHOUSE.get(), DimensionStructuresSettings.DEFAULTS.get(MVSStructures.CALCITEHOUSE.get()));
            tempMap.putIfAbsent(MVSStructures.CAMPSITE.get(), DimensionStructuresSettings.DEFAULTS.get(MVSStructures.CAMPSITE.get()));
            tempMap.putIfAbsent(MVSStructures.CART.get(), DimensionStructuresSettings.DEFAULTS.get(MVSStructures.CART.get()));
            tempMap.putIfAbsent(MVSStructures.CASTLERUINS.get(), DimensionStructuresSettings.DEFAULTS.get(MVSStructures.CASTLERUINS.get()));
            tempMap.putIfAbsent(MVSStructures.CRYSTAL.get(), DimensionStructuresSettings.DEFAULTS.get(MVSStructures.CRYSTAL.get()));
            tempMap.putIfAbsent(MVSStructures.DARKOAKLOGPILE.get(), DimensionStructuresSettings.DEFAULTS.get(MVSStructures.DARKOAKLOGPILE.get()));
            tempMap.putIfAbsent(MVSStructures.DEEPSLATEHOUSE.get(), DimensionStructuresSettings.DEFAULTS.get(MVSStructures.DEEPSLATEHOUSE.get()));
            tempMap.putIfAbsent(MVSStructures.DESERTPUMP.get(), DimensionStructuresSettings.DEFAULTS.get(MVSStructures.DESERTPUMP.get()));
            tempMap.putIfAbsent(MVSStructures.DUCK.get(), DimensionStructuresSettings.DEFAULTS.get(MVSStructures.DUCK.get()));
            tempMap.putIfAbsent(MVSStructures.FLOWERHOLE.get(), DimensionStructuresSettings.DEFAULTS.get(MVSStructures.FLOWERHOLE.get()));
            tempMap.putIfAbsent(MVSStructures.FOXHUT.get(), DimensionStructuresSettings.DEFAULTS.get(MVSStructures.FOXHUT.get()));
            tempMap.putIfAbsent(MVSStructures.HORSEPEN.get(), DimensionStructuresSettings.DEFAULTS.get(MVSStructures.HORSEPEN.get()));
            tempMap.putIfAbsent(MVSStructures.HOUSE.get(), DimensionStructuresSettings.DEFAULTS.get(MVSStructures.HOUSE.get()));
            tempMap.putIfAbsent(MVSStructures.ISLAND.get(), DimensionStructuresSettings.DEFAULTS.get(MVSStructures.ISLAND.get()));
            tempMap.putIfAbsent(MVSStructures.JUNGLELOGPILE.get(), DimensionStructuresSettings.DEFAULTS.get(MVSStructures.JUNGLELOGPILE.get()));
            tempMap.putIfAbsent(MVSStructures.JUNGLETOWER.get(), DimensionStructuresSettings.DEFAULTS.get(MVSStructures.JUNGLETOWER.get()));
            tempMap.putIfAbsent(MVSStructures.LAMPCHEST.get(), DimensionStructuresSettings.DEFAULTS.get(MVSStructures.LAMPCHEST.get()));
            tempMap.putIfAbsent(MVSStructures.LECTURNGARDEN.get(), DimensionStructuresSettings.DEFAULTS.get(MVSStructures.LECTURNGARDEN.get()));
            tempMap.putIfAbsent(MVSStructures.LOGRUIN.get(), DimensionStructuresSettings.DEFAULTS.get(MVSStructures.LOGRUIN.get()));
            tempMap.putIfAbsent(MVSStructures.MUSHROOMPOND.get(), DimensionStructuresSettings.DEFAULTS.get(MVSStructures.MUSHROOMPOND.get()));
            tempMap.putIfAbsent(MVSStructures.MUSHROOMSTATUE.get(), DimensionStructuresSettings.DEFAULTS.get(MVSStructures.MUSHROOMSTATUE.get()));
            tempMap.putIfAbsent(MVSStructures.NETHERDEVIL.get(), DimensionStructuresSettings.DEFAULTS.get(MVSStructures.NETHERDEVIL.get()));
            tempMap.putIfAbsent(MVSStructures.OAKLOGPILE.get(), DimensionStructuresSettings.DEFAULTS.get(MVSStructures.OAKLOGPILE.get()));
            tempMap.putIfAbsent(MVSStructures.POND.get(), DimensionStructuresSettings.DEFAULTS.get(MVSStructures.POND.get()));
            tempMap.putIfAbsent(MVSStructures.RAILWAY.get(), DimensionStructuresSettings.DEFAULTS.get(MVSStructures.RAILWAY.get()));
            tempMap.putIfAbsent(MVSStructures.RAREWELL.get(), DimensionStructuresSettings.DEFAULTS.get(MVSStructures.RAREWELL.get()));
            tempMap.putIfAbsent(MVSStructures.RUINEDBEACON.get(), DimensionStructuresSettings.DEFAULTS.get(MVSStructures.RUINEDBEACON.get()));
            tempMap.putIfAbsent(MVSStructures.SHED.get(), DimensionStructuresSettings.DEFAULTS.get(MVSStructures.SHED.get()));
            tempMap.putIfAbsent(MVSStructures.SMALLCOPPERWELL.get(), DimensionStructuresSettings.DEFAULTS.get(MVSStructures.SMALLCOPPERWELL.get()));
            tempMap.putIfAbsent(MVSStructures.SMALLRUIN.get(), DimensionStructuresSettings.DEFAULTS.get(MVSStructures.SMALLRUIN.get()));
            tempMap.putIfAbsent(MVSStructures.SPRUCELOGPILE.get(), DimensionStructuresSettings.DEFAULTS.get(MVSStructures.SPRUCELOGPILE.get()));
            tempMap.putIfAbsent(MVSStructures.STONEFOUNTAIN.get(), DimensionStructuresSettings.DEFAULTS.get(MVSStructures.STONEFOUNTAIN.get()));
            tempMap.putIfAbsent(MVSStructures.STONEPILLARS.get(), DimensionStructuresSettings.DEFAULTS.get(MVSStructures.STONEPILLARS.get()));
            tempMap.putIfAbsent(MVSStructures.SUNZIGATE.get(), DimensionStructuresSettings.DEFAULTS.get(MVSStructures.SUNZIGATE.get()));
            tempMap.putIfAbsent(MVSStructures.TALLHOUSE.get(), DimensionStructuresSettings.DEFAULTS.get(MVSStructures.TALLHOUSE.get()));
            tempMap.putIfAbsent(MVSStructures.TREEMONUMENT.get(), DimensionStructuresSettings.DEFAULTS.get(MVSStructures.TREEMONUMENT.get()));
            tempMap.putIfAbsent(MVSStructures.VILLAGERSTATUE.get(), DimensionStructuresSettings.DEFAULTS.get(MVSStructures.VILLAGERSTATUE.get()));
            tempMap.putIfAbsent(MVSStructures.WARPEDHOUSE.get(), DimensionStructuresSettings.DEFAULTS.get(MVSStructures.WARPEDHOUSE.get()));
            tempMap.putIfAbsent(MVSStructures.WELL.get(), DimensionStructuresSettings.DEFAULTS.get(MVSStructures.WELL.get()));

            serverWorld.getChunkSource().generator.getSettings().structureConfig = tempMap;
        }
   }
}
