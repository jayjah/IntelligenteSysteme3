package main.java.isys.project3;

import java.util.ArrayList;
import java.util.List;

import org.jscience.mathematics.number.Float64;
import org.jscience.mathematics.vector.Float64Vector;


public class MarkovChances {
	ArrayList<ArrayList<Float64>> chances = null;
	
	public MarkovChances(FishReader fr, double k){
		chances = new ArrayList<ArrayList<Float64>>(9);
		for(int i = 0 ; i < 9; i++){
			chances.add(new ArrayList<Float64>(9));
			for(int j = 0 ; j < 9; j++){
				chances.get(i).add(Float64.valueOf(0d));
			
			}
		
		}

		List<Float64Vector> fcm = fr.getFlatCatMovement(k);
		
		for(int i = 0; i < fcm.size()-1; i++){
			Float64Vector elementCurr = fcm.get(i);
			Float64Vector elementNext = fcm.get(i+1);
			
			int chanceIndexX = (int)(elementCurr.get(0).doubleValue())*3 +(int)elementCurr.get(1).doubleValue();
			int chanceIndexY = (int)(elementNext.get(0).doubleValue())*3 +(int)elementNext.get(1).doubleValue();
			
			chances.get(chanceIndexX).set(chanceIndexY, chances.get(chanceIndexX).get(chanceIndexY).plus(1d)); //Increment the corresponding cell in the chance table

		
			
		}

		System.out.println(chances);
		
		

	}
	
}
