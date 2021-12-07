import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Which_Figure implements ActionListener {
    JFrame ramka = new JFrame();
    ImageIcon miniaturka = new ImageIcon("big_brain.jpg");
    JPanel game_panel = new JPanel();
    JButton powrot_do_menu = new JButton("Powrot do menu");
    JButton potwierdz = new JButton("Potwierdz odpowiedz");
    JPanel pole_odpowiedzi = new JPanel(new GridLayout(2, 4));
    Figures rys = new Figures();

    JTextField odp_kwadraty = new JTextField();
    JTextField odp_prostokaty = new JTextField();
    JTextField odp_okregi = new JTextField();
    JTextField odp_linie = new JTextField();

    int difficulty = 1; //potem zmienic na 0
    int ile_poprawnie = 0;
    Which_Figure() {
        super();
        ramka.setTitle("Jaka figura");
        ramka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ramka.setSize(650, 700);
        ramka.setResizable(false);
        ramka.setVisible(true);
        ramka.setIconImage(miniaturka.getImage());
        ramka.setLocationRelativeTo(null);
        ramka.setLayout(null);
        ramka.getContentPane().setBackground(new Color(107, 184, 202));

        potwierdz.addActionListener(this);
        potwierdz.setBounds(100, 300, 450, 80);
        potwierdz.setFont(new Font("DejaVu Sans Mono", Font.PLAIN, 40));
        potwierdz.setVisible(false);
        ramka.add(potwierdz);

        powrot_do_menu.setBounds(430, 610, 200, 50);
        powrot_do_menu.setHorizontalAlignment(SwingConstants.CENTER);
        powrot_do_menu.addActionListener(this);
        powrot_do_menu.setFont(new Font("DejaVu Sans Mono", Font.PLAIN, 20));
        ramka.add(powrot_do_menu);

        pole_odpowiedzi.setBounds(30, 30, 590, 200);
        pole_odpowiedzi.setBorder(BorderFactory.createLineBorder(Color.black));
        ramka.add(pole_odpowiedzi);

        JLabel kwadraty_text = new JLabel("Ile bylo kwadratow?");
        kwadraty_text.setBorder(BorderFactory.createLineBorder(Color.black));
        kwadraty_text.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel prostokaty_text = new JLabel("Ile bylo prostokatow?");
        prostokaty_text.setBorder(BorderFactory.createLineBorder(Color.black));
        prostokaty_text.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel okregi_text = new JLabel("Ile bylo okregow?");
        okregi_text.setBorder(BorderFactory.createLineBorder(Color.black));
        okregi_text.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel linie_text = new JLabel("Ile bylo linii?");
        linie_text.setBorder(BorderFactory.createLineBorder(Color.black));
        linie_text.setHorizontalAlignment(SwingConstants.CENTER);

        odp_kwadraty.setHorizontalAlignment(SwingConstants.CENTER);
        odp_kwadraty.setBorder(BorderFactory.createLineBorder(Color.black));

        odp_prostokaty.setBorder(BorderFactory.createLineBorder(Color.black));
        odp_prostokaty.setHorizontalAlignment(SwingConstants.CENTER);

        odp_okregi.setHorizontalAlignment(SwingConstants.CENTER);
        odp_okregi.setBorder(BorderFactory.createLineBorder(Color.black));

        odp_linie.setHorizontalAlignment(SwingConstants.CENTER);
        odp_linie.setBorder(BorderFactory.createLineBorder(Color.black));


        pole_odpowiedzi.add(kwadraty_text);
        pole_odpowiedzi.add(prostokaty_text);
        pole_odpowiedzi.add(okregi_text);
        pole_odpowiedzi.add(linie_text);

        pole_odpowiedzi.add(odp_kwadraty);
        pole_odpowiedzi.add(odp_prostokaty);
        pole_odpowiedzi.add(odp_okregi);
        pole_odpowiedzi.add(odp_linie);

        pole_odpowiedzi.setVisible(false);

        game_panel.setBackground(Color.LIGHT_GRAY);
        game_panel.setLayout(new BorderLayout());
        ramka.add(game_panel);
        game_panel.setBounds(50, 30, 550, 450);
        if (difficulty == 1) //easy
        {
            rys.ilosc_wywolan = 10;
        }
        else if (difficulty == 2) //medium
        {
            rys.ilosc_wywolan = 15;
        }
        else if (difficulty == 3) //hard
        {
            rys.ilosc_wywolan = 20;
        }
        else
        {
            JOptionPane.showMessageDialog(null, """
                            Cos poszlo nie tak...
                            Prosze sprobowac uruchomic gre od nowa
                            Przepraszamy za niedogodnosci.""",
                    "Uwaga!", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
        
        gra();
    }
    public void gra()
    {
            game_panel.add(rys, BorderLayout.CENTER);
            rys.setLocation(10, 10);
            rys.repaint();

            Timer timer = new Timer(3000, new ActionListener() { //wincyj czasu!
                @Override
                public void actionPerformed(ActionEvent e) {
                    int ile_kwadratow = rys.ilosc_kwadratow;
                    int ile_prostokatow = rys.ilosc_prostokatow;
                    int ile_okregow = rys.ilosc_okregow;
                    int ile_linii = rys.ilosc_linii;
                    game_panel.setVisible(false);
                    pole_odpowiedzi.setVisible(true);
                    potwierdz.setVisible(true);

                    System.out.println("" + ile_kwadratow + ile_prostokatow + ile_okregow + ile_linii);
                }
            });
            timer.setRepeats(false);
            timer.start();
    }

    public void czy_lepszy_wynik(long obecny_wynik)
    {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("scores.txt"));
            String line = reader.readLine();
            java.util.List<String> temp_array = new ArrayList<>();

            while (line != null)
            {
                temp_array.add(line);
                line = reader.readLine();
            }
            String[] tempsArray = temp_array.toArray(new String[0]);
            if(difficulty == 1)
            {
                if (Integer.parseInt(tempsArray[1]) > obecny_wynik)
                {
                    try {
                        FileWriter myWriter = new FileWriter("scores.txt", false);
                        tempsArray[1] = String.valueOf(obecny_wynik);
                        for (int i = 0; i < tempsArray.length; i++) {
                            myWriter.write(tempsArray[i] + "\n");
                            System.out.println(tempsArray[i]);
                        }
                        myWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            else if (difficulty == 2)
            {
                if (Integer.parseInt(tempsArray[2]) > obecny_wynik)
                {
                    try {
                        FileWriter myWriter = new FileWriter("scores.txt", false);
                        tempsArray[2] = String.valueOf(obecny_wynik);
                        for (int i = 0; i < tempsArray.length; i++) {
                            myWriter.write(tempsArray[i] + "\n");
                            System.out.println(tempsArray[i]);
                        }
                        myWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            else if (difficulty == 3)
            {
                if (Integer.parseInt(tempsArray[3]) > obecny_wynik)
                {
                    try {
                        FileWriter myWriter = new FileWriter("scores.txt", false);
                        tempsArray[3] = String.valueOf(obecny_wynik);
                        for (int i = 0; i < tempsArray.length; i++) {
                            myWriter.write(tempsArray[i] + "\n");
                            System.out.println(tempsArray[i]);
                        }
                        myWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            else
            {
                System.out.println("Nieprzewidziany blad");
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
        else if (e.getSource() == potwierdz)
        {
            String wynik_odpowiedzi = "";
            int czy_dobrze = 0;
            try
            {
                if(rys.ilosc_kwadratow == Integer.parseInt(odp_kwadraty.getText()))
                {
                    wynik_odpowiedzi += "Dobrze! Ilosc kwadratow sie zgadza\n";
                    czy_dobrze++;
                }
                else {
                    wynik_odpowiedzi += "Niestety, poprawna ilosc kwadratow to: " + rys.ilosc_kwadratow +"\n";
                }
                if(rys.ilosc_prostokatow == Integer.parseInt(odp_prostokaty.getText()))
                {
                    wynik_odpowiedzi += "Dobrze! Ilosc prostokatow sie zgadza.\n";
                    czy_dobrze++;
                }
                else {
                    wynik_odpowiedzi += "Niestety, poprawna ilosc prostokatow to: " + rys.ilosc_prostokatow + "\n";
                }
                if(rys.ilosc_okregow == Integer.parseInt(odp_okregi.getText()))
                {
                    wynik_odpowiedzi += "Dobrze! Ilosc okregow sie zgadza.\n";
                    czy_dobrze++;
                }
                else {
                    wynik_odpowiedzi += "Niestety, poprawna ilosc okregow to: " + rys.ilosc_okregow + "\n";
                }
                if(rys.ilosc_linii == Integer.parseInt(odp_linie.getText()))
                {
                    wynik_odpowiedzi += "Dobrze! Ilosc linii sie zgadza.\n";
                    czy_dobrze++;
                }
                else {
                    wynik_odpowiedzi += "Niestety, poprawna ilosc linii to: " + rys.ilosc_linii + "\n";
                }

                if (czy_dobrze == 4)
                    ile_poprawnie++;

                if (ile_poprawnie == 1)
                {
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyy HH:mm:ss");

                    try {
                        BufferedReader reader = new BufferedReader(new FileReader("scores.txt"));
                        String line = reader.readLine();
                        java.util.List<String> temp_array = new ArrayList<>();

                        while (line != null)
                        {
                            temp_array.add(line);
                            line = reader.readLine();
                        }
                        String[] tempsArray = temp_array.toArray(new String[0]);

                        LocalDateTime czas_koncowy = LocalDateTime.now();

                        LocalDateTime czas_poczatkowy = LocalDateTime.parse(tempsArray[0], dtf);
                        System.out.println(czas_poczatkowy);
                        System.out.println(czas_koncowy);
                        long roznica_czasu = ChronoUnit.SECONDS.between(czas_poczatkowy, czas_koncowy);
                        System.out.println(roznica_czasu);
                        JOptionPane.showMessageDialog(null, "Gratulacje!" +
                                        "\nUdalo Ci sie przejsc gre!" +
                                        "\nTwoj wynik to: " + roznica_czasu + " sekund",
                                "Koniec!", JOptionPane.INFORMATION_MESSAGE);
                        czy_lepszy_wynik(roznica_czasu);
                    }
                    catch (IOException ef) {
                        ef.printStackTrace();
                    }

                    System.exit(0); //tymczasowe
                }
                else {
                    JOptionPane.showMessageDialog(null, wynik_odpowiedzi,
                            "Wynik", JOptionPane.INFORMATION_MESSAGE);
                    System.out.println(wynik_odpowiedzi);
                    pole_odpowiedzi.setVisible(false);
                    potwierdz.setVisible(false);
                    odp_okregi.setText("");
                    odp_prostokaty.setText("");
                    odp_linie.setText("");
                    odp_kwadraty.setText("");
                    gra();
                }

            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, "Upewnij się, że wpisałeś tylko liczby,\nbez przecinków",
                        "Uwaga!", JOptionPane.INFORMATION_MESSAGE);
            }
        }

    }
}
