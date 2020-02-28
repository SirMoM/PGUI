package de.pgui.util;

import processing.core.PApplet;
import processing.core.PVector;

/**
 * TODO Missing DOC
 */
public class Area {
    /**
     * X-Position of the Area
     */
    private float xPos;

    /**
     * Y-Position of the Area
     */
    private float yPos;

    /**
     * Height of the Area
     */
    private float height;

    /**
     * Width of the Area
     */
    private float width;

    /**
     * @param xPos   X-Position of the Area
     * @param yPos   Y-Position of the Area
     * @param width  Width of the Area
     * @param height Height of the Area
     */
    public Area(float xPos, float yPos, float width, float height) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.height = height;
        this.width = width;
    }

    /**
     * Returns <code>true</code> if the Position <code>(xPos,YPos)</code>
     * is over / in the space of the Component otherwise false.
     *
     * @param xPos the X of the Position
     * @param yPos the Y of the Position
     * @return if the position is over the Area
     */
    public boolean isOverArea(int xPos, int yPos) {
        return xPos >= this.getxPos() && xPos <= +this.getxPos() + this.getWidth() && yPos >= this.getyPos() && yPos <= this.getyPos() + this.getHeight();
    }

    /**
     * Returns <code>true</code> if the Position <code>(xPos,YPos)</code>
     * is over / in the space of the Area otherwise false.
     *
     * @param pos the position
     * @return if the position is over the Component
     */
    public boolean isOverArea(PVector pos) {
        return pos.x >= this.getxPos() && pos.x <= this.getxPos() + this.getWidth() && pos.y >= this.getyPos() && pos.y <= this.getyPos() + this.getHeight();
    }


    /**
     * TODO missing DOC
     * Check if 2 Areas intersect.
     *
     * @param area Area to check intersection
     * @return true if the Areas intersect otherwise false
     */
    public boolean isOverArea(Area area) {
        // TODO Use AABB algorithm for calculation
        return false;
    }

    public PVector getPositionAsVector() {
        return new PVector(xPos, yPos);
    }

    public PVector getSizeAsVector() {
        return new PVector(width, height);
    }

    @Override
    public Area clone() {
        return new Area(xPos, yPos, width, height);
    }

    /**
     * Draws a simple rect around the Area.
     *
     * @param pa Reference to the Processing sketch
     */
    public void drawSimpleRectFromArea(PApplet pa) {
        pa.rect(xPos, yPos, width, height);
    }

    public float getxPos() {
        return xPos;
    }

    public void setxPos(float xPos) {
        this.xPos = xPos;
    }

    public float getyPos() {
        return yPos;
    }

    public void setyPos(float yPos) {
        this.yPos = yPos;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }
}
