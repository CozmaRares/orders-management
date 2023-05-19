package com.raru.presentation.views.utils;

import javax.swing.*;

public class SuccessView extends JFrame {
    public SuccessView() {
        var j = new JLabel("Success", SwingConstants.CENTER);
        add(j);
        setVisible(true);
        setSize(200, 200);
    }
}
