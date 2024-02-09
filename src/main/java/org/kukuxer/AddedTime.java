package org.kukuxer;

import javax.swing.*;
import java.awt.*;

public class AddedTime extends JPanel {
    private JLabel index;
    public JTextField notedTime;
    WatchButtons watchButtons;

    public AddedTime(WatchButtons watchButtons){
        setPreferredSize(new Dimension(40, 20));
        setLayout(new BorderLayout());
        index = new JLabel("");
        index.setPreferredSize(new Dimension(20, 20));
        index.setHorizontalAlignment(JLabel.CENTER);
        add(index, BorderLayout.WEST);
        notedTime = new JTextField(watchButtons.getFormattedTime());
        notedTime.setBorder(BorderFactory.createEmptyBorder());
      //  notedTime.setBackground();

        add(notedTime, BorderLayout.CENTER);
    }
    public void changeIndex(int number) {
        index.setText(number + "");
        validate();
    }



}
