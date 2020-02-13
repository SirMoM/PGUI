package de.pgui.component.control;

import de.pgui.component.Component;
import de.pgui.component.LabeledComponent;
import de.pgui.util.Area;
import de.pgui.util.Theme;
import processing.core.PApplet;

/**
 * @author Noah Ruben
 * @created 09.01.2020
 */
public class Button extends LabeledComponent {

    /**
     * TODO Missing DOC
     *
     * @param pa   {@link Component#pa}
     * @param xPos X-Position of the {@link Component}
     * @param yPos Y-Position of the {@link Component}
     * @param text The text of the {@link LabeledComponent}
     */
    public Button(PApplet pa, int xPos, int yPos, String text) {
        super(pa, xPos, yPos, text);
        setComponentClickableArea(this.componentArea);
    }

    /**
     * @param pa     {@link Component#pa}
     * @param xPos   X-Position of the {@link Component}
     * @param yPos   Y-Position of the {@link Component}
     * @param width  Width of the {@link Component}
     * @param height Height of the {@link Component}
     * @see Area
     */
    public Button(PApplet pa, int xPos, int yPos, float width, float height) {
        super(pa, xPos, yPos, width, height);
        setComponentClickableArea(this.componentArea);
    }

    /**
     * @param pa     {@link Component#pa}
     * @param xPos   X-Position of the {@link Component}
     * @param yPos   Y-Position of the {@link Component}
     * @param width  Width of the {@link Component}
     * @param height Height of the {@link Component}
     * @see Area
     */
    public Button(PApplet pa, int xPos, int yPos, float width, float height, String text) {
        super(pa, xPos, yPos, width, height);
        setText(text);
        setComponentClickableArea(this.componentArea);
    }

    /**
     * @param pa   {@link Component#pa}
     * @param area {@link Component#componentArea}
     * @see Area for the diemension
     */
    public Button(PApplet pa, Area area) {
        super(pa, area);
    }

    @Override
    public void beforeDraw() {
    }

    @Override
    public void draw() {
        if (isOverComponent(getPa().mouseX, getPa().mouseY)) {
            getPa().fill(toProcessingColor(backgroundHighlightColor));
            getPa().stroke(toProcessingColor(outlineHighlightColor));
        } else {
            getPa().fill(toProcessingColor(backgroundColor));
            getPa().stroke(toProcessingColor(outlineColor));
        }
        getPa().rect(getxPos(), getyPos(), componentArea.getWidth(), componentArea.getHeight(), 7);
        getPa().sphere(33);
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
