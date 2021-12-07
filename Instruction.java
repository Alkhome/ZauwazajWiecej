import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileReader;
import java.io.IOException;

public class Instruction extends JFrame implements ActionListener{
    JFrame ramka = new JFrame();
    JButton przycisk_powrotu = new JButton("Powrot do Menu");
    ImageIcon miniaturka = new ImageIcon("big_brain.jpg");
    JTextArea instrukcja_do_gry = new JTextArea();
    JScrollPane sp = new JScrollPane(instrukcja_do_gry); //sa- scrollable pane


    public Instruction(){
        super();
        ramka.setTitle("INSTRUKCJA");
        ramka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ramka.setSize(650,700);
        ramka.setResizable(false);
        ramka.setVisible(true);
        ramka.setIconImage(miniaturka.getImage());
        ramka.setLocationRelativeTo(null);
        ramka.setLayout(null);
        sp.setBounds(20,20,610,580);
        ramka.getContentPane().add(sp);
        ramka.getContentPane().setBackground(new Color(107, 184, 202));


        przycisk_powrotu.setBounds(430,610,200,50);//zmienic - obliczyc
        przycisk_powrotu.setFont(new Font("DejaVu Sans Mono", Font.PLAIN, 20));
        ramka.add(przycisk_powrotu);
        przycisk_powrotu.addActionListener(this);

        try {
            FileReader filereader = new FileReader("instrukcja_do_gry.txt");
            instrukcja_do_gry.read(filereader,"instrukcja do gry");
        } catch (IOException ex) {
            ex.printStackTrace();
        }


    }


    @Override
    public void actionPerformed(ActionEvent e) {
       Menu.ramka.setVisible(true);
       ramka.dispose();


    }


}
