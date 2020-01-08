/**
 * 
 */
package de.pgui;

import java.util.List;

import de.pgui.component.Component;

/**
 * @author Noah Ruben
 *
 *
 * @created 02.01.2020
 */
public class View {
	
	public final String name;
	
	private List<Component> components;
	/**
	 * @param name name of the View
	 */
	public View(final String name) {
		this.name = name;
	}
	
	public void draw() {
		
	}
	public void useKeyPressed() {
		
	}
	public void mouseClicked() {
		
	}


}
