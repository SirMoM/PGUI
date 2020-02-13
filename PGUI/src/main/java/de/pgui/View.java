/**
 * 
 */
package de.pgui;

import de.pgui.action.IMouseInput;
import de.pgui.component.Component;
import de.pgui.event.MouseInputEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Noah Ruben
 *
 *
 * @created 02.01.2020
 */
public class View {
	
	public final String name;
	
	private List<Component> components;
	
	/** TODO DOC missing
	 * @param name
	 * @param components
	 */
	public View(String name, List<Component> components) {
		this.name = name;
		this.components = components;
	}
	
	/** TODO DOC missing
	 * @param name name of the View
	 */
	public View(final String name) {
		this(name, new ArrayList<Component>());
	}

	public void draw() {
		for (Component component : components) {
			if (component.isVisible()) {
				component.beforeDraw();
				component.draw();
			}
		}
	}
	public void useKeyPressed() {
		for (Component component : components) {
//			TODO for IKeyInput / Input stream ?
//		if (component instanceof IMouseInput && component.isVisible()) {
//			((IMouseInput) component).handleMouseInputEvent(mouseInputEvent);;
//		}
		}

	}
	public void mouseClicked(MouseInputEvent mouseInputEvent) {
		for (Component component : components) {
			if (component instanceof IMouseInput && component.isVisible()) {
				((IMouseInput) component).handleMouseInputEvent(mouseInputEvent);
			}
		}
	}

	/** TODO DOC missing */
	public void addComponent(Component component) {
		components.add(component);
	}

	
	/** TODO DOC missing */
	public List<Component> getComponents() {
		return components;
	}
	

}
