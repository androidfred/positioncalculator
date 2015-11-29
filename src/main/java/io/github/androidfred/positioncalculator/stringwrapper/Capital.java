package io.github.androidfred.positioncalculator.stringwrapper;

import io.github.androidfred.positioncalculator.NotNull;
import io.github.androidfred.positioncalculator.PositiveSignum;

import java.math.BigDecimal;

public class Capital extends BigDecimal implements NotNull, PositiveSignum {
    public Capital(final String val) {
        super(val);
        notNull(this);
        positiveSignum(this);
    }

    @Override
    public void notNull(final BigDecimal notNull) {
        if (notNull == null) {
            throw new IllegalArgumentException("capital must not be null");
        }
    }

    @Override
    public void positiveSignum(final BigDecimal positiveSignum) {
        if (!(positiveSignum.signum() > 0)) {
            throw new IllegalArgumentException("capital must have positive signum");
        }
    }
}