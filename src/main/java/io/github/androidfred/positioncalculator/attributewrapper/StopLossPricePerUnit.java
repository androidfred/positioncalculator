package io.github.androidfred.positioncalculator.attributewrapper;

import io.github.androidfred.positioncalculator.NotNull;
import io.github.androidfred.positioncalculator.PositiveSignum;

import java.math.BigDecimal;

public class StopLossPricePerUnit implements NotNull, PositiveSignum {

    private BigDecimal stopLossPricePerUnit;

    private StopLossPricePerUnit() {
    }

    public StopLossPricePerUnit(final BigDecimal stopLossPricePerUnit) {
        notNull(stopLossPricePerUnit);
        positiveSignum(stopLossPricePerUnit);
        this.stopLossPricePerUnit = stopLossPricePerUnit;
    }

    public BigDecimal getStopLossPricePerUnit() {
        return stopLossPricePerUnit;
    }

    @Override
    public void notNull(final BigDecimal notNull) {
        if (notNull == null) {
            throw new IllegalArgumentException("stopLossPricePerUnit must not be null");
        }
    }

    @Override
    public void positiveSignum(final BigDecimal positiveSignum) {
        if (!(positiveSignum.signum() > 0)) {
            throw new IllegalArgumentException("stopLossPricePerUnit must have positive signum");
        }
    }
}
