import Morpions.*;

import java.util.Scanner;

public class TicTacToe {

    private int modeJeu;
    private boolean inGame;
    private Morpion morpion;
    private int joueur = 1;

    private Scanner scanner = new Scanner(System.in);

    public int getModeJeu() {
        return modeJeu;
    }

    public void setModeJeu(int modeJeu) {
        this.modeJeu = modeJeu;
    }

    public boolean isInGame() {
        return inGame;
    }

    public void setInGame(boolean inGame) {
        this.inGame = inGame;
    }

    public Morpion getMorpion() {
        return morpion;
    }

    public void setMorpion(Morpion morpion) {
        this.morpion = morpion;
    }

    public void selectionnerModeJeu(int mode, int taille) throws IllegalArgumentException {
        switch (mode){
            case 1:
                this.modeJeu = 1;
                this.morpion = new Morpion2D(taille);
                this.morpion.init();
                break;
            case 2:
                this.modeJeu = 2;
//                this.morpion = new Morpion3D(taille);
//                this.morpion.init();

                break;
            default:
                throw new IllegalArgumentException("Mode de jeu inconnue");
        }
    }

    /**
     * Lancer le jeu
     * Demande au joueur de sélectionner le mode de jeu et la taille de grille souhaités.
     * Mets à jour le mode de jeu et lance la partie
     */
    public void jouer(){

        // Paramètre par défaut
        int mode = 1;
        int size = 3;

        while (true) {
            System.out.println("\u001b[33;1mMode de jeu : " + mode + ", taille de grille : " + size + "\u001b[0m \n");
            System.out.println("1. Sélectionner Mode de jeu");
            System.out.println("2. Sélectionner taille de la grille");
            System.out.println("3. Lancer la partie");
            System.out.print("→ ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Entrer 1 pour morpion2D ou 2 pour morpion3D : ");
                    mode = scanner.nextInt();
                    break;
                case 2:
                    System.out.print("Enter grid size: ");
                    size = scanner.nextInt();
                    break;
                case 3:
                    try{
                        this.selectionnerModeJeu(mode, size);
                    } catch (Exception e){
                        System.out.println("\u001b[33;1m" + e.getMessage() + "\u001b[0m");
                        continue;
                    }
                    startGame();
                    return;
                default:
                    System.out.println("\u001b[33;1mChoix incorrecte. Veuillez réessayer.\u001b[0m");
                    mode = 1;
            }
        }
    }

    private void startGame() {
        // Le jeu continue tant que personne n'a gagné

        //
        while (!this.morpion.checkWin() || !this.morpion.isFull()){
            this.morpion.afficher();
            System.out.println("Joueur " + this.joueur + ", veuillez sélectionner une position pour placer votre pion : ");
            int pos = this.scanner.nextInt();

            // verify if the pawn can be placed
            try {
                this.morpion.valideIndex();
            } catch (Exception e){
                System.out.println("\u001b[33;1m" + e.getMessage() + "\u001b[0m");
                continue;
            }

            // Ask the player to confirm position
            this.morpion.afficherEmplacement(pos);
            System.out.println("Joueur " + this.joueur + ", veuillez confirmer la position actuelle entrant à nouveau le nombre.\nAutrement la position vous sera redemandée : ");
            int confirmation = this.scanner.nextInt();

            if (confirmation != pos){
                continue;
            }

            try {
                switch (this.joueur){
                    case 1 :
                        this.morpion.placer(pos, "X");
                        break;
                    case 2:
                        this.morpion.placer(pos, "O");
                        break;
                    default:
                        System.out.println("\u001b[33;1m" + "Erreur : Joueur inconnue" + "\u001b[0m");
                }
            } catch (Exception e){
                System.out.println("\u001b[33;1m" + e.getMessage() + "\u001b[0m");
                continue;
            }

            // changement de tour
            if (this.joueur == 1) {
                this.joueur = 2;
            } else {
                this.joueur = 1;
            }
        }

        // Récupérer la combinaison gagnante
        if (!this.morpion.checkWin()) {
            this.morpion.afficher();
            System.out.println("Match Nul !");
        }
        // Afficher la combinaison gagnante

        else {
            this.morpion.afficher();

            // Switch pour avoir le gagnant
            if (this.joueur == 1) {
                this.joueur = 2;
            } else {
                this.joueur = 1;
            }

            System.out.println("\u001b[32m" + "Victoire du joueur " + this.joueur + "\u001b[0m");
        }
        // Proposer au joueur de rejouer
    }
}