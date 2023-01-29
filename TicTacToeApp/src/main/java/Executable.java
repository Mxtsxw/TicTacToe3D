import Morpions.*;

public class Executable {
    private int modeJeu;
    private boolean inGame;
    private Morpion morpion;

    public Executable(Morpion morpion){
        this.morpion = morpion;
    }

    public static void main(String[] args) {
        System.out.println("Lancement du programme");
    }
}
