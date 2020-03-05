package de.pgui.component;

import de.pgui.action.IAction;
import de.pgui.action.IMouseInput;
import de.pgui.event.MouseInputEvent;
import de.pgui.util.Area;
import processing.core.PApplet;
import processing.core.PVector;

/**
 *
 */
public abstract class ClickableComponent extends Component implements IMouseInput {
    final static protected int PRESSED = 1;
    final static protected int RELEASE = 2;
    final static protected int CLICK = 3;
    final static protected int DRAG = 4;
    final static protected int MOVE = 5;
    final static protected int ENTER = 6;
    final static protected int EXIT = 7;
    final static protected int WHEEL = 8;

    /**
     * The Clickable-{@link Area} of the Component
     */
    private Area componentClickableArea;

    /**
     * If the {@link Component} is clickable
     */
    public boolean isClickable = false;

    /**
     * The action if the Component registers a "click"
     */
    private IAction action;

    /**
     * @param pa     {@link Component#pa}
     * @param xPos   X-Position of the {@link Component}
     * @param yPos   Y-Position of the {@link Component}
     * @param width  Width of the {@link Component}
     * @param height Height of the {@link Component}
     * @see Area
     */
    public ClickableComponent(PApplet pa, int xPos, int yPos, float width, float height) {
        super(pa, xPos, yPos, width, height);
    }

    /**
     * @param pa   {@link Component#pa}
     * @param area {@link Component#componentArea}
     * @see Area for the diemension
     */
    public ClickableComponent(PApplet pa, Area area) {
        super(pa, area);
    }

    /**
     * Returns <code>true</code> if the Position <code>(xPos,YPos)</code>
     * is over / in the space of the ClickableArea otherwise false.
     *
     * @param pos the position
     * @return if the position is over the Component
     */
    protected boolean isOverClickableArea(PVector pos) {
        return componentClickableArea.isOverArea(pos);
    }

    /**
     * Returns <code>true</code> if the Position <code>(xPos,YPos)</code>
     * is over / in the space of the Component otherwise false.
     *
     * @param xPos the X of the Position
     * @param yPos the Y of the Position
     * @return if the position is over the Area
     */
    public boolean isOverClickableArea(int xPos, int yPos) {
        return componentClickableArea.isOverArea(xPos, yPos);
    }

    @Override
    public void handleMouseInputEvent(MouseInputEvent mouseInputEvent) {
        if (!mouseInputEvent.consumed && isClickable) {
            if (isOverClickableArea(mouseInputEvent.getMouseEvent().getX(), mouseInputEvent.getMouseEvent().getY())) {
                getAction().fireAction(this);
                mouseInputEvent.consume(); // TODo das könnte probleme geben
            }
        }
    }

    /**
     * Returns the {@link IAction} for the {@link ClickableComponent}
     *
     * @return the buttonAction
     */
    public IAction getAction() {
        return action;
    }

    /**
     * Sets the {@link IAction} for the {@link ClickableComponent}.
     *
     * @param action the {@link IAction} to set
     */
    public void setAction(IAction action) {
        this.action = action;
        if (componentClickableArea != null) {
            isClickable = true;
        }
    }

    public Area getComponentClickableArea() {
        return componentClickableArea;
    }

    public void setComponentClickableArea(Area componentClickableArea) {
        this.componentClickableArea = componentClickableArea;
        if (action != null) {
            isClickable = true;
        }
    }
}
