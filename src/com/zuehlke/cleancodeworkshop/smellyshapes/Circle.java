package com.zuehlke.cleancodeworkshop.smellyshapes;

public class Circle extends Shape {
    private final Point center;
    private int radius;
    private Color color = new Color("Green");

    public Circle(Point center, int radius) {
        this.center = center;
        this.radius = radius;
    }

    public boolean contains(Point point) {
        int deltaX = point.getX() - this.center.getX();
        int deltaY = point.getY() - this.center.getY();
        return square(deltaX) + square(deltaY) <= square(radius);
    }

    private int square(int deltaY) {
        return deltaY * deltaY;
    }

    public int countContainingPoints(int[] xCords, int[] yCords) {
        int numberOfContainingPoints = 0;
        for (int i = 0; i < xCords.length; ++i) {
            if (contains(new Point(xCords[i], yCords[i]))) {
                numberOfContainingPoints++;
            }
        }
        return numberOfContainingPoints;
    }

    /**
     * This method returns the shape color.
     *
     * @return the shape color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Sets the shape color
     *
     * @param color the new color of the shape
     */
    public void setColor(Color color) {
        this.color = color;
    }

    public int getX() {
        return this.center.getX();
    }

    public int getY() {
        return this.center.getY();
    }

    public int getRadius() {
        return radius;
    }

    public String toString() {
        return String.format("Circle: %s radius= %d %s", center, radius, color.asRgbString());
    }

    public String toXml() {
        StringBuilder builder = new StringBuilder();

        builder.append("<circle");
        builder.append(" x=\"" + this.getX() + "\"");
        builder.append(" y=\"" + this.getY() + "\"");
        builder.append(" radius=\"" + this.getRadius() + "\"");
        builder.append(" />\n");

        return builder.toString();
    }
}
