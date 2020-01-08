/**
 * 
 */
package de.pgui;

import java.util.List;
import java.util.Set;

/**
 * @author Noah Ruben
 *
 *
 * @created 02.01.2020
 */
public class PGuiManager {

	public Set<String> viewNames;
	private List<View> views;
	private View currentView;

	public void adjustsSettings() {

	}
	
	public void draw() {
		currentView.draw();
	}
	public void useKeyPressed() {
		currentView.useKeyPressed();;
		
	}
	public void mouseClicked() {
		currentView.mouseClicked();
		
	}

	public boolean registerView(View view) {
		return false;
	}

	public boolean goToView(final String viewName) {
		return false;
	}

}
