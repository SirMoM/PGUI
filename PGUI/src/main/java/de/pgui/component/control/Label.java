package de.pgui.component.control;

import de.pgui.component.LabeledComponent;
import de.pgui.util.Area;
import processing.core.PApplet;

public class Label extends LabeledComponent {

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
    public void draw() {
        super.draw();
    }
}
