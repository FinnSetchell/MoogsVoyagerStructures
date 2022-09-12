package com.finndog.mvs;

import com.finndog.mvs.misc.structurepiececounter.StructurePieceCountsManager;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("mvs")
public class MVSMain {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "mvs";

    public static StructurePieceCountsManager structurePieceCountsManager = new StructurePieceCountsManager();

    public MVSMain() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        MVSStructures.DEFERRED_REGISTRY_STRUCTURE.register(modEventBus);
    }
}
