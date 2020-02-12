/**
 *
 */
package de.pgui.component.control;

import de.pgui.action.IAction;
import de.pgui.component.IMouseInput;
import de.pgui.component.Labeled;
import processing.core.PApplet;

/**
 * @author Noah Ruben
 * @created 09.01.2020
 */
public abstract class ButtonBase extends Labeled implements IMouseInput {

	private IAction buttonAction;

	/**
	 * TODO DOC missingRe
	 *
	 * @param pa   {@link de.pgui.component.Component#pa}
	 * @param xPos {@link de.pgui.component.Component#xPos}
	 * @param yPos {@link de.pgui.component.Component#yPos}
	 */
	public ButtonBase(PApplet pa, int xPos, int yPos) {
		super(pa, xPos, yPos);
	}

	/**
	 * TODO DOC missing
	 *
	 * @param pa     {@link de.pgui.component.Component#pa}
	 * @param xPos   {@link de.pgui.component.Component#xPos}
	 * @param yPos   {@link de.pgui.component.Component#yPos}
	 * @param width  {@link de.pgui.component.Component#width}
	 * @param height {@link de.pgui.component.Component#height}
	 */
	public ButtonBase(PApplet pa, int xPos, int yPos, float width, float height, String labelText) {
		super(pa, xPos, yPos, width, height, labelText);
	}

	/**
	 * TODO DOC missing
	 *
	 * @param xPos
	 * @param yPos
	 * @return
	 */
	protected boolean isOverRect(int xPos, int yPos) {
		return xPos >= this.getxPos() && xPos <= +this.getxPos() + this.getWidth() && yPos >= this.getyPos() && yPos <= this.getyPos() + this.getHeight();
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
