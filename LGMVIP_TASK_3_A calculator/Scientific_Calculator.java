//Lets Grow More : Task-03
//Sudip Chakrabarty


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class S_CalC extends JFrame implements ActionListener {
    
    private JTextField display;
    private JButton btn[];

    public S_CalC() {
        super("Scientific Calculator");

        display = new JTextField();
        Font bf = display.getFont().deriveFont(Font.PLAIN, 40f);
        display.setFont(bf);
        display.setEditable(false);
        getContentPane().add(display, BorderLayout.NORTH);

        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(5, 5));
        
        String button_name[] = { "sin", "cos", "tan", "exp", "Clear",
                "7", "8", "9", "/", "sqrt",
                "4", "5", "6", "*", "1/x",
                "1", "2", "3", "-", "exp",
                "0", "+/-", ".", "+", "=" };
        btn = new JButton[button_name.length];

        for (int i = 0; i < button_name.length; i++) {
            btn[i] = new JButton(button_name[i]);
            btn[i].addActionListener(this);
            buttons.add(btn[i]);
        }
        getContentPane().add(buttons, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent event) 
    {
        String user_input = event.getActionCommand();
        if (user_input.equals("Clear")) 
        {
            display.setText("");
        } 
        else if (user_input.equals("=")) 
        {
            String expression = display.getText();
            try {
                double ans = evaluateExpression(expression);
                display.setText(Double.toString(ans));
            } 
            catch (IllegalArgumentException ex) 
            {
                display.setText(ex.getMessage());
            }
        } 
        else if (user_input.equals("sqrt")) 
        {
            double input = Double.parseDouble(display.getText());
            double ans = Math.sqrt(input);
            display.setText(Double.toString(ans));
        } 
        else if (user_input.equals("1/x")) 
        {
            double input = Double.parseDouble(display.getText());
            double ans = 1.0 / input;
            display.setText(Double.toString(ans));
        } 
        else if (user_input.equals("+/-")) 
        {
            double input = Double.parseDouble(display.getText());
            double ans = -input;
            display.setText(Double.toString(ans));
        } 
        else if (user_input.equals("sin")) 
        {
            double input = Double.parseDouble(display.getText());
            double ans = Math.sin(input);
            display.setText(Double.toString(ans));
        } 
        else if (user_input.equals("cos")) 
        {
            double input = Double.parseDouble(display.getText());
            double ans = Math.cos(input);
            display.setText(Double.toString(ans));
        } 
        else if (user_input.equals("tan")) 
        {
            double input = Double.parseDouble(display.getText());
            double ans = Math.tan(input);
            display.setText(Double.toString(ans));
        } 
        else if (user_input.equals("log")) 
        {
            double input = Double.parseDouble(display.getText());
            double ans = Math.log10(input);
            display.setText(Double.toString(ans));
        } 
        else if (user_input.equals("exp")) 
        {
            double input = Double.parseDouble(display.getText());
            double ans = Math.exp(input);
            display.setText(Double.toString(ans));
        } 
        else 
        {
            display.setText(display.getText() + user_input);
        }
    }

    private double evaluateExpression(String expression) 
    {
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        return evaluator.evaluate(expression);
    }

    private class ExpressionEvaluator 
    {
        private String expression;
        private int index;
        public double evaluate(String expression) 
        {
            this.expression = expression;
            index = 0;
            double ans = parseExpression();
            if (index != expression.length()) 
            {
                throw new IllegalArgumentException("Invalid expression");
            }
            return ans;
        }

        private double parseExpression() 
        {
            double ans = parseTerm();
            while (index < expression.length()) 
            {
                char operator = expression.charAt(index);
                if (operator != '+' && operator != '-') 
                {
                    break;
                }
                index++;
                double operand = parseTerm();
                if (operator == '+') 
                {
                    ans += operand;
                } else 
                {
                    ans -= operand;
                }
            }
            return ans;
        }

        private double parseTerm() 
        {
            double ans = parseFactor();
            while (index < expression.length()) 
            {
                char operator = expression.charAt(index);
                if (operator != '*' && operator != '/') 
                {
                    break;
                }
                index++;
                double operand = parseFactor();
                if (operator == '*') {
                    ans *= operand;
                } 
                else 
                {
                    ans /= operand;
                }
            }
            return ans;
        }

        private double parseFactor() 
        {
            char ch = expression.charAt(index);
            if (ch >= '0' && ch <= '9') 
            {
                return parseNumber();
            } 
            else if (ch == '(') 
            {
                index++;
                double ans = parseExpression();
                if (expression.charAt(index) != ')') 
                {
                    throw new IllegalArgumentException("Mismatched parentheses");
                }
                index++;
                return ans;
            } 
            else if (ch == '-') 
            {
                index++;
                return -parseFactor();
            } 
            else if (ch == '+') 
            {
                index++;
                return parseFactor();
            } 
            else 
            {
                throw new IllegalArgumentException("Invalid character: " + ch);
            }
        }

        private double parseNumber() 
        {
            int startIndex = index;
            while (index < expression.length() && Character.isDigit(expression.charAt(index))) 
            {
                index++;
            }
            if (index < expression.length() && expression.charAt(index) == '.') 
            {
                index++;
                while (index < expression.length() && Character.isDigit(expression.charAt(index))) 
                {
                    index++;
                }
            }
            return Double.parseDouble(expression.substring(startIndex, index));
        }
    }
}

public class Scientific_Calculator {
    public static void main(String[] args) 
    {
        S_CalC s_c = new S_CalC();
        s_c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        s_c.setSize(500, 400);
        s_c.setVisible(true);
    }
}