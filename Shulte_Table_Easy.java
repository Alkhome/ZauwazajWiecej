import java.awt.GridLayout;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.EventQueue;
import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Shulte_Table_Easy implements MouseListener {
    JFrame ramka_shulte_easy = new JFrame();
    JLabel[] okno;
    JLabel powrot_do_menu = new JLabel("Powrot do menu");
    JPanel obszar_gry = new JPanel();

    int kolejna_liczba;

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


    public Shulte_Table_Easy(){

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

        ramka_shulte_easy.setTitle("Twoim zadaniem jest znaleźć liczbę 1.");
        ramka_shulte_easy.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ramka_shulte_easy.setSize(650,700);
        ramka_shulte_easy.setResizable(false);
        ramka_shulte_easy.setLayout(null);
        ramka_shulte_easy.setLocationRelativeTo(null);
        ramka_shulte_easy.getContentPane().setBackground(new Color(107, 184, 202));

        obszar_gry.setLayout(new GridLayout(3,3));
        obszar_gry.setSize(650,600);
        obszar_gry.setVisible(true);

        ramka_shulte_easy.add(obszar_gry);

        powrot_do_menu.setBounds(15,620,170,30);
        powrot_do_menu.setBorder(BorderFactory.createLineBorder(Color.black));
        powrot_do_menu.addMouseListener(this);
        powrot_do_menu.setOpaque(true);
        powrot_do_menu.setBackground(Color.WHITE);
        powrot_do_menu.setFont(new Font("DejaVu Sans Mono", Font.PLAIN, 20));
        powrot_do_menu.setHorizontalAlignment(SwingConstants.CENTER);
        ramka_shulte_easy.add(powrot_do_menu);

        okno = new JLabel[9];
        int[] liczby_easy = {1,2,3,4,5,6,7,8,9};
        shuffle(liczby_easy);

        for (int i = 0;i<9;i++){

            okno[i]=new JLabel(""+liczby_easy[i]); //wypełnia środki kwadratów kolejnymi liczbami z pomieszanej tablicy
            okno[i].setOpaque(true);
            okno[i].setBackground(Color.WHITE);
            okno[i].setHorizontalAlignment(SwingConstants.CENTER);
            okno[i].setFont(new Font("DejaVu Sans Mono", Font.PLAIN, 40)); //większa czcionka, żeby było lepiej widać
            okno[i].setBorder(BorderFactory.createLineBorder(Color.black));
            okno[i].addMouseListener(this); // ten sam Listener do każdego kwadratu
        }

        for (JLabel jLabel : okno) obszar_gry.add(jLabel); // dodaje wszystkie kwadraty
        ramka_shulte_easy.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) { //naciśnięcie myszki

        JLabel nacisniete = (JLabel) e.getSource();

        if (String.valueOf(nacisniete.getText()).equals( String.valueOf(kolejna_liczba))) {
            ramka_shulte_easy.setTitle("Ostatnia liczba, która została znaleziona to: " + kolejna_liczba);
            kolejna_liczba++;
            System.out.println("Teraz musisz nacisnąć: " + kolejna_liczba);

        }
        if(kolejna_liczba == 10) { //zmenic, to tylko po to, zeby naprawic blad w kolejnej grze

            Zapamietywanie_Numerow_Easy zne = new Zapamietywanie_Numerow_Easy();
            zne.ramka.setVisible(true);
            ramka_shulte_easy.dispose();
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
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {new Menu();}

            });
            ramka_shulte_easy.dispose();
        }
    }
    public void mouseEntered(MouseEvent e) // zmienia kolor na szary, żeby można było zobaczyć, na którym polu jest kursor
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
    } // po wyjściu kursora z danego pola, zmienia z powrotem na białe
    public void mouseReleased(MouseEvent e){
        JLabel przycisk_odpuszczony = (JLabel) e.getSource();
        przycisk_odpuszczony.setBackground(Color.LIGHT_GRAY);
    }

}

