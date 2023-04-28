package com.finndog.mvs.modinit;

import com.finndog.mvs.MVSMain;
import com.finndog.mvs.world.predicates.MatterPhaseRuleTest;
import com.finndog.mvs.world.predicates.PieceOriginAxisAlignedLinearPosRuleTest;
import com.finndog.mvs.world.predicates.YValuePosRuleTest;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.structure.templatesystem.PosRuleTestType;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTestType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public final class MVSPredicates {
    public static final DeferredRegister<RuleTestType<?>> RULE_TEST = DeferredRegister.create(Registry.RULE_TEST_REGISTRY, MVSMain.MODID);
    public static final DeferredRegister<PosRuleTestType<?>> POS_RULE_TEST = DeferredRegister.create(Registry.POS_RULE_TEST_REGISTRY, MVSMain.MODID);

    public static final RegistryObject<RuleTestType<MatterPhaseRuleTest>> MATTER_PHASE_RULE_TEST = RULE_TEST.register("matter_phase_rule_test", () -> () -> MatterPhaseRuleTest.CODEC);

    public static final RegistryObject<PosRuleTestType<PieceOriginAxisAlignedLinearPosRuleTest>> PIECE_ORIGIN_AXIS_ALIGNED_LINEAR_POS_RULE_TEST = POS_RULE_TEST.register("piece_origin_axis_aligned_linear_pos_rule_test", () -> () -> PieceOriginAxisAlignedLinearPosRuleTest.CODEC);
    public static final RegistryObject<PosRuleTestType<YValuePosRuleTest>> Y_VALUE_POS_RULE_TEST = POS_RULE_TEST.register("y_value_pos_rule_test", () -> () -> YValuePosRuleTest.CODEC);
}
