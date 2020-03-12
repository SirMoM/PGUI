package de.pgui.component.control;

import de.pgui.component.ListComponent;
import de.pgui.event.MouseInputEvent;
import de.pgui.util.IToStringConverter;
import de.pgui.util.Theme;
import processing.core.PApplet;

import java.util.ArrayList;

public class ListView<G> extends ListComponent<G> {

    /**
     * {@link ContextMenu Context-Menu} of this {@link ListComponent}
     */
    private ContextMenu subMenu;

    /**
     * @param pa     {@link Component#pa}
     * @param xPos   X-Position of the {@link Component}
     * @param yPos   Y-Position of the {@link Component}
     * @param width  Width of the {@link Component}
     * @param height Height of the {@link Component}
     * @see Area
     */
    public ListView(PApplet pa, int xPos, int yPos, float width, float height) {
        super(pa, xPos, yPos, width, height);
        this.values = new ArrayList();
        this.cells = new ArrayList();
        this.horizontalScrollBar = new Scrollbar(getPa(), xPos, 0, true, width);
        this.horizontalScrollBar.setyPos((int) (yPos + height - horizontalScrollBar.getHeight()));
        this.horizontalScrollBar.setVisible(false);
        this.verticalScrollBar = new Scrollbar(getPa(), xPos, yPos, false, height);
        this.verticalScrollBar.setxPos(xPos + width - verticalScrollBar.getWidth());
        this.verticalScrollBar.setVisible(false);

        this.setConverter(new IToStringConverter<String>() {
            @Override
            public String toObject(String string) {
                return string;
            }

            @Override
            public String toString(String object) {
                return object;
            }
        });
    }

    @Override
    public void beforeDraw() {
        if (hasValuesChanged) {
            float y = getyPos();
            float x = getxPos();

            float lastIncrement = 0;
            for (int i = showCellFrom, cellsSize = cells.size(); i < cellsSize; i++) {
                Cell cell = cells.get(i);
                cell.setyPos(y);
                cell.setxPos(x);
                cell.setWidth(getWidth() * (getxPos() / cell.getxPos()));
                if (verticalScrollBar.isVisible())
                    cell.setWidth(getWidth() - ListView.this.verticalScrollBar.getWidth());
                lastIncrement = cell.getHeight();
                y += lastIncrement;
            }
            if (y > componentArea.getHeight() - horizontalScrollBar.getHeight() - lastIncrement) {
                verticalScrollBar.setVisible(true);
            }
        }

        if (horizontalScrollBar.isVisible()) {
            horizontalScrollBar.beforeDraw();
        }

        if (verticalScrollBar.isVisible()) {
            verticalScrollBar.beforeDraw();
            horizontalScrollBar.setWidth(getWidth() - verticalScrollBar.getWidth());
            showCellFrom = verticalScrollBar.getPositionIndex();
        } else {
            horizontalScrollBar.setWidth(getWidth());
        }

    }

    @Override
    public void draw() {
        for (int i = showCellFrom; i < cells.size(); i++) {
            Cell cell = cells.get(i);
            if (cell.getyPos() < getyPos() + getHeight() - cell.getHeight()) {
                cell.draw();
            } else {
                verticalScrollBar.setSteps(cells.size() - i);
                break;
            }
        }

        if (horizontalScrollBar.isVisible()) horizontalScrollBar.draw();
        if (verticalScrollBar.isVisible()) verticalScrollBar.draw();
        getPa().noFill();
        getPa().strokeWeight(2);
        componentArea.drawSimpleRectFromArea(getPa());
        getPa().strokeWeight(1);
    }

    @Override
    public void applyTheme(Theme theme) {
        super.applyTheme(theme);
        cells.forEach(cell -> cell.applyTheme(theme));
        horizontalScrollBar.applyTheme(theme);
        verticalScrollBar.applyTheme(theme);
    }

    @Override
    public void handleMouseInputEvent(MouseInputEvent mouseInputEvent) {
        if (horizontalScrollBar.isVisible()) horizontalScrollBar.handleMouseInputEvent(mouseInputEvent);
        if (verticalScrollBar.isVisible()) verticalScrollBar.handleMouseInputEvent(mouseInputEvent);

        super.handleMouseInputEvent(mouseInputEvent);
    }
}
