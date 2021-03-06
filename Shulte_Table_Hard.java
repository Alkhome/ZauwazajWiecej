import java.awt.GridLayout;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Shulte_Table_Hard implements MouseListener {
    JFrame ramka_shulte_hard = new JFrame();
    JLabel[] okno;
    JLabel powrot_do_menu = new JLabel("Powrot do menu");
    JPanel obszar_gry = new JPanel();
    int kolejna_liczba;
    ImageIcon miniaturka = new ImageIcon("big_brain.jpg");

    public static void shuffle(int[] array)
    {
        Random random = new Random();
        for(int i = 0; i < array.length; i++)
        {
            int losowy_index = random.nextInt(array.length);
            int losowa_cyfra_z_tablicy = array[losowy_index];
            array[losowy_index] = array[i];
            array[i] = losowa_cyfra_z_tablicy;
        }
    }


    public Shulte_Table_Hard(){

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyy HH:mm:ss");
        LocalDateTime ldt = LocalDateTime.now();
        String czas_poczatkowy = dtf.format(ldt);

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

            try {
                FileWriter myWriter = new FileWriter("scores.txt", false);
                tempsArray[0] = czas_poczatkowy;
                for (int i = 0; i < tempsArray.length; i++) {
                    myWriter.write(tempsArray[i] + "\n");
                }
                myWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        kolejna_liczba = 1;

        ramka_shulte_hard.setTitle("Twoim zadaniem jest znale???? liczb?? 1.");
        ramka_shulte_hard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ramka_shulte_hard.setSize(650,700);
        ramka_shulte_hard.setResizable(false);
        ramka_shulte_hard.setLayout(null);
        ramka_shulte_hard.setIconImage(miniaturka.getImage());
        ramka_shulte_hard.setLocationRelativeTo(null);
        ramka_shulte_hard.getContentPane().setBackground(new Color(107, 184, 202));


        obszar_gry.setLayout(new GridLayout(7,7));
        obszar_gry.setSize(650,600);
        obszar_gry.setVisible(true);

        ramka_shulte_hard.add(obszar_gry);

        powrot_do_menu.setBounds(15,620,170,30);
        powrot_do_menu.setBorder(BorderFactory.createLineBorder(Color.black));
        powrot_do_menu.addMouseListener(this);
        powrot_do_menu.setOpaque(true);
        powrot_do_menu.setBackground(Color.WHITE);
        powrot_do_menu.setFont(new Font("DejaVu Sans Mono", Font.PLAIN, 20));
        powrot_do_menu.setHorizontalAlignment(SwingConstants.CENTER);
        ramka_shulte_hard.add(powrot_do_menu);

        okno = new JLabel[49];
        int liczby_hard[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,
                27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49 };
        shuffle(liczby_hard);

        for (int i = 0;i<49;i++){
            okno[i]=new JLabel(""+liczby_hard[i]); //wype??nia ??rodki kwadrat??w kolejnymi liczbami z pomieszanej tablicy
            okno[i].setOpaque(true);
            okno[i].setBackground( Color.WHITE);
            okno[i].setHorizontalAlignment(SwingConstants.CENTER);
            okno[i].setFont(new Font("Arial", Font.PLAIN, 40)); //wi??ksza czcionka, ??eby by??o lepiej wida??
            okno[i].setBorder(BorderFactory.createLineBorder(Color.black));
            okno[i].addMouseListener(this); // ten sam Listener do ka??dego kwadratu
        }

        for (JLabel jLabel : okno) obszar_gry.add(jLabel); // dodaje wszystkie kwadraty
        ramka_shulte_hard.setVisible(true);
    }

    @Override //problem jest funkcja if tutaj, przydaloby sie j?? zmieni??, bo wywala b????d jak naciska si?? litery nie cyfry
    public void mouseClicked(MouseEvent e) { //naci??ni??cie myszki

        JLabel nacisniete = (JLabel) e.getSource();

        if (String.valueOf(nacisniete.getText()).equals( String.valueOf(kolejna_liczba))) {
            ramka_shulte_hard.setTitle("Ostatnia liczba, kt??ra zosta??a znaleziona to: " + kolejna_liczba);
            kolejna_liczba++;
            System.out.println("Teraz musisz nacisn????: " + kolejna_liczba);

        }
        if(kolejna_liczba == 46) {

            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {new Zapamietywanie_Numerow_Easy();}

            });
        }
        if (kolejna_liczba == 50)
        {
            Zapamietywanie_Numerow_Hard znh = new Zapamietywanie_Numerow_Hard();
            znh.ramka.setVisible(true);
            ramka_shulte_hard.dispose();
        }
    }
    public void mousePressed(MouseEvent e) {
        JLabel wcisniete = (JLabel) e.getSource();
        if (String.valueOf(wcisniete.getText()).equals( String.valueOf(kolejna_liczba))) {
            wcisniete.setBackground(Color.GREEN);
        }
        if (!String.valueOf(wcisniete.getText()).equals( String.valueOf(kolejna_liczba))) {
            wcisniete.setBackground(Color.RED);
        }
        if (String.valueOf(wcisniete.getText()).equals("Powrot do menu"))
        {
            ramka_shulte_hard.dispose();
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {new Menu();}

            });
        }
    }
    public void mouseEntered(MouseEvent e) // zmienia kolor na szary, ??eby mo??na by??o zobaczy??, na kt??rym polu jest kursor
    {
        JLabel kursor_wjechal = (JLabel) e.getSource();
        if (String.valueOf(kursor_wjechal.getText()).equals("Powrot do menu"))
            kursor_wjechal.setBackground(Color.RED);
        else
            kursor_wjechal.setBackground(Color.LIGHT_GRAY);

    }
    public void mouseExited(MouseEvent e)
    {
        JLabel kursor_oposcil = (JLabel) e.getSource();
        kursor_oposcil.setBackground(Color.WHITE);
    } // po wyj??ciu kursora z danego pola, zmienia z powrotem na bia??e
    public void mouseReleased(MouseEvent e){
        JLabel przycisk_odpuszczony = (JLabel) e.getSource();
        przycisk_odpuszczony.setBackground(Color.LIGHT_GRAY);
    }

}

