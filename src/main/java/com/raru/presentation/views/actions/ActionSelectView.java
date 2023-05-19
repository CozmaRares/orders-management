package com.raru.presentation.views.actions;

import java.awt.event.ActionListener;

import javax.swing.*;

import com.raru.presentation.views.utils.GridView;

public class ActionSelectView extends GridView {
    private final int FIRST_COL = col();

    private final int CATERINCA_ROW = row();
    private final int BILL_ROW = row();
    private final int CATEGORY_ROW = row();
    private final int CLIENT_ROW = row();
    private final int ORDER_ROW = row();
    private final int PRODUCT_ROW = row();

    private JButton billButton;
    private JButton categoryButton;
    private JButton clientButton;
    private JButton orderButton;
    private JButton productButton;

    /**
     * Constructs an instance of ActionSelectView.
     * This view displays buttons for various actions related to bills,
     * categories, clients, orders, and products.
     */
    public ActionSelectView() {
        var label = new JLabel("Farmacia inimii CATENA!", SwingConstants.CENTER);
        label.setBounds(FIRST_COL, CATERINCA_ROW, COL_WIDTH, ROW_HEIGHT);
        add(label);

        billButton = new JButton("Bill actions");
        billButton.setBounds(FIRST_COL, BILL_ROW, COL_WIDTH, ROW_HEIGHT);
        add(billButton);

        categoryButton = new JButton("Category actions");
        categoryButton.setBounds(FIRST_COL, CATEGORY_ROW, COL_WIDTH, ROW_HEIGHT);
        add(categoryButton);

        clientButton = new JButton("Client actions");
        clientButton.setBounds(FIRST_COL, CLIENT_ROW, COL_WIDTH, ROW_HEIGHT);
        add(clientButton);

        orderButton = new JButton("Order actions");
        orderButton.setBounds(FIRST_COL, ORDER_ROW, COL_WIDTH, ROW_HEIGHT);
        add(orderButton);

        productButton = new JButton("Product actions");
        productButton.setBounds(FIRST_COL, PRODUCT_ROW, COL_WIDTH, ROW_HEIGHT);
        add(productButton);

        setSize();
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setBillButtonListener(ActionListener listener) {
        billButton.addActionListener(listener);
    }

    public void setCategoryButtonListener(ActionListener listener) {
        categoryButton.addActionListener(listener);
    }

    public void setClientButtonListener(ActionListener listener) {
        clientButton.addActionListener(listener);
    }

    public void setOrderButtonListener(ActionListener listener) {
        orderButton.addActionListener(listener);
    }

    public void setProductButtonListener(ActionListener listener) {
        productButton.addActionListener(listener);
    }
}
