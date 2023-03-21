//Lets Grow More : Task - 01
//Sudip Chakrabarty

import javax.swing.*;
import java.awt.*;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.Border;


public class CurrencyConverter extends JFrame implements ActionListener {

    private JLabel amount, from, to, result;
    private JTextField amountfield, convertedfield;
    private JComboBox<String> fcbox, tcbox;
    private JButton button;

    //1 usd = 84.24 rupee...
    private final String[] currency_name = {"INR", "BD", "USD", "EUR", "GBP",  "CAD", "AUD", "JPY", "CHF", "CNY"};
    private final double[] exchange_rates = {1, 1.28, 82.24, 87.89, 96.45, 62.49, 57.78, 0.63, 89.41, 11.98, 10.51, 0.08, 5.98, 7.47, 3.001, 8.07};

    Border blackLine = BorderFactory.createLineBorder(Color.green);
    public CurrencyConverter() {

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 15, 50));
        panel.setBorder(blackLine);
        panel.setBackground(new Color(95, 158, 160));

        amount = new JLabel("          Enter amount:");
        amount.setFont(new Font("Arrial",Font.BOLD,17));
        from = new JLabel("          From:");
        from.setFont(new Font("Arrial",Font.BOLD,17));
        to = new JLabel("          To:");
        to.setFont(new Font("Arrial",Font.BOLD,17));
        result = new JLabel("          Converted Currency:");
        result.setFont(new Font("Arrial",Font.BOLD,17));

        amountfield = new JTextField();
        amountfield.setFont(new Font("Arrial",Font.BOLD,17));
        convertedfield = new JTextField();
        convertedfield.setFont(new Font("Arrial",Font.BOLD,17));
        convertedfield.setEditable(false);

        fcbox = new JComboBox<>(currency_name);
        tcbox = new JComboBox<>(currency_name);

        button = new JButton("Convert Currency");
        button.setFont(new Font("Arrial",Font.BOLD,17));
        button.addActionListener(this);

        panel.add(amount);
        panel.add(amountfield);
        panel.add(from);
        panel.add(fcbox);
        panel.add(to);
        panel.add(tcbox);
        panel.add(result);
        panel.add(convertedfield);
        add(panel, BorderLayout.CENTER);
        add(button, BorderLayout.SOUTH);

    }

    public void actionPerformed(ActionEvent con) 
    {
        if (con.getSource() == button) 
        {
            try 
            {
                double amount = Double.parseDouble(amountfield.getText());
                int fromIndex = fcbox.getSelectedIndex();
                int toIndex = tcbox.getSelectedIndex();
                double result = amount * exchange_rates[fromIndex] / exchange_rates[toIndex];
                DecimalFormat deciform = new DecimalFormat("#.###");
                convertedfield.setText(deciform.format(result));
            }
            catch (NumberFormatException ex) 
            {
                JOptionPane.showMessageDialog(this, "Please Enter Valid Amount!");
            }
        }
    }


    public static void main(String[] args) {
        try 
        {
            CurrencyConverter cc = new CurrencyConverter();
            cc.setTitle("Currency Converter App");
            cc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            cc.setSize(550, 400);
            cc.setLocationRelativeTo(null);
            cc.setVisible(true);
        }
        catch(Exception ex) 
        {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
}