package com.zuehlke.cleancodeworkshop.smellyshapes;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class ShapeGroup extends Shape {

    private Set<Shape> shapes = new LinkedHashSet<>();
    private boolean readOnly = false;

    public ShapeGroup() {
    }

    public ShapeGroup(boolean readOnly, Set<Shape> shapes) {
        this.shapes = shapes;
        this.readOnly = readOnly;
    }

    public void add(Shape shape) {
        if (readOnly || contains(shape)) {
            return;
        }

        addToShapes(shape);
    }

    private void addToShapes(Shape shape) {
        shapes.add(shape);
    }

    public boolean contains(Shape shape) {
        return shapes.contains(shape);
    }

    public boolean contains(int x, int y) {
        return shapes.stream()
                .filter(Objects::nonNull)
                .anyMatch(shape -> shape.contains(x, y));
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    public String toXml() {
        StringBuilder builder = new StringBuilder();

        builder.append("<shapegroup>\n");

        shapes.stream()
                .map(Shape::toXml)
                .forEach(builder::append);

        builder.append("</shapegroup>\n");

        return builder.toString();
    }

    public int getSize() {
        return shapes.size();
    }
}
