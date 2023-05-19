package com.raru.presentation.views.data;

import java.awt.Choice;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

import com.raru.model.Category;
import com.raru.model.Product;
import com.raru.presentation.views.utils.GridView;

public class AddProductView extends GridView {
    private final int LABEL_COL = col();
    private final int FIELD_COL = col();

    private final int CATEGORY_ROW = row();
    private final int PRODUCT_ROW = row();
    private final int BUTTON_ROW = row();

    private Choice categoryChoice;
    private Choice productChoice;
    private JButton button;

    /**
     * Constructs an instance of AddProductView with the given categories and
     * products.
     *
     * @param categories The list of categories to populate the category choice
     *                   with.
     * @param products   The list of products to populate the product choice with.
     */
    public AddProductView(List<Category> categories, List<Product> products) {
        var label = new JLabel("Select category:");
        label.setBounds(LABEL_COL, CATEGORY_ROW, COL_WIDTH, ROW_HEIGHT);
        add(label);

        categoryChoice = new Choice();
        categoryChoice.setBounds(FIELD_COL, CATEGORY_ROW, COL_WIDTH, ROW_HEIGHT);
        add(categoryChoice);
        categories.forEach(c -> categoryChoice.add(c.getId()));

        label = new JLabel("Select product:");
        label.setBounds(LABEL_COL, PRODUCT_ROW, COL_WIDTH, ROW_HEIGHT);
        add(label);

        productChoice = new Choice();
        productChoice.setBounds(FIELD_COL, PRODUCT_ROW, COL_WIDTH, ROW_HEIGHT);
        add(productChoice);
        products.forEach(p -> productChoice.add(p.getId()));

        button = new JButton("Add");
        button.setBounds(FIELD_COL, BUTTON_ROW, COL_WIDTH, ROW_HEIGHT);
        add(button);

        setSize();
        setLayout(null);
        setVisible(true);
    }

    public String getSelectedCategory() {
        return categoryChoice.getSelectedItem();
    }

    public String getSelectedProduct() {
        return productChoice.getSelectedItem();
    }

    public void setButtonListener(ActionListener listener) {
        button.addActionListener(listener);
    }
}
