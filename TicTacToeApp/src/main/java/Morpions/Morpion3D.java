package Morpions;

import java.util.Scanner;

public class Morpion3D {
    //Attributs
    // à modifier en fonction du diagramme de classe
    private int n; // largeur du cube de grilles n>=3 pour un prog efficace
    private int[][] tab; //3 grilles pour un 3x3 en 3D, n grilles pour un nxn en 3D:
    //pour tout i tab[k][i] est un tableau int[] contenant la grille de l'étage k (0<k<n-1) et (1<i<n)

    //Getters & setters

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int[][] getTab() {
        return tab;
    }

    public void setTab(int[][] tab) {
        this.tab = tab;
    }

    public Morpion3D(int n, int[][] tab) {
        this.n = n;
        this.tab = tab;
    }
    //Getters & setters

    //Méthodes
    public void placer(int etage, int choix,int joueur)
    {//vérifications choix appartient à [1, n]
        if ((choix>=1)&&(choix<=this.n*this.n)&&(etage>=0)&&(etage<=this.n))
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
        int[][][] grille3d = new int[this.n][this.n][this.n];
        for (int k=0; k<this.n; k++) {
            for (int i = 0; i < this.n; i++) {
                for (int j = 0; j < this.n; j++) {
                    grille3d[k][i][j] = this.tab[k][i * this.n + j];
                }
            }
        }
        return grille3d;
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
            //Pour tester les diagos des bords

            //4diagos centrales
            //8diagos sur les bords

            //cas diagoaa1
            // x 2 3 | 1 2 3 | 1 2 3
            // 4 5 6 | x 5 6 | 4 5 6
            // 7 8 9 | 7 8 9 | x 8 9
            //cas diagoaa1bis
            // 1 2 x | 1 2 3 | 1 2 3
            // 4 5 6 | 4 5 x | 4 5 6
            // 7 8 9 | 7 8 9 | 7 8 x
            //cas diagocv1
            // 1 x 3 | 1 2 3 | 1 2 3
            // 4 5 6 | 4 x 6 | 4 5 6
            // 7 8 9 | 7 8 9 | 7 x 9
            int numetage = etage;
            int numli = choix/n;
            int numcol = choix-(n*numli);
            while (numetage!=0) //si etage!=0, on n'est pas en bas du cube
            {   //on se recentre
                numli = numli - 1;
                numetage=numetage-1;
            }
            if (numli ==0)//si ce n'est pas le cas, il est impossible de faire une diagoaa1
            {
                boolean test = true;
                for (int k = 0; k < n - 1; k++)//on parcourt la colonne de choix
                {
                    if (grille[k][numli+k][numcol] != grille[k][numli+k+1][numcol]) {
                        test = false;//s'ils sont différents ils ne sont pas alignés
                    }
                    else {
                        indices[k] = n * (numli+k) +numcol;
                        indices[k + 1] = n *(numli+k+1) +numcol;
                        etages[k]=k;
                        etages[k+1]=k+1;
                    }
                }
                if (test == false) {
                    int[] vide = new int[n];
                    indices = vide;
                    etages=vide;
                } else {
                    return new int[][]{etages, indices};
                }
            }

            //cas diagoaa2
            // 1 2 3 | 1 2 3 | x 2 3
            // 4 5 6 | x 5 6 | 4 5 6
            // x 8 9 | 7 8 9 | 7 8 9
            //cas diagoaa2bis
            // 1 2 3 | 1 2 3 | 1 2 x
            // 4 5 6 | 4 5 x | 4 5 6
            // 7 8 x | 7 8 9 | 7 8 9
            //cas diagocv2
            // 1 2 3 | 1 2 3 | 1 x 3
            // 4 5 6 | 4 x 6 | 4 5 6
            // 7 x 9 | 7 8 9 | 7 8 9
            numetage = etage;
            numli = choix/n;
            numcol = choix-(n*numli);
            while (numetage!=0) //si etage!=0, on n'est pas en bas du cube
            {   //on se recentre
                numli = numli + 1;
                numetage=numetage-1;
            }
            if (numli ==n-1)//si ce n'est pas le cas, il est impossible de faire une diagoaa1
            {
                boolean test = true;
                for (int k = 0; k < n - 1; k++)//on parcourt la colonne de choix
                {
                    if (grille[k][numli-k][numcol] != grille[k+1][numli-(k+1)][numcol] || (grille[k][numli-k][numcol] == 0) || (grille[k+1][numli-(k+1)][numcol] == 0)) {
                        test = false;//s'ils sont différents ils ne sont pas alignés
                    }
                    else {
                        indices[k] = n * (numli-k) +numcol;
                        indices[k + 1] = n *(numli-(k+1)) +numcol;
                        etages[k]=k;
                        etages[k+1]=k+1;
                    }
                }
                if (test == false) {
                    int[] vide = new int[n];
                    indices = vide;
                    etages=vide;
                } else {
                    return new int[][]{etages, indices};
                }
            }

            //cas diagobb1
            // 1 2 3 | 1 2 3 | 1 2 3
            // 4 5 6 | 4 5 6 | 4 5 6
            // x 8 9 | 7 x 9 | 7 8 x
            //cas diagobb1bis
            // x 2 3 | 1 x 3 | 1 2 x
            // 4 5 6 | 4 5 6 | 4 5 6
            // 7 8 9 | 7 8 9 | 7 8 9
            //cas diagoch1
            // 1 2 3 | 1 2 3 | 1 2 3
            // x 5 6 | 4 x 6 | 4 5 x
            // 7 8 9 | 7 8 9 | 7 8 9
            numetage = etage;
            numli = choix/n;
            numcol = choix-(n*numli);
            while (numetage!=0) //si etage!=0, on n'est pas en bas du cube
            {   //on se recentre
                numcol = numcol + 1;
                numetage=numetage-1;
            }
            if (numcol==0)//si ce n'est pas le cas, il est impossible de faire une diagobb1
            {
                boolean test = true;
                for (int k = 0; k < n - 1; k++)//on parcourt la colonne de choix
                {
                    if ((grille[k][numli][numcol + k] != grille[k+1][numli][numcol + (k + 1)]) || (grille[k][numli][numcol + k] == 0) || (grille[k+1][numli][numcol + (k + 1)] == 0)) {
                        test = false;//s'ils sont différents ils ne sont pas alignés
                    }
                    else {
                        indices[k] = n * numli +numcol + k;
                        indices[k + 1] = n * numli +numcol + (k + 1);
                        etages[k]=k;
                        etages[k+1]=k+1;
                    }
                }
                if (test == false) {
                    int[] vide = new int[n];
                    indices = vide;
                    etages=vide;
                } else {
                    return new int[][]{etages, indices};
                }
            }

            //cas diagobb2
            // 1 2 3 | 1 2 3 | 1 2 3
            // 4 5 6 | 4 5 6 | 4 5 6
            // 7 8 x | 7 x 9 | x 8 9
            //cas diagobb2bis
            // 1 2 x | 1 x 3 | x 2 3
            // 4 5 6 | 4 5 6 | 4 5 6
            // 7 8 9 | 7 8 9 | 7 8 9
            //cas diagoch2
            // 1 2 3 | 1 2 3 | 1 2 3
            // 4 5 x | 4 x 6 | x 5 6
            // 7 8 9 | 7 8 9 | 7 8 9
            numetage = etage;
            numli = choix/n;
            numcol = choix-(n*numli);
            while (numetage!=0) //si etage!=0, on n'est pas en bas du cube
            {   //on se recentre
                numcol = numcol + 1;
                numetage=numetage-1;
            }
            if (numcol==n-1)//si ce n'est pas le cas, il est impossible de faire une diagobb2
            {
                boolean test = true;
                for (int k = 0; k < n - 1; k++)//on parcourt la colonne de choix
                {
                    if ((grille[k][numli][numcol - k] != grille[k + 1][numli][numcol - (k + 1)]) || (grille[k][numli][numcol - k] == 0) ||(grille[k + 1][numli][numcol - (k + 1)] == 0)) {
                        test = false;//s'ils sont différents ils ne sont pas alignés
                    }
                    else {
                        indices[k] = n * numli +numcol - k;
                        indices[k + 1] = n * numli +numcol - (k + 1);
                        etages[k]=k;
                        etages[k+1]=k+1;
                    }
                }
                if (test == false) {
                    int[] vide = new int[n];
                    indices = vide;
                    etages=vide;
                } else {
                    return new int[][]{etages, indices};
                }
            }

            //pour tester sur des ETAGES DIFFERENTS (et pas sur les faces)
            //4 diagos étages

            //cas diagoG2
            // x 2 3 | 1 2 3 | 1 2 3
            // 4 5 6 | 4 x 6 | 4 5 6
            // 7 8 9 | 7 8 9 | 7 8 x
            numetage = etage;
            numli = choix/n;
            numcol = choix-(n*numli);
            while (numetage!=0) //si etage!=0, on n'est pas en bas du cube
            {   //on se recentre
                numli = numli - 1;
                numcol = numcol - 1;
                numetage=numetage-1;
            }
            if ((numli ==0)&&(numcol==0))//si ce n'est pas le cas, il est impossible de faire une diagoG1
            {
                boolean test = true;
                for (int k = 0; k < n - 1; k++)//on parcourt la colonne de choix
                {
                    if (grille[k][numli + k][numcol + k] != grille[k + 1][numli + (k + 1)][numcol + (k + 1)] ||(grille[k][numli + k][numcol + k] == 0 || (grille[k + 1][numli + (k + 1)][numcol + (k + 1)] == 0) ) ) {
                        test = false;
                    } //s'ils sont différents ils ne sont pas alignés
                    else {
                        indices[k] = n * (numli+k) +numcol + k;
                        indices[k + 1] = n * (numli+k+1) +numcol + (k + 1);
                        etages[k]=k;
                        etages[k+1]=k+1;
                    }
                }
                if (test == false) {
                    int[] vide = new int[n];
                    indices = vide;
                    etages=vide;
                } else {
                    return new int[][]{etages, indices};
                }
            }


            //cas diagoD2
            // 1 2 x | 1 2 3 | 1 2 3
            // 4 5 6 | 4 x 6 | 4 5 6
            // 7 8 9 | 7 8 9 | x 8 9
            numli = choix/n;
            numcol = choix-(n*numli);
            numetage=etage;
            while (numetage!=0) //si etage n'est pas égal à 0 cela veut dire que l'on n'est pas tout en bas du cube
            {                //donc on se recentre
                numli=numli-1;
                numcol=numcol+1;
                numetage=numetage-1;
            }
            if ((numcol == n -1)&&(numli== 0))//si ce n'est pas le cas, il est impossible de faire une diagoD2
            {
                boolean test = true;
                for (int k = 0; k < n - 1; k++)//on parcourt la colonne de choix
                {
                    if (grille[k][numli + k][numcol - k] != grille[k+1][numli + (k + 1)][numcol -(k + 1)] || (grille[k][numli + k][numcol - k] == 0) || (grille[k+1][numli + (k + 1)][numcol -(k + 1)] == 0) ) {
                        test = false;
                    } //s'ils sont différents ils ne sont pas alignés
                    else {
                        indices[k] = n * (numli+k) +numcol - k;
                        indices[k + 1] = n * (numli+k+1) +numcol - (k + 1);
                        etages[k]=k;
                        etages[k+1]=k+1;
                    }
                }
                if (test == false) {
                    int[] vide = new int[n];
                    indices = vide;
                    etages=vide;
                } else {
                    return new int[][]{etages, indices};
                }
            }

            //cas diagoD1
            // 1 2 3 | 1 2 3 | 1 2 x
            // 4 5 6 | 4 x 6 | 4 5 6
            // x 8 9 | 7 8 9 | 7 8 9
            numli = choix/n;
            numcol = choix-(n*numli);
            numetage=etage;
            while (numetage!=0) //si etage n'est pas égal à 0 cela veut dire que l'on n'est pas tout en bas du cube
            {                //donc on se recentre
                numli=numli+1;
                numcol=numcol-1;
                numetage=numetage-1;
            }
            if ((numcol == 0)&&(numli==n-1))//si ce n'est pas le cas, il est impossible de faire une diagoD1
            {
                boolean test = true;
                for (int k = 0; k < n - 1; k++)//on parcourt la colonne de choix
                {
                    if ((grille[k][numli - k][numcol + k] != grille[k + 1][numli - (k + 1)][numcol + k + 1] )|| (grille[k][numli - k][numcol + k] == 0)|| (grille[k + 1][numli - (k + 1)][numcol + k + 1] == 0) ) {
                        test = false;
                    } //s'ils sont différents ils ne sont pas alignés
                    else {
                        indices[k] = n * (numli-k) +numcol + k;
                        indices[k + 1] = n * (numli-k+1) +numcol + (k + 1);
                        etages[k]=k;
                        etages[k+1]=k+1;
                    }
                }
                if (test == false) {
                    int[] vide = new int[n];
                    indices = vide;
                    etages=vide;
                } else {
                    return new int[][]{etages, indices};
                }
            }

            //cas diagoG1
            // 1 2 3 | 1 2 3 | x 2 3
            // 4 5 6 | 4 x 6 | 4 5 6
            // 7 8 x | 7 8 9 | 7 8 9
            numetage = etage;
            numli = choix/n;
            numcol = choix-(n*numli);
            while (numetage!=0) //si etage!=0, on n'est pas en bas du cube
            {   //on se recentre
                numli = numli + 1;
                numcol = numcol + 1;
                numetage=numetage-1;
            }
            if ((numli == n - 1)&&(numcol== n-1))//si ce n'est pas le cas, il est impossible de faire une diagoG1
            {
                boolean test = true;
                for (int k = 0; k < n - 1; k++)//on parcourt la colonne de choix
                {
                    if ((grille[k][numli - k][numcol - k] != grille[k + 1][numli - (k + 1)][numcol - (k + 1)]) || (grille[k][numli - k][numcol - k] == 0) || (grille[k + 1][numli - (k + 1)][numcol - (k + 1)] ==0) ) {
                        test = false;
                    } //s'ils sont différents ils ne sont pas alignés
                    else {
                        indices[k] = n * (numli-k) +numcol - k;
                        indices[k + 1] = n * (numli-(k+1)) +numcol - (k + 1);
                        etages[k]=k;
                        etages[k+1]=k+1;
                    }
                }
                if (test == false) {
                    int[] vide = new int[n];
                    indices = vide;
                    etages=vide;
                } else {
                    return new int[][]{etages, indices};
                }
            }

            //cas alignement étage
            // 1 2 x | 1 2 x | 1 2 x
            // 4 5 6 | 4 5 6 | 4 5 6
            // 7 8 9 | 7 8 9 | 7 8 9
            boolean test2=true;
            numli= choix /n;
            numcol = choix-(n*numli);
            for (int k = 0; k < n - 1; k++)//on parcours les étages avec le même choix
            {
                if (grille [k][numli][numcol] != grille[k+1][numli][numcol] || (grille [k][numli][numcol] == 0) || (grille [k][numli][numcol] == 0) ) {
                    test2 = false;
                } //s'ils sont différents ils ne sont pas alignés
                //par défaut, on remplit les indices
                else {
                    indices[k] = choix;
                    indices[k + 1] = choix;
                    etages[k]=k;
                    etages[k+1]=k+1;
                }
            }
            //si c'est faux on vide les indices
            if (test2 == false) {
                int[] vide = new int[n];
                indices = vide;
                etages= vide;
            } else { //sinon on les returns
                return new int[][]{etages, indices};
            }

            //pour tester sur LE MEME ETAGE
            //pour tester la ligne
            numli = choix/n;
            //on se recentre si besoin
            while (numli!=0) {
                numli = numli - 1;
            }
            test2 = true;
            for (int k = 0; k < n - 1; k++)//on parcours la ligne de choix
            {
                if (grille [etage][numli][k] != grille[etage][numli][k + 1] ) {
                    test2 = false;
                } //s'ils sont différents ils ne sont pas alignés
                //par défaut, on remplit les indices
                else {
                    indices[k] = numli * n + k;
                    indices[k + 1] = numli * n + k + 1;
                    etages[k]=etage;
                    etages[k+1]=etage;
                }
            }
            //si c'est faux on vide les indices
            if (test2 == false) {
                int[] vide = new int[n];
                indices = vide;
                etages= vide;
            } else { //sinon on les returns
                return new int[][]{etages, indices};
            }

            //pour tester la colonne
            numcol = choix / n;
            while (numcol!=0){numcol=numcol-1;}//tant qu'on est pas au début de la colonne, on se recentre
            test2 = true;
            for (int k = 0; k < n - 1; k++)//on parcours la colonne de choix
            {
                if (grille[etage][k][numcol] != grille[etage][k + 1][numcol] ) {
                    test2 = false;
                } //si ils sont différents ils ne sont pas alignés
                //par défaut on stock les valeurs des indices
                else {
                    indices[k] = numcol + k * n;
                    indices[k + 1] = numcol + (k + 1) * n;
                    etages[k]=etage;
                    etages[k+1]=etage;
                }
            }
            //si il s'avère que ils ne sont pas alignés avec ce mode d'alignement on vide les tableaux
            if (test2 == false) {
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
            numli = choix/n;
            numcol = choix-(n*numli);
            while (numcol!=0){
                numcol=numcol-1;
                numli=numli+1;}
            if (numli == n-1)//si ce n'est pas le cas, il est impossible de faire une diagonale droite
            {
                boolean test = true;
                for (int k = 0; k < n - 1; k++)//on parcours la colonne de choix
                {
                    if (grille[etage][numli - k][numcol + k] != grille[etage][numli - (k + 1)][numcol + k + 1] ) {
                        test = false;
                    } //si ils sont différents ils ne sont pas alignés
                    else {
                        indices[k] = (numli - k) * n + numcol+ k;
                        indices[k + 1] = (numli - (k+1)) * n + numcol+ (k+1);
                        etages[k]=etage;
                        etages[k+1]=etage;
                    }
                }
                if (test == false) {
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
            numli = choix/n;
            numcol = choix-(n*numli);
            while (numcol!=n-1){
                numcol=numcol+1;
                numli=numli+1;}
            if (numli == n - 1)//si ce n'est pas le cas, il est impossible de faire une diagonale gauche
            {
                boolean test = true;
                for (int k = 0; k < n - 1; k++)//on parcourt la colonne de choix
                {
                    if (grille[etage][numli - k][numcol - k] != grille[etage][numli - (k + 1)][numcol - (k + 1)]) {
                        test = false;
                    } //s'ils sont différents ils ne sont pas alignés
                    else {
                        indices[k] = (numli - k) * n +numcol- k;
                        indices[k + 1] = (numli - (k+1)) * n +numcol- (k+1);
                        etages[k]=etage;
                        etages[k+1]=etage;
                    }
                }
                if (test == false) {
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
    public boolean endGame(int[][] tab) {
        //Vérification si le tableau est plein
        for (int i=0;i<tab.length;i++){
            for (int j=0; j<tab.length;j++){
                if (tab[i][j] == 0)
                    return false;
            }
        }
        return true;
    }
    public void start(){
        Scanner input = new Scanner(System.in);
        boolean end = true;
        int currentPlayer = 1;
        while (!this.endGame(this.getTab()) && end) {
            this.afficher();
            System.out.println("Joueur " + currentPlayer + ", choisi une case (1-" + (getN() * getN()) + "): ");
            int choice = -1;
            while (choice < 1 || choice > getN() * getN()) {
                while (!input.hasNextInt()) {
                    input.nextLine();
                    System.out.println("Saisie non valide, veuillez entrer un nombre entier.");
                    System.out.println("Joueur " + currentPlayer + ", choisi une case (1-" + (getN() * getN()) + "): ");
                }
                choice = input.nextInt();
                if (choice < 1 || choice > getN() * getN()) {
                    System.out.println("Choix en dehors de la limite du tableau, veuillez réessayer.");
                } else if (this.getTab()[getN() - 1][choice - 1] != 0) {
                    System.out.println("Cette case est déjà jouée, veuillez réessayer.");
                }
            }

            System.out.println("Joueur " + currentPlayer + ", choisi un étage (0-" + getN() + "): ");
            int etage = -1;
            while (etage < 0 || etage > getN() || this.getTab()[etage][choice - 1] != 0) {
                while (!input.hasNextInt()) {
                    input.nextLine();
                    System.out.println("Saisie non valide, veuillez entrer un nombre entier.");
                    System.out.println("Joueur " + currentPlayer + ", choisi un étage (0-" + getN() + "): ");
                }
                etage = input.nextInt();
                if (etage < 0 || etage > getN()) {
                    System.out.println("Choix en dehors de la limite du tableau, veuillez réessayer.");
                } else if (this.getTab()[etage][choice - 1] != 0) {
                    System.out.println("Cette case est déjà jouée, veuillez réessayer.");
                }
            }
            this.placer(etage, choice, currentPlayer);
            System.out.println("prout");

            try{
                Object[] a = this.alignement(etage, choice);
                int[] b = (int[]) a[0];//etages
                int[] c = (int[]) a[1];//indices
                if (b != null && c != null){
                    end = false;

                    this.afficherFin(b, c);
                }
            }catch (Exception e){
                System.out.println(e);
            }


            if (currentPlayer == 1) {
                currentPlayer = 2;
            } else {
                currentPlayer = 1;
            }
        }
    }
}

