import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Launcher {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("1. Morpion2D");
        System.out.println("2. Morpion3D");
        System.out.println("Choisissez votre mode de jeu :");

        boolean gameModeValid = false;
        int gameMode = 0;

        while (!gameModeValid) {
            try {
                gameMode = input.nextInt();
                if (gameMode == 1 || gameMode == 2) {
                    gameModeValid = true;
                } else {
                    System.out.println("Choix invalide, veuillez réessayer.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrée invalide, veuillez entrer un entier.");
                input.next();
            }
        }

        switch (gameMode) {
            case 1:
                boolean nValid = false;
                int n = 0;
                while (!nValid) {
                    System.out.println("Choisissez la taille de votre jeu (doit être un entier supérieur ou égal à 3) :");
                    try {
                        n = input.nextInt();
                        if (n <= 2) {
                            throw new IllegalArgumentException("La taille doit être un entier supérieur ou égal à 3.");
                        }
                        nValid = true;
                    } catch (InputMismatchException e) {
                        System.out.println("Entrée invalide, veuillez entrer un entier.");
                        input.next();
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                }
                int[] tab2D = new int[n * n];
                Arrays.fill(tab2D, 0);
                Morpion2D game2D = new Morpion2D(n, tab2D);
                game2D.start();
                break;
            case 2:
                nValid = false;
                n = 0;
                while (!nValid) {
                    System.out.println("Choisissez la taille de votre jeu (doit être un entier supérieur ou égal à 3) :");
                    try {
                        n = input.nextInt();
                        if (n <= 2) {
                            throw new IllegalArgumentException("La taille doit être un entier supérieur ou égal à 3.");
                        }
                        nValid = true;
                    } catch (InputMismatchException e) {
                        System.out.println("Entrée invalide, veuillez entrer un entier.");
                        input.next();
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                }
                int[][] tab3D = new int[n][n * n];
                for (int i = 0; i < n; i++) {
                    Arrays.fill(tab[i], 0);
                }
                Morpion3D game3D = new Morpion3D(n, tab3D);
                game3D.start();
                break;
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