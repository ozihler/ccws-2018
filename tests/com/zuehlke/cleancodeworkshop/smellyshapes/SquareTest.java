package com.zuehlke.cleancodeworkshop.smellyshapes;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


@DisplayName("A square")
public class SquareTest {

    @Test
    @DisplayName("should have an area of 4 when the edge length is 2")
    public void calculateArea() {
        Square square = new Square(0, 0, 2);
        assertEquals(4, square.calculate());
    }

    @Test
    @DisplayName("has a formatted toString() method")
    public void squareToString() {
        Square square = new Square(0, 0, 1, new Color("Red"));
        assertEquals("Square: (0,0) edgeLength=1 color=#FF0000", square.toString());
    }

    @Test
    @DisplayName("returns a valid xml representation when converted to xml")
    public void toXml() {
        Square square = new Square(0, 1, 2);
        String xml = square.toXml();
        assertEquals("<square x=\"0\" y=\"1\" edgeLength=\"2\" />\n", xml);
    }

    @Test
    @DisplayName("can determine if it contains points")
    public void containsPoints() {
        Square square = new Square(0, 0, 1);

        assertTrue(square.containsPoint(0, 0));
        assertTrue(square.containsPoint(0, 1));
        assertTrue(square.containsPoint(1, 1));
        assertTrue(square.containsPoint(1, 0));

        assertFalse(square.containsPoint(-1, -1));
        assertFalse(square.containsPoint(-1, 0));
        assertFalse(square.containsPoint(0, -1));
        assertFalse(square.containsPoint(1, 2));
        assertFalse(square.containsPoint(2, 1));
    }

    @Test
    @DisplayName("throws an exception when getHeight() is called")
    public void getHeigth() {
        assertThrows(RuntimeException.class, () -> new Square(0, 0, 0).getHeight());
    }
}
