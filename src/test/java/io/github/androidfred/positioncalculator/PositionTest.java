package io.github.androidfred.positioncalculator;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class PositionTest {

    @Test(expected = IllegalArgumentException.class)
    public void arguments_must_not_be_null() throws Exception {
        Position.builder()
                .capital(null)
                .tolerableRiskInPercentOfCapitalPerTrade(null)
                .direction(null)
                .pricePerUnit(null)
                .stopLossPricePerUnit(null)
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void arguments_must_not_be_zero() throws Exception {
        BigDecimal zero = new BigDecimal(0);
        Position.builder()
                .capital(zero)
                .tolerableRiskInPercentOfCapitalPerTrade(zero)
                .direction(Direction.LONG)
                .pricePerUnit(zero)
                .stopLossPricePerUnit(zero)
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void arguments_must_not_be_negative() throws Exception {
        BigDecimal negative = new BigDecimal(-1);
        Position.builder()
                .capital(negative)
                .tolerableRiskInPercentOfCapitalPerTrade(negative)
                .direction(Direction.LONG)
                .pricePerUnit(negative)
                .stopLossPricePerUnit(negative)
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void tolerable_risk_in_percent_of_capital_per_trade_must_be_less_than_100() throws Exception {
        Position.builder()
                .capital(new BigDecimal(10000))
                .tolerableRiskInPercentOfCapitalPerTrade(new BigDecimal(100))
                .direction(Direction.LONG)
                .pricePerUnit(new BigDecimal(25))
                .stopLossPricePerUnit(new BigDecimal(24))
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void price_per_unit_must_be_higher_than_stop_loss_price_per_unit_when_long() throws Exception {
        Position.builder()
                .capital(new BigDecimal(10000))
                .tolerableRiskInPercentOfCapitalPerTrade(new BigDecimal(2))
                .direction(Direction.LONG)
                .pricePerUnit(new BigDecimal(25))
                .stopLossPricePerUnit(new BigDecimal(26))
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void stop_loss_price_per_unit_must_be_higher_than_price_per_unit_when_short() throws Exception {
        Position.builder()
                .capital(new BigDecimal(10000))
                .tolerableRiskInPercentOfCapitalPerTrade(new BigDecimal(2))
                .direction(Direction.SHORT)
                .pricePerUnit(new BigDecimal(26))
                .stopLossPricePerUnit(new BigDecimal(25))
                .build();
    }

    @Test
    public void long_no_decimals() throws Exception {
        Position position = Position.builder()
                .capital(new BigDecimal(10000))
                .tolerableRiskInPercentOfCapitalPerTrade(new BigDecimal(2))
                .direction(Direction.LONG)
                .pricePerUnit(new BigDecimal(25))
                .stopLossPricePerUnit(new BigDecimal(24))
                .build();
        Assert.assertEquals(new BigDecimal(200), position.getUnitsToBuy());
    }

    @Test
    public void long_decimals() throws Exception {
        Position position = Position.builder()
                .capital(new BigDecimal(9999))
                .tolerableRiskInPercentOfCapitalPerTrade(new BigDecimal(2))
                .direction(Direction.LONG)
                .pricePerUnit(new BigDecimal(19.5))
                .stopLossPricePerUnit(new BigDecimal(17.3))
                .build();
        Assert.assertEquals(new BigDecimal(90), position.getUnitsToBuy());
    }

    @Test
    public void short_no_decimals() throws Exception {
        Position position = Position.builder()
                .capital(new BigDecimal(10000))
                .tolerableRiskInPercentOfCapitalPerTrade(new BigDecimal(2))
                .direction(Direction.SHORT)
                .pricePerUnit(new BigDecimal(24))
                .stopLossPricePerUnit(new BigDecimal(25))
                .build();
        Assert.assertEquals(new BigDecimal(200), position.getUnitsToBuy());
    }

    @Test
    public void short_decimals() throws Exception {
        Position position = Position.builder()
                .capital(new BigDecimal(9999))
                .tolerableRiskInPercentOfCapitalPerTrade(new BigDecimal(2))
                .direction(Direction.SHORT)
                .pricePerUnit(new BigDecimal(17.3))
                .stopLossPricePerUnit(new BigDecimal(19.5))
                .build();
        Assert.assertEquals(new BigDecimal(90), position.getUnitsToBuy());
    }

    @Test
    public void capital_must_be_higher_than_total() throws Exception {
        Position position = Position.builder()
                .capital(new BigDecimal(10000))
                .tolerableRiskInPercentOfCapitalPerTrade(new BigDecimal(2))
                .direction(Direction.LONG)
                .pricePerUnit(new BigDecimal(1000000))
                .stopLossPricePerUnit(new BigDecimal(999999))
                .build();
        Assert.assertEquals(new BigDecimal(0), position.getUnitsToBuy());
    }

    @Test
    public void total_tolerable_risk_per_trade() throws Exception {
        Position position = Position.builder()
                .capital(new BigDecimal(10000))
                .tolerableRiskInPercentOfCapitalPerTrade(new BigDecimal(2))
                .direction(Direction.LONG)
                .pricePerUnit(new BigDecimal(25))
                .stopLossPricePerUnit(new BigDecimal(24))
                .build();
        BigDecimal expected = new BigDecimal(200);
        int result = expected.compareTo(position.getTotalTolerableRiskPerTrade());
        Assert.assertEquals(0, result);
    }

    @Test
    public void stop_loss_per_unit_loss() throws Exception {
        Position position = Position.builder()
                .capital(new BigDecimal(10000))
                .tolerableRiskInPercentOfCapitalPerTrade(new BigDecimal(2))
                .direction(Direction.LONG)
                .pricePerUnit(new BigDecimal(25))
                .stopLossPricePerUnit(new BigDecimal(24))
                .build();
        Assert.assertEquals(new BigDecimal(1), position.getStopLossPerUnitLoss());
    }

    @Test
    public void stop_loss_total_loss() throws Exception {
        Position position = Position.builder()
                .capital(new BigDecimal(10000))
                .tolerableRiskInPercentOfCapitalPerTrade(new BigDecimal(2))
                .direction(Direction.LONG)
                .pricePerUnit(new BigDecimal(25))
                .stopLossPricePerUnit(new BigDecimal(24))
                .build();
        Assert.assertEquals(new BigDecimal(200), position.getStopLossTotalLoss());
    }

    @Test
    public void total() throws Exception {
        Position position = Position.builder()
                .capital(new BigDecimal(10000))
                .tolerableRiskInPercentOfCapitalPerTrade(new BigDecimal(2))
                .direction(Direction.LONG)
                .pricePerUnit(new BigDecimal(25))
                .stopLossPricePerUnit(new BigDecimal(24))
                .build();
        Assert.assertEquals(new BigDecimal(5000), position.getTotal());
    }
}