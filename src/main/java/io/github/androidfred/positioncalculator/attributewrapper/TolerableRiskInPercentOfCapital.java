package io.github.androidfred.positioncalculator.attributewrapper;

import io.github.androidfred.positioncalculator.Below100;
import io.github.androidfred.positioncalculator.NotNull;
import io.github.androidfred.positioncalculator.PositiveSignum;

import java.math.BigDecimal;

public class TolerableRiskInPercentOfCapital implements NotNull, PositiveSignum, Below100 {

    private BigDecimal tolerableRiskInPercentOfCapital;

    private TolerableRiskInPercentOfCapital() {
    }

    public TolerableRiskInPercentOfCapital(final BigDecimal tolerableRiskInPercentOfCapital) {
        notNull(tolerableRiskInPercentOfCapital);
        positiveSignum(tolerableRiskInPercentOfCapital);
        below100(tolerableRiskInPercentOfCapital);
        this.tolerableRiskInPercentOfCapital = tolerableRiskInPercentOfCapital;
    }

    public BigDecimal getTolerableRiskInPercentOfCapital() {
        return tolerableRiskInPercentOfCapital;
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
