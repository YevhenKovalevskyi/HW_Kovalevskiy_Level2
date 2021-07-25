package ru.gb.homeworks.hw04.task1.components;

import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.swing.*;
import java.awt.*;

@EqualsAndHashCode(callSuper = true)
@Value
public class TextAreaPanel extends JPanel {
    
    JScrollPane textAreaPanel = new JScrollPane();
    JTextArea textArea = new JTextArea();
    
    public TextAreaPanel() {
        textAreaPanel.setLayout(new ScrollPaneLayout());
        textAreaPanel.setBorder(BorderFactory.createEmptyBorder(12, 12, 0, 12));
        textAreaPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        textAreaPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        textAreaPanel.setViewportView(tuneTextArea());
    }
    
    private JTextArea tuneTextArea() {
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        textArea.setFont(new Font("SansSerif", Font.PLAIN, 16));
        textArea.setMargin(new Insets(5, 8, 5, 8));
        
        return textArea;
    }
}
