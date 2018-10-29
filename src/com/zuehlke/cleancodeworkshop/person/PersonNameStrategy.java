package com.zuehlke.cleancodeworkshop.person;

public abstract class PersonNameStrategy {

    private final boolean capitalizeSurname;

    PersonNameStrategy(boolean capitalizeSurname) {
        this.capitalizeSurname = capitalizeSurname;
    }

    public abstract String nameString(String familyName, String givenName);

    protected String formatSurname(String familyName) {
        if (capitalizeSurname) {
            return familyName.toUpperCase();
        }
        return familyName;
    }
}
