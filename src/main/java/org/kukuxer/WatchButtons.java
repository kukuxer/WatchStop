package org.kukuxer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static javax.swing.text.StyleConstants.setIcon;


public class WatchButtons extends JPanel {
    long startTime;
    boolean active;
    long timeOnScreen;
    String formattedTime;
    JButton startStopButton;
    JButton noteButton;
    JButton randomColor;
    JTextField textField;
    Timer timer;
    NotedTime notedTime;
    Icon pauseButton;
    Icon startButton;


    public String getFormattedTime() {
        return formattedTime;
    }

    public WatchButtons(JTextField textField, Color color, NotedTime notedTime) {

        this.textField = textField;
        startStopButton = new JButton();
        startStopButton.setBackground(color);

        ImageIcon originalIcon = new ImageIcon("play-button.png");
        Image image = originalIcon.getImage();
        Image smallerIcon = image.getScaledInstance(145, 145, Image.SCALE_SMOOTH);
        ImageIcon resultImage = new ImageIcon(smallerIcon);
        startStopButton.setIcon(resultImage);
        startButton = resultImage;

        startStopButton.setBorderPainted(false);
        startStopButton.setContentAreaFilled(false);
        startStopButton.setFocusPainted(false);
        startStopButton.setPreferredSize(new Dimension(150, 50));
        //startStopButton.setBackground(Color.CYAN);

        noteButton = new JButton();
        noteButton.setBackground(color);

        ImageIcon originalIcon2 = new ImageIcon("back.png");
        Image image2 = originalIcon2.getImage();
        Image smallerIcon2 = image2.getScaledInstance(145, 145, Image.SCALE_SMOOTH);
        ImageIcon resultImage2 = new ImageIcon(smallerIcon2);
        noteButton.setIcon(resultImage2);


        ImageIcon originalIcon3 = new ImageIcon("video-pause-button.png");
        Image image3 = originalIcon3.getImage();
        Image smallerIcon3 = image3.getScaledInstance(145, 145, Image.SCALE_SMOOTH);
        pauseButton = new ImageIcon(smallerIcon3);


        noteButton.setBorderPainted(false);
        noteButton.setContentAreaFilled(false);
        noteButton.setFocusPainted(false);
        noteButton.setPreferredSize(new Dimension(200, 30));
        // noteButton.setBackground(Color.RED);
        this.notedTime = notedTime;

        noteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                AddedTime addedTime = new AddedTime(WatchButtons.this);
                addedTime.setBackground(color.brighter());
                notedTime.add(addedTime);
                addedTime.notedTime.setBackground(color.brighter().brighter());
                notedTime.updateIndex();
                revalidate();
            }
        });


        setOpaque(false);
        setPreferredSize(new Dimension(100, 100));
        startStopButton.addActionListener(new ActionListener() {
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
        add(noteButton, BorderLayout.WEST);
        add(startStopButton, BorderLayout.EAST);
    }

    private void startTimer() {
        startTime = System.currentTimeMillis();
        //startStopButton.setText("Stop");
        notedTime.removeAddedTime();
        startStopButton.setIcon(pauseButton);
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
        startStopButton.setIcon(startButton);
        timer.stop();
        textField.setText(formattedTime);
        active = false;
        //startStopButton.setText("start");
    }
}

