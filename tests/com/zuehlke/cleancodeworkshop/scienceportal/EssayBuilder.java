package com.zuehlke.cleancodeworkshop.scienceportal;
import java.util.Random;

class EssayBuilder {
    private String title;
    private String text;

    static EssayBuilder newEssay() {
        return new EssayBuilder();
    }

    EssayBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    EssayBuilder withText(String text) {
        this.text = text;
        return this;
    }

    Essay complete() {
        if (title == null) {
            title = new Random().nextInt() * 1000000 + "";
        }
        if (text == null) {
            text = new Random().nextInt() * 1000000 + "";
        }
        return new Essay(title, text);
    }
}
