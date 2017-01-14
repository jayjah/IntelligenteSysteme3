package main.java.isys.project3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jscience.mathematics.vector.Float64Vector;

public class FishReader {

	// list of all fishes
	private ArrayList<Fish> fishList = null;

	public ArrayList<Fish> getFishList() {
		return fishList;
	}

	public void setFishList(ArrayList<Fish> fishList) {
		this.fishList = fishList;
	}

	public FishReader(Fish f)
	{
		this.fishList=new ArrayList<Fish>();
		this.fishList.add(f);
	}
	
	/**
	 * Constructor
	 * Reads the file and holds a list of all fishes in the file.
	 * A fish is a line in the given file.
	 * @param filename file to read
	 */
	public FishReader(String filename) {
		BufferedReader br = null;
		// ArrayList<String> lines=new ArrayList<String>();
		this.fishList = new ArrayList<Fish>();

		try {
			br = new BufferedReader(new FileReader(filename));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			for (;;) {
				String s = "";
				s = br.readLine();
				if (s == null)
					break;
				this.fishList.add(new Fish(s));
				
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void regenerateCVectors(double k){
		for(Fish f : this.fishList)
			f.generateCVectors(k);
		
	}
	public List<Float64Vector> getFlatCatMovement(double k) {
		ArrayList<Float64Vector> output = new ArrayList<Float64Vector>();
		for (Fish fish : fishList) {
			output.addAll(fish.cVectors(k));
		}
		return output;
	}
}
