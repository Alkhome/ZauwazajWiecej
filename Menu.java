import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Menu implements ActionListener {
    static JFrame ramka = new JFrame();
    ImageIcon miniaturka = new ImageIcon("big_brain.jpg");
    JLabel background = new JLabel(new ImageIcon("menu_bg.jpg"));
    JButton odpalanie_instrukcji  = new JButton("Instrukcja do gry");
    JButton poziom_latwy = new JButton("Latwy");
    JButton poziom_sredni = new JButton("Sredni");
    JButton poziom_trudny = new JButton("Trudny");
    JButton najwyzsze_wyniki = new JButton("Najwyzsze wyniki");
    JButton wyjdz_z_gry = new JButton("Zakoncz Gre");
    public Menu() {

        ramka.setTitle("Zauwazaj Wiecej!");
        ramka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ramka.setSize(650, 700);
        ramka.setResizable(false);
        ramka.setVisible(true);
        ramka.setIconImage(miniaturka.getImage());
        ramka.setLocationRelativeTo(null);
        ramka.setLayout(null);

        poziom_latwy.setBounds(105,30,350,70);
        poziom_latwy.setFont(new Font("DejaVu Sans Mono",Font.BOLD,37));
        poziom_latwy.addActionListener(this);
        ramka.add(poziom_latwy);

        poziom_sredni.setBounds(55,140,350,70);
        poziom_sredni.setFont(new Font("DejaVu Sans Mono",Font.BOLD,40));
        poziom_sredni.addActionListener(this);
        ramka.add(poziom_sredni);

        poziom_trudny.setBounds(5,250,350,70);
        poziom_trudny.setFont(new Font("DejaVu Sans Mono",Font.BOLD,40));
        poziom_trudny.addActionListener(this);
        ramka.add(poziom_trudny);

        odpalanie_instrukcji.addActionListener(this);
        odpalanie_instrukcji.setFont(new Font("DejaVu Sans Mono",Font.BOLD,40));
        odpalanie_instrukcji.setBounds(5, 470,370,70);
        ramka.add(odpalanie_instrukcji);

        najwyzsze_wyniki.addActionListener(this);
        najwyzsze_wyniki.setFont(new Font("DejaVu Sans Mono",Font.BOLD,32));
        najwyzsze_wyniki.setBounds(5, 360,330,70);
        ramka.add(najwyzsze_wyniki);

        wyjdz_z_gry.setBounds(55,580,350,70);
        wyjdz_z_gry.setFont(new Font("DejaVu Sans Mono",Font.BOLD,40));
        wyjdz_z_gry.addActionListener(this);
        ramka.add(wyjdz_z_gry);

        background.setBounds(0,0,650,700);
        background.setVisible(true);
        ramka.add(background);

        try {
            File wyniki = new File("scores.txt");
            if (wyniki.createNewFile()) {
                System.out.println("Stworzono plik: " + wyniki.getName());
                FileWriter myWriter = new FileWriter("scores.txt");
                myWriter.write("2147483647\n2147483647\n2147483647\n2147483647");
                myWriter.close();
            }
        } catch (IOException e) {
            System.out.println("Wystapil blad z plikiem.");
            e.printStackTrace();
        }

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
        else if (e.getSource() == najwyzsze_wyniki)
        {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run () {
                    new High_Scores();
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
