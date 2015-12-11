package io.github.androidfred.positioncalculator;

import java.math.BigDecimal;

public abstract class Position {

    private PricePerUnit pricePerUnit;
    private StopLossPricePerUnit stopLossPricePerUnit;
    private Capital capital;
    private TolerableRiskInPercentOfCapital tolerableRiskInPercentOfCapital;

    private Position() {
    }

    public Position(final Capital capital,
                    final TolerableRiskInPercentOfCapital tolerableRiskInPercentOfCapital,
                    final PricePerUnit pricePerUnit,
                    final StopLossPricePerUnit stopLossPricePerUnit) {
        this.capital = new NotNull<>(capital).provide();
        this.tolerableRiskInPercentOfCapital = new NotNull<>(tolerableRiskInPercentOfCapital).provide();
        this.pricePerUnit = new NotNull<>(pricePerUnit).provide();
        this.stopLossPricePerUnit = new NotNull<>(stopLossPricePerUnit).provide();
    }

    public final BigDecimal getTotalTolerableRiskPerTrade() {
        return this.capital.provide().multiply(this.tolerableRiskInPercentOfCapital.provide().divide(new BigDecimal(100)));
    }

    public abstract BigDecimal getStopLossPerUnitLoss();

    public final BigDecimal getStopLossTotalLoss() {
        return this.getStopLossPerUnitLoss().multiply(this.getUnitsToBuy());
    }

    public final BigDecimal getUnitsToBuy() {
        final BigDecimal result = this.getTotalTolerableRiskPerTrade().divide(this.getStopLossPerUnitLoss(), 0, BigDecimal.ROUND_DOWN);
        if (this.capital.provide().compareTo(result.multiply(this.pricePerUnit.provide())) != 1) {
            return new BigDecimal(0);
        } else {
            return result;
        }
    }

    public final BigDecimal getTotal() {
        return this.getUnitsToBuy().multiply(this.pricePerUnit.provide());
    }

    protected final PricePerUnit getPricePerUnit() {
        return this.pricePerUnit;
    }

    protected final StopLossPricePerUnit getStopLossPricePerUnit() {
        return this.stopLossPricePerUnit;
    }
}
