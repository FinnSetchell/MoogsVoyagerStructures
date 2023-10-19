package com.finndog.mvs.modinit;

import com.finndog.mvs.MVSCommon;
import com.finndog.mvs.modinit.registry.RegistryEntry;
import com.finndog.mvs.modinit.registry.ResourcefulRegistries;
import com.finndog.mvs.modinit.registry.ResourcefulRegistry;
import com.finndog.mvs.world.processors.WaterloggingFixProcessor;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;

public final class MVSProcessors {
    public static final ResourcefulRegistry<StructureProcessorType<?>> STRUCTURE_PROCESSOR = ResourcefulRegistries.create(Registry.STRUCTURE_PROCESSOR, MVSCommon.MODID);

    public static final RegistryEntry<StructureProcessorType<WaterloggingFixProcessor>> WATERLOGGING_FIX_PROCESSOR = STRUCTURE_PROCESSOR.register("waterlogging_fix_processor", () -> () -> WaterloggingFixProcessor.CODEC);
}
