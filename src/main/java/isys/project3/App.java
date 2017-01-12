package main.java.isys.project3;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	FishReader fr=new FishReader("src/resources/eval_alone.txt");
   
        System.out.println( "Wir sind die drei Weizen aus dem Morgenland!" );

        System.out.println(fr.getFishList().size());
        System.out.println(fr.getFishList().get(0).cVectors(1).size());
        System.out.println(fr.getFlatCatMovement(1));
        MarkovChances c = new MarkovChances(fr, 1);
    }
}
