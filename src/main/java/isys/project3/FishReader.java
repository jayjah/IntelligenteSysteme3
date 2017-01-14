package main.java.isys.project3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
/**
 * This class reads the given file and holds a list of fishes.
 * @author Kim Oliver Schweikert, Markus Krebs
 *
 */
public class FishReader {

	/**
	 * List of all fishes
	 */
	private ArrayList<Fish> fishList = null;

	/**
	 * Getter
	 * @return fishlist
	 */
	public ArrayList<Fish> getFishList() {
		return fishList;
	}

	/**
	 * Setter
	 * @param fishList
	 */
	public void setFishList(ArrayList<Fish> fishList) {
		this.fishList = fishList;
	}

	/**
	 * Constructor
	 * Create a list of fishes with the given fish
	 * @param f given fish
	 */
	public FishReader(Fish f)
	{
		this.fishList=new ArrayList<Fish>();
		this.fishList.add(f);
	}
	
	/**
	 * Constructor
	 * Reads the file and holds a list of all fishes in the file.
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
}
