package org.kukuxer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import static javax.swing.text.StyleConstants.setIcon;


public class WatchButtons extends JPanel {
    long startTime;
    boolean active;
    long timeOnScreen;
    String formattedTime;
    JButton startStopButton;
    JButton noteButton;
    JButton randomColorButton;
    JTextField textField;
    Timer timer;
    NotedTime notedTime;
    Icon pauseButton;
    Icon startButton;
    WatchStopFrame watchStopFrame;
    Color randomColor;

    public String getFormattedTime() {
        return formattedTime;
    }

    public WatchButtons(JTextField textField, Color color, NotedTime notedTime, WatchStopFrame watchStopFrame) {
        randomColor = color;
        this.watchStopFrame = watchStopFrame;
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
        startStopButton.setPreferredSize(new Dimension(150, 15));


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
        this.notedTime = notedTime;


        randomColorButton = new JButton();
        randomColorButton.setBackground(color);

        ImageIcon originalIcon4 = new ImageIcon("radio-button.png");
        Image image4 = originalIcon4.getImage();
        Image smallerIcon4 = image4.getScaledInstance(145, 145, Image.SCALE_SMOOTH);
        ImageIcon resultImage4 = new ImageIcon(smallerIcon4);
        randomColorButton.setIcon(resultImage4);

        randomColorButton.setBorderPainted(false);
        randomColorButton.setContentAreaFilled(false);
        randomColorButton.setFocusPainted(false);
        randomColorButton.setPreferredSize(new Dimension(150, 50));
        randomColorButton.setText("RANDOM COLOR");
        randomColorButton.setForeground(randomColor.darker().darker().darker().darker());
        randomColorButton.setHorizontalAlignment(SwingConstants.CENTER);
        randomColorButton.setVerticalAlignment(SwingConstants.CENTER);
        randomColorButton.setHorizontalTextPosition(SwingConstants.CENTER);
        randomColorButton.setVerticalTextPosition(SwingConstants.CENTER);
        randomColorButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                changeInterfaceColor();
                System.out.println("Random color");
            }
        });

        noteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                AddedTime addedTime = new AddedTime(WatchButtons.this, randomColor);
                addedTime.setBackground(randomColor);
                notedTime.add(addedTime);
                addedTime.notedTime.setBackground(randomColor.brighter().brighter());
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
        add(randomColorButton, BorderLayout.CENTER);
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

    private void changeInterfaceColor() {
        Random random = new Random();
        int red = random.nextInt(256);
        int green = random.nextInt(256);
        int blue = random.nextInt(256);

        randomColor = new Color(red, green, blue);
        watchStopFrame.changeBackgroundColor(randomColor);

        for (Component component : notedTime.getComponents()) {
            if (component instanceof AddedTime) {
                ((AddedTime) component).changeBackgroundColor(randomColor);
            }
        }
    }

}

