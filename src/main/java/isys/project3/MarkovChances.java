package main.java.isys.project3;

import java.util.ArrayList;
import java.util.List;

import org.jscience.mathematics.number.Float64;
import org.jscience.mathematics.vector.Float64Vector;


public class MarkovChances {
	
	ArrayList<ArrayList<Float64>> chances = null;
	ArrayList<ArrayList<Float64>> relativchances = null;
	private int rows = 9;
	private int columns = 9;
	
	public ArrayList<ArrayList<Float64>> getChances() {
		return chances;
	}

	public ArrayList<ArrayList<Float64>> getRelativchances() {
		return relativchances;
	}

	public MarkovChances(FishReader fr, double k){
		chances = new ArrayList<ArrayList<Float64>>(9);
		relativchances = new ArrayList<ArrayList<Float64>>(9);
		for(int i = 0 ; i < rows; i++){
			chances.add(new ArrayList<Float64>(9));
			relativchances.add(new ArrayList<Float64>(9));
			for(int j = 0 ; j < columns; j++){
				chances.get(i).add(Float64.valueOf(0d));
				relativchances.get(i).add(Float64.valueOf(0d));
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
		
		//set all relativ chances in table
		double counter = 0d;
		for (int i=0;i<rows;i++) {
			for (int j=0;j<columns;j++) {
				counter +=chances.get(i).get(j).doubleValue();
			}
			for (int j=0;j<columns;j++) {
				relativchances.get(i).add((chances.get(i).get(j).copy().divide(counter)));	
			}
			counter = 0;
		}
	}
}
