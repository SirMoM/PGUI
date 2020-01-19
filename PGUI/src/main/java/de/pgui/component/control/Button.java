/**
 * 
 */
package de.pgui.component.control;

import de.pgui.action.IAction;
import de.pgui.component.ButtonBase;
import de.pgui.component.Component;
import de.pgui.event.MouseInputEvent;
import processing.core.PApplet;

/**
 * @author Noah Ruben
 *
 *
 * @created 09.01.2020
 */
public class Button extends ButtonBase{
	
	private IAction buttonAction;

	/**
	 * @param pa {@link Component#pa}
	 * @param xPos X-Position of the Button
	 * @param yPos Y-Position of the Button
	 * @param width Width of the Button
	 * @param height Height of the Button
	 */
	public Button(PApplet pa, int xPos, int yPos, float width, float height, String labelText) {
		super(pa, xPos, yPos, width, height);
	}

	@Override
	public void handleMouseInputEvent(MouseInputEvent mouseInputEvent) {
		if (isOverRect(mouseInputEvent.getMouseEvent().getX(), mouseInputEvent.getMouseEvent().getY())) {
			if (buttonAction != null) {
				buttonAction.fireAction();
			}else {
				// TODO A real logging system
				System.out.println("No action for that button.");
			}
		}
	}

	@Override
	public void beforeDraw() {
	}

	@Override
	public void draw() {
		super.draw();
		if (isOverRect(getPa().mouseX, getPa().mouseY)) {
			getPa().fill(toProcessingColor(0x035e7b));
		}else {
			getPa().fill(toProcessingColor(0x002e2c));
			
		}
		getPa().stroke(toProcessingColor(0x0));
		getPa().rect(getxPos(), getyPos(), getWidth(), getHeight(), 7);
	}


}
