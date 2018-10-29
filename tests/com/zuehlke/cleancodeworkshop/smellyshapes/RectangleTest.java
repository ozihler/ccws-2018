package com.zuehlke.cleancodeworkshop.smellyshapes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("A rectangle")
public class RectangleTest {

    private Rectangle rectangle;

    @BeforeEach
    public void setUp() {
        rectangle = new Rectangle(new Point(0, 0), 2, 1);
    }

    @Test
    @DisplayName("returns true if it contains a point and false if not")
    public void contains() {
        assertTrue(rectangle.contains(new Point(0, 0)));
        assertTrue(rectangle.contains(new Point(1, 0)));
        assertTrue(rectangle.contains(new Point(1, 1)));
        assertTrue(rectangle.contains(new Point(2, 1)));

        assertFalse(rectangle.contains(new Point(2, 2)));
        assertFalse(rectangle.contains(new Point(-1, 0)));
        assertFalse(rectangle.contains(new Point(0, -1)));
    }

    @Test
    @DisplayName("should have an area of 2 when the width is 2 and the hight is 1")
    public void calculateArea() {
        assertEquals(2, rectangle.calculate());
    }

    @Test
    @DisplayName("returns a valid xml representation when converted to xml")
    public void toXml() {
        String xml = rectangle.toXml();

        assertEquals("<rectangle x=\"0\" y=\"0\" width=\"2\" height=\"1\" />\n", xml);
    }

    @Test
    @DisplayName("returns a formatted string when converted to string")
    public void toString_() {

        assertEquals("Rectangle: (0,0) width=2 height=1 color=#00FF00", rectangle.toString());
    }
}
