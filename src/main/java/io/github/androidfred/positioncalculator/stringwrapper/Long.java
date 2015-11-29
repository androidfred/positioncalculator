package io.github.androidfred.positioncalculator.stringwrapper;

import java.math.BigDecimal;

public class Long extends Position {

    public Long(Capital capital, TolerableRiskInPercentOfCapital tolerableRiskInPercentOfCapital, PricePerUnit pricePerUnit, StopLossPricePerUnit stopLossPricePerUnit) {
        super(capital, tolerableRiskInPercentOfCapital, pricePerUnit, stopLossPricePerUnit);
        if (pricePerUnit.compareTo(stopLossPricePerUnit) != 1) {
            throw new IllegalArgumentException("price must be higher than stopLossPrice");
        }
    }

    @Override
    public final BigDecimal getStopLossPerUnitLoss() {
        return pricePerUnit.subtract(stopLossPricePerUnit);
    }
}
