package main.java.isys.project3;

import java.util.ArrayList;

import org.jscience.mathematics.number.Float64;

public class Simuator {

	public Simuator(MarkovChances m_train_alone, MarkovChances m_train_group, MarkovChances m_eval_alone, MarkovChances m_eval_group) {
		int k = 0;
		int groupcounter = 0;
		int alonecounter = 0;
		for (;k<100;k++) {
			for (int j=0;j<m_train_alone.getRelativchances().size();j++) {
				for (int i=0;i<m_train_alone.getRelativchances().get(j).size();i++) {
					int switcher = Float64.valueOf(1d).minus(m_train_alone.getRelativchances().get(j).get(i)).compareTo(Float64.valueOf(1d).minus(m_train_alone.getRelativchances().get(j).get(i)));
					switch (switcher) {
					case -1:
						break;
					case 0:
						break;
					case 1:
						break;
					}
				}
			}
		}
	}

}
