/**
 * 
 */
package de.pgui.component;

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
	
	
	/**
	 * @param pa {@link ProcessingGui#pa}
	 */
	public Component(PApplet pa) {
		this.pa = pa;
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
		int color = Integer.parseInt(colorStr, 16); 
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
	 * Sets the visibility of the component.
	 * 
	 * @param visible set true to be visible
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
}
