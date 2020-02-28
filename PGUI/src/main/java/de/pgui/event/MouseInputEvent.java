/**
 * 
 */
package de.pgui.event;

import processing.event.MouseEvent;

import java.time.LocalTime;

/**
 * @author Noah Ruben
 * @created 23.12.2019
 */
public class MouseInputEvent {
    private final LocalTime when;
    private final MouseEvent mouseEvent;
    public boolean consumed = false;

    /**
     * @param mouseEvent {@link MouseEvent}
     */
    public MouseInputEvent(MouseEvent mouseEvent) {
        this.when = LocalTime.now();
        this.mouseEvent = mouseEvent;
    }

    /**
     * @return the when
     */
    public LocalTime getWhen() {
        return when;
    }

    /**
     * @param event The other event to compare time with
     * @return the time between events in nanoseconds
     */
    public int timeBetweenEvent(MouseInputEvent event) {
        return event.getWhen().getNano() - this.getWhen().getNano();
    }

    /**
     * @return the mouseEvent
     */
    public MouseEvent getMouseEvent() {
        return mouseEvent;
    }

    public void consume() {
        // TODO consumes Event pos finalize ? what happens if it is in a list ?? NullPointer ?
        consumed = true;
    }

}
