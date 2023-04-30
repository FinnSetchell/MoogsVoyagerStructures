package com.finndog.mvs.events.lifecycle;

import com.finndog.mvs.events.base.EventHandler;
import net.minecraft.core.RegistryAccess;

public record TagsUpdatedEvent(RegistryAccess registryAccess, boolean fromPacket) {

    public static final EventHandler<TagsUpdatedEvent> EVENT = new EventHandler<>();
}
