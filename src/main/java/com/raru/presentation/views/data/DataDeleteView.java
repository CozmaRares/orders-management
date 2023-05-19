package com.raru.presentation.views.data;

import java.awt.Choice;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.*;

import com.raru.presentation.views.utils.GridView;

public class DataDeleteView extends GridView {
    private final int LABEL_COL = col();
    private final int FIELD_COL = col();

    private Choice itemChoice;
    private JButton button;

    /**
     * Constructs a DataDeleteView with the specified IDs.
     * 
     * @param ids The collection of IDs to be displayed in the itemChoice dropdown.
     */
    public DataDeleteView(Collection<String> ids) {
        int r = row();

        var label = new JLabel("Select id:");
        label.setBounds(LABEL_COL, r, COL_WIDTH, ROW_HEIGHT);
        add(label);

        itemChoice = new Choice();
        itemChoice.setBounds(FIELD_COL, r, COL_WIDTH, ROW_HEIGHT);
        add(itemChoice);

        ids.forEach(id -> itemChoice.add(id));

        button = new JButton("Delete");
        button.setBounds(FIELD_COL, row(), COL_WIDTH, ROW_HEIGHT);
        add(button);

        setSize();
        setLayout(null);
        setVisible(true);
    }

    public String getSelected() {
        return itemChoice.getSelectedItem();
    }

    public void setButtonListener(ActionListener listener) {
        button.addActionListener(listener);
    }
}
