package io.github.androidfred.positioncalculator.stringwrapper;

import io.github.androidfred.positioncalculator.Below100;
import io.github.androidfred.positioncalculator.NotNull;
import io.github.androidfred.positioncalculator.PositiveSignum;

import java.math.BigDecimal;

public class TolerableRiskInPercentOfCapital extends BigDecimal implements NotNull, PositiveSignum, Below100 {
    public TolerableRiskInPercentOfCapital(final String val) {
        super(val);
        notNull(this);
        positiveSignum(this);
        below100(this);
    }

    @Override
    public void notNull(final BigDecimal notNull) {
        if (notNull == null) {
            throw new IllegalArgumentException("tolerableRiskInPercentOfCapital must not be null");
        }
    }

    @Override
    public void positiveSignum(final BigDecimal positiveSignum) {
        if (!(positiveSignum.signum() > 0)) {
            throw new IllegalArgumentException("tolerableRiskInPercentOfCapital must have positive signum");
        }
    }

    @Override
    public void below100(final BigDecimal below100) {
        if (below100.compareTo(new BigDecimal(100)) != -1) {
            throw new IllegalArgumentException("tolerableRiskInPercentOfCapital must be lower than 100");
        }
    }
}