import java.util.Arrays;

public class Morpion2D {
    private int n; // largeur de la grille n>=3 pour un prog efficace
    private int[]tab; //tableau de taille nxn de 1, 2 ou de 0 en 1 ligne

    public Morpion2D(int n, int[] tab) {
        super();
        this.n = n;
        this.tab = tab;
    }


    public int getN() {
        return n;
    }


    public void setN(int n) {
        this.n = n;
    }


    public int[] getTab() {
        return tab;
    }


    public void setTab(int[] tab) {
        this.tab = tab;
    }

    //fonction auxiliaire de afficher, permet d'afficher une grille n*n
    public static void printGrille(String[][] grid, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static void printGrille(int[][] grid, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    //prend n, la taille de la grille (n*n) et un tableau d'une ligne
    //valeur def par la classe
    //affiche dans la console une grille avec les correspondance adéquate
    //-1->[x],-2->[O]; 1->x; 2->O ; 0->le numéro de la case en commençant par 1
    public void afficher()
    {
        int[] tab=this.tab;
        int n=this.n;
        String[][] grille = new String[n][n];
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if (tab[i*n+j]==1)
                grille[i][j] = "X";
                else if (tab[i*n+j]==2)
                    grille[i][j] = "O";
                else if (tab[i*n+j]==-1)
                    grille[i][j] = "[X]";
                else if (tab[i*n+j]==-2)
                    grille[i][j] = "[O]";
                else
                    grille[i][j] = String.valueOf(i*n+j+1); //comme ça la première case est 1 ;
            }
        }
        System.out.println("_____________________________");
        printGrille(grille,n);
        System.out.println("_____________________________");
    }

    //placer dans un tableau à une ligne, joueur correspond au joueur qui joue 1 ou 2
    public void placer(int choix,int joueur)
    {//vérifications choix appartient à [1, n]
        System.out.println("Je place mon pion");
        if ((choix>=1)&&(choix<=this.n*this.n))
        {
            this.tab[choix-1]=joueur;
        }
    }

    //après vérification, on lui passe les indices gagnants et il remplace
    //dans le tableau les valeurs 1 par -1 et 2 par -2,
    //ce qui fait que lorsque l'on affiche la grille,
    //les cases gagnantes seront affichées avec des []
    //elle appelle ensuite la fonction afficher pour l'afficher
    public void afficherFin(int[] indices) {
        if (indices.length == 0) {
            System.out.println("Aucun indices donnés");
            return;
        }
        else {
            for (int i = 0; i < indices.length; i++) {
                if (indices[i] >= 0 && indices[i] < this.tab.length) {
                    if (this.tab[indices[i]] == 1) {
                        this.tab[indices[i]] = -1;
                    } else if (this.tab[indices[i]] == 2) {
                        this.tab[indices[i]] = -2;
                    }
                } else {
                    System.out.println("Indice non valide : " + indices[i]);
                }
            }
        }
        afficher();
    }

    //transforme le tableau 1D en grille 2D
    public int[][] grille() {
        int[][] grille = new int[this.n][this.n];
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.n; j++) {
                grille[i][j] = this.tab[i * this.n + j];
            }
        }
        return grille;
    }

    //prend en para le choix du dernier élément placer (l'indice) et retourne les indices des éléments qui sont alignés
    //on va tester si pour cet élément placé, si le joueur a réussi à aligner n éléments
    //pour un morpion 3x3 il faut en aligner 3
    public int[] alignement(int choix) {
        int[][] grille = grille();
        int[] tab = this.tab;
        printGrille(grille, this.n);
        System.out.println("_____");
        int n = this.n;
        int[] indices = new int[n];
        // vérifie les lignes
        if ((choix < 1) || (choix >n*n)) {
            System.out.println("l'indice du choix n'a pas la bonne valeure");
            return indices;
        }
        else {
            choix=choix-1; //pour n=3 le choix va de 1 à 9 mais dans un tableau les indices vont de 0 à 8

            //pour tester la ligne
            int ligne = choix;
            if ((ligne) % n != 0) //si ce n'est pas le cas on est pas au début de la ligne
            {
                //on se recentre
                while (ligne%n!=0) {
                    ligne = ligne - 1;
                }
            }
            boolean testligne = true;
            int numli = ligne / n;
            for (int k = 0; k < n - 1; k++)//on parcours la ligne de choix
            {
                if (grille[numli][k] != grille[numli][k + 1] && ((grille[numli][k] != 1) || (grille[numli][k] != 2))) {
                    testligne = false;
                } //si ils sont différents ils ne sont pas alignés
                else {
                    indices[k] = numli * n + k;
                    indices[k + 1] = numli * n + k + 1;
                }
            }
            if (testligne == false) {
                int[] vide = new int[n];
                indices = vide;
            } else {
                return indices;
            }

            //pour tester la colonne
            int numcol = choix;
            int col = numcol / n;
            if (col != 0) //si ce n'est pas le cas on est pas au début de la colonne
            {
                //on se recentre
                while (col != 0) {
                    col = numcol / n;
                    numcol = numcol - n;
                }
            }
            boolean testcol = true;
            for (int k = 0; k < n - 1; k++)//on parcours la colonne de choix
            {
                if (grille[k][numcol] != grille[k + 1][numcol] && ((grille[k][numcol] != 1) || (grille[k][numcol] != 2))) {
                    testcol = false;
                } //si ils sont différents ils ne sont pas alignés
                else {
                    indices[k] = numcol + k * n;
                    indices[k + 1] = numcol + (k + 1) * n;
                }
            }
            if (testcol == false) {
                int[] vide = new int[n];
                indices = vide;
            } else {
                return indices;
            }

            //pour tester les diagonales :
            //diagonale droite
            // _ _ _ X
            // _ _ X _
            // _ X _ _
            // X _ _ _
            int diagoD = choix;
            int numcolD = numcol;
            int numliD = numli;
            if (numliD != n - 1) //si ce n'est pas le cas on est pas en bas à gauche de la diagonale
            {
                //on se recentre
                while (numliD != n - 1) {
                    numliD = numliD + 1;
                    numcolD = numcolD - 1;
                }
            }
            if (numcolD == 0)//si ce n'est pas le cas, il est impossible de faire une diagonale droite
            {
                boolean testdiagoD = true;
                for (int k = 0; k < n - 1; k++)//on parcours la colonne de choix
                {
                    if (grille[numliD - k][numcolD + k] != grille[numliD - (k + 1)][numcolD + k + 1] && ((grille[numliD][numcolD] != 1) || (grille[numliD - k][numcolD] != 2))) {
                        testdiagoD = false;
                    } //si ils sont différents ils ne sont pas alignés
                    else {
                        indices[k] = (numliD - k) * n + k;
                        indices[k + 1] = (numliD - k + 1) * n + k + 1;
                    }
                }
                if (testdiagoD == false) {
                    int[] vide = new int[n];
                    indices = vide;
                } else {
                    return indices;
                }
            }

            //diagonale gauche
            // X _ _ _
            // _ X _ _
            // _ _ X _
            // _ _ _ X
            int diagoG = choix;
            int numcolG = numcol;
            int numliG = numli;
            if (numcolG != n - 1) //si ce n'est pas le cas on est pas en bas à droite de la diagonale
            {
                //on se recentre
                while (numcolG != n - 1) {
                    numliG = numliG + 1;
                    numcolG = numcolG + 1;
                }
            }
            if (numliG == n - 1)//si ce n'est pas le cas, il est impossible de faire une diagonale gauche
            {
                boolean testdiagoG = true;
                for (int k = 0; k < n - 1; k++)//on parcours la colonne de choix
                {
                    if (grille[numliG - k][numcolG - k] != grille[numliG - (k + 1)][numcolG - (k + 1)] && ((grille[numliG][numcolG] != 1) || (grille[numliG - k][numcolG] != 2))) {
                        testdiagoG = false;
                    } //si ils sont différents ils ne sont pas alignés
                    else {
                        indices[k] = (n * n - 1) - k * (n + 1);
                        indices[k + 1] = (n * n - 1) - (k + 1) * (n + 1);
                    }
                }
                if (testdiagoG == false) {
                    int[] vide = new int[n];
                    indices = vide;
                } else {
                    return indices;
                }
            }
            return null;
        }
    }



    public int[] finPartie()
    {
        int[] indices=new int[this.n];//pour une grille de taille normale 3x3 il faut aligner 3 éléments
        int[][] grille= grille(); //plus simple pour vérifier si on est aligné
        return indices;
    }
}
