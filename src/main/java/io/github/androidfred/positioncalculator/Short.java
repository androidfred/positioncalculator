package io.github.androidfred.positioncalculator;

import java.math.BigDecimal;

public class Short extends Position {

    public Short(final Capital capital,
                 final TolerableRiskInPercentOfCapital tolerableRiskInPercentOfCapital,
                 final PricePerUnit pricePerUnit,
                 final StopLossPricePerUnit stopLossPricePerUnit) {
        super(capital, tolerableRiskInPercentOfCapital, pricePerUnit, stopLossPricePerUnit);
        if (stopLossPricePerUnit.provide().compareTo(pricePerUnit.provide()) != 1) {
            throw new IllegalArgumentException("stopLossPrice must be higher than price");
        }
    }

    @Override
    public final BigDecimal getStopLossPerUnitLoss() {
        return getStopLossPricePerUnit().provide().subtract(getPricePerUnit().provide());
    }
}
