/**
 * 
 */
package de.pgui;

import de.pgui.action.IAction;
import de.pgui.component.Component;
import de.pgui.component.control.Button;
import de.pgui.component.control.Label;
import de.pgui.component.control.ListView;
import de.pgui.component.control.RadioRutton;
import de.pgui.event.MouseInputEvent;
import de.pgui.util.Area;
import de.pgui.util.ExpandModes;
import de.pgui.util.Theme;
import processing.core.PApplet;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

import java.io.File;
import java.io.IOException;

import static de.pgui.util.BasicColors.*;


/**
 * @author Noah Ruben
 * @created 23.12.2019
 */
public class TestMain extends PApplet {

	private PGuiManager manager;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Theme theme = new Theme(BASE_COLOR_L, HILI_COLOR_L, BLACK, BLACK, WHITE, WHITE);
		try {
			theme.saveTheme(new File("theme.pgui"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		String[] appletArgs = new String[]{TestMain.class.getName()};
		PApplet.main(appletArgs);
	}

	public TestMain() {
		super();
		this.manager = new PGuiManager();
	}

	/**
	 * Settings for Processing before Processing has been initialised.
	 */
	public void settings() {
		manager.adjustSettings(this);
	}

	/**
	 * Settings for Processing after Processing has been initialised.
	 * It is possible now to access the fully initialised {@link PApplet} Object.
	 */
	public void setup() {
		super.setup();
		manager.adjustSetup(this);

		View view = new View("TEST");
		View view2 = new View("TEST1");

		Button test = new Button(this, 100, 100, 500, 40, "TEST");
		test.resizeToNeededSize(ExpandModes.EXPAND_HORIZONTAL_RIGHT);
		test.setAction((x) -> {
			System.out.println("Sogar mit lambda");
			TestMain.this.manager.goToView("TEST1");
		});

		Button tryBtn = new Button(this, 500, 300, "Das ist ein Button :P");
		tryBtn.resizeToNeededSize(ExpandModes.EXPAND_HORIZONTAL_RIGHT);
		tryBtn.setAction(new IAction() {
			@Override
			public void fireAction(Component component) {
				component.setVisible(false);
			}
		});

		RadioRutton radioBtn = new RadioRutton(this, 225, 125, "RadioBtn");
		Label label = new Label(this, 300, 300, 100, 20);
		label.setText("Das ist ein Label :P");
		label.setBorder(true);
		label.setBackground(true);
		label.setUnderline(true);


		ListView<String> listView = new ListView<String>(this, new Area(50, 200, 400, 500));
		listView.add("1ADS");
		listView.add("2ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZ"); // TODO DON'T overdraw text do it in de.pgui.component.LabeledComponent
		listView.add("3ADS");

		ListView<Integer> intListView = new ListView<Integer>(this, new Area(50, 200, 400, 500));
		intListView.add(0);
		intListView.add(0);
		intListView.add(0);
		intListView.add(0);
		intListView.add(Integer.MAX_VALUE);
		intListView.add(0);
		intListView.add(0);
		intListView.add(0);

		view.addComponent(test);
		view.addComponent(tryBtn);
		view.addComponent(listView);
		view2.addComponent(intListView);
		view2.addComponent(label);
		view2.addComponent(test);
		view2.addComponent(radioBtn);

		this.manager.registerView(view);
		this.manager.registerView(view2);


		File file = new File("theme.pgui");
		this.manager.applyTheme(new Theme(BASE_COLOR_L, HILI_COLOR_L, BLACK, BLACK, BLACK, BLACK));
	}

	public void draw() {
		manager.draw(this);
	}

	public void mousePressed(MouseEvent mouseEvent) {
		// TODO implement
	}

	public void mouseClicked(MouseEvent mouseEvent) {
		manager.mouseClicked(new MouseInputEvent(mouseEvent));
	}

	public void keyPressed(KeyEvent keyEvent) {
		super.keyPressed(keyEvent);
	}
}
