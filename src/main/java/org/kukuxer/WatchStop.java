package org.kukuxer;

import org.kukuxer.WatchStopButton;

import javax.swing.*;
import java.awt.*;

//package org.kukuxer;
//
//import javax.swing.*;
//import java.awt.*;
//
//public class WatchStop extends JFrame {
//    private Color color;
//    private JTextField textField;
//    JButton button;
//
//    public WatchStop(Color color) {
//        setTitle("Sheniamer");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLayout(new BorderLayout());
//        getContentPane().setBackground(color);
//
//        textField = new JTextField("0.00");
//        textField.setEditable(false);
//        textField.setBackground(color.darker());
//
//        // Set a different foreground color for the text field
//        textField.setForeground(color);
//
//        textField.setBorder(BorderFactory.createLineBorder(color));
//        textField.setFont(new Font("Arial", Font.PLAIN, 48));
//        textField.setPreferredSize(new Dimension(400, 100));
//        add(textField, BorderLayout.NORTH);
//
//        WatchStopButton button = new WatchStopButton(textField, color);
//        add(button, BorderLayout.CENTER);
//
//        pack();
//        setLocationRelativeTo(null);
//        setVisible(true);
//    }
//}
public class WatchStop extends JFrame {
    private Color color;
    private JTextField timeshow;

    private JButton button;

    public WatchStop(Color color) {
        setTitle("Sheniamer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(240, 240, 240));  // Light gray background

        // Gradient background for the text field
        GradientPanel gradientPanel = new GradientPanel(color, color.darker(), 0, 0.5);
        gradientPanel.setLayout(new BorderLayout());
        timeshow = new JTextField("0.00");
        timeshow.setEditable(false);
        timeshow.setOpaque(false);
        if(color.equals(Color.black)){
            timeshow.setForeground(Color.white);
        }else timeshow.setForeground(color.darker().darker().darker());
        timeshow.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));  // Add padding
        timeshow.setFont(new Font("Arial", Font.PLAIN, 111));
        timeshow.setHorizontalAlignment(SwingConstants.CENTER);
        gradientPanel.add(timeshow, BorderLayout.NORTH);
        timeshow.setPreferredSize(new Dimension(500, 250));
        WatchStopButton watchStopButton = new WatchStopButton(timeshow, color);
        gradientPanel.add(watchStopButton, BorderLayout.CENTER);
        add(gradientPanel, BorderLayout.CENTER);




        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Custom JPanel for gradient background
    private static class GradientPanel extends JPanel {
        private final Color startColor;
        private final Color endColor;
        private final double startFraction;
        private final double endFraction;

        public GradientPanel(Color startColor, Color endColor, double startFraction, double endFraction) {
            this.startColor = startColor;
            this.endColor = endColor;
            this.startFraction = startFraction;
            this.endFraction = endFraction;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();

            int w = getWidth();
            int h = getHeight();

            GradientPaint gradientPaint = new GradientPaint(
                    (int) (w * startFraction), 0, startColor,
                    (int) (w * endFraction), 0, endColor);

            g2d.setPaint(gradientPaint);
            g2d.fillRect(0, 0, w, h);

            g2d.dispose();
        }
    }
}