package io.github.androidfred.positioncalculator.stringwrapper;

import java.math.BigDecimal;

public abstract class Position {

    Capital capital;
    TolerableRiskInPercentOfCapital tolerableRiskInPercentOfCapital;
    PricePerUnit pricePerUnit;
    StopLossPricePerUnit stopLossPricePerUnit;

    private Position() {
    }

    public Position(final Capital capital,
                    final TolerableRiskInPercentOfCapital tolerableRiskInPercentOfCapital,
                    final PricePerUnit pricePerUnit,
                    final StopLossPricePerUnit stopLossPricePerUnit) {
        if (capital == null
                || tolerableRiskInPercentOfCapital == null
                || pricePerUnit == null
                || stopLossPricePerUnit == null) {
            throw new IllegalArgumentException("all arguments must be non-null");
        }
        this.capital = capital;
        this.tolerableRiskInPercentOfCapital = tolerableRiskInPercentOfCapital;
        this.pricePerUnit = pricePerUnit;
        this.stopLossPricePerUnit = stopLossPricePerUnit;
    }

    public final BigDecimal getTotalTolerableRiskPerTrade() {
        return capital.multiply(tolerableRiskInPercentOfCapital.divide(new BigDecimal(100)));
    }

    public abstract BigDecimal getStopLossPerUnitLoss();

    public final BigDecimal getStopLossTotalLoss() {
        return getStopLossPerUnitLoss().multiply(getUnitsToBuy());
    }

    public final BigDecimal getUnitsToBuy() {
        BigDecimal result = getTotalTolerableRiskPerTrade().divide(getStopLossPerUnitLoss(), 0, BigDecimal.ROUND_DOWN);
        if (capital.compareTo(result.multiply(pricePerUnit)) != 1) {
            return new BigDecimal(0);
        } else {
            return result;
        }
    }

    public final BigDecimal getTotal() {
        return getUnitsToBuy().multiply(pricePerUnit);
    }

}