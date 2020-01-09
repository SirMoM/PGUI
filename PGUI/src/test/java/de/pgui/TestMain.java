/**
 * 
 */
package de.pgui;

import java.util.List;

import de.pgui.component.control.Button;
import de.pgui.event.MouseInputEvent;
import processing.core.PApplet;
import processing.event.Event;
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

	/**
	 * @param manager {@link PGuiManager} for this App.
	 */
	public TestMain(PGuiManager manager) {
		super();
		this.manager = manager;
	}

	public TestMain() {
		super();
		this.manager = new PGuiManager();
		View view = new View("TEST");
		view.addComponent(new Button(this, 100, 100, 100, 20, "TEST"));
		this.manager.registerView(view);
		System.out.println("as");
		
	}

	/**
	 * Settings für Processing bevor Processing initialisiert wurde.
	 */
	public void settings() {
		super.settings();
		manager.adjustSettings(this);
	}

	/**
	 * Settings für Processing nachdem Processing initialisiert wurde. Hier kann auf
	 * Processing zugegriffen werden.
	 */
	public void setup() {
		super.setup();
		this.frameRate(1);
		manager.adjustSetup();
	}

	public void draw() {
		super.draw();
		manager.draw();
		this.rect(200, 200, 100, 200);
		System.out.println("as");
	}
	
	public void mousePressed() {
		System.out.println("SAD");
	}

	public void mouseClicked() {
		System.out.println("ASD");
	}

	public void keyPressed(KeyEvent keyEvent) {
		super.keyPressed(keyEvent);
	}
}
