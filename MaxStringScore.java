//Deze klasse is aangemaakt en geprogrammeerd door Amato op 15/04 om 16u20.
/*Deze klasse is volledig gebouwd rond de methode maxStringCalculator.
 * oorspronkelijk was deze niet in een klasse gegoten en stond die volledig in de methode exhaustiveSubStringSearch.
 * Deze is dan in een klasse gegoten om deze dan te kunnen herbruiken in branchAndBoundSearch.
 */

public class MaxStringScore {
	private int maxScore;									// heeft vergelijkbare elementen als Consensus, echter, om verwarring te 
	private String maxString;								// vermijden is dit in een nieuwe klasse gezet

	public MaxStringScore(int maxScore, String maxString) {	// constructor
		this.maxScore = maxScore;
		this.maxString = maxString;
	}

	public MaxStringScore() {								// default constructor
		this(0, "");
	}

	public int geefScore() {								// basis bewerkingen op objecten van de klasse
		return maxScore;
	}

	public String geefString() {
		return maxString;
	}

	public void wijzigScore(int maxScore) {
		this.maxScore = maxScore;
	}

	public void wijzigString(String maxString) {
		this.maxString = maxString;
	}

	public MaxStringScore maxStringCalculator(String[] sequences, String woord) {	// de hoofdmethode van deze klasse
		int maxScore = this.geefScore(), subStringLength = woord.length();			// bepaalt op basis van een woord uit de boom de
		String maxString = this.geefString();										// maximale score mogelijk met die woord en de
																					// overeenkomstige String, gebruikt hier voor de rij
		int maxSubScore = 0;														// met sequences en de algemene maxScore
		String maxSubString[] = new String[sequences.length];

		for (int i = 0; i < sequences.length; i++) {								// overloopt alle sequenties en vergelijkt 1-aan-1
			String sequentie = sequences[i];										// het woord met een mogelijke substring
			String[] rij = { sequentie, woord };
			for (int j = 0; j <= sequentie.length() - subStringLength; j++) {		// overloopt hier alle substrings van een sequentie
				int[] start = { j, 0 };
				Consensus tussenConsensus = Consensus.calculateConsensus(rij, subStringLength, start);
				if (tussenConsensus.getScore() > maxSubScore) {						// is de overeenkomstige score van de substring groter
					maxSubScore = tussenConsensus.getScore();						// de vorige; onthoud die en stockeer de overeenkomstige
					maxSubString[i] = tussenConsensus.getConsensusString();			// String in een tabel die de beste String van elke
				}																	// sequentie bijhoudt

			}
			maxSubScore = 0;

		}
		int[] start = new int[maxSubString.length];

		Consensus eindConsensus = Consensus.calculateConsensus(maxSubString, subStringLength, start);
		if (eindConsensus.getScore() > maxScore) {				// bepaalt de consensusScore en consensusString van alle gevonden 
			maxScore = eindConsensus.getScore();				// maxSubStrings. Is deze nieuwe maxScore die overeenkomt met het 
			maxString = eindConsensus.getConsensusString();		// meegegeven woord groter dan het huidige absolute maximum wordt deze 
		}														// vervangen

		this.wijzigScore(maxScore);
		this.wijzigString(maxString);

		return this;											// returnt de gevonden String en score 
	}
}
