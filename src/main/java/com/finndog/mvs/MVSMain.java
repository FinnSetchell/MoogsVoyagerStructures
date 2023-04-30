package com.finndog.mvs;

import com.finndog.mvs.events.RegisterVillagerTradesEvent;
import com.finndog.mvs.events.RegisterWanderingTradesEvent;
import com.finndog.mvs.events.lifecycle.RegisterReloadListenerEvent;
import com.finndog.mvs.events.lifecycle.ServerGoingToStartEvent;
import com.finndog.mvs.events.lifecycle.ServerGoingToStopEvent;
import com.finndog.mvs.events.lifecycle.SetupEvent;
import com.finndog.mvs.misc.pooladditions.PoolAdditionMerger;
import com.finndog.mvs.misc.structurepiececounter.StructurePieceCountsManager;
import com.finndog.mvs.modinit.*;
import com.finndog.mvs.utils.AsyncLocator;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.packs.PackType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MVSMain implements ModInitializer {

    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "mvs";

    public static void init() {
        MVSTags.initTags();

        MVSStructures.STRUCTURE_TYPE.init();
        MVSPlacements.PLACEMENT_MODIFIER.init();
        MVSProcessors.STRUCTURE_PROCESSOR.init();
        MVSStructurePieces.STRUCTURE_PIECE.init();
        MVSStructurePieces.STRUCTURE_POOL_ELEMENT.init();
        MVSStructurePlacementType.STRUCTURE_PLACEMENT_TYPE.init();
        MVSConditionsRegistry.MVS_JSON_CONDITIONS_REGISTRY.init();

        SetupEvent.EVENT.addListener(MVSMain::setup);
        RegisterReloadListenerEvent.EVENT.addListener(MVSMain::registerDatapackListener);
        ServerGoingToStartEvent.EVENT.addListener(MVSMain::serverAboutToStart);
        ServerGoingToStopEvent.EVENT.addListener(MVSMain::onServerStopping);
        RegisterVillagerTradesEvent.EVENT.addListener(MVSMain::onAddVillagerTrades);
        RegisterWanderingTradesEvent.EVENT.addListener(MVSMain::onWanderingTrades);
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

    private static void onAddVillagerTrades(final RegisterVillagerTradesEvent event) {
    }

    private static void onWanderingTrades(final RegisterWanderingTradesEvent event) {
    }

    public static void registerDatapackListener(final RegisterReloadListenerEvent event) {
        event.register(new ResourceLocation(MVSMain.MODID, "mvs_pieces_spawn_counts"), StructurePieceCountsManager.STRUCTURE_PIECE_COUNTS_MANAGER);
    }
}
