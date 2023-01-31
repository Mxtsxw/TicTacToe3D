public class Main {
    public static void main(String[] args)
    {
        // TODO Auto-generated method stub
        int[] tab= {0,0,0,0,0,0,0,0,0};

        Morpion2D Morpion2D=new Morpion2D(3,tab);


          Morpion2D.afficher();
          System.out.println("c'est l'heure du verdict "+ Morpion2D.endGame(Morpion2D.getTab()));
//        Morpion2D.placer(3,1);
//        Morpion2D.afficher();
//        Morpion2D.placer(5,1);
//        Morpion2D.afficher();
//        System.out.println("verif");
//        int[] test=Morpion2D.alignement(5);
//        for (int i=0;i< test.length;i++){System.out.println(test[i]);}
//        System.out.println("fin verif");
//        int[] indices= {0,3,6};

    }
}