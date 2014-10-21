package com.example.positioncalculator;

import java.math.BigDecimal;

public final class PositionCalculator {

    public enum Direction {
      LONG,
      SHORT
    }

    /**
     * Suppress default constructor to prevent instantiation.
     */
    private PositionCalculator() {
        throw new AssertionError();
    }

    /**
     * Fixed percent risk money management calculator. Return value does not take into account slippage or commissions.
     * Expects all arguments to be in the same currency.
     *
     * Example 1:
     * Capital in portfolio is 10 000, trader can risk 2 percent of portfolio per trade = 200 per trade.
     * Price of security is 25, stop loss price 24 = loss if stop loss triggers at desired level is 1 per unit.
     * = trader can buy 200 (200/1) securities.
     *
     * Example 2:
     * Capital in portfolio is 9999, trader can risk 2 percent of portfolio per trade = 199.98 per trade.
     * Price of security is 19.5, stop loss price 17.3 = loss if stop loss triggers at desired level is 2.2 per unit.
     * = trader can buy 90 (199.98/2.2 = 90.9 round down to 90) securities.
     *
     * @param capital capital in portfolio
     * @param riskInPercent how many percent of the capital in the portfolio trader could risk per trade
     * @param price price of security
     * @param stopLossPrice price of security at which stop loss is desired
     * @param direction direction of trade
     * @return amount of security trader could buy
     */

    public static BigDecimal calculatePosition(final BigDecimal capital,
                                               final BigDecimal riskInPercent,
                                               final BigDecimal price,
                                               final BigDecimal stopLossPrice,
                                               final Direction direction){
        basicValidate(capital);
        basicValidate(riskInPercent);
        basicValidate(price);
        basicValidate(stopLossPrice);

        if (riskInPercent.compareTo(new BigDecimal(100)) != -1){
            throw new IllegalArgumentException("riskInPercent must be lower than 100");
        }
        if (direction==null){
            throw new IllegalArgumentException("argument can't be null");
        }

        if (direction.equals(Direction.LONG) && price.compareTo(stopLossPrice) != 1){
            throw new IllegalArgumentException("price must be higher than stopLossPrice");
        }

        if (direction.equals(Direction.SHORT) && stopLossPrice.compareTo(price) != 1){
            throw new IllegalArgumentException("stopLossPrice must be higher than price");
        }

        return riskSumPerTrade(capital, riskInPercent).divide(riskSumPerUnit(price, stopLossPrice, direction), 0, BigDecimal.ROUND_DOWN);
    }

    protected static void basicValidate(final BigDecimal bigDecimal){
        if (bigDecimal == null){
            throw new IllegalArgumentException("argument can't be null");
        }
        if (!(bigDecimal.signum() > 0)){
            throw new IllegalArgumentException("argument must have positive signum");
        }
    }

    protected static BigDecimal riskSumPerUnit(final BigDecimal price, final BigDecimal stopLossPrice, final Direction direction){
        if (direction.equals(Direction.LONG)){
            return price.subtract(stopLossPrice);
        } else {
            return stopLossPrice.subtract(price);
        }
    }

    protected static BigDecimal riskSumPerTrade(final BigDecimal capital, final BigDecimal riskInPercent){
        return capital.multiply(riskInPercent.divide(new BigDecimal(100)));
    }
}
