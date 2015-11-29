package io.github.androidfred.positioncalculator.oo;

import java.math.BigDecimal;

public class PricePerUnit implements NotNull, PositiveSignum {

    private BigDecimal pricePerUnit;

    private PricePerUnit() {
    }

    public PricePerUnit(final BigDecimal pricePerUnit) {
        notNull(pricePerUnit);
        positiveSignum(pricePerUnit);
        this.pricePerUnit = pricePerUnit;
    }

    public BigDecimal getPricePerUnit() {
        return pricePerUnit;
    }

    @Override
    public void notNull(final BigDecimal notNull) {
        if (notNull == null) {
            throw new IllegalArgumentException("pricePerUnit must not be null");
        }
    }

    @Override
    public void positiveSignum(final BigDecimal positiveSignum) {
        if (!(positiveSignum.signum() > 0)) {
            throw new IllegalArgumentException("pricePerUnit must have positive signum");
        }
    }
}
