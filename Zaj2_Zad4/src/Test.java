import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

public class Test
{

    static private LinkedList<Prostokat> figury;

    private static void showMenu()
    {
        System.out.println("1. Wczytaj prostokąt");
        System.out.println("2. Wyświetl wszystkie prostokąty");
        System.out.println("3. Oblicz sumę pól wszystkich prostokątów");
        System.out.println("4. Zakończ");
        System.out.println("Wybierz opcję:");
    }

    private static int getInt()
    {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    private static double getDouble()
    {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextDouble();
    }

    private static void loadRectangle()
    {
        double a,b;
        System.out.println("Podaj bok A prostokąta:");
        a = getDouble();
        System.out.println("Podaj bok B prostokąta:");
        b = getDouble();
        figury.add(new Prostokat(a,b));
    }

    private static void showAllRectangles()
    {
        int counter = 0;
        for (Prostokat prostokat:figury) {
            ++counter;
            System.out.println("Prostokąt "+counter+":");
            System.out.println("Bok A: "+prostokat.getA()+" Bok B: "+prostokat.getB()+" Pole: "+prostokat.area());
        }
    }

    private static void showSumOfFields()
    {
        double sum = 0;
        for (Prostokat prostokat:figury) {
            sum += prostokat.area();
        }
        System.out.println("Suma pól wynosi: "+sum);
    }

    public static void main(String[] argv)
    {
        boolean end = false;
        figury = new LinkedList<Prostokat>();
        while (!end)
        {
            showMenu();
            switch (getInt())
            {
                case 1:
                    loadRectangle();
                    break;
                case 2:
                    showAllRectangles();
                    break;
                case 3:
                    showSumOfFields();
                    break;
                case 4:
                    end = true;
                    break;

                    default:
                        break;
            }
        }
    }
}
