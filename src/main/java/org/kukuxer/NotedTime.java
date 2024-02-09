package org.kukuxer;

import javax.swing.*;
import java.awt.*;

public class NotedTime extends JPanel {
    public NotedTime() {
        GridLayout layout = new GridLayout(10, 1);
        layout.setVgap(5);
        setLayout(layout);
        setPreferredSize(new Dimension(150, 500));
        // setBackground(Color.RED);
    }

    public void updateIndex() {
        Component[] ListItems = this.getComponents();
        for (int i = 0; i < ListItems.length; i++) {
            if (ListItems[i] instanceof AddedTime) {
                ((AddedTime) ListItems[i]).changeIndex(i + 1);
            }
        }
    }

    public void removeAddedTime() {

        for (Component c : getComponents()) {
            if (c instanceof AddedTime) {
                remove(c);
                repaint();
                updateIndex();
            }
        }
    }


}
