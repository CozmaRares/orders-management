package com.raru.presentation.views.data;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.*;

import com.raru.presentation.views.utils.GridView;
import com.raru.utils.Utils;

public class DataCreateView extends GridView {
    private final int LABEL_COL = col();
    private final int FIELD_COL = col();

    private JButton button;

    /**
     * Constructs a DataCreateView with the specified fields.
     * 
     * @param fields The map of field names and corresponding Component instances.
     */
    public DataCreateView(Map<String, ? extends Component> fields) {
        for (var field : fields.entrySet()) {
            int r = row();

            var label = new JLabel(Utils.capitalize(field.getKey()) + ":");
            label.setBounds(LABEL_COL, r, COL_WIDTH, ROW_HEIGHT);

            var f = field.getValue();
            f.setBounds(FIELD_COL, r, COL_WIDTH, ROW_HEIGHT);

            add(label);
            add(f);
        }

        button = new JButton("Create");
        button.setBounds(FIELD_COL, row(), COL_WIDTH, ROW_HEIGHT);

        setSize();
        add(button);
        setLayout(null);
        setVisible(true);
    }

    public void setButtonListener(ActionListener listener) {
        button.addActionListener(listener);
    }
}
