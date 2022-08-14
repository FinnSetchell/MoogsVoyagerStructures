package com.finndog.mvs;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.FlatGenerationSettings;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

public class MVSConfiguredStructures {
    /**
     * Static instance of our structure so we can reference it and add it to biomes easily.
     */
    public static StructureFeature<?, ?> CONFIGURED_RUN_DOWN_HOUSE = MVSStructures.RUN_DOWN_HOUSE.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<?, ?> CONFIGURED_ABANDONEDLIBRARY = MVSStructures.ABANDONEDLIBRARY.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<?, ?> CONFIGURED_ACACIALOGPILE = MVSStructures.ACACIALOGPILE.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<?, ?> CONFIGURED_AZELEAHOUSE = MVSStructures.AZELEAHOUSE.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<?, ?> CONFIGURED_BARN = MVSStructures.BARN.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<?, ?> CONFIGURED_BASALTSTATUE = MVSStructures.BASALTSTATUE.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<?, ?> CONFIGURED_BEACHBAR = MVSStructures.BEACHBAR.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<?, ?> CONFIGURED_BIGOAKTREE = MVSStructures.BIGOAKTREE.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<?, ?> CONFIGURED_BIRCHLOGPILE = MVSStructures.BIRCHLOGPILE.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<?, ?> CONFIGURED_BIRCHTREE1 = MVSStructures.BIRCHTREE1.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<?, ?> CONFIGURED_BOULDER = MVSStructures.BOULDER.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<?, ?> CONFIGURED_CALCITEHOUSE = MVSStructures.CALCITEHOUSE.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<?, ?> CONFIGURED_CAMPSITE = MVSStructures.CAMPSITE.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<?, ?> CONFIGURED_CART = MVSStructures.CART.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<?, ?> CONFIGURED_CASTLERUINS = MVSStructures.CASTLERUINS.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<?, ?> CONFIGURED_CRYSTAL = MVSStructures.CRYSTAL.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<?, ?> CONFIGURED_DARKOAKLOGPILE = MVSStructures.DARKOAKLOGPILE.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<?, ?> CONFIGURED_DEEPSLATEHOUSE = MVSStructures.DEEPSLATEHOUSE.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<?, ?> CONFIGURED_DESERTPUMP = MVSStructures.DESERTPUMP.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<?, ?> CONFIGURED_DUCK = MVSStructures.DUCK.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<?, ?> CONFIGURED_FLOWERHOLE = MVSStructures.FLOWERHOLE.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<?, ?> CONFIGURED_FOXHUT = MVSStructures.FOXHUT.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<?, ?> CONFIGURED_HORSEPEN = MVSStructures.HORSEPEN.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<?, ?> CONFIGURED_HOUSE = MVSStructures.HOUSE.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<?, ?> CONFIGURED_ISLAND = MVSStructures.ISLAND.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<?, ?> CONFIGURED_JUNGLELOGPILE = MVSStructures.JUNGLELOGPILE.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<?, ?> CONFIGURED_JUNGLETOWER = MVSStructures.JUNGLETOWER.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<?, ?> CONFIGURED_LAMPCHEST = MVSStructures.LAMPCHEST.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<?, ?> CONFIGURED_LECTURNGARDEN = MVSStructures.LECTURNGARDEN.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<?, ?> CONFIGURED_LOGRUIN = MVSStructures.LOGRUIN.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<?, ?> CONFIGURED_MUSHROOMPOND = MVSStructures.MUSHROOMPOND.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<?, ?> CONFIGURED_MUSHROOMSTATUE = MVSStructures.MUSHROOMSTATUE.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<?, ?> CONFIGURED_NETHERDEVIL = MVSStructures.NETHERDEVIL.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<?, ?> CONFIGURED_OAKLOGPILE = MVSStructures.OAKLOGPILE.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<?, ?> CONFIGURED_POND = MVSStructures.POND.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<?, ?> CONFIGURED_RAILWAY = MVSStructures.RAILWAY.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<?, ?> CONFIGURED_RAREWELL = MVSStructures.RAREWELL.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<?, ?> CONFIGURED_RUINEDBEACON = MVSStructures.RUINEDBEACON.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<?, ?> CONFIGURED_SHED = MVSStructures.SHED.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<?, ?> CONFIGURED_SMALLCOPPERWELL = MVSStructures.SMALLCOPPERWELL.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<?, ?> CONFIGURED_SMALLRUIN = MVSStructures.SMALLRUIN.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<?, ?> CONFIGURED_SPRUCELOGPILE = MVSStructures.SPRUCELOGPILE.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<?, ?> CONFIGURED_STONEFOUNTAIN = MVSStructures.STONEFOUNTAIN.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<?, ?> CONFIGURED_STONEPILLARS = MVSStructures.STONEPILLARS.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<?, ?> CONFIGURED_SUNZIGATE = MVSStructures.SUNZIGATE.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<?, ?> CONFIGURED_TALLHOUSE = MVSStructures.TALLHOUSE.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<?, ?> CONFIGURED_TREEMONUMENT = MVSStructures.TREEMONUMENT.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<?, ?> CONFIGURED_VILLAGERSTATUE = MVSStructures.VILLAGERSTATUE.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<?, ?> CONFIGURED_WARPEDHOUSE = MVSStructures.WARPEDHOUSE.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<?, ?> CONFIGURED_WELL = MVSStructures.WELL.get().configured(IFeatureConfig.NONE);

    /**
     * Registers the configured structure which is what gets added to the biomes.
     * Noticed we are not using a forge registry because there is none for configured structures.
     *
     * We can register configured structures at any time before a world is clicked on and made.
     * But the best time to register configured features by code is honestly to do it in FMLCommonSetupEvent.
     */
    public static void registerConfiguredStructures() {
        Registry<StructureFeature<?, ?>> registry = WorldGenRegistries.CONFIGURED_STRUCTURE_FEATURE;
        Registry.register(registry, new ResourceLocation(MVSMain.MODID, "configured_run_down_house"), CONFIGURED_RUN_DOWN_HOUSE);

        Registry.register(registry, new ResourceLocation(MVSMain.MODID, "configured_abandonedlibrary"), CONFIGURED_ABANDONEDLIBRARY);
        Registry.register(registry, new ResourceLocation(MVSMain.MODID, "configured_acacialogpile"), CONFIGURED_ACACIALOGPILE);
        Registry.register(registry, new ResourceLocation(MVSMain.MODID, "configured_azeleahouse"), CONFIGURED_AZELEAHOUSE);
        Registry.register(registry, new ResourceLocation(MVSMain.MODID, "configured_barn"), CONFIGURED_BARN);
        Registry.register(registry, new ResourceLocation(MVSMain.MODID, "configured_basaltstatue"), CONFIGURED_BASALTSTATUE);
        Registry.register(registry, new ResourceLocation(MVSMain.MODID, "configured_beachbar"), CONFIGURED_BEACHBAR);
        Registry.register(registry, new ResourceLocation(MVSMain.MODID, "configured_bigoaktree"), CONFIGURED_BIGOAKTREE);
        Registry.register(registry, new ResourceLocation(MVSMain.MODID, "configured_birchlogpile"), CONFIGURED_BIRCHLOGPILE);
        Registry.register(registry, new ResourceLocation(MVSMain.MODID, "configured_birchtree1"), CONFIGURED_BIRCHTREE1);
        Registry.register(registry, new ResourceLocation(MVSMain.MODID, "configured_boulder"), CONFIGURED_BOULDER);
        Registry.register(registry, new ResourceLocation(MVSMain.MODID, "configured_calcitehouse"), CONFIGURED_CALCITEHOUSE);
        Registry.register(registry, new ResourceLocation(MVSMain.MODID, "configured_campsite"), CONFIGURED_CAMPSITE);
        Registry.register(registry, new ResourceLocation(MVSMain.MODID, "configured_cart"), CONFIGURED_CART);
        Registry.register(registry, new ResourceLocation(MVSMain.MODID, "configured_castleruins"), CONFIGURED_CASTLERUINS);
        Registry.register(registry, new ResourceLocation(MVSMain.MODID, "configured_crystal"), CONFIGURED_CRYSTAL);
        Registry.register(registry, new ResourceLocation(MVSMain.MODID, "configured_darkoaklogpile"), CONFIGURED_DARKOAKLOGPILE);
        Registry.register(registry, new ResourceLocation(MVSMain.MODID, "configured_deepslatehouse"), CONFIGURED_DEEPSLATEHOUSE);
        Registry.register(registry, new ResourceLocation(MVSMain.MODID, "configured_desertpump"), CONFIGURED_DESERTPUMP);
        Registry.register(registry, new ResourceLocation(MVSMain.MODID, "configured_duck"), CONFIGURED_DUCK);
        Registry.register(registry, new ResourceLocation(MVSMain.MODID, "configured_flowerhole"), CONFIGURED_FLOWERHOLE);
        Registry.register(registry, new ResourceLocation(MVSMain.MODID, "configured_foxhut"), CONFIGURED_FOXHUT);
        Registry.register(registry, new ResourceLocation(MVSMain.MODID, "configured_horsepen"), CONFIGURED_HORSEPEN);
        Registry.register(registry, new ResourceLocation(MVSMain.MODID, "configured_house"), CONFIGURED_HOUSE);
        Registry.register(registry, new ResourceLocation(MVSMain.MODID, "configured_island"), CONFIGURED_ISLAND);
        Registry.register(registry, new ResourceLocation(MVSMain.MODID, "configured_junglelogpile"), CONFIGURED_JUNGLELOGPILE);
        Registry.register(registry, new ResourceLocation(MVSMain.MODID, "configured_jungletower"), CONFIGURED_JUNGLETOWER);
        Registry.register(registry, new ResourceLocation(MVSMain.MODID, "configured_lampchest"), CONFIGURED_LAMPCHEST);
        Registry.register(registry, new ResourceLocation(MVSMain.MODID, "configured_lecterngarden"), CONFIGURED_LECTURNGARDEN);
        Registry.register(registry, new ResourceLocation(MVSMain.MODID, "configured_logruin"), CONFIGURED_LOGRUIN);
        Registry.register(registry, new ResourceLocation(MVSMain.MODID, "configured_mushroompond"), CONFIGURED_MUSHROOMPOND);
        Registry.register(registry, new ResourceLocation(MVSMain.MODID, "configured_mushroomstatue"), CONFIGURED_MUSHROOMSTATUE);
        Registry.register(registry, new ResourceLocation(MVSMain.MODID, "configured_netherdevil"), CONFIGURED_NETHERDEVIL);
        Registry.register(registry, new ResourceLocation(MVSMain.MODID, "configured_oaklogpile"), CONFIGURED_OAKLOGPILE);
        Registry.register(registry, new ResourceLocation(MVSMain.MODID, "configured_pond"), CONFIGURED_POND);
        Registry.register(registry, new ResourceLocation(MVSMain.MODID, "configured_railway"), CONFIGURED_RAILWAY);
        Registry.register(registry, new ResourceLocation(MVSMain.MODID, "configured_rarewell"), CONFIGURED_RAREWELL);
        Registry.register(registry, new ResourceLocation(MVSMain.MODID, "configured_ruinedbeacon"), CONFIGURED_RUINEDBEACON);
        Registry.register(registry, new ResourceLocation(MVSMain.MODID, "configured_shed"), CONFIGURED_SHED);
        Registry.register(registry, new ResourceLocation(MVSMain.MODID, "configured_smallcopperwell"), CONFIGURED_SMALLCOPPERWELL);
        Registry.register(registry, new ResourceLocation(MVSMain.MODID, "configured_smallruin"), CONFIGURED_SMALLRUIN);
        Registry.register(registry, new ResourceLocation(MVSMain.MODID, "configured_sprucelogpile"), CONFIGURED_SPRUCELOGPILE);
        Registry.register(registry, new ResourceLocation(MVSMain.MODID, "configured_stonefountain"), CONFIGURED_STONEFOUNTAIN);
        Registry.register(registry, new ResourceLocation(MVSMain.MODID, "configured_stonepillars"), CONFIGURED_STONEPILLARS);
        Registry.register(registry, new ResourceLocation(MVSMain.MODID, "configured_sunzigate"), CONFIGURED_SUNZIGATE);
        Registry.register(registry, new ResourceLocation(MVSMain.MODID, "configured_tallhouse"), CONFIGURED_TALLHOUSE);
        Registry.register(registry, new ResourceLocation(MVSMain.MODID, "configured_treemonument"), CONFIGURED_TREEMONUMENT);
        Registry.register(registry, new ResourceLocation(MVSMain.MODID, "configured_villagerstatue"), CONFIGURED_VILLAGERSTATUE);
        Registry.register(registry, new ResourceLocation(MVSMain.MODID, "configured_warpedhouse"), CONFIGURED_WARPEDHOUSE);
        Registry.register(registry, new ResourceLocation(MVSMain.MODID, "configured_well"), CONFIGURED_WELL);

        /* Ok so, this part may be hard to grasp but basically, just add your structure to this to
        * prevent any sort of crash or issue with other mod's custom ChunkGenerators. If they use
        * FlatGenerationSettings.STRUCTURE_FEATURES in it and you don't add your structure to it, the game
        * could crash later when you attempt to add the StructureSeparationSettings to the dimension.
        *
        * (It would also crash with superflat worldtype if you omit the below line
        * and attempt to add the structure's StructureSeparationSettings to the world)
        *
        * Note: If you want your structure to spawn in superflat, remove the FlatChunkGenerator check
        * in StructureTutorialMain.addDimensionalSpacing and then create a superflat world, exit it,
        * and re-enter it and your structures will be spawning. I could not figure out why it needs
        * the restart but honestly, superflat is really buggy and shouldn't be your main focus in my opinion.
        *
        * Requires AccessTransformer ( see resources/META-INF/accesstransformer.cfg )
        */
        FlatGenerationSettings.STRUCTURE_FEATURES.put(MVSStructures.RUN_DOWN_HOUSE.get(), CONFIGURED_RUN_DOWN_HOUSE);

        FlatGenerationSettings.STRUCTURE_FEATURES.put(MVSStructures.ABANDONEDLIBRARY.get(), CONFIGURED_ABANDONEDLIBRARY);
        FlatGenerationSettings.STRUCTURE_FEATURES.put(MVSStructures.ACACIALOGPILE.get(), CONFIGURED_ACACIALOGPILE);
        FlatGenerationSettings.STRUCTURE_FEATURES.put(MVSStructures.AZELEAHOUSE.get(), CONFIGURED_AZELEAHOUSE);
        FlatGenerationSettings.STRUCTURE_FEATURES.put(MVSStructures.BARN.get(), CONFIGURED_BARN);
        FlatGenerationSettings.STRUCTURE_FEATURES.put(MVSStructures.BASALTSTATUE.get(), CONFIGURED_BASALTSTATUE);
        FlatGenerationSettings.STRUCTURE_FEATURES.put(MVSStructures.BEACHBAR.get(), CONFIGURED_BEACHBAR);
        FlatGenerationSettings.STRUCTURE_FEATURES.put(MVSStructures.BIGOAKTREE.get(), CONFIGURED_BIGOAKTREE);
        FlatGenerationSettings.STRUCTURE_FEATURES.put(MVSStructures.BIRCHLOGPILE.get(), CONFIGURED_BIRCHLOGPILE);
        FlatGenerationSettings.STRUCTURE_FEATURES.put(MVSStructures.BIRCHTREE1.get(), CONFIGURED_BIRCHTREE1);
        FlatGenerationSettings.STRUCTURE_FEATURES.put(MVSStructures.BOULDER.get(), CONFIGURED_BOULDER);
        FlatGenerationSettings.STRUCTURE_FEATURES.put(MVSStructures.CALCITEHOUSE.get(), CONFIGURED_CALCITEHOUSE);
        FlatGenerationSettings.STRUCTURE_FEATURES.put(MVSStructures.CAMPSITE.get(), CONFIGURED_CAMPSITE);
        FlatGenerationSettings.STRUCTURE_FEATURES.put(MVSStructures.CART.get(), CONFIGURED_CART);
        FlatGenerationSettings.STRUCTURE_FEATURES.put(MVSStructures.CASTLERUINS.get(), CONFIGURED_CASTLERUINS);
        FlatGenerationSettings.STRUCTURE_FEATURES.put(MVSStructures.CRYSTAL.get(), CONFIGURED_CRYSTAL);
        FlatGenerationSettings.STRUCTURE_FEATURES.put(MVSStructures.DARKOAKLOGPILE.get(), CONFIGURED_DARKOAKLOGPILE);
        FlatGenerationSettings.STRUCTURE_FEATURES.put(MVSStructures.DEEPSLATEHOUSE.get(), CONFIGURED_DEEPSLATEHOUSE);
        FlatGenerationSettings.STRUCTURE_FEATURES.put(MVSStructures.DESERTPUMP.get(), CONFIGURED_DESERTPUMP);
        FlatGenerationSettings.STRUCTURE_FEATURES.put(MVSStructures.DUCK.get(), CONFIGURED_DUCK);
        FlatGenerationSettings.STRUCTURE_FEATURES.put(MVSStructures.FLOWERHOLE.get(), CONFIGURED_FLOWERHOLE);
        FlatGenerationSettings.STRUCTURE_FEATURES.put(MVSStructures.FOXHUT.get(), CONFIGURED_FOXHUT);
        FlatGenerationSettings.STRUCTURE_FEATURES.put(MVSStructures.HORSEPEN.get(), CONFIGURED_HORSEPEN);
        FlatGenerationSettings.STRUCTURE_FEATURES.put(MVSStructures.HOUSE.get(), CONFIGURED_HOUSE);
        FlatGenerationSettings.STRUCTURE_FEATURES.put(MVSStructures.ISLAND.get(), CONFIGURED_ISLAND);
        FlatGenerationSettings.STRUCTURE_FEATURES.put(MVSStructures.JUNGLELOGPILE.get(), CONFIGURED_JUNGLELOGPILE);
        FlatGenerationSettings.STRUCTURE_FEATURES.put(MVSStructures.JUNGLETOWER.get(), CONFIGURED_JUNGLETOWER);
        FlatGenerationSettings.STRUCTURE_FEATURES.put(MVSStructures.LAMPCHEST.get(), CONFIGURED_LAMPCHEST);
        FlatGenerationSettings.STRUCTURE_FEATURES.put(MVSStructures.LECTURNGARDEN.get(), CONFIGURED_LECTURNGARDEN);
        FlatGenerationSettings.STRUCTURE_FEATURES.put(MVSStructures.LOGRUIN.get(), CONFIGURED_LOGRUIN);
        FlatGenerationSettings.STRUCTURE_FEATURES.put(MVSStructures.MUSHROOMPOND.get(), CONFIGURED_MUSHROOMPOND);
        FlatGenerationSettings.STRUCTURE_FEATURES.put(MVSStructures.MUSHROOMSTATUE.get(), CONFIGURED_MUSHROOMSTATUE);
        FlatGenerationSettings.STRUCTURE_FEATURES.put(MVSStructures.NETHERDEVIL.get(), CONFIGURED_NETHERDEVIL);
        FlatGenerationSettings.STRUCTURE_FEATURES.put(MVSStructures.OAKLOGPILE.get(), CONFIGURED_OAKLOGPILE);
        FlatGenerationSettings.STRUCTURE_FEATURES.put(MVSStructures.POND.get(), CONFIGURED_POND);
        FlatGenerationSettings.STRUCTURE_FEATURES.put(MVSStructures.RAILWAY.get(), CONFIGURED_RAILWAY);
        FlatGenerationSettings.STRUCTURE_FEATURES.put(MVSStructures.RAREWELL.get(), CONFIGURED_RAREWELL);
        FlatGenerationSettings.STRUCTURE_FEATURES.put(MVSStructures.RUINEDBEACON.get(), CONFIGURED_RUINEDBEACON);
        FlatGenerationSettings.STRUCTURE_FEATURES.put(MVSStructures.SHED.get(), CONFIGURED_SHED);
        FlatGenerationSettings.STRUCTURE_FEATURES.put(MVSStructures.SMALLCOPPERWELL.get(), CONFIGURED_SMALLCOPPERWELL);
        FlatGenerationSettings.STRUCTURE_FEATURES.put(MVSStructures.SMALLRUIN.get(), CONFIGURED_SMALLRUIN);
        FlatGenerationSettings.STRUCTURE_FEATURES.put(MVSStructures.SPRUCELOGPILE.get(), CONFIGURED_SPRUCELOGPILE);
        FlatGenerationSettings.STRUCTURE_FEATURES.put(MVSStructures.STONEFOUNTAIN.get(), CONFIGURED_STONEFOUNTAIN);
        FlatGenerationSettings.STRUCTURE_FEATURES.put(MVSStructures.STONEPILLARS.get(), CONFIGURED_STONEPILLARS);
        FlatGenerationSettings.STRUCTURE_FEATURES.put(MVSStructures.SUNZIGATE.get(), CONFIGURED_SUNZIGATE);
        FlatGenerationSettings.STRUCTURE_FEATURES.put(MVSStructures.TALLHOUSE.get(), CONFIGURED_TALLHOUSE);
        FlatGenerationSettings.STRUCTURE_FEATURES.put(MVSStructures.TREEMONUMENT.get(), CONFIGURED_TREEMONUMENT);
        FlatGenerationSettings.STRUCTURE_FEATURES.put(MVSStructures.VILLAGERSTATUE.get(), CONFIGURED_VILLAGERSTATUE);
        FlatGenerationSettings.STRUCTURE_FEATURES.put(MVSStructures.WARPEDHOUSE.get(), CONFIGURED_WARPEDHOUSE);
        FlatGenerationSettings.STRUCTURE_FEATURES.put(MVSStructures.WELL.get(), CONFIGURED_WELL);
    }
}
