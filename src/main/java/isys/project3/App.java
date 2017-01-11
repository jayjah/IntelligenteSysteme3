package main.java.isys.project3;

import java.nio.file.Paths;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	FishReader fr=new FishReader(Paths.get("","src","resources","eval_alone.txt").toAbsolutePath().toString());
        System.out.println( "Wir sind die drei Weizen aus dem Morgenland!" );
        
        System.out.println(fr.getFishList().size());
        System.out.println(fr.getFishList().get(fr.getFishList().size()-1).getRoute().size());
    }
}
