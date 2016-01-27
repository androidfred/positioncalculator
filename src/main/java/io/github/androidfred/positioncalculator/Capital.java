package io.github.androidfred.positioncalculator;

import java.math.BigDecimal;

public class Capital<T extends BigDecimal> {

    private T verified;

    private Capital() {
    }

    public Capital(final T verify) {
        this.verified = new PositiveSignum<>(
                new NotNull<>(verify).provide()
        ).provide();
    }

    public final T provide() {
        return this.verified;
    }
}
