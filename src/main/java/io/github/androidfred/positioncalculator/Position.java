package io.github.androidfred.positioncalculator;

import java.io.Serializable;
import java.math.BigDecimal;

public class Position implements Serializable {

    private BigDecimal capital;
    private BigDecimal tolerableRiskInPercentOfCapitalPerTrade;
    private Direction direction;
    private BigDecimal pricePerUnit;
    private BigDecimal stopLossPricePerUnit;

    private Position(){}

    public final BigDecimal getTotalTolerableRiskPerTrade() {
        return capital.multiply(tolerableRiskInPercentOfCapitalPerTrade.divide(new BigDecimal(100)));
    }

    public final BigDecimal getStopLossPerUnitLoss() {
        if (direction.equals(Direction.LONG)){
            return pricePerUnit.subtract(stopLossPricePerUnit);
        } else {
            return stopLossPricePerUnit.subtract(pricePerUnit);
        }
    }

    public final BigDecimal getStopLossTotalLoss() {
        return getStopLossPerUnitLoss().multiply(getUnitsToBuy());
    }

    public final BigDecimal getUnitsToBuy() {
        BigDecimal result = getTotalTolerableRiskPerTrade().divide(getStopLossPerUnitLoss(), 0, BigDecimal.ROUND_DOWN);
        if (capital.compareTo(result.multiply(pricePerUnit)) != 1){
            return new BigDecimal(0);
        } else {
            return result;
        }
    }

    public final BigDecimal getTotal() {
        return getUnitsToBuy().multiply(pricePerUnit);
    }

    public static ICapital builder(){
        return new Builder();
    }

    public interface ICapital {
        ITolerableRiskInPercentOfCapitalPerTrade capital(final BigDecimal capital);
    }

    public interface ITolerableRiskInPercentOfCapitalPerTrade {
        IDirection tolerableRiskInPercentOfCapitalPerTrade(final BigDecimal tolerableRiskInPercentOfCapitalPerTrade);
    }

    public interface IDirection {
        IPricePerUnit direction(final Direction direction);
    }

    public interface IPricePerUnit {
        IStopLossPricePerUnit pricePerUnit(final BigDecimal pricePerUnit);
    }

    public interface IStopLossPricePerUnit {
        IBuild stopLossPricePerUnit(final BigDecimal stopLossPricePerUnit);
    }

    public interface IBuild {
        Position build();
    }

    private static class Builder implements ICapital, ITolerableRiskInPercentOfCapitalPerTrade, IDirection, IPricePerUnit, IStopLossPricePerUnit, IBuild {
        private final Position instance = new Position();

        @Override
        public Position build() {
            return instance;
        }

        @Override
        public ITolerableRiskInPercentOfCapitalPerTrade capital(final BigDecimal capital) {
            basicValidate(capital);
            instance.capital = capital;
            return this;
        }

        @Override
        public IDirection tolerableRiskInPercentOfCapitalPerTrade(final BigDecimal tolerableRiskInPercentOfCapitalPerTrade) {
            basicValidate(tolerableRiskInPercentOfCapitalPerTrade);
            if (tolerableRiskInPercentOfCapitalPerTrade.compareTo(new BigDecimal(100)) != -1) {
                throw new IllegalArgumentException("riskInPercent must be lower than 100");
            }
            instance.tolerableRiskInPercentOfCapitalPerTrade = tolerableRiskInPercentOfCapitalPerTrade;
            return this;
        }

        @Override
        public IPricePerUnit direction(final Direction direction) {
            if (direction==null) {
                throw new IllegalArgumentException("argument can't be null");
            }
            instance.direction = direction;
            return this;
        }

        @Override
        public IStopLossPricePerUnit pricePerUnit(final BigDecimal pricePerUnit) {
            basicValidate(pricePerUnit);
            instance.pricePerUnit = pricePerUnit;
            return this;
        }

        @Override
        public IBuild stopLossPricePerUnit(final BigDecimal stopLossPricePerUnit) {
            basicValidate(stopLossPricePerUnit);
            if (instance.direction.equals(Direction.LONG) && instance.pricePerUnit.compareTo(stopLossPricePerUnit) != 1) {
                throw new IllegalArgumentException("price must be higher than stopLossPrice");
            }

            if (instance.direction.equals(Direction.SHORT) && stopLossPricePerUnit.compareTo(instance.pricePerUnit) != 1) {
                throw new IllegalArgumentException("stopLossPrice must be higher than price");
            }
            instance.stopLossPricePerUnit = stopLossPricePerUnit;
            return this;
        }
    }

    protected static void basicValidate(final BigDecimal bigDecimal) {
        if (bigDecimal == null) {
            throw new IllegalArgumentException("argument can't be null");
        }
        if (!(bigDecimal.signum() > 0)) {
            throw new IllegalArgumentException("argument must have positive signum");
        }
    }
}