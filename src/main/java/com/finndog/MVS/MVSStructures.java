package com.finndog.mvs;

import com.finndog.mvs.structures.*;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MVSStructures {

    /**
     * We are using the Deferred Registry system to register our structure as this is the preferred way on Forge.
     * This will handle registering the base structure for us at the correct time so we don't have to handle it ourselves.
     *
     * HOWEVER, do note that Deferred Registries only work for anything that is a Forge Registry. This means that
     * configured structures and configured features need to be registered directly to WorldGenRegistries as there
     * is no Deferred Registry system for them.
     */
    public static final DeferredRegister<Structure<?>> DEFERRED_REGISTRY_STRUCTURE = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, MVSMain.MODID);

    /**
     * Registers the structure itself and sets what its path is. In this case, the
     * structure will have the resourcelocation of structure_tutorial:run_down_house.
     *
     * It is always a good idea to register your Structures so that other mods and datapacks can
     * use them too directly from the registries. It great for mod/datapacks compatibility.
     *
     * IMPORTANT: Once you have set the name for your structure below and distributed your mod,
     *   changing the structure's registry name or removing the structure may cause log spam.
     *   This log spam won't break your worlds as forge already fixed the Mojang bug of removed structures wrecking worlds.
     *   https://github.com/MinecraftForge/MinecraftForge/commit/56e538e8a9f1b8e6ff847b9d2385484c48849b8d
     *
     *   However, users might not know that and think you are to blame for issues that doesn't exist.
     *   So it is best to keep your structure names the same as long as you can instead of changing them frequently.
     */
    public static final RegistryObject<Structure<NoFeatureConfig>> RUN_DOWN_HOUSE = DEFERRED_REGISTRY_STRUCTURE.register("run_down_house", () -> (new RunDownHouseStructure(NoFeatureConfig.CODEC)));

    public static final RegistryObject<Structure<NoFeatureConfig>> ABANDONEDLIBRARY = DEFERRED_REGISTRY_STRUCTURE.register("abandonedlibrary", () -> (new AbandonedLibrary(NoFeatureConfig.CODEC)));
    public static final RegistryObject<Structure<NoFeatureConfig>> ACACIALOGPILE = DEFERRED_REGISTRY_STRUCTURE.register("acacialogpile", () -> (new AcaciaLogPile(NoFeatureConfig.CODEC)));
    public static final RegistryObject<Structure<NoFeatureConfig>> AZELEAHOUSE = DEFERRED_REGISTRY_STRUCTURE.register("azeleahouse", () -> (new AzeleaHouse(NoFeatureConfig.CODEC)));
    public static final RegistryObject<Structure<NoFeatureConfig>> BARN = DEFERRED_REGISTRY_STRUCTURE.register("barn", () -> (new Barn(NoFeatureConfig.CODEC)));
    public static final RegistryObject<Structure<NoFeatureConfig>> BASALTSTATUE = DEFERRED_REGISTRY_STRUCTURE.register("basaltstatue", () -> (new BasaltStatue(NoFeatureConfig.CODEC)));
    public static final RegistryObject<Structure<NoFeatureConfig>> BEACHBAR = DEFERRED_REGISTRY_STRUCTURE.register("beachbar", () -> (new BeachBar(NoFeatureConfig.CODEC)));
    public static final RegistryObject<Structure<NoFeatureConfig>> BIGOAKTREE = DEFERRED_REGISTRY_STRUCTURE.register("bigoaktree", () -> (new BigOakTree(NoFeatureConfig.CODEC)));
    public static final RegistryObject<Structure<NoFeatureConfig>> BIRCHLOGPILE = DEFERRED_REGISTRY_STRUCTURE.register("birchlogpile", () -> (new BirchLogPile(NoFeatureConfig.CODEC)));
    public static final RegistryObject<Structure<NoFeatureConfig>> BIRCHTREE1 = DEFERRED_REGISTRY_STRUCTURE.register("birchtree1", () -> (new BirchTree1(NoFeatureConfig.CODEC)));
    public static final RegistryObject<Structure<NoFeatureConfig>> BOULDER = DEFERRED_REGISTRY_STRUCTURE.register("boulder", () -> (new Boulder(NoFeatureConfig.CODEC)));
    public static final RegistryObject<Structure<NoFeatureConfig>> CALCITEHOUSE = DEFERRED_REGISTRY_STRUCTURE.register("calcitehouse", () -> (new CalciteHouse(NoFeatureConfig.CODEC)));
    public static final RegistryObject<Structure<NoFeatureConfig>> CAMPSITE = DEFERRED_REGISTRY_STRUCTURE.register("campsite", () -> (new Campsite(NoFeatureConfig.CODEC)));
    public static final RegistryObject<Structure<NoFeatureConfig>> CART = DEFERRED_REGISTRY_STRUCTURE.register("cart", () -> (new Cart(NoFeatureConfig.CODEC)));
    public static final RegistryObject<Structure<NoFeatureConfig>> CASTLERUINS = DEFERRED_REGISTRY_STRUCTURE.register("castleruins", () -> (new CastleRuins(NoFeatureConfig.CODEC)));
    public static final RegistryObject<Structure<NoFeatureConfig>> CRYSTAL = DEFERRED_REGISTRY_STRUCTURE.register("crystal", () -> (new Crystal(NoFeatureConfig.CODEC)));
    public static final RegistryObject<Structure<NoFeatureConfig>> DARKOAKLOGPILE = DEFERRED_REGISTRY_STRUCTURE.register("darkoaklogpile", () -> (new DarkOakLogPile(NoFeatureConfig.CODEC)));
    public static final RegistryObject<Structure<NoFeatureConfig>> DEEPSLATEHOUSE = DEFERRED_REGISTRY_STRUCTURE.register("deepslatehouse", () -> (new DeepSlateHouse(NoFeatureConfig.CODEC)));
    public static final RegistryObject<Structure<NoFeatureConfig>> DESERTPUMP = DEFERRED_REGISTRY_STRUCTURE.register("desertpump", () -> (new DesertPump(NoFeatureConfig.CODEC)));
    public static final RegistryObject<Structure<NoFeatureConfig>> DUCK = DEFERRED_REGISTRY_STRUCTURE.register("duck", () -> (new Duck(NoFeatureConfig.CODEC)));
    public static final RegistryObject<Structure<NoFeatureConfig>> FLOWERHOLE = DEFERRED_REGISTRY_STRUCTURE.register("flowerhole", () -> (new FlowerHole(NoFeatureConfig.CODEC)));
    public static final RegistryObject<Structure<NoFeatureConfig>> FOXHUT = DEFERRED_REGISTRY_STRUCTURE.register("foxhut", () -> (new FoxHut(NoFeatureConfig.CODEC)));
    public static final RegistryObject<Structure<NoFeatureConfig>> HORSEPEN = DEFERRED_REGISTRY_STRUCTURE.register("horsepen", () -> (new HorsePen(NoFeatureConfig.CODEC)));
    public static final RegistryObject<Structure<NoFeatureConfig>> HOUSE = DEFERRED_REGISTRY_STRUCTURE.register("house", () -> (new House(NoFeatureConfig.CODEC)));
    public static final RegistryObject<Structure<NoFeatureConfig>> ISLAND = DEFERRED_REGISTRY_STRUCTURE.register("island", () -> (new Island(NoFeatureConfig.CODEC)));
    public static final RegistryObject<Structure<NoFeatureConfig>> JUNGLELOGPILE = DEFERRED_REGISTRY_STRUCTURE.register("junglelogpile", () -> (new JungleLogPile(NoFeatureConfig.CODEC)));
    public static final RegistryObject<Structure<NoFeatureConfig>> JUNGLETOWER = DEFERRED_REGISTRY_STRUCTURE.register("jungletower", () -> (new JungleTower(NoFeatureConfig.CODEC)));
    public static final RegistryObject<Structure<NoFeatureConfig>> LAMPCHEST = DEFERRED_REGISTRY_STRUCTURE.register("lampchest", () -> (new LampChest(NoFeatureConfig.CODEC)));
    public static final RegistryObject<Structure<NoFeatureConfig>> LECTURNGARDEN = DEFERRED_REGISTRY_STRUCTURE.register("lecturngarden", () -> (new LecturnGarden(NoFeatureConfig.CODEC)));
    public static final RegistryObject<Structure<NoFeatureConfig>> LOGRUIN = DEFERRED_REGISTRY_STRUCTURE.register("logruin", () -> (new LogRuin(NoFeatureConfig.CODEC)));
    public static final RegistryObject<Structure<NoFeatureConfig>> MUSHROOMPOND = DEFERRED_REGISTRY_STRUCTURE.register("mushroompond", () -> (new MushroomPond(NoFeatureConfig.CODEC)));
    public static final RegistryObject<Structure<NoFeatureConfig>> MUSHROOMSTATUE = DEFERRED_REGISTRY_STRUCTURE.register("mushroomstatue", () -> (new MushroomStatue(NoFeatureConfig.CODEC)));
    public static final RegistryObject<Structure<NoFeatureConfig>> NETHERDEVIL = DEFERRED_REGISTRY_STRUCTURE.register("netherdevil", () -> (new NetherDevil(NoFeatureConfig.CODEC)));
    public static final RegistryObject<Structure<NoFeatureConfig>> OAKLOGPILE = DEFERRED_REGISTRY_STRUCTURE.register("oaklogpile", () -> (new OakLogPile(NoFeatureConfig.CODEC)));
    public static final RegistryObject<Structure<NoFeatureConfig>> POND = DEFERRED_REGISTRY_STRUCTURE.register("pond", () -> (new Pond(NoFeatureConfig.CODEC)));
    public static final RegistryObject<Structure<NoFeatureConfig>> RAILWAY = DEFERRED_REGISTRY_STRUCTURE.register("railway", () -> (new Railway(NoFeatureConfig.CODEC)));
    public static final RegistryObject<Structure<NoFeatureConfig>> RUINEDBEACON = DEFERRED_REGISTRY_STRUCTURE.register("ruinedbeacon", () -> (new RuinedBeacon(NoFeatureConfig.CODEC)));
    public static final RegistryObject<Structure<NoFeatureConfig>> SHED = DEFERRED_REGISTRY_STRUCTURE.register("shed", () -> (new Shed(NoFeatureConfig.CODEC)));
    public static final RegistryObject<Structure<NoFeatureConfig>> SMALLCOPPERWELL = DEFERRED_REGISTRY_STRUCTURE.register("smallcopperwell", () -> (new SmallCopperWell(NoFeatureConfig.CODEC)));
    public static final RegistryObject<Structure<NoFeatureConfig>> SMALLRUIN = DEFERRED_REGISTRY_STRUCTURE.register("smallruin", () -> (new SmallRuin(NoFeatureConfig.CODEC)));
    public static final RegistryObject<Structure<NoFeatureConfig>> SPRUCELOGPILE = DEFERRED_REGISTRY_STRUCTURE.register("sprucelogpile", () -> (new SpruceLogPile(NoFeatureConfig.CODEC)));
    public static final RegistryObject<Structure<NoFeatureConfig>> STONEFOUNTAIN = DEFERRED_REGISTRY_STRUCTURE.register("stonefountain", () -> (new StoneFountain(NoFeatureConfig.CODEC)));
    public static final RegistryObject<Structure<NoFeatureConfig>> STONEPILLARS = DEFERRED_REGISTRY_STRUCTURE.register("stonepillars", () -> (new StonePillars(NoFeatureConfig.CODEC)));
    public static final RegistryObject<Structure<NoFeatureConfig>> SUNZIGATE = DEFERRED_REGISTRY_STRUCTURE.register("sunzigate", () -> (new SunziGate(NoFeatureConfig.CODEC)));
    public static final RegistryObject<Structure<NoFeatureConfig>> TALLHOUSE = DEFERRED_REGISTRY_STRUCTURE.register("tallhouse", () -> (new TallHouse(NoFeatureConfig.CODEC)));
    public static final RegistryObject<Structure<NoFeatureConfig>> TREEMONUMENT = DEFERRED_REGISTRY_STRUCTURE.register("treemonument", () -> (new TreeMonument(NoFeatureConfig.CODEC)));
    public static final RegistryObject<Structure<NoFeatureConfig>> VILLAGERSTATUE = DEFERRED_REGISTRY_STRUCTURE.register("villagerstatue", () -> (new VillagerStatue(NoFeatureConfig.CODEC)));
    public static final RegistryObject<Structure<NoFeatureConfig>> WARPEDHOUSE = DEFERRED_REGISTRY_STRUCTURE.register("warpedhouse", () -> (new WarpedHouse(NoFeatureConfig.CODEC)));
    public static final RegistryObject<Structure<NoFeatureConfig>> WELL = DEFERRED_REGISTRY_STRUCTURE.register("well", () -> (new Well(NoFeatureConfig.CODEC)));

    /**
     * This is where we set the rarity of your structures and determine if land conforms to it.
     * See the comments in below for more details.
     */
    public static void setupStructures() {
        setupMapSpacingAndLand(
                RUN_DOWN_HOUSE.get(), /* The instance of the structure */
                new StructureSeparationSettings(10 /* average distance apart in chunks between spawn attempts */,
                        5 /* minimum distance apart in chunks between spawn attempts. MUST BE LESS THAN ABOVE VALUE*/,
                        1234567890 /* this modifies the seed of the structure so no two structures always spawn over each-other. Make this large and unique. */),
                true);
        setupMapSpacingAndLand(BARN.get(), new StructureSeparationSettings(10, 5, 1465521987), true);

        public static void SetupMapSpacingAndLandLoop() {
            Random random = new Random();
            RegistryObject<Structure<NoFeatureConfig>>[] ROStructuresEpic = {BASALTSTATUE, CRYSTAL, DUCK, ISLAND, JUNGLETOWER, RUINEDBEACON, SHED, SUNZIGATE, TALLHOUSE};
            RegistryObject<Structure<NoFeatureConfig>>[] ROStructuresRare = {ABANDONEDLIBRARY, CASTLERUINS, DEEPSLATEHOUSE, FLOWERHOLE, LAMPCHEST, LOGRUIN, NETHERDEVIL, RAILWAY, STONEPILLARS, STONEFOUNTAIN, TREEMONUMENT, VILLAGERSTATUE};
            RegistryObject<Structure<NoFeatureConfig>>[] ROStructuresCommon = {AZELEAHOUSE, BARN, BEACHBAR, BIGOAKTREE, CALCITEHOUSE, CAMPSITE, CART, DESERTPUMP, FOXHUT, HORSEPEN, HOUSE, LECTURNGARDEN, MUSHROOMPOND, MUSHROOMSTATUE, POND, SMALLCOPPERWELL, SMALLRUIN,WARPEDHOUSE, WELL};
            RegistryObject<Structure<NoFeatureConfig>>[] ROStructuresDecoration = {ACACIALOGPILE, BIRCHLOGPILE, BIRCHTREE1, BOULDER, DARKOAKLOGPILE, JUNGLELOGPILE, OAKLOGPILE, SPRUCELOGPILE};


            for (int i = 0; i < ROStructuresDecoration.length; i++) {
                setupMapSpacingAndLand(ROStructuresDecoration[i].get(), new StructureSeparationSettings(random.nextInt(20)+15, 15, 1465521987), true);
            }
        }

        // Add more structures here and so on
    }

    public static double getSalt() {
        double[] Salts = {};
        Random random = new Random();
        double number = random.nextInt(999999999) + 1000000000;




        return number;
    }

    /**
     * Adds the provided structure to the registry, and adds the separation settings.
     * The rarity of the structure is determined based on the values passed into
     * this method in the structureSeparationSettings argument.
     * This method is called by setupStructures above.
     */
    public static <F extends Structure<?>> void setupMapSpacingAndLand(
            F structure,
            StructureSeparationSettings structureSeparationSettings,
            boolean transformSurroundingLand)
    {
        /*
         * We need to add our structures into the map in Structure class
         * alongside vanilla structures or else it will cause errors.
         *
         * If the registration is setup properly for the structure,
         * getRegistryName() should never return null.
         */
        Structure.STRUCTURES_REGISTRY.put(structure.getRegistryName().toString(), structure);

        /*
         * Whether surrounding land will be modified automatically to conform to the bottom of the structure.
         * Basically, it adds land at the base of the structure like it does for Villages and Outposts.
         * Doesn't work well on structure that have pieces stacked vertically or change in heights.
         *
         * Note: The air space this method will create will be filled with water if the structure is below sealevel.
         * This means this is best for structure above sealevel so keep that in mind.
         *
         * NOISE_AFFECTING_FEATURES requires AccessTransformer  (See resources/META-INF/accesstransformer.cfg)
         */
        if(transformSurroundingLand){
            Structure.NOISE_AFFECTING_FEATURES =
                    ImmutableList.<Structure<?>>builder()
                            .addAll(Structure.NOISE_AFFECTING_FEATURES)
                            .add(structure)
                            .build();
        }

        /*
         * This is the map that holds the default spacing of all structures.
         * Always add your structure to here so that other mods can utilize it if needed.
         *
         * However, while it does propagate the spacing to some correct dimensions from this map,
         * it seems it doesn't always work for code made dimensions as they read from this list beforehand.
         *
         * Instead, we will use the WorldEvent.Load event in StructureTutorialMain to add the structure
         * spacing from this list into that dimension or to do dimension blacklisting properly.
         * We also use our entry in DimensionStructuresSettings.DEFAULTS in WorldEvent.Load as well.
         *
         * DEFAULTS requires AccessTransformer  (See resources/META-INF/accesstransformer.cfg)
         */
        DimensionStructuresSettings.DEFAULTS =
                ImmutableMap.<Structure<?>, StructureSeparationSettings>builder()
                        .putAll(DimensionStructuresSettings.DEFAULTS)
                        .put(structure, structureSeparationSettings)
                        .build();


        /*
         * There are very few mods that relies on seeing your structure in the noise settings registry before the world is made.
         *
         * You may see some mods add their spacings to DimensionSettings.BUILTIN_OVERWORLD instead of the NOISE_GENERATOR_SETTINGS loop below but
         * that field only applies for the default overworld and won't add to other worldtypes or dimensions (like amplified or Nether).
         * So yeah, don't do DimensionSettings.BUILTIN_OVERWORLD. Use the NOISE_GENERATOR_SETTINGS loop below instead if you must.
         */
        WorldGenRegistries.NOISE_GENERATOR_SETTINGS.entrySet().forEach(settings -> {
            Map<Structure<?>, StructureSeparationSettings> structureMap = settings.getValue().structureSettings().structureConfig();

            /*
             * Pre-caution in case a mod makes the structure map immutable like datapacks do.
             * I take no chances myself. You never know what another mods does...
             *
             * structureConfig requires AccessTransformer  (See resources/META-INF/accesstransformer.cfg)
             */
            if(structureMap instanceof ImmutableMap){
                Map<Structure<?>, StructureSeparationSettings> tempMap = new HashMap<>(structureMap);
                tempMap.put(structure, structureSeparationSettings);
                settings.getValue().structureSettings().structureConfig = tempMap;
            }
            else{
                structureMap.put(structure, structureSeparationSettings);
            }
        });
    }
}
