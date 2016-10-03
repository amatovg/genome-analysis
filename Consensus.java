
public class Consensus {
	private static final char[] LET = { 'A', 'T', 'C', 'G' };		// de enige letters waaruit de boom kan opgebouwd worden; 			
	private int score;												// dit vergemakkelijkt verwijzigingen en bewerkingen hierop
	private String consensusString;

	private Consensus(int score, String consensusString) {			// constructor van een element, wordt alleen inwendig gebruikt
		this.score = score;
		this.consensusString = consensusString;
	}

	public static Consensus calculateConsensus(String[] sequences, int subStringLength, int[] startPositions) {

		// Geprogammeerd door Amato op 13/04 om 13u39: methode om de
		// consensusString en score te berekenen.

		char[][] alignMat = new char[sequences.length][subStringLength];	// stelt de alignmentmatrix op
		for (int i = 0; i < sequences.length; i++) {
			alignMat[i] = (sequences[i]).substring(startPositions[i], startPositions[i] + subStringLength).toCharArray();
		}

		StringBuilder consensusString = new StringBuilder(subStringLength);	// StringBuilder wordt gebruikt omdat er stelselmatig
		int score = 0, maxpositie, k;										// elementen aan worden toegevoegd
		int[] tussenScore = new int[4];

		for (int j = 0; j < subStringLength; j++) {							// we overlopen het aantal kolommen van de matrix 
			maxpositie = 0;													// en berekenen van elk de score
			for (int i = 0; i < sequences.length; i++) {					// we overlopen hier de rijen van de matrix

				switch (alignMat[i][j]) {									// bepaalt de index van een bepaald element in de rij
				case 'A':													// van frequenties (tussenScore)
					k = 0;
					break;
				case 'T':
					k = 1;
					break;
				case 'C':
					k = 2;
					break;
				default:
					k = 3;
				}
				if (tussenScore[k] == tussenScore[maxpositie])				// zorgt ervoor dat maxpositie naar het grootste element
					maxpositie = k;											// in de rij wijst.
				tussenScore[k]++;

			}
			score += tussenScore[maxpositie];								// bij de consensusscore wordt de maximale score van het
			consensusString = consensusString.append(LET[maxpositie]);		// element opgeteld en het karakter wordt bij de String gevoegd
			for (int i = 0; i < 4; i++) {									// reset alle waarden
				tussenScore[i] = 0;
			}
		}

		Consensus eind = new Consensus(score, consensusString.toString());	// de gevonden score en String worden gereturned
		return eind;

	}

	public int getScore() {													// methodes voor basisbewerkingen op objecten van de klasse
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getConsensusString() {
		return consensusString;
	}

	public void setConsensusString(String consensusString) {
		this.consensusString = consensusString;
	}

}
