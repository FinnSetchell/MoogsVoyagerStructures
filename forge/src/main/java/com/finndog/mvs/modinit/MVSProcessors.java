package com.finndog.mvs.modinit;

import com.finndog.mvs.MVSMain;
import com.finndog.mvs.world.processors.WaterloggingFixProcessor;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public final class MVSProcessors {
    public static final DeferredRegister<StructureProcessorType<?>> STRUCTURE_PROCESSOR = DeferredRegister.create(Registries.STRUCTURE_PROCESSOR, MVSMain.MODID);

    public static final RegistryObject<StructureProcessorType<WaterloggingFixProcessor>> WATERLOGGING_FIX_PROCESSOR = STRUCTURE_PROCESSOR.register("waterlogging_fix_processor", () -> () -> WaterloggingFixProcessor.CODEC);
    public static final RegistryObject<StructureProcessorType<CloseOffFluidSourcesProcessor>> CLOSE_OFF_FLUID_SOURCES_PROCESSOR = STRUCTURE_PROCESSOR.register("close_off_fluid_sources_processor", () -> () -> CloseOffFluidSourcesProcessor.CODEC);
}