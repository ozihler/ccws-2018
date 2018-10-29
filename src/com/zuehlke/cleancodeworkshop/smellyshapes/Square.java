package com.zuehlke.cleancodeworkshop.smellyshapes;

public class Square extends Rectangle {

    public Square(int edgeLength, Point corner) {
        super(corner, edgeLength, edgeLength);
    }

    public Square(int edgeLength, Color color, Point corner) {
        this(edgeLength, corner);
        this.c = color;
    }

    public boolean containsPoint(Point point) {
        return getX() <= point.getX() && point.getX() <= getX() + getWidth() && getY() <= point.getY() && point.getY() <= getY() + getWidth();
    }

    public int getHeight() throws RuntimeException {
        throw new RuntimeException("Square does not have a height, only edgeLength");
    }

    public String toString() {
        return String.format("Square: (%d,%d) edgeLength=%d color=%s",
                getX(), getY(), getWidth(), c.getColorAsHex());
    }

    public boolean contains(Point point, Point point1) {
        return contains(point) && contains(point1);
    }

    @Override
    public String toXml() {
        StringBuilder builder = new StringBuilder();

        builder.append("<square");
        builder.append(" x=\"" + this.getX() + "\"");
        builder.append(" y=\"" + this.getY() + "\"");
        builder.append(" edgeLength=\"" + this.getWidth() + "\"");
        builder.append(" />\n");

        return builder.toString();
    }
}
