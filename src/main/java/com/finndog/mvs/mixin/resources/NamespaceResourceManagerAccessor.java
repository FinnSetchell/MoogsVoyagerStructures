package com.finndog.mvs.mixin.resources;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackResources;
import net.minecraft.server.packs.resources.FallbackResourceManager;
import net.minecraft.server.packs.resources.Resource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Mixin(FallbackResourceManager.class)
public interface NamespaceResourceManagerAccessor {
    @Accessor("fallbacks")
    List<FallbackResourceManager.PackEntry> mvs_getFallbacks();

    @Invoker("createResourceGetter")
    Resource.IoSupplier<InputStream> mvs_callCreateResourceGetter(ResourceLocation id, PackResources pack) throws IOException;
}
