package com.finndog.mvs.neoforge;

import com.finndog.mvs.RepurposedStructures;
import com.finndog.mvs.configs.neoforge.RSConfigHandler;
import com.finndog.mvs.events.RegisterVillagerTradesEvent;
import com.finndog.mvs.events.RegisterWanderingTradesEvent;
import com.finndog.mvs.events.lifecycle.RegisterReloadListenerEvent;
import com.finndog.mvs.events.lifecycle.ServerGoingToStartEvent;
import com.finndog.mvs.events.lifecycle.ServerGoingToStopEvent;
import com.finndog.mvs.events.lifecycle.SetupEvent;
import com.finndog.mvs.modinit.neoforge.RSBiomeModifiers;
import com.finndog.mvs.modinit.neoforge.RSGlobalLootModifier;
import com.finndog.mvs.modinit.registry.neoforge.ResourcefulRegistriesImpl;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.AddReloadListenerEvent;
import net.neoforged.neoforge.event.server.ServerAboutToStartEvent;
import net.neoforged.neoforge.event.server.ServerStoppingEvent;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;
import net.neoforged.neoforge.event.village.WandererTradesEvent;


@Mod(RepurposedStructures.MODID)
public class MVSNeoforge {

    public MVSNeoforge(IEventBus modEventBus, ModContainer modContainer) {
        RSConfigHandler.setup(modEventBus, modContainer);
        modEventBus.addListener(EventPriority.NORMAL, ResourcefulRegistriesImpl::onRegisterForgeRegistries);

        RepurposedStructures.init();

        RSBiomeModifiers.BIOME_MODIFIER_SERIALIZERS.register(modEventBus);
        RSGlobalLootModifier.GLM.register(modEventBus);
        RSGlobalLootModifier.LOOT_CONDITION_TYPE.register(modEventBus);

        modEventBus.addListener(RepurposedStructuresNeoforge::onSetup);

        IEventBus eventBus = NeoForge.EVENT_BUS;
        eventBus.addListener(RepurposedStructuresNeoforge::onServerStarting);
        eventBus.addListener(RepurposedStructuresNeoforge::onServerStopping);
        eventBus.addListener(RepurposedStructuresNeoforge::onAddVillagerTrades);
        eventBus.addListener(RepurposedStructuresNeoforge::onWanderingTrades);
        eventBus.addListener(RepurposedStructuresNeoforge::onAddReloadListeners);
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

    private static void onAddVillagerTrades(VillagerTradesEvent event) {
        RegisterVillagerTradesEvent.EVENT.invoke(new RegisterVillagerTradesEvent(event.getType(), (i, listing) -> event.getTrades().get(i.intValue()).add(listing)));
    }

    private static void onWanderingTrades(WandererTradesEvent event) {
        RegisterWanderingTradesEvent.EVENT.invoke(new RegisterWanderingTradesEvent(event.getGenericTrades()::add, event.getRareTrades()::add));
    }

    private static void onAddReloadListeners(AddReloadListenerEvent event) {
        RegisterReloadListenerEvent.EVENT.invoke(new RegisterReloadListenerEvent((id, listener) -> event.addListener(listener)));
    }

}
