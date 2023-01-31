package Morpions;

import org.apache.maven.surefire.shade.org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;

/**
 * Classe Morpion2D
 * extends Morpion
 */
public class Morpion2D extends Morpion {
    private int taille;
    private String[] tab;

    /**
     * Constructeur avec spécification de la taille et préinitialisation de la grille
     * @param n int : la dimension de la grille nxn
     * @param tab int[] : la grille à laquelle on veut initialiser la grille
     */
    public Morpion2D(int n, String[] tab) {
        super();
        this.taille = n;
        if (tab.length != n * n) {
            throw new IllegalArgumentException("La grille doit être de dimension n * n.");
        }
        this.tab = tab;
    }

    /**
     * Constructeur par défaut, la dimension de la grille est de 3x3
     */
    public Morpion2D(){
        super();
        this.taille = 3;
        this.tab = new String[3*3];

    }

    /**
     * Constructeur où seul la dimension de la grille est spécifiée
     * @param n int : la dimension de la grille nxn
     */
    public Morpion2D(int n){

        super();
        this.taille = n;
        this.tab = new String[this.taille * this.taille];
    }

    /**
     * Réinitialise la grille
     */
    public void init(){
        Arrays.fill(tab, "0");
    }

    /**
     * @return taille la taille de de la grille
     */
    public int getTaille() {
        return taille;
    }


    /**
     * @param taille int la nouvelle taille de la grille
     */
    public void setTaille(int taille) {
        this.taille = taille;
    }


    /**
     * @return tab String[] l'état de la grille
     */
    public String[] getTab() {
        return tab;
    }

    /**
     * @param tab String[] l'état de la nouvelle grille
     */
    public void setTab(String[] tab) {
        this.tab = tab;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.taille * this.taille; i++) {
            sb.append(" ");
            if (tab[i].equals("X"))
                sb.append("X");
            else if (tab[i].equals("O"))
                sb.append("O");
            else
                sb.append(String.valueOf(i+1));
            sb.append(" ");
            sb.append(" ");
            if ((i + 1) % this.taille == 0) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    /**
     * Fonction qui affiche sur la console le morpion
     */
    public void afficher(){
        System.out.println("-------------------------------");
        System.out.println("--- \033[0;33mAffichage du Morpion2D\033[0m ---");
        System.out.println("-------------------------------");

        for (int i = 0; i < tab.length; i++) {
            if (tab[i].equals("X")) {
                System.out.print("\u001B[31m X \u001B[0m ");
            } else if (tab[i].equals("O")) {
                System.out.print("\u001B[34m O \u001B[0m ");
            } else {
                System.out.print(" " + String.valueOf(i+1) + " " + " ");
            }
            if ((i + 1) % taille == 0) {
                System.out.println();
            }
        }
        System.out.println();

    }

    /**
     * Fonction qui affiche sur la console le morpion avec le pion sur le point d'être placé
     * @param index int : l'indice ou le pion doit être placé
     */
    public void afficherEmplacement(int index) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.taille * this.taille; i++) {
            if (i == index - 1) {
                sb.append("[");
                sb.append(String.valueOf(i+1));
                sb.append("]");
            } else {
                sb.append(" ").append(String.valueOf(i+1)).append(" ");
            }
            sb.append(" ");
            if ((i + 1) % this.taille == 0) {
                sb.append("\n");
            }
        }
        System.out.println(sb.toString());
    }

    /**
     * Fonction qui place à l'indice donnée le pion
     * @param index int     l'indice où placer le pion
     * @param value String   le charactère correspondant au pion à placer
     */
    public void placer(int index, String value) throws IllegalArgumentException {
        if (index - 1 < 0 || index - 1>= this.taille * this.taille) {
            throw new IllegalArgumentException("Indice de placement invalide.");
        }
        if (this.tab[index - 1].equals("X") || this.tab[index - 1].equals("O")) {
            throw new IllegalArgumentException("Placement impossible. Un pion est dékà présent sur cette case.");
        }
        this.tab[index - 1] = value;
    }

    /**
     * Fonction qui vérifie si le placement est possible, autrement retourne l'excpetion correspondant
     * @param index int : la position
     * @return boolean
     */
    public boolean validIndex(int index) throws IllegalArgumentException{
        if (index - 1 < 0 || index - 1>= this.taille * this.taille) {
            throw new IllegalArgumentException("Indice de placement invalide.");
        }
        if (this.tab[index - 1].equals("X") || this.tab[index - 1].equals("O")) {
            throw new IllegalArgumentException("Placement impossible. Un pion est déjà présent sur cette case.");
        }

        return true;
    }

    /**
     * Fonction qui vérifie si une combinaison est gagnante et marque la fin d'une partie
     * @return boolean, si une combinaison est gagnante
     */
    public boolean checkWin() {
        // Check rows
        for (int i = 0; i < this.taille * this.taille; i += this.taille) {
            int j;
            for (j = 1; j < this.taille; j++) {
                if (!this.tab[i].equals(this.tab[i + j])) {
                    break;
                }
            }
            if (j == this.taille && !this.tab[i].equals("0")) {
                return true;
            }
        }

        // Check columns
        for (int i = 0; i < this.taille; i++) {
            int j;
            for (j = 1; j < this.taille; j++) {
                if (!this.tab[i].equals(this.tab[i + j * this.taille])) {
                    break;
                }
            }
            if (j == this.taille && !this.tab[i].equals("0")) {
                return true;
            }
        }

        // Check diagonal (top-left to bottom-right)
        int j;
        for (j = 1; j < this.taille; j++) {
            if (this.tab[0] != this.tab[j * this.taille + j]) {
                break;
            }
        }
        if (j == this.taille && !this.tab[0].equals("0")) {
            return true;
        }

        // Check diagonal (top-right to bottom-left)
        for (j = 1; j < this.taille; j++) {
            if (!this.tab[this.taille - 1].equals(this.tab[j * this.taille + this.taille - j - 1])) {
                break;
            }
        }
        if (j == this.taille && !this.tab[this.taille - 1].equals("0"))
        {
            return true;
        }
        return false;
    }

    /**
     * Fonction qui vérifie si la grille est pleine
     * @return boolean
     */
    public boolean isFull(){
        for (int i = 0; i < this.taille * this.taille; i++) {
            if (this.tab[i].equals("0")){
                return false;
            }
        }
        return true;
    }

    /**
     * Fonction qui retourne les indices des éléments de la combinaison gagnante.
     * @return int[] les indices des éléments composant la combinaison gagnante.
     */
    public int[] alignement() {
        // Check rows
        for (int i = 0; i < this.taille * this.taille; i += this.taille) {
            int j;
            for (j = 1; j < this.taille; j++) {
                if (!this.tab[i].equals(this.tab[i + j])) {
                    break;
                }
            }
            if (j == this.taille && !this.tab[i].equals("0")) {
                int[] result = new int[this.taille];
                for (int k = 0; k < this.taille; k++) {
                    result[k] = i + k;
                }
                return result;
            }
        }

        // Check columns
        for (int i = 0; i < this.taille; i++) {
            int j;
            for (j = 1; j < this.taille; j++) {
                if (!this.tab[i].equals(this.tab[i + j * this.taille])) {
                    break;
                }
            }
            if (j == this.taille && !this.tab[i].equals("0")) {
                int[] result = new int[this.taille];
                for (int k = 0; k < this.taille; k++) {
                    result[k] = i + k * this.taille;
                }
                return result;
            }
        }

        // Check diagonal (top-left to bottom-right)
        int j;
        for (j = 1; j < this.taille; j++) {
            if (!this.tab[0].equals(this.tab[j * this.taille + j])) {
                break;
            }
        }
        if (j == this.taille && !this.tab[0].equals("0")) {
            int[] result = new int[this.taille];
            for (int k = 0; k < this.taille; k++) {
                result[k] = k * this.taille + k;
            }
            return result;
        }

        // Check diagonal (top-right to bottom-left)
        for (j = 1; j < this.taille; j++) {
            if (!this.tab[this.taille - 1].equals(this.tab[j * this.taille + this.taille - j - 1])) {
                break;
            }
        }
        if (j == this.taille && !this.tab[this.taille - 1].equals("0")) {
            int[] result = new int[this.taille];
            for (int k = 0; k < this.taille; k++) {
                result[k] = k * this.taille + this.taille - k - 1;
            }
            return result;
        }

        return new int[0];
    }



    /**
     * Fonction d'affichage permettant de mettre en valeur les position en paramètre, utile pour la combinaison gagnante
     * @param indexes : les indices à mettre en valeur
     */
    public void afficherCombinaison(int[] indexes){
        System.out.println("---------------------------------------");
        System.out.println("- \033[0;33mAffichage de la combinaison gagnante\033[0m -");
        System.out.println("---------------------------------------");

        for (int i = 0; i < tab.length; i++) {
            if (tab[i].equals("X")) {
                if (ArrayUtils.contains(indexes, i))
                    System.out.print("\u001b[32m X \u001B[0m ");
                else
                    System.out.print("\u001B[31m X \u001B[0m ");
            } else if (tab[i].equals("O")) {
                if (ArrayUtils.contains(indexes, i))
                    System.out.print("\u001b[32m O \u001B[0m ");
                else
                    System.out.print("\u001B[34m O \u001B[0m ");
            } else {
                System.out.print(" " + tab[i] + " " + " ");
            }
            if ((i + 1) % taille == 0) {
                System.out.println();
            }
        }
        System.out.println();
    }
}
