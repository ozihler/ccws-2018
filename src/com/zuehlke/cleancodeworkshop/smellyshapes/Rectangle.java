package com.zuehlke.cleancodeworkshop.smellyshapes;


public class Rectangle extends Shape {

    private final Point corner;
    protected Color c = new Color("Blue");
    private int width;
    private int height;

    public Rectangle(Point corner, int width, int height) {
        this.corner = corner;
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public String toXml() {
        StringBuilder builder = new StringBuilder();

        builder.append("<rectangle");
        builder.append(" x=\"" + this.getX() + "\"");
        builder.append(" y=\"" + this.getY() + "\"");
        builder.append(" width=\"" + this.getWidth() + "\"");
        builder.append(" height=\"" + this.getHeight() + "\"");
        builder.append(" />\n");

        return builder.toString();
    }

    public boolean contains(Point point) {
        return this.corner.getX() <= point.getX() && point.getX() <= this.corner.getX() + width && this.corner.getY() <= point.getY() && point.getY() <= this.corner.getY() + height;
    }

    public int calculate() {
        return width * height;
    }

    public int getX() {
        return this.corner.getX();
    }

    public int getY() {
        return this.corner.getY();
    }

    public String toString() {
        return String.format("Rectangle: (%d,%d) width=%d height=%d color=%s", this.corner.getX(), this.corner.getY(), width, height,
                c.getColorAsHex());
    }

}
