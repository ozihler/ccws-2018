package com.zuehlke.cleancodeworkshop.person;

import java.util.ArrayList;
import java.util.List;

public class PersonNameStrategy {
    static List<String> surnameFirst = new ArrayList<>();

    static {
        PersonNameStrategy.surnameFirst.add("CHN");
        PersonNameStrategy.surnameFirst.add("KOR");
        // etc...
    }

    private final String nationality;
    private final boolean olympicMode;
    private final boolean capitalizeSurname;

    public PersonNameStrategy(String nationality, boolean olympicMode, boolean capitalizeSurname) {
        this.nationality = nationality;
        this.olympicMode = olympicMode;
        this.capitalizeSurname = capitalizeSurname;
    }

    public String getNationality() {
        return nationality;
    }

    public boolean isOlympicMode() {
        return olympicMode;
    }

    public boolean isCapitalizeSurname() {
        return capitalizeSurname;
    }

    public String nameString(String familyName, String givenName) {
        String surname = familyName;
        if (isCapitalizeSurname()) {
            surname = familyName.toUpperCase();
        }
        if (surnameFirst())
            return surname + " " + givenName;
        else
            return givenName + " " + surname;
    }

    private boolean surnameFirst() {
        if (!isOlympicMode())
            return false;
        return surnameFirst.contains(getNationality());
    }
}
