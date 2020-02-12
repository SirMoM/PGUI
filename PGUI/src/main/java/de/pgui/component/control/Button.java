package de.pgui.component.control;

import de.pgui.component.Component;
import de.pgui.event.MouseInputEvent;
import de.pgui.util.Theme;
import processing.core.PApplet;
import processing.core.PFont;

import java.util.Arrays;

/**
 * @author Noah Ruben
 *
 *
 * @created 09.01.2020
 */
public class Button extends ButtonBase {

	/**
	 * @param pa     {@link Component#pa}
	 * @param xPos   X-Position of the Button
	 * @param yPos   Y-Position of the Button
	 * @param width  Width of the Button
	 * @param height Height of the Button
	 */
	public Button(PApplet pa, int xPos, int yPos, float width, float height, String labelText) {
		super(pa, xPos, yPos, width, height, labelText);
	}

	@Override
	public void handleMouseInputEvent(MouseInputEvent mouseInputEvent) {
		if (isOverRect(mouseInputEvent.getMouseEvent().getX(), mouseInputEvent.getMouseEvent().getY())) {
			if (getButtonAction() != null) {
				getButtonAction().fireAction();
			} else {
				// TODO A real logging system
				System.out.println("No action for that button.");
				System.out.println(Arrays.toString(PFont.list()));
			}
		}
	}

	@Override
	public void beforeDraw() {
	}

	@Override
	public void draw() {
		if (isOverRect(getPa().mouseX, getPa().mouseY)) {
			getPa().fill(toProcessingColor(backgroundHighlightColor));
			getPa().stroke(toProcessingColor(outlineHighlightColor));
		} else {
			getPa().fill(toProcessingColor(backgroundColor));
			getPa().stroke(toProcessingColor(outlineColor));
		}
		getPa().rect(getxPos(), getyPos(), getWidth(), getHeight(), 7);
		super.draw();
	}


	@Override
	public void applyTheme(Theme theme) {
		super.applyTheme(theme);
		backgroundColor = theme.getBackgroundColor();
		outlineColor = theme.getOutlineColor();
		outlineHighlightColor = theme.getOutlineHighlightColor();
		backgroundHighlightColor = theme.getBackgroundHighlightColor();
	}
}
