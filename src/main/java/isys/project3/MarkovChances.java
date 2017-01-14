package main.java.isys.project3;

import java.util.ArrayList;

import org.jscience.mathematics.number.Float64;
import org.jscience.mathematics.vector.Float64Vector;

/**
 * This class holds the absolute chances and the calculated relative chances to
 * the absolute chances.
 * 
 * @author Kim Oliver Schweikert, Johannes Gosch, Markus Krebs
 *
 */
public class MarkovChances {

	/**
	 * Absolute chances
	 */
	ArrayList<ArrayList<Float64>> chances = null;

	/**
	 * Relativ chances
	 */
	ArrayList<ArrayList<Float64>> relativchances = null;

	/**
	 * Getter
	 * 
	 * @return chances
	 */
	public ArrayList<ArrayList<Float64>> getChances() {
		return chances;
	}

	/**
	 * Getter
	 * 
	 * @return relativchances
	 */
	public ArrayList<ArrayList<Float64>> getRelativchances() {
		return relativchances;
	}

	/**
	 * Constructor Calculates the absolute and relative chances of the given
	 * fishes
	 * 
	 * @param fr
	 *            List with all fishes
	 * @param k
	 *            actual chosen k value
	 */
	public MarkovChances(FishReader fr, double k) {
		chances = new ArrayList<ArrayList<Float64>>(9);
		relativchances = new ArrayList<ArrayList<Float64>>(9);

		// init the lists
		for (int i = 0; i < 9; i++) {
			relativchances.add(new ArrayList<Float64>(9));
			chances.add(new ArrayList<Float64>(9));
			for (int j = 0; j < 9; j++) {
				chances.get(i).add(Float64.valueOf(1d));
			}
		}

		// Calculate the absolute chances of the given fish list
		ArrayList<Fish> fishes = fr.getFishList();
		for (int i = 0; i < fishes.size(); i++) {
			for (int j = 0; j < fishes.get(i).getGeneratedCVectors().size() - 1; j++) {
				Float64Vector elementCurr = fishes.get(i).getGeneratedCVectors().get(j);
				Float64Vector elementNext = fishes.get(i).getGeneratedCVectors().get(j + 1);
				int chanceIndexX = (int) (elementCurr.get(0).doubleValue()) * 3
						+ (int) elementCurr.get(1).doubleValue();
				int chanceIndexY = (int) (elementNext.get(0).doubleValue()) * 3
						+ (int) elementNext.get(1).doubleValue();
				chances.get(chanceIndexX).set(chanceIndexY, chances.get(chanceIndexX).get(chanceIndexY).plus(1d)); // Increment
																													// the
																													// corresponding
																													// cell
																													// in
																													// the
																													// chance
																													// table
			}
		}

		// Calculate the relativ chances of the absolute chances
		double counter = 0d;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				counter += chances.get(i).get(j).doubleValue();
			}
			for (int j = 0; j < 9; j++) {
				relativchances.get(i).add((chances.get(i).get(j).divide(counter)));
			}
			counter = 0;
		}
	}
}
