package main.java.isys.project3;

import java.util.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.measure.quantity.Dimensionless;

import org.jscience.JScience;
import org.jscience.mathematics.vector.Float64Vector;
import org.jscience.mathematics.vector.Vector;


public class FishReader {
	
	//Class for a fish
	public class Fish{
 		private ArrayList<Float64Vector> route=new ArrayList<Float64Vector>();
		public ArrayList<Float64Vector> getRoute() {
			return route;
		}
		public void setRoute(ArrayList<Float64Vector> route) {
			this.route = route;
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
