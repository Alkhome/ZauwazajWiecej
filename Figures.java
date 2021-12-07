import java.awt.*;
import java.util.ArrayList;
import java.util.Random;


public class Figures extends Canvas{
    Random rng = new Random();
    int ilosc_wywolan = 0;
    ArrayList<Point> points;

    int ilosc_prostokatow;
    int ilosc_kwadratow;
    int ilosc_okregow;
    int ilosc_linii;

    public void paint(Graphics g) {

        points = new ArrayList<Point>();
        ilosc_prostokatow = 0;
        ilosc_kwadratow = 0;
        ilosc_okregow = 0;
        ilosc_linii = 0;

        for (int i = 0; i < ilosc_wywolan; i++) {
            int x = rng.nextInt(500) + 20;
            int y = rng.nextInt(400) + 20;
            points.add(new Point(x, y));
        }

            for (Point p : points) {

                int element = rng.nextInt(4) + 1;//problemem jest, że w tablicy nie zapisuje jaki obiekt stworzyło, więc jak odczytuje, to od nowa generuje ten random randint
                int x_point = (int)p.getX();
                int y_point = (int)p.getY();

                if (element == 1) { // prostokąt
                    int rect_width = rng.nextInt(30)+45;
                    int rect_height = rng.nextInt(20)+10;
                    g.setColor(new Color(128,0,128));
                    g.fillRect(x_point, y_point, rect_width, rect_height);
                    g.setColor(Color.BLACK);
                    ilosc_prostokatow++;
                }

                else if (element == 2) {
                    int r = rng.nextInt(20)+30;
                    g.drawOval(x_point, y_point, r, r);
                    ilosc_okregow++;
                }

                else if (element == 3) {
                    int a = rng.nextInt(20)+30;
                    g.drawRect(x_point, y_point, a, a);
                    ilosc_kwadratow++;
                }
                else if (element == 4) {
                    g.drawLine(x_point, y_point, x_point + rng.nextInt(20)+30, y_point + rng.nextInt(20)+30);
                    ilosc_linii++;
                }
                else {
                    System.out.println("huh?");
                }
            }
        }
    }

