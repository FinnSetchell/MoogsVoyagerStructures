/**
 * A huge thanks to TelepathicGrunt for much of the source code, partially from:
 * https://github.com/TelepathicGrunt/RepurposedStructures and
 * https://github.com/TelepathicGrunt/StructureTutorialMod*/

package com.finndog.mvs;

import com.finndog.mvs.misc.structurepiececounter.StructurePieceCountsManager;
import com.finndog.mvs.utils.AsyncLocator;
import com.finndog.mvs.misc.pooladditions.PoolAdditionMerger;
import com.finndog.mvs.misc.structurepiececounter.JSONConditionsRegistry;
import com.finndog.mvs.misc.structurepiececounter.StructurePieceCountsManager;
import com.finndog.mvs.modinit.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.server.ServerAboutToStartEvent;
import net.minecraftforge.event.server.ServerStoppingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("mvs")
public class MVSMain {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "mvs";

    public static StructurePieceCountsManager structurePieceCountsManager = new StructurePieceCountsManager();

    public MVSMain() {
        MVSTags.initTags();
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        eventBus.addListener(this::setup);
        // Classload and create custom registry. Other mods should add to this custom registry in FMLCommonSetupEvent.
        JSONConditionsRegistry.registerTestJSONCondition();

        // Register the setup method for modloading
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        forgeBus.addListener(this::registerDatapackListener);
        forgeBus.addListener(PoolAdditionMerger::mergeAdditionPools);
        forgeBus.addListener((ServerAboutToStartEvent ignoredEvent) -> AsyncLocator.handleServerAboutToStartEvent());
        forgeBus.addListener((ServerStoppingEvent ignoredEvent) -> AsyncLocator.handleServerStoppingEvent());

        modEventBus.addListener(this::setup);
        MVSPredicates.RULE_TEST.register(modEventBus);
        MVSPredicates.POS_RULE_TEST.register(modEventBus);
        MVSStructures.STRUCTURE_TYPE.register(modEventBus);
        MVSPlacements.PLACEMENT_MODIFIER.register(modEventBus);
        MVSProcessors.STRUCTURE_PROCESSOR.register(modEventBus);
        MVSStructurePieces.STRUCTURE_PIECE.register(modEventBus);
        MVSStructurePieces.STRUCTURE_POOL_ELEMENT.register(modEventBus);
        MVSStructurePlacementType.STRUCTURE_PLACEMENT_TYPE.register(modEventBus);
    }

    public void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
        });
    }

    public void registerDatapackListener(final AddReloadListenerEvent event) {
        event.addListener(MVSMain.structurePieceCountsManager);
    }
}
