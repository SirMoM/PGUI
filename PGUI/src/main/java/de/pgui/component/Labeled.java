/**
 * 
 */
package de.pgui.component;

import de.pgui.event.MouseInputEvent;
import processing.core.PApplet;

/**
 * @author Noah Ruben
 *
 *
 * @created 09.01.2020
 */
public abstract class Labeled extends Component {

	/** TODO DOC missing
	 * 
	 * @param pa   {@link Component#pa}
	 * @param xPos {@link Component#xPos}
	 * @param yPos {@link Component#yPos}
	 */
	public Labeled(PApplet pa, int xPos, int yPos) {
		super(pa, xPos, yPos);
	}

	/** TODO DOC missing
	 * 
	 * @param pa {@link Component#pa}
	 * @param xPos {@link Component#xPos}
	 * @param yPos {@link Component#yPos}
	 * @param width {@link Component#width}
	 * @param height {@link Component#height}
	 */
	public Labeled(PApplet pa, int xPos, int yPos, float width, float height) {
		super(pa, xPos, yPos, width, height);
	}

}
