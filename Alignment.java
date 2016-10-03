public class Alignment {

	private StringBuilder[] alignedSequences; // het uiteindelijke resultaat
	private String[] inputSequences; // de input van sequenties
	private char[][] modifiedInput; // de aangepaste input
	private int score; // de eindscore

	public Alignment(String[] inputSequences) {
		this.inputSequences = inputSequences;
		this.modifiedInput = new char[inputSequences.length][];
		this.alignedSequences = new StringBuilder[inputSequences.length];

		for (int i = 0; i < inputSequences.length; i++) {
			// verander de input lichtjes om er later makkelijker mee te kunnen werken
			String temp = '-' + inputSequences[i];
			this.modifiedInput[i] = temp.toCharArray();
			this.alignedSequences[i] = new StringBuilder(30);
		}

		this.score = 0;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	// methode om de alignment uit te printen. Is owv esthetische redenen redelijk buitensporig geworden
	public String toString() { 
		String res = "&------------------------&\n";
		res += "| Final Alignment:       |\n|------------------------|\n|";
		res += " Input Strings:         |\n|------------------------|\n";
		for (int i = 0; i < inputSequences.length; i++) {
			res += "| " + inputSequences[i];
			for (int j = 0; j < 23 - inputSequences[i].length(); j++)
				res += ' ';
			res += "|\n";
		}
		res += "|------------------------|\n| Aligned Strings:       |\n|------------------------|\n";
		for (int i = 0; i < alignedSequences.length; i++) {
			res += "| " + alignedSequences[i].toString();
			for (int j = 0; j < 23 - alignedSequences[i].length(); j++)
				res += ' ';
			res += "|\n";
		}
		res += "|------------------------|\n| Score:  " + score;
		for (int i = 0; i < 14 - Math.log10(score); i++) {
			res += ' ';
		}
		res += "|\n&------------------------&\n";
		return res;
	}

	public int[][][] solveAndSetScore() {
		int[][][] m = new int[modifiedInput[0].length][modifiedInput[1].length][modifiedInput[2].length];
		// scorematrix voor 3D
		int[][][] s = new int[modifiedInput[0].length][modifiedInput[1].length][modifiedInput[2].length];
		// padmatrix voor 3D

		m[0][0][0] = 0; // beginpunt
		s[0][0][0] = 0;

		// initialisering van de grenskolommen
		for (int i = 1; i < m.length; i++) {
			m[i][0][0] = -3*i; // wat je ervan aftrekt is combinatie van 2 uit k want je hebt voor alle combinaties -1 want allemaal streepjes en mismatch
			s[i][0][0] = 1;
		}
		for (int j = 1; j < m[0].length; j++) {
			m[0][j][0] = -3*j;
			s[0][j][0] = 2;
		}
		for (int k = 1; k < m[0][0].length; k++) {
			m[0][0][k] = -3*k;
			s[0][0][k] = 3;
		}

		// initialisering van het i,j-vlak
		for (int i = 1; i < m.length; i++) {
			for (int j = 1; j < m[0].length; j++) {
				int max = m[i - 1][j - 1][0];
				int index = 12;
				if (m[i][j - 1][0] > max) {
					max = m[i][j - 1][0];
					index = 1;
				}
				if (m[i - 1][j][0] > max) {
					max = m[i - 1][j][0];
					index = 2;
				}
				m[i][j][0] = max
						+ calcScore(modifiedInput[0][i], modifiedInput[1][j],
								modifiedInput[2][0]);
				s[i][j][0] = index;
			}
		}
		// initialisering van het i,k-vlak
		for (int i = 1; i < m.length; i++) {
			for (int k = 1; k < m[0][0].length; k++) {
				int max = m[i - 1][0][k - 1];
				int index = 13;
				if (m[i][0][k - 1] > max) {
					max = m[i][0][k - 1];
					index = 3;
				}
				if (m[i - 1][0][k] > max) {
					max = m[i - 1][0][k];
					index = 1;
				}
				m[i][0][k] = max
						+ calcScore(modifiedInput[0][i], modifiedInput[1][0],
								modifiedInput[2][k]);
				s[i][0][k] = index;
			}
		}
		// initialisering van het j,k-vlak
		for (int j = 1; j < m[0].length; j++) {
			for (int k = 1; k < m[0][0].length; k++) {
				int max = m[0][j - 1][k - 1];
				int index = 23;
				if (m[0][j][k - 1] > max) {
					max = m[0][j][k - 1];
					index = 3;
				}
				if (m[0][j - 1][k] > max) {
					max = m[0][j - 1][k];
					index = 2;
				}
				m[0][j][k] = max
						+ calcScore(modifiedInput[0][0], modifiedInput[1][j],
								modifiedInput[2][k]);
				s[0][j][k] = index;
			}
		}

		// berekening van de rest
		for (int i = 1; i < m.length; i++) {
			for (int j = 1; j < m[0].length; j++) {
				for (int k = 1; k < m[0][0].length; k++) {

					int max = m[i - 1][j - 1][k - 1];
					int index = 123;

					if (m[i - 1][j - 1][k] > max) {
						max = m[i - 1][j - 1][k];
						index = 12;
					}
					if (m[i - 1][j][k - 1] > max) {
						max = m[i - 1][j][k - 1];
						index = 13;
					}
					if (m[i][j - 1][k - 1] > max) {
						max = m[i][j - 1][k - 1];
						index = 23;
					}
					if (m[i - 1][j][k] > max) {
						max = m[i - 1][j][k];
						index = 1;
					}
					if (m[i][j - 1][k] > max) {
						max = m[i][j - 1][k];
						index = 2;
					}
					if (m[i][j][k - 1] > max) {
						max = m[i][j][k - 1];
						index = 3;
					}

					m[i][j][k] = max + calcScore(modifiedInput[0][i], modifiedInput[1][j], modifiedInput[2][k]);
					s[i][j][k] = index;
				}
			}
		}

		setScore(m[m.length - 1][m[0].length - 1][m[0][0].length - 1]); 
		// de optimale waarde bevindt zich in de hoek

		return s;
	}

	public void alignStrings(int[][][] s) {
		int i = modifiedInput[0].length - 1, j = modifiedInput[1].length - 1, k = modifiedInput[2].length - 1;

		while (s[i][j][k] != 0) {
			switch (s[i][j][k]) {
			case 123:
				alignedSequences[0].insert(0, modifiedInput[0][i]);
				alignedSequences[1].insert(0, modifiedInput[1][j]);
				alignedSequences[2].insert(0, modifiedInput[2][k]);
				i--;
				j--;
				k--;
				break;
			case 12:
				alignedSequences[0].insert(0, modifiedInput[0][i]);
				alignedSequences[1].insert(0, modifiedInput[1][j]);
				alignedSequences[2].insert(0, '-');
				i--;
				j--;
				break;
			case 13:
				alignedSequences[0].insert(0, modifiedInput[0][i]);
				alignedSequences[1].insert(0, '-');
				alignedSequences[2].insert(0, modifiedInput[2][k]);
				i--;
				k--;
				break;
			case 23:
				alignedSequences[0].insert(0, '-');
				alignedSequences[1].insert(0, modifiedInput[1][j]);
				alignedSequences[2].insert(0, modifiedInput[2][k]);
				j--;
				k--;
				break;
			case 1:
				alignedSequences[0].insert(0, modifiedInput[0][i]);
				alignedSequences[1].insert(0, '-');
				alignedSequences[2].insert(0, '-');
				i--;
				break;
			case 2:
				alignedSequences[0].insert(0, '-');
				alignedSequences[1].insert(0, modifiedInput[1][j]);
				alignedSequences[2].insert(0, '-');
				j--;
				break;
			case 3:
				alignedSequences[0].insert(0, '-');
				alignedSequences[1].insert(0, '-');
				alignedSequences[2].insert(0, modifiedInput[2][k]);
				k--;
				break;

			default:
				break;
			}

		}

	}

	public static int calcScore(char c, char d, char e) {
		int som = 0;
		if (c == d && c != '-')
			som += 1;
		else
			som += -1;
		if (c == e && c != '-')
			som += 1;
		else
			som += -1;
		if (d == e && d != '-')
			som += 1;
		else
			som += -1;

		return som;
	}
	
	public int checkScore(){
		int som = 0;
		for (int i = 0; i < alignedSequences[0].length(); i++) {
			som+=calcScore(alignedSequences[0].charAt(i), alignedSequences[1].charAt(i), alignedSequences[2].charAt(i));
		}		
		
		return som;
	}

}
