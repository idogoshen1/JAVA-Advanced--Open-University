package CalculatorSwing;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class RoundButton extends JButton {
        public RoundButton(String text) {
            super(text);
            setBackground(Color.WHITE);
            setFocusable(false);

    /*
     These statements enlarge the button so that it
     becomes a circle rather than an oval.
    */
            Dimension size = getPreferredSize();
            size.width = size.height = Math.max(size.width, size.height);
            setPreferredSize(size);

    /*
     This call causes the JButton not to paint the background.
     This allows us to paint a round background.
    */
            setContentAreaFilled(false);
        }

        protected void paintComponent(Graphics g) {
            if (getModel().isArmed()) {
                g.setColor(Color.white);
            } else {
                g.setColor(getBackground());
            }
            g.fillOval(0, 0, getSize().width - 1, getSize().height - 1);

            super.paintComponent(g);
        }

        protected void paintBorder(Graphics g) {
            g.setColor(Color.darkGray);
            g.drawOval(0, 0, getSize().width - 1, getSize().height - 1);
        }
    }

