package com.raru.presentation.views.actions;

import java.awt.event.ActionListener;

import javax.swing.*;

import com.raru.presentation.views.utils.GridView;

public class BillActionsView extends GridView {
    private final int FIRST_COL = col();

    private final int READ_ROW = row();

    private JButton readButton;

    /**
     * Constructs an instance of BillActionsView.
     * This view displays buttons for viewing bills.
     */
    public BillActionsView() {
        readButton = new JButton("View Bills");
        readButton.setBounds(FIRST_COL, READ_ROW, COL_WIDTH, ROW_HEIGHT);
        add(readButton);

        setSize();
        setLayout(null);
        setTitle("Bill");
        setVisible(true);
    }

    public void setReadButtonListener(ActionListener listener) {
        readButton.addActionListener(listener);
    }
}
