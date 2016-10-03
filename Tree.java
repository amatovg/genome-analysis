public class Tree {
	// Deze klasse is afgewerkt normaal gezien.

	private static final char[] LET = { 'A', 'T', 'C', 'G' };	// de enige letters waaruit de boom kan opgebouwd worden; 
																// dit vergemakkelijkt verwijzigingen en bewerkingen hierop
	private int subStringLength;								// de maximumlengte van de knopen van de boom

	public StringBuilder currentNode;							// de knoop op die plaats in de boom, StringBuilder gekozen omdat
																// bewerkingen hier veel sneller op zijn
	public Tree(int subStringLength) {							// Constructor van de boom.

		this.currentNode = new StringBuilder();
		this.subStringLength = subStringLength;

	}

	public String nextTreeNode() {
		// Geprogammeerd door Amato op 14/04 om 0u59: methode om de boom te doorlopen.

		if (currentNode.length() == 0) {						// als de root leeg is voegen we 'A' toe
			currentNode.append(LET[0]);							
			return currentNode.toString();
		}
		char c1 = currentNode.charAt(currentNode.length() - 1), c2, c3 = LET[3];
		int i = indexOf(c1), j;									// gaat na welke positie c1 in LET heeft

		if (currentNode.length() == subStringLength) {			// we zitten in een blad van de boom
			currentNode = currentNode.deleteCharAt(subStringLength - 1);
			if (c1 != c3) {
				c2 = LET[i + 1];								// verwijder laatste letter, indien niet 'G' en voeg de volgende toe
				currentNode = currentNode.append(c2);
			} else {
				while (c1 == c3 && currentNode.length() != 0) {	// verwijder de laatste letter tot wanneer die geen 'G' meer is
					c1 = currentNode.charAt(currentNode.length() - 1);
					currentNode = currentNode.deleteCharAt(currentNode.length() - 1);
				}
				j = indexOf(c1);
				if (j == LET.length - 1)						// voeg de volgende letter in LET toe, behalve wanneer we
					return null;								// op het einde van de boom zitten
				c2 = LET[j + 1];
				currentNode.append(c2);
			}
		} else
			currentNode = currentNode.append(LET[0]);			// als het geen blad van de boom is voegen we gewoon 'A' toe

		return currentNode.toString();

	}

	public String skipSubTree() {

		// Geprogrammeerd door Amato op 15/04 om 15u20: 
		// methode om een hele subtree over te slaan voor het branch and bound algoritme
		int eind = currentNode.length() - 1;					// index van het laatste karakter in de interne knoop

		while (currentNode.charAt(eind) == 'G') {				// zolang het laatste karakter 'G' is blijven we deze verwijderen
			currentNode = currentNode.deleteCharAt(eind);		
			eind--;

			if (eind == 0) {									// als we bij de knoop 'G' zitten kan er niet naar een sibling
				if (currentNode.charAt(eind) == 'G')			// gegaan worden -> return null
					return null;
				else
					break;										// is het een andere letter kunnen we gewoon verdergaan
			} else if (eind < 0)								// indien de knoop 'G' meegegeven wordt zitten we aan een speciaal geval
				return null;									// deze code vangt dit op

		}

		
		int ind = indexOf(currentNode.charAt(eind));			// werd er geen speciaal geval gevonden, staat er nu geen 'G'
		currentNode = currentNode.deleteCharAt(eind);			// op het einde, we kunnen dus een sibling selecteren door er
		currentNode = currentNode.append(LET[ind + 1]);			// de volgende letter aan toe te voegen
		

		return currentNode.toString();

	}

	private static int indexOf(char c) {						// hulpmethode om snel de index van een karakter in LET te bepalen
		int i;
		switch (c) {
		case 'A':
			i = 0;
			break;
		case 'T':
			i = 1;
			break;
		case 'C':
			i = 2;
			break;
		case 'G':
			i = 3;
			break;
		default:
			i = -1;
		}

		return i;
	}

}
