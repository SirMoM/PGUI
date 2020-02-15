/**
 *
 */
package de.pgui.component;

import de.pgui.util.Area;
import de.pgui.util.ExpandModes;
import de.pgui.util.Theme;
import processing.core.PApplet;
import processing.core.PConstants;

/**
 * @author Noah Ruben
 * @created 09.01.2020
 */
public abstract class LabeledComponent extends ClickableComponent {

    protected float textSize = 20;
    private String text;
    protected float margin = 10f;

    /** TODO Missing DOC
     * @param pa     {@link Component#pa}
     * @param xPos   X-Position of the {@link Component}
     * @param yPos   Y-Position of the {@link Component}
     *
     */
    public LabeledComponent(PApplet pa, int xPos, int yPos, String text) {
        super(pa, xPos, yPos, 0, 0);
        this.text = text;
        this.setHeight(textSize + 2);
        this.resizeToNeededSize(ExpandModes.EXPAND_HORIZONTAL_RIGHT);
    }

    /**
     * @param pa     {@link Component#pa}
     * @param xPos   X-Position of the {@link Component}
     * @param yPos   Y-Position of the {@link Component}
     * @param width  Width of the {@link Component}
     * @param height Height of the {@link Component}
     * @see Area
     */
    public LabeledComponent(PApplet pa, int xPos, int yPos, float width, float height) {
        super(pa, xPos, yPos, width, height);
    }

    /**
     * @param pa   {@link Component#pa}
     * @param area {@link Component#componentArea}
     * @see Area for the diemension
     */
    public LabeledComponent(PApplet pa, Area area) {
        super(pa, area);
    }

    @Override
    public void draw() {
        this.getPa().textSize(textSize);
        this.getPa().fill(textColor);
        this.getPa().textAlign(PConstants.LEFT, PConstants.CENTER);
        // yOffset is half of the height - the forth of the textSize
        // to have the text in the middle of the button
        float yOffset = componentArea.getHeight() / 2 - textSize / 4;
        // xOffset is a fifth of the Width
        // to have the text start left of the boarder
        float xOffset = margin;

        this.getPa().text(getText(), getxPos() + xOffset, getyPos() + yOffset);
    }


    @Override
    public void applyTheme(Theme theme) {
        textColor = toProcessingColor(theme.getTextColor());
        textHighlightColor = toProcessingColor(theme.getTextHighlightColor());
    }

    // TODO Missing DOC
    public void resizeToNeededSize(ExpandModes mode) {
        // TODO resize the LabeledComponent-Component based on the size the Text occupies and the
        switch (mode) {
            case EXPAND_HORIZONTAL_RIGHT:
                // calculate new Size and Round to after 2 digits after the decimal point
                float newSize = Math.round((getPa().textWidth(getText()) + margin * 2) * 100) / 100;
                System.out.println(this.getClass() + "Set the size to " + newSize);
                setWidth(newSize);
                break;
            case EXPAND_HORIZONTAL_LEFT:
                break;
            case EXPAND_VERTICAL_LEFT:
                break;
            case EXPAND_VERTICAL_RIGHT:
                break;
        }

    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
