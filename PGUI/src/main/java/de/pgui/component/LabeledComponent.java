/**
 *
 */
package de.pgui.component;

import de.pgui.util.Area;
import de.pgui.util.ExpandModes;
import de.pgui.util.Theme;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PFont;

/**
 * @author Noah Ruben
 * @created 09.01.2020
 */
public abstract class LabeledComponent extends ClickableComponent {
    // TODO DOC missing
    // TODO force show TEXT

    private String fontName = "Monospaced.plain";

    private float textSize = 50;

    private String text;

    private PFont font = getPa().createFont(fontName, textSize);

    protected float margin = textSize / 4;

    /**
     * TODO Missing DOC
     *
     * @param pa   {@link Component#pa}
     * @param xPos X-Position of the {@link Component}
     * @param yPos Y-Position of the {@link Component}
     */
    public LabeledComponent(PApplet pa, int xPos, int yPos, String text) {
        super(pa, xPos, yPos, 0, 0);
        this.text = text;
        this.resizeToNeededSize(ExpandModes.EXPAND);
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

        float width = getWidth();
        float cWidth = calculateTextWidth();
        if (cWidth > width) {
            float scale = width / cWidth;
            int charsToDisplay = (int) (getText().toCharArray().length * scale);
            this.getPa().text(getText().toCharArray(), 0, charsToDisplay, getxPos() + xOffset, getyPos() + yOffset);
        } else {
            this.getPa().text(getText(), getxPos() + xOffset, getyPos() + yOffset);
        }
    }


    // TODO Missing DOC
    @Override
    public void applyTheme(Theme theme) {
        textColor = toProcessingColor(theme.getTextColor());
        textHighlightColor = toProcessingColor(theme.getTextHighlightColor());
    }

    @Override
    public void beforeDraw() {
        getPa().textFont(font);
    }

    // TODO Missing DOC
    public void resizeToNeededSize(ExpandModes mode) {
        // TODO resize the LabeledComponent-Component based on the size the Text occupies and the
        float newWidth = calculateTextWidth();
        float newHeight = calculateTextHeight();
        switch (mode) {
            case EXPAND:
                setWidth(newWidth);
                setHeight(newHeight);
            case EXPAND_HORIZONTAL_RIGHT:
                setWidth(newWidth);
                break;
            case EXPAND_HORIZONTAL_LEFT:
                float endX = getxPos() + getWidth();
                setxPos((int) (endX - newWidth));
                setWidth(newWidth); // TODO shift xPos to left
                break;
            case EXPAND_VERTICAL_TOP:
                setHeight(newHeight);
                break;
            case EXPAND_VERTICAL_BOTTOM:
                setHeight(newHeight); // TODO shift yPost down
                break;
        }

    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    protected float calculateTextWidth() {
        getPa().textFont(getPa().createFont(fontName, textSize));
        float textWidth = getPa().textWidth(getText());
        float newSize = Math.round((textWidth + margin * 2) * 100) / 100;
        return newSize;
    }

    protected float calculateTextHeight() {
        float newSize = Math.round((textSize + (textSize * 0.25)) * 100) / 100;
        return newSize;
    }

    public float getTextSize() {
        return textSize;
    }

    public void setTextSize(float textSize) {
        getPa().createFont(fontName, textSize);
        this.textSize = textSize;
    }
}
