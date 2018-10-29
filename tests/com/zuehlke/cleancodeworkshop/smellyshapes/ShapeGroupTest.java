package com.zuehlke.cleancodeworkshop.smellyshapes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("A shape group")
public class ShapeGroupTest {

    private ShapeGroup shapeGroup;

    @BeforeEach
    public void init() {
        shapeGroup = new ShapeGroup();
    }

    @Test
    @DisplayName("returns a valid xml representation containing all its shapes when converted to xml")
    public void toXml() {
        shapeGroup.add(new Rectangle(new Point(0, 0), 2, 1));

        String xml = shapeGroup.toXml();

        assertEquals("<shapegroup>\n<rectangle x=\"0\" y=\"0\" width=\"2\" height=\"1\" />\n</shapegroup>\n", xml);
    }

    @Test
    @DisplayName("cannot be added the same shape twice")
    public void add_sameElementTwice() {
        shapeGroup.setReadOnly(false);

        Circle circle = new Circle(new Point(0, 0), 0);
        shapeGroup.add(circle);
        shapeGroup.add(circle);

        assertEquals(1, shapeGroup.getSize());
    }

    @Test
    @DisplayName("returns false if the shape group is empty")
    public void contains_pointNotInGroup() {
        assertFalse(shapeGroup.contains(new Point(0, 0)));
    }

    @Test
    @DisplayName("returns true if at least one shape in the group contains a point")
    public void contains_pointInGroup() {
        shapeGroup.add(new Circle(new Point(0, 0), 0));

        assertTrue(shapeGroup.contains(new Point(0, 0)));
    }

    @Test
    @DisplayName("returns false if no shape inside the group contains a point")
    public void contains_pointOutsideGroup() {
        shapeGroup.add(new Circle(new Point(0, 0), 0));

        assertFalse(shapeGroup.contains(new Point(1, 1)));
    }

    @Test
    @DisplayName("returns false if null is passed to check for containment within the group")
    public void contains_null() {
        assertFalse(shapeGroup.contains((Shape) null));
    }

    @Test
    @DisplayName("returns true if the shape is contained with the shape group")
    public void contains_shapeInGroup() {
        Circle c = new Circle(new Point(0, 0), 0);
        shapeGroup.add(c);

        assertTrue(shapeGroup.contains(c));
    }

    @Nested
    @DisplayName("when not readonly")
    class WhenNotReadOnly {

        private ShapeGroup shapeGroup;

        @BeforeEach
        public void init() {
            shapeGroup = new ShapeGroup();
        }

        @Test
        @DisplayName("can be added additional shapes")
        public void add_withoutReadOnly() {
            shapeGroup = new ShapeGroup();
            shapeGroup.setReadOnly(false);

            shapeGroup.add(new Circle(new Point(0, 0), 0));

            assertEquals(1, shapeGroup.getSize());
        }


        @Test
        @DisplayName("increases the number of elements it can hold on add once exceeded")
        public void add_internalArraySizeExceeded() {
            shapeGroup.setReadOnly(false);

            for (int i = 0; i < 11; i++) {
                shapeGroup.add(new Circle(new Point(0, 0), 0));
            }

            assertEquals(11, shapeGroup.getSize());
        }

        @Test
        @DisplayName("won't increase the number of elements on add if the size limit is reached but already contains the element")
        public void add_notIncreasingSizeIfElementNotAdded() {
            shapeGroup.setReadOnly(false);

            for (int i = 0; i < 9; i++) {
                shapeGroup.add(new Circle(new Point(0, 0), 0));
            }
            var shape = new Circle(new Point(0, 0), 0);
            shapeGroup.add(shape);
            shapeGroup.add(shape);

            assertEquals(10, shapeGroup.getSize());
        }
    }

    @Nested
    @DisplayName("when read only")
    class WhenInitialisedEmpty {

        private ShapeGroup shapeGroup;

        @BeforeEach
        public void init() {
            shapeGroup = new ShapeGroup();
        }

        @Test
        @DisplayName("cannot add another element")
        public void add_withReadOnly() {
            shapeGroup.setReadOnly(true);

            shapeGroup.add(new Circle(new Point(0, 0), 0));

            assertEquals(0, shapeGroup.getSize());
        }
    }

    @Nested
    @DisplayName("when initialised with shape array containing one element")
    class WhenInitialisedWithOneElement {

        private ShapeGroup shapeGroup;

        @BeforeEach
        void initWithOneElement() {
            final Shape[] shapes = new Shape[]{new Circle(new Point(0, 0), 0)};
            shapeGroup = new ShapeGroup(true, Set.of(shapes));
        }

        @Test
        @DisplayName("has a size of 1")
        public void constructor_withShapeArray() {
            assertEquals(1, shapeGroup.getSize());
        }
    }


}
