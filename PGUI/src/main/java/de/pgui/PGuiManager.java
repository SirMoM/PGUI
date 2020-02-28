/**
 *
 */
package de.pgui;

import de.pgui.component.Component;
import de.pgui.event.MouseInputEvent;
import de.pgui.util.BasicColors;
import de.pgui.util.Theme;
import processing.core.PApplet;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Noah Ruben
 * @created 02.01.2020
 */
public class PGuiManager {

    // temporary default values
    private final int windowHeight_D = 1000;
    private final int windowWidth_D = 1500;


    private View currentView;
    private Theme currentTheme;
    private Map<String, View> views;

    private MouseInputEvent lastMouseInputEvent = new MouseInputEvent(null);


    ///** TODO DO THIS WITH A MAP
    // * @param viewNames
    // * @param views
    // * @param currentView
    // */
    //public PGuiManager(Set<String> viewNames, List<View> views, View currentView) {
    //	this.viewNames = viewNames;
    //	this.views = views;
    //	this.currentView = currentView;
    //}
//
    public PGuiManager() {
        this.views = new HashMap<String, View>();
    }

    public void adjustSettings(PApplet pa) {
        pa.size(windowWidth_D, windowHeight_D);
    }

    public void draw(PApplet pa) {
        pa.background(BasicColors.WHITE);
        currentView.draw();
    }

    public void useKeyPressed() {
        currentView.useKeyPressed();
    }

    public void handleMouseEvent(MouseInputEvent mouseInputEvent) {
        currentView.handleMouseEvent(mouseInputEvent);
    }

    public boolean registerView(View view) {
        if (currentView == null) {
            currentView = view;
            views.put(view.name, view);
            return true;
        } else {
            if (!views.containsKey(view.name)) {
                views.put(view.name, view);
                return true;
            }
        }
        return false;
    }

    public boolean goToView(final String viewName) {
        currentView = views.get(viewName);
        return false;
    }

    public void adjustSetup(PApplet pa) {
        pa.frameRate(120f);
        //for (String fontName : PFont.list()) {
        //	if (fontName.contains("Monospaced.bold")){
        //		PFont pFont = pa.createFont(fontName, 20);
        //		pa.textFont(pFont);
        //	}
        //}
        pa.textFont(pa.createFont("Monospaced.plain", 20));
    }

    /**
     * TODO DOC missing
     */
    public void applyTheme(Theme theme) {
        this.currentTheme = theme;
        this.applyTheme();

    }

    /**
     * TODO DOC missing
     */
    private void applyTheme() {
        for (View view : views.values()) {
            for (Component component : view.getComponents()) {
                component.applyTheme(currentTheme);
            }
        }
    }

}
