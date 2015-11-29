package io.github.androidfred.positioncalculator.attributewrapper;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class PositionTest {
    @Test(expected = IllegalArgumentException.class)
    public void arguments_null_IAE() throws Exception {
        new Long(null, null, null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void arguments_0_IAE() throws Exception {
        BigDecimal zero = new BigDecimal(0);
        new Long(new Capital(zero),
                new TolerableRiskInPercentOfCapital(zero),
                new PricePerUnit(zero),
                new StopLossPricePerUnit(zero));
    }

    @Test(expected = IllegalArgumentException.class)
    public void arguments_negative_IAE() throws Exception {
        BigDecimal negative = new BigDecimal(-1);
        new Long(new Capital(negative),
                new TolerableRiskInPercentOfCapital(negative),
                new PricePerUnit(negative),
                new StopLossPricePerUnit(negative));
    }

    @Test(expected = IllegalArgumentException.class)
    public void tolerableRiskInPercentOfCapitalPerTrade_100OrOver_IAE() throws Exception {
        new Long(new Capital(new BigDecimal(10000)),
                new TolerableRiskInPercentOfCapital(new BigDecimal(100)),
                new PricePerUnit(new BigDecimal(25)),
                new StopLossPricePerUnit(new BigDecimal(24)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void longPricePerUnit_lowerThanStopLoss_IAE() throws Exception {
        new Long(new Capital(new BigDecimal(10000)),
                new TolerableRiskInPercentOfCapital(new BigDecimal(2)),
                new PricePerUnit(new BigDecimal(25)),
                new StopLossPricePerUnit(new BigDecimal(26)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shortPricePerUnit_lowerThanStopLoss_IAE() throws Exception {
        new Short(new Capital(new BigDecimal(10000)),
                new TolerableRiskInPercentOfCapital(new BigDecimal(2)),
                new PricePerUnit(new BigDecimal(26)),
                new StopLossPricePerUnit(new BigDecimal(25)));
    }

    @Test
    public void long_noDecimals_correct() throws Exception {
        Position position = new Long(new Capital(new BigDecimal(10000)),
                new TolerableRiskInPercentOfCapital(new BigDecimal(2)),
                new PricePerUnit(new BigDecimal(25)),
                new StopLossPricePerUnit(new BigDecimal(24)));
        Assert.assertEquals(new BigDecimal(200), position.getUnitsToBuy());
    }

    @Test
    public void long_decimals_correct() throws Exception {
        Position position = new Long(new Capital(new BigDecimal(9999)),
                new TolerableRiskInPercentOfCapital(new BigDecimal(2)),
                new PricePerUnit(new BigDecimal(19.5)),
                new StopLossPricePerUnit(new BigDecimal(17.3)));
        Assert.assertEquals(new BigDecimal(90), position.getUnitsToBuy());
    }

    @Test
    public void short_noDecimals_correct() throws Exception {
        Position position = new Short(new Capital(new BigDecimal(10000)),
                new TolerableRiskInPercentOfCapital(new BigDecimal(2)),
                new PricePerUnit(new BigDecimal(24)),
                new StopLossPricePerUnit(new BigDecimal(25)));
        Assert.assertEquals(new BigDecimal(200), position.getUnitsToBuy());
    }

    @Test
    public void short_decimals_correct() throws Exception {
        Position position = new Short(new Capital(new BigDecimal(9999)),
                new TolerableRiskInPercentOfCapital(new BigDecimal(2)),
                new PricePerUnit(new BigDecimal(17.3)),
                new StopLossPricePerUnit(new BigDecimal(19.5)));
        Assert.assertEquals(new BigDecimal(90), position.getUnitsToBuy());
    }

    @Test
    public void total_higherThanCapital_noUnitsToBuy() throws Exception {
        Position position = new Long(new Capital(new BigDecimal(10000)),
                new TolerableRiskInPercentOfCapital(new BigDecimal(2)),
                new PricePerUnit(new BigDecimal(1000000)),
                new StopLossPricePerUnit(new BigDecimal(999999)));
        Assert.assertEquals(new BigDecimal(0), position.getUnitsToBuy());
    }

    @Test
    public void totalTolerableRiskPerTrade() throws Exception {
        Position position = new Long(new Capital(new BigDecimal(10000)),
                new TolerableRiskInPercentOfCapital(new BigDecimal(2)),
                new PricePerUnit(new BigDecimal(25)),
                new StopLossPricePerUnit(new BigDecimal(24)));
        BigDecimal expected = new BigDecimal(200);
        int result = expected.compareTo(position.getTotalTolerableRiskPerTrade());
        Assert.assertEquals(0, result);
    }

    @Test
    public void stopLossPerUnitLoss() throws Exception {
        Position position = new Long(new Capital(new BigDecimal(10000)),
                new TolerableRiskInPercentOfCapital(new BigDecimal(2)),
                new PricePerUnit(new BigDecimal(25)),
                new StopLossPricePerUnit(new BigDecimal(24)));
        Assert.assertEquals(new BigDecimal(1), position.getStopLossPerUnitLoss());
    }

    @Test
    public void stopLossTotalLoss() throws Exception {
        Position position = new Long(new Capital(new BigDecimal(10000)),
                new TolerableRiskInPercentOfCapital(new BigDecimal(2)),
                new PricePerUnit(new BigDecimal(25)),
                new StopLossPricePerUnit(new BigDecimal(24)));
        Assert.assertEquals(new BigDecimal(200), position.getStopLossTotalLoss());
    }

    @Test
    public void total() throws Exception {
        Position position = new Long(new Capital(new BigDecimal(10000)),
                new TolerableRiskInPercentOfCapital(new BigDecimal(2)),
                new PricePerUnit(new BigDecimal(25)),
                new StopLossPricePerUnit(new BigDecimal(24)));
        Assert.assertEquals(new BigDecimal(5000), position.getTotal());
    }
}