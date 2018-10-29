package com.zuehlke.cleancodeworkshop.smellyshapes;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("A color")
public class ColorTest {

    @Test
    @DisplayName("returns an error message if the color given as text is written incorrectly")
    public void getErrorMessage_invalidColor() {
        Color c = new Color("INVALIDColor_N4me");

        assertEquals("Color not recognized", c.getErrorMessage());
    }

    @Test
    @DisplayName("returns an error message if the color given as text is not an existing color (Magenta)")
    public void getErrorMessage_Magenta() {
        Color c = new Color("Magenta");

        assertEquals("Color not recognized", c.getErrorMessage());
    }

    @Test
    @DisplayName("returns an error message if the color given as text is not an existing color (Cyan)")
    public void getErrorMessage_Cyan() {
        Color c = new Color("Cyan");

        assertEquals("Color not recognized", c.getErrorMessage());
    }


    @Test
    @DisplayName("returns a string containing hex and rgb values if its a recognized color (Red)")
    public void getColorFormatted_true() {
        Color c = new Color("Red");

        String formattedColor = c.getColorFormatted();

        assertEquals("Red #FF0000 255:0:0", formattedColor);
    }

    @Test
    @DisplayName("returns a string only containing its name if its a recognized color (Red) and should not include hex and rgb")
    public void getColorFormatted_false() {
        Color c = new Color("Red");

        String formattedColor = c.getColorAsText();

        assertEquals("Red", formattedColor);
    }

}
