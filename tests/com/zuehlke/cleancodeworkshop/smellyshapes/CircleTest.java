package com.zuehlke.cleancodeworkshop.smellyshapes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("A circle")
public class CircleTest {

    private Circle circle;

    @BeforeEach
    public void setUp() {
        circle = new Circle(new Point(0, 0), 1);
        circle.setColor(new Color("Red"));
    }

    @Test
    @DisplayName("returns true if it contains a point and false if not")
    public void contains() {
        assertTrue(circle.contains(new Point(0, 0)));
        assertTrue(circle.contains(new Point(0, 1)));
        assertTrue(circle.contains(new Point(1, 0)));

        assertFalse(circle.contains(new Point(1, 1)));
        assertFalse(circle.contains(new Point(-1, -1)));
        assertFalse(circle.contains(new Point(1, -1)));
        assertFalse(circle.contains(new Point(-1, 1)));
    }

    @Test
    @DisplayName("returns the number of containing points it contains when counted")
    public void countContainingPoints() {
        int result = circle.countContainingPoints(new int[]{0, 10}, new int[]{0, 10});

        assertEquals(1, result);
    }

    @Test
    @DisplayName("returns a valid xml representation when converted to xml")
    public void toXml() {
        String xml = circle.toXml();

        assertEquals("<circle x=\"0\" y=\"0\" radius=\"1\" />\n", xml);
    }

    @Test
    @DisplayName("returns a formatted string when converted to string")
    public void toString_() {
        assertEquals("Circle: (0,0) radius= 1 RGB=255,0,0", circle.toString());
    }
}
