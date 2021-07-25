package ru.gb.homeworks.hw04.task1.components;

import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.swing.*;
import java.awt.*;

@EqualsAndHashCode(callSuper = true)
@Value
public class SendPanel extends JPanel {
    
    JPanel sendPanel = new JPanel();
    
    public SendPanel(int frameWidth, JTextArea textArea) {
        sendPanel.setLayout(new BorderLayout());
        sendPanel.setBorder(BorderFactory.createEmptyBorder(4, 8, 8, 8));
    
        InputPanel inputPanel = new InputPanel(textArea, frameWidth);
        ButtonPanel buttonPanel = new ButtonPanel(textArea, inputPanel.getTextField());
    
        sendPanel.add(inputPanel.getInputPanel(), BorderLayout.WEST);
        sendPanel.add(buttonPanel.getButtonPanel(), BorderLayout.EAST);
    }
}
