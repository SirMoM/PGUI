package de.pgui.component.control;

import de.pgui.component.LabeledComponent;
import de.pgui.util.Area;
import de.pgui.util.Theme;
import processing.core.PApplet;

public class Label extends LabeledComponent {

    private boolean border = false;
    private boolean underline = true;
    private boolean background = false;

    public Label(PApplet pa, int xPos, int yPos, String text) {
        super(pa, xPos, yPos, text);
    }

    public Label(PApplet pa, int xPos, int yPos, float width, float height) {
        super(pa, xPos, yPos, width, height);
    }

    public Label(PApplet pa, Area area) {
        super(pa, area);
    }

    /**
     * Code that needs to be run before every draw of this Component.
     */
    @Override
    public void beforeDraw() {

    }

    @Override
    public void applyTheme(Theme theme) {
        outlineColor = toProcessingColor(theme.getOutlineColor());
        backgroundColor = toProcessingColor(theme.getBackgroundColor());
        super.applyTheme(theme);
    }

    @Override
    public void draw() {
        if (border) {
            getPa().noFill();
            getPa().stroke(outlineColor);
            getPa().rect(getxPos(), getyPos(), componentArea.getWidth(), componentArea.getHeight());
        }

        if (background) {
            getPa().noStroke();
            getPa().fill(this.backgroundColor);
            getPa().rect(getxPos(), getyPos(), componentArea.getWidth(), componentArea.getHeight());
        }

        if (underline) {
            // yOffset is half of the height - the forth of the textSize
            // to have the text in the middle of the button
            float yOffset = componentArea.getHeight() / 2 + textSize / 4;

            getPa().line(getxPos() + margin - 3, getyPos() + yOffset + 3, getxPos() + componentArea.getWidth() - margin + 3, getyPos() + yOffset + 3);
        }
        super.draw();
    }
}
