package io.github.androidfred.positioncalculator.stringwrapper;

import java.math.BigDecimal;

public class Short extends Position {

    public Short(Capital capital, TolerableRiskInPercentOfCapital tolerableRiskInPercentOfCapital, PricePerUnit pricePerUnit, StopLossPricePerUnit stopLossPricePerUnit) {
        super(capital, tolerableRiskInPercentOfCapital, pricePerUnit, stopLossPricePerUnit);
        if (stopLossPricePerUnit.compareTo(pricePerUnit) != 1) {
            throw new IllegalArgumentException("stopLossPrice must be higher than price");
        }
    }

    @Override
    public final BigDecimal getStopLossPerUnitLoss() {
        return stopLossPricePerUnit.subtract(pricePerUnit);
    }
}
