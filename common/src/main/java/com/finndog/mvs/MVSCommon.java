package com.finndog.mvs;

import com.finndog.mvs.events.lifecycle.RegisterReloadListenerEvent;
import com.finndog.mvs.events.lifecycle.ServerGoingToStartEvent;
import com.finndog.mvs.events.lifecycle.ServerGoingToStopEvent;
import com.finndog.mvs.events.lifecycle.SetupEvent;
import com.finndog.mvs.misc.pooladditions.PoolAdditionMerger;
import com.finndog.mvs.modinit.MVSPlacements;
import com.finndog.mvs.modinit.MVSProcessors;
import com.finndog.mvs.modinit.MVSStructurePieces;
import com.finndog.mvs.modinit.MVSStructurePlacementType;
import com.finndog.mvs.modinit.MVSStructures;
import com.finndog.mvs.modinit.MVSTags;
import com.finndog.mvs.utils.AsyncLocator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class MVSCommon {
    public static final String MODID = "mvs";
    public static final Logger LOGGER = LogManager.getLogger();

    public static void init() {
        MVSTags.initTags();

        MVSStructures.STRUCTURE_TYPE.init();
        MVSPlacements.PLACEMENT_MODIFIER.init();
        MVSProcessors.STRUCTURE_PROCESSOR.init();
        MVSStructurePieces.STRUCTURE_PIECE.init();
        MVSStructurePieces.STRUCTURE_POOL_ELEMENT.init();
        MVSStructurePlacementType.STRUCTURE_PLACEMENT_TYPE.init();

        SetupEvent.EVENT.addListener(MVSCommon::setup);
        RegisterReloadListenerEvent.EVENT.addListener(MVSCommon::registerDatapackListener);
        ServerGoingToStartEvent.EVENT.addListener(MVSCommon::serverAboutToStart);
        ServerGoingToStopEvent.EVENT.addListener(MVSCommon::onServerStopping);
    }

    private static void setup(final SetupEvent event) {
    }

    private static void serverAboutToStart(final ServerGoingToStartEvent event) {
        PoolAdditionMerger.mergeAdditionPools(event);

        AsyncLocator.handleServerAboutToStartEvent();
    }

    private static void onServerStopping(final ServerGoingToStopEvent event) {
        AsyncLocator.handleServerStoppingEvent();
    }

    public static void registerDatapackListener(final RegisterReloadListenerEvent event) {
    }
}
