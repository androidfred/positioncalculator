package io.github.androidfred.positioncalculator.attributewrapper;

import java.math.BigDecimal;

public class Short extends Position {

    public Short(Capital capital, TolerableRiskInPercentOfCapital tolerableRiskInPercentOfCapital, PricePerUnit pricePerUnit, StopLossPricePerUnit stopLossPricePerUnit) {
        super(capital, tolerableRiskInPercentOfCapital, pricePerUnit, stopLossPricePerUnit);
        if (stopLossPricePerUnit.getStopLossPricePerUnit().compareTo(pricePerUnit.getPricePerUnit()) != 1) {
            throw new IllegalArgumentException("stopLossPrice must be higher than price");
        }
    }

    @Override
    public final BigDecimal getStopLossPerUnitLoss() {
        return stopLossPricePerUnit.getStopLossPricePerUnit().subtract(pricePerUnit.getPricePerUnit());
    }
}
