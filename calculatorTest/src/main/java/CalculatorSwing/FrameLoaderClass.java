package CalculatorSwing;

import javax.swing.*;
import java.awt.*;
//the loader class that make the Frame appear on the screen
public class FrameLoaderClass {
    public static void main (String [] args) {
        CalculatorFrameLookAndFeel calculatorFrameLookAndFeel = new CalculatorFrameLookAndFeel();
        calculatorFrameLookAndFeel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        calculatorFrameLookAndFeel.setSize(new Dimension(300,300));
        calculatorFrameLookAndFeel.setLocationRelativeTo(null);
        calculatorFrameLookAndFeel.setVisible(true);
        calculatorFrameLookAndFeel.pack();
    }
}
