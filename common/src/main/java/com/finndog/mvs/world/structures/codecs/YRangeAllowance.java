package com.finndog.mvs.world.structures.codecs;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import java.util.Optional;

public class YRangeAllowance {
    public static final Codec<YRangeAllowance> CODEC = RecordCodecBuilder.create((configInstance) -> configInstance.group(
            Codec.INT.optionalFieldOf("min_y_allowed").forGetter(allowance -> allowance.minYAllowed),
            Codec.INT.optionalFieldOf("max_y_allowed").forGetter(allowance -> allowance.maxYAllowed)
            ).apply(configInstance, YRangeAllowance::new));

    public final Optional<Integer> minYAllowed;
    public final Optional<Integer> maxYAllowed;

    public YRangeAllowance(Optional<Integer> minYAllowed, Optional<Integer> maxYAllowed) {
        this.minYAllowed = minYAllowed;
        this.maxYAllowed = maxYAllowed;
    }
}
