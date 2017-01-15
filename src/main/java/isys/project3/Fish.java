package main.java.isys.project3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.jscience.mathematics.vector.Float64Vector;

/**
 * This class holds all points in the given string as a route of the fish, a
 * list with all distance vectors and a list with all qualified vectors.
 * 
 * @author Kim Oliver Schweikert, Markus Krebs
 *
 */
public class Fish {

	/**
	 * List with all distance vectors
	 */
	private LinkedList<Float64Vector> dvectors = null;

	/**
	 * Qualified distance vectors
	 */
	private LinkedList<Float64Vector> cvectors = null;

	/**
	 * All vectors 
	 */
	private LinkedList<Float64Vector> vectors = null;

	/**
	 * Points of the fish in same time passing
	 */
	private List<Float64Vector> route = new ArrayList<Float64Vector>();

	/**
	 * Getter
	 * 
	 * @return route
	 */
	public List<Float64Vector> getRoute() {
		return route;
	}

	/**
	 * Setter
	 * 
	 * @param route
	 */
	public void setRoute(List<Float64Vector> route) {
		this.route = route;
	}

	/**
	 * Constructor Get the points out of the string, then generates the distance
	 * vectors of the points and then the list with the qualified values'll be
	 * created out of the distance vectors.
	 * 
	 * @param route
	 *            contains the points
	 */
	public Fish(String route) {
		String[] pointsS = route.split(";");
		for (String s : pointsS) {
			this.route.add(
					Float64Vector.valueOf(Double.parseDouble(s.split(",")[0]), Double.parseDouble(s.split(",")[1])));
		}
		this.generateVectors();
		this.generatDVectors();
	}

	/**
	 * generate all classified vectors for this fish
	 * 
	 * @param k
	 *            k value
	 */
	public void generateCVectors(double k) {
		this.cvectors = this.cVectors(k);

	}

	/**
	 * generate all classified vectors for this fish
	 * 
	 * @return
	 *            created CVectors
	 */
	public LinkedList<Float64Vector> getGeneratedCVectors() {
		return this.cvectors;
	}

	
	/**
	 * getter
	 * 
	 * @return
	 * 			all vectors
	 */
	public LinkedList<Float64Vector> Vectors() {
		return this.vectors;
	}
	/**
	 * 
	 * @return
	 * 			all distance vectors
	 */
	public LinkedList<Float64Vector> dVectors() {
		return this.dvectors;
	}

	
	/**
	 * generate all Vectors
	 */
	private void generateVectors() {
		LinkedList<Float64Vector> vectors = new LinkedList<Float64Vector>();
		for (int i = 0; i < route.size() - 1; i++) {
			vectors.add(route.get(i + 1).minus(route.get(i)));
		}
		this.vectors = vectors;

	}

	/**
	 * generate all distance vectors
	 */
	private void generatDVectors() {
		LinkedList<Float64Vector> vectors = Vectors();
		LinkedList<Float64Vector> dVectors = new LinkedList<Float64Vector>();
		for (int i = 0; i < vectors.size() - 1; i++) {
			dVectors.add(vectors.get(i + 1).minus(vectors.get(i)));
		}
		this.dvectors = dVectors;
	}

	/**
	 * 
	 * @param k k value
	 * @return generated cVectors
	 * 
	 */
	public LinkedList<Float64Vector> cVectors(double k) {
		LinkedList<Float64Vector> dVectors = dVectors();
		LinkedList<Float64Vector> cVectors = new LinkedList<Float64Vector>();
		for (int i = 0; i < dVectors.size() - 1; i++) {
			cVectors.add(Float64Vector.valueOf(classify(dVectors.get(i).getValue(0), k),
					classify(dVectors.get(i).getValue(1), k)));
		}
		return cVectors;
	}

	/**
	 * 
	 * @param input w (x or y of a distane vector)
	 * @param k k value
	 * @return classified value
	 */
	public double classify(double input, double k) {
		if (input < -k) {
			return 0d;
		} else if (-k <= input && input <= k) {
			return 1d;
		} else if (input > k) {
			return 2d;
		}
		return 3d;
	}

	// Not in use
	/*
	 * public int compareToMarkovChances(ArrayList<MarkovChances> markovs,
	 * double k){ MarkovChances thisChances=new MarkovChances(new
	 * FishReader(this),k); double[] sums=new double[markovs.size()];
	 * ArrayList<ArrayList<ArrayList<Float64>>> markovsRelativeResults = new
	 * ArrayList<ArrayList<ArrayList<Float64>>>();
	 * 
	 * for(int ic=0;ic<markovs.size();ic++){ MarkovChances mic=markovs.get(ic);
	 * markovsRelativeResults.add(new ArrayList<ArrayList<Float64>>());
	 * sums[ic]=0; for (int i=0;i<mic.getRelativchances().size();i++) {
	 * markovsRelativeResults.get(ic).add(new ArrayList<Float64>()); for (int
	 * j=0;j<mic.getRelativchances().get(i).size();j++) {
	 * sums[ic]+=Math.abs(markovs.get(ic).getRelativchances().get(i).get(j).
	 * minus(thisChances.getRelativchances().get(i).get(j)).doubleValue()); } }
	 * } double minVal=Double.MAX_VALUE; int minIndex=0; for(int
	 * reti=0;reti<sums.length;reti++){ if(sums[reti]<=minVal){
	 * minVal=sums[reti]; minIndex=reti; } } return minIndex; }
	 */
}