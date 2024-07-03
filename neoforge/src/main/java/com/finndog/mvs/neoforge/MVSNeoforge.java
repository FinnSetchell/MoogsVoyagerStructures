package com.finndog.mvs.neoforge;

import com.finndog.mvs.MVSCommon;
import com.finndog.mvs.events.lifecycle.RegisterReloadListenerEvent;
import com.finndog.mvs.events.lifecycle.ServerGoingToStartEvent;
import com.finndog.mvs.events.lifecycle.ServerGoingToStopEvent;
import com.finndog.mvs.events.lifecycle.SetupEvent;
import com.finndog.mvs.modinit.registry.neoforge.ResourcefulRegistriesImpl;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.javafmlmod.FMLJavaModLoadingContext;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.AddReloadListenerEvent;
import net.neoforged.neoforge.event.server.ServerAboutToStartEvent;
import net.neoforged.neoforge.event.server.ServerStoppingEvent;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;
import net.neoforged.neoforge.event.village.WandererTradesEvent;


@Mod(MVSCommon.MODID)
public class MVSNeoforge {

    public MVSNeoforge(IEventBus modEventBus) {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(EventPriority.NORMAL, ResourcefulRegistriesImpl::onRegisterForgeRegistries);

        MVSCommon.init();

        IEventBus eventBus = NeoForge.EVENT_BUS;

        modEventBus.addListener(MVSNeoforge::onSetup);
        eventBus.addListener(MVSNeoforge::onServerStarting);
        eventBus.addListener(MVSNeoforge::onServerStopping);
        eventBus.addListener(MVSNeoforge::onAddReloadListeners);
    }

    private static void onSetup(FMLCommonSetupEvent event) {
        SetupEvent.EVENT.invoke(new SetupEvent(event::enqueueWork));
    }

    private static void onServerStarting(ServerAboutToStartEvent event) {
        ServerGoingToStartEvent.EVENT.invoke(new ServerGoingToStartEvent(event.getServer()));
    }

    private static void onServerStopping(ServerStoppingEvent event) {
        ServerGoingToStopEvent.EVENT.invoke(ServerGoingToStopEvent.INSTANCE);
    }

    private static void onAddReloadListeners(AddReloadListenerEvent event) {
        RegisterReloadListenerEvent.EVENT.invoke(new RegisterReloadListenerEvent((id, listener) -> event.addListener(listener)));
    }

}
