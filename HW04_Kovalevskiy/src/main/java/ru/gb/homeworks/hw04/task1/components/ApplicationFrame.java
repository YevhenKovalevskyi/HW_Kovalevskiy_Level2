package ru.gb.homeworks.hw04.task1.components;

import javax.swing.*;
import java.awt.*;

public class ApplicationFrame extends JFrame {
    
    static int frameWidth, frameHeight, xCoord, yCoord;
    
    static {
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        
        frameWidth = 500;
        frameHeight = 600;
        xCoord = (int)size.getWidth() / 2 - frameWidth / 2;
        yCoord = (int)size.getHeight() / 2 - frameHeight / 2;
    }
    
    public ApplicationFrame() {
        setTitle("Chat v1.0");
        setBounds(xCoord, yCoord, frameWidth, frameHeight);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());

        TextAreaPanel textAreaPanel = new TextAreaPanel();
        SendPanel sendPanel = new SendPanel(frameWidth, textAreaPanel.getTextArea());
        
        add(textAreaPanel.getTextAreaPanel(), BorderLayout.CENTER);
        add(sendPanel.getSendPanel(), BorderLayout.SOUTH);
    
        setVisible(true);
    }
}
