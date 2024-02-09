package org.kukuxer;

import javax.swing.*;
import java.awt.*;

public class AddedTime extends JPanel {
    private JLabel index;
    public JTextField notedTime;
    WatchButtons watchButtons;

    public AddedTime(WatchButtons watchButtons,Color color){
        setPreferredSize(new Dimension(40, 20));
        setLayout(new BorderLayout());
        index = new JLabel("");
        index.setPreferredSize(new Dimension(20, 20));
        index.setHorizontalAlignment(JLabel.CENTER);
        add(index, BorderLayout.WEST);
        notedTime = new JTextField(watchButtons.getFormattedTime());
        notedTime.setBorder(BorderFactory.createEmptyBorder());
        notedTime.setBackground(color);

        add(notedTime, BorderLayout.CENTER);
    }
    public void changeIndex(int number) {
        index.setText(number + ".");
        validate();
    }
    public void changeBackgroundColor(Color newColor) {
        setBackground(newColor.darker().darker());
        notedTime.setBackground(newColor);
        index.setBackground(newColor.brighter().brighter());
        // If you have other components that need their color changed, update them here.
        // For example: notedTime.setBackground(newColor.darker());
        repaint();
    }


}
