/**
 * 
 */
package de.pgui.component;

import de.pgui.action.IAction;
import processing.core.PApplet;

/**
 * @author Noah Ruben
 *
 *
 * @created 09.01.2020
 */
public abstract class ButtonBase extends Labeled implements IMouseInput {

	private IAction buttonAction;

	/**
	 * TODO DOC missingRe
	 * 
	 * @param pa   {@link Component#pa}
	 * @param xPos {@link Component#xPos}
	 * @param yPos {@link Component#yPos}
	 */
	public ButtonBase(PApplet pa, int xPos, int yPos) {
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
	public ButtonBase(PApplet pa, int xPos, int yPos, float width, float height) {
		super(pa, xPos, yPos, width, height);
	}

	/**
	 * TODO DOC missing
	 * 
	 * @param xPos
	 * @param yPos
	 * @return
	 */
	protected boolean isOverRect(int xPos, int yPos) {
		if (xPos >= this.getxPos() && xPos <= +this.getxPos() + this.getWidth() && yPos >= this.getyPos() && yPos <= this.getyPos() + this.getHeight()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Returns the IAction for the Button
	 * 
	 * @return the buttonAction
	 */
	public IAction getButtonAction() {
		return buttonAction;
	}

	/**
	 * Sets the {@link IAction} for the button.
	 * 
	 * @param buttonAction the IAction to set
	 */
	public void setButtonAction(IAction buttonAction) {
		this.buttonAction = buttonAction;
	}

}
