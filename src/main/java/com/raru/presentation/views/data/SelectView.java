package com.raru.presentation.views.data;

import com.raru.presentation.views.utils.GridView;

import java.awt.Choice;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

public class SelectView extends GridView {
    private final int LABEL_COL = col();
    private final int FIELD_COL = col();

    private final int FIRST_ROW = row();
    private final int SECOND_ROW = row();

    private Choice itemChoice;
    private JButton button;

    /**
     * Constructs a SelectView with the given list of IDs.
     * 
     * @param ids the list of IDs to be displayed in the view
     */
    public SelectView(List<String> ids) {
        var label = new JLabel("Select id: ");
        label.setBounds(LABEL_COL, FIRST_ROW, COL_WIDTH, ROW_HEIGHT);
        add(label);

        itemChoice = new Choice();
        itemChoice.setBounds(FIELD_COL, FIRST_ROW, COL_WIDTH, ROW_HEIGHT);
        add(itemChoice);
        ids.forEach(id -> itemChoice.add(id));

        button = new JButton("Go!");
        button.setBounds(FIELD_COL, SECOND_ROW, COL_WIDTH, ROW_HEIGHT);
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
