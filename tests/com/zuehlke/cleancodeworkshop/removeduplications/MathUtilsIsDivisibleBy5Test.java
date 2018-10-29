package com.zuehlke.cleancodeworkshop.removeduplications;

import com.zuehlke.cleancodeworkshop.removeduplication.MathUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("isDivisibleBy5")
public class MathUtilsIsDivisibleBy5Test {

    @Test
    @DisplayName("returns true if the numerator is positive and divisible by 5")
    public void testIsDivisibleBy5_FactorsOf_5() {
        assertTrue(MathUtils.isDivisibleBy(5, 5));
        assertTrue(MathUtils.isDivisibleBy(10, 5));
        assertTrue(MathUtils.isDivisibleBy(15, 5));
        assertTrue(MathUtils.isDivisibleBy(30, 5));
    }

    @Test
    @DisplayName("returns true if the numerator is negative and divisible by 5")
    public void testIsDivisibleBy5_Given_Negative_FactorsOf_5() {
        assertTrue(MathUtils.isDivisibleBy(-5, 5));
        assertTrue(MathUtils.isDivisibleBy(-10, 5));
        assertTrue(MathUtils.isDivisibleBy(-15, 5));
        assertTrue(MathUtils.isDivisibleBy(-30, 5));
    }

    @Test
    @DisplayName("returns false if the numerator is positive but not divisible by 5")
    public void testIsDivisibleBy5_Given_FailingInputs() {
        assertFalse(MathUtils.isDivisibleBy(4, 5));
        assertFalse(MathUtils.isDivisibleBy(6, 5));
    }


    @Test
    @DisplayName("returns false if the numerator is negative but not divisible by 5")
    public void testIsDivisibleBy5_Given_Negative_FailingInputs() {
        assertFalse(MathUtils.isDivisibleBy(-4, 5));
        assertFalse(MathUtils.isDivisibleBy(-6, 5));
    }

    @Test
    @DisplayName("returns true if the numerator is 0")
    public void testIsDivisibleBy5_Given_0() {
        // 0 is divisible by every number
        assertTrue(MathUtils.isDivisibleBy(0, 5));
    }
    @Test
    @DisplayName("returns false if the numerator is 1")
    public void testIsDivisibleBy5_Given_1() {
        // 1 isn't divisible by any number except 1 itself
        assertFalse(MathUtils.isDivisibleBy(1, 5));
    }

}
