package io.github.androidfred.positioncalculator;

import java.math.BigDecimal;

public class TolerableRiskInPercentOfCapital {

    private BigDecimal verified;

    public TolerableRiskInPercentOfCapital() {
    }

    public TolerableRiskInPercentOfCapital(final BigDecimal verify) {
        this.verified = new Max(
                new PositiveSignum(
                        new NotNull<>(verify).provide()
                ).provide(),
                new BigDecimal(100)
        ).provide();
    }

    public final BigDecimal provide() {
        return this.verified;
    }
}
