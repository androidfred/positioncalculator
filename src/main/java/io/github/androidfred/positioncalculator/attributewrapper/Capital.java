package io.github.androidfred.positioncalculator.attributewrapper;

import io.github.androidfred.positioncalculator.NotNull;
import io.github.androidfred.positioncalculator.PositiveSignum;

import java.math.BigDecimal;

public class Capital implements NotNull, PositiveSignum {

    private BigDecimal capital;

    private Capital() {
    }

    public Capital(final BigDecimal capital) {
        notNull(capital);
        positiveSignum(capital);
        this.capital = capital;
    }

    public BigDecimal getCapital() {
        return capital;
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
