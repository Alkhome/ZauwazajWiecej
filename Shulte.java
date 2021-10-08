import java.util.Random;
import java.awt.GridLayout;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;



public class Shulte extends JFrame implements MouseListener {
    public Shulte()
    {
        super( "Tablica Shultego" );
        this. setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(100,100);
        int poziom_trudnosci = 2;

        if (poziom_trudnosci == 0)
        {
            this.setSize(300, 300);
            this.setLayout(new GridLayout(3, 3));
            for (int i = 0; i < 9; i++)
                add(new JButton("" + (i + 1)));
        }

        else if (poziom_trudnosci == 1)
        {
            setSize(500, 500);
            setLayout(new GridLayout(5, 5));
            for (int i = 0; i < 25; i++)
                add(new JButton("" + (i + 1)));
        }

        else if (poziom_trudnosci == 2)
        {
            setSize(700, 700);
            setLayout(new GridLayout(7, 7));
            for (int i = 0; i < 49; i++)
                add(new JButton("" + (i + 1)));
        }

        else {

        }


        setVisible(true);
    }
    @Override
    public void mouseClicked(MouseEvent e)
    {

    }


}
