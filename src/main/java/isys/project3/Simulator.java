package main.java.isys.project3;

import java.util.ArrayList;
import java.util.LinkedList;

import org.jscience.mathematics.number.Float64;
import org.jscience.mathematics.vector.Float64Vector;

/**
 * This class calculates out of the given k value, created chances tables(alone and group) and the given fish list
 * the number of correct qualified fishes out of the given fish list.
 * 
 * @author Kim Oliver Schweikert, Markus Krebs
 *
 */
public class Simulator {

	// Default Constructor
	public Simulator() {

	}

	/**
	 * 
	 * @param k k-value
	 * @param fishlist fishes to qualify
	 * @param m_train_alone marvoc chances out of train_alone data
	 * @param m_train_group marvoc chances out of train_group data
	 * @return number of correct qualified fishes
	 */
	public int simulate(double k, ArrayList<Fish> fishlist, MarkovChances m_train_alone,
			MarkovChances m_train_group) {
		int rightcount = 0;
		for (int i = 0; i < fishlist.size(); i++) {
			// Aktueller Fisch
			LinkedList<Float64Vector> cv_alone = fishlist.get(i).getGeneratedCVectors();
			// Alle relativen Positionen iterieren.
			Float64 v = Float64.valueOf(1.0);
			Float64 v2 = Float64.valueOf(1.0);
			for (int j = 0; j < cv_alone.size() - 1; j++) {
				int tableIndexX = (int) (cv_alone.get(j).getValue(0)) * 3 + (int) cv_alone.get(j).getValue(1);
				int tableIndexY = (int) (cv_alone.get(j + 1).getValue(0)) * 3 + (int) cv_alone.get(j + 1).getValue(1);
				Float64 tabVal, tabVal2;
				tabVal = m_train_alone.getRelativchances().get(tableIndexX).get(tableIndexY);
				tabVal2 = m_train_group.getRelativchances().get(tableIndexX).get(tableIndexY);
				v = v.plus(tabVal.log());
				v2 = v2.plus(tabVal2.log());
			}
			if (v.doubleValue() >= v2.doubleValue()) {// System.out.println("Fish
														// "+i+" alone?: true");
				rightcount++;
			}
		}
		return rightcount;
	}

}
