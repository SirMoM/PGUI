/**
 * 
 */
package de.pgui;

import processing.core.PApplet;
import processing.event.Event;
import processing.event.MouseEvent;

/**
 * @author Noah Ruben
 *
 *
 * @created 23.12.2019
 */
public class TestMain extends PApplet{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] appletArgs = new String[] { TestMain.class.getName() };
		PApplet.main(appletArgs);
	}
	@Override
	public void mouseClicked(MouseEvent event) {
		super.mouseClicked(event);
		System.out.println(event);
	}
	
	@Override
	public void draw() {
		super.draw();
		rect(1, 1, 20, 20);
	}
	
}
