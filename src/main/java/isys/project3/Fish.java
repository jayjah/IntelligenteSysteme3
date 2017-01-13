package main.java.isys.project3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.jscience.mathematics.vector.Float64Vector;

public class Fish {
	
	//points of the fish in same time passing
	private List<Float64Vector> route = new ArrayList<Float64Vector>();

	public List<Float64Vector> getRoute() {
		return route;
	}

	public void setRoute(List<Float64Vector> route) {
		this.route = route;
	}
	
	/**
	 * Constructor
	 * Get the points out of the string.
	 * @param route contains the points
	 */
	public Fish(String route) {
		String[] pointsS = route.split(";");
		for (String s : pointsS) {
			this.route.add(Float64Vector.valueOf(Double.parseDouble(s.split(",")[0]),
					Double.parseDouble(s.split(",")[1])));
		}
	}

	public LinkedList<Float64Vector> Vectors() {
		LinkedList<Float64Vector> vectors = new LinkedList<Float64Vector>();
		for (int i = 0; i < route.size() - 1; i++) {
			vectors.add(route.get(i + 1).minus(route.get(i)));
		}
		return vectors;
	}

	public LinkedList<Float64Vector> dVectors() {
		LinkedList<Float64Vector> vectors = Vectors();
		LinkedList<Float64Vector> dVectors = new LinkedList<Float64Vector>();
		for (int i = 0; i < vectors.size() - 1; i++) {
			dVectors.add(vectors.get(i + 1).minus(vectors.get(i)));
		}
		return dVectors;
	}

	public LinkedList<Float64Vector> cVectors(double k) {
		LinkedList<Float64Vector> dVectors = dVectors();
		LinkedList<Float64Vector> cVectors = new LinkedList<Float64Vector>();
		for (int i = 0; i < dVectors.size() - 1; i++) {
			cVectors.add(Float64Vector.valueOf(classify(dVectors.get(i).getValue(0), k),
					classify(dVectors.get(i).getValue(1), k)));
		}
		return cVectors;
	}
	
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
}