package de.pgui.component.control;

import de.pgui.component.ClickableComponent;
import de.pgui.component.Component;
import de.pgui.event.MouseInputEvent;
import de.pgui.util.Area;
import de.pgui.util.ExpandModes;
import de.pgui.util.Theme;
import processing.core.PApplet;
import processing.event.MouseEvent;

public class Scrollbar extends ClickableComponent {

    private final int defaultWidth = 25;

    /**
     * Index of the position of the Scrollbar. Values is between 0 and {@link Scrollbar#steps}
     */
    private int positionIndex = 0;

    /**
     * How many Steps the scrollbar has
     */
    private int steps = 15;


    /**
     * Indicates which rotation the scrollbar has:
     * <ul>
     *     <li>true:  horizontal</li>
     *     <li>false: vertical</li>
     * </ul>
     *
     * @implNote default is true!
     */
    private boolean horizontalBar = true;

    private final Button moveRightTopButton;
    private final Button moveLeftDownButton;

    // TODo better names for the Thing in the middle(rect) and the space it can move in(space)
    private Area rect;
    private final Area space;

    private int lastDragX;


    /**
     * @param pa     {@link Component#pa}
     * @param xPos   X-Position of the {@link Component}
     * @param yPos   Y-Position of the {@link Component}
     * @param width  Width of the {@link Component}
     * @param height Height of the {@link Component}
     */
//    public Scrollbar(PApplet pa, int xPos, int yPos, float width, float height) {
//        super(pa, xPos, yPos, width, height);
//    }

    /**
     * @param pa            {@link Component#pa}
     * @param xPos          X-Position of the {@link Component}
     * @param yPos          Y-Position of the {@link Component}
     * @param horizontalBar Rotation of the scrollbar {@link Scrollbar#horizontalBar}
     * @param size          Width or height of the Component depending on the {@link Scrollbar#horizontalBar rotation}
     */
    public Scrollbar(PApplet pa, int xPos, int yPos, boolean horizontalBar, float size) {
        super(pa, xPos, yPos, 0, 0);
        if (horizontalBar) {
            this.getComponentArea().setWidth(size);
            this.getComponentArea().setHeight(defaultWidth);

            this.moveLeftDownButton = new Button(getPa(), getxPos(), getyPos(), defaultWidth, getComponentArea().getHeight());
            this.moveLeftDownButton.curve = 0;
            this.moveLeftDownButton.setText("<");
            this.moveLeftDownButton.setTextSize(20);
            this.moveLeftDownButton.resizeToNeededSize(ExpandModes.EXPAND_HORIZONTAL_RIGHT);
            this.moveLeftDownButton.setAction(x -> {
                Scrollbar.this.incrementIndex(-1);
            });

            this.moveRightTopButton = new Button(getPa(), (int) (getxPos() + size - defaultWidth), getyPos(), defaultWidth, getComponentArea().getHeight());
            this.moveRightTopButton.curve = 0;
            this.moveRightTopButton.setText(">");
            this.moveRightTopButton.setTextSize(20);
            this.moveRightTopButton.resizeToNeededSize(ExpandModes.EXPAND_HORIZONTAL_LEFT);
            this.moveRightTopButton.setAction(x -> Scrollbar.this.incrementIndex(1));

            this.space = new Area(getxPos() + moveLeftDownButton.getComponentArea().getWidth(), getyPos(), getComponentArea().getWidth() - moveLeftDownButton.getComponentArea().getWidth() - moveRightTopButton.getComponentArea().getWidth(), getComponentArea().getHeight());
            this.rect = new Area(space.getxPos(), getyPos(), defaultWidth, getComponentArea().getHeight());

        } else {
            this.getComponentArea().setWidth(defaultWidth);
            this.getComponentArea().setHeight(size);

            this.moveLeftDownButton = new Button(getPa(), getxPos(), getyPos(), defaultWidth, getComponentArea().getHeight());
            this.moveLeftDownButton.curve = 0;
            this.moveLeftDownButton.setText("v");
            this.moveLeftDownButton.setTextSize(20);
            this.moveLeftDownButton.resizeToNeededSize(ExpandModes.EXPAND_HORIZONTAL_RIGHT);
            this.moveLeftDownButton.setAction(x -> {
                Scrollbar.this.positionIndex--;
            });

            this.moveRightTopButton = new Button(getPa(), (int) (getxPos() + size - defaultWidth), getyPos(), defaultWidth, getComponentArea().getHeight());
            this.moveRightTopButton.curve = 0;
            this.moveRightTopButton.setText("^");
            this.moveRightTopButton.setTextSize(20);
            this.moveRightTopButton.resizeToNeededSize(ExpandModes.EXPAND_HORIZONTAL_RIGHT);
            this.moveRightTopButton.setAction(x -> Scrollbar.this.positionIndex++);

            this.space = new Area(getxPos() + moveLeftDownButton.getComponentArea().getWidth(), getyPos(), getComponentArea().getWidth() - moveLeftDownButton.getComponentArea().getWidth() - moveRightTopButton.getComponentArea().getWidth(), getComponentArea().getHeight());
        }
    }

    /**
     * Code that needs to be run before every draw of this Component.
     */
    @Override
    public void beforeDraw() {
        rect.setxPos(getXFromIndex());
    }

    /**
     * Draws the component onto the Sketch
     */
    @Override
    public void draw() {
        moveRightTopButton.draw();
        moveLeftDownButton.draw();
        getPa().stroke(toProcessingColor(this.outlineColor));
        if (rect.isOverArea(getPa().mouseX, getPa().mouseY)) {
            getPa().fill(toProcessingColor(this.backgroundHighlightColor));

        } else {
            getPa().fill(toProcessingColor(this.backgroundColor));
        }
        rect.drawSimpleRectFromArea(getPa());
        getPa().noFill();
        space.drawSimpleRectFromArea(getPa());
    }

    @Override
    public void handleMouseInputEvent(MouseInputEvent mouseInputEvent) {
        MouseEvent event = mouseInputEvent.getMouseEvent();
        if (event.getAction() == CLICK) {
            super.handleMouseInputEvent(mouseInputEvent);
        } else if (event.getAction() == DRAG && space.isOverArea(event.getX(), event.getY())) {
            if (rect.getxPos() + rect.getWidth() > event.getX()) {
                incrementIndex(1);
            }
            if (lastDragX < event.getX()) {
                incrementIndex(-1);
            }
            lastDragX = event.getX();
        }

        moveLeftDownButton.handleMouseInputEvent(mouseInputEvent);
        moveRightTopButton.handleMouseInputEvent(mouseInputEvent);
    }

    /**
     * This sets all color values Bases on the Theme
     *
     * @param theme The theme for the Component.
     */
    @Override
    public void applyTheme(Theme theme) {
        this.outlineColor = this.toProcessingColor(theme.getOutlineHighlightColor());
        this.backgroundColor = this.toProcessingColor(theme.getBackgroundColor());
        this.backgroundHighlightColor = this.toProcessingColor(theme.getBackgroundHighlightColor());
        this.textColor = toProcessingColor(theme.getTextColor());

        moveLeftDownButton.applyTheme(theme);
        moveRightTopButton.applyTheme(theme);

    }

    private float getXFromIndex() {
        if (positionIndex == steps) {
            return space.getxPos() + space.getWidth() - rect.getWidth();
        }
        float stepWidth = space.getWidth() / steps;
        float x = space.getxPos() + (stepWidth * positionIndex);
        return x;
    }

    public void incrementIndex(int i) {
        int temp = positionIndex + i;
        if (temp >= 0 && temp <= steps) positionIndex = temp;
    }
}
