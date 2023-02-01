package Morpions;

import org.apache.maven.surefire.shade.org.apache.commons.lang3.ArrayUtils;

import java.nio.channels.IllegalChannelGroupException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Classe Morpion3D
 * extends Morpion
 */
public class Morpion3D extends Morpion{

    private int taille;
    private String[][] tab;

    /**
     * Constructeur avec spécification de la taille et préinitialisation de la grille
     * @param n int : taille de la grille
     * @param tab : String[][] : Morpion initialisé
     */
    public Morpion3D(int n, String[][] tab) {
        this.taille = n;
        this.tab = tab;
    }

    /**
     * Constructeur par défaut, la dimension de la grille est de 3x3x3
     */
    public Morpion3D(){
        super();
        this.taille = 3;
        this.tab = new String[3][3*3];

    }

    /**
     * Constructeur où seul la dimension de la grille est spécifiée
     * @param n int : la dimension de la grille nxnxn
     */
    public Morpion3D(int n){
        super();
        this.taille = n;
        this.tab = new String[this.taille][this.taille* this.taille];
    }

    /**
     * Réinitialise la grille
     */
    public void init(){
        for (int j = 0; j < this.taille; j++) {
            for (int i = 0; i < tab.length; i++) {
                Arrays.fill(tab[i], "0");
            }
        }
    }

    //Getters & setters
    /**
     * @return taille la taille de la grille
     */
    public int getTaille() {
        return taille;
    }

    /**
     * @param taille int la dimension de la grille
     */
    public void setTaille(int taille) {
        this.taille = taille;
    }

    /**
     * @return tab String[][] l'état de la grille
     */
    public String[][] getTab() {
        return tab;
    }

    /**
     * @param tab String[][] l'état de la nouvelle grille
     */
    public void setTab(String[][] tab) {
        this.tab = tab;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("En développement");
        return sb.toString();
    }

    /**
     * Fonction qui affiche sur la console le morpion, affiche trois grille nxn correspondant aux différentes couches du Morpion
     */
    public void afficher()
    {
        System.out.println("--------------------------------");
        System.out.println("--- \033[0;33m Affichage du Morpion3D \033[0m ---");
        System.out.println("--------------------------------");
        System.out.println();

        for (int i = 0; i < this.taille; i++) {
            System.out.println("-- " + "\033[0;33m"+"Étage " + (i+1) + "\033[0m" + " --");

            for (int j = 0; j < this.tab[i].length; j++) {
                if (tab[i][j].equals("X")) {
                    System.out.print("\u001B[31m X \u001B[0m ");
                } else if (tab[i][j].equals("O")) {
                    System.out.print("\u001B[34m O \u001B[0m ");
                } else {
                    System.out.print(" " + String.valueOf(j+1) + " " + " ");
                }
                if ((j + 1) % taille == 0) {
                    System.out.println();
                }
            }
            System.out.println();
        }
    }

    public void afficherEmplacement(int pos){
        try{
            String code = String.valueOf(pos);
            String positionDecode = code.substring(0, code.length() - String.valueOf(this.taille).length());
            String etageDecode = code.substring(code.length() - String.valueOf(this.taille).length(), code.length());

            int position = Integer.parseInt(positionDecode);
            int etage = Integer.parseInt(etageDecode);

            if (etage < 1 || etage > this.taille) throw new IllegalArgumentException("Étage incorrecte");
            if (position < 1 || position > this.taille * this.taille) throw new IllegalArgumentException("Position incorrecte");
            if (this.tab[etage - 1][position - 1].equals("X") || this.tab[etage - 1][position - 1].equals("O")) {
                throw new IllegalArgumentException("Placement impossible. Un pion est déjà présent sur cette case.");
            }

            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < this.taille; i++) {
                sb.append("\n");
                sb.append("-- " + "\033[0;33m" + "Étage ").append(i + 1).append("\033[0m").append(" --");
                sb.append("\n");
                for (int j = 0; j < this.tab[i].length; j++) {
                    if (i == etage - 1 && j == position - 1) {
                        sb.append("[");
                        sb.append(String.valueOf(i+1));
                        sb.append("]");
                    }
                    else if (tab[i][j].equals("X")) {
                        sb.append("\u001B[31m X \u001B[0m ");
                    } else if (tab[i][j].equals("O")) {
                        sb.append("\u001B[34m O \u001B[0m ");
                    } else {
                        sb.append(" " + String.valueOf(j+1) + " ");
                    }
                    if ((j + 1) % taille == 0) {
                        sb.append("\n");
                    }
                }
                sb.append("\n");
            }
            System.out.println(sb.toString());

        } catch (IllegalArgumentException e)
        {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e)
        {
            throw new IllegalArgumentException("Code position incorrecte");
        }
    }

    /**
     * Fonction qui vérifie si le placement est possible, autrement retourne l'excpetion correspondant
     * @param pos int : le code de la position
     * @return boolean
     */
    public boolean validIndex(int pos) throws IllegalArgumentException{
        try{
            String code = String.valueOf(pos);
            System.out.println("yes");
            String positionDecode = code.substring(0, code.length() - String.valueOf(this.taille).length());
            String etageDecode = code.substring(code.length() - String.valueOf(this.taille).length(), code.length());

            int position = Integer.parseInt(positionDecode);
            int etage = Integer.parseInt(etageDecode);

            if (etage < 1 || etage > this.taille) throw new IllegalArgumentException("Étage incorrecte");
            if (position < 1 || position > this.taille * this.taille) throw new IllegalArgumentException("Position incorrecte");
            if (this.tab[etage - 1][position - 1].equals("X") || this.tab[etage - 1][position - 1].equals("O")) {
                throw new IllegalArgumentException("Placement impossible. Un pion est déjà présent sur cette case.");
            }

        } catch (IllegalArgumentException e)
        {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e)
        {
            throw new IllegalArgumentException("Code position incorrecte");
        }

        return true;
    }

    /**
     * Fonction qui permet de placer un pion sur la grille, fait appelle à une fonction spécifique pour la version 3D
     * @param pos int
     * @param value String
     */
    @Override
    public void placer(int pos, String value) throws IllegalArgumentException{
        try{
            String code = String.valueOf(pos);
            String positionDecode = code.substring(0, code.length() - String.valueOf(3).length());
            String etageDecode = code.substring(code.length() - String.valueOf(3).length(), code.length());

            int position = Integer.parseInt(positionDecode);
            int etage = Integer.parseInt(etageDecode);

            placer3D(etage, position, value);
        } catch (StringIndexOutOfBoundsException e)
        {
            throw new IllegalArgumentException("Placement impossible");
        }

    }

    /**
     * Fonction qui place à l'indice donnée le pion sur la version morpion 3D
     * @param etage int         l'étage de placement
     * @param position int      l'indice où placer le pion
     * @param value String      le charactère correspondant au pion à placer
     */
    public void placer3D(int etage, int position, String value) throws IllegalArgumentException
    {
        if (etage < 1 || etage > this.taille) throw new IllegalArgumentException("Étage incorrecte");
        if (position < 1 || position > this.taille * this.taille) throw new IllegalArgumentException("Position incorrecte");
        this.tab[etage - 1][position-1] = value;
    }

    /**
     * Fonction qui vérifie si une combinaison est gagnante et marque la fin d'une partie
     * @return boolean, si une combinaison est gagnante
     */
    @Override
    public boolean checkWin() {
        int n = this.taille;
        String[][] grid = this.tab;

        int m = n * n;

        // Check rows
        for (int i = 0; i < m; i++) {
            for (int k = 0; k < n; k++) {
                int j;
                for (j = 1; j < n; j++) {
                    if (!grid[i][k].equals(grid[i][k + j])) {
                        break;
                    }
                }
                if (j == n && !grid[i][k].equals("0")) {
                    return true;
                }
            }
        }

        // Check columns
        for (int i = 0; i < n; i++) {
            for (int k = 0; k < n; k++) {
                int j;
                for (j = 1; j < n; j++) {
                    if (!grid[i][k].equals(grid[i + j][k])) {
                        break;
                    }
                }
                if (j == n && !grid[i][k].equals("0")) {
                    return true;
                }
            }
        }

        // Check depth (front to back)
        for (int k = 0; k < n; k++) {
            int j;
            for (j = 1; j < n; j++) {
                if (!grid[0][k].equals(grid[j][k])) {
                    break;
                }
            }
            if (j == n && !grid[0][k].equals("0")) {
                return true;
            }
        }

        // Check depth (back to front)
        for (int k = 0; k < n; k++) {
            int j;
            for (j = 1; j < n; j++) {
                if (!grid[n - 1][k].equals(grid[n - 1 - j][k])) {
                    break;
                }
            }
            if (j == n && !grid[n - 1][k].equals("0")) {
                return true;
            }
        }

        // Check diagonals in xy-plane (top-left to bottom-right)
        for (int k = 0; k < n; k++) {
            int j;
            for (j = 1; j < n; j++) {
                if (!grid[0][k].equals(grid[j][j + k])) {
                    break;
                }
            }
            if (j == n && !grid[0][k].equals("0")) {
                return true;
            }
        }

        // Check diagonals in xy-plane (top-right to bottom-left)
        for (int k = 0; k < n; k++) {
            int j;
            for (j = 1; j < n; j++) {
                if (!grid[n - 1][k].equals(grid[n - 1 - j][n - 1 - j + k])) {
                    break;
                }
            }
            if (j == n && !grid[n - 1][k].equals("0")) {
                return true;
            }
        }


        // Check diagonals in yz-plane (left-front to right-back)
        for (int i = 0; i < n; i++) {
            int j;
            for (j = 1; j < n; j++) {
                if (!grid[i][0].equals(grid[i + j][j])) {
                    break;
                }
            }
            if (j == n && !grid[i][0].equals("")) {
                return true;
            }
        }

        // Check diagonals in yz-plane (right-front to left-back)
        for (int i = 0; i < n; i++) {
            int j;
            for (j = 1; j < n; j++) {
                if (!grid[i][n - 1].equals(grid[i + j][n - 1 - j])) {
                    break;
                }
            }
            if (j == n && !grid[i][n - 1].equals("")) {
                return true;
            }
        }

        // Check diagonals in xz-plane (front-left to back-right)
        for (int i = 0; i < n; i++) {
            int j;
            for (j = 1; j < n; j++) {
                if (!grid[0][i].equals(grid[j][i + j])) {
                    break;
                }
            }
            if (j == n && !grid[0][i].equals("")) {
                return true;
            }
        }

        // Check diagonals in xz-plane (back-left to front-right)
        for (int i = 0; i < n; i++) {
            int j;
            for (j = 1; j < n; j++) {
                if (!grid[n - 1][i].equals(grid[n - 1 - j][i + j])) {
                    break;
                }
            }
            if (j == n && !grid[n - 1][i].equals("")) {
                return true;
            }
        }
        return false;
    }


    /**
     * Vérifie si la grille est totalement remplie
     * @return
     */
    @Override
    public boolean isFull() {
        for (int i = 0; i < this.taille; i++) {
            for (int j = 0; j < this.tab[i].length; j++) {
                if (this.tab[i][j].equals("0")) return false;
            }
        }
        return true;
    }

    @Override
    public int[] alignement() {
        return new int[0];
    }

    /**
     * Fonction d'affichage permettant de mettre en valeur les position en paramètre, utile pour la combinaison gagnante
     * @param indexes : les indices à mettre en valeur
     */
    @Override
    public void afficherCombinaison(int[] indexes){
        System.out.println("---------------------------------------");
        System.out.println("- \033[0;33mAffichage de la combinaison gagnante\033[0m -");
        System.out.println("---------------------------------------");

        for (int i = 0; i < this.taille; i++) {
            System.out.println("-- " + "\033[0;33m"+"Étage " + (i+1) + "\033[0m" + " --");

            for (int j = 0; j < this.tab[i].length; j++) {
                if (tab[i][j].equals("X")) {
                    if (ArrayUtils.contains(indexes, i))
                        System.out.print("\u001b[32m X \u001B[0m ");
                    else
                        System.out.print("\u001B[31m X \u001B[0m ");
                } else if (tab[i][j].equals("O")) {
                    if (ArrayUtils.contains(indexes, i))
                        System.out.print("\u001b[32m O \u001B[0m ");
                    else
                        System.out.print("\u001B[34m O \u001B[0m ");
                } else {
                    System.out.print(" " + String.valueOf(j+1) + " " + " ");
                }
                if ((j + 1) % taille == 0) {
                    System.out.println();
                }
            }
            System.out.println();
        }
    }


//    //récupère les indices gagnants testés et trouvés par la fonction alignement
//    // et remplace ces à l'indice de ces derniers dans le tableau par
//    // des -1 si joueur 1, des -2 si joueur 2
//    // la longeur du tbleau indice= celle de etage
//    public void afficherFin(int[] indices, int[] etage) throws Exception {
//        if (indices.length!=etage.length){throw new Exception("Nombre d'indices et d'étages incompatibles");}
//        if (indices.length == 0 || etage.length==0) {
//            throw new Exception("Aucun indice donné");
//        }
//        else {
//            for (int i = 0; i < indices.length; i++) {
//                if (indices[i] >= 0 && indices[i] < this.tab[0].length) {
//                    if (this.tab[etage[i]][indices[i]] == "1") {
//                        this.tab[etage[i]][indices[i]] = "-1";
//                    } else if (this.tab[etage[i]][indices[i]] == 2) {
//                        this.tab[etage[i]][indices[i]] = "-2";
//                    }
//                } else {
//                    throw new Exception("Indice non valide : " + indices[i]);
//                }
//            }
//        }
//        afficher();
//    }
//
//
//    //prend en para le choix du dernier élément placer (l'indice) et retourne les indices des éléments qui sont alignés
//    //on va tester si pour cet élément placé, si le joueur a réussi à aligner taille éléments
//    //pour un morpion 3x3 il faut en aligner 3
//    //return les étages array [0] PUIS les indices array[1]
//    public Object[] alignement(int etage, int choix) throws Exception {
//        int[][][] grille = grille();
//        System.out.println("_____");
//        int n = this.taille;
//        int[] indices = new int[n];
//        int[] etages = new int[n];
//        // vérifie les indices du choix
//        if ((choix < 1) || (choix > n * n) || ((etage < 0) || (etage >= n))) {
//            throw new Exception("Le choix de l'étage ou de la case taille'a pas la bonne valeur");
//        }
//        else
//        {
//            choix=choix-1; //pour taille=3 le choix va de 1 à 9 mais dans un tableau les indices vont de 0 à 8
//            //Pour tester les diagos des bords
//
//            //4diagos centrales
//            //8diagos sur les bords
//
//            //cas diagoaa1
//            // x 2 3 | 1 2 3 | 1 2 3
//            // 4 5 6 | x 5 6 | 4 5 6
//            // 7 8 9 | 7 8 9 | x 8 9
//            //cas diagoaa1bis
//            // 1 2 x | 1 2 3 | 1 2 3
//            // 4 5 6 | 4 5 x | 4 5 6
//            // 7 8 9 | 7 8 9 | 7 8 x
//            //cas diagocv1
//            // 1 x 3 | 1 2 3 | 1 2 3
//            // 4 5 6 | 4 x 6 | 4 5 6
//            // 7 8 9 | 7 8 9 | 7 x 9
//            int numetage = etage;
//            int numli = choix/n;
//            int numcol = choix-(n*numli);
//            while (numetage!=0) //si etage!=0, on taille'est pas en bas du cube
//            {   //on se recentre
//                numli = numli - 1;
//                numetage=numetage-1;
//            }
//            if (numli ==0)//si ce taille'est pas le cas, il est impossible de faire une diagoaa1
//            {
//                boolean test = true;
//                for (int k = 0; k < n - 1; k++)//on parcourt la colonne de choix
//                {
//                    if (grille[k][numli+k][numcol] != grille[k][numli+k+1][numcol]) {
//                        test = false;//s'ils sont différents ils ne sont pas alignés
//                    }
//                    else {
//                        indices[k] = n * (numli+k) +numcol;
//                        indices[k + 1] = n *(numli+k+1) +numcol;
//                        etages[k]=k;
//                        etages[k+1]=k+1;
//                    }
//                }
//                if (test == false) {
//                    int[] vide = new int[n];
//                    indices = vide;
//                    etages=vide;
//                } else {
//                    return new int[][]{etages, indices};
//                }
//            }
//
//            //cas diagoaa2
//            // 1 2 3 | 1 2 3 | x 2 3
//            // 4 5 6 | x 5 6 | 4 5 6
//            // x 8 9 | 7 8 9 | 7 8 9
//            //cas diagoaa2bis
//            // 1 2 3 | 1 2 3 | 1 2 x
//            // 4 5 6 | 4 5 x | 4 5 6
//            // 7 8 x | 7 8 9 | 7 8 9
//            //cas diagocv2
//            // 1 2 3 | 1 2 3 | 1 x 3
//            // 4 5 6 | 4 x 6 | 4 5 6
//            // 7 x 9 | 7 8 9 | 7 8 9
//            numetage = etage;
//            numli = choix/n;
//            numcol = choix-(n*numli);
//            while (numetage!=0) //si etage!=0, on taille'est pas en bas du cube
//            {   //on se recentre
//                numli = numli + 1;
//                numetage=numetage-1;
//            }
//            if (numli ==n-1)//si ce taille'est pas le cas, il est impossible de faire une diagoaa1
//            {
//                boolean test = true;
//                for (int k = 0; k < n - 1; k++)//on parcourt la colonne de choix
//                {
//                    if (grille[k][numli-k][numcol] != grille[k+1][numli-(k+1)][numcol] || (grille[k][numli-k][numcol] == 0) || (grille[k+1][numli-(k+1)][numcol] == 0)) {
//                        test = false;//s'ils sont différents ils ne sont pas alignés
//                    }
//                    else {
//                        indices[k] = n * (numli-k) +numcol;
//                        indices[k + 1] = n *(numli-(k+1)) +numcol;
//                        etages[k]=k;
//                        etages[k+1]=k+1;
//                    }
//                }
//                if (test == false) {
//                    int[] vide = new int[n];
//                    indices = vide;
//                    etages=vide;
//                } else {
//                    return new int[][]{etages, indices};
//                }
//            }
//
//            //cas diagobb1
//            // 1 2 3 | 1 2 3 | 1 2 3
//            // 4 5 6 | 4 5 6 | 4 5 6
//            // x 8 9 | 7 x 9 | 7 8 x
//            //cas diagobb1bis
//            // x 2 3 | 1 x 3 | 1 2 x
//            // 4 5 6 | 4 5 6 | 4 5 6
//            // 7 8 9 | 7 8 9 | 7 8 9
//            //cas diagoch1
//            // 1 2 3 | 1 2 3 | 1 2 3
//            // x 5 6 | 4 x 6 | 4 5 x
//            // 7 8 9 | 7 8 9 | 7 8 9
//            numetage = etage;
//            numli = choix/n;
//            numcol = choix-(n*numli);
//            while (numetage!=0) //si etage!=0, on taille'est pas en bas du cube
//            {   //on se recentre
//                numcol = numcol + 1;
//                numetage=numetage-1;
//            }
//            if (numcol==0)//si ce taille'est pas le cas, il est impossible de faire une diagobb1
//            {
//                boolean test = true;
//                for (int k = 0; k < n - 1; k++)//on parcourt la colonne de choix
//                {
//                    if ((grille[k][numli][numcol + k] != grille[k+1][numli][numcol + (k + 1)]) || (grille[k][numli][numcol + k] == 0) || (grille[k+1][numli][numcol + (k + 1)] == 0)) {
//                        test = false;//s'ils sont différents ils ne sont pas alignés
//                    }
//                    else {
//                        indices[k] = n * numli +numcol + k;
//                        indices[k + 1] = n * numli +numcol + (k + 1);
//                        etages[k]=k;
//                        etages[k+1]=k+1;
//                    }
//                }
//                if (test == false) {
//                    int[] vide = new int[n];
//                    indices = vide;
//                    etages=vide;
//                } else {
//                    return new int[][]{etages, indices};
//                }
//            }
//
//            //cas diagobb2
//            // 1 2 3 | 1 2 3 | 1 2 3
//            // 4 5 6 | 4 5 6 | 4 5 6
//            // 7 8 x | 7 x 9 | x 8 9
//            //cas diagobb2bis
//            // 1 2 x | 1 x 3 | x 2 3
//            // 4 5 6 | 4 5 6 | 4 5 6
//            // 7 8 9 | 7 8 9 | 7 8 9
//            //cas diagoch2
//            // 1 2 3 | 1 2 3 | 1 2 3
//            // 4 5 x | 4 x 6 | x 5 6
//            // 7 8 9 | 7 8 9 | 7 8 9
//            numetage = etage;
//            numli = choix/n;
//            numcol = choix-(n*numli);
//            while (numetage!=0) //si etage!=0, on taille'est pas en bas du cube
//            {   //on se recentre
//                numcol = numcol + 1;
//                numetage=numetage-1;
//            }
//            if (numcol==n-1)//si ce taille'est pas le cas, il est impossible de faire une diagobb2
//            {
//                boolean test = true;
//                for (int k = 0; k < n - 1; k++)//on parcourt la colonne de choix
//                {
//                    if ((grille[k][numli][numcol - k] != grille[k + 1][numli][numcol - (k + 1)]) || (grille[k][numli][numcol - k] == 0) ||(grille[k + 1][numli][numcol - (k + 1)] == 0)) {
//                        test = false;//s'ils sont différents ils ne sont pas alignés
//                    }
//                    else {
//                        indices[k] = n * numli +numcol - k;
//                        indices[k + 1] = n * numli +numcol - (k + 1);
//                        etages[k]=k;
//                        etages[k+1]=k+1;
//                    }
//                }
//                if (test == false) {
//                    int[] vide = new int[n];
//                    indices = vide;
//                    etages=vide;
//                } else {
//                    return new int[][]{etages, indices};
//                }
//            }
//
//            //pour tester sur des ETAGES DIFFERENTS (et pas sur les faces)
//            //4 diagos étages
//
//            //cas diagoG2
//            // x 2 3 | 1 2 3 | 1 2 3
//            // 4 5 6 | 4 x 6 | 4 5 6
//            // 7 8 9 | 7 8 9 | 7 8 x
//            numetage = etage;
//            numli = choix/n;
//            numcol = choix-(n*numli);
//            while (numetage!=0) //si etage!=0, on taille'est pas en bas du cube
//            {   //on se recentre
//                numli = numli - 1;
//                numcol = numcol - 1;
//                numetage=numetage-1;
//            }
//            if ((numli ==0)&&(numcol==0))//si ce taille'est pas le cas, il est impossible de faire une diagoG1
//            {
//                boolean test = true;
//                for (int k = 0; k < n - 1; k++)//on parcourt la colonne de choix
//                {
//                    if (grille[k][numli + k][numcol + k] != grille[k + 1][numli + (k + 1)][numcol + (k + 1)] ||(grille[k][numli + k][numcol + k] == 0 || (grille[k + 1][numli + (k + 1)][numcol + (k + 1)] == 0) ) ) {
//                        test = false;
//                    } //s'ils sont différents ils ne sont pas alignés
//                    else {
//                        indices[k] = n * (numli+k) +numcol + k;
//                        indices[k + 1] = n * (numli+k+1) +numcol + (k + 1);
//                        etages[k]=k;
//                        etages[k+1]=k+1;
//                    }
//                }
//                if (test == false) {
//                    int[] vide = new int[n];
//                    indices = vide;
//                    etages=vide;
//                } else {
//                    return new int[][]{etages, indices};
//                }
//            }
//
//
//            //cas diagoD2
//            // 1 2 x | 1 2 3 | 1 2 3
//            // 4 5 6 | 4 x 6 | 4 5 6
//            // 7 8 9 | 7 8 9 | x 8 9
//            numli = choix/n;
//            numcol = choix-(n*numli);
//            numetage=etage;
//            while (numetage!=0) //si etage taille'est pas égal à 0 cela veut dire que l'on taille'est pas tout en bas du cube
//            {                //donc on se recentre
//                numli=numli-1;
//                numcol=numcol+1;
//                numetage=numetage-1;
//            }
//            if ((numcol == n -1)&&(numli== 0))//si ce taille'est pas le cas, il est impossible de faire une diagoD2
//            {
//                boolean test = true;
//                for (int k = 0; k < n - 1; k++)//on parcourt la colonne de choix
//                {
//                    if (grille[k][numli + k][numcol - k] != grille[k+1][numli + (k + 1)][numcol -(k + 1)] || (grille[k][numli + k][numcol - k] == 0) || (grille[k+1][numli + (k + 1)][numcol -(k + 1)] == 0) ) {
//                        test = false;
//                    } //s'ils sont différents ils ne sont pas alignés
//                    else {
//                        indices[k] = n * (numli+k) +numcol - k;
//                        indices[k + 1] = n * (numli+k+1) +numcol - (k + 1);
//                        etages[k]=k;
//                        etages[k+1]=k+1;
//                    }
//                }
//                if (test == false) {
//                    int[] vide = new int[n];
//                    indices = vide;
//                    etages=vide;
//                } else {
//                    return new int[][]{etages, indices};
//                }
//            }
//
//            //cas diagoD1
//            // 1 2 3 | 1 2 3 | 1 2 x
//            // 4 5 6 | 4 x 6 | 4 5 6
//            // x 8 9 | 7 8 9 | 7 8 9
//            numli = choix/n;
//            numcol = choix-(n*numli);
//            numetage=etage;
//            while (numetage!=0) //si etage taille'est pas égal à 0 cela veut dire que l'on taille'est pas tout en bas du cube
//            {                //donc on se recentre
//                numli=numli+1;
//                numcol=numcol-1;
//                numetage=numetage-1;
//            }
//            if ((numcol == 0)&&(numli==n-1))//si ce taille'est pas le cas, il est impossible de faire une diagoD1
//            {
//                boolean test = true;
//                for (int k = 0; k < n - 1; k++)//on parcourt la colonne de choix
//                {
//                    if ((grille[k][numli - k][numcol + k] != grille[k + 1][numli - (k + 1)][numcol + k + 1] )|| (grille[k][numli - k][numcol + k] == 0)|| (grille[k + 1][numli - (k + 1)][numcol + k + 1] == 0) ) {
//                        test = false;
//                    } //s'ils sont différents ils ne sont pas alignés
//                    else {
//                        indices[k] = n * (numli-k) +numcol + k;
//                        indices[k + 1] = n * (numli-k+1) +numcol + (k + 1);
//                        etages[k]=k;
//                        etages[k+1]=k+1;
//                    }
//                }
//                if (test == false) {
//                    int[] vide = new int[n];
//                    indices = vide;
//                    etages=vide;
//                } else {
//                    return new int[][]{etages, indices};
//                }
//            }
//
//            //cas diagoG1
//            // 1 2 3 | 1 2 3 | x 2 3
//            // 4 5 6 | 4 x 6 | 4 5 6
//            // 7 8 x | 7 8 9 | 7 8 9
//            numetage = etage;
//            numli = choix/n;
//            numcol = choix-(n*numli);
//            while (numetage!=0) //si etage!=0, on taille'est pas en bas du cube
//            {   //on se recentre
//                numli = numli + 1;
//                numcol = numcol + 1;
//                numetage=numetage-1;
//            }
//            if ((numli == n - 1)&&(numcol== n-1))//si ce taille'est pas le cas, il est impossible de faire une diagoG1
//            {
//                boolean test = true;
//                for (int k = 0; k < n - 1; k++)//on parcourt la colonne de choix
//                {
//                    if ((grille[k][numli - k][numcol - k] != grille[k + 1][numli - (k + 1)][numcol - (k + 1)]) || (grille[k][numli - k][numcol - k] == 0) || (grille[k + 1][numli - (k + 1)][numcol - (k + 1)] ==0) ) {
//                        test = false;
//                    } //s'ils sont différents ils ne sont pas alignés
//                    else {
//                        indices[k] = n * (numli-k) +numcol - k;
//                        indices[k + 1] = n * (numli-(k+1)) +numcol - (k + 1);
//                        etages[k]=k;
//                        etages[k+1]=k+1;
//                    }
//                }
//                if (test == false) {
//                    int[] vide = new int[n];
//                    indices = vide;
//                    etages=vide;
//                } else {
//                    return new int[][]{etages, indices};
//                }
//            }
//
//            //cas alignement étage
//            // 1 2 x | 1 2 x | 1 2 x
//            // 4 5 6 | 4 5 6 | 4 5 6
//            // 7 8 9 | 7 8 9 | 7 8 9
//            boolean test2=true;
//            numli= choix /n;
//            numcol = choix-(n*numli);
//            for (int k = 0; k < n - 1; k++)//on parcours les étages avec le même choix
//            {
//                if (grille [k][numli][numcol] != grille[k+1][numli][numcol] || (grille [k][numli][numcol] == 0) || (grille [k][numli][numcol] == 0) ) {
//                    test2 = false;
//                } //s'ils sont différents ils ne sont pas alignés
//                //par défaut, on remplit les indices
//                else {
//                    indices[k] = choix;
//                    indices[k + 1] = choix;
//                    etages[k]=k;
//                    etages[k+1]=k+1;
//                }
//            }
//            //si c'est faux on vide les indices
//            if (test2 == false) {
//                int[] vide = new int[n];
//                indices = vide;
//                etages= vide;
//            } else { //sinon on les returns
//                return new int[][]{etages, indices};
//            }
//
//            //pour tester sur LE MEME ETAGE
//            //pour tester la ligne
//            numli = choix/n;
//            //on se recentre si besoin
//            while (numli!=0) {
//                numli = numli - 1;
//            }
//            test2 = true;
//            for (int k = 0; k < n - 1; k++)//on parcours la ligne de choix
//            {
//                if (grille [etage][numli][k] != grille[etage][numli][k + 1] ) {
//                    test2 = false;
//                } //s'ils sont différents ils ne sont pas alignés
//                //par défaut, on remplit les indices
//                else {
//                    indices[k] = numli * n + k;
//                    indices[k + 1] = numli * n + k + 1;
//                    etages[k]=etage;
//                    etages[k+1]=etage;
//                }
//            }
//            //si c'est faux on vide les indices
//            if (test2 == false) {
//                int[] vide = new int[n];
//                indices = vide;
//                etages= vide;
//            } else { //sinon on les returns
//                return new int[][]{etages, indices};
//            }
//
//            //pour tester la colonne
//            numcol = choix / n;
//            while (numcol!=0){numcol=numcol-1;}//tant qu'on est pas au début de la colonne, on se recentre
//            test2 = true;
//            for (int k = 0; k < n - 1; k++)//on parcours la colonne de choix
//            {
//                if (grille[etage][k][numcol] != grille[etage][k + 1][numcol] ) {
//                    test2 = false;
//                } //si ils sont différents ils ne sont pas alignés
//                //par défaut on stock les valeurs des indices
//                else {
//                    indices[k] = numcol + k * n;
//                    indices[k + 1] = numcol + (k + 1) * n;
//                    etages[k]=etage;
//                    etages[k+1]=etage;
//                }
//            }
//            //si il s'avère que ils ne sont pas alignés avec ce mode d'alignement on vide les tableaux
//            if (test2 == false) {
//                int[] vide = new int[n];
//                indices = vide;
//                etages=vide;
//            } else {
//                return new int[][]{etages, indices};
//            }
//
//            //pour tester les diagonales :
//            //diagonale droite
//            // _ _ _ X
//            // _ _ X _
//            // _ X _ _
//            // X _ _ _
//            numli = choix/n;
//            numcol = choix-(n*numli);
//            while (numcol!=0){
//                numcol=numcol-1;
//                numli=numli+1;}
//            if (numli == n-1)//si ce taille'est pas le cas, il est impossible de faire une diagonale droite
//            {
//                boolean test = true;
//                for (int k = 0; k < n - 1; k++)//on parcours la colonne de choix
//                {
//                    if (grille[etage][numli - k][numcol + k] != grille[etage][numli - (k + 1)][numcol + k + 1] ) {
//                        test = false;
//                    } //si ils sont différents ils ne sont pas alignés
//                    else {
//                        indices[k] = (numli - k) * n + numcol+ k;
//                        indices[k + 1] = (numli - (k+1)) * n + numcol+ (k+1);
//                        etages[k]=etage;
//                        etages[k+1]=etage;
//                    }
//                }
//                if (test == false) {
//                    int[] vide = new int[n];
//                    indices = vide;
//                    etages=vide;
//                } else {
//                    return new int[][]{etages, indices};
//                }
//            }
//
//            //diagonale gauche
//            // X _ _ _
//            // _ X _ _
//            // _ _ X _
//            // _ _ _ X
//            numli = choix/n;
//            numcol = choix-(n*numli);
//            while (numcol!=n-1){
//                numcol=numcol+1;
//                numli=numli+1;}
//            if (numli == n - 1)//si ce taille'est pas le cas, il est impossible de faire une diagonale gauche
//            {
//                boolean test = true;
//                for (int k = 0; k < n - 1; k++)//on parcourt la colonne de choix
//                {
//                    if (grille[etage][numli - k][numcol - k] != grille[etage][numli - (k + 1)][numcol - (k + 1)]) {
//                        test = false;
//                    } //s'ils sont différents ils ne sont pas alignés
//                    else {
//                        indices[k] = (numli - k) * n +numcol- k;
//                        indices[k + 1] = (numli - (k+1)) * n +numcol- (k+1);
//                        etages[k]=etage;
//                        etages[k+1]=etage;
//                    }
//                }
//                if (test == false) {
//                    int[] vide = new int[n];
//                    indices = vide;
//                    etages=vide;
//                } else {
//                    return new int[][]{etages, indices};
//                }
//            }
//        }
//        return new int[][]{null, null};
//    }

}

