/**
 * 
 */
package de.pgui.component;

import de.pgui.event.MouseInputEvent;
import processing.core.PApplet;

/**
 * @author Noah Ruben
 *
 *
 * @created 23.12.2019
 */
public abstract class MouseInputComponent extends Component {

	public MouseInputComponent(PApplet pa) {
		super(pa);
	}
	
	abstract void onMouseInput(MouseInputEvent mouseInputEvent);

}
