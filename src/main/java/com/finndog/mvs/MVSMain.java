package com.finndog.mvs;

import com.finndog.mvs.modinit.MVSStructurePlacementType;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MVSMain implements ModInitializer {
//1.19.1
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "mvs";

    @Override
    public void onInitialize() {

        MVSStructurePlacementType.registerStructurePlacementTypes();
        MVSStructures.registerStructureFeatures();
    }
}