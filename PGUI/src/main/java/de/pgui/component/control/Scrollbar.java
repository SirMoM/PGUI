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

    private enum states {INIT, WAITING, STOP}

    private states state = states.INIT;


    private final int defaultWidth = 25;

    /**
     * Index of the position of the Scrollbar. Values is between 0 and {@link Scrollbar#steps}
     */
    private int positionIndex = 0;

    /**
     * How many Steps the scrollbar has
     */
    private int steps = 10;


    /**
     * Indicates which rotation the scrollbar has:
     * <ul>
     *     <li>true:  horizontal</li>
     *     <li>false: vertical</li>
     * </ul>
     *
     * @implNote default is true!
     */
    private boolean horizontalBar;

    private final Button moveRightTopButton;
    private final Button moveLeftDownButton;

    // TODo better names for the Thing in the middle(rect) and the space it can move in(space)
    // TODO change these if the base
    private Area rect;
    private final Area space;

    /**
     * @param pa            {@link Component#pa}
     * @param xPos          X-Position of the {@link Component}
     * @param yPos          Y-Position of the {@link Component}
     * @param horizontalBar Rotation of the scrollbar {@link Scrollbar#horizontalBar}
     * @param size          Width or height of the Component depending on the {@link Scrollbar#horizontalBar rotation}
     */
    public Scrollbar(PApplet pa, int xPos, int yPos, boolean horizontalBar, float size) {
        super(pa, xPos, yPos, 0, 0);
        this.horizontalBar = horizontalBar;
        if (this.horizontalBar) {
            super.setWidth(size);
            super.setHeight(defaultWidth);
            this.moveLeftDownButton = new Button(getPa(), (int) getxPos(), (int) getyPos(), defaultWidth, getHeight());
            this.moveLeftDownButton.curve = 0;
            this.moveLeftDownButton.setText("<");
            this.moveLeftDownButton.setTextSize(20);
            this.moveLeftDownButton.resizeToNeededSize(ExpandModes.EXPAND_HORIZONTAL_RIGHT);
            this.moveLeftDownButton.setAction(x -> {
                Scrollbar.this.incrementIndex(-1);
            });

            this.moveRightTopButton = new Button(getPa(), (int) (getxPos() + size - defaultWidth), (int) getyPos(), defaultWidth, getHeight());
            this.moveRightTopButton.curve = 0;
            this.moveRightTopButton.setText(">");
            this.moveRightTopButton.setTextSize(20);
            this.moveRightTopButton.resizeToNeededSize(ExpandModes.EXPAND_HORIZONTAL_LEFT);
            this.moveRightTopButton.setAction(x -> Scrollbar.this.incrementIndex(1));

            this.space = new Area(getxPos() + moveLeftDownButton.getWidth(), getyPos(), getWidth() - moveLeftDownButton.getWidth() - moveRightTopButton.getWidth(), getHeight());
            this.rect = new Area(space.getxPos(), getyPos(), defaultWidth, getHeight());

        } else {
            super.setWidth(defaultWidth);
            super.setHeight(size);

            this.moveLeftDownButton = new Button(getPa(), (int) getxPos(), 0, getWidth(), 0);
            this.moveLeftDownButton.curve = 0;
            this.moveLeftDownButton.setText("v");
            this.moveLeftDownButton.setTextSize(20);
            this.moveLeftDownButton.resizeToNeededSize(ExpandModes.EXPAND);
            moveLeftDownButton.setyPos((int) (getyPos() + size - moveLeftDownButton.getHeight()));
            this.moveLeftDownButton.setAction(x -> Scrollbar.this.incrementIndex(1));

            this.moveRightTopButton = new Button(getPa(), (int) getxPos(), (int) getyPos(), defaultWidth, size);
            this.moveRightTopButton.curve = 0;
            this.moveRightTopButton.setText("^");
            this.moveRightTopButton.setTextSize(20);
            this.moveRightTopButton.resizeToNeededSize(ExpandModes.EXPAND);
            this.moveRightTopButton.setAction(x -> Scrollbar.this.incrementIndex(-1));

            this.componentArea.setWidth(moveLeftDownButton.getWidth());

            this.space = new Area(getxPos(), getyPos() + moveRightTopButton.getHeight(), getWidth(), getHeight() - moveLeftDownButton.getHeight() - moveRightTopButton.getHeight());
            this.rect = new Area(space.getxPos(), getyPos() + 200, componentArea.getWidth(), defaultWidth);
        }
    }

    /**
     * Code that needs to be run before every draw of this Component.
     */
    @Override
    public void beforeDraw() {
        if (horizontalBar) {
            rect.setxPos(getXFromIndex());
        } else {
            rect.setyPos(getYFromIndex());
        }
    }

    /**
     * Draws the component onto the Sketch
     */
    @Override
    public void draw() {
        this.correctRect();
        moveRightTopButton.draw();
        moveLeftDownButton.draw();
        getPa().stroke(toProcessingColor(this.outlineColor));

        if (rect.isOverArea(getPa().mouseX, getPa().mouseY) || state == states.WAITING) {
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
        moveLeftDownButton.handleMouseInputEvent(mouseInputEvent);
        moveRightTopButton.handleMouseInputEvent(mouseInputEvent);
        if (event.getAction() == CLICK) {
            if (space.isOverArea(event.getX(), event.getY())) {
                if (horizontalBar) {
                    rect.setxPos(event.getX());
                    positionIndex = getIndexFromX();
                } else {
                    rect.setyPos(event.getY());
                    positionIndex = getIndexFromY();
                }
            }
        } else if (event.getAction() == PRESSED && space.isOverArea(event.getX(), event.getY())) {
            state = states.WAITING;
        } else if (event.getAction() == RELEASE && state == states.WAITING) {
            state = states.STOP;
        } else if (event.getAction() == DRAG && state == states.WAITING) {
            if (horizontalBar) {
                if (space.isOverArea(event.getX(), (int) (space.getyPos() + 1)) && event.getX() < (space.getxPos() + space.getWidth()) - defaultWidth) {
                    rect.setxPos(event.getX());
                    positionIndex = getIndexFromX();
                    mouseInputEvent.consume();
                }
            } else {
                if (space.isOverArea((int) (space.getxPos() + 1), event.getY()) && event.getY() < (space.getyPos() + space.getHeight()) - defaultWidth) {
                    rect.setyPos(event.getY());
                    positionIndex = getIndexFromY();
                    mouseInputEvent.consume();
                }
            }

        }
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
        float stepWidth = space.getWidth() / steps;
        float x = space.getxPos() + (stepWidth * positionIndex);
        return x;
    }

    private int getIndexFromX() {
        float stepWidth = space.getWidth() / steps;
        int x = Math.round((rect.getxPos() - space.getxPos()) / stepWidth);
        return x;
    }

    private float getYFromIndex() {
        float stepHeight = space.getHeight() / steps;
        float y = space.getyPos() + (stepHeight * positionIndex);
        return y;
    }

    private int getIndexFromY() {
        float stepHeight = space.getHeight() / steps;
        int y = Math.round((rect.getyPos() - space.getyPos()) / stepHeight);
        return y;
    }

    public void incrementIndex(int i) {
        int temp = positionIndex + i;
        if (temp >= 0 && temp <= steps) positionIndex = temp;
    }

    private void correctRect() {
        if (horizontalBar) {
            float maxX = space.getxPos() + space.getWidth() - rect.getWidth();
            if (rect.getxPos() > maxX) {
                rect.setxPos(maxX);
            }
        } else {
            float maxY = space.getyPos() + space.getHeight() - rect.getHeight();
            if (rect.getyPos() > maxY) {
                rect.setyPos(maxY);
            }
        }
    }

    public int getPositionIndex() {
        return positionIndex;
    }

    public void setPositionIndex(int positionIndex) {
        this.positionIndex = positionIndex;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    @Override
    public void setxPos(float xPos) {
        componentArea.setxPos(xPos);
        space.setxPos(xPos);
        rect.setxPos(xPos);
        moveLeftDownButton.setxPos((int) xPos);
        moveRightTopButton.setxPos((int) xPos);
    }

    @Override
    public void setyPos(float yPos) {
        componentArea.setyPos(yPos);
        space.setyPos(yPos);
        rect.setyPos(yPos);
        moveLeftDownButton.setyPos(yPos);
        moveRightTopButton.setyPos(yPos);
    }

    @Override
    public void setHeight(float height) {
        super.setHeight(height);
        space.setHeight(height - moveLeftDownButton.getHeight() - moveRightTopButton.getHeight());
        moveLeftDownButton.setyPos(getyPos() + height);
    }

    @Override
    public void setWidth(float width) {
        super.setWidth(width);
        space.setWidth(width - moveLeftDownButton.getWidth() - moveRightTopButton.getWidth());
        moveRightTopButton.setxPos(getxPos() + width - moveRightTopButton.getWidth());
    }
}
