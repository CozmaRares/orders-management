package com.raru.presentation.views.actions;

import java.awt.event.ActionListener;

import javax.swing.*;

import com.raru.presentation.views.utils.GridView;

public class CategoryActionsView extends GridView {
    private final int FIRST_COL = col();

    private final int CREATE_ROW = row();
    private final int READ_ROW = row();
    private final int UPDATE_ROW = row();
    private final int DELETE_ROW = row();
    private final int PRODUCT_ROW = row();

    private JButton createButton;
    private JButton readButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton addProductButton;

    /**
     * Constructs an instance of CategoryActionsView.
     * This view displays buttons for creating, viewing, updating and deleting
     * categories, and adding products.
     */
    public CategoryActionsView() {
        createButton = new JButton("Create Category");
        createButton.setBounds(FIRST_COL, CREATE_ROW, COL_WIDTH, ROW_HEIGHT);
        add(createButton);

        readButton = new JButton("View Categories");
        readButton.setBounds(FIRST_COL, READ_ROW, COL_WIDTH, ROW_HEIGHT);
        add(readButton);

        updateButton = new JButton("Update Category");
        updateButton.setBounds(FIRST_COL, UPDATE_ROW, COL_WIDTH, ROW_HEIGHT);
        add(updateButton);

        deleteButton = new JButton("Delete Category");
        deleteButton.setBounds(FIRST_COL, DELETE_ROW, COL_WIDTH, ROW_HEIGHT);
        add(deleteButton);

        addProductButton = new JButton("Add Product");
        addProductButton.setBounds(FIRST_COL, PRODUCT_ROW, COL_WIDTH, ROW_HEIGHT);
        add(addProductButton);

        setSize();
        setLayout(null);
        setVisible(true);
        setTitle("Category");
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

    public void setAddProductButtonListener(ActionListener listener) {
        addProductButton.addActionListener(listener);
    }
}
