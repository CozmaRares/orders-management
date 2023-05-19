package com.raru.presentation.views.data;

import java.awt.Choice;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.util.Collection;
import java.util.Map;

import javax.swing.*;

import com.raru.presentation.views.utils.GridView;
import com.raru.utils.Utils;

public class DataUpdateView extends GridView {
    private final int LABEL_COL = col();
    private final int FIELD_COL = col();

    private Choice itemChoice;
    private JButton button;

    /**
     * Constructs a DataUpdateView with the specified fields and IDs.
     * 
     * @param fields The map of field names and corresponding Component instances.
     * @param ids    The collection of IDs to be displayed in the itemChoice
     *               dropdown.
     */
    public DataUpdateView(Map<String, ? extends Component> fields, Collection<String> ids) {
        int r = row();

        var label = new JLabel("Select id:");
        label.setBounds(LABEL_COL, r, COL_WIDTH, ROW_HEIGHT);
        add(label);

        itemChoice = new Choice();
        itemChoice.setBounds(FIELD_COL, r, COL_WIDTH, ROW_HEIGHT);
        add(itemChoice);

        ids.forEach(id -> itemChoice.add(id));

        for (var field : fields.entrySet()) {
            r = row();

            label = new JLabel(Utils.capitalize(field.getKey()) + ":");
            label.setBounds(LABEL_COL, r, COL_WIDTH, ROW_HEIGHT);
            add(label);

            var f = field.getValue();
            f.setBounds(FIELD_COL, r, COL_WIDTH, ROW_HEIGHT);
            add(f);
        }

        button = new JButton("Update");
        button.setBounds(FIELD_COL, row(), COL_WIDTH, ROW_HEIGHT);
        add(button);

        setSize();
        setLayout(null);
        setVisible(true);
    }

    public String getSelected() {
        return itemChoice.getSelectedItem();
    }

    public void setChoiceListener(ItemListener listener) {
        itemChoice.addItemListener(listener);
    }

    public void setButtonListener(ActionListener listener) {
        button.addActionListener(listener);
    }
}
