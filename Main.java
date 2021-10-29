import java.awt.EventQueue;


public class Main {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() { //poczytac o funkcji lambda
            @Override
         public void run() {new Zapamietywanie_Numerow_Easy();}
        });
    }
}