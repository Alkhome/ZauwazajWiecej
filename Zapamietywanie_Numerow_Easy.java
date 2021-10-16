import javax.swing.*;
import java.awt.*;

public class Zapamietywanie_Numerow_Easy extends JFrame {
    JLabel tekst = new JLabel("Test");
    public Zapamietywanie_Numerow_Easy()
    {

        super("Tekst");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,500);
        setResizable(false);
        setVisible(true);
        tekst.setHorizontalAlignment(SwingConstants.CENTER);
        add(tekst);


    }

}