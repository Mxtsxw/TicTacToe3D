public class Main {
    public static void main(String[] args)
    {
        try{
            // TODO Auto-generated method stub
            //Morpion2D
//            int[] tab= {1,0,0,2,0,0,1,2,1};
//            Morpion2D Morpion2D=new Morpion2D(3,tab);
//            Morpion2D.afficher();
//            Morpion2D.placer(5,1);
//            Morpion2D.afficher();
//            System.out.println("verif");
//            int[] test=Morpion2D.alignement(5);
//            for (int i=0;i< test.length;i++){System.out.println(test[i]);}
//            System.out.println("fin verif");
//            Morpion2D.afficherFin(test);

            //Morpion3D
            int [][] tab3d={{1,0,0,2,0,0,1,2,1},{0,0,0,0,0,2,1,2,1},{1,0,0,2,0,1,1,2,1}};
            Morpion3D Morpion3D=new Morpion3D(3,tab3d);
            Morpion3D.afficher();
            Morpion3D.placer(2,5,2);
            Morpion3D.afficher();
//            int [] indices={7,7,7};
//            int [] etages= {0,1,2};
            Object [] array= Morpion3D.alignement(2,5);
            int [] indices = (int[]) array[1];
            int [] etages = (int[]) array[0];
            Morpion3D.afficherFin(indices,etages);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}