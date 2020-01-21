/**
 * 
 */
package de.pgui;

import java.io.File;

import de.pgui.component.control.Button;
import de.pgui.event.MouseInputEvent;
import de.pgui.util.Theme;
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

//			File file = new File("theme.pgui");
//			file.createNewFile();
//			Theme theme = new Theme(BasicColors.WHITE, BasicColors.HILI_COLOR_L, BasicColors.BLACK, BasicColors.HILI_COLOR_D, BasicColors.BLACK, BasicColors.WHITE);
//			theme.saveTheme(file);
	}

	public TestMain() {
		super();

		this.manager = new PGuiManager();

		View view = new View("TEST");
		view.addComponent(new Button(this, 100, 100, 100, 20, "TEST"));
		this.manager.registerView(view);

		File file = new File("theme.pgui");
		this.manager.applyTheme(Theme.loadTheme(file));
	}

	/**
	 * Settings f�r Processing bevor Processing initialisiert wurde.
	 */
	public void settings() {
		super.settings();
		manager.adjustSettings(this);
	}

	/**
	 * Settings f�r Processing nachdem Processing initialisiert wurde. Hier kann auf
	 * Processing zugegriffen werden.
	 */
	public void setup() {
		super.setup();
		manager.adjustSetup();
	}

	public void draw() {
		manager.draw();
	}

	public void mousePressed(MouseEvent mouseEvent) {
		// TODO implemnt
	}

	public void mouseClicked(MouseEvent mouseEvent) {
		manager.mouseClicked(new MouseInputEvent(mouseEvent));
	}

	public void keyPressed(KeyEvent keyEvent) {
		super.keyPressed(keyEvent);
	}
}
