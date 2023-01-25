public class Morpion3D{
    // à modifier en fonction du diagramme de classe
    private int n; // largeur du cube de grilles n>=3 pour un prog efficace
    private int[][] tab; //3 grilles pour un 3x3 en 3D, n grilles pour un nxn en 3D:
    //pour tout i tab[k][i] est un tableau int[] contenant la grille de l'étage k (0<k<n-1) et (1<i<n)

    public Morpion3D(int n, int[][] tab) {
        this.n = n;
        this.tab = tab;
    }

    public void placer(int etage, int choix,int joueur)
    {//vérifications choix appartient à [1, n]
        //vérifications étage appartient à [0, n-1]
        System.out.println("\033[0;33mJe place mon pion {étage :"+ etage +"; choix :"+choix+"}\033[0m");
        if ((choix>=1)&&(choix<=this.n*this.n))
        {
            this.tab[etage][choix-1]=joueur;
        }
        else
        {
            System.out.println("Choix non valide");
        }
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

    //fonction d'affichage, transforme le tableau de tableaux de Morpion3D en 3 grilles nxn
    public void afficher()
    {
        System.out.println("________________________________");
        System.out.println("___ \033[0;33m Affichage du Morpion3D \033[0m ___");
        System.out.println("________________________________");
        int[][] tab=this.tab;
        int n=this.n;
        String[][] grille = new String[n][n];
        for (int k=0; k<n; k++){
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (tab[k][i * n + j] == 1)
                        grille[i][j] = " \033[1;31mX\033[0m ";
                    else if (tab[k][i * n + j] == 2)
                        grille[i][j] = " \033[1;34mO\033[0m ";
                    else if (tab[k][i * n + j] == -1)
                        grille[i][j] = "\033[1;31m[X]\033[0m";
                    else if (tab[k][i * n + j] == -2)
                        grille[i][j] = "\033[1;34m[O]\033[0m";
                    else
                        grille[i][j] = " " + String.valueOf(i * n + j + 1) + " "; //comme ça la première case est 1 ;
                }
            }
            System.out.println("___ \033[0;33m étage : "+ String.valueOf(k)+" \033[0m __________");
            printGrille(grille, n);
        }
        System.out.println("________________________________");
    }

    //récupère les indices gagnants testés et trouvés par la fonction alignement
    // et remplace ces à l'indice de ces derniers dans le tableau par
    // des -1 si joueur 1, des -2 si joueur 2
    // la longeur du tbleau indice= celle de etage

    public void afficherFin(int[] indices, int[] etage) throws Exception {
        if (indices.length!=etage.length){throw new Exception("Nombre d'indices et d'étages incompatibles");}
        if (indices.length == 0 || etage.length==0) {
            throw new Exception("Aucun indice donné");
        }
        else {
            for (int i = 0; i < indices.length; i++) {
                if (indices[i] >= 0 && indices[i] < this.tab[0].length) {
                    if (this.tab[etage[i]][indices[i]] == 1) {
                        this.tab[etage[i]][indices[i]] = -1;
                    } else if (this.tab[etage[i]][indices[i]] == 2) {
                        this.tab[etage[i]][indices[i]] = -2;
                    }
                } else {
                    throw new Exception("Indice non valide : " + indices[i]);
                }
            }
        }
        afficher();
    }

    //[ l'étage][ligne] [colonne]
    public int[][][] grille() {
        int[][][] etages = new int[this.n][this.n][this.n];
        int[][] grille = new int[this.n][this.n];
        for (int k=0; k<this.n; k++) {
            for (int i = 0; i < this.n; i++) {
                for (int j = 0; j < this.n; j++) {
                    grille[i][j] = this.tab[k][i * this.n + j];
                }
            }
            etages[k]=grille;
        }
        return etages;
    }

    //prend en para le choix du dernier élément placer (l'indice) et retourne les indices des éléments qui sont alignés
    //on va tester si pour cet élément placé, si le joueur a réussi à aligner n éléments
    //pour un morpion 3x3 il faut en aligner 3
    //return les étages array [0] PUIS les indices array[1]
    public Object[] alignement(int etage, int choix) throws Exception {
        int[][][] grille = grille();
        System.out.println("_____");
        int n = this.n;
        int[] indices = new int[n];
        int[] etages = new int[n];
        // vérifie les indices du choix
        if ((choix < 1) || (choix > n * n) || ((etage < 0) || (etage >= n))) {
            throw new Exception("Le choix de l'étage ou de la case n'a pas la bonne valeur");
        }
        else
        {
            choix=choix-1; //pour n=3 le choix va de 1 à 9 mais dans un tableau les indices vont de 0 à 8

            //pour tester la ligne sur le même étage
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
                if (grille [etage][numli][k] != grille[etage][numli][k + 1] ) {
                    testligne = false;
                } //s'ils sont différents ils ne sont pas alignés
                //par défaut, on remplit les indices
                else {
                    indices[k] = numli * n + k;
                    indices[k + 1] = numli * n + k + 1;
                    etages[k]=etage;
                }
            }
            //si c'est faux on vide les indices
            if (testligne == false) {
                int[] vide = new int[n];
                indices = vide;
                etages= vide;
            } else { //sinon on les returns
                return new int[][]{etages, indices};
            }

            //pour tester la colonne
            int numcol = choix;
            int col = numcol / n;
            if (col != 0) //si ce n'est pas le cas on est pas au début de la colonne
            {
                //on se recentre
                while ((numcol>=n) || (numcol < 0)) {
                    numcol = numcol-n;
                }
            }
            boolean testcol = true;
            for (int k = 0; k < n - 1; k++)//on parcours la colonne de choix
            {
                if (grille[k][numcol] != grille[k + 1][numcol] ) {
                    testcol = false;
                } //si ils sont différents ils ne sont pas alignés
                //par défaut on stock les valeurs des indices
                else {
                    indices[k] = numcol + k * n;
                    indices[k + 1] = numcol + (k + 1) * n;
                    etages[k]=etage;
                }
            }
            //si il s'avère que ils ne sont pas alignés avec ce mode d'alignement on vide les tableaux
            if (testcol == false) {
                int[] vide = new int[n];
                indices = vide;
                etages=vide;
            } else {
                return new int[][]{etages, indices};
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
            if ((numliD != n - 1)||(numcolD!=0)) //si ce n'est pas le cas on est pas en bas à gauche de la diagonale
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
                    if (grille[etage][numliD - k][numcolD + k] != grille[etage][numliD - (k + 1)][numcolD + k + 1] ) {
                        testdiagoD = false;
                    } //si ils sont différents ils ne sont pas alignés
                    else {
                        indices[k] = (numliD - k) * n + k;
                        indices[k + 1] = (numliD - (k+1)) * n + (k+1);
                        etages[k]=etage;
                    }
                }
                if (testdiagoD == false) {
                    int[] vide = new int[n];
                    indices = vide;
                    etages=vide;
                } else {
                    return new int[][]{etages, indices};
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
                    if (grille[etage][numliG - k][numcolG - k] != grille[etage][numliG - (k + 1)][numcolG - (k + 1)]) {
                        testdiagoG = false;
                    } //si ils sont différents ils ne sont pas alignés
                    else {
                        indices[k] = (n * n - 1) - k * (n + 1);
                        indices[k + 1] = (n * n - 1) - (k + 1) * (n + 1);
                        etages[k]=etage;
                    }
                }
                if (testdiagoG == false) {
                    int[] vide = new int[n];
                    indices = vide;
                    etages=vide;
                } else {
                    return new int[][]{etages, indices};
                }
            }
        }
        return new int[][]{null, null};
    }

}

