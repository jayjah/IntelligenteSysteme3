package main.java.isys.project3;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Main
 * @author Kim Oliver Schweikert, Markus Krebs
 *
 */
public class App {

	public static void main(String[] args) throws IOException {

		// Filewriter
		FileWriter fw = null;
		fw = new FileWriter(new File("src/resources/validation.csv"), false);
		fw.append("k;right_alone;right_group;sum\r\n");

		// Start value of k
		double k = 0.10000;
		int savepg = 0;
		int savepa = 0;
		int savep = 0;
		double savek = k;

		// Reads the files
		FishReader fr_train_alone = new FishReader("src/resources/train_alone.txt");
		FishReader fr_eval_alone = new FishReader("src/resources/eval_alone.txt");
		FishReader fr_train_group = new FishReader("src/resources/train_group.txt");
		FishReader fr_eval_group = new FishReader("src/resources/eval_group.txt");

		Simulator sim = new Simulator();

		// Endless loop, till k is reached
		for (;;) {

			fr_train_alone.regenerateCVectors(k);
			fr_train_group.regenerateCVectors(k);
			MarkovChances m_train_alone = new MarkovChances(fr_train_alone, k);
			MarkovChances m_train_group = new MarkovChances(fr_train_group, k);
			fr_eval_group.regenerateCVectors(k);
			fr_eval_alone.regenerateCVectors(k);
			ArrayList<Fish> fishlist_alone = fr_eval_alone.getFishList();
			ArrayList<Fish> fishlist_group = fr_eval_group.getFishList();

			// Number of right qualified fishes
			int rightcount_alone = sim.simulate(k, fishlist_alone, m_train_alone, m_train_group);
			int rightcount_group = sim.simulate(k, fishlist_group, m_train_group, m_train_alone);

			if (savep < rightcount_group+ rightcount_alone) {
				savep = rightcount_group + rightcount_alone;
				savepa = rightcount_alone;
				savepg = rightcount_group;
				savek = k;
			}
			fw.append(Math.round(k * 1000.0) / 1000.0 + ";" + rightcount_alone + ";" + rightcount_group + ";"
					+ ((rightcount_group + rightcount_alone)/2) + "\r\n");
			k += 0.1;
			if (k > 20)
				break;
		}
		System.out.println("Best k value found at: " + savek + " with matches in alone : " + savepa + " ("
				+ (savepa / (48.0)) * 100 + ")%");
		System.out.println("Best k value found at: " + savek + " with matches in group : " + savepg + " ("
				+ (savepg / (48.0)) * 100 + ")%");
		fw.close();
	}
}
