import java.util.Arrays;



public class ProblemTwo {

	
// File containing three sequences to align
private final static String THREE = "PROBLEM_TWO_THREE_STRINGS.txt";
// File containing five sequences to align
private final static String FIVE = "PROBLEM_TWO_FIVE_STRINGS.txt";
// File containing six sequences to align 
private final static String SIX = "PROBLEM_TWO_SIX_STRINGS.txt";


	
	public static void main (String[] args){
		
		/* Read the sequences */
		
		String[] three =SequenceReader.readSequence(THREE);
		String[] five = SequenceReader.readSequence(FIVE);
		String[] six = SequenceReader.readSequence(SIX);
		
		
		/* Implement the algorithm for 3 strings OR for k strings
 			you can use as many classes and methods as you want */
		
		//Alignment res1 = solverProblemTwo(Arrays.copyOf(three, 3));	
		//Alignment res2 = solverProblemTwo(Arrays.copyOf(five, 3));
		//Alignment res3 = solverProblemTwo(Arrays.copyOf(six, 3));

		/* Test your algorithm using the sequences above */
		
		//print(res1);
		//print(res2);
		//print(res3);
		
		String[] two = {"TA","AT",""};
		Alignment res4 = solverProblemTwo(two);
		print(res4);
		//System.out.println(res1.checkScore());
		System.out.println(res4.checkScore());
		
		

		/* Running this main method should result in the output of the algorithm being printed
		 * to the standard output (console). The output should show the optimal alignment of strings
		 * using - to indicates skips.
		 * 
		 * For example:
		 * 
		 *  CT---ATGCCTCATA
		    -TACAATGCCTCA--
		    ATGCC-T-CATT-GC
            A-A--ATGCATCAGG
            GTGTGATGT-TCA--
            ATG--ATCGATGAT-
		 */
		
	
	}



	public static void print(Alignment res) {
		System.out.println(res);
	}
	
	public static void printMatrix(int m[][]){
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[i].length; j++) {
				if(m[i][j]<10 && m[i][j]>=0)	// 0->9
					System.out.print(m[i][j]+"   ");
				else if(m[i][j]<0 && m[i][j]> -10)	// -9 -> -1
					System.out.print(m[i][j]+"  ");
				else if(m[i][j]<=-10 && m[i][j]> -100)	// -99 -> -10
					System.out.print(m[i][j]+" ");
				else if(m[i][j]<100 && m[i][j]>=10)	// 10->99
					System.out.print(m[i][j]+"  ");
				else
					System.out.print(m[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}



	private static Alignment solverProblemTwo(String[] sequences) {
		Alignment al = new Alignment(sequences);
		int[][][] pathMatrix = al.solveAndSetScore();
		al.alignStrings(pathMatrix);
		return al;
	}

}
