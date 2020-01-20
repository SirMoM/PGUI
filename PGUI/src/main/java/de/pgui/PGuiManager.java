/**
 * 
 */
package de.pgui;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import de.pgui.component.Component;
import de.pgui.event.MouseInputEvent;
import de.pgui.util.Theme;
import processing.core.PApplet;

/**
 * @author Noah Ruben
 *
 *
 * @created 02.01.2020
 */
public class PGuiManager {
	
	// temporary default values 
	private final int windowHeight_D = 1000;
	private final int windowWidth_D = 1000;

	
	public Set<String> viewNames;
	private List<View> views;
	private View currentView;
	private Theme currentTheme;
	
	
	/**
	 * @param viewNames
	 * @param views
	 * @param currentView
	 */
	public PGuiManager(Set<String> viewNames, List<View> views, View currentView) {
		this.viewNames = viewNames;
		this.views = views;
		this.currentView = currentView;
	}

	public PGuiManager() {
		this(new HashSet<String>(), new ArrayList<View>(), null);
	}

	public void adjustSettings(PApplet pa) {
		pa.size(windowWidth_D, windowHeight_D);
	}

	public void draw() {
		currentView.draw();
	}

	public void useKeyPressed() {
		currentView.useKeyPressed();

	}

	public void mouseClicked(MouseInputEvent mouseInputEvent) {
		currentView.mouseClicked(mouseInputEvent);

	}

	public boolean registerView(View view) {
		if (currentView == null) {
			currentView = view;
			views.add(view);
			return true;
		} else {
			if (!views.contains(view.name)) {
				views.add(view);
				return true;
			}
		}
		return false;
	}

	public boolean goToView(final String viewName) {
		return false;
	}

	public void adjustSetup() {

	}

	/** TODO DOC missing */
	public void applyTheme(Theme theme) {
		this.currentTheme = theme;
		this.applyTheme();
		
	}

	/** TODO DOC missing */
	private void applyTheme() {
		for (View view : views) {
			for (Component component: view.getComponents()) {
				component.applyTheme(currentTheme);
			}
		}
	}

}
