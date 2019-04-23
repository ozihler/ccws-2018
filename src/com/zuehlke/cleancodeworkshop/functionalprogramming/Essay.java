package com.zuehlke.cleancodeworkshop.functionalprogramming;

import java.util.Objects;

public class Essay {
    private final String title;
    private final String text;

    Essay(String title, String text) {
        this.title = title;
        this.text = text;
    }

    String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Essay essay = (Essay) o;
        return Objects.equals(title, essay.title) &&
                Objects.equals(text, essay.text);
    }
}
