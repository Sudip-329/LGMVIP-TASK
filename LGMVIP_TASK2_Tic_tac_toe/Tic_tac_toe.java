//Lets Grow More : Task - 02
//Sudip Chakrabarty

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Tic_tac_toe extends JPanel 
{
    char player = 'x';
    int tcels = 9;
    int trows = 3;
    int tcols = 3;
    JButton[] jBtn = new JButton[tcels];

    public Tic_tac_toe() {  // Constructor
        GridLayout GridLayout = new GridLayout(trows, tcols);
        setLayout(GridLayout);
        createButtons();
    }

    public void createButtons() {
        for (int i = 0; i <= 8; i++) {
            jBtn[i] = new JButton();
            jBtn[i].setText("");

            jBtn[i].addActionListener(al-> {
                JButton Cbtn = (JButton) al.getSource();
                Cbtn.setText(String.valueOf(player));
                Cbtn.setEnabled(false);

                if (player == 'x')
                {
                    player = 'o';
                }
                else
                {
                    player = 'x';
                }
                showWinner();
            });
            add(jBtn[i]);
        }
    }

    public void showWinner() 
    {
        if (WinnerCheck()) 
        {
            if (player == 'x')
            {
                player = 'o';
            }
            else
            {
                player = 'x';
            }

            JOptionPane jop = new JOptionPane();
            int gameEnd = JOptionPane.showConfirmDialog(jop, "Game Over. " + "Player "
            + player + " is the winner. ","Result",JOptionPane.DEFAULT_OPTION);

            if (gameEnd == JOptionPane.OK_OPTION)
                System.exit(0);

        } 
        else if (CheckDraw()) 
        {
            JOptionPane jop = new JOptionPane();
            int gameEnd = JOptionPane.showConfirmDialog(jop, "Game Draw",
            "Result",JOptionPane.DEFAULT_OPTION);

            if (gameEnd == JOptionPane.OK_OPTION)
                System.exit(0);
        }
    }

    public boolean CheckDraw() 
    {
        boolean gridsFull = true;
        for (int i = 0; i < tcels; i++) 
        {
            if (jBtn[i].getText().equals("")) 
            {
                gridsFull = false;
            }
        }
        return gridsFull;
    }

    public boolean WinnerCheck() 
    {
        return Rowscheck() || ColsCheck() || DiagonalCheck();
    }

    public boolean Rowscheck() 
    {
        int i = 0;
        for (int j = 0; j < 3; j++) {
            if (jBtn[i].getText().equals(jBtn[i + 1].getText())
            && jBtn[i].getText().equals(jBtn[i + 2].getText())
            && !jBtn[i].getText().equals("")) 
                {
                    return true;
                }
            i = i + 3;
        }
        return false;
    }

    public boolean ColsCheck() 
    {
        int i = 0;
        for (int j = 0; j < 3; j++) {

            if (jBtn[i].getText().equals(jBtn[i + 3].getText())
                    && jBtn[i].getText().equals(jBtn[i + 6].getText())
                    && !jBtn[i].getText().equals("")) {
                return true;
            }
            i++;
        }
        return false;
    }

    public boolean DiagonalCheck() 
    {
        if (jBtn[0].getText().equals(jBtn[4].getText()) && jBtn[0].getText().equals(
            jBtn[8].getText())&& !jBtn[0].getText().equals(""))
            {
                return true;
            }
        else
            {
                return jBtn[2].getText().equals(jBtn[4].getText())
                && jBtn[2].getText().equals(jBtn[6].getText())
                && !jBtn[2].getText().equals("");
            }
    }

    public static void main(String[] args) {
        JFrame obj = new JFrame("Tic Tac Toe Game");
        obj.getContentPane().add(new Tic_tac_toe());
        obj.setBounds(500, 500, 600, 550);
        obj.setVisible(true);
        obj.setLocationRelativeTo(null);
    }
}