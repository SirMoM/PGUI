package de.pgui.component.control;

import de.pgui.action.IAction;
import de.pgui.component.Component;
import de.pgui.component.LabeledComponent;
import de.pgui.util.Area;
import de.pgui.util.Theme;
import processing.core.PApplet;

/**
 * @author Noah Ruben
 */
public class RadioRutton extends LabeledComponent {

    /**
     * True if the RadioButton is selected
     */
    private boolean selected = false;

    /**
     * Default implementation for the RadioButtonAction.
     * Toggles the selected property of the {@link RadioRutton}
     *
     * @implNote Action: <code>selected = !selected</code>
     */
    private IAction defaultAction = new IAction() {
        @Override
        public void fireAction(Component component) {
            RadioRutton.this.selected = !RadioRutton.this.selected;
        }
    };

    /**
     * TODO Missing DOC
     *
     * @param pa   {@link Component#pa}
     * @param xPos X-Position of the {@link Component}
     * @param yPos Y-Position of the {@link Component}
     * @param text The text of the {@link LabeledComponent}
     */
    public RadioRutton(PApplet pa, int xPos, int yPos, String text) {
        super(pa, xPos, yPos, text);
        setComponentClickableArea(this.componentArea);
        setAction(defaultAction);
    }

    /**
     * @param pa     {@link Component#pa}
     * @param xPos   X-Position of the {@link Component}
     * @param yPos   Y-Position of the {@link Component}
     * @param width  Width of the {@link Component}
     * @param height Height of the {@link Component}
     * @see Area
     */
    public RadioRutton(PApplet pa, int xPos, int yPos, float width, float height) {
        super(pa, xPos, yPos, width, height);
        setComponentClickableArea(this.componentArea);
        setAction(defaultAction);
    }

    /**
     * @param pa     {@link Component#pa}
     * @param xPos   X-Position of the {@link Component}
     * @param yPos   Y-Position of the {@link Component}
     * @param width  Width of the {@link Component}
     * @param height Height of the {@link Component}
     * @see Area
     */
    public RadioRutton(PApplet pa, int xPos, int yPos, float width, float height, String text) {
        super(pa, xPos, yPos, width, height);
        this.setText(text);
        setComponentClickableArea(this.componentArea);
        setAction(defaultAction);
    }

    /**
     * @param pa   {@link Component#pa}
     * @param area {@link Component#componentArea}
     * @see Area for the diemension
     */
    public RadioRutton(PApplet pa, Area area) {
        super(pa, area);
    }

    @Override
    public void beforeDraw() {
    }

    @Override
    public void draw() {
        boolean highlight = isOverComponent(getPa().mouseX, getPa().mouseY);
        float sizeOuterCircle = textSize / 2 + (this.componentArea.getHeight() / 2 - textSize / 2);
        float sizeInnerCircle = sizeOuterCircle - (this.textSize / 4);
        // TODO better name
        float circleX = getxPos() + sizeOuterCircle / 2 + 0.5f;
        float circleY = getyPos() + (this.componentArea.getHeight() / 2);

        getPa().strokeWeight(2);
        if (this.selected && highlight) {
            getPa().noFill();
            getPa().stroke(outlineHighlightColor);
            getPa().circle(circleX, circleY, sizeOuterCircle);

            getPa().fill(backgroundHighlightColor);
            getPa().noStroke();
            getPa().circle(circleX, circleY, sizeInnerCircle);
        } else if (selected) {
            getPa().noFill();
            getPa().stroke(outlineColor);
            getPa().circle(circleX, circleY, sizeOuterCircle);

            getPa().noStroke();
            getPa().fill(backgroundColor);
            getPa().circle(circleX, circleY, sizeInnerCircle + 1);
        } else {
            getPa().noFill();
            getPa().stroke(outlineColor);
            getPa().circle(circleX, circleY, sizeOuterCircle);
        }
        getPa().strokeWeight(1);
        getPa().stroke(this.outlineColor);
        Area temp = this.componentArea;
        this.componentArea = new Area(temp.getxPos() + sizeOuterCircle, temp.getyPos(), temp.getWidth(), temp.getHeight());
        super.draw();
        this.componentArea = temp;
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
