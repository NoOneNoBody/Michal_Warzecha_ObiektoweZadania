import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String [] args){

        int option1;
        int option2;
        Scanner scanner = new Scanner(System.in);

        if(args.length < 2){
            System.out.println("Za mało argumentów");
            System.exit(0);
        }

        System.out.println("Wybierz opcję:");
        System.out.println("1. Szyfrowanie");
        System.out.println("2. Deszyfrowanie");

        option1 = scanner.nextInt();
        if(option1 != 1 && option1 != 2){
            System.out.println("Zła opcja");
            System.exit(0);
        }

        System.out.println("Wybierz algorytm:");
        System.out.println("1. ROT11");
        System.out.println("2. Szachownica polibiusza");

        option2 = scanner.nextInt();

        Algorithm algorithm = new ROT11();

        switch (option2){
            case 1:
                algorithm = new ROT11();
                break;
            case 2:
                algorithm = new Polibiusz();
                break;
            default:
                System.out.println("Zła opcja");
                System.exit(0);
        }

        File sourceFile = new File(args[0]);
        File destFile = new File(args[1]);

        try {


            switch (option1){
                case 1:
                    Cryptographer.cryptfile(sourceFile, destFile, algorithm);
                    break;
                case 2:
                    Cryptographer.decryptfile(sourceFile, destFile, algorithm);
                    break;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
