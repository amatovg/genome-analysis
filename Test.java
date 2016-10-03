// Java-API; http://docs.oracle.com/javase/7/docs/api/

// Deze klasse is aangemaakt door Amato op 13/04 om 12u59: Implementatie van een testklasse om de geschreven code uit te voeren.

import java.util.*;

public class Test {
	
	// These variables indicate the length of substring to be found
	private final static int SHORT_STRING_8 = 8 ; // Actual hidden substring -> ACGACTAGA
	private final static int MED_STRING_10 = 10 ; // Actual hidden substring -> ATGAATATGC
	private final static int LONG_STRING_15 = 15 ; // Actual hidden substring -> GATCTAGATGGAAAA
	private final static int EXTRA_LONG_STRING_16 = 16 ; // Actual hidden substring -> GATCTAGATGGAAAAT
	
	// These are the different files containing the sequences
	//
	// S: amount of sequences in the files
	// L: length of the sequences in the files
	// MU or NM: Mutated or non-mutated (is the actual hidden substring a bit different in each sequence)
	private final static String S_800_L_60_NM_8 = "S_800_L_60_NM_8.txt";
	private final static String S_800_L_60_MU_8 = "S_800_L_60_MU_8.txt";
	private final static String S_800_L_80_MU_8 = "S_800_L_80_MU_8.txt";
	private final static String S_800_L_90_MU_8 = "S_800_L_90_MU_8.txt";
	private final static String S_800_L_750_MU_16 = "S_800_L_750_MU_16.txt";
	private final static String S_800_L_750_NM_16 = "S_800_L_750_NM_16.txt";
	private final static String S_800_L_60_MU_15 = "S_800_L_60_MU_15.txt";	
	private final static String S_800_L_60_MU_10 = "S_800_L_60_MU_10.txt";
	
	public static void main(String[] args){
		
		//System.out.println("Testen van Consensus.java");
		String[] sequences1 ={ "ATGCTATCTATCTGATGCAATGTGTACACTATGTCATGTA" , "CTGCAGTGTGACTCTACCTGATGCAAGTATCTATGATCTA","GCTGACGCCACTCCTAATCTACTACTACGATCTATCGTAG"};
		String[] sequences2 ={"CTAATG","TCAGGT"};
		/*int[] startPos = {11,19,1};
		Consensus test = Consensus.calculateConsensus(sequences1, 9, startPos);
		System.out.println(test.getConsensusString());
		System.out.println(test.getScore());*/
		
		
		
		System.out.println("\nTesten van Tree.java\n");
		Tree boom = new Tree(4);
		int teller=0;
		String woord=boom.nextTreeNode();
		teller++;
		System.out.println(woord);
		while(true){
			if(woord.charAt(woord.length()-1)=='T' && woord.length()==3) woord=boom.skipSubTree();
			
			else woord = boom.nextTreeNode();
			
			
			System.out.println(woord);
			if(woord==null) break;
			teller++;
		}
		System.out.println(teller);
		
		/*Solvers solve = new Solvers();		
		solve.solve(Arrays.copyOf(sequences1, 3), Solvers.EXHAUSTIVE_ALL_SUBSTRING_SEARCH,9 );
		solve.solve(Arrays.copyOf(sequences2, 2), Solvers.EXHAUSTIVE_ALL_SUBSTRING_SEARCH,5 );*/

		
	}
}
