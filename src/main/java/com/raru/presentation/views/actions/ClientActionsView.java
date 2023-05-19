package com.raru.presentation.views.actions;

import java.awt.event.ActionListener;

import javax.swing.*;

import com.raru.presentation.views.utils.GridView;

public class ClientActionsView extends GridView {
    private final int FIRST_COL = col();

    private final int CREATE_ROW = row();
    private final int READ_ROW = row();
    private final int UPDATE_ROW = row();
    private final int DELETE_ROW = row();
    private final int PRODUCTS_ROW = row();

    private JButton createButton;
    private JButton readButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton productsButton;

    /**
     * Constructs an instance of ClientActionsView.
     * This view displays buttons for creating, viewing, updating, and deleting
     * clients.
     */
    public ClientActionsView() {
        createButton = new JButton("Create Client");
        createButton.setBounds(FIRST_COL, CREATE_ROW, COL_WIDTH, ROW_HEIGHT);
        add(createButton);

        readButton = new JButton("View Clients");
        readButton.setBounds(FIRST_COL, READ_ROW, COL_WIDTH, ROW_HEIGHT);
        add(readButton);

        updateButton = new JButton("Update Client");
        updateButton.setBounds(FIRST_COL, UPDATE_ROW, COL_WIDTH, ROW_HEIGHT);
        add(updateButton);

        deleteButton = new JButton("Delete Client");
        deleteButton.setBounds(FIRST_COL, DELETE_ROW, COL_WIDTH, ROW_HEIGHT);
        add(deleteButton);

        productsButton = new JButton("Ordered Products");
        productsButton.setBounds(FIRST_COL, PRODUCTS_ROW, COL_WIDTH, ROW_HEIGHT);
        add(productsButton);

        setSize();
        setLayout(null);
        setVisible(true);
        setTitle("Client");
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

    public void setProductsButtonListener(ActionListener listener) {
        productsButton.addActionListener(listener);
    }
}
