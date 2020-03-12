package de.pgui.component.control;

import de.pgui.action.IAction;
import de.pgui.component.ClickableComponent;
import de.pgui.component.Component;
import de.pgui.event.MouseInputEvent;
import de.pgui.util.Area;
import de.pgui.util.BasicColors;
import de.pgui.util.ExpandModes;
import de.pgui.util.Theme;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;


public class ListViewTrash<G> extends ClickableComponent {

    private List<G> entrys;
    private List<G> entrysToShow;
    private List<Component> subComponents;

    private List<ClickableComponent> subMenu;
    private boolean showSubMenu = false;

    private boolean horizontalScrollBar = false;
    private boolean verticalScrollBar = false;


    public ListViewTrash(PApplet pa, int xPos, int yPos, float width, float height) {
        super(pa, xPos, yPos, width, height);
        entrys = new ArrayList<G>();
        subComponents = new ArrayList<Component>();
        subMenu = new ArrayList<ClickableComponent>();
    }

    public ListViewTrash(PApplet pa, Area area) {
        super(pa, area);
        entrys = new ArrayList<G>();
        entrysToShow = new ArrayList<G>();
        subComponents = new ArrayList<Component>();
        subMenu = new ArrayList<ClickableComponent>();
        addToSubMenu("item");
        addToSubMenu("item2");
    }

    /**
     * Code that needs to be run before every draw of this Component.
     */
    @Override
    public void beforeDraw() {
        float totalHeight = entrys.size() * 20;
        if (totalHeight > componentArea.getHeight()) {
            System.out.println("Oversize");
        }
        // TODO calculate size ?
        // TODO calc wich part of the Array to draw
        int x = getPa().mouseX;
        int y = getPa().mouseY;
        Area tempArea = componentArea.clone();
        tempArea.setxPos(componentArea.getxPos() - 200);
        tempArea.setyPos(componentArea.getyPos() - 200);
        tempArea.setHeight(componentArea.getHeight() + 400);
        tempArea.setWidth(componentArea.getWidth() + 400);
        if (!tempArea.isOverArea(x, y)) {
            showSubMenu = false;
        }
    }

    private List<Component> generateMissingLabels() { // TODO übergabeparam wenn neu entrys ?
        float x = getxPos();
        float y = getyPos() + 1.5f;
        ArrayList<Component> missingComponents = new ArrayList<Component>();
        for (G entry : entrys) {
            Label tempLabel = new Label(getPa(), (int) x, (int) y, getWidth(), 0);
            tempLabel.setText(entry.toString());
            tempLabel.setBorder(true);
            tempLabel.resizeToNeededSize(ExpandModes.EXPAND_VERTICAL_TOP);
            missingComponents.add(tempLabel);
            y += tempLabel.getHeight();
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
//        ddrawSimpleRectFromArea(getPa());
        getPa().strokeWeight(1);
        for (Component c : subComponents) {
            c.draw();
        }
        if (showSubMenu) {
            for (Component c : subMenu) {
                c.draw();
            }
        }
    }

    @Override
    public void handleMouseInputEvent(MouseInputEvent mouseInputEvent) {
        if (isOverComponent(mouseInputEvent.getMouseEvent().getX(), mouseInputEvent.getMouseEvent().getY())) {
            switch (mouseInputEvent.getMouseEvent().getAction()) {
                case WHEEL:
                    if (horizontalScrollBar) {
                    }
                    break;
                case CLICK:
                    int button = mouseInputEvent.getMouseEvent().getButton();
                    if (button == PConstants.RIGHT) {
                        showSubMenu = !showSubMenu;
                        PVector pos = new PVector(getPa().mouseX, getPa().mouseX);
                        for (int i = 0; i < subMenu.size(); i++) {
                            float y = i * subMenu.get(i).getHeight() + 5; // Push buttons down
                            subMenu.get(i).setPos(pos.add(0, y));
                        }

                    }
                    if (button == PConstants.LEFT) {
                        if (showSubMenu) {
                            for (ClickableComponent c : subMenu) {
                                c.handleMouseInputEvent(mouseInputEvent);
                            }
                        }
                        // TODO action auf dem Listen element / index
                    }
                default:
//                    throw new IllegalStateException("Unexpected value: " + mouseInputEvent.getMouseEvent().getAction());
//                    System.out.println("Unexpected value: " + mouseInputEvent.getMouseEvent().getAction());
            }
            super.handleMouseInputEvent(mouseInputEvent);
        }
    }

    public List<ClickableComponent> getSubMenu() {
        return subMenu;
    }

    public void setSubMenu(List<ClickableComponent> subMenu) {
        this.subMenu = subMenu;
    }

    public boolean addToSubMenu(String item) {
        Button button = new Button(getPa(), 0, 0, item);
        button.setAction(new IAction() {
            @Override
            public void fireAction(Component component) {
                System.out.println(component.getClass().getName() + "Submenu");
            }
        });
        button.applyTheme(Theme.loadTheme("theme.pgui"));
        button.curve = 0;
        return this.subMenu.add(button);
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