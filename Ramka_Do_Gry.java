import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Font;

public class Ramka_Do_Gry {
    static JFrame ramka = new JFrame();
    JButton powrot_do_menu = new JButton("Powrot do menu");
    ImageIcon miniaturka = new ImageIcon("big_brain.jpg");
    public Ramka_Do_Gry()
    {
        ramka.setTitle("Zapamietywanie numerow");
        ramka.getContentPane().setBackground(new Color(107, 184, 202));
        ramka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ramka.setSize(650,700);
        ramka.setResizable(false);
        ramka.setVisible(true);
        ramka.setIconImage(miniaturka.getImage());
        ramka.setLocationRelativeTo(null);
        ramka.setLayout(null);

        powrot_do_menu.setBounds(430,610,200,50);
        powrot_do_menu.setHorizontalAlignment(SwingConstants.CENTER);
        powrot_do_menu.setFont(new Font("DejaVu Sans Mono", Font.PLAIN, 20));
        ramka.add(powrot_do_menu);
    }

}
