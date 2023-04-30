package com.finndog.mvs.modinit;

import com.finndog.mvs.MVSMain;
import com.finndog.mvs.modinit.registry.RegistryEntry;
import com.finndog.mvs.modinit.registry.ResourcefulRegistries;
import com.finndog.mvs.modinit.registry.ResourcefulRegistry;
import com.finndog.mvs.world.processors.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;

public final class MVSProcessors {
    public static final ResourcefulRegistry<StructureProcessorType<?>> STRUCTURE_PROCESSOR = ResourcefulRegistries.create(BuiltInRegistries.STRUCTURE_PROCESSOR, MVSMain.MODID);

    public static final RegistryEntry<StructureProcessorType<CloseOffFluidSourcesProcessor>> CLOSE_OFF_FLUID_SOURCES_PROCESSOR = STRUCTURE_PROCESSOR.register("close_off_fluid_sources_processor", () -> () -> CloseOffFluidSourcesProcessor.CODEC);
    public static final RegistryEntry<StructureProcessorType<WaterloggingFixProcessor>> WATERLOGGING_FIX_PROCESSOR = STRUCTURE_PROCESSOR.register("waterlogging_fix_processor", () -> () -> WaterloggingFixProcessor.CODEC);
}