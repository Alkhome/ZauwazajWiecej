import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class High_Scores implements ActionListener {
    JFrame ramka = new JFrame();
    JLabel wynik_latwy = new JLabel();
    JLabel wynik_sredni = new JLabel();
    JLabel wynik_trudny = new JLabel();

    JButton powrot_do_menu = new JButton("Powrot do menu");

    public High_Scores()
    {
        ramka.setTitle("Twoje najwyzsze wyniki");
        ramka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ramka.setSize(650,700);
        ramka.setResizable(false);
        ramka.setLayout(null);
        ramka.setLocationRelativeTo(null);
        ramka.getContentPane().setBackground(new Color(107, 184, 202));
        ramka.setVisible(true);

        wynik_latwy.setBounds(30,30, 590, 70);
        wynik_latwy.setOpaque(true);
        wynik_latwy.setVisible(true);
        wynik_latwy.setFont(new Font("DejaVu Sans Mono", Font.PLAIN, 40));
        ramka.add(wynik_latwy);

        wynik_sredni.setBounds(30,130, 590, 70);
        wynik_sredni.setOpaque(true);
        wynik_sredni.setVisible(true);
        wynik_sredni.setFont(new Font("DejaVu Sans Mono", Font.PLAIN, 40));
        ramka.add(wynik_sredni);

        wynik_trudny.setBounds(30,230, 590, 70);
        wynik_trudny.setOpaque(true);
        wynik_trudny.setVisible(true);
        wynik_trudny.setFont(new Font("DejaVu Sans Mono", Font.PLAIN, 40));
        ramka.add(wynik_trudny);

        powrot_do_menu.setBounds(430,610,200,50);
        powrot_do_menu.setHorizontalAlignment(SwingConstants.CENTER);
        powrot_do_menu.addActionListener(this);
        powrot_do_menu.setFont(new Font("DejaVu Sans Mono", Font.PLAIN, 20));
        ramka.add(powrot_do_menu);

        wyswietl_wyniki();

    }
    public void wyswietl_wyniki()
    {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("scores.txt"));
            String line = reader.readLine();
            List<String> temp_array = new ArrayList<>();

            while (line != null)
            {
                temp_array.add(line);
                line = reader.readLine();

            }
            String[] tempsArray = temp_array.toArray(new String[0]);

            if (Integer.parseInt(tempsArray[1]) != 1)
            {
                wynik_latwy.setText("Latwy: "+tempsArray[1] + " sekund");
            }
            else
            {
                wynik_latwy.setText("Latwy: BRAK");
            }

            if (Integer.parseInt(tempsArray[2]) != 2147483647)
            {
                wynik_sredni.setText("Sredni: "+tempsArray[2] + " sekund");
            }
            else
            {
                wynik_sredni.setText("Sredni: BRAK");
            }

            if (Integer.parseInt(tempsArray[3]) != 2147483647)
            {
                wynik_trudny.setText("Trudny: "+tempsArray[3] + " sekund");
            }
            else
            {
                wynik_trudny.setText("Trudny: BRAK");
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == powrot_do_menu)
        {
            ramka.dispose();
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {new Menu();}

            });
        }
    }
}
