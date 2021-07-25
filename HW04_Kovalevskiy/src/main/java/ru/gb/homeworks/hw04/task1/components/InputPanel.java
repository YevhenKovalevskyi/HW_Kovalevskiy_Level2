package ru.gb.homeworks.hw04.task1.components;

import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.swing.*;
import java.awt.*;

@EqualsAndHashCode(callSuper = true)
@Value
public class InputPanel extends JPanel {
    
    public static final String INPUT_DEFAULT_VALUE = "";
    
    JPanel inputPanel = new JPanel();
    JTextField textField = new JTextField();
    
    public InputPanel(JTextArea textArea, int frameWidth) {
        inputPanel.setLayout(new BorderLayout());
        inputPanel.setPreferredSize(new Dimension(frameWidth - 120, 50));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 4, 0, 0));
        inputPanel.add(tuneTextField(textArea, inputPanel), BorderLayout.NORTH);
    }
    
    private JTextField tuneTextField(JTextArea textArea, JPanel inputPanel) {
        textField.setEditable(true);
        textField.setPreferredSize(new Dimension(inputPanel.getWidth(), 35));
        textField.setFont(new Font("SansSerif", Font.PLAIN, 16));
        textField.setMargin(new Insets(0, 5, 0, 5));
        textField.setText(INPUT_DEFAULT_VALUE);
        textField.addActionListener(SendPanelListener.getActionListener(textArea, textField));

        return textField;
    }
}
