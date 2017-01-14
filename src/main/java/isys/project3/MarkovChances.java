package main.java.isys.project3;

import java.util.ArrayList;
import java.util.List;

import org.jscience.mathematics.number.Float64;
import org.jscience.mathematics.vector.Float64Vector;


public class MarkovChances {

	ArrayList<ArrayList<Float64>> chances = null;
	ArrayList<ArrayList<Float64>> relativchances = null;
	
	public ArrayList<ArrayList<Float64>> getChances() {
		return chances;
	}

	public ArrayList<ArrayList<Float64>> getRelativchances() {
		return relativchances;
	}

	public MarkovChances(FishReader fr, double k){
		chances = new ArrayList<ArrayList<Float64>>(9);
		relativchances = new ArrayList<ArrayList<Float64>>(9);
		
		for(int i = 0 ; i < 9; i++){
			relativchances.add(new ArrayList<Float64>(9));
			chances.add(new ArrayList<Float64>(9));
			for(int j = 0 ; j < 9; j++){
				chances.get(i).add(Float64.valueOf(1d));
			}
		}

		//List<Float64Vector> fcm = fr.getFlatCatMovement(k);	
		ArrayList<Fish> fishes=fr.getFishList();
		
		
		for(int i=0;i<fishes.size();i++){
			for(int j=0;j<fishes.get(i).getGeneratedCVectors().size()-1;j++){
				Float64Vector elementCurr = fishes.get(i).getGeneratedCVectors().get(j);
				Float64Vector elementNext = fishes.get(i).getGeneratedCVectors().get(j+1);
				
				int chanceIndexX = (int)(elementCurr.get(0).doubleValue())*3 +(int)elementCurr.get(1).doubleValue();
				int chanceIndexY = (int)(elementNext.get(0).doubleValue())*3 +(int)elementNext.get(1).doubleValue();
				
				chances.get(chanceIndexX).set(chanceIndexY, chances.get(chanceIndexX).get(chanceIndexY).plus(1d)); //Increment the corresponding cell in the chance table
			
				
			}
			
		}
		/*
		for(int i = 0; i < fcm.size()-1; i++){
			Float64Vector elementCurr = fcm.get(i);
			Float64Vector elementNext = fcm.get(i+1);
			
			int chanceIndexX = (int)(elementCurr.get(0).doubleValue())*3 +(int)elementCurr.get(1).doubleValue();
			int chanceIndexY = (int)(elementNext.get(0).doubleValue())*3 +(int)elementNext.get(1).doubleValue();
			
			chances.get(chanceIndexX).set(chanceIndexY, chances.get(chanceIndexX).get(chanceIndexY).plus(1d)); //Increment the corresponding cell in the chance table
		}
		*/
		//set all relativ chances in table
		double counter = 0d;
		for (int i=0;i<9;i++) {
			for (int j=0;j<9;j++) {
				counter +=chances.get(i).get(j).doubleValue();
			}
			for (int j=0;j<9;j++) {
				relativchances.get(i).add((chances.get(i).get(j).divide(counter)));
			}
			counter = 0;
		}
//		System.out.println(chances);
//		System.out.println(relativchances);
//		System.out.println(relativchances.get(0).size());
	}
}
