import java.util.InputMismatchException;
import java.util.Scanner;

public class Launcher {
    public static void main(String[] args)
    {
        try {
        //Définition des variables
        int[] tab= {0,0,0,1,0,0,1,2,0};
        //Lancement du script
        Scanner input = new Scanner(System.in);
        System.out.println("1. Morpion2D");
        System.out.println("2. Morpion3D");
        System.out.println("Choisissez votre mode de jeu");

            int gameMode = input.nextInt();
            switch (gameMode) {
                case 1:
                    Morpion2D game = new Morpion2D(3,tab);
                    game.start();
                    break;
//                case 2:
//                    Morpion3D game = new Morpion3D();
//                    game.start();
//                    break;
                default:
                    System.out.println("Choix invalide, veuillez réessayer.");
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrée invalide, veuillez réessayer.");
        }
    }
}
