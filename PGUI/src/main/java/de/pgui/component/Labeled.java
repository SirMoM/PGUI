/**
 * 
 */
package de.pgui.component;

import de.pgui.event.MouseInputEvent;
import de.pgui.util.BasicColors;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PFont;

/**
 * @author Noah Ruben
 *
 *
 * @created 09.01.2020
 */
public abstract class Labeled extends Component {

	private int textSize = 20;
	private int textColor = BasicColors.BLACK;
	private String text = "text";
	private PFont font;
	
	
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
	
	@Override
	public void draw() {
		getPa().textAlign(PConstants.CENTER, PConstants.CENTER);
		getPa().text(this.text, getxPos(), getyPos());
	}

}
