package Morpions;

public abstract class Morpion {

    public abstract void init();

    public abstract void afficher();

    public abstract void afficherEmplacement(int index);

    public abstract void placer(int index, String value);

    public abstract boolean checkWin();

    public abstract boolean isFull();

    public abstract int[] alignement();

    public abstract void afficherCombinaison(int[] indexes);

    public abstract boolean validIndex(int index);
}
