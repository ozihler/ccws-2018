package com.zuehlke.cleancodeworkshop.removeduplications;

import com.zuehlke.cleancodeworkshop.removeduplication.MathUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("isDivisibleBy3")
public class MathUtilsIsDivisibleBy3Test {

    @Test
    @DisplayName("returns true if the numerator is positive and divisible by 3")
    public void testIsDivisibleBy3_Given_FactorsOf_3() {
        assertTrue(MathUtils.isDivisibleBy(3, 3));
        assertTrue(MathUtils.isDivisibleBy(6, 3));
        assertTrue(MathUtils.isDivisibleBy(9, 3));
        assertTrue(MathUtils.isDivisibleBy(15, 3));
        assertTrue(MathUtils.isDivisibleBy(30, 3));
    }

    @Test
    @DisplayName("returns true if the numerator is negative and divisible by 3")
    public void testIsDivisibleBy3_Given_Negative_FactorsOf_3() {
        assertTrue(MathUtils.isDivisibleBy(-3, 3));
        assertTrue(MathUtils.isDivisibleBy(-6, 3));
        assertTrue(MathUtils.isDivisibleBy(-9, 3));
        assertTrue(MathUtils.isDivisibleBy(-15, 3));
        assertTrue(MathUtils.isDivisibleBy(-30, 3));
    }

    @Test
    @DisplayName("returns false if the numerator is positive and not divisible by 3")
    public void testIsDivisibleBy3_Given_FailingInputs() {
        assertFalse(MathUtils.isDivisibleBy(2, 3));
        assertFalse(MathUtils.isDivisibleBy(4, 3));
        assertFalse(MathUtils.isDivisibleBy(10, 3));
    }


    @Test
    @DisplayName("returns false if the numerator is negative but not divisible by 3")
    public void testIsDivisibleBy5_Given_Negative_FailingInputs() {
        assertFalse(MathUtils.isDivisibleBy(-2, 3));
        assertFalse(MathUtils.isDivisibleBy(-4, 3));
        assertFalse(MathUtils.isDivisibleBy(-10, 3));
    }

    @Test
    @DisplayName("returns true if the numerator is 0")
    public void testIsDivisibleBy3_Given_0() {
        // 0 is divisible by every number
        assertTrue(MathUtils.isDivisibleBy(0, 3));
    }
    @Test
    @DisplayName("returns false if the numerator is 1")
    public void testIsDivisibleBy3_Given_1() {
        // 1 isn't divisible by any number except 1 itself
        assertFalse(MathUtils.isDivisibleBy(1, 3));
    }


}
