import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Zapamietywanie_Numerow_Medium extends Ramka_Do_Gry implements ActionListener {
    Random random = new Random();
    JButton potwierdz = new JButton("Potwierdź");
    JButton potwierdzenie_zapamietania = new JButton("Zapamiętano");
    JLabel wygenerowana_liczba_jl = new JLabel();
    JLabel rozpraszanie = new JLabel(new ImageIcon("random.jpeg"));
    JLabel[] postep;
    JTextField wprowadzenie_odpowiedzi = new JTextField();
    int lp_zgadywanego_numeru = 1;
    int zmienna;

    public Zapamietywanie_Numerow_Medium() {

        new Ramka_Do_Gry();
        powrot_do_menu.addActionListener(this);

        potwierdz.addActionListener(this);
        potwierdz.setBounds(100,300,450,80);
        potwierdz.setFont(new Font("DejaVu Sans Mono", Font.PLAIN, 40));
        potwierdz.setVisible(false);
        ramka.add(potwierdz);

        wprowadzenie_odpowiedzi.setBounds(20,100,610,120);
        wprowadzenie_odpowiedzi.setHorizontalAlignment(SwingConstants.CENTER);
        wprowadzenie_odpowiedzi.setVisible(false);
        ramka.add(wprowadzenie_odpowiedzi);

        wygenerowana_liczba_jl.setBounds(20,100,610,120);
        wygenerowana_liczba_jl.setHorizontalAlignment(SwingConstants.CENTER);
        wygenerowana_liczba_jl.setText("");
        ramka.add(wygenerowana_liczba_jl);

        potwierdzenie_zapamietania.setBounds(100,300,450,80);
        potwierdzenie_zapamietania.setFont(new Font("DejaVu Sans Mono", Font.PLAIN, 40));
        potwierdzenie_zapamietania.setHorizontalAlignment(SwingConstants.CENTER);
        potwierdzenie_zapamietania.addActionListener(this);
        ramka.add(potwierdzenie_zapamietania);

        postep = new JLabel[5];
        for(int i = 0; i<5;i++) {
            postep[i] = new JLabel(""+(i+1));
            postep[i].setOpaque(true);
            postep[i].setBackground(Color.WHITE);
            postep[i].setBorder(BorderFactory.createLineBorder(Color.black));
            postep[i].setHorizontalAlignment(SwingConstants.CENTER);
            postep[i].setFont(new Font("DejaVu Sans Mono", Font.PLAIN, 40));
            postep[i].setBounds(150+70*i,400,70,70);
            ramka.add(postep[i]);
        }

        rozpraszanie.setBounds(0,0,650,700);
        rozpraszanie.setVisible(false);
        ramka.add(rozpraszanie);

        wyswietlanie_wygenerowanego_numeru();
    }

    public int Generowanie_Numeru() {

        int wygenerowany_numer = -1;
        if (lp_zgadywanego_numeru == 1 || lp_zgadywanego_numeru == 2) {
            wygenerowany_numer = random.nextInt(90000)+10000; //5 cyfr
        }
        if (lp_zgadywanego_numeru == 3 || lp_zgadywanego_numeru == 4) {
            wygenerowany_numer = random.nextInt(900000)+100000; //6 cyfr
        }
        if (lp_zgadywanego_numeru == 5) {
            wygenerowany_numer = random.nextInt(9000000)+1000000; // 7 cyfr
        }
        return wygenerowany_numer;
    }

    public void wyswietlanie_wygenerowanego_numeru() {

        String wygenerowany_numer = String.valueOf(Generowanie_Numeru());
        wygenerowana_liczba_jl.setText(wygenerowany_numer);
        wygenerowana_liczba_jl.setFont(new Font("DejaVu Sans Mono", Font.PLAIN, 75));
        wygenerowana_liczba_jl.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == potwierdz) {
            int wygenerowany_numer = Integer.parseInt(wygenerowana_liczba_jl.getText()); // pobierz wartość z jlabel
            try {
                zmienna = Integer.parseInt(wprowadzenie_odpowiedzi.getText());
                if (wygenerowany_numer == zmienna) {
                    JOptionPane.showMessageDialog(null, "Dobrze", "", JOptionPane.INFORMATION_MESSAGE);
                    postep[lp_zgadywanego_numeru-1].setBackground(Color.GREEN);
                    lp_zgadywanego_numeru++;
                }
                else {
                    JOptionPane.showMessageDialog(null, "Żle, numerem do zapamiętania był: " + wygenerowany_numer, "Suzanna", JOptionPane.INFORMATION_MESSAGE);
                }
                wyswietlanie_wygenerowanego_numeru();
                potwierdzenie_zapamietania.setVisible(true);
                potwierdz.setVisible(false);
                wprowadzenie_odpowiedzi.setVisible(false);
                wprowadzenie_odpowiedzi.setText("");

            }
            catch (NumberFormatException ex) {
                if (wygenerowany_numer > Integer.MAX_VALUE){
                    System.out.println("test");
                }
                else {
                    System.out.println("test2");
                    JOptionPane.showMessageDialog(null, "Nie wpisałeś liczby, lub liczba wpisana przez Ciebie jest zbyt duża!",
                            "Uwaga!", JOptionPane.INFORMATION_MESSAGE);
                    wprowadzenie_odpowiedzi.setVisible(true);
                }
            }

            if ( lp_zgadywanego_numeru == 6) {

                System.out.println("koniec");
                System.exit(0); //tymczasowo, potem jak będą kolejne gry to zmienićna dispose
            }
        }
        if (e.getSource() == potwierdzenie_zapamietania) {
            System.out.println("pamiętasz?");
            System.out.println(zmienna + " to po nacisnieciu potwierdzenia zapamietania");
            rozpraszanie.setVisible(true);
            wygenerowana_liczba_jl.setVisible(false);
            potwierdz.setVisible(false);
            wprowadzenie_odpowiedzi.setVisible(false);
            potwierdzenie_zapamietania.setVisible(false);
            for(int i = 0; i<5;i++) {
                postep[i].setVisible(false);
            }

            Timer timer = new Timer(500, new ActionListener() { // tu potem dodac 0 zeby bylo 5 sekund
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("timer");
                    System.out.println(zmienna + "\t to z Timera");
                    rozpraszanie.setVisible(false);
                    potwierdz.setVisible(true);
                    wprowadzenie_odpowiedzi.setVisible(true);
                    for(int i = 0; i<5;i++) {
                        postep[i].setVisible(true);
                    }
                }
            });
            timer.setRepeats(false);
            timer.start();
        }
        if (e.getSource() == powrot_do_menu) {

            ramka.dispose();
            EventQueue.invokeLater(Menu::new);
        }
    }
}