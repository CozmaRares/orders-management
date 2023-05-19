package com.raru.presentation.views.data;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.*;

import com.raru.utils.Utils;

public class DataReadView extends JFrame {
    /**
     * Constructs a DataReadView to display the data from a list of objects.
     * 
     * @param list  the list of objects to be displayed
     * @param clazz the class of the objects in the list
     */
    public <T> DataReadView(List<T> list, Class<T> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        String[] columnNames = Arrays
                .asList(fields)
                .stream()
                .map(f -> f.getName())
                .collect(Collectors.toList())
                .toArray(new String[0]);
        String[][] data = list
                .stream()
                .map(obj -> Utils.getFieldValuesArray(obj))
                .collect(Collectors.toList())
                .toArray(new String[0][0]);
        var table = new JTable(data, columnNames);
        var pane = new JScrollPane(table);

        table.setBounds(0, 0, 1500, 900);

        add(pane);
        setVisible(true);
        setSize(1500, 900);
        setTitle(clazz.getSimpleName());
    }
}
