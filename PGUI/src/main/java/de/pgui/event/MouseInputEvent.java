/**
 * 
 */
package de.pgui.event;

import java.time.LocalTime;
import processing.event.MouseEvent;

/**
 * @author Noah Ruben
 *
 * @created 23.12.2019
 */
public class MouseInputEvent {
	private final LocalTime when;
	private final MouseEvent mouseEvent;

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
	 * @return the mouseEvent
	 */
	public MouseEvent getMouseEvent() {
		return mouseEvent;
	}
	
	public void consume() {
		// TODO consums Event pos finalize ? what happens if it is in a list ?? Nullpointer ?
	}
	
}
