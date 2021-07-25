package ru.gb.homeworks.hw04.task1.components;

import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.swing.*;
import java.awt.*;

@EqualsAndHashCode(callSuper = true)
@Value
public class ButtonPanel extends JPanel {
    
    JPanel buttonPanel = new JPanel();
    JButton sendButton = new JButton();
    
    public ButtonPanel(JTextArea textArea, JTextField inputField) {
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        buttonPanel.add(tuneSendButton(textArea, inputField));
    }
    
    private JButton tuneSendButton(JTextArea textArea, JTextField textField) {
        sendButton.setText("Send");
        sendButton.setFont(new Font("SansSerif", Font.PLAIN, 18));
        sendButton.setFocusPainted(false);
        sendButton.addActionListener(SendPanelListener.getActionListener(textArea, textField));
        
        return sendButton;
    }
}
