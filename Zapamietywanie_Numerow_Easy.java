import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;


public class Zapamietywanie_Numerow_Easy extends JFrame implements ActionListener {

    Random random = new Random();
    JFrame ramka = new JFrame();
    JButton potwierdz = new JButton("Potwierdz - wersja robocza");
    JButton powrot_do_menu = new JButton("Powrot do Menu"); // a tutaj dodac zeby potwierdzic ze chce się wyjsc z gry
    JButton podpowiedz = new JButton("Podpowiedz");
    JButton potwierdzenie_zapamietania = new JButton("potwierdz zapoznanie sie czy cos");
    JLabel wygenerowana_liczba_jl = new JLabel();
    JTextField wprowadzenie_odpowiedzi = new JTextField();
    ImageIcon miniaturka = new ImageIcon("big_brain.jpg");
    int lp_zgadywanego_numeru = 1;
    JLabel rozpraszanie = new JLabel(new ImageIcon("random.jpeg"));

    public Zapamietywanie_Numerow_Easy()
    {

        super("Tekst");
        ramka.setTitle("Zapamietywanie numerow");
        ramka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ramka.setSize(650,700);
        ramka.setResizable(false);
        ramka.setVisible(true);
        ramka.setIconImage(miniaturka.getImage());
        ramka.setLocationRelativeTo(null);
        ramka.setLayout(null);
        
        potwierdz.addActionListener(this);
        potwierdz.setBounds(10,300,400,100);
        potwierdz.setVisible(false);
        ramka.add(potwierdz);

        wprowadzenie_odpowiedzi.setBounds(100,100,300,70);
        wprowadzenie_odpowiedzi.setHorizontalAlignment(SwingConstants.CENTER);
        wprowadzenie_odpowiedzi.setVisible(false);
        ramka.add(wprowadzenie_odpowiedzi);

        wygenerowana_liczba_jl.setBounds(100,400,300,70);
        wygenerowana_liczba_jl.setBorder(BorderFactory.createLineBorder(Color.black));
        wygenerowana_liczba_jl.setHorizontalAlignment(SwingConstants.CENTER);
        ramka.add(wygenerowana_liczba_jl);

        potwierdzenie_zapamietania.setBounds(100,500,300,30);
        potwierdzenie_zapamietania.setHorizontalAlignment(SwingConstants.CENTER);
        potwierdzenie_zapamietania.addActionListener(this);
        ramka.add(potwierdzenie_zapamietania);

        rozpraszanie.setBounds(0,0,650,700);
        rozpraszanie.setVisible(false);
        ramka.add(rozpraszanie);
        wyswietlanie_wygenerowanego_numeru();

    }

    public int Generowanie_Numeru()
    {
        int wygenerowany_numer = -1;
        if (lp_zgadywanego_numeru == 1 || lp_zgadywanego_numeru == 2)
        {
            wygenerowany_numer = random.nextInt(900)+100; //zmienic, zeby losowalo liczbe 3 cyfrowe
        }
        if (lp_zgadywanego_numeru == 3 || lp_zgadywanego_numeru == 4)
        {
            wygenerowany_numer = random.nextInt(9000) + 1000; //zmienic, zeby losowalo liczbe 4 cyfrowe
        }
        if (lp_zgadywanego_numeru == 5)
        {
            wygenerowany_numer = random.nextInt(90000)+10000; // zmienic zeby losowalo liczbe 5 cyfrowa
        }
        return wygenerowany_numer;
    }

    public void wyswietlanie_wygenerowanego_numeru()
    {
        String wygenerowany_numer = String.valueOf(Generowanie_Numeru());
        wygenerowana_liczba_jl.setText(wygenerowany_numer);
        wygenerowana_liczba_jl.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == potwierdz) {
            int wygenerowany_numer = Integer.parseInt(wygenerowana_liczba_jl.getText()); // pobierz wartość z jlabel
            System.out.println(wygenerowany_numer);
            int zmienna = Integer.parseInt(wprowadzenie_odpowiedzi.getText()); //pobierz wartość wpisanąprzez użytkownika
            //^ tu trzeba dać obslugęwyjątkow, jakby ktoś wpisał co innego niż cyfry
            System.out.println(zmienna);
            if (wygenerowany_numer == zmienna) {
                System.out.println("to samo");
                lp_zgadywanego_numeru++;
            }
            else{
                System.out.println("masz slaba pamiec");
            }

            wyswietlanie_wygenerowanego_numeru();
            potwierdzenie_zapamietania.setVisible(true);
            potwierdz.setVisible(false);
            wprowadzenie_odpowiedzi.setVisible(false); // musi jeszcze czyścić to pole tekstowy, ttylko nie wy=iem czy tu
            if ( lp_zgadywanego_numeru == 6)
            {
                System.out.println("koniec");
            }
        }
        if (e.getSource() == potwierdzenie_zapamietania) {
            System.out.println("pamiętasz?");
            rozpraszanie.setVisible(true);
            wygenerowana_liczba_jl.setVisible(false);
            potwierdz.setVisible(false);
            wprowadzenie_odpowiedzi.setVisible(false);
            potwierdzenie_zapamietania.setVisible(false);


        Timer timer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("timer");
                rozpraszanie.setVisible(false);
                potwierdz.setVisible(true);
                wprowadzenie_odpowiedzi.setVisible(true);

            }
        });
        timer.setRepeats(false);
        timer.start();
    }
    }
}


 /*
       Dodać jakieś okienko że brawo zgadłeś albo że źle
       Niech usuwa wpisany tekst z okienka po wpisaniu
         */
