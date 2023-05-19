package com.raru.presentation.views.actions;

import java.awt.event.ActionListener;

import javax.swing.*;

import com.raru.presentation.views.utils.GridView;

public class OrderActionsView extends GridView {
    private final int FIRST_COL = col();

    private final int CREATE_ROW = row();
    private final int READ_ROW = row();

    private JButton createButton;
    private JButton readButton;

    /**
     * Constructs an instance of OrderActionsView.
     * This view displays buttons for creating and viewing orders.
     */
    public OrderActionsView() {
        createButton = new JButton("Create Order");
        createButton.setBounds(FIRST_COL, CREATE_ROW, COL_WIDTH, ROW_HEIGHT);
        add(createButton);

        readButton = new JButton("View Orders");
        readButton.setBounds(FIRST_COL, READ_ROW, COL_WIDTH, ROW_HEIGHT);
        add(readButton);

        setSize();
        setLayout(null);
        setVisible(true);
        setTitle("Order");
    }

    public void setCreateButtonListener(ActionListener listener) {
        createButton.addActionListener(listener);
    }

    public void setReadButtonListener(ActionListener listener) {
        readButton.addActionListener(listener);
    }
}
