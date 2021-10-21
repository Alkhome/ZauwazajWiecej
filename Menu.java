import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame implements ActionListener {
    static JFrame ramka = new JFrame();
    ImageIcon miniaturka = new ImageIcon("big_brain.jpg");
    JButton odpalanie_instrukcji  = new JButton("Instrukcja do gry");
    JButton poziom_latwy = new JButton("Latwy");
    JButton poziom_sredni = new JButton("Sredni");
    JButton poziom_trudny = new JButton("Trudny");
    JButton wyjdz_z_gry = new JButton("Zakoncz Gre");
    public Menu() {
        super("Menu");
        ramka.setTitle("Zauwazaj Wiecej!");
        ramka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ramka.setSize(700, 700);
        ramka.setResizable(false);
        ramka.setVisible(true);
        ramka.setIconImage(miniaturka.getImage());
        ramka.setLocationRelativeTo(null);
        ramka.setLayout(null);

        poziom_latwy.setBounds(100,30,300,70);
        poziom_latwy.setFont(new Font("Helvetica Neue",Font.BOLD,37));
        poziom_latwy.addActionListener(this);
        ramka.getContentPane().add(poziom_latwy);

        poziom_sredni.setBounds(100,110,300,70);
        poziom_sredni.setFont(new Font("Helvetica Neue",Font.BOLD,40));
        poziom_sredni.addActionListener(this);
        ramka.getContentPane().add(poziom_sredni);

        poziom_trudny.setBounds(100,200,300,70);
        poziom_trudny.setFont(new Font("Helvetica Neue",Font.BOLD,40));
        poziom_trudny.addActionListener(this);
        ramka.getContentPane().add(poziom_trudny);

        odpalanie_instrukcji.addActionListener(this);
        odpalanie_instrukcji.setFont(new Font("Helvetica Neue",Font.BOLD,40));
        odpalanie_instrukcji.setBounds(100, 290,300,70);
        ramka.add(odpalanie_instrukcji);

        wyjdz_z_gry.setBounds(100,400,300,70);
        wyjdz_z_gry.setFont(new Font("Helvetica Neue",Font.BOLD,40));
        wyjdz_z_gry.addActionListener(this);
        ramka.getContentPane().add(wyjdz_z_gry);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == poziom_latwy)
        {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run () {
                    new Shulte_Table_Easy();
                }
            });
        }

        else if (e.getSource() == poziom_sredni)
        {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run () {
                    new Shulte_Table_Medium();
                }
            });
        }
        else if (e.getSource() == poziom_trudny)
        {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run () {
                    new Shulte_Table_Hard();
                }
            });
        }
        else if (e.getSource() == odpalanie_instrukcji)
        {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run () {
                    new Instruction();
                }
            });
        }
        else if (e.getSource() == wyjdz_z_gry)
        {
            System.exit(0);
        }
        ramka.setVisible(false);
    }
}
