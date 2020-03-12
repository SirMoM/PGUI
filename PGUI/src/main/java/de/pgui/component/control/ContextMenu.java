package de.pgui.component.control;

import de.pgui.component.ListComponent;
import processing.core.PApplet;

public class ContextMenu extends ListComponent<String> {
    /**
     * @param pa     {@link Component#pa}
     * @param xPos   X-Position of the {@link Component}
     * @param yPos   Y-Position of the {@link Component}
     * @param width  Width of the {@link Component}
     * @param height Height of the {@link Component}
     * @see Area
     */
    public ContextMenu(PApplet pa, int xPos, int yPos, float width, float height) {
        super(pa, xPos, yPos, width, height);
    }
}
