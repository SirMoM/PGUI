package de.pgui.component.control;

import de.pgui.component.ClickableComponent;
import de.pgui.component.Component;
import de.pgui.util.Area;
import de.pgui.util.BasicColors;
import de.pgui.util.Theme;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

// TODO how to do Scroll weel

public class ListView<G> extends ClickableComponent {

    private List<G> entrys;
    private List<Component> subComponents;

    public ListView(PApplet pa, int xPos, int yPos, float width, float height) {
        super(pa, xPos, yPos, width, height);
        entrys = new ArrayList<G>();
        subComponents = new ArrayList<Component>();
    }

    public ListView(PApplet pa, Area area) {
        super(pa, area);
        entrys = new ArrayList<G>();
        subComponents = new ArrayList<Component>();
    }

    /**
     * Code that needs to be run before every draw of this Component.
     */
    @Override
    public void beforeDraw() {
        // TODO calculate size ?
        // TODO calc wich part of the Array to draw
    }

    private List<Component> generateMissingLabels() { // TODO übergabeparam wenn neu entrys ?
        float x = getxPos();
        float y = getyPos() + 1.5f;
        ArrayList<Component> missingComponents = new ArrayList<Component>();
        for (G entry : entrys) {
            Label tempLabel = new Label(getPa(), (int) x, (int) y, entry.toString());
            tempLabel.setWidth(getComponentArea().getWidth());
            tempLabel.setBorder(true);
            missingComponents.add(tempLabel);
            y += tempLabel.getComponentArea().getHeight();
        }
        return missingComponents;
    }

    @Override
    public void applyTheme(Theme theme) {
        outlineColor = toProcessingColor(theme.getOutlineColor());
        backgroundColor = toProcessingColor(theme.getBackgroundColor());
    }

    @Override
    public void draw() {
        getPa().noFill();
        getPa().stroke(BasicColors.BLACK);
        getPa().strokeWeight(3);
        getComponentArea().drawSimpleRectFromArea(getPa());
        getPa().strokeWeight(1);
        for (Component c : subComponents) {
            c.draw();
        }
    }

    public List<G> getEntrys() {
        return entrys;
    }

    public void setEntrys(List<G> entrys) {
        this.entrys = entrys;
        this.subComponents = this.generateMissingLabels();
    }

    /**
     * Appends the specified element to the end of this list (optional
     * operation).
     *
     * <p>Lists that support this operation may place limitations on what
     * elements may be added to this list.  In particular, some
     * lists will refuse to add null elements, and others will impose
     * restrictions on the type of elements that may be added.  List
     * classes should clearly specify in their documentation any restrictions
     * on what elements may be added.
     *
     * @param entry element to be appended to this list
     * @return <tt>true</tt> (as specified by {@link Collection#add})
     * @throws UnsupportedOperationException if the <tt>add</tt> operation
     *                                       is not supported by this list
     * @throws ClassCastException            if the class of the specified element
     *                                       prevents it from being added to this list
     * @throws NullPointerException          if the specified element is null and this
     *                                       list does not permit null elements
     * @throws IllegalArgumentException      if some property of this element
     *                                       prevents it from being added to this list
     */
    public boolean add(G entry) {
        this.subComponents = generateMissingLabels();
        return entrys.add(entry);
    }
}