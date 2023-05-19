package com.raru.presentation.views.utils;

import javax.swing.JFrame;

public abstract class GridView extends JFrame {
    private int COL_IOTA = 0;
    private int ROW_IOTA = 0;

    private final int WINDOW_HANDLES_SIZE = 35;

    private final int MARGIN_X = 10;
    private final int MARGIN_Y = 10;

    private final int ROW_GAP = 10;
    private final int COL_GAP = 10;

    protected final int COL_WIDTH = 250;
    protected final int ROW_HEIGHT = 30;

    protected int row() {
        return MARGIN_Y + (ROW_IOTA++) * (ROW_HEIGHT + ROW_GAP);
    }

    protected int col() {
        return MARGIN_X + (COL_IOTA++) * (COL_WIDTH + COL_GAP);
    }

    private int getWindowWidth() {
        return MARGIN_X * 2 + COL_WIDTH * COL_IOTA + COL_GAP * (COL_IOTA - 1);
    }

    private int getWindowHeight() {
        return WINDOW_HANDLES_SIZE + MARGIN_Y * 2
                + ROW_HEIGHT * ROW_IOTA + ROW_GAP * (ROW_IOTA - 1);
    }

    public void setSize() {
        setSize(getWindowWidth(), getWindowHeight());
    }
}
