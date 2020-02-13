/**
 *
 */
package de.pgui.component;

import de.pgui.util.Area;
import de.pgui.util.Theme;
import processing.core.PApplet;
import processing.core.PVector;


/**
 * Base-Class for all things that are displayable by PGUI
 *
 * @author Noah Ruben
 * @created 19.12.2019
 */
public abstract class Component {

    /** Default background color <p>
     *  <b>FIELD-VALUES ARE Processing colors</b> */
    protected int outlineColor;

    /** Default background color <p>
     *  <b>FIELD-VALUES ARE Processing colors</b> */
    protected int textHighlightColor;

    /** Default background color <p>
     *  <b>FIELD-VALUES ARE Processing colors</b> */
    protected int backgroundColor;

    /** Default background color <p>
     *  <b>FIELD-VALUES ARE Processing colors</b> */
    protected int textColor;

    /** Default background color <p>
     *  <b>FIELD-VALUES ARE Processing colors</b> */
    protected int outlineHighlightColor;

    /** Default background color <p>
     *  <b>FIELD-VALUES ARE Processing colors</b> */
    protected int backgroundHighlightColor;

    /** The visibility of the Component */
    private boolean visible = true;

    /** Reference to the processing sketch */
    private PApplet pa;

    /** The {@link Area} of the Component */
    protected Area componentArea;

    /**
     * @param pa {@link Component#pa}
     * @param xPos X-Position of the {@link Component}
     * @param yPos Y-Position of the {@link Component}
     * @param width Width of the {@link Component}
     * @param height Height of the {@link Component}
     *
     * @see Area
     */
    public Component(PApplet pa, int xPos, int yPos, float width, float height) {
        this.pa = pa;
        this.componentArea = new Area(xPos, yPos, width, height);
    }

    /**
     * @param pa   {@link Component#pa}
     * @param area {@link Component#componentArea}
     * @see Area for the Component
     */
    public Component(PApplet pa, Area area) {
        this.pa = pa;
        this.componentArea = area;
    }

    /**
     * Transfers a HEX RGB value into the processing color value.
     *
     * @param color a RGB-Color as int representation from the HEX-Value
     * @return the color as Processing color int
     */
    protected int toProcessingColor(int color) {
        return this.pa.color(this.pa.red(color), this.pa.green(color), this.pa.blue(color));
    }

    /**
     * Transfers a HEX RGB value into the processing color value.
     *
     * @param colorStr a RGB-Color as String representation from the HEX-Value
     * @return the color as Processing color int
     */
    protected int toProcessingColor(String colorStr) {
        int color = (int) Long.parseLong(colorStr, 16);
        return this.pa.color(this.pa.red(color), this.pa.green(color), this.pa.blue(color));
    }


    /**
     * Code that needs to be run before every draw of this Component.
     */
    public abstract void beforeDraw();

    /**
     * Draws the component onto the Sketch
     */
    public abstract void draw();

    /** @return the pa */
    public PApplet getPa() {
        return pa;
    }

    /**
     * @return if the component is {@link #visible}
     */
    public boolean isVisible() {
        return visible;
    }

    /** @return the xPos */
    public float getxPos() {
        return componentArea.getxPos();
    }

    /** @param xPos the xPos to set */
    public void setxPos(int xPos) {
        this.componentArea.setxPos(xPos);
    }

    /**  @return the yPos */
    public float getyPos() {
        return this.componentArea.getyPos();
    }

    /** @param yPos the yPos to set */
    public void setyPos(int yPos) {
        this.componentArea.setyPos(yPos);
    }


    /** @param height the height to set */
    public void setHeight(float height) {
        this.componentArea.setHeight(height);
    }

    /** @param width the width to set */
    public void setWidth(float width) {
        this.componentArea.setWidth(width);
    }

    /**
     * Sets the visibility of the component.
     *
     * @param visible set true to be visible
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    /**
     * This sets all color values Bases on the Theme
     *
     * @param theme The theme for the Component.
     */
    public abstract void applyTheme(Theme theme);

    /**
     * Returns <code>true</code> if the Position <code>(xPos,YPos)</code>
     * is over / in the space of the Component otherwise false.
     *
     * @param xPos the X of the Position
     * @param yPos the Y of the Position
     *
     * @return if the position is over the Component
     */
    protected boolean isOverComponent(int xPos, int yPos) {
        return this.componentArea.isOverArea(xPos, yPos);
    }

    /**
     * Returns <code>true</code> if the Position <code>(xPos,YPos)</code>
     * is over / in the space of the Component otherwise false.
     *
     * @param pos the position
     *
     * @return if the position is over the Component
     */
    protected boolean isOverComponent(PVector pos) {
        return this.componentArea.isOverArea(pos);
    }
}
