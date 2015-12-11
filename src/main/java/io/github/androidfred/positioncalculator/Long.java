package io.github.androidfred.positioncalculator;

import java.math.BigDecimal;

public class Long extends Position {
    public Long(final Capital capital,
                final TolerableRiskInPercentOfCapital tolerableRiskInPercentOfCapital,
                final PricePerUnit pricePerUnit,
                final StopLossPricePerUnit stopLossPricePerUnit) {
        super(capital, tolerableRiskInPercentOfCapital, pricePerUnit, stopLossPricePerUnit);
        if (pricePerUnit.provide().compareTo(stopLossPricePerUnit.provide()) != 1) {
            throw new IllegalArgumentException("price must be higher than stopLossPrice");
        }
    }

    @Override
    public final BigDecimal getStopLossPerUnitLoss() {
        return getPricePerUnit().provide().subtract(getStopLossPricePerUnit().provide());
    }
}
