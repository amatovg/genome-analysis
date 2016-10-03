import java.util.*;

public class ProblemOne {

	
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
		

		

		
		public static void main (String[] args){
			
			Solvers solve = new Solvers();
			

			/* Assignment 1 :
			 *  1) Complete the method 'calculateConsensus' in the class Consensus
			 *  2) Implement an exhaustive approach (exhaustiveStartSearch) in the Solver class to the problem using the calculateConsensus method.
			 *  3) Uncomment the following test code and use it to test your implementation and to answer the following questions
			 *     in your written report. You can add and run additional tests if you want, but do not remove the original ones.
			 *     
			 *     
			 *   
			 *   Questions:
			 *   Run the exhaustive algorithm for an increasing amount of sequences, start with 2 and increase the amount of sequences until the algorithm takes too much time to finish. 
			 *   Write down the average execution time for each run. Does the algorithm find the correct motif and why?  Use S_800_L_60_NM_8.
			 *   
			 *   Repeat the same experiment with the mutated sequence S_800_L_60_MU_8. Answer the same questions. 
			 *   
			 *   Now increase the sequence length. Keep the amount of sequences constant at 4.
			 *   Use sequences : S_800_L_60_MU_8, S_800_L_80_MU_8, S_800_L_90_MU_8
			 *   Write down the average execution time.
			 *   
			 *   Now increase the substring length. Keep the amount of sequences constant at 4.
			 *   Use sequences : S_800_L_60_MU_8, S_800_L_60_MU_10, S_800_L_60_MU_15
			 *   		    
			 *   Can you explain your measurements by deriving intuitively a big 0 notation for the complexity of this algorithm? 
			 *   
			 *   
			 *  
			 */

			
				// Tests
				String[] sequences =SequenceReader.readSequence(S_800_L_60_NM_8);
				//solve.solve(Arrays.copyOf(sequences, 2), Solvers.EXHAUSTIVE_ALL_START_SEARCH,SHORT_STRING_8 );
				//solve.solve(Arrays.copyOf(sequences, 3), Solvers.EXHAUSTIVE_ALL_START_SEARCH,SHORT_STRING_8 );
				//solve.solve(Arrays.copyOf(sequences, 4), Solvers.EXHAUSTIVE_ALL_START_SEARCH,SHORT_STRING_8 );
				//solve.solve(Arrays.copyOf(sequences, 5), Solvers.EXHAUSTIVE_ALL_START_SEARCH,SHORT_STRING_8 );
				
				
				// Tests
				sequences =SequenceReader.readSequence(S_800_L_60_MU_8);
				//solve.solve(Arrays.copyOf(sequences, 2), Solvers.EXHAUSTIVE_ALL_START_SEARCH,SHORT_STRING_8 );
				//solve.solve(Arrays.copyOf(sequences, 3), Solvers.EXHAUSTIVE_ALL_START_SEARCH,SHORT_STRING_8 );
				//solve.solve(Arrays.copyOf(sequences, 4), Solvers.EXHAUSTIVE_ALL_START_SEARCH,SHORT_STRING_8 );
				//solve.solve(Arrays.copyOf(sequences, 5), Solvers.EXHAUSTIVE_ALL_START_SEARCH,SHORT_STRING_8 );
				
				// Tests
				sequences =SequenceReader.readSequence(S_800_L_60_MU_8);
				//solve.solve(Arrays.copyOf(sequences, 4), Solvers.EXHAUSTIVE_ALL_START_SEARCH,SHORT_STRING_8 );
				sequences =SequenceReader.readSequence(S_800_L_80_MU_8);
			    //solve.solve(Arrays.copyOf(sequences, 4), Solvers.EXHAUSTIVE_ALL_START_SEARCH,SHORT_STRING_8 );
				sequences =SequenceReader.readSequence(S_800_L_90_MU_8);
				//solve.solve(Arrays.copyOf(sequences, 4), Solvers.EXHAUSTIVE_ALL_START_SEARCH,SHORT_STRING_8 );

				// Tests
				sequences =SequenceReader.readSequence(S_800_L_60_MU_8);
				//solve.solve(Arrays.copyOf(sequences, 4), Solvers.EXHAUSTIVE_ALL_START_SEARCH,SHORT_STRING_8 );
				sequences =SequenceReader.readSequence(S_800_L_60_MU_10);
				//solve.solve(Arrays.copyOf(sequences, 4), Solvers.EXHAUSTIVE_ALL_START_SEARCH,MED_STRING_10 );
				sequences =SequenceReader.readSequence(S_800_L_60_MU_15);
				//solve.solve(Arrays.copyOf(sequences, 4), Solvers.EXHAUSTIVE_ALL_START_SEARCH,LONG_STRING_15 );

				// END ASSIGNMENT 1
				
			
			
			
			
			/* Assignment 2 :
			 *  1) Complete the method nextTreeNode in the class Tree to loop over all possible substrings. // Done, zie de methode in tree.java
			 *  2) Implement an exhaustive approach (exhaustiveSubStringSearch) in the Solver class to the class Tree. // Done zie de methode in Solvers.java
			 *  3) Uncomment the following testcode to answer the questions below.
			 *     Run additional tests if needed.
			 *     
			 *     
			 *     Questions:
			 *     
			 *
 			 *	   Run the exhaustive algorithm for an increasing amount of sequences, start with 2 and increase the amount of sequences until the algorithm takes too much time to finish. 
			 *     Write down the average execution time for each run. Does the algorithm find the correct motif and why?  Use S_800_L_60_MU_8.
			 *   
			 *     Now increase the substring length. Keep the amount of sequences constant.
			 *     Use sequences : S_800_L_60_MU_8, S_800_L_60_MU_10, S_800_L_60_MU_15
			 *     Abort if the algorithm takes too much time to finish. 
			 *     
			 *     
			 *     Run additional tests that might give insight in the complexity of this algorithm if needed.
			 *     Can you derive intuitively a big 0 notation of the complexity of this approach?
			 *     
			 *     Compare the two exhaustive approaches briefly, which one would you use for which kind of sequence parameters (sequence length, substring length, amount of sequences...)
			 *     
			 *     
			 *     
			 */
			
				
				// Tests
				sequences =SequenceReader.readSequence(S_800_L_60_MU_8);
				//solve.solve(Arrays.copyOf(sequences, 2), Solvers.EXHAUSTIVE_ALL_SUBSTRING_SEARCH,SHORT_STRING_8 );	//5.270s  ACTAGACT
				//solve.solve(Arrays.copyOf(sequences, 3), Solvers.EXHAUSTIVE_ALL_SUBSTRING_SEARCH,SHORT_STRING_8 );	//8.004s  AAGACGAT
				//solve.solve(Arrays.copyOf(sequences, 4), Solvers.EXHAUSTIVE_ALL_SUBSTRING_SEARCH,SHORT_STRING_8 );	//10.505s ACGACTAG
				//solve.solve(Arrays.copyOf(sequences, 5), Solvers.EXHAUSTIVE_ALL_SUBSTRING_SEARCH,SHORT_STRING_8 );	//12.822s ACGACTAG
				/*solve.solve(Arrays.copyOf(sequences, 6), Solvers.EXHAUSTIVE_ALL_SUBSTRING_SEARCH,SHORT_STRING_8 );	//16.312s ACGACTAG
				
				sequences =SequenceReader.readSequence(S_800_L_60_NM_8);
				solve.solve(Arrays.copyOf(sequences, 2), Solvers.EXHAUSTIVE_ALL_SUBSTRING_SEARCH,SHORT_STRING_8 );		//5.208s  ACGACTAG
				solve.solve(Arrays.copyOf(sequences, 3), Solvers.EXHAUSTIVE_ALL_SUBSTRING_SEARCH,SHORT_STRING_8 );		//7.802s  ACGACTAG
				solve.solve(Arrays.copyOf(sequences, 4), Solvers.EXHAUSTIVE_ALL_SUBSTRING_SEARCH,SHORT_STRING_8 );		//10.253s ACGACTAG
				solve.solve(Arrays.copyOf(sequences, 5), Solvers.EXHAUSTIVE_ALL_SUBSTRING_SEARCH,SHORT_STRING_8 );		//12.662s ACGACTAG
				solve.solve(Arrays.copyOf(sequences, 6), Solvers.EXHAUSTIVE_ALL_SUBSTRING_SEARCH,SHORT_STRING_8 );		//15.276s ACGACTAG
				//Bovenstaande code is er zelf bij geplaatst als testcode*/
				
				// Tests
				sequences =SequenceReader.readSequence(S_800_L_60_MU_8);
				//solve.solve(Arrays.copyOf(sequences, 8), Solvers.EXHAUSTIVE_ALL_SUBSTRING_SEARCH,SHORT_STRING_8 );	//20,900s  CGACTAGA
				sequences =SequenceReader.readSequence(S_800_L_60_MU_10);
				//solve.solve(Arrays.copyOf(sequences, 8), Solvers.EXHAUSTIVE_ALL_SUBSTRING_SEARCH,MED_STRING_10 );		//367,217s ATGAATATGC
				sequences =SequenceReader.readSequence(S_800_L_60_MU_15);
				//solve.solve(Arrays.copyOf(sequences, 8), Solvers.EXHAUSTIVE_ALL_SUBSTRING_SEARCH,LONG_STRING_15 );

				

			
			/* Assignment 3 : Implement this if you chose to do a branch and bound extension of assignment 2 : 
			 *  1) Complete the method SkipSubTree in the class Tree. This method skips all the children of the current node and returns the next sibling.
			 *  2) Implement a branch and bound approach extensions of exhaustive approach 2 using the SkipSubTree method the at each internal node of the tree. 
			 *     Skip subtrees when the most optimistic continuation of the partial string cannot lead to a better result.
			 *  3) Can you find a more aggressive bound than the most optimistic continuation which will allow you to skip more subtrees? The algorithm should however continue to return the correct result.    
			 *     Which bound did you use?
			 *  
			 *  4) Test your algorithm freely with the available sequences. 
			 *  
			 *  
			 */
				
			// Tests
			/*sequences =SequenceReader.readSequence(S_800_L_60_NM_8);			// zelf toegevoegd
			solve.solve(Arrays.copyOf(sequences, 8), Solvers.BB_SEARCH,SHORT_STRING_8 );*/
			sequences =SequenceReader.readSequence(S_800_L_60_MU_15);						// zelf toegevoegd
			//solve.solve(Arrays.copyOf(sequences, 8), Solvers.BB_SEARCH,LONG_STRING_15 );		//40.342s	GATCTAGATGGAAAA
			sequences =SequenceReader.readSequence(S_800_L_60_MU_8);
			//solve.solve(Arrays.copyOf(sequences, 8), Solvers.BB_SEARCH,SHORT_STRING_8 );		//0.758s	CGACTAGA
			sequences =SequenceReader.readSequence(S_800_L_60_MU_10);
			//solve.solve(Arrays.copyOf(sequences, 8), Solvers.BB_SEARCH,MED_STRING_10 );			//4.270s	ATGAATATGC
			sequences =SequenceReader.readSequence(S_800_L_60_MU_15);
			//solve.solve(Arrays.copyOf(sequences, 8), Solvers.BB_SEARCH,LONG_STRING_15 );		//29.032s	GATCTAGATGGAAAA
			
			// Tests
			sequences =SequenceReader.readSequence(S_800_L_60_MU_10);							
			solve.solve(Arrays.copyOf(sequences, 800), Solvers.BB_SEARCH,MED_STRING_10 );		//170.777s	ATGAATATGC 

		
			
			/* Assignment 4: Implement this if you chose to do a greedy variation of the first exhaustive approach: 
			 *  1) Implement a greedy approach to the motif finding problem:
			 *     First find the optimal starting positions for the first 2 sequences using an exhaustive search.
			 *     Next, add one sequence at a time and determine the optimal starting position of this sequence  to have the highest consensus score 
			 *  
			 *  2) Run the algorithm for sequence sequence S_800_L_750_NM_16 (Non-mutated!), use EXTRA_LONG_MOTIF_16 for motif length.
			 *     Does the algorithm work correctly? Check for a different amount of sequences.
			 *  
			 *  3) Now run the algorithm for the sequence S_800_L_750_MU_16 (mutated), use EXTRA_LONG_MOTIF_16 for motif length.
			 *  	Does the algorithm still find the correct result? Why? Check for a different amount of sequences.
			 *      
			 *  4) Can you find and implement a modification of the greedy algorithm which will increase the chances of finding the correct motif?
			 *  
			 *  	
			 *  5) Test your algorithm freely with the available sequences. 
			 */
			sequences = SequenceReader.readSequence(S_800_L_750_NM_16);	
			//solve.solve(Arrays.copyOf(sequences, 2), Solvers.GREEDY_SEARCH , EXTRA_LONG_STRING_16);
			sequences = SequenceReader.readSequence(S_800_L_750_MU_16);	
			//solve.solve(Arrays.copyOf(sequences, 2), Solvers.GREEDY_SEARCH , EXTRA_LONG_STRING_16);

			
				
			
			
			
		}
}
