package org.kukuxer;

import org.kukuxer.WatchButtons;

import javax.swing.*;
import java.awt.*;

public class WatchStopFrame extends JFrame {
    private Color color;
    private JTextField timeshow;
    //private JButton button;
    private NotedTime notedTime;
    GradientPanel gradientPanel;

    public WatchStopFrame(Color color) {
        setTitle("Sheniamer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(240, 240, 240));  // Light gray background

        // Gradient background for the text field
        GradientPanel gradientPanel = new GradientPanel(color, color.darker(), 0, 0.5);
        gradientPanel.setLayout(new BorderLayout());
        timeshow = new JTextField("0.00");
        timeshow.setEditable(true);
        timeshow.setOpaque(false);
        timeshow.setForeground(color.brighter().brighter().brighter());
        timeshow.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));  // Add padding
        timeshow.setFont(new Font("Arial", Font.PLAIN, 111));
        timeshow.setHorizontalAlignment(SwingConstants.CENTER);
        gradientPanel.add(timeshow, BorderLayout.NORTH);
        timeshow.setPreferredSize(new Dimension(500, 250));
        notedTime = new NotedTime();
        notedTime.setBackground(color.darker());
        add(notedTime,BorderLayout.WEST);
        WatchButtons watchButton = new WatchButtons(timeshow, color,notedTime,WatchStopFrame.this);
        gradientPanel.add(watchButton, BorderLayout.CENTER);
        add(gradientPanel, BorderLayout.CENTER);


        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public void changeBackgroundColor(Color newColor) {
        getContentPane().setBackground(newColor);
        timeshow.setForeground(newColor.brighter().brighter().brighter());
        notedTime.setBackground(newColor.darker());
       notedTime.repaint();
        // If you have other components that need their color changed, update them here.
        // For example: notedTime.setBackground(newColor.darker());
        gradientPanel.setStartColor(newColor);
        gradientPanel.setEndColor(newColor.darker());
        gradientPanel.repaint();
        repaint();
    }

    // Custom JPanel for gradient background
    private  class GradientPanel extends JPanel {
        private Color startColor;
        private  Color endColor;
        private final double startFraction;
        private final double endFraction;

        public GradientPanel(Color startColor, Color endColor, double startFraction, double endFraction) {
            this.startColor = startColor;
            this.endColor = endColor;
            this.startFraction = startFraction;
            this.endFraction = endFraction;
            gradientPanel = GradientPanel.this;
        }
        public  void setStartColor(Color color){
            startColor = color;
        }
        public  void setEndColor(Color color){
            endColor = color;
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
