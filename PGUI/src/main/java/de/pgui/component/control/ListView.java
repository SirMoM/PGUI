package de.pgui.component.control;

import de.pgui.component.LabeledComponent;
import de.pgui.util.Area;
import de.pgui.util.Theme;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

// TODO how to do Scroll weel

public class ListView<G> extends LabeledComponent {

    private List<G> entrys;

    public ListView(PApplet pa, int xPos, int yPos, float width, float height) {
        super(pa, xPos, yPos, width, height);
        entrys = new ArrayList<G>();
    }

    public ListView(PApplet pa, Area area) {
        super(pa, area);
    }

    /**
     * Code that needs to be run before every draw of this Component.
     */
    @Override
    public void beforeDraw() {
        // TODO calculate size ?
    }


    @Override
    public void applyTheme(Theme theme) {
        outlineColor = toProcessingColor(theme.getOutlineColor());
        backgroundColor = toProcessingColor(theme.getBackgroundColor());
        super.applyTheme(theme);
    }

    @Override
    public void draw() {


        super.draw();
    }
}