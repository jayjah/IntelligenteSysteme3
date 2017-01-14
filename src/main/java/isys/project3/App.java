package main.java.isys.project3;
import org.jscience.mathematics.number.Float64;
import org.jscience.mathematics.vector.Float64Vector;
import java.awt.List;
import java.util.ArrayList;
import java.util.LinkedList;

public class App {
	public static void main(String[] args) {
		
		double k =0.000000;
		
		FishReader fr_train_alone = new FishReader("src/resources/train_alone.txt");
		FishReader fr_eval_alone = new FishReader("src/resources/eval_alone.txt");
		FishReader fr_train_group = new FishReader("src/resources/train_group.txt");
		FishReader fr_eval_group = new FishReader("src/resources/eval_group.txt");
		
		int savep=0;
		double savek=k;
		// System.out.println( "Wir sind die drei Weizen aus dem Morgenland!" );

		// System.out.println(fr.getFishList().size());
		// System.out.println(fr.getFishList().get(0).cVectors(1).size());
		// System.out.println(fr.getFlatCatMovement(1));
		for(;;){
			fr_train_alone.regenerateCVectors(k);
			fr_train_group.regenerateCVectors(k);
		MarkovChances m_train_alone = new MarkovChances(fr_train_alone, k);
		MarkovChances m_train_group = new MarkovChances(fr_train_group, k);
		
		fr_eval_alone.regenerateCVectors(k);
		ArrayList<Fish> test=fr_eval_alone.getFishList();
		
		//Counter für richtige
		
		
		//Alle Fische iterieren
			int rightcount=0;
		for(int i=0;i<test.size();i++){
			//Aktueller Fisch
			LinkedList<Float64Vector> cv_alone=test.get(i).getGeneratedCVectors();
			//Alle relativen Positionen iterieren.
			Float64 v=Float64.valueOf(1.0);
			Float64 v2=Float64.valueOf(1.0);
			for(int j=0;j<cv_alone.size()-1;j++){
			
				
				int tableIndexX= (int)(cv_alone.get(j).getValue(0))*3 +(int)cv_alone.get(j).getValue(1);
				int tableIndexY= (int)(cv_alone.get(j+1).getValue(0))*3 +(int)cv_alone.get(j+1).getValue(1);	
				Float64 tabVal, tabVal2;		
				tabVal=m_train_alone.getRelativchances().get(tableIndexX).get(tableIndexY);
				tabVal2=m_train_group.getRelativchances().get(tableIndexX).get(tableIndexY);
	
					v=v.plus(tabVal.log());
			
			
					v2=v2.plus(tabVal2.log());
		
				//System.out.print(tabVal.doubleValue()+","+tabVal2+" | ");
			}
			if(v.isLargerThan(v2)){
				//System.out.println("Fish "+i+" alone?: true");
				rightcount++;
			}
			
			
			//System.out.println("\r\n"+v+" | ");
		}
		
		if(savep<rightcount){
			savep=rightcount;
			savek=k;
		}
		
		k+=0.1;
		if(k>90)
			break;
	}
		System.out.println("Best k value found at: "+savek+ " with matches: "+savep+" ("+(savep/48.0)*100+")%");
		
//		System.out.println(m_train_alone.getRelativchances());
	}
}
