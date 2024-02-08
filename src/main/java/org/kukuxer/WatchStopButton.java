package org.kukuxer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.text.StyleConstants.setIcon;


public class WatchStopButton extends JPanel {
    long startTime;
    boolean active;
    long timeOnScreen;
    String formattedTime;
    JButton button;
    JTextField textField;
    Timer timer;

    public WatchStopButton(JTextField textField, Color color) {

        this.textField = textField;
        button = new JButton();
        button.setBackground(color);
        button.setForeground(color);
       button.setIcon(new ImageIcon("Vote please!.jpeg"));  // Provide your icon path
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        setOpaque(false);
        setPreferredSize(new Dimension(100, 100));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform an action when the button is clicked
                if (active) {
                    stopTimer();
                } else {
                    startTimer();
                    active = true;
                }
            }
        });
        setLayout(new BorderLayout());
        add(button, BorderLayout.CENTER);
    }

    private void startTimer() {
        startTime = System.currentTimeMillis();
        button.setText("Stop");

        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double timeAfterStart = (System.currentTimeMillis() - startTime) / 1000.0;
                formattedTime = String.format("%.2f", timeAfterStart);
                textField.setText(formattedTime);
            }
        });
        timer.start();
    }


    private void stopTimer() {
        timer.stop();
        textField.setText(formattedTime);
        active = false;
        button.setText("start");
    }
}

