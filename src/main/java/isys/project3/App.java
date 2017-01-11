package main.java.isys.project3;

import java.util.ArrayList;

import main.java.isys.project3.FishReader.Fish;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	FishReader fr=new FishReader("testfile.txt");
        System.out.println( "Wir sind die drei Weizen aus dem Morgenland!" );
    }
}
