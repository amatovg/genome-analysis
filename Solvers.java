import java.util.*;

public class Solvers {

	public static final int EXHAUSTIVE_ALL_START_SEARCH = 0;					// klasseconstanten
	public static final int EXHAUSTIVE_ALL_SUBSTRING_SEARCH = 1;
	public static final int BB_SEARCH = 2;
	public static final int GREEDY_SEARCH = 3;

	public Solvers() {
	}

	public void solve(String[] sequences, int method, int subStringLength) {	//meegeleverde methode om een van de strategieen te 
																				// gebruiken
		long before = System.currentTimeMillis();
		String subString;

		switch (method) {

		case EXHAUSTIVE_ALL_START_SEARCH:
			subString = this.exhaustiveStartSearch(sequences, subStringLength);
			break;
		case EXHAUSTIVE_ALL_SUBSTRING_SEARCH:
			subString = this.exhaustiveSubStringSearch(sequences,subStringLength);
			break;
		case BB_SEARCH:
			subString = this.branchAndBoundSearch(sequences, subStringLength);
			break;
		case GREEDY_SEARCH:
			subString = this.greedySearch(sequences, subStringLength);
			break;
		default:
			System.err.println("Unknown method specified for solve");
			subString = null;

		}

		System.out.println("Execution time:"
				+ (System.currentTimeMillis() - before));
		System.out.println(subString);

	}

	private String exhaustiveStartSearch(String[] sequences, int subStringLength) { 

		// Implement here
				int aantal_strings=sequences.length;
				int lengte_strings=sequences[0].length();
				int [] startposities= new int [aantal_strings];
				int [] beginposities= new int [aantal_strings];					
				for (int i=0;i<aantal_strings;i++){
					beginposities[i]=0;
				}
				for (int i=0;i<aantal_strings;i++){
					startposities[i]=0;
				}
				int best_score=0;
				String best_string="";
				do {
					Consensus current_consensus=Consensus.calculateConsensus(sequences,subStringLength,startposities);
					if (current_consensus.getScore()>best_score){
						best_score=current_consensus.getScore();
						best_string=current_consensus.getConsensusString();
					}
					startposities=next_position(startposities,lengte_strings,subStringLength);
				}while (!Arrays.equals(beginposities,startposities));
				
				System.out.println(best_score);
				 // remove when implemented
				//System.err.println("Exhaustive start position not implemented yet.");
				return best_string;

	}
	
	private int[] next_position(int[] huidig,int lengte_strings,int subStringLength ){
		int[] nieuw=huidig;
		for(int i=0;i<huidig.length;i++){
			if(huidig[i]<lengte_strings-subStringLength-1){
				nieuw[i]=huidig[i]+1;
				return nieuw;
			}
			else nieuw[i]=0;
		}
		return nieuw;
	}

	private String exhaustiveSubStringSearch(String[] sequences, int subStringLength) {

		// Geprogammeerd door Amato op 14/04 om 2u40: Exhaustive Approach II.
		// Normaal gezien is dit nu afgewerkt; sterk vermoeden dat dit werkt.
		// Toch niet blijkbaar; geeft foutieve uitvoer
		// Kleine aanpassing door Amato op 14/04 om 14u30: Werkt nu wel
		
		MaxStringScore max = new MaxStringScore();				// zie de overeenkomstige klasse, houdt het maximum bij van alle scores
		String woord="";										// van de al bezochte knopen en de overeenkomste subsequenties
		Tree boom = new Tree(subStringLength);					// initialiseert de boom
	
		while (true) {
			woord = boom.nextTreeNode();						// doorloopt de boom

			if (woord == null)									// is het einde van de boom bereikt, stap uit de lus en geef het resultaat
				break;

			if (woord.length() == subStringLength) {			// is de bezochte knoop een blad, dan kunnen we deze gebruiken om een 
																// score mee te berekenen
				MaxStringScore subMax = new MaxStringScore();	// dit houdt het  voorlopige maximum bij van alle sequenties die we 
				subMax.maxStringCalculator(sequences, woord);	// aan de hand van 1 woord vergelijken
				
				if(subMax.geefScore() > max.geefScore()){		// is dit voorlopige maximum groter dan het absolute maximum, pas deze aan
					max.wijzigScore(subMax.geefScore());
					max.wijzigString(subMax.geefString());
				}

			}

		}
		
		System.out.println("De maximale consensusscore is:\t"+max.geefScore()); // Overbodige code; enkel als controle
		return max.geefString();	// return de maximale subsequentie

	}
	
	

	private String branchAndBoundSearch(String[] sequences, int motifLength) {

		//Geprogrammeerd door Amato op 15/04 om 16u35
		/*Deze methode is erg analoog aan de gewone Exhaustive Search. 
		 * Het grootste verschil zit hem in het gebruiken van een vlag
		 * om te bepalen wanneer er moet geskipt worden of niet. Deze vlag
		 * wordt bepaald aan de hand van (eerst) de optimal behaalbaar score.
		 * Deze bepaling gebeurt in de zelf aangemaakte methode bepaalVlag.
		 */
		
		MaxStringScore max = new MaxStringScore();
		String woord="";
		Tree boom = new Tree(motifLength);
		boolean vlag;

		while (true) {
			
			if(woord.length()!=motifLength){				// we kunnen alleen maar inwendige knopen hebben subtrees
				vlag = bepaalVlag(sequences, motifLength, woord, max);	// zie de methode; bepaalt of we al dan niet skippen
				if(vlag) 									
					woord = boom.skipSubTree();				// vlag = true -> skippen
				else 
					woord = boom.nextTreeNode();			// vlag = false -> loop gewoon verder
			}
			else woord = boom.nextTreeNode();				// als we in een blad zitten skippen we toch nooit

			if (woord == null)								// vanaf hier begint de code die dezelfde is als in exhaustiveSubStringSearch
				break;

			if (woord.length() == motifLength) {
				MaxStringScore subMax = new MaxStringScore();
				subMax.maxStringCalculator(sequences, woord);
				
				if(subMax.geefScore() > max.geefScore()){
					max.wijzigScore(subMax.geefScore());
					max.wijzigString(subMax.geefString());
				}

			}

		}
		
		System.out.println("De maximale consensusscore is:\t"+max.geefScore()); // Overbodige code; enkel als controle
		return max.geefString();
		

	}

	private static boolean bepaalVlag(String[] sequences, int subStringLength, String woord, MaxStringScore max) {
		//Aangemaakt door Amato op 15/04 om 16u40
		//Deze methode bepaalt de vlag die aangeeft of er een subtree moet geskipt worden of niet 
		
		int maxScore = max.geefScore();
				
		if(maxScore == sequences.length*subStringLength){		// maximum haalbare score al bereikt -> skippen, is niet nodig, 
			 return true;										// maar kan algoritme erg verkorten als een exacte match gevonden is
		}		
		
		int optScore=berekenOptScore(sequences,subStringLength,woord); 	// berekent de optimaal haalbare score
			
		if(optScore <= maxScore) {								// is de optimale score kleiner dan het huidige maximum -> skippen
			return true;
		}
		
		return false;											// geen van voorgaande voorwaarden vervuld, doe gewoon verder
	}

	private static int berekenOptScore(String[] sequences, int motifLength, String woord) {
		//Methode aangemaakt door Amato op 15/04 om 18u00
		/* methode aangemaakt om de meest optimale score te berekenen die een interne knoop kan hebben
		 * berekend op basis van de sequenties
		 */
		int[]startOpt=new int[sequences.length];
		int[]startTemp=new int[2];
		
		for (int i = 0; i < sequences.length; i++) {						// overloopt alle sequenties
			int maxScore=0;
			for (int j = 0; j <= sequences[i].length()-motifLength; j++) {	// overloopt alle mogelijke startposities
				String[] temp ={woord,sequences[i]};
				startTemp[1]=j;
				Consensus tussen = Consensus.calculateConsensus(temp,woord.length(),startTemp);
				if(tussen.getScore() > maxScore){							// bepaalt de startposities van de substrings die het best
					startOpt[i]=j;											// met het woord overeenkomen adhv Consensus
					maxScore=tussen.getScore();
					if(maxScore == woord.length()*sequences.length) break;	// als er exacte match tussen de interne knoop en een substring 
				}															// gevonden wordt moet er niet verder gezocht worden
			}
			maxScore=0;
		}
		Consensus opt = Consensus.calculateConsensus(sequences, woord.length(), startOpt);	//de Consensus wordt berekend van alle gevonden
		return opt.getScore()+(motifLength-woord.length())*sequences.length;// substrings, dit is de maximaal haalbare score
	}

	private String greedySearch(String[] sequences, int motifLength) {

		int aantal_strings=sequences.length;
		int lengte_strings=sequences[0].length();
		int [] startposities= new int [aantal_strings];
		int [] beginposities= new int [aantal_strings];		
		int []	besteposities=new int [aantal_strings];
		Consensus current_consensus;
		boolean einde=false;
		for (int i=0;i<2;i++){
			beginposities[i]=0;
		}
		for (int i=0;i<2;i++){
			startposities[i]=0;
		}
		int best_score=0;
		String best_string="0";
		do {
			current_consensus=Consensus.calculateConsensus( Arrays.copyOfRange(sequences, 0, 2),motifLength,startposities);
			if (current_consensus.getScore()>best_score){
				best_score=current_consensus.getScore();
				best_string=current_consensus.getConsensusString();
				besteposities=startposities;
			}
			startposities=next_position(startposities,lengte_strings,motifLength);
			einde=true;
			for (int j=0; j<2; j++){
				if(startposities[j]!=beginposities[j]){
					einde=false;
				}
			}
		}while (einde==false);
		
		int i=2;
		best_score=0;
		while(i<aantal_strings){
			do{
				current_consensus=Consensus.calculateConsensus( Arrays.copyOfRange(sequences, 0, i+1),motifLength,startposities);
				if (current_consensus.getScore()>best_score){
					best_score=current_consensus.getScore();
					best_string=current_consensus.getConsensusString();
					besteposities[i]=startposities[i];
				}
				if (startposities[i]<lengte_strings-motifLength-1){
					startposities[i]++;
				}
				else startposities[i]=0;
			}while(startposities[i]!=0);
			i++;
			startposities=besteposities;
		}
		return best_string; 

	}

}
