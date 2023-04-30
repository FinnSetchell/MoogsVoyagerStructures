package com.finndog.mvs;

import com.finndog.mvs.misc.pooladditions.PoolAdditionMerger;
import com.finndog.mvs.misc.structurepiececounter.StructurePieceCountsManager;
import com.finndog.mvs.modinit.MVSPlacements;
import com.finndog.mvs.modinit.MVSProcessors;
import com.finndog.mvs.modinit.MVSStructurePlacementType;
import com.finndog.mvs.modinit.MVSTags;
import com.finndog.mvs.utils.AsyncLocator;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.packs.PackType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MVSMain implements ModInitializer {

    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "mvs";

    public static StructurePieceCountsManager structurePieceCountsManager = new StructurePieceCountsManager();

    @Override
    public void onInitialize() {

        MVSTags.initTags();
        MVSPlacements.registerPlacements();
        MVSProcessors.registerProcessors();
        MVSStructures.registerStructures();
        MVSStructurePlacementType.registerStructurePlacementTypes();

        PoolAdditionMerger.mergeAdditionPools();
        ResourceManagerHelper.get(PackType.SERVER_DATA).registerReloadListener(MVSMain.structurePieceCountsManager);

        ServerLifecycleEvents.SERVER_STARTING.register((MinecraftServer s) -> AsyncLocator.handleServerAboutToStartEvent());
        ServerLifecycleEvents.SERVER_STOPPING.register((MinecraftServer s) -> AsyncLocator.handleServerStoppingEvent());
    }
}