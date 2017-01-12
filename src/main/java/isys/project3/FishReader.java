package main.java.isys.project3;

import java.util.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import java.util.LinkedList;

import javax.measure.quantity.Dimensionless;

import org.jscience.JScience;
import org.jscience.mathematics.vector.Float64Vector;
import org.jscience.mathematics.vector.Vector;


public class FishReader {
	
	private double classify(double input, double k){
		if(input < -k){
			return 0d;			
		}else if(-k <= input && input <= k){
			return 1d;
		}else if(input > k){
			return 2d;
			
		}
		return 3d;
	}
	
	//Class for a fish
	public class Fish{
 		private List<Float64Vector> route=new ArrayList<Float64Vector>();
		
 		public List<Float64Vector> getRoute() {
			return route;
		}
 		
		public void setRoute(List<Float64Vector> route) {
			this.route = route;
		}
		
		public LinkedList<Float64Vector> Vectors (){
			LinkedList<Float64Vector> vectors = new LinkedList<Float64Vector>();
			for(int i = 0 ; i < route.size()-1; i++){
				vectors.add(route.get(i+1).minus(route.get(i)));
			}
			return vectors;
		}
		
		public LinkedList<Float64Vector> dVectors (){
			LinkedList<Float64Vector> vectors = Vectors();
			LinkedList<Float64Vector> dVectors = new LinkedList<Float64Vector>();
			for(int i = 0 ; i < vectors.size()-1; i++){
				dVectors.add(vectors.get(i+1).minus(vectors.get(i)));
			}
			return dVectors;
		}
		
		
		public LinkedList<Float64Vector> cVectors(double k){
			LinkedList<Float64Vector> dVectors = dVectors();
			LinkedList<Float64Vector> cVectors = new LinkedList<Float64Vector>();
			for(int i = 0 ; i < dVectors.size()-1; i++){
				cVectors.add(Float64Vector.valueOf(classify(dVectors.get(i).getValue(0), k),classify(dVectors.get(i).getValue(1), k)));
			}
			return cVectors;
		}
		

		private Fish(String route){
			String[] pointsS = route.split(";");
			for(String s : pointsS){
				this.route.add(Float64Vector.valueOf(Double.parseDouble(s.split(",")[0]),Double.parseDouble(s.split(",")[1])));
			}
		}
	}
	//list of all fishes
	private ArrayList<Fish> fishList=null;
	public ArrayList<Fish> getFishList() {
		return fishList;
	}
	public void setFishList(ArrayList<Fish> fishList) {
		this.fishList = fishList;
	}
	
	public List<Float64Vector>getFlatCatMovement(double k){
		ArrayList<Float64Vector> output = new ArrayList<Float64Vector>();
		for(Fish fish :fishList){
			output.addAll(fish.cVectors(k));
		}
		return output;
	}

	//reads fishes
	public FishReader(String filename){
		BufferedReader br = null;
		ArrayList<String> lines=new ArrayList<String>();
		this.fishList=new ArrayList<Fish>();
		
		try {
			br = new BufferedReader(new FileReader(filename));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		try {	
			for(;;){
				String s="";
					s = br.readLine();
					if(s==null)
						break;
				this.fishList.add(new Fish(s));
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
