import java.awt.GridLayout;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.EventQueue;
import java.util.Random;

public class Shulte_Table_Medium extends JFrame implements MouseListener {
    JFrame ramka_shulte_medium = new JFrame();
    JLabel[] okno;
    JLabel powrot_do_menu = new JLabel("Powrot do menu");
    JPanel obszar_gry = new JPanel();
    public static int kolejna_liczba;
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


    public Shulte_Table_Medium(){

        super("Shulte Easy");

        kolejna_liczba = 1;

        ramka_shulte_medium.setTitle("Twoim zadaniem jest znaleźć liczbę 1.");
        ramka_shulte_medium.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ramka_shulte_medium.setSize(650,700);
        ramka_shulte_medium.setResizable(false);
        ramka_shulte_medium.setLayout(null);
        ramka_shulte_medium.setIconImage(miniaturka.getImage());
        ramka_shulte_medium.setLocationRelativeTo(null);
        ramka_shulte_medium.getContentPane().setBackground(new Color(107, 184, 202));


        obszar_gry.setLayout(new GridLayout(5,5));
        obszar_gry.setSize(650,600);
        obszar_gry.setVisible(true);

        ramka_shulte_medium.add(obszar_gry);

        powrot_do_menu.setBounds(15,620,170,30);
        powrot_do_menu.setBorder(BorderFactory.createLineBorder(Color.black));
        powrot_do_menu.addMouseListener(this);
        powrot_do_menu.setOpaque(true);
        powrot_do_menu.setBackground(Color.WHITE);
        powrot_do_menu.setFont(new Font("Helvetica Neue", Font.PLAIN, 20));
        powrot_do_menu.setHorizontalAlignment(SwingConstants.CENTER);
        ramka_shulte_medium.add(powrot_do_menu);

        okno = new JLabel[25];
        int liczby_medium[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25};
        shuffle(liczby_medium);

        for (int i = 0;i<25;i++){

            okno[i]=new JLabel(""+liczby_medium[i]); //wypełnia środki kwadratów kolejnymi liczbami z pomieszanej tablicy
            okno[i].setOpaque(true);
            okno[i].setBackground( Color.WHITE);
            okno[i].setHorizontalAlignment(SwingConstants.CENTER);
            okno[i].setFont(new Font("Arial", Font.PLAIN, 40)); //większa czcionka, żeby było lepiej widać
            okno[i].setBorder(BorderFactory.createLineBorder(Color.black));
            okno[i].addMouseListener(this); // ten sam Listener do każdego kwadratu
        }

        for (JLabel jLabel : okno) obszar_gry.add(jLabel); // dodaje wszystkie kwadraty
        ramka_shulte_medium.setVisible(true);
    }

    @Override //problem jest funkcja if tutaj, przydaloby sie ją zmienić, bo wywala błąd jak naciska się litery nie cyfry
    public void mouseClicked(MouseEvent e) { //naciśnięcie myszki

        JLabel nacisniete = (JLabel) e.getSource();

        if (String.valueOf(nacisniete.getText()).equals( String.valueOf(kolejna_liczba))) {
            ramka_shulte_medium.setTitle("Ostatnia liczba, która została znaleziona to: " + kolejna_liczba);
            kolejna_liczba++;
            System.out.println("Teraz musisz nacisnąć: " + kolejna_liczba);

        }
        if(kolejna_liczba == 22) { //do zmiany

            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {new Zapamietywanie_Numerow_Easy();}

            });
        }
        if (kolejna_liczba == 26)
        {
            System.out.println("Przeszedłeś poziom");
            Zapamietywanie_Numerow_Easy.ramka.setVisible(true);
            ramka_shulte_medium.dispose();
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
            ramka_shulte_medium.dispose();
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {new Menu();}

            });
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

