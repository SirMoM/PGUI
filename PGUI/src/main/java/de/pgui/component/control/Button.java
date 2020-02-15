package de.pgui.component.control;

import de.pgui.component.Component;
import de.pgui.component.LabeledComponent;
import de.pgui.util.Area;
import de.pgui.util.Theme;
import processing.core.PApplet;

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
            getPa().fill(backgroundHighlightColor);
            getPa().stroke(outlineHighlightColor);
        } else {
            getPa().fill(backgroundColor);
            getPa().stroke(outlineColor);
        }
        getPa().rect(getxPos(), getyPos(), componentArea.getWidth(), componentArea.getHeight(), 7);
        super.draw();
    }


    @Override
    public void applyTheme(Theme theme) {
        super.applyTheme(theme);
        backgroundColor = toProcessingColor(theme.getBackgroundColor());
        outlineColor = toProcessingColor(theme.getOutlineColor());
        outlineHighlightColor = toProcessingColor(theme.getOutlineHighlightColor());
        backgroundHighlightColor = toProcessingColor(theme.getBackgroundHighlightColor());
    }
}
