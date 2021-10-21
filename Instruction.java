import javax.swing.*;
import java.awt.event.*;

public class Instruction extends JFrame implements ActionListener{
    JFrame ramka = new JFrame();
    JButton kolejna_strona = new JButton("Kolejna Strona");
    JButton przycisk_powrotu = new JButton("Powrot do Menu");
    ImageIcon miniaturka = new ImageIcon("big_brain.jpg");
    JTextArea instrukcja_do_gry = new JTextArea("Lorem ipsum dolor sit amet, \n" +
            "consectetur adipiscing elit. Donec nec ante ac nisi\n" +
            " facilisis varius. \n" +
            "Donec ac dui id felis scelerisque eleifend id nec magna. \n" +
            "Mauris ante felis, \n" +
            "laoreet et leo non, sagittis ullamcorper libero. \n" +
            "Pellentesque a condimentum nunc. Mauris \n" +
            "viverra metus id augue \n" +
            "scelerisque, et tempus odio consequat. Ut id est lacinia, mollis tellus \n" +
            "fermentum, ultrices justo. Vestibulum \n" +
            "metus elit, varius eget magna eu, \n" +
            "suscipit pharetra justo. Pellentesque habitant \n" +
            "morbi tristique senectus et\n" +
            " netus et malesuada fames ac turpis egestas. \n\n\n\n\n\n" +
            "Fusce vel varius tellus. \n" +
            "Nulla pharetra tempor tincidunt. Vivamus ac metus ante.\n" +
            " Duis molestie \n" +
            "ut tellus at malesuada. Quisque euismod ligula sit amet \n" +
            "hendrerit consectetur. \n" +
            "Quisque vulputate elit sapien, sit amet vulputate\n" +
            " enim dictum eget. \n" +
            "Pellentesque non urna sagittis, convallis\n" +
            " arcu sit amet, consectetur sapien.");
    JScrollPane sp = new JScrollPane(instrukcja_do_gry); //sa- scrollable pane

    public Instruction(){
        super("Instrukcja");
        ramka.setTitle("Probojemy cos");
        ramka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ramka.setSize(700,700);
        ramka.setResizable(false);
        ramka.setVisible(true);
        ramka.setIconImage(miniaturka.getImage());
        ramka.setLocationRelativeTo(null);
        ramka.setLayout(null);
        sp.setBounds(20,20,460,370);
        ramka.getContentPane().add(sp);
        Powrot_Do_Menu();

    }
    public void Powrot_Do_Menu(){

        przycisk_powrotu.setBounds(290,410,200,50);//zmienic - obliczyc
        ramka.add(przycisk_powrotu);
        przycisk_powrotu.addActionListener(this);
        };

    @Override
    public void actionPerformed(ActionEvent e) {
       Menu.ramka.setVisible(true);
       ramka.dispose();


    }


}
