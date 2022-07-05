package CalculatorSwing;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.geom.RoundRectangle2D;

public class CalculatorFrameLookAndFeel extends JFrame {
    private final String[] names = {"1", "2", "3", "D", "4", "5", "6",
            "C", "7", "8", "9", "+", "-", "0", "/", "*", ".", " ", " ", "="}; //array that "name" of Buttons
    private JTextField textField;
    private final CalculatorLogic calculatorLogic = new CalculatorLogic();

    public CalculatorFrameLookAndFeel() {
        super("Calculator Swing");
        initializedGUI();
    }

    /**
     * set the frame look
     */
    private void initializedGUI() {
        //set the title of app and the kind of Layout's jFrame
        setLayout(new BorderLayout());
        getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.BLUE));
        //set the text Panel Detention the

        textField = new JTextField(20);
        textField.setLayout(new FlowLayout());
        textField.setPreferredSize(new Dimension(40, 26));

        //set the text fieldPanel look&feel
        JPanel textFieldPanel = new JPanel();

        textFieldPanel.setBackground(Color.MAGENTA);
        textFieldPanel.add(textField);
        add(textFieldPanel, BorderLayout.NORTH);

        JPanel pressPanel = new JPanel();
        pressPanel.setLayout(new GridLayout(5, 4,5,5));
        for (int i = 0; i < names.length; i++) {
            JButton jButton = createButton(names[i]);
            pressPanel.add(jButton);
        }
        add(pressPanel, BorderLayout.CENTER);
    }

    /**
     *
     * @param name
     * @return
     */
    private JButton createButton(String name)
    {
        JButton jButton = new RoundButton(name);
        jButton.setFont(new Font("Arial", Font.ITALIC, 30));
        jButton.addActionListener(e -> actionHandler(e));
        //set the look and feel of operators
        if(jButton.getText().charAt(0) < '0' || jButton.getText().charAt(0) > '9')
            jButton.setForeground(Color.ORANGE);
            if(jButton.getText().charAt(0) == '=')
                jButton.setBackground(Color.GREEN);
        return jButton;
    }

    /**
     *
     * @param e
     */
    private void actionHandler(ActionEvent e) {
        String cmd = e.getActionCommand();
        if (cmd.equals(" ")) {
            return;
        }
        if (cmd.equals("C")) {
            textField.setText("");
        }
        else if (cmd.equals("D")) {
            String text = textField.getText();
            if (text.length() > 0) {
                textField.setText(text.substring(0, text.length() - 1));
            }
        }
        else if (cmd.equals(".")) {
            String text = textField.getText();
            if (text.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Cannot add dot to empty number",
                        "Hey!", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (text.indexOf('.') >= 0) {
                JOptionPane.showMessageDialog(null, "Cannot add have 2 dots in a number",
                        "Hey!", JOptionPane.ERROR_MESSAGE);
                return;
            }
            textField.setText(textField.getText() + cmd);
        }
        else if (cmd.equals("+") || cmd.equals("-") || cmd.equals("*") || cmd.equals("/")) {
            String text = textField.getText();
            if (text.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No number",
                        "Hey!", JOptionPane.ERROR_MESSAGE);
                return;
            }
            double firstNum = 0;
            try {
                firstNum = Double.parseDouble(text);
            }
            catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Bad number",
                        "Hey!", JOptionPane.ERROR_MESSAGE);
                return;
            }
            char operator = cmd.charAt(0);
            calculatorLogic.setOperator(operator);
            calculatorLogic.setFirstNum(firstNum);
            textField.setText("");
        }
        else if (!cmd.equals("=")) {
            textField.setText(textField.getText() + cmd);
        }
        else {
            String text = textField.getText();
            if (text.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No number",
                        "Hey!", JOptionPane.ERROR_MESSAGE);
                return;
            }
            double secondNum = 0;
            try {
                secondNum = Double.parseDouble(text);
            }
            catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Bad number",
                        "Hey!", JOptionPane.ERROR_MESSAGE);
                return;
            }
            double result = calculatorLogic.getResult(secondNum);
            textField.setText(String.valueOf(result));
        }
    }
}
