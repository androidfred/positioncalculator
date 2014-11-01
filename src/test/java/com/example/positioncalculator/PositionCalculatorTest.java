package com.example.positioncalculator;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class PositionCalculatorTest {

    @Test(expected = IllegalArgumentException.class)
    public void arguments_must_not_be_null() throws Exception {
        PositionCalculator.calculatePosition(null, null, null, null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void arguments_must_not_be_zero() throws Exception {
        PositionCalculator.calculatePosition(new BigDecimal(0), new BigDecimal(0), new BigDecimal(0), new BigDecimal(0), Direction.LONG);
    }

    @Test(expected = IllegalArgumentException.class)
    public void arguments_must_not_be_negative() throws Exception {
        PositionCalculator.calculatePosition(new BigDecimal(-1), new BigDecimal(-1), new BigDecimal(-1), new BigDecimal(-1), Direction.LONG);
    }

    @Test(expected = IllegalArgumentException.class)
    public void long_price_must_be_higher_than_stop_loss_price() throws Exception {
        PositionCalculator.calculatePosition(new BigDecimal(10000), new BigDecimal(2), new BigDecimal(25), new BigDecimal(26), Direction.LONG);
    }

    @Test(expected = IllegalArgumentException.class)
    public void short_stop_loss_price_must_be_higher_than_price() throws Exception {
        PositionCalculator.calculatePosition(new BigDecimal(10000), new BigDecimal(2), new BigDecimal(26), new BigDecimal(25), Direction.SHORT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void risk_in_percent_must_be_less_than_100() throws Exception {
        PositionCalculator.calculatePosition(new BigDecimal(10000), new BigDecimal(100), new BigDecimal(25), new BigDecimal(24), Direction.LONG);
    }

    @Test
    public void long_no_decimals() throws Exception {
        Assert.assertEquals(new BigDecimal(200), PositionCalculator.calculatePosition(new BigDecimal(10000), new BigDecimal(2), new BigDecimal(25), new BigDecimal(24), Direction.LONG));
    }

    @Test
    public void long_decimals() throws Exception {
        Assert.assertEquals(new BigDecimal(90), PositionCalculator.calculatePosition(new BigDecimal(9999), new BigDecimal(2), new BigDecimal(19.5), new BigDecimal(17.3), Direction.LONG));
    }

    @Test
    public void short_no_decimals() throws Exception {
        Assert.assertEquals(new BigDecimal(200), PositionCalculator.calculatePosition(new BigDecimal(10000), new BigDecimal(2), new BigDecimal(24), new BigDecimal(25), Direction.SHORT));
    }

    @Test
    public void short_decimals() throws Exception {
        Assert.assertEquals(new BigDecimal(90), PositionCalculator.calculatePosition(new BigDecimal(9999), new BigDecimal(2), new BigDecimal(17.3), new BigDecimal(19.5), Direction.SHORT));
    }

    @Test
    public void capital_must_be_higher_than_answer_multiplied_by_price() throws Exception {
        Assert.assertEquals(new BigDecimal(0), PositionCalculator.calculatePosition(new BigDecimal(10000), new BigDecimal(2), new BigDecimal(1000000), new BigDecimal(999999), Direction.LONG));
    }

}