package com.zuehlke.cleancodeworkshop.person;

import java.util.ArrayList;
import java.util.List;

public class PersonNameStrategyFactory {
    public static List<String> surnameFirst = new ArrayList<>();

    static {
        PersonNameStrategyFactory.surnameFirst.add("CHN");
        PersonNameStrategyFactory.surnameFirst.add("KOR");
        // etc...
    }

    public static PersonNameStrategy create(String nationality, boolean olympicMode, boolean capitalizeSurname) {
        if (olympicMode) {
            return new OlympicPersonNameStrategy(capitalizeSurname, surnameFirst.contains(nationality));
        }
        return new DefaultPersonNameStrategy(capitalizeSurname);
    }
}
