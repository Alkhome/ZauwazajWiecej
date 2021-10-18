import java.awt.GridLayout;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.EventQueue;
import java.util.Random;

public class Shulte_Table_Easy extends JFrame implements MouseListener{
    JFrame ramka_shulte_easy = new JFrame();
    JLabel okno[];
    public static int kolejna_liczba = 1;
    ImageIcon miniaturka = new ImageIcon("big_brain.jpg");

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


    public Shulte_Table_Easy(){

        super("Shulte Easy");
        ramka_shulte_easy.setTitle("Twoim zadaniem jest znaleźć liczbę 1.");
        ramka_shulte_easy.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ramka_shulte_easy.setSize(500,500);
        ramka_shulte_easy.setResizable(false);
        ramka_shulte_easy.setLayout(new GridLayout(3,3));
        ramka_shulte_easy.setIconImage(miniaturka.getImage());
        ramka_shulte_easy.setLocationRelativeTo(null);

        okno = new JLabel[9];
        int liczby_easy[] = {1,2,3,4,5,6,7,8,9};
        shuffle(liczby_easy);

        for (int i = 0;i<9;i++){

            okno[i]=new JLabel(""+liczby_easy[i]); //wypełnia środki kwadratów kolejnymi liczbami z pomieszanej tablicy
            okno[i].setOpaque(true);
            okno[i].setBackground( Color.WHITE);
            okno[i].setHorizontalAlignment(SwingConstants.CENTER);
            okno[i].setFont(new Font("Arial", Font.PLAIN, 40)); //większa czcionka, żeby było lepiej widać
            okno[i].setBorder(BorderFactory.createLineBorder(Color.black));
            okno[i].addMouseListener(this); // ten sam Listener do każdego kwadratu
        }

        for (JLabel jLabel : okno) ramka_shulte_easy.add(jLabel); // dodaje wszystkie kwadraty
        ramka_shulte_easy.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) { //naciśnięcie myszki

        JLabel nacisniete = (JLabel) e.getSource();

        if (Integer.parseInt(nacisniete.getText()) == kolejna_liczba) {
            ramka_shulte_easy.setTitle("Ostatnia liczba, która została znaleziona to: " + kolejna_liczba);
            kolejna_liczba++;
            System.out.println("Teraz musisz nacisnąć: " + kolejna_liczba);

            // TimeUnit.MILLISECONDS.sleep(100);


        }
        if(kolejna_liczba == 10) {
            System.out.println("Przeszedłeś poziom");
            /*EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {new Zapamietywanie_Numerow_Easy();}

            });*/
            ramka_shulte_easy.dispose();
        }
    }
    public void mousePressed(MouseEvent e) {
        JLabel wcisniete = (JLabel) e.getSource();
        if (Integer.parseInt(wcisniete.getText()) == kolejna_liczba) {
            wcisniete.setBackground(Color.GREEN);
        }
        else if (Integer.parseInt(wcisniete.getText()) != kolejna_liczba) {
            wcisniete.setBackground(Color.RED);
        }
    }
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
    public void mouseReleased(MouseEvent e){
        JLabel przycisk_odpuszczony = (JLabel) e.getSource();
        przycisk_odpuszczony.setBackground(Color.LIGHT_GRAY);
    }

}