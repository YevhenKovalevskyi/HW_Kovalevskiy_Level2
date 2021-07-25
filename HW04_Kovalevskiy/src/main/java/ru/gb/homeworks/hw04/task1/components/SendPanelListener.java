package ru.gb.homeworks.hw04.task1.components;

import javax.swing.*;
import java.awt.event.ActionListener;

public class SendPanelListener {
    
    public static ActionListener getActionListener(JTextArea textArea, JTextField textField) {
        return e -> {
            String currText = textField.getText();

            if (!currText.equals(InputPanel.INPUT_DEFAULT_VALUE)) {
                textArea.append(currText + "\n");
                textField.setText(InputPanel.INPUT_DEFAULT_VALUE);
                textField.requestFocus();
            }
        };
    }
}
