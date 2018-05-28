package com.zuehlke.cleancodeworkshop.person;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("A person")
public class PersonTest {

    @Test
    @DisplayName("in default mode prints 'firstname surname'")
    public void toString_defaultMode() {
        Person michael = new Person("Ammann", "Simon", "CH");
        assertEquals("Simon Ammann", michael.toString());
    }

    @Test
    @DisplayName("in olympic mode with capitalize surname prints 'firstname LASTNAME'")
    public void toString_olympicModeAndCapitalize() {
        Person michael = new Person("Ammann", "Simon", "CH", true, true);
        assertEquals("Simon AMMANN", michael.toString());
    }

    @Test
    @DisplayName("in default mode without capitalization and part of 'surname-first' nationality prints 'firstname lastname'")
    public void toString_chineseInNonOlympicNonCapitalize() {
        Person yao = new Person("Yao", "Ming", "CHN", false, false);
        assertEquals("Ming Yao", yao.toString());
    }

    @Test
    @DisplayName("in olympic mode without capitalization and part of 'surname-first' nationality prints 'lastname firstname'")
    public void toString_chineseInOlympicMode() {
        Person yao = new Person("Yao", "Ming", "CHN", true, false);
        assertEquals("Yao Ming", yao.toString());
    }

    @Test
    @DisplayName("in olympic mode with capitalization and part of 'surname-first' nationality prints 'LASTNAME firstname'")
    public void toString_chineseInOlympicModeAndCapitalize() {
        Person yao = new Person("Yao", "Ming", "CHN", true, true);
        assertEquals("YAO Ming", yao.toString());
    }

}
