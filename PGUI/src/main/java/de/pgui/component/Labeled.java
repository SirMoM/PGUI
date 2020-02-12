/**
 * 
 */
package de.pgui.component;

import de.pgui.util.ExpandModes;
import de.pgui.util.Theme;
import processing.core.PApplet;
import processing.core.PConstants;

/**
 * @author Noah Ruben
 * @created 09.01.2020
 */
public abstract class Labeled extends Component {

	private float textSize = 20;
	private String text;
	private float margin = 10f;

	/**
	 * TODO DOC missing
	 *
	 * @param pa   {@link Component#pa}
	 * @param xPos {@link Component#xPos}
	 * @param yPos {@link Component#yPos}
	 */
	public Labeled(PApplet pa, int xPos, int yPos) {
		super(pa, xPos, yPos);
	}

	/**
	 * TODO DOC missing
	 *
	 * @param pa     {@link Component#pa}
	 * @param xPos   {@link Component#xPos}
	 * @param yPos   {@link Component#yPos}
	 * @param width  {@link Component#width}
	 * @param height {@link Component#height}
	 */
	public Labeled(PApplet pa, int xPos, int yPos, float width, float height, String text) {
		super(pa, xPos, yPos, width, height);
		this.text = text;
	}

	@Override
	public void draw() {
		this.getPa().textSize(textSize);
		this.getPa().fill(textColor);
		this.getPa().textAlign(PConstants.LEFT, PConstants.CENTER);
		// yOffset is half of the height - the forth of the textSize
		// to have the text in the middle of the button
		float yOffset = getHeight() / 2 - textSize / 4;
		// xOffset is a fifth of the Width
		// to have the text start left of the boarder
		float xOffset = margin;

		this.getPa().text(getText(), getxPos() + xOffset, getyPos() + yOffset);
	}


	@Override
	public void applyTheme(Theme theme) {
		textColor = theme.getTextColor();
		textHighlightColor = theme.getTextHighlightColor();
	}

	// TODO Missing DOC
	public void resizeToNeededSize(ExpandModes mode) {
		// TODO resize the Labeled-Component based on the size the Text occupies and the
		switch (mode) {
			case EXPAND_HORIZONTAL_RIGHT:
				// calculate new Size and Round to after 2 digits after the decimal point
				float newSize = Math.round((getPa().textWidth(getText()) + margin * 2) * 100) / 100;
				System.out.println("Set the size to " + newSize);
				setWidth(newSize);
				break;
			case EXPAND_HORIZONTAL_LEFT:
				break;
			case EXPAND_VERTICAL_LEFT:
				break;
			case EXPAND_VERTICAL_RIGHT:
				break;
		}

	}

	public String getText() {
		return text;
	}
}
