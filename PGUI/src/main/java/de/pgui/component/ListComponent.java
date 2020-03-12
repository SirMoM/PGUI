package de.pgui.component;

// TODO how to do Scroll wheel
// TODO Untermenu
// TODO CLickable
// TODO index
// TODO selection modes
// TODO TO String converter for lines
// TODO edible
// TODO values
// TODO Sub menu
// TODO scrollBar horr
// TODO scroll verti


import de.pgui.component.control.Button;
import de.pgui.component.control.Scrollbar;
import de.pgui.util.*;
import processing.core.PApplet;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * @param <G> Type of the to displayed values
 */
public abstract class ListComponent<G> extends LabeledComponent {
    /**
     * All values for the ListComponent
     */
    protected List<G> values;

    /**
     * True if the values have change since the last render <br><br>
     * <p>
     * This triggers a lot of actions.
     */
    protected boolean hasValuesChanged = false;

    /**
     * All cells for the ListComponent
     */
    protected List<Cell> cells;

    private IToStringConverter converter;

    /**
     * Index of the selected Cell <br>
     * Index is -1 if nothing is selected
     */
    private int index = -1;

    /**
     * Index of the first visible {@link Cell}
     */
    protected int showCellFrom = 0;

    protected int cantShowCellFrom;

    /**
     * TODO missing DOC
     */
    private SelectionModes selectionMode = SelectionModes.SINGLE;

    /**
     * The horizontal Scrollbar of the List
     */
    protected Scrollbar horizontalScrollBar;

    /**
     * The vertical Scrollbar of the List
     */
    protected Scrollbar verticalScrollBar;
    private G g;

    /**
     * @param pa     {@link Component#pa}
     * @param xPos   X-Position of the {@link Component}
     * @param yPos   Y-Position of the {@link Component}
     * @param width  Width of the {@link Component}
     * @param height Height of the {@link Component}
     * @see Area
     */
    public ListComponent(PApplet pa, int xPos, int yPos, float width, float height) {
        super(pa, xPos, yPos, width, height);
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
     * @param g element to be appended to this list
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
    public boolean add(G g) {
        hasValuesChanged = values.add(g) && cells.add(new Cell(converter.toString(g)));
        return hasValuesChanged;
    }

    /**
     * Removes the first occurrence of the specified element from this list,
     * if it is present (optional operation).  If this list does not contain
     * the element, it is unchanged.  More formally, removes the element with
     * the lowest index <tt>i</tt> such that
     * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>
     * (if such an element exists).  Returns <tt>true</tt> if this list
     * contained the specified element (or equivalently, if this list changed
     * as a result of the call).
     *
     * @param o element to be removed from this list, if present
     * @return <tt>true</tt> if this list contained the specified element
     * @throws ClassCastException            if the type of the specified element
     *                                       is incompatible with this list
     *                                       (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException          if the specified element is null and this
     *                                       list does not permit null elements
     *                                       (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws UnsupportedOperationException if the <tt>remove</tt> operation
     *                                       is not supported by this list
     */
    public boolean remove(G g) {
        int index = values.indexOf(g);
        if (cells.contains(index)) {
            cells.remove(index);
        } else {
            // TODo maybe search the cells list
            throw new NoSuchElementException("Could not remove item");

        }
        hasValuesChanged = values.remove(g);
        return hasValuesChanged;
    }


    public IToStringConverter getConverter() {
        return converter;
    }

    public void setConverter(IToStringConverter converter) {
        this.converter = converter;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public SelectionModes getSelectionMode() {
        return selectionMode;
    }

    public void setSelectionMode(SelectionModes selectionMode) {
        this.selectionMode = selectionMode;
    }

    // ######################################################################################################################
    // #                                     CELL                                                                           #
    // ######################################################################################################################
    protected class Cell extends Button {
        /**
         * TODO Missing DOC
         *
         * @param pa   {@link Component#pa}
         * @param xPos X-Position of the {@link Component}
         * @param yPos Y-Position of the {@link Component}
         * @param text The text of the {@link LabeledComponent}
         */
        public Cell(PApplet pa, int xPos, int yPos, String text) {
            super(pa, xPos, yPos, ListComponent.this.getWidth(), ListComponent.this.calculateTextHeight(), text);
            curve = 0;
        }

        public Cell(String text) {
            super(ListComponent.this.getPa(), (int) ListComponent.this.getxPos(), (int) ListComponent.this.getyPos(), ListComponent.this.getWidth(), ListComponent.this.calculateTextHeight(), text);
            curve = 0;
        }

        @Override
        public void applyTheme(Theme theme) {
            super.applyTheme(theme);
            backgroundColor = toProcessingColor(BasicColors.WHITE);
            outlineColor = toProcessingColor(theme.getOutlineColor());
            outlineHighlightColor = toProcessingColor(theme.getOutlineHighlightColor());
            backgroundHighlightColor = toProcessingColor(theme.getBackgroundHighlightColor());
        }
    }
}