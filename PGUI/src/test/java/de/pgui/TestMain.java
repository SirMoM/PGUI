/**
 * 
 */
package de.pgui;

import de.pgui.component.control.Button;
import de.pgui.event.MouseInputEvent;
import processing.core.PApplet;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

/**
 * @author Noah Ruben
 *
 *
 * @created 23.12.2019
 */
public class TestMain extends PApplet {

	private PGuiManager manager;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] appletArgs = new String[] { TestMain.class.getName() };
		PApplet.main(appletArgs);
	}

	public TestMain() {
		super();
		this.manager = new PGuiManager();
		View view = new View("TEST");
		view.addComponent(new Button(this, 100, 100, 100, 20, "TEST"));
		this.manager.registerView(view);
		
	}

	/**
	 * Settings für Processing bevor Processing initialisiert wurde.
	 */
	public void settings() {
		super.settings();
		manager.adjustSettings(this);
	}

//	/**
//	 * Settings für Processing nachdem Processing initialisiert wurde. Hier kann auf
//	 * Processing zugegriffen werden.
//	 */
//	public void setup() {
//		super.setup();
//		manager.adjustSetup();
//	}

	public void draw() {
		manager.draw();
	}
	
	public void mousePressed(MouseEvent mouseEvent) {
		//TODO Das
	}

	public void mouseClicked(MouseEvent mouseEvent) {
		manager.mouseClicked(new MouseInputEvent(mouseEvent));
	}

	public void keyPressed(KeyEvent keyEvent) {
		super.keyPressed(keyEvent);
	}
}
