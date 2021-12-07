import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Zapamietywanie_Numerow_Easy implements ActionListener {

    Random random = new Random();
    JFrame ramka = new JFrame();
    JButton potwierdz = new JButton("Potwierdź");
    JButton powrot_do_menu = new JButton("Powrot do menu");
    JButton potwierdzenie_zapamietania = new JButton("Zapamiętano");
    JLabel wygenerowana_liczba_jl = new JLabel();
    JTextField wprowadzenie_odpowiedzi = new JTextField();
    ImageIcon miniaturka = new ImageIcon("big_brain.jpg");
    int lp_zgadywanego_numeru = 1;
    int zmienna;
    JLabel rozpraszanie = new JLabel(new ImageIcon("random.jpeg"));
    JLabel[] postep;


    public Zapamietywanie_Numerow_Easy()
    {

        ramka.setTitle("Zapamietywanie numerow");
        ramka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ramka.setSize(650,700);
        ramka.setResizable(false);
        ramka.setVisible(true);
        ramka.setIconImage(miniaturka.getImage());
        ramka.setLocationRelativeTo(null);
        ramka.setLayout(null);
        ramka.getContentPane().setBackground(new Color(107, 184, 202));


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

        powrot_do_menu.setBounds(430,610,200,50);
        powrot_do_menu.setHorizontalAlignment(SwingConstants.CENTER);
        powrot_do_menu.addActionListener(this);
        powrot_do_menu.setFont(new Font("DejaVu Sans Mono", Font.PLAIN, 20));
        ramka.add(powrot_do_menu);

        postep = new JLabel[5];
        for(int i = 0; i<5;i++)
        {
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

    public int Generowanie_Numeru()
    {
        int wygenerowany_numer = -1;
        if (lp_zgadywanego_numeru == 1 || lp_zgadywanego_numeru == 2) {
            wygenerowany_numer = random.nextInt(900) + 100;
        } //3 cyfry

        if (lp_zgadywanego_numeru == 3 || lp_zgadywanego_numeru == 4) {
            wygenerowany_numer = random.nextInt(9000) + 1000;
        }  //4 cyfry
        if (lp_zgadywanego_numeru == 5) {
            wygenerowany_numer = random.nextInt(90000)+10000;
        }  // 5 cyfr
        return wygenerowany_numer;
    }

    public void wyswietlanie_wygenerowanego_numeru()
    {
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
            catch (NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(null, "Nie wpisałeś liczby!\nPamiętaj, żeby wpisywać tylko liczby",
                        "Uwaga!", JOptionPane.INFORMATION_MESSAGE);
                wprowadzenie_odpowiedzi.setVisible(true);
            }

            if ( lp_zgadywanego_numeru == 6)
            {
                Which_Figure fig = new Which_Figure();
                fig.difficulty = 1;
                fig.ramka.setVisible(true);
                ramka.dispose();
            }
        }
        if (e.getSource() == potwierdzenie_zapamietania) {
            rozpraszanie.setVisible(true);
            wygenerowana_liczba_jl.setVisible(false);
            potwierdz.setVisible(false);
            wprowadzenie_odpowiedzi.setVisible(false);
            potwierdzenie_zapamietania.setVisible(false);


        Timer timer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rozpraszanie.setVisible(false);
                potwierdz.setVisible(true);
                wprowadzenie_odpowiedzi.setVisible(true);

            }
        });
        timer.setRepeats(false);
        timer.start();
    }
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