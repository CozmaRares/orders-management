package com.raru.presentation.views.utils;

import javax.swing.*;

public class ErrorView extends JFrame {
    public ErrorView(String message) {
        var j = new JLabel("<html>" + message + "</html>", SwingConstants.CENTER);
        add(j);
        setVisible(true);
        setSize(400, 200);
    }
}
