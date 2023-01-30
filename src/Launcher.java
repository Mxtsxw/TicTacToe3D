import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Launcher {
    public static void main(String[] args)
    {
        boolean tryAgain = true;
        while (tryAgain){

            try {
                //Définition des variables

                //Lancement du script
                Scanner input = new Scanner(System.in);
                System.out.println("1. Morpion2D");
                System.out.println("2. Morpion3D");
                System.out.println("Choisissez votre mode de jeu :");

                int gameMode = input.nextInt();
                switch (gameMode) {
                    case 1:
                        System.out.println("coisissez la taille de votre jeu(doit être un entier supérieur à 3) :");
                        try {
                            int n = input.nextInt();
                            if (n<=2){
                                throw new IllegalArgumentException("La taille doit être un entier supérieur à 3!");
                            }
                            int[] tab = new int[n*n];
                            Arrays.fill(tab, 0);
                            Morpion2D game = new Morpion2D(n,tab);
                            game.start(game);
                            tryAgain = false;
                            break;
                        } catch(InputMismatchException e) {
                            System.out.println("Entrée invalide, veuillez entrer un entier!");
                        } catch(IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        }

//                case 2:
//                    Morpion3D game = new Morpion3D();
//                    game.start();
//                    tryAgain = false;
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
}