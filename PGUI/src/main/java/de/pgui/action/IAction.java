/**
 *
 */
package de.pgui.action;

import de.pgui.component.Component;

/**
 * @author Noah Ruben
 *
 *
 * @created 19.12.2019
 */
public interface IAction {

	/**
	 * This Function is called by the {@link Component} if it is pressed.
	 * @param component the component which is fires Action
	 */
	void fireAction(Component component);

}
