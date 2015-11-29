package io.github.androidfred.positioncalculator.oo;

import java.math.BigDecimal;

public class Long extends Position {

    public Long(Capital capital, TolerableRiskInPercentOfCapital tolerableRiskInPercentOfCapital, PricePerUnit pricePerUnit, StopLossPricePerUnit stopLossPricePerUnit) {
        super(capital, tolerableRiskInPercentOfCapital, pricePerUnit, stopLossPricePerUnit);
        if (pricePerUnit.getPricePerUnit().compareTo(stopLossPricePerUnit.getStopLossPricePerUnit()) != 1) {
            throw new IllegalArgumentException("price must be higher than stopLossPrice");
        }
    }

    @Override
    public final BigDecimal getStopLossPerUnitLoss() {
        return pricePerUnit.getPricePerUnit().subtract(stopLossPricePerUnit.getStopLossPricePerUnit());
    }
}
