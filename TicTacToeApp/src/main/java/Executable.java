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

        Morpion m2d = new Morpion2D();
        System.out.println(m2d);
        m2d.init();
        System.out.println(m2d);
        m2d.afficher();
        m2d.afficherEmplacement(1);
        m2d.placer(1, "X");
        m2d.afficher();
        System.out.println(m2d.checkWin());
        m2d.placer(2, "X");
        System.out.println(m2d.checkWin());
        m2d.placer(3, "X");
        System.out.println(m2d.checkWin());

    }
}
