package com.finndog.mvs;

import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MVSmain implements ModInitializer {
//forge
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "mvs";

    @Override
    public void onInitialize() {

        /*
         * We setup and register our structures here.
         * You should always register your stuff to prevent mod compatibility issue down the line.
         */
        MVSstructures.registerStructureFeatures();
    }
}
