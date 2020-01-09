/**
 * 
 */
package de.pgui.component;

import de.pgui.event.MouseInputEvent;
import processing.core.PApplet;

/**
 * Base-Class for all things that are displayable by PGUI
 * 
 * @author Noah Ruben
 * @created 19.12.2019
 */
public abstract class Component {

	/** The visibility of the Component */
	private boolean visible = true;

	/** Reference to the processing sketch */
	private PApplet pa;

	/** X-Position of the Component */
	private int xPos;

	/** Y-Position of the Component */
	private int yPos;

	/** Height of the Component */
	private float height;

	/** Width of the Component */
	private float width;

	/**
	 * @param pa {@link ProcessingGui#pa}
	 * @param xPos {@link ProcessingGui#xPos}
	 * @param yPos {@link ProcessingGui#yPos}
	 */
	public Component(PApplet pa, int xPos, int yPos) {
		this.pa = pa;
		this.xPos = xPos;
		this.yPos = yPos;
	}

	/**
	 * @param pa {@link ProcessingGui#pa}
	 * @param xPos {@link ProcessingGui#xPos}
	 * @param yPos {@link ProcessingGui#yPos}
	 */
	public Component(PApplet pa, int xPos, int yPos, float width, float height) {
		this(pa, xPos, yPos);
		this.width = width;
		this.height = height;
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
	 * @param color a RGB-Color as String representation from the HEX-Value
	 * @return the color as Processing color int
	 */
	protected int toProcessingColor(String colorStr) {
		int color = (int) Long.parseLong(colorStr, 16);
		return this.pa.color(this.pa.red(color), this.pa.green(color), this.pa.blue(color));
	}

	/**
	 * Draws the component onto the Sketch
	 */
	public abstract void draw();

	/**
	 * @return the pa
	 */
	public PApplet getPa() {
		return pa;
	}

	/**
	 * @return if the component is {@link #visible}
	 */
	public boolean isVisible() {
		return visible;
	}

	/**
	 * @return the xPos
	 */
	public int getxPos() {
		return xPos;
	}

	/**
	 * @param xPos the xPos to set
	 */
	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	/**
	 * @return the yPos
	 */
	public int getyPos() {
		return yPos;
	}

	/**
	 * @param yPos the yPos to set
	 */
	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

	/**
	 * @return the height
	 */
	public float getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(float height) {
		this.height = height;
	}

	/**
	 * @return the width
	 */
	public float getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(float width) {
		this.width = width;
	}

	/**
	 * Sets the visibility of the component.
	 * 
	 * @param visible set true to be visible
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
}
