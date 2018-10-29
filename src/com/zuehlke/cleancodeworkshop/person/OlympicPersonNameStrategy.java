package com.zuehlke.cleancodeworkshop.person;

public class OlympicPersonNameStrategy extends PersonNameStrategy {

    private boolean hasSurnameFirst;

    public OlympicPersonNameStrategy(boolean capitalizeSurname, boolean hasSurnameFirst) {
        super(capitalizeSurname);
        this.hasSurnameFirst = hasSurnameFirst;
    }

    @Override
    public String nameString(String familyName, String givenName) {
        if (hasSurnameFirst) {
            return String.format("%s %s", formatSurname(familyName), givenName);
        } else {
            return String.format("%s %s", givenName, formatSurname(familyName));
        }
    }

}
