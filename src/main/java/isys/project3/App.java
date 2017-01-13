package main.java.isys.project3;


public class App {
	public static void main(String[] args) {
		FishReader fr_train_alone = new FishReader("src/resources/train_alone.txt");
		FishReader fr_eval_alone = new FishReader("src/resources/eval_alone.txt");
		FishReader fr_train_group = new FishReader("src/resources/train_group.txt");
		FishReader fr_eval_group = new FishReader("src/resources/eval_group.txt");
		int k = 1;
		
		// System.out.println( "Wir sind die drei Weizen aus dem Morgenland!" );

		// System.out.println(fr.getFishList().size());
		// System.out.println(fr.getFishList().get(0).cVectors(1).size());
		// System.out.println(fr.getFlatCatMovement(1));
		
		MarkovChances m_train_alone = new MarkovChances(fr_train_alone, k);
		MarkovChances m_eval_alone = new MarkovChances(fr_eval_alone, k);
		MarkovChances m_train_group = new MarkovChances(fr_train_group, k);
		MarkovChances m_eval_group = new MarkovChances(fr_eval_group, k);
		
		for (int i=0;i<9;i++) {
			for (int j=0;j<9;j++) {
				System.out.print(m_eval_alone.getChances().get(i).get(j));
			}
			System.out.println("");
		}
		
		for (int i=0;i<3;i++) {
			for (int j=0;j<3;j++) {
				System.out.print(m_eval_alone.getRelativchances().get(i).get(j));
			}
			System.out.println("");
		}
//		System.out.println(m_train_alone.getRelativchances());
	}
}
