package com.zuehlke.cleancodeworkshop.smellyshapes;


public class Rectangle extends Shape {

    protected Color c = new Color("Blue");
    private int x;
    private int y;
    int width;
    int height;

    public Rectangle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
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

    public boolean contains(int x, int y) {
        return this.x <= x && x <= this.x + width && this.y <= y && y <= this.y + height;
    }

    public int calculate() {
        return width * height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String toString() {
        return String.format("Rectangle: (%d,%d) width=%d height=%d color=%s", x, y, width, height,
                c.getColorAsHex());
    }
}
