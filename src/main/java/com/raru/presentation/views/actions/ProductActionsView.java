package com.raru.presentation.views.actions;

import java.awt.event.ActionListener;

import javax.swing.*;

import com.raru.presentation.views.utils.GridView;

public class ProductActionsView extends GridView {
    private final int FIRST_COL = col();

    private final int CREATE_ROW = row();
    private final int READ_ROW = row();
    private final int UPDATE_ROW = row();
    private final int DELETE_ROW = row();
    private final int CLIENTS_ROW = row();

    private JButton createButton;
    private JButton readButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton clientsButton;

    /**
     * Constructs an instance of ProductActionsView.
     * This view displays buttons for creating, viewing, updating, and deleting
     * products.
     */
    public ProductActionsView() {
        createButton = new JButton("Create Product");
        createButton.setBounds(FIRST_COL, CREATE_ROW, COL_WIDTH, ROW_HEIGHT);
        add(createButton);

        readButton = new JButton("View Products");
        readButton.setBounds(FIRST_COL, READ_ROW, COL_WIDTH, ROW_HEIGHT);
        add(readButton);

        updateButton = new JButton("Update Product");
        updateButton.setBounds(FIRST_COL, UPDATE_ROW, COL_WIDTH, ROW_HEIGHT);
        add(updateButton);

        deleteButton = new JButton("Delete Product");
        deleteButton.setBounds(FIRST_COL, DELETE_ROW, COL_WIDTH, ROW_HEIGHT);
        add(deleteButton);

        clientsButton = new JButton("Ordered By");
        clientsButton.setBounds(FIRST_COL, CLIENTS_ROW, COL_WIDTH, ROW_HEIGHT);
        add(clientsButton);

        setSize();
        setLayout(null);
        setVisible(true);
        setTitle("Product");
    }

    public void setCreateButtonListener(ActionListener listener) {
        createButton.addActionListener(listener);
    }

    public void setReadButtonListener(ActionListener listener) {
        readButton.addActionListener(listener);
    }

    public void setUpdateButtonListener(ActionListener listener) {
        updateButton.addActionListener(listener);
    }

    public void setDeleteButtonListener(ActionListener listener) {
        deleteButton.addActionListener(listener);
    }

    public void setClientsButtonListener(ActionListener listener) {
        clientsButton.addActionListener(listener);
    }
}
