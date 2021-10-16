import java.awt.GridLayout;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.EventQueue;
import java.util.Random;

public class Shulte_Table_Medium extends JFrame implements MouseListener{

    JLabel okno[];
    private int kolejna_liczba = 1;

    public static void shuffle(int array[])
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

        super("Shulte Medium");
        setTitle("Twoim zadaniem jest znaleźć liczbę 1.");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,500);
        setResizable(false);
        setLayout(new GridLayout(5,5));
        addMouseListener(this); // dodaje wykrywanie kliknięcia

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

        for (JLabel jLabel : okno) add(jLabel); // dodaje wszystkie kwadraty
        setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) { //naciśnięcie myszki

        JLabel nacisniete = (JLabel) e.getSource();

        if (Integer.parseInt(nacisniete.getText()) == kolejna_liczba) {
            setTitle("Ostatnia liczba, która została znaleziona to: " + kolejna_liczba);
            kolejna_liczba++;
            System.out.println("Teraz musisz nacisnąć: " + kolejna_liczba);
            //dzwiek ze dobrze

        }
        if(kolejna_liczba == 26) {
            System.out.println("Przeszedłeś poziom");
            EventQueue.invokeLater(new Runnable() {
                @Override
                 public void run() {new Zapamietywanie_Numerow_Easy();}
            });

        }
    }
    public void mousePressed(MouseEvent e){}
    public void mouseEntered(MouseEvent e) // zmienia kolor na szary, żeby można było zobaczyć, na którym polu jest kursor
    {
        JLabel kursor_wjechal = (JLabel) e.getSource();
        kursor_wjechal.setBackground(Color.LIGHT_GRAY);

    }
    public void mouseExited(MouseEvent e)
    {
        JLabel kursor_oposcil = (JLabel) e.getSource();
        kursor_oposcil.setBackground(Color.WHITE);
    } // po wyjściu kursora z danego pola, zmienia z powrotem na białe
    public void mouseReleased(MouseEvent e){}

}
